/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.service;

/**
 * @author gongyf
 *
 */
public interface IErrorNoItem extends IMetadataItem {
	String getConstantName();
	String getLevel();
	String getMessage();
	String getNo();
}
