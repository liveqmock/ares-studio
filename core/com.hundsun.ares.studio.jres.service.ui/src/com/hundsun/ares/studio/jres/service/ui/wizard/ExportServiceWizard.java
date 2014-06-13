/**
 * 
 */
package com.hundsun.ares.studio.jres.service.ui.wizard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.biz.constants.IBizConstants;
import com.hundsun.ares.studio.biz.constants.IBizResType;
import com.hundsun.ares.studio.biz.ui.excel.ExcelStructCreateor;
import com.hundsun.ares.studio.biz.ui.excel.ExportExcelEntity;
import com.hundsun.ares.studio.biz.ui.wizard.ExportExcelWizard;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.service.ui.ServiceUI;

/**
 * @author yanwj06282
 *
 */
public class ExportServiceWizard extends ExportExcelWizard {

	public ExportServiceWizard() {
		super("");//zhuyf:这里必须要调用""参数的构造函数，否则会报空指针问题，具体原因还未探究。
		setWindowTitle("导出业务逻辑");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		page.getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin(ServiceUI.PLUGIN_ID, "icons/full/obj16/Service.gif").createImage());
	}
	
	@Override
	public void addPages() {
		IFolder rootFolder = ARESElementUtil.getModuleRootFolder(this.project,IBizConstants.BIZ_ROOT);
		if (rootFolder == null) {
			rootFolder = ARESElementUtil.getModuleRootFolder(this.project,IBizConstants.BIZ_ROOT2);
		}
		if( rootFolder != null){
			setModuleRootName(rootFolder.getName());//这里必须通过扩展点得到文件夹名，未必是business
			addPage(page = new ServiceExportWizardPage("", selection ,moduleRootName));//在显示导出向导界面时，再重新指定一次模块根目录
			return;
		}
		MessageDialog.openError(getShell(), "导出错误", "当前工程不存在业务逻辑模块根！");
	}
	
	@Override
	public IWorkspaceRunnable createExportOperation(final IARESProject project,
			final List<IARESResource> results, final String file) {
		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			
			@Override
			public void run(IProgressMonitor monitor) throws CoreException {
				monitor.beginTask("资源处理。。。", 4);
				Map<String , Multimap<IARESModule ,IARESResource>> serviceGroup = new LinkedHashMap<String , Multimap<IARESModule ,IARESResource>>();
				Map<String , Multimap<IARESModule ,IARESResource>> objectGroup = new LinkedHashMap<String , Multimap<IARESModule ,IARESResource>>();
				
				IARESModuleRoot root = null;
				if (results.size() > 0) {
					root = results.get(0).getRoot();
				}
				
				groupResByModule(serviceGroup ,objectGroup , results);
				
				Set<String> keySets = new HashSet<String>();
				keySets.addAll(serviceGroup.keySet());
				keySets.addAll(objectGroup.keySet());
				
				monitor.worked(1);
				monitor.subTask("开始导出...");
				if (((ServiceExportWizardPage)page).getSplitdocStatus()) {
					List<String> fileNames = new ArrayList<String>();
					String fileName = file;
					for (Iterator<String> subsysNames = keySets.iterator(); subsysNames.hasNext();) {
						String subsysName = subsysNames.next();
						Multimap<IARESModule, IARESResource> sm = serviceGroup.get(subsysName);
						Multimap<IARESModule, IARESResource> om =objectGroup.get(subsysName);
						String prefix = StringUtils.substringBeforeLast(file, ".");
						prefix += "-"+subsysName;
						if (root != null) {
							IARESModule module = root.getModule(subsysName);
							if (module != null) {
								IARESResource moduleRes= module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
								if (moduleRes != null && moduleRes.exists()) {
									ModuleProperty modulePro = moduleRes.getInfo(ModuleProperty.class);
									if (modulePro != null) {
										Object obj = modulePro.getValue(ICommonModel.CNAME);
										if (obj instanceof String) {
											prefix += "("+obj.toString()+")";
										}
									}
								}
							}
						}
						fileName = prefix + "." +  StringUtils.substringAfterLast(file, ".");
						fileNames.add(fileName);
						setFileName(fileNames);
						exportExcel(sm ,om,fileName, monitor);
					}
				}else {
					Multimap<IARESModule ,IARESResource> serviceMap = LinkedHashMultimap.<IARESModule, IARESResource>create();
					Multimap<IARESModule ,IARESResource> objectMap = LinkedHashMultimap.<IARESModule, IARESResource>create();
					for(Multimap<IARESModule, IARESResource> s : serviceGroup.values()){
						serviceMap.putAll(s);
					}
					for(Multimap<IARESModule, IARESResource> o : objectGroup.values()){
						objectMap.putAll(o);
					}
					exportExcel(serviceMap ,objectMap, file , monitor);
				}
				monitor.done();
			}
		};
		return runnable;
	}

	public void exportExcel(Multimap<IARESModule ,IARESResource> serviceMap , Multimap<IARESModule ,IARESResource> objectMap ,String fileName , IProgressMonitor monitor) {
		IMetadataService metadataService = DataServiceManager.getInstance().getService(project, IMetadataService.class);
		DataStructCreateor.metadataService = metadataService;
		DataStructCreateor.devValueStatus = ((ServiceExportWizardPage) page).getDefValueStatus();
		DataStructCreateor.multStatus = ((ServiceExportWizardPage) page).getMultStatus();
		DataStructCreateor.project = project;
		ExportExcelEntity entity = new ExportExcelEntity();
		

		if (objectMap != null) {
			monitor.subTask("对象资源处理...");
			ExportExcelEntity entity2 = DataStructCreateor.createObjectExcelEntity(objectMap);
			entity.getMenuList().addAll(entity2.getMenuList());
			entity.getSheetList().addAll(entity2.getSheetList());
			monitor.worked(1);
		}
		if (serviceMap != null) {
			monitor.subTask("服务资源处理...");
			ExportExcelEntity entity1 = DataStructCreateor.createServiceExcelEntity(serviceMap);
			entity.getMenuList().addAll(entity1.getMenuList());
			entity.getSheetList().addAll(entity1.getSheetList());
			monitor.worked(1);
		}
		monitor.subTask("生成文档...");
		ExcelStructCreateor.createExcelStruts(project, entity, fileName);
		monitor.worked(1);
	}
	
	/**
	 * 模块和资源的数据处理，将业务逻辑资源按报包分组
	 * 
	 * @param reses
	 */
	private void groupResByModule(Map<String , Multimap<IARESModule ,IARESResource>> serviceGroup ,Map<String , Multimap<IARESModule ,IARESResource>> objectGroup ,List<IARESResource> reses){
		for (IARESResource res : reses) {
			IARESModule module = res.getModule();
			String subsysKey = StringUtils.substringBefore(module.getElementName(), ".");
			
			if (StringUtils.equals(res.getType(), IBizResType.Object)) {
				if (objectGroup.get(subsysKey) == null) {
					objectGroup.put(subsysKey, LinkedHashMultimap.<IARESModule, IARESResource>create());
				}
				objectGroup.get(subsysKey).put(module, res);
			}else if (StringUtils.equals(res.getType(), IBizResType.Service)) {
				if (serviceGroup.get(subsysKey) == null) {
					serviceGroup.put(subsysKey, LinkedHashMultimap.<IARESModule, IARESResource>create());
				}
				serviceGroup.get(subsysKey).put(module, res);
			}
		}
	}

}
