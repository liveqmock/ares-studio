package com.hundsun.ares.studio.engin.constant;

import com.hundsun.ares.studio.core.IARESProblem;

public interface IEngineContextConstant {
	/////////////////////////公共上下文//////////////////////////////
	public static final String BUFFER = "文件内容";
	
	public static final String TOKEN_LIST = "token列表";
	
	public static final String DATE_STR = "日期字符串";
	
	public static final String USER_NAME = "用户名";
	
	public static final String DOMAIN_HANDLER = "domain处理器";
	
	public static final String CURR_RESOURCE = "当前资源";
	
	public static final String ENGINE_EXCEPTION = "生成代码时产生的错误";
	
	public static final String SKELETON_INPUT = "蓝图输入模型";
	
	
	/**
	 * 上下文中存取伪代码中出现过的变量，也就是@开头的变量
	 */
	public static final String PSEUDO_CODE_PARA_LIST = "伪代码使用变量列表";
	
	/**
	 * 上下文中存取为代码中出现是的对象变量,也就是@开始的对象对量,以及打包器或者解包器
	 */
	public static final String PSEUDO_CODE_OBJECT_PARA_LIST ="伪代码中使用的对象变量列表";


	
	/////////////////////////////////错误消息////////////////////////////////
	/**产生错误的对象**/
	public static final String MSG_ATTR_OWNER = IARESProblem.UNPERSISTENT_PROPERTY_PREFIX + ".MSG_ATTR_OWNER";

	/**产生错误的输入**/
	public static final String MSG_ATTR_INPUT = IARESProblem.UNPERSISTENT_PROPERTY_PREFIX + ".MSG_ATTR_INPUT";

	
	//////////////////////////////////监听器///////////////////////////////////////
	public static final String TOKEN_LISTENER_MANAGER = "监听器管理类";
	
	public static final String LASTEST_CUR_ID = "最近游标ID";
	
	/**
	 * 资源代码生成后需要返回给全局上下文的内容
	 */
	public static final String RETURN_CONTANT_TO_GLOBAL_CONTEXT = "资源代码生成后需要返回给全局上下文的内容";
}
