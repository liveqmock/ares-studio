/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.core.util.Util;

/**
 * IResPathEntry的实现
 * @author sundl
 */
public class ResPathEntry implements IResPathEntry {

	// xml里的几个tag名字
	static final String TAG_ENTRY = "entry";
	static final String TAG_KIND = "kind";
	static final String TAG_PATH = "path";
	static final String TAG_TYPE = "type";
	
	// 代表各种类型的值
	private static final String RPE_KIND_PROJECT = "prj";
	private static final String RPE_KIND_SRC = "src";
	private static final String RPE_KIND_LIB = "lib";
	
	private int entryKind;		// 类型
	private int contentKind;	// 内容类型
	private String type;		// 如果对应了一个模块根，则是模块根的类型；如果是引用包，则是引用包的类型
	private IPath path;			// 路径
	
	static int kindFromString(String str) {
		if (str.equals(RPE_KIND_LIB)) {
			return RPE_LIBRAY;
		} else if (str.equals(RPE_KIND_SRC)) {
			return RPE_SOURCE;
		} else if (str.equals(RPE_KIND_PROJECT)) {
			return RPE_PROJECT;
		}
		return -1;
	}
	
	static String kindToString(int kind) {
		switch (kind) {
		case RPE_LIBRAY:
			return RPE_KIND_LIB;
		case RPE_PROJECT:
			return RPE_KIND_PROJECT;
		case RPE_SOURCE:
			return RPE_KIND_SRC;
		default:
			return "unknown";
		}
	}
	
	static IResPathEntry decodeFromElement(Element element, IARESProject project) {
		if (element.getName() != TAG_ENTRY) {
			return null;
		}
		String kindAttr = StringUtil.convertString(element.attributeValue(TAG_KIND));
		String pathAttr = StringUtil.convertString(element.attributeValue(TAG_PATH));
		
		int kind = kindFromString(kindAttr);
		IPath path = new Path(pathAttr);
				
		IResPathEntry entry = null;
		switch(kind) {
		case IResPathEntry.RPE_PROJECT:
			String type = StringUtil.convertString(element.attributeValue(TAG_TYPE));
			entry = ARESCore.newProjectEntry(path, type);
			break;
		case IResPathEntry.RPE_SOURCE:
			String rootId = StringUtil.convertString(element.attributeValue(TAG_TYPE));
			entry = ARESCore.newSourceEntry(rootId, path);
			break;
		case IResPathEntry.RPE_LIBRAY:
			if (!path.isAbsolute()) {
				path = project.getPath().append(path);
			}
			entry = ARESCore.newLibEntry(path);
			break;
		}
		return entry;
	}
		
	/**
	 * 构造一个Res-Path Entry.
	 * @param entryKind Entry的类型（src, project, lib）
	 * @param contentKind 内容类型(src, bin)
	 * @param type 仅在entry kind == src的情况下，传入对应ModuleRoot的ID
	 * @param path 对应的路径
	 */
	public ResPathEntry(int entryKind, int contentKind, String type, IPath path) {
		this.entryKind = entryKind;
		this.contentKind = contentKind;
		this.type = type;
		this.path = path;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.IARESResourcePathEntry#getPath()
	 */
	public IPath getPath() {
		return path;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.IARESResourcePathEntry#getType()
	 */
	public String getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.IResPathEntry#getContentKind()
	 */
	public int getContentKind() {
		return this.contentKind;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.IResPathEntry#getEntryKind()
	 */
	public int getEntryKind() {
		return this.entryKind;
	}
	
	void encodeEntry(Element parent) {
		Element entryElement = parent.addElement(TAG_ENTRY);
		String kind = kindToString(entryKind);
		entryElement.addAttribute(TAG_KIND, kind);
		
		if (entryKind == RPE_SOURCE || entryKind == RPE_PROJECT) {
			entryElement.addAttribute(TAG_TYPE, type);
		} 
		
		entryElement.addAttribute(TAG_PATH, path.toString());
	}
	
	public void setPath(IPath path) {
		this.path = path;
	}

	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof ResPathEntry)) {
			return false;
		}
		
		ResPathEntry other = (ResPathEntry)obj;
		return entryKind == other.entryKind
			&& path.equals(other.path)
			&& StringUtils.equals(type, other.type);
	}
	
	@Override
	public int hashCode() {
		return Util.combineHashCodes(entryKind, path.hashCode());
	}
	
	public String toString() {
		return "EntryKind: " + entryKind + " " +
				"ContentKind: "  + contentKind + " " +
				"Type: " + StringUtil.convertString(type) + " " +
				"Path: " + path;
	}

}
