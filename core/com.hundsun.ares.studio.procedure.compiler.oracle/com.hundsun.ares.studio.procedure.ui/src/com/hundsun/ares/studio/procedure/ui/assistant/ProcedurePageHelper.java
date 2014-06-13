/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.assistant;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.ui.extendpoint.manager.ExtendPageInfo;
import com.hundsun.ares.studio.ui.extendpoint.manager.ExtendPageManager;

/**
 * @author qinyuan
 *
 */
public class ProcedurePageHelper {
	
	//过程编辑器注册id
	public static final String procedure_editor_id = "com.hundsun.ares.studio.procedure.ui.editor.procedure";
	
	//证券二部扩展页面id
	public static final String stock_two_ex_page_id = "com.hundsun.ares.studio.procedure.ui.extend.procedureextendpage";

	/**
	 * 过程是否有证券二部扩展页面
	 * @return
	 */
	public static boolean hasProcedureStock2ExtendPage() {
		for(ExtendPageInfo info:ExtendPageManager.getDefault().getPageInfo(procedure_editor_id)){
			if(StringUtils.equals(info.getPageId(), ProcedurePageHelper.stock_two_ex_page_id)){
				return true;
			}
		}
		return false;
	}

}
