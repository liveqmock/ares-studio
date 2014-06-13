package com.hundsun.ares.studio.biz.excel.export;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.biz.excel.export.TableBlock.Column;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandlerFactory;
import com.hundsun.ares.studio.core.excel.handler.PropertyHandlerFactoryManager;
import com.hundsun.ares.studio.core.model.ExtensibleModel;

public class AbstractBuilder {
	protected static final Logger logger = Logger.getLogger(AbstractBuilder.class);

	/** 表格中列内容的style，不是表头 */
	protected static int[] TABLE_COL_STYLES = new int[] {TableBlock.Column.LABEL_STYLE, TableBlock.Column.TEXT_STYLE, TableBlock.Column.TEXT_STYLE, 
			TableBlock.Column.TEXT_STYLE, TableBlock.Column.TEXT_STYLE, TableBlock.Column.TEXT_STYLE, };
	
	protected List<Group> groups = new ArrayList<Group>();

	protected IARESProject project;
	
	public AbstractBuilder(IARESProject project) {
		this.project = project;
	}
	
	
	public List<Group> getGroups() {
		return groups;
	}


	public IARESProject getProject() {
		return project;
	}

	protected KeyValueBlock buildKeyValueBlock(ExtensibleModel model, String[] properties, int[] spans, IPropertyHandlerFactory handlerFactory) {
		KeyValueBlock keyValueBlock = new KeyValueBlock();
		for (int i = 0; i < properties.length; i++) {
			String property = properties[i];
			int span = spans[i];
			String value = StringUtils.EMPTY;
			IPropertyHandler handler = handlerFactory.getPropertyHandler(property, project);
			if (handler != null) {
//				// FIXME:特殊处理, 正确的做法应该是注册一个特殊的handler来处理
//				if (property.equals("结果集返回")) {
//					value = handler.getValue(((Service) model).getInterface());
//				} else {
//					value = handler.getValue(model);
//				}
				value = handler.getValue(model);
			}
			keyValueBlock.addKeyValue(property, StringUtils.defaultString(value), span);
		}
		return keyValueBlock;
	}
	
	protected TableBlock buildTableBlock(String[] properties,List<? extends ExtensibleModel> models, IPropertyHandlerFactory handlerFactory) {
		return buildTableBlock(properties, TABLE_COL_STYLES, models, handlerFactory);
	}
	
	protected TableBlock buildTableBlock(String[] properties, int[] styles, List<? extends ExtensibleModel> models, IPropertyHandlerFactory handlerFactory) {
		TableBlock tableBlock = new TableBlock();
		
		for (int i = 0; i< properties.length; i++) {
			String header = properties[i];
			Column c = tableBlock.addColumn(header);
			if (styles != null ) {
				c.style = styles[i];
			} else {
				c.style = TableBlock.Column.TEXT_STYLE;
			}
		}
		
		for (ExtensibleModel model : models) {
			List<String> row = new ArrayList<String>();
			for (String key : properties) {
				IPropertyHandler handler = handlerFactory.getPropertyHandler(key, project);
				String value = null;
				if (handler != null) {
					value = handler.getValue(model);
				} 
				row.add(StringUtils.defaultString(value));
			}
			tableBlock.addRow(row);
		}
		return tableBlock;
	}
	
	protected TextBlock buildTextBlock(String property, ExtensibleModel model, IPropertyHandlerFactory factory, boolean newRow) {
		return buildTextBlock(property, 5, model,factory,newRow);
	}
	
	protected TextBlock buildTextBlock(String property, int textColumns, ExtensibleModel model, IPropertyHandlerFactory factory, boolean newRow) {
		TextBlock block = new TextBlock();
		block.label = property;
		block.newRow = newRow;
		block.textColumns = textColumns;
		String value = null;
		IPropertyHandler handler = factory.getPropertyHandler(property, project);
		if (handler != null) {
			value = StringUtils.defaultString(handler.getValue(model));
		}
		block.text = value;
		return block;
	}
	
	protected IPropertyHandlerFactory getPropertyHandlerFactory(EClass eClass) {
		return PropertyHandlerFactoryManager.getPropertyHandlerFactory(eClass);
	}
}
