/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.control;


import org.eclipse.core.resources.IProject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.ui.util.EditorDirtyStatus;



/**
 * @author 带脏状态的控件
 *
 * @param <T>
 */
public abstract class ControlWithDitryState<T extends Control> implements IEditable {

	/**
	 * 控件脏状态。
	 */
	protected EditorDirtyStatus dirty;

	protected FormToolkit toolkit;

	protected Composite parent;

	/**
	 * 可编辑型控件。
	 */
	protected T control;

	protected int controlStyle;
	

	/**
	 * 
	 * @param parent
	 * @param dirtyStatus
	 */
	public ControlWithDitryState(Composite parent, int controlStyle, EditorDirtyStatus dirtyStatus) {
		this.dirty = dirtyStatus;
		this.parent = parent;
		this.controlStyle = controlStyle;

		this.control = this.initControl();
		this.initControlSize();
	}

	/**
	 * 设置空间长度
	 * 
	 * @return
	 */
	protected void initControlSize() {
	};

	
	public ControlWithDitryState(FormToolkit toolkit, Composite parent, int controlStyle, EditorDirtyStatus dirtyStatus) {
		this.dirty = dirtyStatus;
		this.parent = parent;
		this.controlStyle = controlStyle;
		this.toolkit = toolkit;
		this.control = this.initControl();
		this.initControlSize();
	}


	/**
	 * 添加修改事件监听器
	 */
	public abstract void addModifyListener();
	
	/**
	 * 删除修改事件监听器
	 */
	public abstract void removeModifyListener();

	/**
	 * 添加修改事件监听器
	 */
	protected abstract void addFocusListener();

	/**
	 * 添加鼠标监听器
	 */
	protected abstract void addMouseListener();

	/**
	 * 在构造时会调用初始化方法，子类必须在该方法中实例化并返回一个输入型控件。
	 * 
	 * @param items
	 * 
	 * @return
	 */
	protected abstract T initControl();

	protected T initControlByArray(String[] items,String[] infos) {
		return control;
	};

	/**
	 * @param project
	 * @param filtra 
	 * @return
	 */
	protected T initControlByProject(IProject project, String filtra) {
		return control;
	};

	/**
	 * 获取控件值。例如Text型控件应返回Text.getText()的值。
	 * 
	 * @return
	 */
	public abstract Object getValue();
	
	/**
	 * 设置控件值。例如Text型控件应返回Text.getText()的值。
	 * 
	 * @return
	 */
	public abstract void setValue(Object object);

	/**
	 * 当控件值发生变化时，需要更新对应的模型值。
	 * 
	 */
	public abstract void syncModel();

	/**
	 * 为控件设置模型值。
	 */
	public abstract void syncControl();

	/**
	 * @return the control
	 */
	public T getControl() {
		return control;
	}

	/**
	 * @param control
	 *            the control to set
	 */
	public void setControl(T control) {
		this.control = control;
	}

	/**
	 * 设置控件的页面布局。
	 * 
	 * @param layoutData
	 */
	public void setControlLayoutData(Object layoutData) {
		if (null != this.control) {
			this.control.setLayoutData(layoutData);
		}
	}

	/**
	 * @return the dirty
	 */
	public EditorDirtyStatus getDirty() {
		return dirty;
	}

	/**
	 * @param dirty
	 *            the dirty to set
	 */
	public void setDirty(EditorDirtyStatus dirty) {
		this.dirty = dirty;
	}

	/**
	 * 对控件进行自定义调整。默认不做任何行为，子类可以覆盖该方法，对控件进行自定义调整。 由子类决定调用时机。
	 */
	protected void adjustControl() {
	}

	/**
	 * 若传入参数为null，则返回空字符串，否则返回原字符串。
	 * 
	 * @param maybeNull
	 *            可能为null的字符串。
	 * @return 非null字符串。
	 */
	public static String wrapString(String maybeNull) {
		return (null == maybeNull || "null".equals(maybeNull)) ? "" : maybeNull;
	}

	public void setEditable(boolean editable) {
		this.control.setEnabled(editable);
	}
	
	public void refresh() {
		removeModifyListener();
		syncControl();
		addModifyListener();
	}
	
	public void setHelpContextId(String id){
		if(id != null){
			PlatformUI.getWorkbench().getHelpSystem().setHelp(control, id);
		}
	}
}
