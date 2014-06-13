/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.extend;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.model.Constants;

/**
 * 
 * @author qinyuan
 */
public class ComboMapEMPropertyDescriptor extends AbstractMapEMPropertyDescriptor {

	private String[] items;
	
	/**
	 * @param structuralFeature
	 * @param key
	 */
	public ComboMapEMPropertyDescriptor(String[] items,EStructuralFeature structuralFeature,
			Object key) {
		this(items,structuralFeature, key,Constants.USER_DATA2_KEY);
	}
	public ComboMapEMPropertyDescriptor(String[] items,EStructuralFeature structuralFeature,
			Object key,String extendModelKey) {
		super(structuralFeature, key,extendModelKey);
		this.items = items;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor#getLabelProvider()
	 */
	@Override
	public ILabelProvider getLabelProvider() {
		return new ExtendsLabelProvider((String)getKey(),getStructuralFeature(),getExtendModelKey());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		return new ComboBoxCellEditor(parent, items, SWT.READ_ONLY){
			/* (non-Javadoc)
			 * @see org.eclipse.jface.viewers.ComboBoxCellEditor#doGetValue()
			 */
			@Override
			protected Object doGetValue() {
				int index = (Integer) super.doGetValue();
				if(index < 0) {
					return StringUtils.EMPTY;
				}
				return items[index];
			}
			
			/* (non-Javadoc)
			 * @see org.eclipse.jface.viewers.ComboBoxCellEditor#doSetValue(java.lang.Object)
			 */
			@Override
			protected void doSetValue(Object value) {
				int index = 0;
				if (value instanceof String) {
					for (int i = 0; i < items.length; i++) {
						if(StringUtils.equals((String)value, items[i])){
							index = i;
							break;
						}
					}
				}
				super.doSetValue(index);
			}
		};
	}

}
