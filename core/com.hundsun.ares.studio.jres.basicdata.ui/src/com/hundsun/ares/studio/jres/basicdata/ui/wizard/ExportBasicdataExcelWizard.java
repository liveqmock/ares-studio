package com.hundsun.ares.studio.jres.basicdata.ui.wizard;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.PackageDefine;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.SingleTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.impl.BasicDataBaseModelImpl;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BasicDataEpackageFactory;
import com.hundsun.ares.studio.jres.basicdata.ui.BasicDataUI;

public class ExportBasicdataExcelWizard extends ExportExcelWizard{
	private static Logger logger = Logger.getLogger(ExportBasicdataExcelWizard.class);
	private Multimap<IARESModule ,IARESResource> basicDataMap = LinkedHashMultimap.<IARESModule, IARESResource>create();

	public ExportBasicdataExcelWizard() {
		super("");
		setWindowTitle("导出基础数据");

	}
	
	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		page.getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin(BasicDataUI.PLUGIN_ID, "icons/full/obj16/BasicDataBaseModel.gif").createImage());
	}

	@Override
	public void addPages() {
		IFolder rootFolder = ARESElementUtil.getModuleRootFolder(this.project,"com.hundsun.ares.studio.jres.moduleroot.commondata");
		if( rootFolder != null){
			setModuleRootName(rootFolder.getName());//这里必须通过扩展点得到文件夹名，未必是business
			addPage(page = new ExportWizardPage("导出基础数据", selection ,moduleRootName){
				protected void addDirButtonListener(org.eclipse.swt.widgets.Button dirButton, final org.eclipse.swt.widgets.Group pathGroup) {
					dirButton.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(final SelectionEvent e) {
							
							DirectoryDialog dialog = new DirectoryDialog(pathGroup.getShell(), SWT.OPEN);
							String filePath = dialog.open();
							txtPath.setText(filePath);
						}
						
					});
				};
			});//在显示导出向导界面时，再重新指定一次模块根目录
			return;
		}
		MessageDialog.openError(getShell(), "导出错误", "当前工程不存在基础数据模块根！");
	}
	
	@Override
	public IWorkspaceRunnable createExportOperation(IARESProject project,
			final List<IARESResource> results, String file) {
			IWorkspaceRunnable runnable = new IWorkspaceRunnable() {
			
			@Override
			public void run(IProgressMonitor monitor) throws CoreException {
				monitor.beginTask("资源处理。。。", 4);
				for(IARESResource res : results){
					//按模块分组
					iteratorModuleRes(res);
				}
				monitor.worked(1);
				monitor.subTask("开始导出...");
				exportExcel(monitor);
				monitor.done();
			}
		};
		return runnable;
	}
	
	public void exportExcel(IProgressMonitor monitor) {
		monitor.subTask("生成文档...");
		OutputStream output = null;
		
		String path = page.getPath();
		//每一个模块创建一个文件
		for (IARESModule module : basicDataMap.keySet()) {
			try {
				Map<String, List<String>> headInfo = new LinkedHashMap<String, List<String>>();
				Map<String, List<List<String>>> contents = new LinkedHashMap<String, List<List<String>>>();
				String moduleCname = getMoudleCname("." ,module);
				String fileName = StringUtils.isBlank(moduleCname)? module.getShortName():moduleCname;
				if(StringUtils.isNotBlank(moduleCname)){
					fileName+="("+module.getShortName()+")";
				}
				output = new FileOutputStream(path + "\\通用数据-" + StringUtils.replace(fileName, ".", "-") + ".xls");
				
				//组装导出信息
				
				constractContent(moduleCname,headInfo,contents,module);
				
				// 根据contents中的内容决定需要有多少个sheet页
				int[] starts = new int[contents.keySet().size()];
				for (int i = 0; i < starts.length; i++) {
					starts[i] = 1;// sheet页的起始位置
				}
				
				ExportBasicdataUtil.exportBasicData(headInfo, output, contents,
						contents.keySet().toArray(new String[0]), starts,
						starts);
//				POIUtils.putExcelString(output, null, contents,null,
//						contents.keySet().toArray(new String[0]), starts,
//						starts);

			} catch (FileNotFoundException e) {
				throw new RuntimeException("导出文件不存在，请检查导出路径！"+e.getMessage() ,e);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}finally{
				if (output != null) {
					IOUtils.closeQuietly(output);
				}
			}
		}
		monitor.worked(1);
	}
	
	/**
	 * 获取模块中文名
	 * @param module
	 * @return
	 */
//	private String getMoudleCname(IARESModule module) {
//		IARESResource res = module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
//		ModuleProperty property = null;
//		try {
//			property = res.getInfo(ModuleProperty.class);
//		} catch (ARESModelException e) {
//			e.printStackTrace();
//			return StringUtils.EMPTY;
//		}
//		String cName = property.getString(ICommonModel.CNAME);
//		return StringUtils.isBlank(cName)?StringUtils.EMPTY:cName;
//	}

	public String getMoudleCname (String separator ,IARESModule module){
		StringBuffer sb = new StringBuffer();
		getModuleChineseName(module, sb ,separator);
		String[] ps = StringUtils.split(sb.toString(), separator);
		StringBuffer sbf = new StringBuffer();
		for (int i = ps.length-1; i > -1 && ps.length > 0; i--) {
			if (StringUtils.isNotBlank(sbf.toString())) {
				sbf.append(separator);
			}
			sbf.append(ps[i]);
		}
		return sbf.toString();
	}
	
	public void getModuleChineseName (IARESModule module , StringBuffer sb , String separator){
		if (module != null) {
			IARESResource property = module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
			if (property != null && property.exists()) {
				try {
					ModuleProperty info = property.getInfo(ModuleProperty.class);
					if (info != null) {
						Object obj = info.getValue(ICommonModel.CNAME);
						if (obj != null) {
							if (StringUtils.isNotBlank(sb.toString())) {
								sb.append(separator);
							}
							sb.append(obj.toString());
						}
					}
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}
			getModuleChineseName(module.getParentModule(), sb ,separator);
		}
	}
	
	private List<String> getDirTitle() {
		List<String> dirTitle = new ArrayList<String>();
		dirTitle.add("模块英文名");
		dirTitle.add("模块中文名");
		dirTitle.add("对象号");
		dirTitle.add("基础数据中文名");
		dirTitle.add("基础数据英文名");
		dirTitle.add("关联表英文名");
//		dirTitle.add("关联表中文名");
		dirTitle.add("基础数据模式");
		dirTitle.add("资源sheet名");
		dirTitle.add("生成脚本文件名");
		return dirTitle;
	} 
	
	private String valideSheetName(String name){
		if(name.length() > 31){
			return String.format("名称[%s]不能大于31个字符",name);
		}else if(name.startsWith("'") || name.endsWith("'")){
			return String.format("名称[%s]不能以\"'\"开始或结束",name);
		}else if(StringUtils.contains(name, ":")){
			return String.format("名称[%s]不能包含特殊字符\":\"",name);
		}else if(StringUtils.contains(name, "\\")){
			return String.format("名称[%s]不能包含特殊字符\"\\\"",name);
		}else if(StringUtils.contains(name, "*")){
			return String.format("名称[%s]不能包含特殊字符\"*\"",name);
		}else if(StringUtils.contains(name, "?")){
			return String.format("名称[%s]不能包含特殊字符\"?\"",name);
		}else if(StringUtils.contains(name, "/")){
			return String.format("名称[%s]不能包含特殊字符\"/\"",name);
		}else if(StringUtils.contains(name, "[")){
			return String.format("名称[%s]不能包含特殊字符\"[\"",name);
		}else if(StringUtils.contains(name, "]")){
			return String.format("名称[%s]不能包含特殊字符\"]\"",name);
		}else if(StringUtils.isBlank(name)){
			return String.format("名称[%s]不能为空",name);
		}
		
		return StringUtils.EMPTY;
	}
	
	private void constractContent(String moduleCname,Map<String, List<String>> headInfo, Map<String, List<List<String>>> contents,
			IARESModule module) throws Exception{
		
		//目录数据
		List<List<String>> dirTable = new ArrayList<List<String>>();
		dirTable.add(getDirTitle());//目录页标题
		contents.put(ExportBasicdataUtil.dirSheetName, dirTable);
		Collection<IARESResource> ress = basicDataMap.get(module);
		for (IARESResource resource : ress) {
			if(StringUtils.equals(resource.getType(),IBasicDataRestypes.singleTable)){
				BasicDataBaseModelImpl info = null;
				try {
					info = resource.getInfo(BasicDataBaseModelImpl.class);
				} catch (ARESModelException e1) {
					logger.error("导出基础数据时，读取模型失败", e1);
					continue;
				}//模型
				EPackage ePackage = null;
				try {
					ePackage = BasicDataEpackageFactory.eINSTANCE.createEPackage(resource);
				} catch (Exception e) {
					logger.error("导出基础数据时，读取EPackage失败", e);
					continue;
				}
				EClass masterEclass = (EClass)ePackage.getEClassifier(IBasicDataEpacakgeConstant.MasterItem);
				List<List<String>> table = ExportBasicdataUtil.getMasterTableInfo(resource, info, masterEclass,true);

				String sheetName = "";
				//英文名
				String name = resource.getName();//不直接取模型的名称,因为有可能重命名,那时候模型中的英文名还不是最新的
				//最早的模型中有可能没有英文名，此时要用资源名作为英文名
				//if(StringUtils.isBlank(name)) {
				//	name = resource.getName();
				//}
				if (info != null) {
					if (StringUtils.isNotBlank(info.getChineseName())) {
						sheetName = "通用数据-" + info.getChineseName();
					}
				}
				if(StringUtils.isNotBlank(valideSheetName(sheetName)) || contents.containsKey(sheetName)) {
					sheetName = "通用数据-" + name;
				}
				if(StringUtils.isNotBlank(valideSheetName(sheetName)) || contents.containsKey(sheetName)) {
//					throw new Exception(valideSheetName(sheetName));
					logger.error(valideSheetName(sheetName));
					sheetName = "通用数据-N" + sheetName.hashCode();
				}
				
				contents.put(sheetName, table);//基础数据信息
				
				//关联表
				PackageDefine define = null;
				try {
					define = BasicDataEpackageFactory.eINSTANCE.getDefine(resource);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String linkedTableName = "";
				if(define != null){
					linkedTableName = ((SingleTable) define).getMaster();
				}
				
				//目录信息
				List<String> dirInfo = new ArrayList<String>();
				dirInfo.add(module.getElementName());
				dirInfo.add(moduleCname);
				dirInfo.add(info.getObjectId());
				dirInfo.add(info.getChineseName());
				dirInfo.add(name);
				dirInfo.add(linkedTableName);
//				dirInfo.add("");
				dirInfo.add("二维表");
				dirInfo.add(sheetName);
				dirInfo.add(info.getFile());
				dirTable.add(dirInfo);
				
				

				//头信息
				List<String> headInfoList = new ArrayList<String>();
				headInfoList.add("基础数据英文名");
				headInfoList.add(name);
				headInfoList.add("基础数据中文名");
				headInfoList.add(info.getChineseName());
				headInfoList.add("关联表");
				headInfoList.add(linkedTableName);
				headInfo.put(sheetName, headInfoList);
				
				
			}else if(StringUtils.equals(resource.getType(),IBasicDataRestypes.MasterSlaveTable)){
			}else if(StringUtils.equals(resource.getType(),IBasicDataRestypes.MasterSlaveLinkTable)){
			}else if(StringUtils.equals(resource.getType(), "module.xml")){
			}else {
			}
		}
	}

	/**
	 * 模块和资源的数据处理，将资源按报包分组
	 * 
	 * @param res
	 */
	private void iteratorModuleRes(IARESResource res){
		basicDataMap.put(getSubsys(res.getModule()), res);
	}
	
	private IARESModule getSubsys(IARESModule mod){
		if (mod != null) {
			if (StringUtils.indexOf(mod.getElementName(), ".") > -1) {
				return getSubsys(mod.getParentModule());
			}else {
				return mod;
			}
		}else {
			return null;
		}
	}

}
