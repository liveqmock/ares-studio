/**
 * 源程序名称：StandardFieldViewerBlock.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.block;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.editor.FormPage;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataOverviewColumnLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataOverviewHeaderColumnLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataOverviewProjectColumnLabelProvider;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.StdFieldDictDescColumnLabelProvider;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardField;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.ColumnSelectSorterListener;
import com.hundsun.ares.studio.ui.editor.viewers.BaseEObjectColumnLabelProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author yanwj06282
 *
 */
public class StandardFieldListOverviewViewerBlock extends MetadataListOverviewViewerBlock {

	/**
	 * @param page
	 * @param editingDomain
	 * @param site
	 * @param resource
	 * @param problemPool
	 */
	public StandardFieldListOverviewViewerBlock(FormPage page, EditingDomain editingDomain,
			IWorkbenchPartSite site, IARESResource resource,
			IProblemPool problemPool) {
		super(page, editingDomain, site, resource, problemPool);
	}

	@Override
	protected void createColumns(TreeViewer viewer) {
		final TreeViewer treeViewer = (TreeViewer) viewer;
		/**ID*/
		{
			
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.NAMED_ELEMENT__NAME;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("ID");
			column.getColumn().setWidth(120);
			column.getColumn().setMoveable(true);
			
			// 设置标签提供器
			ColumnLabelProvider provider = new MetadataOverviewHeaderColumnLabelProvider(attribute);
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
		}
		/**名称*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("名称");
			column.getColumn().setWidth(120);
			
			// 设置标签提供器
			ColumnLabelProvider provider = new MetadataOverviewColumnLabelProvider(attribute);
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
			column.getColumn().setMoveable(true);
		}
		/**所属项目*/
		{
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("所属项目");
			column.getColumn().setWidth(200);
			// 设置标签提供器
			ColumnLabelProvider provider = new MetadataOverviewProjectColumnLabelProvider();
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
			column.getColumn().setMoveable(true);
		}
		/**标准字段引用*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.METADATA_ITEM__REF_ID;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("标准字段引用");
			column.getColumn().setWidth(100);
			// 设置标签提供器
			ColumnLabelProvider provider = new MetadataOverviewColumnLabelProvider(attribute);
			column.setLabelProvider(provider);	
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
			column.getColumn().setMoveable(true);
		}
		/**业务数据类型*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.STANDARD_FIELD__DATA_TYPE;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("业务数据类型");
			column.getColumn().setWidth(100);
			// 设置标签提供器
			ColumnLabelProvider provider = new MetadataOverviewColumnLabelProvider(attribute);
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
			column.getColumn().setMoveable(true);
		}
		/**字典条目*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.STANDARD_FIELD__DICTIONARY_TYPE;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("字典条目");
			column.getColumn().setWidth(100);
			// 设置标签提供器
			ColumnLabelProvider provider = new MetadataOverviewColumnLabelProvider(attribute);
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
			column.getColumn().setMoveable(true);
		}
		/**字典条目说明*/
		{
			// 定义主属性
//			EAttribute attribute = MetadataPackage.Literals.STANDARD_FIELD__DICTIONARY_TYPE;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("字典条目说明");
			column.getColumn().setWidth(200);
			// 设置标签提供器
			StdFieldDictDescColumnLabelProvider provider = new StdFieldDictDescColumnLabelProvider(getARESResource());//MetadataColumnLabelProvider(attribute, getARESResource());
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
			column.getColumn().setMoveable(true);
		}
		
		/**备注*/
		{
			// 定义主属性
			EAttribute attribute = MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION;
			
			// 创建表格列
			TreeViewerColumn column = new TreeViewerColumn(treeViewer, SWT.LEFT);
			column.getColumn().setText("备注");
			column.getColumn().setWidth(200);
			// 设置标签提供器
			ColumnLabelProvider provider = new MetadataOverviewColumnLabelProvider(attribute);
			column.setLabelProvider(provider);
			column.getColumn().addSelectionListener(new ColumnSelectSorterListener(column, provider));
			column.getColumn().setMoveable(true);

		}
	}

	@Override
	protected String getReferenceType() {
		return IMetadataRefType.StdField;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.link.ICellLinkProvider#getLinkedObject(org.eclipse.jface.viewers.ViewerCell)
	 */
	@Override
	public Pair<EObject, IARESResource> getLinkedObject(ViewerCell cell) {
		BaseEObjectColumnLabelProvider labelProvider = (BaseEObjectColumnLabelProvider) getColumnViewer().getLabelProvider(cell.getColumnIndex());
		
		EStructuralFeature feature = labelProvider.getEStructuralFeature(cell.getElement());
		Pair<MetadataItem, IARESResource> pair = (Pair<MetadataItem, IARESResource>) cell.getElement();
		DeStandardField d = MetadataUtil.decrypt((StandardField) pair.first, pair.second);

		if (MetadataPackage.Literals.METADATA_ITEM__REF_ID.equals(feature)) {
			
			if(((Pair)d.getResolvedItem()).equals(pair)){
				return null;
			}
			return (Pair)d.getResolvedItem();
		} else if (MetadataPackage.Literals.STANDARD_FIELD__DATA_TYPE.equals(feature)) {
			return (Pair)d.getDataType().getResolvedItem();
		} else if (MetadataPackage.Literals.STANDARD_FIELD__DICTIONARY_TYPE.equals(feature)) {
			return (Pair)d.getDictionaryType().getResolvedItem();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.block.MetadataListViewerBlock#getID()
	 */
	@Override
	protected String getID() {
		return getClass().getName();
	}

}
