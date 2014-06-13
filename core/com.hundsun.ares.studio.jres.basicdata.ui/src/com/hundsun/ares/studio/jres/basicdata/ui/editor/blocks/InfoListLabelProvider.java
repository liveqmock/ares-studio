package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;

public class InfoListLabelProvider implements ILabelProvider{

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override
	public Image getImage(Object element) {
		return null;
	}

	@Override
	public String getText(Object element) {
		if(element instanceof EObject){
			EObject eobj = (EObject)element;
			EClass eClass =  eobj.eClass();
			String[] keys = BasicDataEpackageUtil.getMasterKeyAnnotation(eClass);
			StringBuffer buffer = new StringBuffer();
			for(String item:keys){
				EStructuralFeature feature = eClass.getEStructuralFeature(item);
				buffer.append(eobj.eGet(feature).toString() + " ");
			}
			return buffer.toString();
		}
		return null;
	}

}
