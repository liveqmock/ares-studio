package com.hundsun.ares.studio.jres.basicdata.ui.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.ui.BasicDataUI;
import com.hundsun.ares.studio.ui.wizard.ARESResourceWizard;

/**
 * @author lvgao
 * 
 */
public class NewBasicDataWizard extends ARESResourceWizard {

	public static final int MODE_SINGLE = 0; // 二维表模式
	public static final int MODE_MASTERSLAVE = 1; // 主从表模式
	public static final int MODE_MASTERSLAVELINK = 2; // 主从关联表模式

	public ModeDefine modeDfine = new ModeDefine();
	
	public NewBasicDataModelSelectWizardPage definePage;

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle("新建基础数据");
		
	}
	
	@Override
	protected String getResType() {
		switch (modeDfine.mode) {
		case MODE_SINGLE:
			return IBasicDataRestypes.singleTable;
		case MODE_MASTERSLAVE:
			return IBasicDataRestypes.MasterSlaveTable;
		case MODE_MASTERSLAVELINK:
			return IBasicDataRestypes.MasterSlaveLinkTable;
		default:
			break;
		}
		return null;
	}

	@Override
	public void addPages() {
		definePage = new NewBasicDataModelSelectWizardPage(	"vvv");
		page = new NewBasicDataWizardPage("创建一个基础数据", workbench,	selectedElement, getResType()){
			@Override
			protected void initNewResourceInfo(Object info) {
				super.initNewResourceInfo(info);
				((BasicDataBaseModel)info).setName(getNewName());
				((BasicDataBaseModel)info).setFile(getNewName() + ".sql");
			}
		};
		page.setNewName(initText_Name);
		getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin(BasicDataUI.PLUGIN_ID, "icons/full/obj16/BaiscData.png").createImage());
		addPage(page);
		addPage(definePage);
	}

	public IARESProject getProject() {
		return ((NewBasicDataWizardPage) page).getProject();
	}
	
	@Override
	public boolean canFinish() {
		if(definePage != this.getContainer().getCurrentPage()){
			return false;
		}else{
			if(!definePage.isPageComplete()){
				return false;
			}
			try {
				((NewBasicDataWizardPage)page).createBasicDataInfo("");
			} catch (Exception e) {
				definePage.setErrorMessage(e.getMessage());
				return false;
			}
		}
		return super.canFinish();
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage tpage) {
		if(tpage == page){
			definePage.update();
		}
		return super.getNextPage(page);
	}
	
	

}

class ModeDefine {

	public int mode = NewBasicDataWizard.MODE_SINGLE;

	public String inputType= "";

	public String single_masterTable = "";
	
	public String MS_masterTable = "";
	public String MS_slaveTable= "";
	
	public String MSL_masterTable = "";
	public String MSL_slaveTable= "";
	public String MSL_linkTable= "";
	
	
}
