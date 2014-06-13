package com.hundsun.ares.studio.jres.obj.ui;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;

import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.StandardObjField;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.editingsupport.IEditingSupportDecorator;

public class StdObjFieldEditingSupportDecorator implements IEditingSupportDecorator {

    private EStructuralFeature feature;
    private IARESResource resource;
    
    public StdObjFieldEditingSupportDecorator(EStructuralFeature feature, IARESResource resource) {
	this.feature = feature;
	this.resource = resource;
    }
    
    @Override
    public CellEditor decorateGetCellEditor(CellEditor cellEditor, Object element) {
	return cellEditor;
    }

    @Override
    public boolean decorateCanEdit(boolean canEdit, Object element) {
	if (element instanceof StandardObjField) {
	    if (MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME.equals(feature)
		    || MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION.equals(feature)) {
		return false;
	    }
	}
	return false;
    }

}
