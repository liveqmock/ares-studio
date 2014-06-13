/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom;

import org.eclipse.core.runtime.IAdapterFactory;

import com.hundsun.ares.studio.atom.reference.AtomReferenceProvider;
import com.hundsun.ares.studio.core.model.IReferenceProvider2;

/**
 * @author liaogc
 *
 */
public class AtomAdapterFactory implements IAdapterFactory {


	private static Class[] CLASSES = new Class[] {IReferenceProvider2.class};
	
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof AtomFunction) {
			if (adapterType == IReferenceProvider2.class) {
				return AtomReferenceProvider.INSTANCE;
			}
		}
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return CLASSES;
	}



}
