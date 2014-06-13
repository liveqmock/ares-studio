/**
 * 源程序名称：DictGroupBuilder.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.service.stock
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.service.stock;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.biz.excel.export.AbstractBuilder;
import com.hundsun.ares.studio.biz.excel.export.Area;
import com.hundsun.ares.studio.biz.excel.export.Group;
import com.hundsun.ares.studio.biz.excel.export.TableBlock;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryList;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * @author sundl
 *
 */
public class DictGroupBuilder extends AbstractBuilder{
	
	public static final String[] PROPERTIES = new String[] {
		"序号",	"字典项", "字典项名称", "产品范围",
	};
	
	private List<DictionaryType> dictList;
	
	/**
	 * @param project
	 */
	public DictGroupBuilder(IARESProject project, List<DictionaryType> dictList) {
		super(project);
		this.dictList = dictList;
	}

	public void build() {
		if (dictList != null && !dictList.isEmpty()) {
			Group group = new Group();
			group.name = "数据字典";
			group.columnWidth = new int[] {5, 15, 25, 10};
			groups.add(group);
			
			Area area = new Area();
			group.areas.add(area);
			area.blocks.add(buildDictBlock());
		}
	}
	
	private TableBlock buildDictBlock() {
		TableBlock block = new TableBlock();
		for (String property : PROPERTIES) {
			block.addColumn(property);
		}
		
		List<ExtensibleModel> allItems = new ArrayList<ExtensibleModel>();
		for (int i = 0; i < dictList.size(); i++) {
			DictionaryType type = dictList.get(i);
			allItems.add(type);
			List<String> row = new ArrayList<String>();
			for (String pro : PROPERTIES) {
				String value = null;
				IPropertyHandlerFactory factory = getPropertyHandlerFactory(MetadataPackage.Literals.DICTIONARY_TYPE);
				if (factory != null) {
					IPropertyHandler handler = factory.getPropertyHandler(pro, project);
					value = handler == null ? null : handler.getValue(type);
				}
				row.add(StringUtils.defaultString(value));
			}
			block.addRow(row);
			block.categoryRows.add(block.numOfRows - 1);
			
			for (DictionaryItem itme : type.getItems()) {
				row = new ArrayList<String>();
				for (String pro : PROPERTIES) {
					String value = null;
					IPropertyHandlerFactory factory = getPropertyHandlerFactory(MetadataPackage.Literals.DICTIONARY_ITEM);
					if (factory != null) {
						IPropertyHandler handler = factory.getPropertyHandler(pro, project);
						value = handler == null ? null : handler.getValue(itme);
					}
					row.add(StringUtils.defaultString(value));
				}
				block.addRow(row);
			}
		}
		
		return block;
	}
	
//	protected IPropertyHandlerFactory getPropertyHandlerFactory(EClass eClass) {
//		return new IPropertyHandlerFactory() {
//			@Override
//			public IPropertyHandler getPropertyHandler(String key, IARESProject project) {
//				return null;
//			}
//		};
//		return PropertyHandlerFactoryManager.getPropertyHandlerFactory(eClass);
//	}
}
