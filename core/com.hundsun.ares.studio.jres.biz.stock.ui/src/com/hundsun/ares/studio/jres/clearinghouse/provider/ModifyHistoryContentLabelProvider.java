/**
 * 源程序名称：ModifyHistoryDialogLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.stock3.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：王小恒
 */
package com.hundsun.ares.studio.jres.clearinghouse.provider;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.LabelProvider;

import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.chouse.util.StockUtils;

/**
 * @author wangxh
 *
 */
public class ModifyHistoryContentLabelProvider extends LabelProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		if(element instanceof Modification){
			return StockUtils.getModificationDescription(null ,(Modification) element);
		}
		return StringUtils.EMPTY;
	}

}
