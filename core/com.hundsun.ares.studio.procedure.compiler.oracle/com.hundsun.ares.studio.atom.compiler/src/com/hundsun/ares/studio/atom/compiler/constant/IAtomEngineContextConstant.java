/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.constant;

/**
 * @author lvgao
 *
 */
public interface IAtomEngineContextConstant {

	public static final String Function_Macro_Service = "函数宏服务";
	
	public static final String Procedure_Macro_Service = "存储过程宏服务";
	
	public static final String Structure_Service = "对象服务";
	
	//public static final String Statistic_Provider = "引用信息";
	
	public static final String UserMacro_Service = "用户自定义宏服务";
	
	/////////////////////////////函数和服务////////////////////////////
	
	/**
	 * 上下文中存取当前项目
	 */
	public static final String Aresproject = "当前项目";
	
	/**
	 * 用于上下文中存取相应的宏过滤器。
	 * 如：那些宏是否可以在函数中用，那些宏是否可以在服务当中用
	 */
	public static final String MACRO_FILTER = "宏过滤器";
	
	
	public static final String InternalSTDHelper = "是否是临时标准字段帮助类";
	
	/**
	 * 用于在上下文中存放资源模型
	 * 主要是为了预览设计，引文预览时传入的模型是未保存的
	 * 非预览时不用存入值
	 */
	public static final String ResourceModel = "资源模型";
	
	/**
	 * 以前为了方便使用jet而设置的上下文中放入参数
	 */
	public static final String Macro_args = "宏参数";
	
	public static final String ENGINE_EXCEPTION = "生成代码时产生的错误";
	
	public static final String ERROR_LOG_PATH = "生成代码文件时生成的log文件路径";
	
	public static final String ERROR_LOG_FILENAME = "\\error.log";
	
	/**
	 * 上下文中存取伪代码中出现过的变量，也就是@开头的变量
	 */
	public static final String PseudoCode_Para_LIST = "伪代码使用变量列表";
	
	/////////////////////////////函数和服务全局属性////////////////////////////
	/**
	 * 为了避免参数重复定义而设计的此参数类
	 * 默认实现中记录了参数类型和参数名称，避免重复定义
	 */
	public static final String PARAM_DEFINE_HELPER = "参数定义帮助类";
	
	/**
	 * 由于蓝图中需要存入大量的全局上下文，当然这些属性也可以存到上下文中，但是这样
	 * 上下文就相对复杂了，而且这些属性可能有顺序等特殊要求，所以专门设计了一个全局属性接口
	 * 以下以ATTR_开头的都是存入"蓝图全局属性帮助类"中的
	 */
	public static final String SKELETON_ATTRIBUTE_HELPER = "蓝图全局属性帮助类";
	
	/**
	 * 	不能使用常用规则初始化的变量我们定义为复杂变量
	 *     会出现在生成代码的
	 *     “//宏使用的复杂参数初始化”
	 *     后面
	 */
	public static final String ATTR_INITIAL_PARA = "伪代码中使用的复杂变量定义";
	
	//记录接口
	public static final String ATTR_USED_RECORD_INTERFACE = "使用的对象接口列表";
	//记录结构指针
	public static final String ATTR_USED_RECORD_OBJECT = "使用的对象结构列表";
	
	//加锁的对象
	public static final String ATTR_USED_RECORD_OBJECT_NEED_LOCK = "需要加锁的对象";
	
	//用于调用函数时默认传参数
	public static final String ATTR_USED_RECORD_TYPE = "使用的对象类型列表";
	
	/**
	 * 函数成功时要添加的代码，
	 * 所有成功代码都添加到相应固定位置
	 */
	public static final String ATTR_FUNCTION_SUCCESS = "函数成功";
	
	/**
	 * 函数失败时要添加的代码，
	 * 所有失败代码都添加到相应固定位置
	 */
	public static final String ATTR_FUNCTION_FAIL = "函数失败";
	
	/**
	 * 不管函数成功或失败都要执行的代码
	 */
	public static final String ATTR_FUNCTION_END = "函数结束-包括成功失败";
	
	public static final String ATTR_DATABASE_MACRO = "使用的数据库宏列表";
	
	public static final String ATTR_PROC_VARIABLE_LIST = "PRO*C变量列表";
	
	public static final String ATTR_FUNC_CALL = "函数调用列表";
	/*
	 * IF2ResultSet是基类，IF2UnPacker是扩展类
	 * */
	public static final String ATTR_FUNC_RESULTSET = "函数与结果集列表";//函数调用的结果集是不需要释放的，由于IF2UnPacker结果集与IF2ResultSet结果集都是可以直接打包输出的，故要用一个总列表记录
	
	public static final String ATTR_RESULTSET_LIST = "结果集列表";//这里的结果集必须全部释放
	
	public static final String ATTR_GETLAST_RESULTSET = "取得就近的结果集Id";//取得就近的结果集Id，凡是可以用lpResultSet->取就近结果集的，都需要加到这个列表中
	
	public static final String ATTR_PROCEDURE_CALL_NOTRSRETURN = "非返回结果集过程调用列表";
	
	public static final String ATTR_PROCEDURE_CALL_RSRETURN = "返回结果集过程调用列表";
	
	public static final String ATTR_PROC_MACRO = "使用的PRO*C宏列表";
	
	public static final String ATTR_IN_OUT_PARAM_LIST = "输入输出参数列表";
	
	public static final String ATTR_PACK_VALIABLESET = "pack变量列表";
	
	public static final String ATTR_CURSOR_LIST = "游标列表";
	
	public static final String ATTR_CURSOR_PROC_VARIABLE_LIST = "PRO*C游标变量列表";
	
	public static final String ATTR_IN_OBJECT_INIT_VARIABLE_LIST = "需要初始化的输入对象变量";//从解包器中初始化对象(输入)
	
	public static final String ATTR_IN_OBJECT_NOINIT_VARIABLE_LIST = "不需要初始化的输入对象变量";//定义后直接赋值为NULL(输入)
	
	public static final String ATTR_OUT_OBJECT_INIT_VARIABLE_LIST = "需要初始化的输出对象变量";//从解包器中初始化对象(输出)需要初始化的
	
	public static final String ATTR_OUT_OBJECT_NOINIT_VARIABLE_LIST = "不需要初始化的输出对象变量";//要需要用的情况下才需要初始化的输入对象
	public static final String ATTR_DATABASE_CONN_VARIABLE_LIST = "声明的数据库连接列表";//声明的数据库连接列表
	
	public static final String ATTR_ACK_ADDVALUE_HANDWORK_LIST = "手工打包头列表";
	
	
	public static final String ATTR_LP_PACK_SERVICE_LIST = " lpPackService";// lpPackService声明列表
	
	/**
	 * 是否提前返回结果集,宏处理的时候遇到包头包体，就需要设置成true
	 * 
	 */
	public static final String IS_ALREADY_RETURN_RESULTSET = "是否提前返回结果集";
	
}
