/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.script.engin.ScriptUtils;
import com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionConfigReader;
import com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionDialog;
import com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionRoot;


/**
 * @author lvgao
 *输入处理门面类
 */
public class ScriptInUtilImpl{
	private static Logger logger = Logger.getLogger(ScriptInUtilImpl.class);
	
	static final Logger console = ConsoleHelper.getLogger();

	int model;
	IARESResource jsResource;
	Map<String, Object> context;
	public ScriptInUtilImpl(int model,IARESResource jsResource,Map<String, Object> context){
		this.model = model;
		this.jsResource = jsResource;
		this.context = context;
	}

	public int getInput() {
		
		//2013年3月15日9:49:37 将用户配置文件与脚本文件放在一起
		//2013/5/6 zhuyf 通过读取工具资源模块根扩展点配置的文件夹，动态读取xml所在文件夹
//		IFolder rootFolder = ARESElementUtil.getModuleRootFolder(jsResource.getARESProject(),"com.hundsun.ares.studio.jres.moduleroot.tools");
		
		String filepath = String.format("/%s.xml", jsResource.getName());
		
		//2013年5月13日9:27:22 脚本带模块，xml文件可能不在默认模块下面，需要取脚本直接所在的模块目录
		IFolder rootFolder = (IFolder) jsResource.getParent().getResource();
		
		if(rootFolder == null){
			console.error(String.format(".respath中不存在扩展点[%s]对应的模块根配置。", "com.hundsun.ares.studio.jres.moduleroot.tools"));
			return Window.CANCEL;
		}
		
		IFile file =  rootFolder.getFile(filepath);
		
		if(!file.exists()){
			//console.error(String.format("文件[%s]不存在。", file.getFullPath().toOSString()));
			return Window.OK;
		}
		
		UserOptionConfigReader instance = new UserOptionConfigReader();
		try {
			final UserOptionRoot root = instance.read(file.getContents());
			if(ScriptUtils.MODE_BUILDER == model || ScriptUtils.MODE_CMD_BUILDER == model){
				//root.setOptionValue();
				root.setDefaultValue();
				context.putAll(root.getOptionValue()) ;
			}else{
				UserOptionDialog dlg = new UserOptionDialog(new Shell(),root , jsResource.getARESProject());
				int inState = dlg.open();
				context.putAll(root.getOptionValue()) ;
				return inState;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			console.error(String.format("获取用户输入失败，详细信息:%s", e.getMessage()));
		}
		return Window.CANCEL;
	}
	
	
	
}
