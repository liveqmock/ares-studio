/**
 * 源程序名称：WriterFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.excel.export.writer;

import org.apache.poi.ss.usermodel.Sheet;

import com.hundsun.ares.studio.biz.excel.export.Area;
import com.hundsun.ares.studio.biz.excel.export.Block;
import com.hundsun.ares.studio.biz.excel.export.Group;

/**
 * @author sundl
 *
 */
public interface WriterFactory {

	public Writer newGroupWriter(Group group, Sheet sheet);
	
	public Writer newAreaWriter(Area area, Sheet sheet);

	public Writer newBlockWriter(Block block, Sheet sheet);
	
}
