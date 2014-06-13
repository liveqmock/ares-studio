/**
 * 源程序名称：NewTableWizard.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.wizard;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleConstant;
import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;
import com.hundsun.ares.studio.jres.database.ui.extend.ModuleDatabasePropertyPage;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.DatabaseModuleExtensibleProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleFactory;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleTableProperty;
import com.hundsun.ares.studio.jres.model.database.oracle.table_type;
import com.hundsun.ares.studio.ui.newwizard.ARESResourceNewWizardPage;
import com.hundsun.ares.studio.ui.newwizard.ModuleARESResourceNewWizard;


/**
 * @author qinyuan
 *
 */
public class NewTableWizard extends ModuleARESResourceNewWizard {

	@Override
	protected void initNewResourceInfo(Object info) {
		super.initNewResourceInfo(info);
		
		if(info instanceof TableResourceData) {
			TableResourceData table = (TableResourceData)info;
			Map<Object, Object> context = getContext();
			String resname = context.get(ARESResourceNewWizardPage.CONTEXT_KEY_NAME).toString();
			String resCName = context.get(ARESResourceNewWizardPage.CONTEXT_KEY_CNAME).toString();
			if(StringUtils.isNotBlank(resname)){
				table.setName(resname);
			}
			if(StringUtils.isNotBlank(resCName)){
				table.setChineseName(resCName);
			}
			//RevisionHistory history = CoreFactory.eINSTANCE.createRevisionHistory();
			//history.setModifiedDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
			//history.setVersion(getVersion());
			//table.getHistories().add(history);
			fillTableInfo(table);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.newwizard.ModuleARESResourceNewWizard#addPages()
	 */
	@Override
	public void addPages() {
		super.addPages();
		getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin(DatabaseUI.PLUGIN_ID, "icons/table.gif").createImage());
	}
	
	/**
	 * 新建表资源时，填充模型的相关默认信息
	 * 
	 * @param table
	 */
	private void fillTableInfo(TableResourceData table){
		if(selectedElement instanceof IARESModule){
			IARESModule module = (IARESModule)selectedElement;
			while(module != null){
				IARESResource moduleRes = module.getARESResource("module.xml");
				if (moduleRes != null && moduleRes.exists()) {
					try {
						ModuleProperty modulePro = moduleRes.getInfo(ModuleProperty.class);
						DatabaseModuleExtensibleProperty mem = (DatabaseModuleExtensibleProperty) modulePro.getMap().get(ModuleDatabasePropertyPage.KEY);
						if (mem == null) {
							module = module.getParentModule();
							continue;
						}
						OracleTableProperty tableOp = OracleFactory.eINSTANCE.createOracleTableProperty();
						table.getData2().put(IOracleConstant.TABLE_DATA2_KEY, tableOp);
						String moduleSpace = mem.getSpace();
						table_type moduleTableType = mem.getTableType();
						if (StringUtils.isBlank(moduleSpace) && moduleTableType == null) {
							module = module.getParentModule();
							continue;
						}
						tableOp.setSpace(moduleSpace);
						tableOp.setTabletype(moduleTableType);
						break;
					} catch (ARESModelException e) {
						e.printStackTrace();
						module = module.getParentModule();
					}
				}else {
					module = module.getParentModule();
				}
			}
		}
	}
	
	/**
	 * 参考com.hundsun.ares.studio.ui.editor.actions.AddRevisionHistoryRecordAction.getVersion()
	 * @return
	 */
	private String getVersion() {
		// 2012-09-28 sundl 计算最大版本的时候只取资源所在的当前层的模块
		// 2012-11-21 sundl 数据库下的资源按顶层模块计算； 其他地方的资源仍然取当前模块
		// 2012-12-28 sundl 元数据下的 资源不计算模块，只在本资源和项目属性中取最大值
		IARESModule topModule = null; 
		
		if (selectedElement == null) {
			topModule = null; 
		} else {
			if(selectedElement instanceof IARESModule){
				IARESModule module = (IARESModule)selectedElement;
				if( module.getElementName().indexOf('.') > 0){
					IARESModuleRoot root = module.getRoot();
					String moduleName = StringUtils.substringBefore(module.getElementName(), ".");
					topModule =  root.getModule(moduleName);
				}else {
					topModule = module;
				}
			}
		}
		
		// 当前已经保存的资源中的最大版本
		String currentVersion = RevisionHistoryUtil.getMaxVersion(topModule);
		// 当前编辑器中的最大版本
		//
//		String maxInEditor = RevisionHistoryUtil.getMaxVersion((List<RevisionHistory>)info.eGet(eReference));

		// 项目属性
		String projectVersion = RevisionHistoryUtil.getProjectPropertyVersion(selectedElement.getARESProject());
		
		// 找上述3者最大值
		String versionStr = RevisionHistoryUtil.max(Arrays.asList(currentVersion, projectVersion));
		// 第一次找不到任何记录的时候
		if (StringUtils.isEmpty(versionStr)) {
			versionStr = "1.0.0.0";
		} 
		return versionStr;
	}
	
	@Override
	protected String getResType() {
		return "table";
	}
	
}
