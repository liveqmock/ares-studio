/**
 * 源程序名称：OracleUserWizard.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：王彬
 */
package com.hundsun.ares.studio.jres.database.oracle.ui.wizard;

import com.hundsun.ares.studio.ui.newwizard.ModuleARESResourceNewWizard;

/**
 * @author wangbin
 *
 */
public class OracleUserWizard extends ModuleARESResourceNewWizard {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.wizard.ARESResourceWizard#getResType()
	 */
	@Override
	protected String getResType() {
		// TODO Auto-generated method stub
		return "oracleuser";
	}
	

//	@Override
//	public void addPages() {
//		IResDescriptor resDescriptor = ARESResRegistry.getInstance().getResDescriptor(getResType());		
//		page = new NewARESResourceWizardPage("创建一个" + resDescriptor.getName(), workbench, selectedElement, getResType()){
//			@Override
//			protected void initNewResourceInfo(Object info) {
//				super.initNewResourceInfo(info);
//			}
//		};
//		page.setNewName(initText_Name);
//		addPage(page);
//	}

}
