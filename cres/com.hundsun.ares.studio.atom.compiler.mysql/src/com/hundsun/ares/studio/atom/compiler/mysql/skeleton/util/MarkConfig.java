/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util;

/**
 * 标记配置
 * 
 * @author zhuyf
 * 
 */
public class MarkConfig {

	public static final String MARK_QUOTING = "'";

	/**
	 * 逗号标记
	 */
	public static final String MARK_COMMA = ",";

	/**
	 * 换行标记
	 */
	public static final String MARK_NEWLINE = "\n";

	/**
	 * 下划线标记
	 */
	public static final String MARK_UNDERLINE = "_";

	/**
	 * 游标后缀标记
	 */
	public static final String MARK_SUFFIX_CURSOR = "cur";

	/**
	 * 分号标记
	 */
	public static final String MARK_SEMICOLON = ";";

	/**
	 * 冒号标记
	 */
	public static final String MARK_COLON = ":";

	/**
	 * 等号标记
	 */
	public static final String MARK_EQUAL = "=";

	/**
	 * 空格标记
	 */
	public static final String MARK_BLANK = " ";

	/**
	 * 单引号空格标记
	 */
	public static final String MARK_SINGLEBLANK = "' '";

	/**
	 * 输出输出参数的前缀
	 */
	public static final String MARK_PREFIX_PARAMETER = "p";

	/**
	 * 内部变量的前缀
	 */
	public static final String MARK_PREFIX_VARIABLE = "v";

	/**
	 * 宏起始标志
	 */
	public static final String MARK_MACROBEGIN = "[";

	/**
	 * 宏结束标志
	 */
	public static final String MARK_MACROEND = "]";

	/**
	 * 左括号标记
	 */
	public static final String MARK_LEFTSIGN = "(";

	/**
	 * 右括号标记
	 */
	public static final String MARK_RIGHTSIGN = ")";
	/**
	 * 左大括号标记
	 */
	public static final String MARK_LEFTBIGSIGN = "{";

	/**
	 * 右大括号标记
	 */
	public static final String MARK_RIGHTBIGSIGN = "}";

	/**
	 * values关键字
	 */
	public static final String MARK_VALUE = "values";

	/**
	 * nvl关键字
	 */
	public static final String MARK_NVL = "nvl";
	/**
	 * 系统宏由系统产生的标志
	 */
	public static final String MARK_SYSTEMGENERATE = "systemGenerate=true";

	/**
	 * lpResultSet的名称
	 */
	public static final String MARK_LPRESULTSET_NAME = "lpResultSet";

	/**
	 * error_no变量名称
	 */
	public static final String VARIABLE_ERROR_NO = "error_no";

	/**
	 * error_info变量名称
	 */
	public static final String VARIABLE_ERROR_INFO = "error_info";

	/**
	 * error_id变量名称
	 */
	public static final String VARIABLE_ERROR_ID = "error_id";

	/**
	 * 空字符串标记
	 */
	public static final String MARK_EMPTYSTRING = "";

	/**
	 * true标记
	 */
	public static final String MARK_TRUE = "true";

	/**
	 * false标记
	 */
	public static final String MARK_FALSE = "false";

	/**
	 * “@”标记
	 */
	public static final String MARK_AT = "@";

	/**
	 * 双引号标记
	 */
	public static final String MARK_QUOTATION = "\"";

	/**
	 * 0标记
	 */
	public static final String MARK_ZERO = "0";

	/**
	 * 0.0标记
	 */
	public static final String MARK_ZERO_FLOAT = "0.0";

	/**
	 * error_info的静态文本
	 */
	public static final String MARK_ERROR_INFO = "error_info";

	/**
	 * IO标记
	 */
	public static final String MARK_IOFLAG = "IO";

	/**
	 * 字符串常量：lpResultSet->Next()
	 */
	public static final String MARK_RSNEXT = "lpResultSet->Next()";

	/**
	 * 字符串常量：lpResultSet->isEOF()
	 */
	public static final String MARK_RSEOF = "lpResultSet->IsEOF()";

	/**
	 * 字符串常量：while
	 */
	public static final String MARK_WHILE = "while";

	/**
	 * 参数的类型值
	 */
	public static final int TYPE_PARAM = 0;

	/**
	 * 内部变量的类型值
	 */
	public static final int TYPE_VARIABLE = 1;

	/**
	 * proc声明语句中，输入参数初始语句的标志
	 */
	public static final String MARK_LPINUNPACKER = "lpInUnPacker->";

	/**
	 * 接口标志M
	 */
	public static final String MARK_IFLAG_M = "M";

	/**
	 * 接口标志K
	 */
	public static final String MARK_IFLAG_K = "K";

	/**
	 * 接口标志E
	 */
	public static final String MARK_IFLAG_E = "E";

	/**
	 * 第一列标志M
	 */
	public static final String MARK_FFLAG_M = "M";

	public static final String MARK_V2 = "v2";

	public static final String MARK_GANG = "/";

}
