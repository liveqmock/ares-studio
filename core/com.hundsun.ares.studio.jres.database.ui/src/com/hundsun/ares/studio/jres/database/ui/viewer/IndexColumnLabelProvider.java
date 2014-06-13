/**
 * 源程序名称：IndexColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：王小恒
 */
package com.hundsun.ares.studio.jres.database.ui.viewer;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * 索引字段列的labelprovider
 * @author wangxh
 *
 */
public class IndexColumnLabelProvider extends EObjectColumnLabelProvider {

	/**
	 */
	public IndexColumnLabelProvider(EStructuralFeature attribute) {
		super(attribute);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		if(element instanceof TableIndex){
			EList<TableIndexColumn> columns = ((TableIndex)element).getColumns();
			StringBuffer buffer = new StringBuffer();
			for (int i = 0, length = columns.size(); i < length; i++) {
				buffer.append(columns.get(i).getColumnName());
				if (i < length - 1)
					buffer.append(",");
			}
			return buffer.toString();
		}
		return StringUtils.EMPTY;
	}

//	/* (non-Javadoc)
//	 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipText(java.lang.Object)
//	 */
//	@Override
//	public String getToolTipText(Object element) {
//		return getText(element);
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.jface.viewers.CellLabelProvider#getToolTipDisplayDelayTime(java.lang.Object)
//	 */
//	@Override
//	public int getToolTipDisplayDelayTime(Object object) {
//		return 100;
//	}

	
}
