package com.hundsun.ares.studio.ui.action;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.util.ARESUIUtil;

public abstract class LinkOpenResourceAction extends Action{

	private IARESProject project;
	private String refType;
	
	public IARESProject getProject() {
		return project;
	}

	public void setProject(IARESProject project) {
		this.project = project;
	}
	
	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	@Override
	public void runWithEvent(Event event) {
		if (project == null || refType == null)
			return;
		
		Item item = null;
		if (event.widget instanceof Tree) {
			item = ((Tree) event.widget).getItem(new Point(event.x, event.y));
		} else if (event.widget instanceof Table) {
			item = ((Table) event.widget).getItem(new Point(event.x, event.y));
		}
		
		if (item == null)
			return;
		
		String refText = getRefText(item);
		if (!StringUtils.isEmpty(refText)) {
			
			ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, refType, refText, true);
			if (ref == null)
				return;
			
			IARESResource resource = ref.getResource();
			ARESUIUtil.openResource(resource);
		}
	}
	
	protected abstract String getRefText(Item item);
	
}
