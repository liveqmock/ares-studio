/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.script.api.biz.IBizScriptWrap;
import com.hundsun.ares.studio.jres.script.api.biz.IObjScriptWrap;


/**
 * ARES功能对象,可直接或者间接的获取改对象下的所有信息
 * 
 * @author lvgao
 *
 */
public interface IARESProjectScriptWrap extends IMetadataScriptWrap,IBasicdataScriptWrap,IDatabaseScriptWrap,
												IBusinessScriptWrap, IObjScriptWrap, IBizScriptWrap,IProcedureScriptWrap,
												ILogicScriptWrap,IGenCodeScriptWrap,IScriptCallScriptWarp,IGenCode4UFTScriptWrap,IScriptCmdBuilderWarp{
	
	/**
	 * 获取工程的绝对路径
	 * <p>返回格式如：D:\workspace\helloworld\</p>
	 * @return 
	 */
	public String getProjectPath();
	
	/**
	 * 获取ARESProject对象
	 * 
	 * @return
	 */
	public IARESProject getARESProject();
	
	/**
	 * 获取所有的子系统
	 * 
	 * @return
	 */
	public String[] getAllSubsys();
	
	/**
	 * 获取指定的模块根下面的所有的子系统，即顶层模块; 参数rootType为模块根的类型Id，具体值可以参考项目目录下的
	 * .respath文件;<br>
	 * 默认模块（即名字为空字符串“”的）并不作为子系统处理。
	 * @param root
	 * @return
	 */
	public IARESModuleWrap[] getSubSystems(String rootType);
	
	/**
	 * 根据类型获取模块根， 返回找到的第一个指定类型的模块根; 找不到即返回null
	 * @param type <!-- 后续可以考虑添加getRootByName()等其他获取方法 -->
	 * @return
	 */
	IARESModuleRootWrap getRoot(String type);
}
