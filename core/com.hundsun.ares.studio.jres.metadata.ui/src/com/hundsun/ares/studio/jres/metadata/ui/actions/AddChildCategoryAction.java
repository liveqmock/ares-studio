/**
 * 源程序名称：AddChildCategoryAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.actions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.jres.metadata.ui.MetadataUI;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataViewerUtil;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.UncategorizedItemsCategoryImpl;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAction;

/**
 * 添加子分类操作
 * @author gongyf
 *
 */
public class AddChildCategoryAction extends ColumnViewerAction {

	/**
	 * @param viewer
	 * @param editingDomain
	 */
	public AddChildCategoryAction(ColumnViewer viewer,
			EditingDomain editingDomain) {
		super(viewer, editingDomain);
		
		setText("添加子组");
		setId(IMetadataActionIDConstant.CV_ADD_CHILD_CATEGORY);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(MetadataUI.PLUGIN_ID, "icons/full/obj16/addChildCategory.png"));
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.actions.ColumnViewerAction#createCommand()
	 */
	@Override
	protected Command createCommand() {
		if (MetadataViewerUtil.isShowCategory(getViewer())) {
			MetadataCategory category = MetadataViewerUtil.getSelectedCategory(getViewer(), false);
			// 未分组这个节点是特殊的
			if (category instanceof UncategorizedItemsCategoryImpl) {
				return null;
			}
			return AddCommand.create(getEditingDomain(), category, 
					MetadataPackage.Literals.METADATA_CATEGORY__CHILDREN, MetadataFactory.eINSTANCE.createMetadataCategory());
		}
		return null;
	}

}
