/**
 * 源程序名称：TableColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.viewer;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.internal.core.ARESProjectProperty;
import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * @author yanwj06282
 *
 */
public class TableColumnLabelProvider extends EObjectColumnLabelProvider {
	
	// 标准字段和非标准字段的图标
	private static Image STD = DatabaseUI.image("full/obj16/std.png");
	private static Image NON_STD = DatabaseUI.image("full/obj16/non_std.png");

	private IARESResource resource;
	
	/**
	 * @param attribute
	 */
	public TableColumnLabelProvider(EStructuralFeature attribute ,IARESResource resource) {
		super(attribute);
		this.resource = resource;
	}

	@Override
	public String getText(Object element) {
		EStructuralFeature feature = getEStructuralFeature(element);
		if (feature == DatabasePackage.Literals.TABLE_COLUMN__DATA_TYPE) {
			if (element instanceof TableColumn) {
				TableColumn column = (TableColumn) element;
				if (column.getColumnType() == ColumnType.STD_FIELD) {
					ReferenceInfo referenceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(resource.getARESProject(), IMetadataRefType.StdField, column.getFieldName(), true);
					if (referenceInfo == null)
						return StringUtils.EMPTY;
					StandardField field = (StandardField) referenceInfo.getObject();
					return StringUtils.defaultString(field.getDataType());
				} else {
					return StringUtils.defaultString(column.getDataType());
				}
			}
		}else if (DatabasePackage.Literals.TABLE_COLUMN__DEFAULT_VALUE.equals(feature)) {
			if (element instanceof TableColumn) {
				TableColumn column = (TableColumn) element;
				String defValue = column.getDefaultValue();
				String dbType = StringUtils.EMPTY;
				//取得数据库类型，根据脚本名字截取
				try {
					Object odt = (String) ((ARESProjectProperty)resource.getARESProject().getProjectProperty()).getProperties().get("tabledir");
					if (odt instanceof String && StringUtils.isNotBlank(odt.toString())) {
						String jsName = StringUtils.substringBefore(odt.toString(), ".");
						if (StringUtils.indexOf(jsName, "_") > -1) {
							dbType = StringUtils.substringAfterLast(jsName, "_");
						}
					}
				} catch (ARESModelException e1) {
					e1.printStackTrace();
				}
				//如果直接引用了标准默认值，则直接取该数据类型的值
				if (StringUtils.isNotBlank(defValue)) {
					try {
						TypeDefaultValue tdv = MetadataServiceProvider.getTypeDefaultValueByName(resource.getARESProject(), defValue);
						if (tdv != null) {
							return tdv.getData().get(dbType);
						}
					} catch (Exception e) {
						//e.printStackTrace();
					}
				}else{
					if (column.getColumnType() == ColumnType.STD_FIELD) {
						try {
							TypeDefaultValue tdv = MetadataServiceProvider.getTypeDefaultValueOfStdFieldByName(resource.getARESProject(), column.getFieldName());
							if (tdv != null) {
								return tdv.getData().get(dbType);
							}
						} catch (Exception e) {
							//e.printStackTrace();
						}
					}
					if (column.getColumnType() == ColumnType.NON_STD_FIELD) {
						try {
							TypeDefaultValue tdv = MetadataServiceProvider.getTypeDefaultValueOfBizTypeByName(resource.getARESProject(), column.getDataType());
							if (tdv != null) {
								return tdv.getData().get(dbType);
							}
						} catch (Exception e) {
							//e.printStackTrace();
						}
					}
				}
			}
		}
		return super.getText(element);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider#getBackground(java.lang.Object)
	 */
	@Override
	public Color getBackground(Object element) {
		if (resource.isReadOnly()) {
			return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
		}
		return super.getBackground(element);
	}

	@Override
	protected Image doGetImage(Object element) {
		EStructuralFeature feature = getEStructuralFeature(element);
		// 第一列显示图标
		if (feature == DatabasePackage.Literals.TABLE_COLUMN__MARK) {
			if (element instanceof TableColumn) {
				TableColumn c = (TableColumn) element;
				if (c.getColumnType() == ColumnType.STD_FIELD) {
					return STD;
				} else {
					return NON_STD;
				}
			}
		}
		return super.doGetImage(element);
	}
	
}
