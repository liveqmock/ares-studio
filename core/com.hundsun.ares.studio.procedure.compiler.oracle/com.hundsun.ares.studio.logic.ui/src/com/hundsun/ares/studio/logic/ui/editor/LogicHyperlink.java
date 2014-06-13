package com.hundsun.ares.studio.logic.ui.editor;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;

public class LogicHyperlink implements IHyperlink {


	private final IAction action;
	private final IRegion region;
	
	public LogicHyperlink(IRegion region, IAction action) {
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
