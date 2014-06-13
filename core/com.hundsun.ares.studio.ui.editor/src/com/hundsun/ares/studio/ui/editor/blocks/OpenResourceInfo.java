/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * 
 * @author yanwj06282
 */
public class OpenResourceInfo {

	private IARESResource resource;
	private EObject obj;
	
	public OpenResourceInfo(IARESResource resource ,EObject obj) {
		this.resource = resource;
		this.obj = obj;
	}
	
	public IARESResource getResource() {
		return resource;
	}
	public void setResource(IARESResource resource) {
		this.resource = resource;
	}
	public EObject getObj() {
		return obj;
	}
	public void setObj(EObject obj) {
		this.obj = obj;
	}
	
}
