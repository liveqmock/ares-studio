/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.util;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * 脏状态控件的上下文
 * @author maxh
 *
 */
public class ControlWithDitryStateContext {
	protected Composite parent;
	protected EditorDirtyStatus dirtyStatus;
	protected FormToolkit toolkit;
	public Composite getParent() {
		return parent;
	}
	public void setParent(Composite parent) {
		this.parent = parent;
	}
	public EditorDirtyStatus getDirtyStatus() {
		return dirtyStatus;
	}
	public void setDirtyStatus(EditorDirtyStatus dirtyStatus) {
		this.dirtyStatus = dirtyStatus;
	}
	public FormToolkit getToolkit() {
		return toolkit;
	}
	public void setToolkit(FormToolkit toolkit) {
		this.toolkit = toolkit;
	}
	public ControlWithDitryStateContext(Composite parent,EditorDirtyStatus dirtyStatus,FormToolkit toolkit) {
		this.parent = parent;
		this.dirtyStatus = dirtyStatus;
		this.toolkit = toolkit;
	}
}
