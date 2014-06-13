/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.adapters;

import org.eclipse.core.runtime.IAdapterFactory;

import com.hundsun.ares.studio.atom.AtomFactory;
import com.hundsun.ares.studio.atom.InternalParam;
import com.hundsun.ares.studio.biz.ParamType;
//import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;

/**
 * @author wangxh
 *
 */
public class StandardFieldAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof StandardField) {
			
			StandardField sf = (StandardField) adaptableObject;
			
			if (adapterType.equals(InternalParam.class)) {
				InternalParam param = AtomFactory.eINSTANCE.createInternalParam();
				param.setId(sf.getName());
				param.setParamType(ParamType.STD_FIELD);
				return param;
			}
		}
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[]{ 
				InternalParam.class
		};
	}

}
