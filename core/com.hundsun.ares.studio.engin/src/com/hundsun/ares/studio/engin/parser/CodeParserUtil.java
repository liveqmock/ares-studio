/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.engin.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qinyuan
 *
 */
public class CodeParserUtil {

	/**匹配常量1**/
	private static Pattern CONST_PATTERN = Pattern.compile("(([^<]|^)<[\\w\\d_]+>([^\\[]|$))", Pattern.MULTILINE);
	/**匹配常量2**/
	private static Pattern CONST_PATTERN2 = Pattern.compile("<[\\w\\d_]+>");
	/**
	 * 查找所有常量标记
	 * 
	 * @param strText
	 * @return
	 */
	public static List<String> findConstAll(String strText) {
		
		List<String> ret = new ArrayList<String>();
		Matcher m = CONST_PATTERN.matcher(strText);
		int index = 0;
		while ( m.find(index) ) {
			String group = m.group();
			Matcher m2 = CONST_PATTERN2.matcher(group);
			if (m2.find()) {
				ret.add(m2.group());
			}
			index = m.start() + m2.end() - 1;
		}
		
		return ret;
	}
	
	/**
	 * 查找字符串中所有的标准字段(以@开头的字段)
	 * @param str
	 * @return
	 */
	public static List<String> findStandardField(String str) {
		List<String> stdFlds = new ArrayList<String>();
		Pattern p = Pattern.compile("@[\\w\\d_\\.]+");
		Matcher m = p.matcher(str);
		Pattern p1 = Pattern.compile("[\\w\\d_'\"]");//增加引号判断，引号中直接作为字符串处理
		while (m.find()) {
			//2011年11月29日19:47:11 二部要求  如果@前为字母数字或下划线则不进行标准字段解析
			// 此处借鉴证券二部经验
			int mStart = m.start();
			
			if(mStart == 0 || (mStart > 0 && !p1.matcher(str.substring(mStart-1, mStart)).matches())) {
				stdFlds.add(m.group().substring(1));
			}
		}
		return stdFlds;
	}
	
	/**
	 * 查找字符串中所有的标准字段(以@开头的字段)
	 * @param str
	 * @return
	 */
	public static boolean findResultSetStr(String str) {
		Pattern p = Pattern.compile("lpResultSet->");
		Matcher m = p.matcher(str);
		while (m.find()) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args){
		boolean b = findResultSetStr(" while (!lpResultSet->IsEOF())");
		System.out.println(b);
	}
}
