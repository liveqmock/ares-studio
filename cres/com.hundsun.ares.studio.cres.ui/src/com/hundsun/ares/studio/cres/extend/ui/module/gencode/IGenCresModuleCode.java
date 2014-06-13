/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.extend.ui.module.gencode;

import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;

/**
 * @author qinyuan
 *
 */
public interface IGenCresModuleCode {
	
	/**
	 * 设置是否存在前置代码
	 * 
	 * @param isHeadCode
	 */
	public void setIsHeadCode(boolean isHeadCode);
	
	/**
	 * 设置是否存在前置代码
	 * 
	 * @param isHeadCode
	 */
	public void setIsEndCode(boolean isEndCode);
	
	/**
	 * 清空缓存
	 */
	public void clearCache();
	
	/**
	 * 获取代码生成的上下文
	 * @param project
	 * @return
	 * 	默认返回一个空的上下文
	 */
	public Map<Object, Object> getContext(IARESProject project);
	
	/**
	 * 生成模块代码
	 * @param module
	 * 		需要生车代码的模块
	 * @param context
	 * 		上下文
	 * @param isWithPath
	 * 		是否带目录
	 * @param isPathWithCname
	 * 		目录是否使用中文名
	 * @param monitor
	 * 		进度条
	 */
	void genModuleCode(IARESModule module, Map<Object, Object> context, boolean isWithPath,boolean isPathWithCname,IProgressMonitor monitor);
	
	
	/**
	 * 能否生成
	 * @param module
	 * 		需要生车代码的模块
	 * @return
	 */
	boolean canGenCode(IARESModule module);
	
	void setErrLog(StringBuffer errLog);
}