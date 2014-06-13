/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.extendpoint;

import org.eclipse.core.resources.IResource;

import com.hundsun.ares.studio.ui.control.IEditable;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;


public abstract class PageExtendPointControlProvider {
	
	String showName;
	String bindName;
	ImporveControlWithDitryStateContext context;
	Object info;
	/**
	 * FIXME 换成IARESResurce
	 */
	IResource resource;
	
	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getBindName() {
		return bindName;
	}

	public void setBindName(String bindName) {
		this.bindName = bindName;
	}

	public ImporveControlWithDitryStateContext getContext() {
		return context;
	}


	public void setContext(ImporveControlWithDitryStateContext context) {
		this.context = context;
	}

	public Object getInfo() {
		return info;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public IResource getResource() {
		return resource;
	}

	public void setResource(IResource resource) {
		this.resource = resource;
	}

	public abstract IEditable getControl();
}
