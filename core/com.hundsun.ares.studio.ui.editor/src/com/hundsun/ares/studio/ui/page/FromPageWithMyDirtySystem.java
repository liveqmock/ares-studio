/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.page;


import java.util.List;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.editor.AbstractHSFormEditor;
import com.hundsun.ares.studio.ui.grid.EditorComponent;
import com.hundsun.ares.studio.ui.util.EditorDirtyStatus;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;



public abstract class FromPageWithMyDirtySystem<T> extends HSComponentbasedFormPage {

	/**
	 * 脏状态
	 */
	protected EditorDirtyStatus dirty = new EditorDirtyStatus();
	/**
	 * 关联的资源
	 */
	protected IARESResource resource;
	/**
	 * 撤销重做上下文
	 */
	protected IUndoContext undoContext;
	/**
	 * 编辑器关联的模型
	 */
	protected T info;
	
	public void setUndoContext(IUndoContext undoContext) {
		this.undoContext = undoContext;
	}
	
	public IUndoContext getUndoContext() {
		return undoContext;
	}

	public FromPageWithMyDirtySystem(FormEditor editor, String id, String title) {
		super(editor, id, title);
		if (editor instanceof AbstractHSFormEditor) {
			resource = ((AbstractHSFormEditor) editor).getResource();
		}
	}

	public FromPageWithMyDirtySystem(String id, String title) {
		super(id, title);
	}

	public EditorDirtyStatus getDirtyStatus() {
		return dirty;
	}

	public void setDirtyStatus(EditorDirtyStatus dirty) {
		this.dirty = dirty;
	}

	@Override
	public boolean isDirty() {
		return dirty.getValue();
	}

	/**
	 * @return the resource
	 */
	public IARESResource getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(IARESResource resource) {
		this.resource = resource;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}
	
	public Composite createEditorComponentComposite(EditorComponent component,List input,Composite parent,IManagedForm managedForm){
		component.setDirty(dirty);
		component.setResource(resource);
		component.setInfo(info);
		component.setInput(input);
		component.setUndoContext(undoContext);
		component.setManagedForm(managedForm);
		getEditableComponent().add(component);
		FormToolkit formToolkit = null;
		if(managedForm != null){
			formToolkit = managedForm.getToolkit();
		}
		return component.create(formToolkit, parent);
	}
	
	public String getHelpContextId() {
		return null;
	}
	
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		if(getHelpContextId() != null){
			PlatformUI.getWorkbench().getHelpSystem().setHelp(getPartControl(), getHelpContextId());
		}
	}
	
	public ImporveControlWithDitryStateContext createContext(Composite client,IManagedForm managedForm){
		return new ImporveControlWithDitryStateContext(
				client,
				getDirtyStatus(),
				managedForm.getToolkit(),
				getUndoContext(),
				managedForm.getMessageManager(),
				info
		);
	}
}
