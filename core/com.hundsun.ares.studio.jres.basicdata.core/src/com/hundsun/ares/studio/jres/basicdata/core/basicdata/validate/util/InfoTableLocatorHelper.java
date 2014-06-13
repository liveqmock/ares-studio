/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.logic.util.BasicDataEpackageUtil;

/**
 * @author lvgao
 *
 */
public class InfoTableLocatorHelper  implements IKeyTableLocator{

	InfoTableItemLocator locator;
	EObject resourceInfo;
	EClass eClass;
	EStructuralFeature infoItems;
	
	public InfoTableLocatorHelper(EObject resourceInfo){
		this.resourceInfo = resourceInfo;
		eClass = (EClass)resourceInfo.eClass().getEPackage().getEClassifier(IBasicDataEpacakgeConstant.InfoItem);
		infoItems = resourceInfo.eClass().getEStructuralFeature(IBasicDataEpacakgeConstant.Attr_Info_Items);
		String[] keyWords =  BasicDataEpackageUtil.getMasterKeyAnnotation(eClass);
		locator = new InfoTableItemLocator(keyWords);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.IKeyTableLocator#getLinkObject(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public EObject getLinkObject(EObject obj) throws Exception {
		return locator.getLinkObject(obj);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.IKeyTableLocator#getLinkObjectCount(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public int getLinkObjectCount(EObject obj) throws Exception {
		return locator.getLinkObjectCount(obj);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.IKeyTableLocator#reset()
	 */
	@Override
	public void reset() {
		locator.reset();
		locator.setInput((List<EObject>)resourceInfo.eGet(infoItems));
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.IKeyTableLocator#isReady()
	 */
	@Override
	public boolean isReady() {
		return locator.isReady();
	}
	

}
