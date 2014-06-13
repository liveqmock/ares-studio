/**
 * 源程序名称：TableColumnChineseNameLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.clearinghouse.provider;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.metadata.service.IStandardField;
import com.hundsun.ares.studio.jres.model.chouse.RemovedTableColumn;
import com.hundsun.ares.studio.jres.model.database.ColumnType;

/**
 * @author gongyf
 *
 */
public class RemoveColumnRefLabelProvider extends ColumnLabelProvider {
	
	public enum TYPE {
		ChineseName, Type, Desciption
	}
	
	private IARESBundle bundle;
	private TYPE type;
	
	/**
	 * 
	 * @param bundle 
	 * @param type 显示的引用信息 {@link TYPE}
	 */
	public RemoveColumnRefLabelProvider(IARESBundle bundle, TYPE type) {
		super();
		this.bundle = bundle;
		this.type = type;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		RemovedTableColumn column = (RemovedTableColumn) element;
		// 非标准字段直接返回属性的值
		if (column.getColumnType() == ColumnType.NON_STD_FIELD) {
			switch (type) {
			case ChineseName:
				return StringUtils.defaultString(column.getChineseName()) ;
			case Desciption:
				return StringUtils.defaultString(column.getDescription()) ;
			case Type:
				return StringUtils.defaultString(column.getDataType()) ;
			}
			return StringUtils.EMPTY;
		}
		IARESProject project = com.hundsun.ares.studio.core.util.ResourcesUtil.getARESProject(bundle);
		IMetadataService service = DataServiceManager.getInstance().getService(project, IMetadataService.class);
		if (service != null) {
			IStandardField filed = service.getStandardField(column.getName());
			if (filed != null) {
				switch (type) {
				case ChineseName:
					return StringUtils.defaultString(filed.getChineseName()) ;
				case Desciption:
					return StringUtils.defaultString(filed.getDescription()) ;
				case Type:
					return StringUtils.defaultString(filed.getDataTypeId()) ;
				}
			}
		}
		
		return StringUtils.EMPTY;
	}
}
