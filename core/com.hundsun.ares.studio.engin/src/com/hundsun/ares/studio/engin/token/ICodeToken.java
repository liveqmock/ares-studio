package com.hundsun.ares.studio.engin.token;

import java.util.Map;

public interface ICodeToken {

	/**注释*/
	public static final int COMMENT = 0;
	/**宏*/
	public static final int MACRO = 1;
	/**普通代码文本*/
	public static final int CODE_TEXT = 2;
	/**伪代码*/
	public static final int PseudoCode = 3;
	/**字符串*/
	public static final int STRING = 4;
	
	/**换行*/
	public static final String NEWLINE = "\r\n";
	/**空字符串*/
	public static final String BlackString = "";
	
	/**
	 * 获取token内容
	 * @return
	 */
	public String getContent();
	
	
	/**
	 * 获取标记类型
	 * @return
	 */
	public int getType();
	
	/**
	 * 获取写入文件的内容
	 * @param context
	 * @return
	 */
	public String genCode(Map<Object, Object> context)throws Exception;
}
