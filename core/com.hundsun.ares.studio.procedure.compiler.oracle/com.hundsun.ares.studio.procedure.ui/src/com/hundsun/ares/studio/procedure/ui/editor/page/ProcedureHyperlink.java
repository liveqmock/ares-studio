package com.hundsun.ares.studio.procedure.ui.editor.page;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;

public class ProcedureHyperlink implements IHyperlink {


	private final IAction action;
	private final IRegion region;
	
	public ProcedureHyperlink(IRegion region, IAction action) {
		this.region = region;
		this.action = action;
	}

	@Override
	public IRegion getHyperlinkRegion() {
		return region;
	}

	@Override
	public String getTypeLabel() {
		return null;
	}

	@Override
	public String getHyperlinkText() {
		return null;
	}

	@Override
	public void open() {
		if(action != null){
			action.run();
		}
	}

}
