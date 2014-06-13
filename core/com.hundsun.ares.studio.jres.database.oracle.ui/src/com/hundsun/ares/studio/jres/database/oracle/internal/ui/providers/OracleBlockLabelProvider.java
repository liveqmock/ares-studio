/**
 * 
 */
package com.hundsun.ares.studio.jres.database.oracle.internal.ui.providers;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * @author yanwj06282
 *
 */
public class OracleBlockLabelProvider extends EObjectColumnLabelProvider {

	private IARESResource resource;
	
	public OracleBlockLabelProvider(EStructuralFeature attribute , IARESResource resource) {
		super(attribute);
		this.resource = resource;
	}

	@Override
	public Color getBackground(Object element) {
		if (resource.isReadOnly()) {
			return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
		}
		return super.getBackground(element);
	}
	
}
