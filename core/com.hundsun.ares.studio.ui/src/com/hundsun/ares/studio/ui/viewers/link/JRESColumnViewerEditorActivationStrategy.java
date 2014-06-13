package com.hundsun.ares.studio.ui.viewers.link;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;

import com.hundsun.ares.studio.ui.ARESUI;

/**
 * @author gongyf
 *
 */
public class JRESColumnViewerEditorActivationStrategy extends
		ColumnViewerEditorActivationStrategy {
	/**
	 * @param viewer
	 */
	public JRESColumnViewerEditorActivationStrategy(ColumnViewer viewer) {
		super(viewer);
	}

	protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
		if(StringUtils.equals(ARESUI.getCellEdiorActiveMode(), ARESUI.PRE_CELLEDITOR_ACTIVE_MODE_DOUBLECLICK)){
			return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL || 
			event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION || 
			event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC;
		}else{
			return event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL || 
			event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION || 
			event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC;
		}
	}
}