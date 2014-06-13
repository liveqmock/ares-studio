package com.hundsun.ares.studio.jres.basicdata.ui.viewer;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Control;

import com.hundsun.ares.studio.ui.editor.FormControlProblemView;
import com.hundsun.ares.studio.ui.validate.KeyParameter;
import com.hundsun.ares.studio.ui.validate.ProblemPoolChangeEvent;

public class BlockDetailProblemView extends FormControlProblemView {

	StructuredViewer viewer;
	
	public BlockDetailProblemView(KeyParameter key, Control control,StructuredViewer viewer) {
		super(key, control);
		this.viewer = viewer;
	}

	@Override
	public void refresh(ProblemPoolChangeEvent event) {
		super.refresh(event);
		viewer.refresh();
	}
}
