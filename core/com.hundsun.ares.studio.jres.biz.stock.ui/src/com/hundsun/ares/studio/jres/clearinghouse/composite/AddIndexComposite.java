/**
 * 源程序名称：AddIndexComposite.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.clearinghouse.composite;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.ui.viewer.IndexColumnEditingSupport;
import com.hundsun.ares.studio.jres.database.ui.viewer.IndexColumnLabelProvider;
import com.hundsun.ares.studio.jres.model.chouse.AddIndexModification;
import com.hundsun.ares.studio.jres.model.chouse.ChouseFactory;
import com.hundsun.ares.studio.jres.model.chouse.ChousePackage;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.database.DatabaseFactory;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.editor.editingsupport.BooleanEditingSupport;
import com.hundsun.ares.studio.ui.editor.editingsupport.TextEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.CheckBoxColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * 增加索引
 * @author wangxh
 *
 */
public class AddIndexComposite extends ModifyActionColumnComposite<TableIndex>{

	/**
	 * 增加索引
	 * @param parent
	 * @param resource
	 * @param action
	 */
	public AddIndexComposite(Composite parent, TableResourceData tableData, IARESResource resource,
			Modification action) {
		super(parent, tableData, resource, action);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionColumnComposite#getEReference()
	 */
	@Override
	protected EReference getEReference() {
		return ChousePackage.Literals.ADD_INDEX_MODIFICATION__INDEXS;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionColumnComposite#creatBlankItem()
	 */
	@Override
	protected TableIndex creatBlankItem() {
		return DatabaseFactory.eINSTANCE.createTableIndex();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionColumnComposite#getActionItems(com.hundsun.ares.studio.jres.model.database.Modification)
	 */
	@Override
	protected EList<TableIndex> getActionItems(Modification modification) {
		if(action instanceof AddIndexModification){
			return ((AddIndexModification)action).getIndexs();
		}
		return new BasicEList<TableIndex>();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionColumnComposite#creatColumnComposite(org.eclipse.jface.viewers.TreeViewer, com.hundsun.ares.studio.core.IARESResource)
	 */
	@Override
	protected void creatColumnComposite(TreeViewer treeViewer,
			IARESResource resource) {
		
		// 标记
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_INDEX__MARK;
			
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("标记");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(new TextEditingSupport(treeViewer, attribute));
		}
		
		// 索引名
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_INDEX__NAME;
			
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("索引名");
			
			EObjectColumnLabelProvider provider = new EObjectColumnLabelProvider(attribute);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(new TextEditingSupport(treeViewer, attribute));
		}
//		// 索引字段列表
		{
			EStructuralFeature feature = DatabasePackage.Literals.TABLE_INDEX__COLUMNS;
		
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("索引字段列表");
			
			IndexColumnLabelProvider provider = new IndexColumnLabelProvider(feature);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(new IndexColumnEditingSupport(treeViewer,feature,resource));
		}
		// 是否唯一
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_INDEX__UNIQUE;
			
			TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(100);
			tvColumn.getColumn().setText("是否唯一");
			
			CheckBoxColumnLabelProvider provider = new CheckBoxColumnLabelProvider(attribute , resource);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(new BooleanEditingSupport(treeViewer, attribute));
		}
		//聚簇列
		{
			EAttribute attribute = DatabasePackage.Literals.TABLE_INDEX__CLUSTER;
			
			final TreeViewerColumn tvColumn = new TreeViewerColumn(treeViewer, SWT.LEFT);
			tvColumn.getColumn().setWidth(80);
			tvColumn.getColumn().setText("聚簇列");
			
			CheckBoxColumnLabelProvider provider = new CheckBoxColumnLabelProvider(attribute , resource);
			tvColumn.setLabelProvider(provider);
			
			tvColumn.setEditingSupport(new BooleanEditingSupport(treeViewer, attribute));
			tvColumn.getColumn().setMoveable(true);
		}
		// 扩展信息
		{
			ExtensibleModelUtils.createExtensibleModelTreeViewerColumns(
					treeViewer, resource, DatabasePackage.Literals.TABLE_INDEX, null);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.ui.editors.history.component.ModifyActionComposite#initAction(com.hundsun.ares.studio.jres.model.database.Modification)
	 */
	@Override
	protected void initAction(Modification modification) {
		if(modification != null && modification instanceof AddIndexModification){
			action = modification;
		}
		else{
			action = ChouseFactory.eINSTANCE.createAddIndexModification();
		}
	}

	
}
