/**
 * 源程序名称：ObjectListBuilder.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.service.stock
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.service.stock;

import java.util.List;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.excel.export.AbstractBuilder;
import com.hundsun.ares.studio.biz.excel.export.Area;
import com.hundsun.ares.studio.biz.excel.export.Group;
import com.hundsun.ares.studio.biz.excel.export.TableBlock;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;

/**
 * @author sundl
 *
 */
public class ObjectListBuilder extends AbstractBuilder{

	public static final String[] PROPERTIES = new String[] {
		"对象名", "对象中文名", "对象状态"
	};
	
	private List<ARESObject> objects;
	
	/**
	 * @param project
	 */
	public ObjectListBuilder(IARESProject project, List<ARESObject> objects) {
		super(project);
		this.objects = objects;
	}
	
	public void build() {
		Group group = new Group();
		group.name = "对象列表";
		group.columnWidth = new int [] {15, 25, 15,};
		groups.add(group);
		
		Area area = new Area();
		group.areas.add(area);
		
		IPropertyHandlerFactory handlerFactory = getPropertyHandlerFactory(BizPackage.Literals.ARES_OBJECT);
		TableBlock tableBlock = buildTableBlock(PROPERTIES, null, objects, handlerFactory);
		tableBlock.linkColumn = 0;
		tableBlock.linkedGroup = "对象-全部";
		
		area.blocks.add(tableBlock);
	}

}
