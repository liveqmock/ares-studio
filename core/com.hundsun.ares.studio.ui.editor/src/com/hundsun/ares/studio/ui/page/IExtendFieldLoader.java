/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.page;

import com.hundsun.ares.studio.core.model.extendable.ExtendFieldsHeader;


public interface IExtendFieldLoader extends IExtendItemLoader {
	ExtendFieldsHeader[] getExtendFields();
	void setContext(Object context);
}
