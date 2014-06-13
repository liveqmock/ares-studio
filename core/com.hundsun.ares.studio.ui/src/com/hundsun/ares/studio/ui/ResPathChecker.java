/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.core.registry.IModuleRootDescriptor;
import com.hundsun.ares.studio.core.registry.ModulesRootTypeRegistry;
import com.hundsun.ares.studio.internal.core.ARESProject;

/**
 * 检查respath中配置的模块根是否存在相应的插件
 * @author liaogc
 */
public class ResPathChecker {
	private static ResPathChecker instance  = null;
	private static final Logger console = ConsoleHelper.getLogger();
	private static Logger logger = Logger.getLogger(ResPathChecker.class.getName());
	private StringBuffer messasge = new StringBuffer();/*出错信息*/
	private static String METADATA_MODULEROOT_TYPE = "com.hundsun.ares.studio.jres.moduleroot.metadata";
	private static String DOMMONDATA_MODULEROOT_TYPE= "com.hundsun.ares.studio.jres.moduleroot.commondata";
	private static String DATABASE_MODULEROOT_TYPE= "com.hundsun.ares.studio.jres.moduleroot.database";
	private static String TOOL_MODULE_ROOT_TYPE = "com.hundsun.ares.studio.jres.moduleroot.tools";
	private static String ATOMROOT_MODULE_ROOT_TYPE = "com.hundsun.ares.studio.atom.resources.atomroot";
	private static String LOGICROOT_MODULE_ROOT_TYPE = "com.hundsun.ares.studio.logic.resources.logicroot";
	private static String BUSINESS_MODULE_ROOT_TYPE = "com.hundsun.ares.studio.jres.moduleroot.business";
	private static String BOJECT_MODULE_ROOT_TYPE ="com.hundsun.ares.studio.jres.obj.root";
	private static String PROCEDURE_MODULE_ROOT_TYPE = "com.hundsun.ares.studio.procedure.resources.procedure";
	
	private static String CRES_NATRUE = "com.hundsun.ares.studio.cresnature";
	
    private ResPathChecker(){
    	
    }
	public static ResPathChecker getInstance(){
		if(null ==instance){
			instance = new ResPathChecker();
		}
		return instance;
	}
	/**
	 * 解析respath.xml中的配置的模块根与实现com.hundsun.ares.studio.core.moduleRoot扩展点的插件比较,如果respath.xml模块不存在相关插件则进行错误提示
	 */
	public void resPathCheck(){
		List<IARESProject> projects = this.getWorksapceProjects();
		Map<String, IModuleRootDescriptor>  types = ModulesRootTypeRegistry.getInstance().getRootTypes();
		for(IARESProject project:projects){
			IResPathEntry[] resPathEntries;
			try {
				resPathEntries = ((ARESProject)project).readResPath();
				if(resPathEntries!=null){
					for(IResPathEntry resPathEntry:resPathEntries){
						String type = resPathEntry.getType();
						
						if(!(types!=null && types.keySet().contains(type))){//如果配置的模块不存在相关插件
							if(StringUtils.equals(METADATA_MODULEROOT_TYPE, type)){
								messasge.append("项目"+project.getElementName()+":"+type+"必须存在元数据组件"+"\r\n");
							}else if(StringUtils.equals(DOMMONDATA_MODULEROOT_TYPE, type)){
								messasge.append("项目"+project.getElementName()+":"+type+"必须存在基础数据组件"+"\r\n");
							}else if(StringUtils.equals(DATABASE_MODULEROOT_TYPE, type)){
								messasge.append("项目"+project.getElementName()+":"+type+"必须存在数据库组件"+"\r\n");
							}else if(StringUtils.equals(TOOL_MODULE_ROOT_TYPE, type)){
								messasge.append("项目"+project.getElementName()+":"+type+"必须存在用户脚本组件"+"\r\n");
							}else if(StringUtils.equals(ATOMROOT_MODULE_ROOT_TYPE, type)){
								checkARESNature(project);
								messasge.append("项目"+project.getElementName()+":"+type+"必须存在CRES原子组件"+"\r\n");
							}else if(StringUtils.equals(LOGICROOT_MODULE_ROOT_TYPE, type)){
								checkARESNature(project);
								messasge.append("项目"+project.getElementName()+":"+type+"必须存在CRES逻辑组件"+"\r\n");
							}else if(StringUtils.equals(BUSINESS_MODULE_ROOT_TYPE, type)){
								messasge.append("项目"+project.getElementName()+":"+type+"必须存在服务接口组件"+"\r\n");
							}else if(StringUtils.equals(BOJECT_MODULE_ROOT_TYPE, type)){
								messasge.append("项目"+project.getElementName()+":"+type+"必须存在对象组件"+"\r\n");
							}else if(StringUtils.equals(PROCEDURE_MODULE_ROOT_TYPE, type)){
								messasge.append("项目"+project.getElementName()+":"+type+"必须存在过程组件"+"\r\n");
							}
							
						}
					}
				}
			
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
			
			
		}
		
		if(StringUtils.isNotBlank(this.getMessasge().toString())){//如果存在错误
			console.error(messasge.toString());
			logger.error(messasge.toString());
			Status status = new Status(IStatus.ERROR, ARESUI.PLUGIN_ID, messasge.toString()); 
			ErrorDialog.openError(Display.getDefault().getActiveShell(), "respath配置的模块根有错:", null, status);

		}
		
	}
	
	/**
	 * @return the messasge
	 */
	public String getMessasge() {
		return messasge.toString();
	}

	/**
	 * 获得工作空间中的所有非关闭的ARESProject项目
	 * @return
	 */
	private List<IARESProject> getWorksapceProjects(){
		List<IARESProject>aresProjects = new ArrayList<IARESProject>();
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		if(projects!=null && projects.length>0){
			for(IProject project:projects){
				if(project.isOpen()&& ARESProject.hasARESNature(project)){
					IARESProject aresProject = ARESCore.create(project);
					if(aresProject!=null){
						aresProjects.add(aresProject);
					}
				}
			}
		}
		
		return aresProjects;
	}
	/**
	 * 校验是否存在AresNature
	 * @param project
	 */
	private void checkARESNature(IARESProject project){
		try {
			if(!project.getProject().hasNature(CRES_NATRUE)){
				messasge.append("项目:"+project.getElementName()+"    "+CRES_NATRUE+"必须存在CRES Feature组件"+"\r\n");
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
}



