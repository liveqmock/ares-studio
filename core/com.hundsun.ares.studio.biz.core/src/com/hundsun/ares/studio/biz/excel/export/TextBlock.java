/**
 * 源程序名称：TextBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.export;

/**
 * @author sundl
 *
 */
public class TextBlock extends Block {
	public String label;
	public String text;
	
	/** text里遇到换行符是否在excel中新增行 */
	public boolean newRow;
	/** text占有的列数 */
	public int textColumns = 5;
}
