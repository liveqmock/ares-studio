package com.hundsun.ares.studio.jres.service.ui.wizard;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;

import com.hundsun.ares.studio.biz.ARESObject;
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
import com.hundsun.ares.studio.core.excel.HisSheetParserFactory;
import com.hundsun.ares.studio.core.excel.ISheetParserFactory;
import com.hundsun.ares.studio.core.excel.Resource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.core.util.log.Log;
import com.hundsun.ares.studio.jres.service.Service;

public class ImportServiceWizard extends ImportExcelWizard {

	@Override
	protected BizImportOperation createImportOperation(File[] files) {
		BizImportOperation operation = new BizImportOperation(files, this) {
			@Override
			protected IARESModuleRoot getRoot(Resource res) {
				if (StringUtils.equals(res.type, IBizResType.Service)) {
					return getBizRoot();
				} else if (StringUtils.equals(res.type, IBizResType.Object)) {
					IFolder objFolder = ARESElementUtil.getModuleRootFolder(ImportServiceWizard.this.project, IBizConstants.OBJ_ROOT);
					if (objFolder != null) {
						return (IARESModuleRoot) ARESCore.create(objFolder);
					} else {
						return getBizRoot();
					}
				}
				return null; 
			}
			
			@Override
			protected ExcelParser createParser(File file, Log log) {
				ExcelParser parser = new ExcelParser(file, log);
				parser.context.put("project", this.project);
				
				ISheetParserFactory[] factories = new ISheetParserFactory[] {
					ResourceListSheetParserFactory.INSTANCE,
					BizSheetParserFactory.INSTANCE,
					ServiceSheetParserFactory.INSTANCE,
					HisSheetParserFactory.INSTANCE
				};
				
				ComposedSheetHandlerFactory factory = new ComposedSheetHandlerFactory(factories);
				parser.factory = factory;
				return  parser;
			}
			
			/* (non-Javadoc)
			 * @see com.hundsun.ares.studio.biz.excel.ImportOperation#processParameterTypes(org.eclipse.core.runtime.IProgressMonitor)
			 */
			protected void setParameterTypes(IProgressMonitor monitor) {
				monitor.beginTask("分析参数类型...", resources.size());
				for (Resource res : resources.values()) {
					if (res.info instanceof Service) {
						Service srv  = (Service) res.info;
						for (Parameter p : srv.getInterface().getInputParameters()) {
							setParameterType(p);
						}
						
						for (Parameter p : srv.getInterface().getOutputParameters()) {
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
	
	protected IARESModuleRoot getBizRoot() {
		IFolder bizFolder = ARESElementUtil.getModuleRootFolder(this.project, IBizConstants.BIZ_ROOT2);
		if (bizFolder == null)
			bizFolder = ARESElementUtil.getModuleRootFolder(this.project, IBizConstants.BIZ_ROOT);
		return (IARESModuleRoot) ARESCore.create(bizFolder);
	}

}
