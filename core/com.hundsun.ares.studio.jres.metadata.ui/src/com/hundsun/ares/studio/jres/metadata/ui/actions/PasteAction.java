/**
 * 源程序名称：PasteAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.actions;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataViewerUtil;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.UncategorizedItemsCategoryImpl;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAction;
import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;
import com.hundsun.ares.studio.ui.util.ARESEMFClipboard;

/**
 * 粘贴
 * @author sundl
 */
public class PasteAction extends ColumnViewerAction {

	private EClass cateClass;
	private EClass itemClass;
	
	/**
	 * 
	 * @param viewer
	 * @param editingDomain
	 * @param cateClass 分组的类型
	 * @param itemClass 条目的类型
	 */
	public PasteAction(ColumnViewer viewer, EditingDomain editingDomain, EClass cateClass, EClass itemClass) {
		super(viewer, editingDomain);
		setText("粘贴");
		setEnabled(false);
		
		setId(IActionIDConstant.CV_PASTE);
		
		this.cateClass = cateClass;
		this.itemClass = itemClass;
		setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
	}

	@Override
	protected Command createCommand() {
		EditingDomain domain = getEditingDomain();
		if (domain == null)
			return null;
		
		Command command = null;

		// 复制粘贴的情况有如下4种
		// 1.复制了元数据条目，粘贴到分组下
		// 添加这些条目，并加入选中的分组
		
		// 2.复制了元数据分组，粘贴到分组下
		// 添加新分组，原分组下的条目放弃（此处可能有问题）
		
		// 3.复制了元数据条目，粘贴到未分组下（包括不显示分组的时候）
		// 添加复制条目
		
		// 4.复制了元数据分组，粘贴到为分组下（包括不显示分组的时候）
		// 无反应
		
		List<MetadataCategory> copiedCategories = (List<MetadataCategory>) ARESEMFClipboard.getInstance().pasteFromClipboard(cateClass.getInstanceClass());
		List<MetadataItem> copiedItems = (List<MetadataItem>) ARESEMFClipboard.getInstance().pasteFromClipboard(itemClass.getInstanceClass());
		
		if (MetadataViewerUtil.isShowCategory(getViewer()) ) {
			MetadataCategory cate = MetadataViewerUtil.getSelectedCategory(getViewer(), false);
			// 由于getSelectedCategory会在没有选择的情况下返回root分类，导致粘贴到这个分类下，是种错误情况，导致条目在界面上看不到
			MetadataResourceData<?> model = MetadataViewerUtil.getMetadataModel(getViewer());
			if (model == null) // 创建界面的到时候，setInput之前可能也会调用到
				return null;
			
			if (cate == model.getRoot())
				cate = MetadataViewerUtil.getUncategorizedCategory(getViewer());
			
			if (!copiedCategories.isEmpty()) {
				// 被复制的是分组则将分组负责到选中的分组下，而对条目不再进行复制
				if (cate instanceof UncategorizedItemsCategoryImpl) {
					command = createCommandForCopyCategoriesToNotCategory(copiedCategories, MetadataViewerUtil.getMetadataModel(getViewer()));
				} else {
					command = createCommandForCopyCategoriesToCategory(copiedCategories, cate);
				}
			} else if (!copiedItems.isEmpty()) {
				// 复制了条目
				if (cate instanceof UncategorizedItemsCategoryImpl) {
					command = createCommandForCopyItemsToNotCategory(copiedItems, MetadataViewerUtil.getMetadataModel(getViewer()));
				} else {
					command = createCommandForCopyItemsToCategory(copiedItems, cate);
				}
			}
			
		} else {
			if (!copiedCategories.isEmpty()) {
				// 被复制的是分组则将分组负责到选中的分组下，而对条目不再进行复制
				command = createCommandForCopyCategoriesToNotCategory(copiedCategories, MetadataViewerUtil.getMetadataModel(getViewer()));
			} else if (!copiedItems.isEmpty()) {
				// 复制了条目
				command = createCommandForCopyItemsToNotCategory(copiedItems, MetadataViewerUtil.getMetadataModel(getViewer()));
			}
		}
		return command;
	}

	/**
	 * 
	 * @param categories 无法解析的引用条目将被删除
	 * @param container
	 * @return
	 */
	private Command createCommandForCopyCategoriesToCategory(Collection<? extends MetadataCategory> categories, MetadataCategory container) {
		// 判断是否在一个文件中进行复制
		for (MetadataCategory category : categories) {
			if (!category.getItems().isEmpty()) {
				// FIXME 这里需要能够对proxy的引用进行复制
				EObject obj = category.getItems().get(0);
				if (EcoreUtil.resolve(obj,(EObject)null) == obj) {
					// 如果无法解析出引用条目，则放弃条目的复制
					// 因为这个分组是从剪贴板来的，已经是复制的了，所以修改没有关系
					category.getItems().clear();
				}
			}
		}

		Command command = AddCommand.create(getEditingDomain(), container, MetadataPackage.Literals.METADATA_CATEGORY__CHILDREN, categories);
		return command;
	}
	
	private Command createCommandForCopyCategoriesToNotCategory(Collection<? extends MetadataCategory> categories, MetadataResourceData<?> container) {
		return null;
	}
	
	private Command createCommandForCopyItemsToCategory(Collection<? extends MetadataItem> items, MetadataCategory container) {
		CompoundCommand command = new CompoundCommand();
		// 先添加到总列表
		command.append(AddCommand.create(getEditingDomain(), 
				EcoreUtil.getRootContainer(container), 
				MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS, items))	;
		// 再添加到分组
		command.append(AddCommand.create(getEditingDomain(), container,
				MetadataPackage.Literals.METADATA_CATEGORY__ITEMS, items));
		return command;
	}
	
	private Command createCommandForCopyItemsToNotCategory(Collection<? extends MetadataItem> items, MetadataResourceData<?> container) {
		Command command = AddCommand.create(getEditingDomain(), container, MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS, items);
		return command;
	}

}
