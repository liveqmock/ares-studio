package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.registry.IModuleRootDescriptor;
import com.hundsun.ares.studio.core.registry.ModulesRootTypeRegistry;
import com.hundsun.ares.studio.ui.CommonElementContentProvider;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;

public class IDRangeHeaderLabelProvider extends ColumnLabelProvider {
	
	CommonElementLabelProvider lb;
	
	public IDRangeHeaderLabelProvider() {
		lb = new CommonElementLabelProvider(new CommonElementContentProvider());
	}
	
	@Override
	public String getText(Object element) {
		if(element instanceof IARESModule){
			IARESModule module = (IARESModule)element;
			if(module.getElementName().indexOf('.') == -1){
				return getRootCName(module.getRoot()) + "/" + lb.getText(element);
			}
		}
		return lb.getText(element);
	}
	
	private String getRootCName(IARESModuleRoot root){
		String type = root.getType();
		IModuleRootDescriptor rootDesc = ModulesRootTypeRegistry.getInstance().getModuleRootDescriptor(type);
		if (rootDesc != null && !StringUtils.isEmpty(rootDesc.getName()))
			return rootDesc.getName();
		return root.getElementName();
	}
	
	@Override
	public Image getImage(Object element) {
		return lb.getImage(element);
	}
	
	@Override
	public Color getBackground(Object element) {
		return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
	}
}
