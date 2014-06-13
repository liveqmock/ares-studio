package com.hundsun.ares.studio.procedure.ui.wizard;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.constants.IBizConstants;
import com.hundsun.ares.studio.biz.constants.IBizResType;
import com.hundsun.ares.studio.biz.excel.BizImportOperation;
import com.hundsun.ares.studio.biz.excel.factories.BizSheetParserFactory;
import com.hundsun.ares.studio.biz.excel.factories.ResourceListSheetParserFactory;
import com.hundsun.ares.studio.biz.ui.wizard.ImportExcelWizard;
import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.excel.ComposedSheetHandlerFactory;
import com.hundsun.ares.studio.core.excel.ExcelParser;
import com.hundsun.ares.studio.core.excel.ISheetParserFactory;
import com.hundsun.ares.studio.core.excel.Resource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.core.util.log.Log;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.constants.IProcedureConstant;
import com.hundsun.ares.studio.procdure.constants.IProcedureResType;
import com.hundsun.ares.studio.procdure.excel.ProcedureSheetParserFactory;
import com.hundsun.ares.studio.procdure.provider.ProcedureUI;

public class ImportProcedureWizard extends ImportExcelWizard{
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		    getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin(ProcedureUI.PLUGIN_ID, "icons/procedure.gif").createImage());
		    setWindowTitle("导入存储过程");
			page.setTitle("导入存储过程");
			page.setDescription("选择需要导入的导入存储过程");
			page.setMessage("选择需要导入的导入存储过程(文件或者目录)");
	}

	@Override
	protected BizImportOperation createImportOperation(File[] files) {
		BizImportOperation operation = new BizImportOperation(files, this) {
			@Override
			protected IARESModuleRoot getRoot(Resource res) {
				if (StringUtils.equals(res.type, IProcedureResType.PROCEDURE)) {
					IFolder atomFolder = ARESElementUtil.getModuleRootFolder(ImportProcedureWizard.this.project, IProcedureConstant.PROC_ROOT);
					return (IARESModuleRoot) ARESCore.create(atomFolder);
				}else if (StringUtils.equals(res.type, IBizResType.Object)) {
					IFolder objFolder = ARESElementUtil.getModuleRootFolder(ImportProcedureWizard.this.project, IBizConstants.OBJ_ROOT);
					if (objFolder == null) {
						objFolder = ARESElementUtil.getModuleRootFolder(ImportProcedureWizard.this.project, IBizConstants.OBJ_ROOT);
					}
					return (IARESModuleRoot) ARESCore.create(objFolder);
				}
				return null;
			}
			
			@Override
			protected ExcelParser createParser(File file, Log log) {
				ExcelParser parser = new ExcelParser(file, log);
				ISheetParserFactory[] factories = new ISheetParserFactory[] {
					ResourceListSheetParserFactory.INSTANCE,
					BizSheetParserFactory.INSTANCE,
					ProcedureSheetParserFactory.INSTANCE,
				};
				ComposedSheetHandlerFactory factory = new ComposedSheetHandlerFactory(factories);
				parser.factory = factory;
				return  parser;
			}
			
			/* (non-Javadoc)
			 * @see com.hundsun.ares.studio.biz.excel.ImportOperation#setParameterTypes(org.eclipse.core.runtime.IProgressMonitor)
			 */
			@Override
			protected void setParameterTypes(IProgressMonitor monitor) {
				monitor.beginTask("分析参数类型...", resources.size());
				for (Resource res : resources.values()) {
					if(res.info instanceof Procedure) {
						//过程
						Procedure procedure = (Procedure)res.info;
						for (Parameter p : procedure.getInputParameters()) {
							setParameterType(p);
						}
						
						for (Parameter p : procedure.getOutputParameters()) {
							setParameterType(p);
						}
						
						for (Parameter p : procedure.getInternalVariables()) {
							setParameterType(p);
						}
						
					}else if (res.info instanceof BizInterface) {
						BizInterface iface = (BizInterface) res.info;
						for (Parameter p : iface.getInputParameters()) {
							setParameterType(p);
						}

						for (Parameter p : iface.getOutputParameters()) {
							setParameterType(p);
						}
					} else if (res.info instanceof ARESObject) {
						ARESObject obj = (ARESObject) res.info;
						for (Parameter p : obj.getProperties()) {
							setParameterType(p);
						}
					}
					monitor.worked(1);
				}
				monitor.done();
			}
		};
		return operation;
	}

}
