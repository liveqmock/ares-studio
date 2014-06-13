/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.grid;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.control.IEditable;
import com.hundsun.ares.studio.ui.util.EditorDirtyStatus;



public abstract class EditorComponent<T> implements IEditable{
	
	// 编辑器组件改变了内容时所使用的对象，从创建者处获得
	protected EditorDirtyStatus dirty = null;
	
	// 编辑器组件所关联的数据内容
	protected T input = null;
	
	//编辑器的模型
	protected Object info;
	
	//撤销重做上下文
	protected IUndoContext undoContext;
	
	IManagedForm managedForm;
	
	public void setManagedForm(IManagedForm managedForm) {
		this.managedForm = managedForm;
	}
	
	public IManagedForm getManagedForm() {
		return managedForm;
	}

	public void setUndoContext(IUndoContext undoContext) {
		this.undoContext = undoContext;
	}
	
	public IUndoContext getUndoContext() {
		return undoContext;
	}

	/**
	 * FIXME 换成IARESResurce
	 */
	protected IARESResource resource = null;
	
	public IARESResource getResource() {
		return resource;
	}

	public void setResource(IARESResource resource) {
		this.resource = resource;
	}

	public EditorDirtyStatus getDirty() {
		return dirty;
	}

	public void setDirty(EditorDirtyStatus dirty) {
		this.dirty = dirty;
	}

	public T getInput() {
		return input;
	}

	public void setInput(T input) {
		this.input = input;
	}
	
	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	/**
	 * 判断一个字符串是否为空
	 * 
	 * @param s
	 * @return
	 */
	protected boolean isBlankString(String s) {
		if (s == null || s.trim().equals("")) {
			return true;
		}
		
		return false;
	}
	
	public abstract Composite create(FormToolkit toolkit, Composite parent);
	
	public abstract void dispose();
	
}
