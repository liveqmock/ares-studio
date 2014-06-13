/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util;

import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.jres.script.util.impl.ScriptStringUtilImpl;


/**
 * @author lvgao
 *
 */
public interface IScriptStringUtil {

	public static IScriptStringUtil instance = new ScriptStringUtilImpl();
	
	/**
	 * 按给定的日期格式获取日期字符串
	 * @param pattern
	 * @return
	 */
	public  String getCurrentDate(String pattern) ;
	

	/**
     * 将字符串编码成 Unicode。
     * 
     * @param theString 待转换成Unicode编码的字符串。
     * @param escapeSpace 是否忽略空格。
     * 
     * @return 返回转换后Unicode编码的字符串。
     */
    public  String toUnicode(String theString, boolean escapeSpace);
    
//    public  char toHex(int nibble) ;
    
    /**
     * ???????
     * 从 Unicode 码转换成编码前的特殊字符串。
     * 
     * @param in Unicode编码的字符数组。
     * @param off 转换的起始偏移量。
     * @param len 转换的字符长度。
     * @param convtBuf 转换的缓存字符数组。
     * @return 完成转换，返回编码前的特殊字符串。
     */
    public  String fromUnicode(char[] input, int off, int len, char[] convtBuf) ;
    
    /**
     * 用于比较两个字符串数组中字段是否完全相同。
     * 
     * StringUtils.arrayEquals(null, null)   = true
     * StringUtils.equals(null, String[]{"abc"})  = false
     * StringUtils.equals(String[]{"abc"}, null)  = false
     * StringUtils.equals(String[]{"abc","qwe"}, String[]{"qwe","abc"}) = true
     * StringUtils.equals(String[]{"abc","qwe"}, String[]{"abc","qwe"}) = true
     * </pre>
     * 
     * @param a
     * @param a2
     * @return
     */
    public  boolean arrayEquals(String[] a, String[] a2) ;
    
	/**
	 * 构建一个字符串表，这个字符串表会对齐所有列
	 * <BR>所以其中的字符串不应该过长
	 * <BR>本方法采用的长度基准是字节数，一般全角等于2个半角的长度，只有在等宽字体的编辑器中查看才能得到登长效果
	 * @param contents
	 * @return
	 */
    public  String genStringTable(List< List<String> > contents);

    /**
     * 
     * 获取SQL文件头
     * 
     * @param fileName
     * @param userName
     * @param date
     * @param notes
     * @return
     */
    public String getSQLFileHeader(String fileName,String userName, String date, String notes);
    
    /**
     * 
     * 获取C文件头
     * 
     * @param fileName
     * @param userName
     * @param date
     * @param notes
     * @return
     */
    public String getCHeadFileHeader(String fileName,String userName, String date, String notes);
    
    /**
     * 
     * 获取属性文件头
     * 
     * @param fileName
     * @param userName
     * @param date
     * @param notes
     * @return
     */
    public String getPropertyFileHeader(String fileName,String userName, String date, String notes);
    
    
    /**
     * 获取文本文件头
     * 
     * @param fileName
     * @param userName
     * @param date
     * @param notes
     * @return
     */
    public String getTxtFileHeader(String fileName, String userName,
			String date, String notes);
    
    /**
     * 获取StringBuffer对象
     * 
     * @return
     */
    public StringBuffer getStringBuffer();
    
    /**
     * 获取数组
     * 
     * @return
     */
    public List<Object> getList();
    
    /**
     * 获取Map对象
     * 
     * @return
     */
    public Map getMap();
    
    /**
     * 生成固定长度的字符串，长度不够时以fill补齐到后面
     * @param str
     * @param len
     * @param fill
     * @return
     */
    public String fixLength(String str,int len,char fill);
    
    /**
     * 是否以什么开头
     * @param str
     * @param prefix  前缀
     * @return
     */
    public boolean startWith(String str,String prefix);
  
    /**
    * 字符串格式化
    * @param format
    * @param args
    * @return
    */
   public  String format(String format, List<String> args);
   
   /**
    * <p>Converts all the delimiter separated words in a String into capitalized words, 
    * that is each word is made up of a titlecase character and then a series of 
    * lowercase characters. </p>
    *
    * <p>The delimiters represent a set of characters understood to separate words.
    * The first string character and the first non-delimiter character after a
    * delimiter will be capitalized. </p>
    *
    * <p>A <code>null</code> input String returns <code>null</code>.
    * Capitalization uses the unicode title case, normally equivalent to
    * upper case.</p>
    *
    * <pre>
    * WordUtils.capitalizeFully(null, *)            = null
    * WordUtils.capitalizeFully("", *)              = ""
    * WordUtils.capitalizeFully(*, null)            = *
    * WordUtils.capitalizeFully(*, new char[0])     = *
    * WordUtils.capitalizeFully("i aM.fine", {'.'}) = "I am.Fine"
    * </pre>
    * 
    * @param str  the String to capitalize, may be null
    * @param delimiters  set of characters to determine capitalization, null means whitespace
    * @return capitalized String, <code>null</code> if null String input
    * @since 2.1
    */
   public String capitalizeFully(String str, char[] delimiters);
   
   public String uncapitalize(String str, char[] delimiters);
   
   /**
    * 格式化SQL源文本
    * 
    * @param sql
    * @param language 取值为"Any SQL","SQL Server","DB2/UDB","MSAccess","Sybase","Informix","MYSQL","PostgreSQL","Oracle"（大小写不明感）
    * @return
    */
   public String formatSql(String sql , String language);
   
}
