package com.hundsun.ares.studio.reference.internal;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.hundsun.ares.studio.core.IARESProject;

/**
 * 收集信息的最小区域单位，是工程和资源类型
 * @author gongyf
 *
 */
public class Scope {
	private IARESProject project;
	private String resType;
	
	/**
	 * @param project
	 * @param resType 引用类型
	 */
	public Scope(IARESProject project, String resType) {
		super();
		this.project = project;
		this.resType = resType;
	}
	
	/**
	 * @return the project
	 */
	public IARESProject getProject() {
		return project;
	}
	
	/**
	 * @return the resType
	 */
	public String getResType() {
		return resType;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof Scope) {
			return ObjectUtils.equals(project, ((Scope) obj).project) 
				&& ObjectUtils.equals(resType, ((Scope) obj).resType);
		}
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(project).append(resType).toHashCode();
	}
}