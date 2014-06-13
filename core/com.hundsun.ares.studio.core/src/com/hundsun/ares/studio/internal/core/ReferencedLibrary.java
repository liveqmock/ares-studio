/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESBundleInfo;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IBasicReferencedLibInfo;
import com.hundsun.ares.studio.core.IDependenceDescriptor;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.core.model.extendable.IExtendAbleModel;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.core.util.Util;

/**
 * 引用包
 * @author sundl
 */
public abstract class ReferencedLibrary extends Openable implements IReferencedLibrary {
	
	private static final Logger logger = Logger.getLogger(ReferencedLibrary.class.getName());
	
	protected IResPathEntry entry;
	
	/**
	 * @param parent
	 */
	public ReferencedLibrary(IARESElement parent, IResPathEntry entry) {
		super(parent);
		this.entry = entry;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.IReferencedLibrary#getRoots()
	 */
	public IARESModuleRoot[] getRoots() throws ARESModelException{
		IARESElement[] children = getChildren();
		IARESModuleRoot[] roots = new IARESModuleRoot[children.length];
		System.arraycopy(children, 0, roots, 0, children.length);
		return roots;
	}

	public IARESModuleRoot getRoot(IPath path) {
		try {
			for (IARESModuleRoot root : getRoots()) {
				if (root.getPath().toString().equals(path.toString())) 
					return root;
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	public IResPathEntry getResPathEntry() {
		return entry;
	}
	
	protected abstract ZipFile getZipFile() throws ZipException, IOException ;
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.Openable#buildStructure(com.hundsun.ares.devtool.v2.internal.core.OpenableElementInfo)
	 */
	@Override
	protected boolean buildStructure(OpenableElementInfo info) throws ARESModelException {
		logger.info("Start Referenced Library: " + getElementName() + ", project: " + getARESProject().getElementName());
		ReferencedLibraryInfo libInfo = (ReferencedLibraryInfo)info;
		ZipFile zipFile = null;
		try {
//			zipFile = new ZipFile(new File(file.getLocation().toOSString()));
			zipFile = getZipFile();
			ZipEntry entry = zipFile.getEntry(PROPERTIE_FILE);
			if (entry == null) {
				// 有.aar文件的才会解析，没有就认为文件格式不正确
				logFileFormatNotValid(null);
			} else {
				// 读取基本信息
				logger.debug("Read RerencedLib description file(.aar) ...");
				ReferencedLibrayUtil.readRefLibInfo(libInfo, zipFile.getInputStream(entry), getARESProject());
				// 如果有project.xml，也读一下
				ZipEntry projEntry = zipFile.getEntry(IARESProjectProperty.PRO_FILE);
				if (projEntry != null)
					ReferencedLibrayUtil.readExtendedInfo((IExtendAbleModel)libInfo.getBasicInfo(), zipFile.getInputStream(projEntry));				
				
				// 解析结构
				logger.debug("Resolve referneced lib structure...");
				resolveRefLibStructure(libInfo, zipFile);
			}
		} catch (Exception e) {
			logFileFormatNotValid(e);
			throw new ARESModelException(e, IStatus.ERROR);
		} finally {
			try {
				if(null != zipFile){
					zipFile.close();
				}
			} catch (IOException e) {
				logger.warn("close zip file failed...", e);
			}
		}
		return true;
	}
	
	// 解析结构
	private void resolveRefLibStructure(ReferencedLibraryInfo info, ZipFile zipFile) {
		// 暂存的 模块根--->模块列表 映射
		Map<IARESModuleRoot, List<IARESModule>> rootsInfo = new HashMap<IARESModuleRoot, List<IARESModule>>();
		Map<IARESModule, List<IARESResource>> modulesInfo = new HashMap<IARESModule, List<IARESResource>>();
		
		Enumeration<? extends ZipEntry> entries = zipFile.entries();				
		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			logger.debug("process entry: " + entry);
			// if on res-path
			IResPathEntry respathEntry = isOnRespath(entry, info.getRespath());
			if (respathEntry != null) {
				String name = entry.getName();
				IPath zipEntryPath = new Path(name);
				IPath resPathEntryPath = respathEntry.getPath();
				
				// since it is on the res-path, there's always a module-root
				logger.debug("create module root...");
				IARESModuleRoot root = new ArchiveARESModuleRoot(this, resPathEntryPath, respathEntry.getType());
				logger.debug("create complete.");
				if (rootsInfo.containsKey(root)) {
					// do nothing
				} else {
					// an module root is found, add into the cache
					logElementFound("Module Root", zipEntryPath);
					rootsInfo.put(root, new ArrayList<IARESModule>());
				}
				
				// if a directory, maybe a module root, a module, or non-ares element
				if (entry.isDirectory()) {									
					if (zipEntryPath.equals(resPathEntryPath)) {	// root
						// do nothing, added already
					} else if (zipEntryPath.segmentCount() > resPathEntryPath.segmentCount()) {
						IPath suffixPath = zipEntryPath.removeFirstSegments(resPathEntryPath.segmentCount());
						IARESModule module = testModule(root, suffixPath);
						if (module != null) {	// module
							List<IARESModule> moduleList = rootsInfo.get(root);
							resolveModule(module, moduleList);
						} else {									// nothing, just log it.
							//logger.fine("Zip entry : " + zipEntryPath + " is not a valid common element, ignored.");
							logPathIgnored(zipEntryPath);
						}
					}
				} else {	// not a directory, a file...
					String fileName = zipEntryPath.lastSegment();
					IResDescriptor desc = getResDescriptor(fileName);
					if (desc != null) {
						IPath modulePath = zipEntryPath.removeFirstSegments(resPathEntryPath.segmentCount()).removeLastSegments(1);
						IARESModule module = testModule(root, modulePath);
						if (module != null) {
							List<IARESModule> moduleList = rootsInfo.get(root);
							resolveModule(module, moduleList);
							
							List<IARESResource> resList;
							if (!modulesInfo.containsKey(module)) {
								resList = new ArrayList<IARESResource>();
								modulesInfo.put(module, resList);
							} else {
								resList = modulesInfo.get(module);
							}
							resList.add(new ArchiveARESResource(module, fileName));
							logElementFound("Resource", zipEntryPath);
						} 
					} else
						logPathIgnored(zipEntryPath);
				}
			}			
		} 
		
		info.rootsInfo = rootsInfo;
		info.moduleInfo = modulesInfo;
		info.setChildren(rootsInfo.keySet().toArray(new IARESElement[0]));
	}
	
	private IResDescriptor getResDescriptor(String filename) {
		// 全名优先
		// 应该先检查文件全名，再检查扩展名
		IResDescriptor desc = ARESResRegistry.getInstance().getResDescriptor(filename);
		if (desc == null) {
			String ext = StringUtils.substringAfterLast(filename, ".");
			desc = ARESResRegistry.getInstance().getResDescriptor(ext);
		}
		return desc;
	}
	
	/**
	 * try to resolve the given path as a module. if succeed, return the module.
	 * @param root the module root which the module belongs to
	 * @param path the path
	 * @return if the give path can be map to a module, return the module, or return null
	 */
	private IARESModule testModule(IARESModuleRoot root, IPath path) {
		String[] names = path.segments();
		if (Util.isValidNamesForModule(names)) {
			return new ArchiveARESModule(root, names);
		}
		return null;
	}
	
	/*
	 * Java 的ZIP API在压缩文件的时候，不会把文件夹作为单独的Entry放入zip文件，所以在解析到一个
	 * 模块的时候，同时需要自动把父模块一层一层自动解析出来，否则内存中会缺少某些模块的信息。
	 */
	private void resolveModule(IARESModule module, List<IARESModule> moduleList) {
		if (!moduleList.contains(module)) {
			moduleList.add(module);
		}
		
		IARESModule parentModule = getParentModule((ArchiveARESModule)module);
		if (parentModule != null) {
			resolveModule(parentModule, moduleList);
		}
	}
	
	private IARESModule getParentModule(ArchiveARESModule module) {
		IARESModuleRoot root = module.getRoot();
		String[] names = module.names;
		if (names.length > 1) {
			String[] parentNames = new String[names.length - 1];
			System.arraycopy(names, 0, parentNames, 0, names.length - 1);
			return new ArchiveARESModule(root, parentNames);
		}
		return null;
	}
	
	// 是否在respath上,如果在，则返回对应的res path entry，否则返回null
	private IResPathEntry isOnRespath(ZipEntry ze, List<IResPathEntry> respath) {
		for (IResPathEntry entry : respath) {
			IPath ePath = new Path(ze.getName());
			IPath rPath = entry.getPath();
			//                          这里模块根本身也认为是在res-path上的
			if (rPath.isPrefixOf(ePath) /*&& rPath.segmentCount() < ePath.segmentCount()*/) {
				return entry;
			}
		}
		return null;
	}
	
	private void logFileFormatNotValid(Exception e) {
		if (e != null) {
			logger.warn(getElementName() + " is not a valid Ares Archive file", e);
		} else
			logger.warn(getElementName() + " is not a valid Ares Archive file");
	}
	
	// 日志记录指定类型的元素被识别
	private void logElementFound(String type, IPath path) {
		if (logger.isInfoEnabled()) {
			logger.info(type + " is found, path is:  " + path);
		}
	}
	
	// 日志记录指定的path没有被处理
	private void logPathIgnored(IPath path) {
		if (logger.isInfoEnabled())
			logger.info("Path is ignored: " + path);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.Openable#createElementInfo()
	 */
	@Override
	protected Object createElementInfo() {
		return new ReferencedLibraryInfo();
	}

//	/* (non-Javadoc)
//	 * @see com.hundsun.ares.devtool.v2.internal.core.Openable#validateExistence(org.eclipse.core.resources.IResource)
//	 */
//	@Override
//	protected IStatus validateExistence(IResource underlyingResource) {
//		if (onResPath() && file.exists())
//			return ARESModelStatus.VERIFIED_OK;
//		
//		return newDoesNotExistStatus();
//	}
//	
//	private boolean onResPath() {
//		for (IResPathEntry entry : getARESProject().getRawResPath()) {
//			if (entry.getPath().equals(file.getProjectRelativePath()))
//				return true;
//		}
//		return false;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.hundsun.ares.studio.core.IARESElement#getCorrespondingResource()
//	 */
//	public IResource getCorrespondingResource() throws ARESModelException {
//		return file;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.hundsun.ares.studio.core.IARESElement#getElementName()
//	 */
//	public String getElementName() {
//		return file.getName();
//	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getElementType()
	 */
	public int getElementType() {
		return REF_LIBRAYR;
	}

//	/* (non-Javadoc)
//	 * @see com.hundsun.ares.studio.core.IARESElement#getPath()
//	 */
//	public IPath getPath() {
//		return file.getFullPath();
//	}

//	/* (non-Javadoc)
//	 * @see com.hundsun.ares.studio.core.IARESElement#getResource()
//	 */
//	public IResource getResource() {
//		return file;
//	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ARESElement#getHandleMementoDelimiter()
	 */
	@Override
	protected char getHandleMementoDelimiter() {
		return AEM_LIB;
	}
	
	protected void getHandleMemento(StringBuffer buff) {
		((ARESElement)getParent()).getHandleMemento(buff);
		buff.append(getHandleMementoDelimiter());
		escapeMementoName(buff, getPath().toString());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ARESElement#getHandleFromMemento(java.lang.String, com.hundsun.ares.studio.internal.core.MementoTokenizer)
	 */
	@Override
	public IARESElement getHandleFromMemento(String token, MementoTokenizer memento) {
		switch (token.charAt(0)) {
		case AEM_MODULEROOT:
			//if (memento.hasMoreTokens()) {
				token = memento.nextToken();
			//}
			Path rootPath = new Path(token);
			ARESElement root = (ARESElement) getRoot(rootPath);
			if (root != null) {
				if ((token = memento.nextToken()) != null && token.charAt(0) == AEM_MODULE) {
					return root.getHandleFromMemento(token, memento);
				} else {
					return root.getHandleFromMemento(memento);
				}
			}
			break;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IReferencedLibrary#getBasicInfo()
	 */
	public IBasicReferencedLibInfo getBasicInfo() {
		try {
			ReferencedLibraryInfo info = (ReferencedLibraryInfo) getElementInfo();
			if (info != null) {
				return info.getBasicInfo();
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IBasicReferencedLibInfo#getDependencies()
	 */
	public List<IDependenceDescriptor> getDependencyDescriptors() {
		IBasicReferencedLibInfo info = getBasicInfo();
		if (info != null)
			return getBasicInfo().getDependencyDescriptors();
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IBasicReferencedLibInfo#getPublishTime()
	 */
	public String getPublishTime() {
		IBasicReferencedLibInfo info = getBasicInfo();
		if (info != null)
			return getBasicInfo().getPublishTime();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IBasicReferencedLibInfo#getNote()
	 */
	public String getNote() {
		IBasicReferencedLibInfo info = getBasicInfo();
		if (info != null)
			return getBasicInfo().getNote();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IBasicReferencedLibInfo#getName()
	 */
	public String getName() {
		IBasicReferencedLibInfo info = getBasicInfo();
		if (info != null)
			return getBasicInfo().getName();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IBasicReferencedLibInfo#getPublisher()
	 */
	public String getPublisher() {
		IBasicReferencedLibInfo info = getBasicInfo();
		if (info != null)
			return getBasicInfo().getPublisher();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IBasicReferencedLibInfo#getContact()
	 */
	public String getContact() {
		IBasicReferencedLibInfo info = getBasicInfo();
		if (info != null)
			return getBasicInfo().getContact();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IBasicReferencedLibInfo#getProvider()
	 */
	public String getProvider() {
		IBasicReferencedLibInfo info = getBasicInfo();
		if (info != null)
			return getBasicInfo().getProvider();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IBasicReferencedLibInfo#getVersion()
	 */
	public String getVersion() {
		IBasicReferencedLibInfo info = getBasicInfo();
		if (info != null)
			return getBasicInfo().getVersion();
		return null;
	}
	
	public String getType() {
		IBasicReferencedLibInfo info = getBasicInfo();
		if (info != null)
			return getBasicInfo().getType();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IBasicReferencedLibInfo#getId()
	 */
	public String getId() {
		IBasicReferencedLibInfo info = getBasicInfo();
		if (info != null)
			return getBasicInfo().getId();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESBundle#getInfo()
	 */
	@Override
	public IARESBundleInfo getInfo() {
		return (IARESBundleInfo) getBasicInfo();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESBundle#getResources(java.lang.String)
	 */
	@Override
	public IARESResource[] getResources(String name) throws ARESModelException {
		List<IARESResource> results = new ArrayList<IARESResource>();
		for (IARESResource res : getResources()) {
			if (res.getElementName().equals(name)) {
				results.add(res);
			}
		}
		return results.toArray(new IARESResource[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESBundle#getResources(java.lang.String[])
	 */
	@Override
	public IARESResource[] getResources(String[] types) throws ARESModelException {
		List<IARESResource> results = new ArrayList<IARESResource>();
		for (IARESModuleRoot root : getModuleRoots()) {
			results.addAll(Arrays.asList(root.getResources(types)));
		}
		return results.toArray(new IARESResource[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESBundle#getResources()
	 */
	@Override
	public IARESResource[] getResources() throws ARESModelException {
		List<IARESResource> results = new ArrayList<IARESResource>();
		for (IARESModuleRoot root : getModuleRoots()) {
			results.addAll(Arrays.asList(root.getResources()));
		}
		return results.toArray(new IARESResource[0]);
	}
	
	public IARESModuleRoot[] getModuleRoots() throws ARESModelException {
		return getRoots();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESBundle#getModuleRoot(java.lang.String)
	 */
	@Override
	public IARESModuleRoot getModuleRoot(String path) throws ARESModelException {
		for (IARESModuleRoot root : getModuleRoots()) {
			if (StringUtils.equals(root.getPath().toString(), path)) {
				return root;
			}
		}
		return null;
	}
	
	public IARESResource[] findResource(String fullyQualifiedName) throws ARESModelException {
		List<IARESResource> resources = new ArrayList<IARESResource>();
		for (IARESResource res : getResources()) {
			if (res.getFullyQualifiedName().equals(fullyQualifiedName)) {
				resources.add(res);
			}
		}
		return resources.toArray(new IARESResource[0]);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProject#findResource(java.lang.String, java.lang.String)
	 */
	public IARESResource findResource(String name, String type) throws ARESModelException {
		if (name == null || type == null)
			return null;
		
		String pkgName = "";
		String fileName = name;
		
		if (name.indexOf('.') != -1) {
			pkgName = StringUtils.substringBeforeLast(name, ".");
			fileName = StringUtils.substringAfterLast(name, ".");
		}
		
		for (IARESModuleRoot root : getModuleRoots()) {
			IARESModule module = root.getModule(pkgName);
			if (module.exists()) {
				IARESResource res = module.getARESResource(fileName, type);
				if (res != null && res.exists()) {
					return res;
				}
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESBundle#getRequiredBundles()
	 */
	@Override
	public IARESBundle[] getRequiredBundles() {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ARESElement#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ReferencedLibrary)) {
			return false;
		}
		ReferencedLibrary other = (ReferencedLibrary) o;
		return super.equals(o) && this.getPath().equals(other.getPath());
	}

	@Override
	public int hashCode() {
		// 内部和外部文件可能重名，所以需要用路径来区分hashcode
		return Util.combineHashCodes(getPath().hashCode(), parent.hashCode());
	}
}
