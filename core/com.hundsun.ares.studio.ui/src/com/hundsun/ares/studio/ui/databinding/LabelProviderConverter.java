/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.databinding;

import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.jface.viewers.ILabelProvider;

/**
 * 
 * @author gongyf
 */
public class LabelProviderConverter implements IConverter {

	ILabelProvider provider;
	
	/**
	 * @param provider
	 */
	public LabelProviderConverter(ILabelProvider provider) {
		super();
		this.provider = provider;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#convert(java.lang.Object)
	 */
	public Object convert(Object fromObject) {
		return provider.getText(fromObject);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#getFromType()
	 */
	public Object getFromType() {
		return Object.class;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#getToType()
	 */
	public Object getToType() {
		return String.class;
	}

}
