/**
 * 源程序名称：TableColumnChineseNameLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.viewer;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.ColumnLabelProvider;

import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author gongyf
 *
 */
public class TableColumnRefLabelProvider extends ColumnLabelProvider {
	
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
	public TableColumnRefLabelProvider(IARESBundle bundle, TYPE type) {
		super();
		this.bundle = bundle;
		this.type = type;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		TableColumn column = (TableColumn) element;
		// 非标准字段直接返回存储的值
		if (column.getColumnType() == ColumnType.NON_STD_FIELD) {
			switch (type) {
			case ChineseName:
				return StringUtils.defaultString(column.getChineseName()) ;
			case Desciption:
				return StringUtils.defaultString(column.getDescription()) ;
			case Type:
				return StringUtils.defaultString(column.getDataType()) ;
			default:
				return StringUtils.EMPTY;
			} 
		}
		
		IARESProject project = com.hundsun.ares.studio.core.util.ResourcesUtil.getARESProject(bundle);
		ReferenceInfo  referenceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project,IMetadataRefType.StdField,column.getFieldName(),true);

		if (referenceInfo != null) {
			StandardField field = (StandardField) referenceInfo.getObject();
			if (field != null) {
				switch (type) {
				case ChineseName:
					return StringUtils.defaultString(field.getChineseName()) ;
				case Desciption:
					StringBuffer text = new StringBuffer();
					String dictTypeStr = field.getDictionaryType();
					if(StringUtils.isNotBlank(dictTypeStr)){
						ReferenceInfo  dictReferenceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project,IMetadataRefType.Dict,dictTypeStr,true);
						if(dictReferenceInfo != null){
							DictionaryType objDictionaryType = (DictionaryType) dictReferenceInfo.getObject();
							if(objDictionaryType!=null){
								for(DictionaryItem item : objDictionaryType.getItems()){
									String value = StringUtils.defaultString(item.getValue());
									String chineseName = StringUtils.defaultString(item.getChineseName());
									text.append(objDictionaryType.getName());
									text.append(":");
									text.append(objDictionaryType.getChineseName());
									text.append("-");
									text.append(value);
									text.append(":");
									text.append(chineseName);
									text.append("\r\n");
								}
							}
						}
					}
					if(StringUtils.isNotBlank(text.toString()) && StringUtils.isNotBlank(field.getDescription())){
							text.append("\r\n");
							text.append(field.getDescription());
					}
					return StringUtils.defaultString(StringUtils.defaultIfBlank(text.toString(), field.getDescription())) ;
				case Type:
					return StringUtils.defaultString(field.getDataType()) ;
				}
				
			}
		}
		
		return StringUtils.EMPTY;
	}
}
