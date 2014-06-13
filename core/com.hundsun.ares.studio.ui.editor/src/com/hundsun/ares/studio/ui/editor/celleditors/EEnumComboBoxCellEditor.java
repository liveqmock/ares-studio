/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.editor.celleditors;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author gongyf
 *
 */
public class EEnumComboBoxCellEditor extends ComboBoxCellEditor {
	
	private List<Enumerator> enumerators;
	
	/**
	 * @param parent
	 */
	public EEnumComboBoxCellEditor(Composite parent) {
		super(parent, ArrayUtils.EMPTY_STRING_ARRAY, SWT.READ_ONLY);
	}

	/**
	 * @param eEnum the eEnum to set
	 */
	public void setEEnum(EEnum eEnum, ILabelProvider itemLabelProvider) {
		
		enumerators = new ArrayList<Enumerator>();
		List<String> lables = new ArrayList<String>();
		for (EEnumLiteral eEnumLiteral : eEnum.getELiterals()) {
			Enumerator e = eEnumLiteral.getInstance();
			enumerators.add(e);
			lables.add(itemLabelProvider.getText(e));
		}
		
		setItems(lables.toArray(new String[lables.size()]));
		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ComboBoxCellEditor#doGetValue()
	 */
	@Override
	protected Object doGetValue() {
		int index = (Integer) super.doGetValue();
		
		return enumerators.get(index);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ComboBoxCellEditor#doSetValue(java.lang.Object)
	 */
	@Override
	protected void doSetValue(Object value) {
		if (value instanceof Enumerator) {
			int index = enumerators.indexOf(value);
			if (index < 0) {
				index = 0;
			}
			value = index;
		}
		super.doSetValue(value);
	}
}
