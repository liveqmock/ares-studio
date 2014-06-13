/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.core.basicdata;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EcoreFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;


/**
 * @author liaogc
 *
 */
public class BasicDataEcoreFactory extends  org.eclipse.emf.ecore.impl.EcoreFactoryImpl{
	
	public static final BasicDataEcoreFactory eINSTANCE = init();
	
	  public static BasicDataEcoreFactory init()
	  {
		  /* try
	    {
	      EcoreFactory theEcoreFactory = (EcoreFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/emf/2002/Ecore"); 
	      if (theEcoreFactory != null)
	      {
	        return theEcoreFactory;
	      }
	    }
	    catch (Exception exception)
	    {
	      EcorePlugin.INSTANCE.log(exception);
	    }*/
	    return new BasicDataEcoreFactory();
	  }

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EcoreFactoryImpl#createEAttribute()
	 */
	@Override
	public BasicDataEAttribute createEAttribute() {
		BasicDataEAttribute eAttribute = new BasicDataEAttribute();
	    return eAttribute;
	}

}
