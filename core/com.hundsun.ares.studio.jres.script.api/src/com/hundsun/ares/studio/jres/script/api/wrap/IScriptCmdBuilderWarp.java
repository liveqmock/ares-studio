/**
 * 源程序名称：IScriptCmdBuilderWarp.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script.api
 * 功能说明：$desc
 * 相关文档：
 * 作者：liaogc
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import java.util.Map;

/**
 * 命令行编译脚本接口
 * @author liaogc
 *
 */
public interface IScriptCmdBuilderWarp {

	/**
	 * 
	 * @param scripts 脚本简要说明以及脚本路径
	 * @param parameters 脚本中其他参数
	 */
	public void build(String[][] scripts,Map<String,Object> parameters);



}
