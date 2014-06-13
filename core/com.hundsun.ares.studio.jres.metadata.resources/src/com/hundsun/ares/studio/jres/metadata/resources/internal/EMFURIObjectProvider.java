/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.resources.internal;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IObjectProvider;

/**
 * @author lvgao
 *
 */
public class EMFURIObjectProvider  implements IObjectProvider{
	private Logger logger = Logger.getLogger(EMFURIObjectProvider.class);
	String uri;
	public EMFURIObjectProvider(String uri){
		this.uri = uri;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.reference.IObjectProvider#getObject(com.hundsun.ares.studio.core.IARESResource)
	 */
	@Override
	public Object getObject(IARESResource resource) {
		try {
			EObject eObj = resource.getInfo(EObject.class);
			return eObj.eResource().getEObject(uri);
		} catch (Exception e) {
			logger.error(String.format("引用信息读取资源%s具体info的时候出错...", resource.getElementName()), e);
		}
		return null;
	}

}
