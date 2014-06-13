/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.FunctionProxy;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.script.api.metadata.IFunctionProxyScriptWrap;
import com.hundsun.ares.studio.jres.script.api.metadata.IMenuItemScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class MenuItemScriptWrapImpl extends CommonScriptWrap<MenuItem> implements IMenuItemScriptWrap {

	public MenuItemScriptWrapImpl(MenuItem item ,IARESResource resource) {
		super(item ,resource);
	}

	public String getMenuTitle(){
		return getOriginalInfo().getChineseName();
	}
	
	public String getMenuId(){
		return getOriginalInfo().getName();
	}
	
	public String getUrl(){
		return getOriginalInfo().getUrl();
	}
	
	@Override
	public String getChineseName() {
		return StringUtils.EMPTY;
	}
	
	@Override
	public String getName() {
		return StringUtils.EMPTY;
	}
	
	public void setMenuTitle(String menuTitle){
		getOriginalInfo().setChineseName(menuTitle);
	}
	
	public void setMenuId(String menuId){
		getOriginalInfo().setName(menuId);
	}
	
	public IFunctionProxyScriptWrap[] getFunctionProxys(){
		List<IFunctionProxyScriptWrap> items = new ArrayList<IFunctionProxyScriptWrap>();
		for(FunctionProxy funp : getOriginalInfo().getFunctionProxys()){
			items.add(new FunctionProxyScriptWrapImpl(funp, resource));
		}
		return items.toArray(new IFunctionProxyScriptWrap[0]);
	}
	
	@Override
	public IMenuItemScriptWrap[] getSubItems() {
		List<IMetadataItemScriptWrap> items = new ArrayList<IMetadataItemScriptWrap>();
		for(MenuItem item : getOriginalInfo().getSubItems()){
			items.add(new MenuItemScriptWrapImpl(item, resource));
		}
		return items.toArray(new IMenuItemScriptWrap[0]);
	}
	
	@Override
	public String getDescription() {
		return getOriginalInfo().getDescription();
	}
	
}
