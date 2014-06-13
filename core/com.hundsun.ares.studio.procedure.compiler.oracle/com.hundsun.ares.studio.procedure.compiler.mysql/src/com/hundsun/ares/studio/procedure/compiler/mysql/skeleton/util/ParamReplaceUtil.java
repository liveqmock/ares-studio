/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.mysql.skeleton.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hundsun.ares.studio.engin.parser.PseudoCodeParser;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;

/**
 * @author lvgao
 *
 */
public class ParamReplaceUtil {
	
	/**
	 * 处理变量替换
	 * @param prefix        前缀，proc变量加:,其它情况传""
	 * @param codes			解析后的宏
	 * @param params
	 * @param context
	 * @return
	 */
	public static String handleParams(String prefix,Iterator<ICodeToken> codes,String[] params,Set<String> inoutParamList){
		
		StringBuffer ret = new StringBuffer();
		while(codes.hasNext()) {
			ICodeToken code = codes.next();
			String c = code.getContent();
			if(code.getType() != ICodeToken.COMMENT &&
					code.getType() != ICodeToken.STRING) {//非注释非字符串才需要替换
				for(String para:params){
					if(inoutParamList.contains(para)){
						String rStr = String.format("%s%s%s", prefix, "p_",para);
						c = replaceVariable(c,para, rStr);
					}else{
						String rStr = String.format("%s%s%s", prefix, "v_",para);
						c = replaceVariable(c,para, rStr);
					}
				}
			}
			ret.append(c);
		}
		return ret.toString();
	}

	/**
	 * 处理变量替换
	 * @param prefix        前缀，proc变量加:,其它情况传""
	 * @param content
	 * @param params
	 * @param context
	 * @return
	 */
	public static String handleParams(String prefix,String content,String[] params,Set<String> inoutParamList){
		
		StringBuffer ret = new StringBuffer();
		//用proc解析一次
		Iterator<ICodeToken> codes = PseudoCodeParser.parseEx(content, PseudoCodeParser.PROC_COMMENT_PATTERN, PseudoCodeParser.PROC_STRING_PATTERN, new HashMap<Object, Object>() );
		while(codes.hasNext()) {
			ICodeToken code = codes.next();
			String c = code.getContent();
			if(code.getType() != ICodeToken.COMMENT &&
					code.getType() != ICodeToken.STRING) {//非注释非字符串才需要替换
				for(String para:params){
					if(inoutParamList.contains(para)){
						String rStr = String.format("%s%s%s", prefix, "p_",para);
						c = replaceVariable(c,para, rStr);
					}else{
						String rStr = String.format("%s%s%s", prefix, "v_",para);
						c = replaceVariable(c,para, rStr);
					}
				}
			}
			ret.append(c);
		}
		return ret.toString();
//		for(String para:params){
//			if(inoutParamList.contains(para)){
//				String rStr = String.format("%s%s%s", prefix, "p_",para);
//				content = replaceVariable(content,para, rStr);
//			}else{
//				String rStr = String.format("%s%s%s", prefix, "v_",para);
//				content = replaceVariable(content,para, rStr);
//			}
//		}
//		return content;
	}
	
	/**
	 * 变量替换
	 * @param content
	 * @param params
	 * @param context
	 * @return
	 */
	public static String handleParams(String content,String[] params,Set<String> inoutParamList){
		return handleParams("", content, params, inoutParamList);
	}

	/**
	 * 替换标准字段
	 * @param code
	 * @param varName
	 * @param replaceName
	 * @return
	 */
	public static String replaceVariable(String code, String varName, String replaceName) {
		Pattern p = Pattern.compile("@" + varName+"[^\\@w\\d_]+");
		StringBuffer sbRet = new StringBuffer();
		Matcher m = p.matcher(code);
		int lastPos = 0;
		while (m.find()) {
				if (m.start() > lastPos) {
					sbRet.append(code.substring(lastPos, m.start()));
				}
				sbRet.append(m.group().replaceAll("@" + varName, replaceName));
				lastPos = m.end();
			
		}
		if (lastPos < code.length()) {
			sbRet.append(code.substring(lastPos));
		}
		
		return sbRet.toString();
	}
	
	/**
	 * 替换常量
	 * @param code
	 * @param varName
	 * @param replaceName
	 * @return
	 */
	public static String replaceConstant(String code, String varName, String replaceName) {
		Pattern p = Pattern.compile("@" + varName + "[^\\w\\d_]");
		StringBuffer sbRet = new StringBuffer();
		Matcher m = p.matcher(code);
		int lastPos = 0;
		while (m.find()) {
			if (m.start() > lastPos) {
				sbRet.append(code.substring(lastPos, m.start()));
			}
			sbRet.append(m.group().replaceAll("@" + varName, replaceName));
			lastPos = m.end();
		}
		if (lastPos < code.length()) {
			sbRet.append(code.substring(lastPos));
		}
		
		return sbRet.toString();
	}
	
	/**
	 * 将去除注释后的代码中一个字符串替换成另外一个字符串
	 * @param content 代码
	 * @param targetStr 目标字符串
	 * @param replaceStr 替换字符串
	 * @return
	 */
	public static String replaceContentWithNotString(String content, String targetStr, String replaceStr,Map<Object, Object> context){
		StringBuffer ret = new StringBuffer();
		//用proc解析一次
		Iterator<ICodeToken> codes = PseudoCodeParser.parseEx(content, PseudoCodeParser.PROC_COMMENT_PATTERN, PseudoCodeParser.PROC_STRING_PATTERN, context);
		while(codes.hasNext()) {
			ICodeToken code = codes.next();
			String c = code.getContent();
			if(code.getType() != ICodeToken.COMMENT &&
					code.getType() != ICodeToken.STRING) {//非注释非字符串才需要替换
				c = c.replaceAll(targetStr, replaceStr);
			}
			ret.append(c);
		}
		return ret.toString();
	}
	
	
	private static Pattern CONST_PATTERN = Pattern.compile("(([^<]|^)<[\\w\\d_]+>([^\\[]|$))", Pattern.MULTILINE);
	private static Pattern CONST_PATTERN2 = Pattern.compile("<[\\w\\d_]+>");
	
	/**
	 * 查找所有常量标记
	 * 
	 * @param strText
	 * @return
	 */
	public static List<String> findConstAll(String strText,Map<Object, Object> context) {
		List<String> ret = new ArrayList<String>();
		
		//用proc解析一次
		Iterator<ICodeToken> codes = PseudoCodeParser.parseEx(strText, PseudoCodeParser.PROC_COMMENT_PATTERN, PseudoCodeParser.PROC_STRING_PATTERN, context);
		while(codes.hasNext()) {
			ICodeToken code = codes.next();
			String c = code.getContent();
			if(code.getType() != ICodeToken.COMMENT &&
					code.getType() != ICodeToken.STRING) {//非注释非字符串才需要添加
				Matcher m = CONST_PATTERN.matcher(c);
				int index = 0;
				while ( m.find(index) ) {
					String group = m.group();
					Matcher m2 = CONST_PATTERN2.matcher(group);
					if (m2.find()) {
						ret.add(m2.group());
					}
					index = m.start() + m2.end() - 1;
				}
			}
		}
		
		return ret;
	}
	
	/**
	 * 忽略带有给定标志位的字段.
	 * 如果table是以his_、fil、r_、rl_开头的，还是会返回所有字段
	 * @param flag 标志位
	 * @return 忽略后的
	 */
	public static TableColumn[] getFieldsWithoutFlag(TableResourceData table , String flag,String tableName) {
		if(tableName.indexOf(".") >= 0){
			tableName = tableName.substring(tableName.lastIndexOf(".")+1);
		}
		if(!tableName.isEmpty()){
			tableName = tableName.trim();
			if(tableName.startsWith("his_")||
					tableName.startsWith("fil_")||
					tableName.startsWith("r_")||
					tableName.startsWith("rl_")){
				return table.getColumns().toArray(new TableColumn[0]);
			}
		}
		List<TableColumn> filtered = new ArrayList<TableColumn>();
		char[] flags = flag.toCharArray();
		;
		for (TableColumn field : table.getColumns()) {
			boolean contains = false;
			for (int ch : flags) {
				if (field.getMark() != null) {
					if (field.getMark().indexOf(ch) != -1) {
						contains = true;
						break;
					}
				}
			}
			if (!contains) {
				filtered.add(field);
			}
		}
		return filtered.toArray(new TableColumn[filtered.size()]);
	}
	
	public static StringBuffer formatInsert(String str){
		int haveAt = 0;
		if(str.indexOf("@") != -1)
			haveAt = 1;
		StringBuffer buffer = new StringBuffer(str);
		for(int i = 0 ;20-buffer.length()-haveAt>0;i++){
			buffer.append(" ");
		}
		return buffer;
	 }
	
	public static StringBuffer formatInsert(String str,int maxLength){
		int haveAt = 0;
		if(str.indexOf("@") != -1)
			haveAt = 1;
		StringBuffer buffer = new StringBuffer(str);
		int length = buffer.length();
		if(maxLength>length){
			for(int i = 0 ;i<(maxLength-length-haveAt);i++){
				buffer.append(" ");
			}
		}
		
		return buffer;
	 }
	
}
