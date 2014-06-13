/**
 * 源程序名称：MoveDownAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataViewerUtil;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.UncategorizedItemsCategoryImpl;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerMoveAction;

/**
 * 下移
 * @author gongyf
 *
 */
public class MoveDownAction extends ColumnViewerMoveAction {

	/**
	 * @param viewer
	 * @param editingDomain
	 * @param moveUp
	 */
	public MoveDownAction(ColumnViewer viewer, EditingDomain editingDomain) {
		super(viewer, editingDomain, false);
		setText("下移");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/down.gif"));
		setId(IMetadataActionIDConstant.CV_MOVE_DOWN);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.actions.ColumnViewerMoveAction#getOwner()
	 */
	@Override
	protected EObject getOwner() {
		
		if (MetadataViewerUtil.isShowCategory(getViewer())) {
			MetadataCategory category = MetadataViewerUtil.getSelectedCategory(getViewer(), true);
			if (category instanceof UncategorizedItemsCategoryImpl) {
				return null;
			}
			return category;
		} else {
			return MetadataViewerUtil.getMetadataModel(getViewer());
		}
	}

	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.actions.ColumnViewerMoveAction#getReference()
	 */
	@Override
	protected EReference getReference() {
		if (MetadataViewerUtil.isShowCategory(getViewer())) {
			// 区分在移动分组还是移动条目
			List<Object> objects = getSelectedObjects();
			if (objects.size() > 0) {
				if (objects.get(0) instanceof MetadataItem) {
					return MetadataPackage.Literals.METADATA_CATEGORY__ITEMS;
				} else if (objects.get(0) instanceof MetadataCategory) {
					return MetadataPackage.Literals.METADATA_CATEGORY__CHILDREN;
				}
			}
		} else {
			return MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS;
		}
		
		return null;
	}

}
