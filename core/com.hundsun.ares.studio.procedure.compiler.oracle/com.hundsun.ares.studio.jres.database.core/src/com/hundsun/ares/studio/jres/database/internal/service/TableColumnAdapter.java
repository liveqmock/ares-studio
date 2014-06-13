/**
 * 源程序名称：TableColumnAdapter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.internal.service;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.jres.database.service.ITableColumn;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.metadata.service.IStandardField;
import com.hundsun.ares.studio.jres.model.database.TableColumn;

/**
 * @author gongyf
 *
 */
public class TableColumnAdapter implements ITableColumn {

	protected final TableColumn column;
	
	protected final IARESProject project;
	
	private String chineseName;
	
	private String type;
	
	IStandardField field;
	/**
	 * @param column
	 */
	public TableColumnAdapter(TableColumn column,IARESProject project) {
		super();
		this.column = column;
		this.project = project;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.service.ITableColumn#getName()
	 */
	@Override
	public String getName() {
		return column.getName();
	}

	@Override
	public String getChineseName() {
		// FIXME 查找出对应的标准字段并返回，需要缓存
		if (chineseName == null) {
			if (getField() != null) {
				chineseName = field.getChineseName();
			} else {
			chineseName = StringUtils.EMPTY;
			}
		}
		return chineseName;
	}

	@Override
	public String getType() {
		// FIXME 查找出对应的标准字段并返回，需要缓存
		if (type == null) {
			if (getField() != null) {
				type = field.getDataTypeId();
			} else {
				type = StringUtils.EMPTY;
			}
		}
		return type;
	}

	@Override
	public String getDefaultValue() {
		return column.getDefaultValue();
	}

	@Override
	public boolean isPrimaryKey() {
		return column.isPrimaryKey();
	}

	@Override
	public boolean isNullable() {
		return column.isNullable();
	}
	
	private IStandardField getField(){
		IMetadataService service = DataServiceManager.getInstance().getService(project, IMetadataService.class);
		if(service != null){
			 field = service.getStandardField(column.getFieldName());
		}
		return field;
	}

}
