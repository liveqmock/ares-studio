/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.service;

import java.util.List;

/**
 * @author gongyf
 *
 */
public interface IDictionaryType extends IMetadataItem {
	List<IDictionaryItem> getItemList();
}
