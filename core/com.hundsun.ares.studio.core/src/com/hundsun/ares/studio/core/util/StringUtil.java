/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 字符串相关工具类
 * @author sundl
 */
public class StringUtil {

	/** 空串 
	 * @Deprecated 使用apache commons包里的util代替
	*/
	public static final String EMPTY_STR = "";
	/**十六进制字符集*/
	private static String hexString="0123456789ABCDEF";
	
	/**分隔符字符*/
	public static final int SEPRATOR_ASCII_LEGAL=0;
	public static final int SEPRATOR_ASCII_ILLEGAL=1;
	public static final int SEPRATOR_LENGTH_RUN_OVER=2;

	/**
	 * 如果给定的字符串为null，将null值转换为长度为0的字符串;
	 * 
	 * @param s
	 *            需要转换的字符串
	 * @return 如果不为null，返回字符串本身；否则返回长度为0的字符串。
	 */
	public static String convertString(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}

	/**
	 * 检查字符串是否为空。
	 * 
	 * @param target
	 * @return 若字符串对象为null或其内容为空，或只有空白字符，则返回true。
	 * @deprecated 使用org.apache.common.lang包里提供的工具类替代
	 */
	public static boolean isEmpty(String target) {
		if (null == target || target.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 比较两个对象的字符串形式是否相等。
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static boolean equalsByString(Object obj1, Object obj2) {
		String str1 = String.valueOf(obj1);
		String str2 = String.valueOf(obj2);
		return str1.equals(str2);
	}

	/**
	 * 给定的字符串中删除给定的后缀。
	 * 
	 * @param s
	 *            字符串
	 * @param suffix
	 *            后缀
	 * @return 处理后的字符串。
	 */
	public static String removeSuffix(String s, String suffix) {
		if (s == null) {
			return null;
		}
		if (suffix == null) {
			return s;
		}

		if (!s.endsWith(suffix)) {
			return s;
		}

		StringBuffer sb = new StringBuffer(s);
		int index = sb.lastIndexOf(suffix);
		sb.replace(index, sb.length(), EMPTY_STR); //$NON-NLS-1$
		return sb.toString();
	}

	/**
	 * 在给定的字符串中去除给定的前缀。
	 * 
	 * @param s
	 *            需要处理的字符串
	 * @param prefix
	 *            前缀
	 * @return 去掉前缀后的字符串
	 */
	public static String removePrefix(String s, String prefix) {
		if (s == null) {
			return null;
		}
		if (prefix == null) {
			return s;
		}

		if (!s.startsWith(prefix)) {
			return s;
		}

		return s.replaceFirst(prefix, EMPTY_STR); //$NON-NLS-1$
	}

	/**
	 * 去掉给定分隔符之前（含分隔符）的字串，并返回结果字符串。
	 * 
	 * @param str
	 *            给定的字符串
	 * @param delimeter
	 *            分隔符
	 * @return
	 * @deprecated use StringUtils.substringAfterLast instead
	 */
	public static String removePrefixBefore(String str, String delimeter) {
		return StringUtils.substringAfterLast(str, delimeter);
	}

	/**
	 * 返回指定的分隔符之前的子串。
	 * 
	 * @param str
	 *            字符串
	 * @param delimeter
	 *            分隔符
	 * @return 指定的分隔符之前的子串；如果给定的字符串不含分隔符，返回长度为0的字符串。
	 * @deprecated 使用org.apache.common.lang包里提供的工具类替代
	 */
	public static String getPrefix(String str, String delimeter) {

		if (str == null)
			return null;
		if (delimeter == null)
			return str;

		int index = str.indexOf(delimeter);
		if (index >= 0) {
			return str.substring(0, index);
		} else {
			return EMPTY_STR;
		}

	}

	/**
	 * 取文件名（去掉扩展名）
	 * @param fullFileName
	 * @return
	 * @deprecated 使用org.apache.common.lang包里提供的工具类替代
	 */
	public static String getFileNameWithoutExt(String fullFileName) {
		int dot;
		if ((dot = fullFileName.indexOf(".")) != -1) {
			return fullFileName.substring(0, dot);
		}
		return fullFileName;
	}
	
	/**
	 * 检查字符串是否为位数在8为以内的数字字符串，包括负数。
	 * 
	 * @param target
	 * @return 若字符串对象为数字返回TRUE，否则返回FALSE。为空时判断为FALSE
	 * @deprecated 使用org.apache.common.lang包里提供的工具类替代
	 */
	public static boolean isNumber(String target) {
		if(null ==target){
			return false;
		}
		Pattern pattern = Pattern.compile("-?\\d{1,8}");
		Matcher matcher = pattern.matcher(target);
		return matcher.matches();
	}
	
	/**
	 * 检查字符串是否为位数在8为以内的正整数。
	 * 
	 * @param target
	 * @return 若字符串对象为数字返回TRUE，否则返回FALSE。为空时判断为FalSE
	 * @deprecated 使用org.apache.common.lang包里提供的工具类替代
	 */
	public static boolean isPositiveNumber(String target) {
		if(null ==target){
			return false;
		}
		Pattern pattern = Pattern.compile("\\d{1,8}");
		Matcher matcher = pattern.matcher(target);
		return matcher.matches();
	}

	
	/**
	 * 检查字符串是否为数字。
	 * 
	 * @param target
	 * @return 若字符串对象为数字返回TRUE，否则返回FALSE。
	 */
	public static boolean isXmlNodeSn(String target) {
		// 限制输入9位正整数或者n
		Pattern pattern = Pattern.compile("\\d{0,9}|n");
		Matcher matcher = pattern.matcher(target);
		return matcher.matches();
	}

	/**
	 * 检查字符串是否为符合8583域编号的数字。 最大为128 最小为 0
	 * 
	 * @param target
	 * @return 如果字符为空“”返回TRUE。若字符串对象为数字返回TRUE，否则返回FALSE。
	 */
	public static boolean is8583Number(String target) {
		if (target.equals("")) {
			return false;
		}
		try {
			if (Integer.valueOf(target) >= 0 && Integer.valueOf(target) <= 128)
				return true;
		} catch (Exception ex) {
			return false;
		}
		return false;
	}

	/**
	 * 检查字符串是否为无特殊字符的字符串。
	 * 
	 * @param target
	 * @return 若字符串无特殊字符则返回TRUE，不合法返回FALSE。
	 */
	public static boolean isNormal(String target) {
		Pattern pattern = Pattern.compile("[0-9a-zA-Z][0-9a-zA-Z]*");
		Matcher matcher = pattern.matcher(target);
		return matcher.matches();
	}

	/**
	 * aselect不是数字返回0，否则返回相应数字
	 * 
	 * @param aselect
	 * @return
	 */
	public static int parseInt(String aselect) {
		if (!StringUtil.isNumber(aselect)) {
			return 0;
		} else {
			return Integer.parseInt(aselect);
		}
	}
	
	public static String toHEX(String asciitxt) {
		// 根据默认编码获取字节数组
		byte[] bytes = asciitxt.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return sb.toString();
	}
	
	public static String toASCII(String hextxt) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(hextxt.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < hextxt.length(); i += 2){
			baos.write((hexString.indexOf(hextxt.charAt(i)) << 4 | hexString.indexOf(hextxt.charAt(i + 1))));
		}
		return new String(baos.toByteArray());
	}
	
	/**
	 * 将字符串中的格式字符转换成可视字符
	 * 
	 * @param str
	 * @return
	 */
	public static String toViewText(String str) {
		StringBuffer sb = new StringBuffer();
		char[] chars = str.toCharArray();
		for (char c : chars) {
			switch (c) {
			case '\n':
				sb.append("\\n");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '\r':
				sb.append("\\r");
				break;
			default:
				sb.append(c);
				break;
			}
		}
		
		return sb.toString();
	}
	public static String getCDADAStr(String paramStr)
	{
		return MessageFormat.format("<![CDATA[{0}]]>", paramStr);
	}
	public static String removeCDADAStr(String cdataStr)
	{
		if(cdataStr.indexOf("<![CDATA[")!=-1)
			return cdataStr.replace("<![CDATA[", "").replace("]]>","");
		return cdataStr;
	}
	
	/**
	 * 不能包括特殊字符
	 */
	public static boolean isEspeciallyValidStr(String id) {
		return id.replaceAll("[a-z]*[A-Z]*\\d*_*\\s*", "").length() == 0;
	}

	/**
	 * 不能包含以数字开头的字符
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isPreffixNum(String id) {
		return !id.matches("[0-9]\\w*");
	}
	
	/**
	 * 将字符串首字母大写
	 * 
	 * @param target
	 * @return
	 */
	public static String upperFirstLetter(String target) {
		return target.substring(0, 1).toUpperCase() + target.substring(1);
	}

	/**
	 * @param sectionName
	 * @return
	 */
	public static String getStringSafely(String str) {
		if(str == null){
			return "";
		}
		return str;
	}
	
	/**
	 * 从一个InputStream获得一个文本串
	 * 
	 * @param is
	 * @return
	 */
	public static String getString(InputStream is) {
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String readed;
		try {
			while ((readed = reader.readLine()) != null) {
				sb.append(readed);
				sb.append("\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 获得文件内容
	 * 
	 * @param operation
	 * @return
	 */
	public static String getFileContent(String fileName) {
		File scriptFile = new File(fileName);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(scriptFile);
			String scriptCode = IOUtils.toString(inputStream, "UTF-8");
			return scriptCode;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
		return "";
	
	}
}
