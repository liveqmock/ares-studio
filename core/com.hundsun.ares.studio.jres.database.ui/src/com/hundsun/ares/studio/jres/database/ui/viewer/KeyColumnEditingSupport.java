package com.hundsun.ares.studio.jres.database.ui.viewer;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.ui.editors.celleditor.TableKeyColumnsCellEditor;
import com.hundsun.ares.studio.ui.editor.editingsupport.EMFEditingSupport;

public class KeyColumnEditingSupport extends EMFEditingSupport {

	IARESResource resource;
	
	/**
	 * @param viewer
	 * @param feature
	 * @param resource 
	 */
	public KeyColumnEditingSupport(ColumnViewer viewer,
			EStructuralFeature feature, IARESResource resource) {
		super(viewer, feature);
		this.resource = resource;
	}

	@Override
	protected CellEditor createCellEditor() {
		return new TableKeyColumnsCellEditor(getViewer(),resource);
	}

}
