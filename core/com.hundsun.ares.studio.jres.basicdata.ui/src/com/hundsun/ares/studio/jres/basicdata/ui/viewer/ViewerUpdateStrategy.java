package com.hundsun.ares.studio.jres.basicdata.ui.viewer;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.jface.viewers.StructuredViewer;


public class ViewerUpdateStrategy extends UpdateValueStrategy {

	StructuredViewer viewer;
	Object info;
	
	
	public ViewerUpdateStrategy(StructuredViewer viewer){
		this.viewer = viewer;
	}
	
	public ViewerUpdateStrategy(StructuredViewer viewer,Object info){
		this.info = info;
		this.viewer = viewer;
	}
	@Override
	public Object convert(Object value) {
		if(null != info){
			viewer.refresh(info);
		}else{
			viewer.refresh();
		}
		return super.convert(value);
	}
}
