/**
 * 源程序名称：DomainDataType.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.pdm.bean;


/**
 * @author liaogc
 *
 */
public class PDMDomainDataType {
	private String typeName = "";//类型名
	private String typeDesc = "";//类型描述
	private String typeReal = "";//真实类型
	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return the typeDesc
	 */
	public String getTypeDesc() {
		return typeDesc;
	}
	/**
	 * @param typeDesc the typeDesc to set
	 */
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	/**
	 * @return the typeReal
	 */
	public String getTypeReal() {
		return typeReal;
	}
	/**
	 * @param typeReal the typeReal to set
	 */
	public void setTypeReal(String typeReal) {
		this.typeReal = typeReal;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PDMDomainDataType) {
			return typeName.equals(((PDMDomainDataType) obj).typeName);
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return typeName.hashCode();
	}
	
}
