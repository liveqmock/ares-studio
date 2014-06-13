/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.service;

/**
 * @author gongyf
 *
 */
public interface IStandardField extends IMetadataItem {
	IBusinessDataType getDataType();
	String getDataTypeId();
	IDictionaryType getDictionaryType();
	String getDictionaryTypeId();
	
	String getLength();
	String getPrecision();
	String getRealType(String typeId);
}
