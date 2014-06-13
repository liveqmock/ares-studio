package com.hundsun.ares.studio.jres.clearinghouse.ui.page;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.database.ui.editors.celleditor.TableKeyColumnsCellEditor;
import com.hundsun.ares.studio.jres.model.chouse.AddTableModification;
import com.hundsun.ares.studio.jres.model.chouse.Modification;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.ui.editor.editingsupport.EMFEditingSupport;

public class KeyColumnModificationEditingSupport extends EMFEditingSupport {

	IARESResource resource;
	Modification modification;
	
	/**
	 * @param viewer
	 * @param feature
	 * @param resource 
	 */
	public KeyColumnModificationEditingSupport(ColumnViewer viewer,
			EStructuralFeature feature, IARESResource resource ,Modification modification) {
		super(viewer, feature);
		this.resource = resource;
		this.modification = modification;
	}

	@Override
	protected CellEditor createCellEditor() {
		return new TableKeyColumnsCellEditor(getViewer(),resource){
			@Override
			protected List<TableColumn> getCellEditorInput() {
				
				if (modification instanceof AddTableModification) {
					return ((AddTableModification) modification).getColumns();
				}
				return new ArrayList<TableColumn>();
			}
		};
	}

}
