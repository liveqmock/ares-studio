package com.hundsun.ares.studio.ui.control;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;

public class CheckButtonAdaptor  extends ControlWithShowControl<Button> {

	SelectionAdapter selectionAdapter;
	
	public CheckButtonAdaptor(String label, int controlStyle,
			ImporveControlWithDitryStateContext context) {
		super(label, controlStyle|SWT.CHECK, context);
	}
	
	public CheckButtonAdaptor(String label, int controlStyle,
			ImporveControlWithDitryStateContext context,String field) {
		super(label, controlStyle|SWT.CHECK, context,field);
	}

	@Override
	public void addModifyListener() {
		if (null != this.control) {
			if(selectionAdapter == null){
				selectionAdapter = new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						fireControlValueChange();
					}
				};
			}
			this.control.addSelectionListener(selectionAdapter);
		}
	}

	@Override
	protected void addMouseListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getValue() {
		return getControl().getSelection();
	}

	@Override
	protected Button initControl() {
		Button button = new Button(parent, controlStyle); 
		return button;
	}

	@Override
	public void removeModifyListener() {
		if (null != this.control) {
			this.control.removeSelectionListener(selectionAdapter);
		}
	}

	@Override
	public void setValue(Object object) {
		getControl().setSelection(Boolean.valueOf(object.toString()));
	}

}
