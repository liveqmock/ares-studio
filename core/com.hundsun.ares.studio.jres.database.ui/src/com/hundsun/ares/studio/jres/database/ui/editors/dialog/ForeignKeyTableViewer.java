/**
 * 源程序名称：ForeignKeyTableViewer.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.editors.dialog;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.metadata.service.IStandardField;
import com.hundsun.ares.studio.jres.model.database.TableColumn;

/**
 * @author liaogc
 * 
 */
public class ForeignKeyTableViewer extends CheckboxTableViewer {
	private TableColumn sourceTableColumn;
	private IARESProject project;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.CheckboxTableViewer#handleSelect(org.eclipse
	 * .swt.events.SelectionEvent)
	 */
	@Override
	public void handleSelect(SelectionEvent event) {
		Object[] selectedList = this.getCheckedElements();
		if (selectedList != null) {
			for (Object object : selectedList) {
				TableItem item = (TableItem) event.item;
				Object data = item.getData();
				if (data != object) {
					setChecked(object, false);
				}
			}
		}
		  TableItem item = (TableItem) event.item;
          Object data = item.getData();
		if(data instanceof TableColumn){
			TableColumn destTableColumn =(TableColumn) data;
			StringBuilder errorMessage = new StringBuilder();
			if(item.getChecked()&& !vlidateType(getBusinessId(sourceTableColumn.getFieldName()),getBusinessId(destTableColumn.getFieldName()),errorMessage)){
				MessageDialog.openWarning(null, "字段类型", errorMessage.toString());
			}
		}
		super.handleSelect(event);

	}

	/**
	 * @param table
	 */
	public ForeignKeyTableViewer(Table table, TableColumn sourceTableColumn,IARESProject project) {
		super(table);
		this.sourceTableColumn = sourceTableColumn;
		this.project = project;

	}

	public static CheckboxTableViewer newCheckList(Composite parent,
			TableColumn sourceTableColumn,IARESProject project, int style) {
		Table table = new Table(parent, SWT.CHECK | style);
		return new ForeignKeyTableViewer(table, sourceTableColumn, project);
	}

	private boolean vlidateType(String id, String id2,
			StringBuilder errorMessage) {
		IMetadataService metadataService = getMetadataService(this.project);
		IBusinessDataType type1 = metadataService.getBusinessDataType(id);
		IBusinessDataType type2 = metadataService.getBusinessDataType(id2);
		if (type1.getName().equals(type2.getName())) {
			return true;
		} else {
			if(!type1.getStdType().getName().equals(type2.getStdType().getName())){
				errorMessage.append("字段类型不一致!");	
				return false;
			}else if(type1.getLength() != type2.getLength()){
				errorMessage.append("字段类型长度不一致!");
				return false;
			}
		}
		return true;

	}
	private String getBusinessId(String fieldName){
		IMetadataService service = getMetadataService(project);
		IStandardField filed = service.getStandardField(fieldName);
		return StringUtils.defaultString(filed.getDataTypeId());
		
	}

	private IMetadataService getMetadataService(IARESProject project) {
		return DataServiceManager.getInstance().getService(project,
				IMetadataService.class);
	}

}
