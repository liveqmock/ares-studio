/**
 * 源程序名称：ModuleInfo.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：dollyn
 */
package com.hundsun.ares.studio.core.excel;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.CoreException;

import com.google.common.collect.BiMap;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.util.log.Log;

/**
 * @author dollyn
 *
 */
public class Module {

	public String name;
	public String cName;
	
	private String[] nameSegments;
	private String[] cNameSegments;
	
	public IARESModule create(IARESModuleRoot root, Log log) {
		if (nameSegments == null && cNameSegments == null)
			return null;
		
		try {
			return root.createModule(nameSegments, cNameSegments);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean exists(IARESModuleRoot root) {
		IARESModule module = root.getModule(StringUtils.join(nameSegments, "."));
		return module != null && module.exists();
	}
	
	public void processName(BiMap<String, String> moduleNameMap, Log log) {
		
		if (StringUtils.isBlank(name) && StringUtils.isNotBlank(cName) && isEN(cName)) {
			name = cName;
		}
		
		if (nameSegments == null && name != null) {
			nameSegments = name.split("[-/]");
		}
		
		if (cNameSegments == null && cName != null) {
			cNameSegments = cName.split("[-/]");
		}
		
		if (nameSegments == null && cNameSegments == null)
			return;
		
		// 英文名未指定，则自动生成一个
		if (nameSegments == null || nameSegments.length == 0) {
			nameSegments = new String[cNameSegments.length];
			for (int i = 0; i < cNameSegments.length; i++) {
				String eName = generateEName(moduleNameMap, cNameSegments[i], log);
				nameSegments[i] = eName;
			}
		}
		
		// 中文名未指定
		if (cNameSegments == null) {
			cNameSegments = new String[nameSegments.length];
			for (int i = 0; i < nameSegments.length; i++) {
				cNameSegments[i] = nameSegments[i];
			}
		} else {
			if (nameSegments.length != cNameSegments.length) {
				log.error(String.format("模块[%s(%s)]的中英文名无法对应", name, cName), null);
				return;
			}
		}
	}
	
	/**
	 * 判断传入的字符串是否是英文
	 * 
	 * @param key
	 * @return
	 */
	private static boolean isEN(String key){
		if(key.matches("(^[a-z_][a-z0-9_]{0,49}$)")){
			return true;
		}
		return false;
	}
	
	private String generateEName(BiMap<String, String> moduleNameMap, String cName, Log log) {
		String existingEName = moduleNameMap.get(cName);
		if (existingEName == null) {
			String eName = "m" + StringUtils.replace(String.valueOf(cName.hashCode()), "-", "_");
			moduleNameMap.put(cName, eName);
			log.warn(String.format("模块%s没有指定英文名，将使用自动生成的英文名: %s", cName, eName), null);
			return eName;
		} else {
			String existingCName = moduleNameMap.inverse().get(existingEName);
			if (StringUtils.equals(existingCName, cName)) {
				return existingEName;
			} else {
				int suf = 1;
				String eName = null;
				while(true) {
					eName = existingEName + "_" + suf;
					existingCName = moduleNameMap.get(eName);
					if (existingCName == null || !StringUtils.equals(existingCName, cName)) {
						break;
					}
					suf++;
				}
				return eName;
			}
		}
	}
	
	/**
	 * 调用这个方法必须保证processName()方法已经调用过了
	 * @return
	 */
	public String getFullName() {
		return StringUtils.join(nameSegments, '.');
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cName == null) ? 0 : cName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Module other = (Module) obj;
		if (cName == null) {
			if (other.cName != null)
				return false;
		} else if (!cName.equals(other.cName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
