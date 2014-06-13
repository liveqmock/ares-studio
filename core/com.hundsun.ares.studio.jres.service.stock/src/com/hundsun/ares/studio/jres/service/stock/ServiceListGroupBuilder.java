package com.hundsun.ares.studio.jres.service.stock;

import java.util.List;

import com.hundsun.ares.studio.biz.excel.export.AbstractBuilder;
import com.hundsun.ares.studio.biz.excel.export.Area;
import com.hundsun.ares.studio.biz.excel.export.Group;
import com.hundsun.ares.studio.biz.excel.export.TableBlock;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;
import com.hundsun.ares.studio.jres.service.Service;
import com.hundsun.ares.studio.jres.service.ServicePackage;

public class ServiceListGroupBuilder extends AbstractBuilder {

	public static final String[] PROPERTIES = new String[] {
		"功能号", "老功能号", "业务范围", "产品范围", "功能名称", "功能描述", "功能状态", "结果集返回", 
	};
	
	List<Service> services;
	public ServiceListGroupBuilder(IARESProject project, List<Service> services) {
		super(project);
		this.services = services;
	}
	
	public void build() {
		Group group = new Group();
		group.name = "功能列表";
		group.index = 4;
		group.columnWidth = new int [] {10, 10, 10, 10, 25, 35, 10, 10};
		groups.add(group);
		
		Area area = new Area();
		group.areas.add(area);
		
		IPropertyHandlerFactory handlerFactory = getPropertyHandlerFactory(ServicePackage.Literals.SERVICE);
		TableBlock tableBlock = buildTableBlock(PROPERTIES, null, services, handlerFactory);
		tableBlock.linkColumn = 0;
		tableBlock.linkedGroup = "服务接口-全部";
		
		area.blocks.add(tableBlock);
	}

}
