package com.hundsun.ares.studio.jres.service.stock;

import java.util.List;

import com.hundsun.ares.studio.biz.excel.export.AbstractBuilder;
import com.hundsun.ares.studio.biz.excel.export.Area;
import com.hundsun.ares.studio.biz.excel.export.Group;
import com.hundsun.ares.studio.biz.excel.export.TableBlock;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;

public class StdFieldBlockBuilder extends AbstractBuilder{

	public static String[] HEADER = new String[] {"序号", "字段名", "类型", "字段名称", "数据字典", "备注"};
	
	private List<StandardField> fields;
	public StdFieldBlockBuilder(IARESProject project, List<StandardField> fields) {
		super(project);
		this.fields = fields;
	}
	
	public void build() {
		if (fields != null && fields.size() > 0) {
			Group group = new Group();
			group.name = "标准字段";
			group.columnWidth = new int[] {5, 15, 10, 25, 10,10};
			groups.add(group);
			
			Area area = new Area();
			group.areas.add(area);
			
			int[] colStyles = new int[HEADER.length];
			for (int i = 0; i < colStyles.length; i++) {
				colStyles[i] = TableBlock.Column.TEXT_STYLE;
			}
			
			IPropertyHandlerFactory handlerFactory = getPropertyHandlerFactory(MetadataPackage.Literals.STANDARD_FIELD);
			TableBlock block = buildTableBlock(HEADER, colStyles, fields, handlerFactory);
			area.blocks.add(block);
		}
	}
}
