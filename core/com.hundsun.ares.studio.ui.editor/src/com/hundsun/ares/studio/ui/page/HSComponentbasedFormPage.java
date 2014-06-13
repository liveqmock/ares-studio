/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.page;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;

import com.hundsun.ares.studio.ui.control.IEditable;
import com.hundsun.ares.studio.ui.grid.GridViewerExComponent;
/**
 * @author maxh
 *
 */
public abstract class HSComponentbasedFormPage extends FormPage implements IEditable{

	/**
	 * 可设置只读状态的控件列表
	 */
	private boolean editable = true;
	
	public HSComponentbasedFormPage(String id, String title) {
		super(id, title);
	}
	
	public HSComponentbasedFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}
	
	List<IEditable> controls = new ArrayList<IEditable>();
	
	public List<IEditable> getEditableComponent(){
		return controls;
	}	
	
	public void updateEditableState() {
		for(IEditable e : getEditableComponent()) {
			if (e != null)
				e.setEditable(editable);
		}
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
		updateEditableState();
	}
	
	public boolean isEditable() {
		return this.editable;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void dispose() {
		List<IEditable> editable = getEditableComponent();
		if (editable != null) {
			for(IEditable e : editable) {
				if(e instanceof GridViewerExComponent){
					((GridViewerExComponent)e).dispose();
				}
			}
		}
		super.dispose();
	}
	
	public void refresh() {
		Display.getDefault().asyncExec(new Runnable(){

			public void run() {
				for (IEditable control : controls) {
					control.refresh();
				}
			}});
		
	}
	
}
