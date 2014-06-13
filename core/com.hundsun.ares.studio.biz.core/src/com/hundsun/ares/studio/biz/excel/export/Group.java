/**
 * 源程序名称：Sheet.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.export;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sundl
 *
 */
public class Group {
	
	/** 对应Sheet页的名字 */
	public String name;
	/** 对应sheet页的index,需要注意的是，随着group的添加，这个index可能最终并不会为这个值，所以要配合group的顺序来使用 */
	public int index = -1;
	
	/** 列宽， int，值为字符数 */
	public int[] columnWidth;
	
	public List<Area> areas = new ArrayList<Area>();
	
}
