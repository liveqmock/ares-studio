/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procdure;

import org.eclipse.core.runtime.IAdapterFactory;

import com.hundsun.ares.studio.core.model.IReferenceProvider2;
import com.hundsun.ares.studio.procdure.reference.ProcedureReferenceProvider;

/**
 * @author liaogc
 *
 */
public class ProcedureAdapterFactory implements IAdapterFactory {


	private static Class[] CLASSES = new Class[] {IReferenceProvider2.class};
	
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof Procedure) {
			if (adapterType == IReferenceProvider2.class) {
				return ProcedureReferenceProvider.INSTANCE;
			}
		}
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return CLASSES;
	}



}
