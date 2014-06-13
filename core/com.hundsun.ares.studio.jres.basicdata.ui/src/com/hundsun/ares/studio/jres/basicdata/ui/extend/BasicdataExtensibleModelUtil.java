package com.hundsun.ares.studio.jres.basicdata.ui.extend;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.editor.IDiagnosticProvider;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;

public class BasicdataExtensibleModelUtil {

	/**
	 * 创建引用对象的扩展属性列
	 * @param viewer
	 * @param resource
	 * @param eClass
	 * @param diagnosticProvider
	 */
	public static void createRefExtensibleModelTableViewerColumns(
			TableViewer viewer, IARESResource resource, EClass eClass,
			IDiagnosticProvider diagnosticProvider,EStructuralFeature infoReference) {
		IExtensibleModelEditingSupport[] editingSupports = ExtensibleModelUtils
				.getEndabledEditingSupports(resource, eClass);
		GC gc = new GC(viewer.getControl());

		try {
			for (IExtensibleModelEditingSupport support : editingSupports) {
				for (IExtensibleModelPropertyDescriptor descriptor : support
						.getPropertyDescriptors(resource, eClass)) {
					TableViewerColumn tvColumn = new TableViewerColumn(viewer,
							SWT.LEFT);

					// 长度根据现实的名称类决定
					String displayName = descriptor.getDisplayName();

					Point p = gc.stringExtent(displayName);

					tvColumn.getColumn().setWidth(p.x + 20);
					tvColumn.getColumn().setText(displayName);

					RefExtensibleModelColumnLabelProvider provider = new RefExtensibleModelColumnLabelProvider(
							support, descriptor , infoReference);
					provider.setDiagnosticProvider(diagnosticProvider);
					tvColumn.setLabelProvider(provider);
					tvColumn.getColumn().setMoveable(true);
				}

			}
		} finally {
			gc.dispose();
		}
	}
}
