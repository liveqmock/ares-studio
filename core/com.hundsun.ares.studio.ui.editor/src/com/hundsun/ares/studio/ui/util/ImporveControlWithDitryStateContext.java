/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.util;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IMessageManager;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * 加强型带脏状态控件的上下文
 * 支持报错
 * 支持undo redo
 * 支持利用反射直接修改模型值
 * @author maxh
 *
 */
public class ImporveControlWithDitryStateContext extends
		ControlWithDitryStateContext {
	IUndoContext undoContext;
	IMessageManager message;
	Object info;
	
	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	public IUndoContext getUndoContext() {
		return undoContext;
	}
	public void setUndoContext(IUndoContext undoContext) {
		this.undoContext = undoContext;
	}
	public IMessageManager getMessage() {
		return message;
	}
	public void setMessage(IMessageManager message) {
		this.message = message;
	}
	public ImporveControlWithDitryStateContext(Composite parent,EditorDirtyStatus dirtyStatus,FormToolkit toolkit,IUndoContext undoContext,IMessageManager message,Object info) {
		super(parent, dirtyStatus, toolkit);
		this.undoContext = undoContext;
		this.message = message;
		this.info = info;
	}

}
