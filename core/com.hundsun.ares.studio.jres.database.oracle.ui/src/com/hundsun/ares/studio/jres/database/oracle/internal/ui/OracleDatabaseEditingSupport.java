/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.database.oracle.internal.ui;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModuleRoot;

/**
 * @author liaogc
 *
 */
public class OracleDatabaseEditingSupport extends OracleTableBaseEditingSupport{
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.ui.OracleExtensibleModelEditingSupport#isEnable(com.hundsun.ares.studio.core.IARESResource, org.eclipse.emf.ecore.EClass)
	 */
	@Override
	public boolean isEnable(IARESElement aresElement, EClass eClass) {
		
		return isDatabaseRoot(aresElement);
	}
	private boolean isDatabaseRoot(IARESElement aresElement){
		while(aresElement!=null && !(aresElement instanceof IARESModuleRoot)){
			aresElement = aresElement.getParent();
		}
		if(aresElement!=null && (aresElement instanceof IARESModuleRoot)){
			return "database".equals(aresElement.getElementName());
		}
		return false;
		
		
	}
	
	


}
