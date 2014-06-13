package com.hundsun.ares.studio.jres.service.stock;

import com.hundsun.ares.studio.biz.excel.export.AbstractBuilder;
import com.hundsun.ares.studio.biz.excel.export.Area;
import com.hundsun.ares.studio.biz.excel.export.Group;
import com.hundsun.ares.studio.biz.excel.export.TableBlock;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.StandardFieldList;

public class StockGroupBuilder extends AbstractBuilder {

	public static final String[] PROPERTIES = new String[] {
		"ÐòºÅ", "×ÖµäÏî", "×ÖµäÏîÃû³Æ", "²úÆ··¶Î§"
	};
	
	public StockGroupBuilder(IARESProject project) {
		super(project);
	}

	public void build() {
		IARESResource[] resource;
		try {
			resource = project.findResource(IMetadataResType.StdField);
			if (resource.length > 0) {
				IARESResource stdResource = resource[0];
				StandardFieldList stdList = stdResource.getInfo(StandardFieldList.class);
				Group group = new Group();
				group.name = "Êý¾Ý×Öµä";
				groups.add(group);
				
				Area area = new Area();
				group.areas.add(area);
				
				IPropertyHandlerFactory handlerFactory = getPropertyHandlerFactory(MetadataPackage.Literals.STANDARD_FIELD);
				TableBlock block = buildTableBlock(PROPERTIES, stdList.getItems(), handlerFactory);
				area.blocks.add(block);
			}
		} catch (ARESModelException e) {
			logger.error(e);
		}
	}
}
