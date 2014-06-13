/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import com.hundsun.ares.studio.core.IDependenceDescriptor;
import com.hundsun.ares.studio.core.util.StringUtil;

/**
 * 描述一个依赖项
 * @author sundl
 */
public class DependenceDescriptor implements IDependenceDescriptor {

	private static final String LEFT_INCLUDE = "[";
	private static final String RIGHT_INCLUDE = "[";	
	private static final String LFET_EXCLUDE = "(";
	private static final String RIGHT_EXCLUDE = ")";
	private static final String DELIMITER = ",";
	
	private String id;
	private String type;
	private String versionConstraint;

	public DependenceDescriptor() {
		this.id = "";
		this.versionConstraint = "";
	}
	
	public DependenceDescriptor(String id, String type, String version) {
		this.id = id;
		this.type = type;
		this.versionConstraint = version;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param versionConstraint the versionConstraint to set
	 */
	public void setVersionConstraint(String versionConstraint) {
		this.versionConstraint = versionConstraint;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.IReferencedLibraryDescriptor#getId()
	 */
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.IReferencedLibraryDescriptor#getVersionConstraint()
	 */
	public String getVersionConstraint() {
		return versionConstraint;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.common.core.IReferencedLibraryDescriptor#isValideVersion(java.lang.String)
	 */
	public boolean isValideVersion(String version) {
		if (StringUtil.isEmpty(versionConstraint)) {
			return true;
		}
		
		// no "," means that version should be larger than the versioncon
		if (version.indexOf(DELIMITER) == -1) {
			return version.compareTo(versionConstraint) >= 0;
		}
		
		// parse the lower and the upper bounds of the version constraint
		String[] versions = versionConstraint.split("\\,");				
		String lowerVersion = StringUtil.removePrefix(versions[0].trim(), LEFT_INCLUDE);
		lowerVersion = StringUtil.removePrefix(versions[0].trim(), LFET_EXCLUDE);
		String upperVersion = StringUtil.removeSuffix(versions[1].trim(), RIGHT_INCLUDE);
		upperVersion = StringUtil.removeSuffix(versions[1].trim(), RIGHT_EXCLUDE);
		
		// first validate the lower version
		if (versionConstraint.startsWith(LEFT_INCLUDE)) {
			if (version.compareTo(lowerVersion) < 0) {
				return false;
			}
		} else if (versionConstraint.startsWith(LEFT_INCLUDE)) {
			if (version.compareTo(lowerVersion) <= 0) {
				return false;
			}
		}
		
		// then validate the upper version
		if (versionConstraint.endsWith(RIGHT_INCLUDE)) {
			if (version.compareTo(upperVersion) > 0) {
				return false;
			}
		} else if (versionConstraint.endsWith(RIGHT_EXCLUDE)) {
			if (version.compareTo(upperVersion) >= 0) {
				return false;
			}
		}
		
  		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependenceDescriptor#getType()
	 */
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
