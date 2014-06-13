/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.skeleton.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hundsun.ares.studio.engin.parser.PseudoCodeParser;
import com.hundsun.ares.studio.engin.token.ICodeToken;

/**
 * @author lvgao
 *
 */
public class ParamReplaceUtil {

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
		Iterator<ICodeToken> codes = PseudoCodeParser.parseEx(content, PseudoCodeParser.CPP_COMMENT_PATTERN, PseudoCodeParser.CPP_STRING_PATTERN, new HashMap<Object, Object>() );
		while(codes.hasNext()) {
			ICodeToken code = codes.next();
			String c = code.getContent();
			if(code.getType() != ICodeToken.COMMENT /*&&
					code.getType() != ICodeToken.STRING*/) {//非注释才需要替换
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
	 * 对象变量处理
	 * @param prefix
	 * @param content
	 * @param objects
	 * @return
	 */
	public static String handleObjectParams(String prefix,String content,List<String> objects){
		StringBuffer ret = new StringBuffer();
	Iterator<ICodeToken> codes = PseudoCodeParser.parseEx(content, PseudoCodeParser.CPP_COMMENT_PATTERN, PseudoCodeParser.CPP_STRING_PATTERN, new HashMap<Object, Object>() );
	while(codes.hasNext()) {
		ICodeToken code = codes.next();
		String c = code.getContent();
		if(code.getType() != ICodeToken.COMMENT /*&&
				code.getType() != ICodeToken.STRING*/) {//非注释才需要替换
			//对原来代码做兼容处理
			for(String para:objects){
				String rStr = String.format("%s%s%s", prefix, "v_",para+"ResultSet");
				c = replaceVariable(c,para+"ResultSet", rStr);
			}
			for(String para:objects){
				String rStr = String.format("%s%s%s", prefix, "v_",para+"ResultSet");
				c = replaceVariable(c,para, rStr);
			}
		}
		ret.append(c);
	}
	return ret.toString();
	}
	
	
	/**
	 * 对特殊变量处理
	 * @param prefix
	 * @param content
	 * @param specialsParams
	 * @return
	 */
	public static String handleSpecialParams(String prefix,String content,List<String>specialsParams){
		StringBuffer ret = new StringBuffer();
	Iterator<ICodeToken> codes = PseudoCodeParser.parseEx(content, PseudoCodeParser.CPP_COMMENT_PATTERN, PseudoCodeParser.CPP_STRING_PATTERN, new HashMap<Object, Object>() );
	while(codes.hasNext()) {
		ICodeToken code = codes.next();
		String c = code.getContent();
		if(code.getType() != ICodeToken.COMMENT /*&&
				code.getType() != ICodeToken.STRING*/) {//非注释才需要替换
			//对原来代码做兼容处理
			for(String para:specialsParams){
				String rStr = String.format("%s%s%s", prefix, "",para);
				c = replaceVariable(c,para, rStr);
			}
			
		}
		ret.append(c);
	}
	return ret.toString();
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
		Pattern p = Pattern.compile("@" + varName+"[^a-zA-Z\\d_@=(){};,\\[\\]\\+\\-\\>\\<]*");//这里需要全字匹配，如替换@user_id时，只替换@user_id，而不多替换@user_id1
		//@result_num=@result_num + 1;这种情况也需支持，由于@变量后，可能有=(,+-等符号，这些字符也需去除
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
	public static String replaceContentWithNotString(String content, String targetStr, String replaceStr){
		StringBuffer ret = new StringBuffer();
		Iterator<ICodeToken> codes = PseudoCodeParser.parseEx(content, PseudoCodeParser.CPP_COMMENT_PATTERN, PseudoCodeParser.CPP_STRING_PATTERN, new HashMap<Object, Object>() );
		while(codes.hasNext()) {
			ICodeToken code = codes.next();
			String c = code.getContent();
			if(code.getType() != ICodeToken.COMMENT/* &&
					code.getType() != ICodeToken.STRING*/) {//非注释才需要替换
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
	public static List<String> findConstAll(String strText) {
		
	List<String> ret = new ArrayList<String>();
		
		Iterator<ICodeToken> codes = PseudoCodeParser.parseEx(strText, PseudoCodeParser.CPP_COMMENT_PATTERN, PseudoCodeParser.CPP_STRING_PATTERN, new HashMap<Object, Object>() );
		while(codes.hasNext()) {
			ICodeToken code = codes.next();
			String c = code.getContent();
			if(code.getType() != ICodeToken.COMMENT /*&&
					code.getType() != ICodeToken.STRING*/) {//非注释需要添加
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
