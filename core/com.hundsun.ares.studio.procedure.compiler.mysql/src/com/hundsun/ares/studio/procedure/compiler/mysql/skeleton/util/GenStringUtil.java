/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.mysql.skeleton.util;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liaogc
 *
 */
public class GenStringUtil {
	
	static private Pattern P_SELECT_OR_FROM = Pattern.compile("\\W(select|from)\\W", Pattern.CASE_INSENSITIVE);
		
		/**
		 * 提取一段select sql中的select内容
		 * @param sqlStr
		 * @return
		 */
		public static String getMainSelectContent(String sqlStr)  {
			try {
				sqlStr = " " + sqlStr+ " "; // 即使开头就是select也能被正则匹配到
				int begin = 0, end = 0;
				int level = 0; // 表示目前在第几层的select...from
				Matcher m = P_SELECT_OR_FROM.matcher(sqlStr);
				int start = 0;
				while (m.find(start)) {
					if (m.group().toLowerCase().indexOf("select") != -1) { // 匹配了select
						level++;
						if (level == 1) {
							begin = m.end() - 1;
						}
					} else { // 匹配了from
						level--;
						if (level == 0) {
							end = m.start() + 1;
							break;
						}
					}
					
					start = m.end();
				}
				
				if (end > begin) {
					return sqlStr.substring(begin, end);
				}
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "";
		}
		
		
		// 取得select和from中间的变量部分
		public static String getStrBetweenSelectAndFrom(String str) throws Exception{
			String returnStr = "";
			Stack<String> stack = new Stack<String>();
			String selectStr = "select";
			String tempStr = "";
			boolean isGetStr = false;
			
			Pattern p = Pattern.compile("(\\s+from\\s+)");
			Matcher m = p.matcher(str);
			if (m.find()) {
				int selectIndex = str.indexOf("(select ");
				int fromIndex = str.indexOf(" from ");
				
				if(selectIndex < 0 || selectIndex > fromIndex) {//select语句不存在，或者select语句在第一个from之后
					returnStr = str.substring(0, fromIndex);
				} else {
					stack.push(selectStr);
					
					tempStr = str.substring(selectIndex+ 8, str.length());//截取第一个子查询select之后的字符
					selectIndex = tempStr.indexOf("(select ");
					fromIndex = tempStr.indexOf(" from ");
					
					while(selectIndex >= 0 || !stack.isEmpty()) {
						if(0 <= selectIndex && selectIndex < fromIndex) {//余下字符串还有子查询
							stack.push(selectStr);
							
							tempStr = tempStr.substring(selectIndex+ 8, tempStr.length());
						} else {
							if(!stack.isEmpty()) {
								stack.pop();
								
								tempStr = tempStr.substring(fromIndex+ 6, tempStr.length());
							} else {
								tempStr = tempStr.substring(fromIndex+6, tempStr.length());
								returnStr = str.substring(0, str.indexOf(tempStr));
								isGetStr = true;
							}
						}
						selectIndex = tempStr.indexOf("(select ");
						fromIndex = tempStr.indexOf(" from ");
					}
					
					if(selectIndex < 0) {
//						tempStr = tempStr.substring(fromIndex+ 6, tempStr.length());
						fromIndex = tempStr.indexOf(" from ");
						if(!isGetStr) {
							tempStr = tempStr.substring(fromIndex, tempStr.length());
							returnStr = str.substring(0, str.indexOf(tempStr));
						}
					}
				}
			}
			return returnStr;
		}

		// 取得select和from中间的变量部分
		public static String getStrBetSelectAndFrom(String str) {
			Pattern p = Pattern.compile("(\\s+from\\s+)");
			Matcher m = p.matcher(str);
			if (m.find()) {
				int lastFromIndex = str.lastIndexOf(" from ");//应该截取子查询
				if(lastFromIndex != -1) {
					int lastWhereIndex = str.lastIndexOf(" where ");
					String backtemp = str.substring(lastFromIndex,lastWhereIndex);//最后一个from和最后一个where之间的字符串
					String beforetemp = str.substring(0, lastFromIndex);
					while(backtemp.indexOf(" where ") != -1) {//最后一个from属于子查询中字段，应剔除
						lastFromIndex = beforetemp.lastIndexOf(" from ");//剔除子查询，退回到倒数第二个from
		//				lastWhereIndex = backtemp.lastIndexOf(" where ");
						backtemp = beforetemp.substring(lastFromIndex, beforetemp.length());
						beforetemp = beforetemp.substring(0, lastFromIndex);
					}
					lastFromIndex = str.indexOf(backtemp);
					str = str.substring(0, lastFromIndex).trim();
				} else {
					str = str.substring(0, m.start()).trim();
				}
			}
			return str;
		}
}
