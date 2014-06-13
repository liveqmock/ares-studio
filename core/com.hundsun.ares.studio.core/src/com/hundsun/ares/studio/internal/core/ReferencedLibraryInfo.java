/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.core.BasicReferencedLibInfo;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IBasicReferencedLibInfo;
import com.hundsun.ares.studio.core.IResPathEntry;

/**
 * 引用包的Info类。
 * @author sundl
 */
public class ReferencedLibraryInfo extends OpenableElementInfo {
	
	private IBasicReferencedLibInfo basicInfo = new BasicReferencedLibInfo();
	private Map<String, String> extendedInfo = new HashMap<String, String>();

	private List<IResPathEntry> respath;
	Map<IARESModuleRoot, List<IARESModule>> rootsInfo = new HashMap<IARESModuleRoot, List<IARESModule>>();
	Map<IARESModule, List<IARESResource>> moduleInfo = new HashMap<IARESModule, List<IARESResource>>();
	
	public IBasicReferencedLibInfo getBasicInfo() {
		return basicInfo;
	}
	
	public void setBasicInfo(IBasicReferencedLibInfo basicInfo) {
		this.basicInfo = basicInfo;
	}

	/**
	 * @return the respath
	 */
	public List<IResPathEntry> getRespath() {
		return respath;
	}
	/**
	 * @param respath the respath to set
	 */
	public void setRespath(List<IResPathEntry> respath) {
		this.respath = respath;
	}
	
	public void setRespath(IResPathEntry[] respath) {
		this.respath = Arrays.asList(respath);
	}
	
	/**
	 * @return the extendedInfo
	 */
	public Map<String, String> getExtendedInfo() {
		return extendedInfo;
	}

	/**
	 * @param extendedInfo the extendedInfo to set
	 */
	public void setExtendedInfo(Map<String, String> extendedInfo) {
		this.extendedInfo = extendedInfo;
	}
}
