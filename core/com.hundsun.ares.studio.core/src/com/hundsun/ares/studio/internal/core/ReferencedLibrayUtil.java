/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.BasicReferencedLibInfo;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IDependenceDescriptor;
import com.hundsun.ares.studio.core.IDependencyUnit;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.core.model.extendable.ExtendModelConverterManager;
import com.hundsun.ares.studio.core.model.extendable.IExtendAbleModel;
import com.hundsun.ares.studio.core.util.PersistentUtil;

/**
 * 工具类
 * @author sundl
 */
public class ReferencedLibrayUtil {
	
	private static final String INFO = "info";
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String VERSION = "version";
	private static final String PROVIDER = "provider";
	private static final String CONTACT = "contact";
	private static final String NOTE = "note";
	private static final String PUBLISHER = "publisher";
	private static final String PUB_TIME = "pubtime";
	private static final String DEPENDENCIES = "dependencies";
	private static final String LIB = "lib";
	private static final String TYPE = "type";
	private static final String EXTENDED_INFO = "extended";
	private static final String ITEM = "item";
	private static final String KEY = "key";
	private static final String VALUE = "value";
	
	/**
	 * 读取引用包本身的信息，例如id，版本，依赖项等等...
	 * @param info 存储信息的对象
	 * @param is 输入流
	 * @throws DocumentException
	 */
	public static void readRefLibInfo(ReferencedLibraryInfo info, InputStream is, IARESProject project) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(is);
		Element root = document.getRootElement();
		
		// 基本信息
		Element eInfo = root.element(INFO);
		BasicReferencedLibInfo basicInfo = (BasicReferencedLibInfo) info.getBasicInfo();
		if (eInfo != null) {
			basicInfo.setId(readElementText(eInfo.element(ID)));
			basicInfo.setName(readElementText(eInfo.element(NAME)));
			basicInfo.setVersion(readElementText(eInfo.element(VERSION)));
			basicInfo.setProvider(readElementText(eInfo.element(PROVIDER)));
			basicInfo.setContact(readElementText(eInfo.element(CONTACT)));
			basicInfo.setNote(readElementText(eInfo.element(NOTE)));
			basicInfo.setPublisher(readElementText(eInfo.element(PUBLISHER)));
			basicInfo.setPublishTime(readElementText(eInfo.element(PUB_TIME)));
			basicInfo.setType(readElementText(eInfo.element(TYPE)));
		}
		
		// respath
		Element eRespath = root.element(ARESProject.TAG_RESPATH);
		if (eRespath != null) {
			IResPathEntry[] entries = decodeRespath(eRespath, project);
			info.setRespath(entries);
		}
		
		// dependence
		Element eDep = root.element(DEPENDENCIES);
		if (eDep != null) {
			List<IDependenceDescriptor> dependencies = new ArrayList<IDependenceDescriptor>();
			for (Object obj : eDep.elements(LIB)) {
				Element element = (Element) obj;
				String id = element.attributeValue(ID);
				String version = element.attributeValue(VERSION);
				String type = element.attributeValue(TYPE);
				DependenceDescriptor desc = new DependenceDescriptor(id, type, version);
				dependencies.add(desc);
			}
			basicInfo.setDependencies(dependencies);
		}
		
		// extended info
		Element eExtendedInfo = root.element(EXTENDED_INFO);
		if (eExtendedInfo != null) {
			for (Object obj : eExtendedInfo.elements(ITEM)) {
				Element element= (Element) obj;
				String key = element.attributeValue(KEY);
				String value = element.attributeValue(VALUE);
				info.getExtendedInfo().put(key, value);
			}
		}
	}
	
	/**
	 * 读取项目属性中的扩展信息
	 * @param info
	 * @param is
	 */
	public static void readExtendedInfo(IExtendAbleModel info, InputStream is) {
		Document doc = PersistentUtil.readDocument(is);
		Element root = doc.getRootElement();
		ExtendModelConverterManager.getDefault().readExtendMap(info, root);
	}
	
	/**
	 * 创建指定项目的引用包的描述信息对象。
	 * @param project
	 * @param publisher
	 * @return
	 */
	public static ReferencedLibraryInfo createRefLibInfoObject(IARESProject project, String publisher) {
		if (project != null && project.exists()) {
			IARESProjectProperty property = null;
			try {
				property = project.getProjectProperty();
			} catch (ARESModelException e1) {
				e1.printStackTrace();
			}
			
			if (property == null)
				return null;
			
			BasicReferencedLibInfo basicInfo = new BasicReferencedLibInfo();
			ReferencedLibraryInfo info = new ReferencedLibraryInfo();
			basicInfo.setId(property.getId());
			basicInfo.setVersion(property.getVersion());
			basicInfo.setName(property.getName());
			basicInfo.setProvider(property.getProvider());
			basicInfo.setContact(property.getContact());
			basicInfo.setNote(property.getNote());
			basicInfo.setPublisher(publisher);
			basicInfo.setPublishTime(DateFormat.getDateTimeInstance().format(new Date()));
			info.setBasicInfo(basicInfo);
			
			IResPathEntry[] resPath = project.getRawResPath();
			info.setRespath(resPath);
			
			List<IDependenceDescriptor> dependencies = new ArrayList<IDependenceDescriptor>();
//			for (IResPathEntry entry : resPath) {
//				if (entry.getEntryKind() == IResPathEntry.RPE_LIBRAY) {
//					IReferencedLibrary lib = project.getReferencedLibrary(entry);
//					DependenceDescriptor dep = new DependenceDescriptor(lib.getId(), lib.getType(), lib.getVersion());
//					dependencies.add(dep);
//				} else if (entry.getEntryKind() == IResPathEntry.RPE_PROJECT) {
//					IProject pro = project.getProject().getWorkspace().getRoot().getProject(entry.getPath().toString());
//					DependenceDescriptor dep = new DependenceDescriptor(lib.getId(), lib.getType(), lib.getVersion());
//					dependencies.add(dep);
//				}
//			}
			
			for (IDependencyUnit unit : project.getDependencies()) {
				DependenceDescriptor dep = new DependenceDescriptor(unit.getId(), unit.getType(), unit.getVersion());
				dependencies.add(dep);
			}
			
			basicInfo.setDependencies(dependencies);
			return info;
		}
		
		return null;
	}
	
	public static void writeRefLibInfo(ReferencedLibraryInfo info, OutputStream os) {
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("aar");
		
		Element infoElement = root.addElement(INFO);
		infoElement.addElement(ID).setText(StringUtils.defaultString(info.getBasicInfo().getId()));
		infoElement.addElement(NAME).setText(StringUtils.defaultString(info.getBasicInfo().getName()));
		infoElement.addElement(VERSION).setText(StringUtils.defaultString(info.getBasicInfo().getVersion()));
		infoElement.addElement(PROVIDER).setText(StringUtils.defaultString(info.getBasicInfo().getProvider()));
		infoElement.addElement(CONTACT).setText(StringUtils.defaultString(info.getBasicInfo().getContact()));
		infoElement.addElement(NOTE).setText(StringUtils.defaultString(info.getBasicInfo().getNote()));
		infoElement.addElement(PUBLISHER).setText(StringUtils.defaultString(info.getBasicInfo().getPublisher()));
		infoElement.addElement(PUB_TIME).setText(StringUtils.defaultString(info.getBasicInfo().getPublishTime()));
		infoElement.addElement(TYPE).setText(StringUtils.defaultString(info.getBasicInfo().getType()));
		
		Element respath = root.addElement(ARESProject.TAG_RESPATH);
		for (IResPathEntry entry : info.getRespath()) {
			if (entry.getEntryKind() == IResPathEntry.RPE_SOURCE) {
				((ResPathEntry)entry).encodeEntry(respath);
			}
		}
		
		Element eDep = root.addElement(DEPENDENCIES);
		List<IDependenceDescriptor> dependencies = info.getBasicInfo().getDependencyDescriptors();
		if (dependencies != null) {
			for (IDependenceDescriptor desc : dependencies) {
				String id = StringUtils.defaultString(desc.getId());
				String type = StringUtils.defaultString(desc.getType());
				String version = StringUtils.defaultString(desc.getVersionConstraint());
				Element eLib = eDep.addElement(LIB);
				eLib.addAttribute(ID, id);
				eLib.addAttribute(VERSION, version);
				eLib.addAttribute(TYPE, type);
			}
		}
		
		// extended info
		Map<String, String> extendedInfo = info.getExtendedInfo();
		Element eExtendedInfo = root.addElement(EXTENDED_INFO);
		for (String key : extendedInfo.keySet()) {
			Element eItem = eExtendedInfo.addElement(ITEM);
			eItem.addAttribute(KEY, key);
			eItem.addAttribute(VALUE, StringUtils.defaultString(extendedInfo.get(key)));
		}
		
		PersistentUtil.writeDocument(os, doc);
	}
	
	/**
	 * 检查给定的文件是否一个合法的引用包文件
	 * @param file
	 * @return
	 */
	public static IStatus testFile(IFile file) {
		if (!file.exists()) {
			return new Status(IStatus.ERROR, ARESCore.PLUGIN_ID, "文件不存在");
		}
		
		ZipFile zip = null;
		try {
			zip = new ZipFile(new File(file.getLocation().toOSString()));
			ZipEntry entry = zip.getEntry(IReferencedLibrary.PROPERTIE_FILE);
			if (entry == null) 
				return new Status(IStatus.ERROR, ARESCore.PLUGIN_ID, "没有描述文件(.aar)");
		} catch (Exception e) {
			return new Status(IStatus.ERROR, ARESCore.PLUGIN_ID, "验证文件过程中发生错误: ", e);
		} finally {
			if (zip != null) {
				try {
					zip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return Status.OK_STATUS;
	}
	
	public static IStatus testFile(File file) {
		if (!file.exists()) {
			return new Status(IStatus.ERROR, ARESCore.PLUGIN_ID, "文件不存在");
		}
		
		ZipFile zip = null;
		try {
			zip = new ZipFile(file);
			ZipEntry entry = zip.getEntry(IReferencedLibrary.PROPERTIE_FILE);
			if (entry == null) 
				return new Status(IStatus.ERROR, ARESCore.PLUGIN_ID, "没有描述文件(.aar)");
		} catch (Exception e) {
			return new Status(IStatus.ERROR, ARESCore.PLUGIN_ID, "验证文件过程中发生错误: ", e);
		} finally {
			if (zip != null) {
				try {
					zip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return Status.OK_STATUS;
	}
	
	/**
	 * 解析Respath 
	 * @param root
	 * @return respath
	 */
	private static IResPathEntry[] decodeRespath(Element root, IARESProject project) {
		List<IResPathEntry> entries = new ArrayList<IResPathEntry>();
		for (Object e : root.elements(ResPathEntry.TAG_ENTRY)) {
			IResPathEntry entry = ResPathEntry.decodeFromElement((Element)e, project);
			if (entry != null)
				entries.add(entry);
		}
		return entries.toArray(new IResPathEntry[entries.size()]);
	}
	
	private static Element encodeRespathEntry(Element parent, IResPathEntry entry) {
		Element element = parent.addElement(ResPathEntry.TAG_ENTRY);
		return element;		
	}
	
	private static String readElementText(Element element) {
		if (element != null)
			return element.getTextTrim();
		else
			return "";
	}
	
}
