/**
 * 源程序名称：RemoveAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.actions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataViewerUtil;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.UncategorizedItemsCategoryImpl;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.impl.MetadataCategoryImpl;
import com.hundsun.ares.studio.jres.model.metadata.impl.MetadataItemImpl;
import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerAction;

/**
 * 移除当前分组的条目，但是此条目并不会被删除
 * @author gongyf
 *
 */
public class RemoveAction extends ColumnViewerAction {

	/**
	 * @param viewer
	 * @param editingDomain
	 */
	public RemoveAction(ColumnViewer viewer, EditingDomain editingDomain) {
		super(viewer, editingDomain);
		setId(IMetadataActionIDConstant.CV_REMOVE);
		setText("从分组移除");
		setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_DELETE));
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.actions.ColumnViewerAction#createCommand()
	 */
	@Override
	protected Command createCommand() {
		if (MetadataViewerUtil.isShowCategory(getViewer())) {
			ITreeSelection selection = (ITreeSelection) getViewer().getSelection();
			CompoundCommand cmd = new CompoundCommand();
			if (selection != null && !selection.isEmpty()){
				TreePath[] paths = selection.getPaths();{
					for(TreePath path:paths){
						int count=path.getSegmentCount();
						if(count>=2){
							Object item=path.getSegment(count-1);
							Object category = path.getSegment(count-2);
							if(item instanceof MetadataItemImpl && category instanceof MetadataCategoryImpl){
								if(category instanceof UncategorizedItemsCategoryImpl){//选择未分组下的条目
									return null;
								}
								else{
									//将选中条目从当前分类中移至未分组中
//									MetadataResourceData<?> data=(MetadataResourceData<?>) ((MetadataItemImpl)item).eContainer();
//									UncategorizedItemsCategoryImpl uncateCategory = new UncategorizedItemsCategoryImpl(data);
//									Command addcmd = AddCommand.create(getEditingDomain(), uncateCategory, MetadataPackage.Literals.METADATA_CATEGORY__ITEMS, item);
//									cmd.append(addcmd);
									Command removecmd= RemoveCommand.create(getEditingDomain(), category, MetadataPackage.Literals.METADATA_CATEGORY__ITEMS, item);
									cmd.append(removecmd);
								}
							}
							else{//选择项中含子分类文件夹
								return null;
							}
						}
						else{//选择项中含一级分组文件夹
							return null;
						}
					}
				}
			}			
			return cmd;//RemoveCommand.create(getEditingDomain(), selectedObjects);
		}
		return null;
	}
	
	

}
