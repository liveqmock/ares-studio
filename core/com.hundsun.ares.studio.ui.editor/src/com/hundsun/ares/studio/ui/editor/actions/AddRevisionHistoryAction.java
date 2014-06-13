package com.hundsun.ares.studio.ui.editor.actions;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.RevisionHistory;

/**
 * 添加修改记录相关的Action基类
 * @author sundl
 *
 */
public abstract class AddRevisionHistoryAction extends ColumnViewerAction {

	protected EObject info;
	protected EReference eReference;
	
	/**
	 * @param viewer
	 * @param editingDomain
	 * @param info 				包含有修订历史记录列表的EMF对象
	 * @param eRefernece		对象中对应修订记录列表的EMF属性
	 */
	public AddRevisionHistoryAction(ColumnViewer viewer, EditingDomain editingDomain, EObject info, EReference eRefernece) {
		super(viewer, editingDomain);
		this.info = info;
		this.eReference = eRefernece;
		setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
	}
	
	public void setInfo(EObject info) {
		this.info = info;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.actions.ColumnViewerAction#createCommand()
	 */
	@Override
	protected Command createCommand() {
		if (info == null)
			return null;
		
		RevisionHistory newObj = CoreFactory.eINSTANCE.createRevisionHistory();
		// BUG #3085::[需求]修改记录修改 日期格式修改，框架层在Factory中创建对象后设置了一个格式，此处重新设置成新格式
		newObj.setModifiedDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
		
		newObj.setVersion(getVersion());
		Command command = AddCommand.create(getEditingDomain(), info, eReference, newObj, 0);
		return command;
	}

	/**
	 * 计算新建记录的版本号
	 * @return
	 */
	protected abstract String getVersion();

}
