/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESModelStatus;
import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESBundleInfo;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IDependencyUnit;
import com.hundsun.ares.studio.core.IExternalResPathEntry;
import com.hundsun.ares.studio.core.IModuleRootConstructor;
import com.hundsun.ares.studio.core.IProjectProperty;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.core.registry.IModuleRootDescriptor;
import com.hundsun.ares.studio.core.registry.IRespathProviderDescriptor;
import com.hundsun.ares.studio.core.registry.IRootConstructorDescriptor;
import com.hundsun.ares.studio.core.registry.ModulesRootTypeRegistry;
import com.hundsun.ares.studio.core.registry.RespathProviderRegistry;
import com.hundsun.ares.studio.core.util.ResourcesUtil;
import com.hundsun.ares.studio.internal.core.registry.RootConstructorRegistry;

/**
 * 项目
 * @author sundl
 */
public  class ARESProject extends Openable implements IARESProject, IProjectNature{

	private static final Logger logger = Logger.getLogger(ARESProject.class.getName());
	
	protected IProject project;
	
	public static final String RES_PATH_FILE = ".respath";
	
	static final String TAG_RESPATH = "respath";
		
	public ARESProject() {
		this(null, null);
	}
	
	public ARESProject(IARESElement parent, IProject project) {
		super(parent);
		this.project = project;
	}

	public static boolean hasARESNature(IProject project) {
		try {
			if (project.exists() && project.hasNature(ARESCore.NATURE_ID)) {
				return true;
			}
		} catch (CoreException e) {
			// e.printStackTrace();
		}
		return false;
	}
	
	public static boolean hasRefNature(IProject project) {
		try {
			if (project.exists() && project.hasNature(ARESCore.REF_NATURE)) {
				return true;
			}
		} catch (CoreException e) {
			// e.printStackTrace();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getCorrespondingResource()
	 */
	@Override
	public IResource getCorrespondingResource() throws ARESModelException {
		return getProject();
	}
	
	@Override
	protected Object createElementInfo() {
		return new AresProjectInfo();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getElementName()
	 */
	@Override
	public String getElementName() {
		return project.getName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getElementType()
	 */
	@Override
	public int getElementType() {
		return IARESElement.ARES_PROJECT;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getPath()
	 */
	@Override
	public IPath getPath() {
		return getProject().getFullPath();
	}

	@Override
	protected IStatus validateExistence(IResource underlyingResource) {
		try {
			if (!((IProject) underlyingResource).hasNature(ARESCore.NATURE_ID))
				return newDoesNotExistStatus();
		} catch (CoreException e) {
			return newDoesNotExistStatus();
		}
		return ARESModelStatus.OK_STATUS;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getResource()
	 */
	@Override
	public IResource getResource() {
		return project;
	}
	
	@Override
	public IProject getProject() {
		return project;
	}	
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IProjectNature#configure()
	 */
	@Override
	public void configure() throws CoreException {
		addToBuildSpec(ARESCore.BUILDER_ID);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IProjectNature#deconfigure()
	 */
	@Override
	public void deconfigure() throws CoreException {
		removeFromBuildSpec(ARESCore.BUILDER_ID);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IProjectNature#setProject(org.eclipse.core.resources.IProject)
	 */
	@Override
	public void setProject(IProject project) {
		this.project = project;
	}
	
	public IResPathEntry[] readResPath() throws ARESModelException{
		List<IResPathEntry> entries = new ArrayList<IResPathEntry>();

		IFile file = project.getFile(RES_PATH_FILE);
		if (!file.exists()) {
			logger.warn(project.getName() + "has no .raspath file found.");
			// return new IResPathEntry[0];
		} 
		
		try {
			IResPathEntry[] path = decodeResPathEntry(file.getContents());
			entries.addAll(Arrays.asList(path));
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		RespathProviderRegistry reg = RespathProviderRegistry.getInstance();
		for (IRespathProviderDescriptor provider : reg.getProviders()) {
			List<IExternalResPathEntry> entriesFromProvider = null; 
			try {
				entriesFromProvider = provider.getProvider().getResPathEntries(this);
			} catch (Exception e) {
				logger.error("解析Respath出错：", e);
				continue;
			}
			// 2012-2-24 sundl 记录provider信息
			for (IExternalResPathEntry entry : entriesFromProvider) {
				entry.setProvider(provider);
			}
			entries.addAll(entriesFromProvider);
			if (logger.isDebugEnabled()) {
				logger.debug("Respath entries from provider: " + provider);
				for (IResPathEntry entry : entriesFromProvider) {
					logger.debug(entry);
				}
			}
		}
		
		return entries.toArray(new IResPathEntry[0]);
	}

	private IResPathEntry[] decodeResPathEntry(InputStream is) {
		SAXReader reader = new SAXReader();	
		List<IResPathEntry> entries = new ArrayList<IResPathEntry>();
		try {
			Document document = reader.read(is);
			Element root = document.getRootElement();
			if (root == null || root.getName() != TAG_RESPATH)
				return new IResPathEntry[0];
			
			for (Object o : root.elements()) {
				Element element = (Element)o;
				IResPathEntry entry = ResPathEntry.decodeFromElement(element, this);
				if (entry != null && !entries.contains(entry)) {
					entries.add(entry);
					if (logger.isInfoEnabled())
						logger.trace("res-path entry added: \n" + entry.toString());					
				} else {
					logger.warn("failed to resolve res-path entry, the xml is: \n" + element.asXML());
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return entries.toArray(new IResPathEntry[entries.size()]);
	}
	
	private InputStream encodeResPathEntry(IResPathEntry[] entries) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement(TAG_RESPATH);
		for (IResPathEntry entry : entries) {
			if (entry instanceof IExternalResPathEntry)
				continue;
			
			((ResPathEntry)entry).encodeEntry(root);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setIndent(true);
		format.setEncoding("utf-8");	
		
		try {
			XMLWriter writer = new XMLWriter(bos, format);
			writer.write(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		try {
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bis;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonProject#getAllModuleRoots()
	 */
	@Override
	public IARESModuleRoot[] getAllModuleRoots() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonProject#getModuleRoots()
	 */
	@Override
	public IARESModuleRoot[] getModuleRoots() throws ARESModelException {
		Object[] children = getChildren();
		int length = children.length;
		List<IARESModuleRoot> roots = new ArrayList<IARESModuleRoot>();
		for (int i = 0; i < length; i++) {
			Object child = children[i];
			if (child instanceof IARESModuleRoot) {
				roots.add((IARESModuleRoot)children[i]);
			} /*else if (child instanceof IReferencedLibrary) {
				roots.addAll(Arrays.asList(((IReferencedLibrary)child).getRoots()));
			}*/
		}
		return roots.toArray(new IARESModuleRoot[roots.size()]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonProject#getModules()
	 */
	@Override
	public IARESModule[] getModules() throws ARESModelException {
		List<IARESModule> modules = new ArrayList<IARESModule>();
		for (IARESModuleRoot root : getModuleRoots()) {
			modules.addAll(Arrays.asList(root.getModules()));
		}
		return modules.toArray(new IARESModule[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonProject#getRawResPath()
	 */
	@Override
	public IResPathEntry[] getRawResPath() {
		try {
			AresProjectInfo info = (AresProjectInfo)getElementInfo();
			return info.resPath;
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return new IResPathEntry[0];
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonProject#setRawResPath(com.hundsun.ares.devtool.common.core.IResPathEntry[], org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void setRawResPath(IResPathEntry[] entries, IProgressMonitor monitor) {
		writeResPath(entries);
	}	
	
	public void addResEntryToResPath(IResPathEntry[] newEntries) {
		IResPathEntry[] oldpath = getRawResPath();
		IResPathEntry[] newPath = new IResPathEntry[oldpath.length + newEntries.length];
		System.arraycopy(oldpath, 0, newPath, 0, oldpath.length);
		System.arraycopy(newEntries, 0, newPath, oldpath.length, newEntries.length);
		setRawResPath(newPath, null);
		ARESModelManager.getManager().getDeltaProcessor().entriesAdded(this, newEntries);
	}

	public void writeResPath(IResPathEntry[] newPath) {
		IFile file = project.getFile(RES_PATH_FILE);
		try {
			InputStream is = encodeResPathEntry(newPath);
			if (file.exists()) {				
				file.setContents(is, true, false, null);				
			} else {
				file.create(is, true, null);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonProject#getModuleRoot(org.eclipse.core.resources.IResource)
	 */
	@Override
	public IARESModuleRoot getModuleRoot(IResource resource) {
		switch (resource.getType()) {
		case IResource.FOLDER:
			IFolder folder = (IFolder)resource;
			IPath path = folder.getProjectRelativePath();
			for (IResPathEntry entry : getRawResPath()) {
				if (path.equals(entry.getPath())) {
					return new ARESModuleRoot(this, path, entry.getType());
				}
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESBundle#getModuleRoot(java.lang.String)
	 */
	@Override
	public IARESModuleRoot getModuleRoot(String path) throws ARESModelException {
		IFolder folder = project.getFolder(path);
		return getModuleRoot(folder);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.ICommonProject#getModuleRoot(com.hundsun.ares.devtool.common.core.IResPathEntry)
	 */
	@Override
	public IARESModuleRoot getModuleRoot(IResPathEntry entry) {
		if (entry.getEntryKind() == IResPathEntry.RPE_SOURCE) {
			IPath path = entry.getPath();
			IFolder folder = getProject().getFolder(path);
			ARESModuleRoot mRoot = new ARESModuleRoot();
			mRoot.init(this, folder.getProjectRelativePath(), entry.getType());
			return mRoot;
		} 
		return null;
	}
	
	@Override
	public IReferencedLibrary getReferencedLibrary(IResPathEntry entry) {
		if (entry.getEntryKind() == IResPathEntry.RPE_LIBRAY) {
			//return getReferencedLibrary(entry.getPath()); 
			Object target = getTarget(entry.getPath());
			if (target instanceof IFile) {
				return new WorkspaceReferecedLibrary(this, (IFile) target, entry);
			} else if (target instanceof File) {
				return new ExternalFileReferencedLibrary(this, (File) target, entry);
			}
		}
		return null;
	}
	
	@Override
	public IReferencedLibrary getReferencedLibrary(IPath path) {
		for (IResPathEntry entry : getRawResPath()) {
			if (entry.getPath().equals(path)) {
				return getReferencedLibrary(entry);
			}
		}
		return null;
	}
	
	private Object getTarget(IPath path) {
		Object target = getWorkspaceTarget(path); // Implicitly checks resource existence
		if (target != null)
			return target;
		return getExternalTarget(path, true);
	}
	
	public static Object getExternalTarget(IPath path, boolean checkResourceExistence) {
		if (path == null)
			return null;
		File externalFile = new File(path.toOSString());
		if (!checkResourceExistence) {
			return externalFile;
		} else {
			if (externalFile.isFile()) { // isFile() checks for existence (it returns false if a directory)
				// cache external file
				return externalFile;
			}
		}
		return null;
	}
	
	public IResource getWorkspaceTarget(IPath path) {
		if (path == null || path.getDevice() != null)
			return null;
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		if (workspace == null)
			return null;
		return workspace.getRoot().findMember(path);
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProject#getReferencedLibs()
	 */
	@Override
	public IReferencedLibrary[] getReferencedLibs() throws ARESModelException {
		Object[] children = getChildren();
		int length = children.length;
		List<IReferencedLibrary> result = new ArrayList<IReferencedLibrary>();
		for (int i = 0; i < length; i++) {
			Object child = children[i];
			if (child instanceof IReferencedLibrary) {
				result.add((IReferencedLibrary) child);
			}
		}
		return result.toArray(new IReferencedLibrary[result.size()]);
	}

	// ----------------- BUILD BEGIN----------------
	protected void addToBuildSpec(String builderID) throws CoreException {

		IProjectDescription description = getProject().getDescription();
		ICommand builderCommand = getBuilderCommand(description, builderID);

		if (builderCommand == null) {
			// Add a new build spec
			ICommand command = description.newCommand();
			command.setBuilderName(builderID);
			setBuilderCommand(description, command);
		}
	}

	private ICommand getBuilderCommand(IProjectDescription description, String builderId) throws CoreException {
		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(builderId)) {
				return commands[i];
			}
		}
		return null;
	}

	protected void removeFromBuildSpec(String builderID) throws CoreException {
		IProjectDescription description = getProject().getDescription();
		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(builderID)) {
				ICommand[] newCommands = new ICommand[commands.length - 1];
				System.arraycopy(commands, 0, newCommands, 0, i);
				System.arraycopy(commands, i + 1, newCommands, i, commands.length - i - 1);
				description.setBuildSpec(newCommands);
				return;
			}
		}
	}

	private void setBuilderCommand(IProjectDescription description, ICommand newCommand) throws CoreException {

		ICommand[] oldCommands = description.getBuildSpec();
		ICommand oldBuilderCommand = getBuilderCommand(description, newCommand.getBuilderName());

		ICommand[] newCommands;

		if (oldBuilderCommand == null) {
			// Add a build spec after other builders
			newCommands = new ICommand[oldCommands.length + 1];
			System.arraycopy(oldCommands, 0, newCommands, 0, oldCommands.length);
			newCommands[oldCommands.length] = newCommand;
			
		} else {
			for (int i = 0, max = oldCommands.length; i < max; i++) {
				if (oldCommands[i] == oldBuilderCommand) {
					oldCommands[i] = newCommand;
					break;
				}
			}
			newCommands = oldCommands;
		}

		// Commit the spec change into the project
		description.setBuildSpec(newCommands);
		getProject().setDescription(description, null);
	}
	// -----------------BUILD END-----------------------

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.Openable#buildStructure(com.hundsun.ares.devtool.v2.internal.core.OpenableElementInfo)
	 */
	@Override
	protected boolean buildStructure(OpenableElementInfo info) throws ARESModelException {
		logger.debug("common project building structure...");
		IResPathEntry[] resPath = readResPath();
		AresProjectInfo projectInfo = (AresProjectInfo)info;
		projectInfo.resPath = resPath;
		
		if (logger.isTraceEnabled()) {
			logger.trace("Raw class path is:");
			for (IResPathEntry entry : resPath) {
				logger.trace(entry.toString());
			}
			logger.trace("Root types in registry: ");
			for (String key : ModulesRootTypeRegistry.getInstance().getRootTypes().keySet()) {
//				IModuleRootDescriptor desc = ModulesRootTypeRegistry.getInstance().getModuleRootDescriptor(key);
				logger.trace("ID: " + key );
			}
		}
		
		OpenableElementInfo openableInfo = info;
		for (IResPathEntry entry : resPath) {
			try {
				logger.trace("entry processing start: " + entry.toString());
				switch (entry.getEntryKind()) {
				case IResPathEntry.RPE_SOURCE:
					IPath path = entry.getPath();
					IFolder resource = project.getFolder(path);
					if (resource != null && resource.exists()) {
						String type = entry.getType();
						IModuleRootDescriptor descriptor = ModulesRootTypeRegistry.getInstance().getModuleRootDescriptor(type);
						if (descriptor != null) {
							ARESModuleRoot root = (ARESModuleRoot)descriptor.createRoot();
							root.init(this, resource.getProjectRelativePath(), descriptor.getId());
							openableInfo.addChild(root);
							logger.info("root found: " + root);
						} else {
							logger.error( "Entry " + entry + "'s root-type is not in the registry!");
						}
					} else {
						logger.warn("no root found with entry: " + entry + ", no resouce found with the given path");
					}				
					break;
				case IResPathEntry.RPE_LIBRAY:
					IPath libPath = entry.getPath();
					IReferencedLibrary lib = getReferencedLibrary(entry);
					if (lib != null) {
						openableInfo.addChild(lib);
						logger.debug("Lib added: " + libPath);
					}
					break;
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("parse module root error: ", e);
			}
		}
		
		IFile proFile = project.getFile(IARESProjectProperty.PRO_FILE);
		if (proFile.exists()) {
			openableInfo.addChild(new ProjectProperty(this, proFile));
		}
		
		logger.debug("common project built structure end...");
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProject#findElement(org.eclipse.core.runtime.IPath)
	 */
	@Override
	public IARESElement findElement(IPath path) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProject#getRequiredProjectNames()
	 */
	@Override
	public String[] getRequiredProjectNames() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProject#findResource(java.lang.String, java.lang.String)
	 */
	@Override
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
	 * @see com.hundsun.ares.studio.core.IARESProject#getResources(java.lang.String)
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
	 * @see com.hundsun.ares.studio.core.IARESProject#getResources(java.lang.String[])
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
	 * @see com.hundsun.ares.studio.core.IARESProject#getResources()
	 */
	@Override
	public IARESResource[] getResources() throws ARESModelException {
		List<IARESResource> results = new ArrayList<IARESResource>();
		for (IARESModuleRoot root : getModuleRoots()) {
			results.addAll(Arrays.asList(root.getResources()));
		}
		return results.toArray(new IARESResource[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProject#findResource(java.lang.String)
	 */
	@Override
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
	 * @see com.hundsun.ares.studio.core.IARESProject#getProjectProperty()
	 */
	@Override
	public IARESProjectProperty getProjectProperty() throws ARESModelException{
		IProjectProperty pro = getProperty();
		if (pro.exists()) {
			return pro.getInfo();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProject#createRoot(java.lang.String, java.lang.String)
	 */
	@Override
	public IARESModuleRoot createRoot(String type, String path, IProgressMonitor monitor) throws ARESModelException{
		IFolder folder = project.getFolder(path);
		
		try {
			monitor.beginTask("创建模块根", 300);
			monitor.subTask("创建文件夹");
			ResourcesUtil.safelyCreateFolder(folder);
			monitor.worked(100);
			
			monitor.subTask("修改respath");
			IResPathEntry[] respath = readResPath();
			IResPathEntry entry = ARESCore.newSourceEntry(type, new Path(path));
			IResPathEntry[] newRespath = new IResPathEntry[respath.length + 1];
			System.arraycopy(respath, 0, newRespath, 0, respath.length);
			newRespath[respath.length] = entry;			
			setRawResPath(newRespath, null);
			monitor.worked(100);
			
			monitor.subTask("初始化模块根");
			ARESModuleRoot root = new ARESModuleRoot(this, new Path(path), type);
			initMooduleRoot(root, new SubProgressMonitor(monitor, 100));
			return root;
		} catch (CoreException e) {
			logger.error("创建root文件夹失败: " + path, e);
			throw new ARESModelException(e);
		} finally {
			monitor.done();
		}
	}
	
	private void initMooduleRoot(IARESModuleRoot root, IProgressMonitor monitor) throws ARESModelException {
		RootConstructorRegistry reg = RootConstructorRegistry.getInstance();
		Collection<IRootConstructorDescriptor> descriptors = reg.get(root.getType());
		int size = descriptors.size();
		monitor.beginTask("初始化模块根" + root.getElementName(), size);
		for (IRootConstructorDescriptor desc : descriptors) {
			IModuleRootConstructor constructor = desc.createConstructor();
			constructor.construct(root);
			monitor.worked(1);
		} 
		monitor.done();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.core.ARESElement#getHandleMementoDelimiter()
	 */
	@Override
	protected char getHandleMementoDelimiter() {
		return AEM_ARESPROJECT;
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
				ARESElement root = (ARESElement) getModuleRoot(project.getFolder(rootPath));
				if (root != null) {
					if ((token = memento.nextToken()) != null && token.charAt(0) == AEM_MODULE) {
						return root.getHandleFromMemento(token, memento);
					} else {
						return root.getHandleFromMemento(memento);
					}
				}
				break;
			case AEM_LIB:
				token = memento.nextToken();
				ARESElement lib = (ARESElement) getReferencedLibrary(new Path(token));
				if (lib != null) {
					if ((token = memento.nextToken()) != null && token.charAt(0) == AEM_MODULEROOT) {
						return lib.getHandleFromMemento(token, memento);
					} else {
						return lib.getHandleFromMemento(memento);
					}
				}
				break;
			case AEM_PROJECT_PRO:
				token = memento.nextToken();
				IFile file = project.getFile(token);
				if (file.exists())
					return new ProjectProperty(this, file);
				break;
		}
		return null;
	}

	@Override
	public IARESProject[] getRequiredProjects() {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResPathEntry[] respath = getRawResPath();
		List<IARESProject> projects = new ArrayList<IARESProject>();
		for (IResPathEntry entry : respath) {
			if (entry.getEntryKind() == IResPathEntry.RPE_PROJECT) {
				IPath path = entry.getPath();
				IProject project = (IProject) root.findMember(path);
				IARESProject aresProject = ARESCore.create(project);
				if (aresProject != null && aresProject.exists())
					projects.add(aresProject);
			}
		}
		return projects.toArray(new IARESProject[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESProject#getProperty()
	 */
	@Override
	public IProjectProperty getProperty() {
		return new ProjectProperty(this, project.getFile(IARESProjectProperty.PRO_FILE));
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependencyUnit#getDependencies()
	 */
	@Override
	public List<IDependencyUnit> getDependencies() {
		List<IDependencyUnit> dependencies = new ArrayList<IDependencyUnit>();
		IResPathEntry[] resPath = getRawResPath();
		if (resPath != null) {
			for (IResPathEntry entry : resPath) {
				if (entry.getEntryKind() == IResPathEntry.RPE_LIBRAY) {
					IReferencedLibrary lib = getReferencedLibrary(entry);
					// TODO 如果找不到就不添加，这里可能还需要优化，找不到包要提示错误。
					if (lib != null)
						dependencies.add(lib);
				} else if (entry.getEntryKind() == IResPathEntry.RPE_PROJECT) {
					IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
					IPath path = entry.getPath();
					IProject project = (IProject) root.findMember(path);
					IARESProject aresProject = ARESCore.create(project);
					if (aresProject != null) {
						dependencies.add(new RequiredProject(aresProject, entry.getType()));
					}
				}
			}
		}
		return dependencies;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESBundle#getInfo()
	 */
	@Override
	public IARESBundleInfo getInfo() {
		try {
			return getProjectProperty();
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESBundle#getId()
	 */
	@Override
	public String getId() {
		IARESBundleInfo info = getInfo();
		if (info != null)
			return info.getId();
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESBundle#getRequiredBundles()
	 */
	@Override
	public IARESBundle[] getRequiredBundles() {
		List<IARESBundle> bundles = new ArrayList<IARESBundle>();
		IResPathEntry[] resPath = getRawResPath();
		if (resPath != null) {
			for (IResPathEntry entry : resPath) {
				if (entry.getEntryKind() == IResPathEntry.RPE_LIBRAY) {
					IReferencedLibrary lib = getReferencedLibrary(entry);
					bundles.add(lib);
				} else if (entry.getEntryKind() == IResPathEntry.RPE_PROJECT) {
					IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
					IPath path = entry.getPath();
					IProject project = (IProject) root.findMember(path);
					IARESProject aresProject = ARESCore.create(project);
					bundles.add(aresProject);
				}
			}
		}
		return bundles.toArray(new IARESBundle[0]);
	}

}
