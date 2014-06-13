package com.hundsun.ares.studio.jres.metadata.ui.utils;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.model.metadata.FunctionProxy;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelColumnLabelProvider;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;

public class MenuUtils extends com.hundsun.ares.studio.jres.model.metadata.util.MenuUtils{
	/***
	 * 菜单对应的功能列表的扩展列和功能列表的扩张列保持一致
	 */
	public static void createFunctionProxyExtensibleModelColumns(TableViewer viewer, final IARESResource resource, EClass eClass) {
		IExtensibleModelEditingSupport[] editingSupports = 
			ExtensibleModelUtils.getEndabledEditingSupports(resource, eClass);
		GC gc = new GC(viewer.getControl());
		
		try {
			for (final IExtensibleModelEditingSupport support : editingSupports) {
				for (IExtensibleModelPropertyDescriptor descriptor : support.getPropertyDescriptors(resource, eClass)) {
					TableViewerColumn tvColumn = new TableViewerColumn(viewer, SWT.LEFT);
					
					// 长度根据现实的名称类决定
					String displayName = descriptor.getDisplayName();
										
					Point p = gc.stringExtent(displayName);
					
					tvColumn.getColumn().setWidth(p.x + 20);
					tvColumn.getColumn().setText(displayName);
					
					ExtensibleModelColumnLabelProvider provider = new ExtensibleModelColumnLabelProvider(support, descriptor , resource){
						@Override
						protected EObject getOwner(Object element) {
							if(element instanceof FunctionProxy){
								Function func = getFunctionByName(resource, ((FunctionProxy) element).getFunCode());
								return func.getData2().get(support.getKey());
							}
							return super.getOwner(element);
						}
						@Override
						public Color getBackground(Object element) {
							return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
						}
					};
					tvColumn.setLabelProvider(provider);
					
					tvColumn.getColumn().setMoveable(true);
				}

			}
		} finally {
			gc.dispose();
		}
	}
}
