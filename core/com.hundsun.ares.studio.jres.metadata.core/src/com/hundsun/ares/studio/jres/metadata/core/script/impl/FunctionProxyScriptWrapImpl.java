/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.model.metadata.FunctionProxy;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MenuList;
import com.hundsun.ares.studio.jres.script.api.metadata.IFunctionProxyScriptWrap;
import com.hundsun.ares.studio.jres.script.api.metadata.IMenuFunctionScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class FunctionProxyScriptWrapImpl extends CommonScriptWrap<FunctionProxy> implements IFunctionProxyScriptWrap {

	public FunctionProxyScriptWrapImpl(FunctionProxy fp ,IARESResource resource) {
		super(fp ,resource);
	}

	@Override
	public String getFunCode() {
		return getOriginalInfo().getFunCode();
	}

	@Override
	public String getMenuId() {
		if (getOriginalInfo() != null && getOriginalInfo().eContainer() != null) {
			return ((MenuItem)getOriginalInfo().eContainer()).getName();
		}
		return null;
	}

	@Override
	public IMenuFunctionScriptWrap getFunctions() {
		if (getOriginalInfo() != null && getOriginalInfo().eContainer() instanceof MenuItem && getOriginalInfo().eContainer().eContainer() instanceof MenuList) {
			 EList<Function> funcs = ((MenuList)getOriginalInfo().eContainer().eContainer()).getFunctions();
			 for(Function func : funcs){
				 if (StringUtils.equals(func.getName(), getOriginalInfo().getFunCode())) {
					return new MenuFunctionScriptWrapImpl(func, resource);
				}
			 }
		}
		return null;
	}
	
}
