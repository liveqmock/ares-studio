package com.hundsun.ares.studio.jres.obj.ui;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.StandardObjField;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.biz.core.ObjectRefTypes;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.editor.blocks.DisplayItem;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

public class StdObjFieldColumnLabelProvider extends EObjectColumnLabelProvider{

	IARESProject project;
	public StdObjFieldColumnLabelProvider(EStructuralFeature attribute, IARESProject project) {
		super(attribute);
		this.project = project;
	}

	@Override
	public Color getBackground(Object element) {
		if (element instanceof DisplayItem)
			return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
		
		if (element instanceof StandardObjField) {
			EStructuralFeature feature = getEStructuralFeature(element);
			if (MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME.equals(feature)
					|| MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION.equals(feature)) {
				return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
			}
		}
		return null;
	}
	
	@Override
	public String getText(Object element) {
		EStructuralFeature feature = getEStructuralFeature(element);
		if (element instanceof StandardObjField) {
		    StandardObjField field = (StandardObjField) element;
			ARESObject obj = getObj(element);
			if (obj == null)
			    return StringUtils.EMPTY;
			
			if (MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME.equals(feature)) {
			    return obj.getChineseName();
			} else if (MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION.equals(feature)) {
			    return obj.getDescription();
			} else if (BizPackage.Literals.STANDARD_OBJ_FIELD__TYPE.equals(feature)) {
			    String type = field.getType();
			    if (StringUtils.contains(type, '.')) {
				return StringUtils.substringAfterLast(type, ".");
			    }
			}
		}
		return super.getText(element);
	}
	
	protected ARESObject getObj(Object element) {
		if (element instanceof StandardObjField) {
			StandardObjField field = (StandardObjField) element;
			if (field.getType() != null) {
				List<String> refTypes = ObjectRefTypes.getRefTypes();
				for (String refType : refTypes) {
					ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, refType, field.getType(), true);
					if (ref != null) {
						Object refObject = ref.getObject();
						if (refObject instanceof ARESObject) {
							return (ARESObject) ref.getObject();
						}
					}
				}
			}
		}
		return null;
	}
	
	@Override
	protected Diagnostic getDiagnostic(Object element) {
		if (element instanceof DisplayItem) {
			return null;
		}
		
		EObject owner = getOwner(element);
		// 引用不需要检查错误
		if (owner == element) {
			return super.getDiagnostic(element);
		}
		return null;
	}
	
	protected EObject getOwner(Object element) {
		if (element instanceof DisplayItem) {
			return ((DisplayItem) element).eObject;
		}
		return (EObject) element;
	}
}
