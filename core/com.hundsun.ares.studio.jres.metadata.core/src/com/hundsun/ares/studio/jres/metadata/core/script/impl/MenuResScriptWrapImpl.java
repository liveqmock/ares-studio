/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.model.metadata.FunctionProxy;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MenuList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.script.api.metadata.IFunctionProxyScriptWrap;
import com.hundsun.ares.studio.jres.script.api.metadata.IMenuFunctionScriptWrap;
import com.hundsun.ares.studio.jres.script.api.metadata.IMenuItemScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMenuResScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class MenuResScriptWrapImpl extends MetadataResScriptWrapImpl implements IMenuResScriptWrap {
	
	private static Logger logger = Logger.getLogger(MenuResScriptWrapImpl.class);

	public MenuResScriptWrapImpl(MenuList metadata, IARESResource resource, boolean includeRequiredBundles) {
		super(metadata, resource, includeRequiredBundles);
	}

	public IARESResource getResource(){
		return resource;
	}
	
	@Override
	public MenuList getOriginalInfo() {
		return (MenuList) super.getOriginalInfo();
	}
	
	@Override
	public IMenuItemScriptWrap[] getItems() {
		List<IMetadataItemScriptWrap> items = new ArrayList<IMetadataItemScriptWrap>();
		// 2014-3-28 sundl 修改增加了处理引用工程的情况，为了方便统一在父类里处理，所以这里改为调用super.getItems()
		for (IMetadataItemScriptWrap item : super.getItems()) {
			MetadataItemScriptWrapImpl metadataItem = (MetadataItemScriptWrapImpl) item;
			MenuItem menuItem = (MenuItem) metadataItem.getOriginalInfo();
			items.add(new MenuItemScriptWrapImpl(menuItem, metadataItem.getResource()));
		}
		return items.toArray(new IMenuItemScriptWrap[0]);
	}
	
	@Override
	public IMetadataItemScriptWrap[] getNotCateItems() {
		List<IMetadataItemScriptWrap> items = new ArrayList<IMetadataItemScriptWrap>();
		return items.toArray(new IMetadataItemScriptWrap[0]);
	}
	
	/**
	 * 获取功能号
	 * 
	 * @return
	 */
	public IMenuFunctionScriptWrap[] getFunctions(){
		List<IMenuFunctionScriptWrap> items = new ArrayList<IMenuFunctionScriptWrap>();
		for(Function fun : getOriginalInfo().getFunctions()){
			items.add(new MenuFunctionScriptWrapImpl(fun, resource));
		}
		
		if (includeRequiredBundles) {
			IARESBundle[] bundles = ARESElementUtil.getRefARESProjects(resource.getARESProject());
			for (IARESBundle bundle : bundles) {
				try {
					IARESResource refRes = bundle.findResource(resource.getName(), resource.getType());
					// getRefARESProjects()方法返回的包含了自身
					if (refRes != null && !refRes.equals(resource)) {
						MenuList info = (MenuList) refRes.getInfo(MenuList.class);
						for(Function item : info.getFunctions()){
							items.add(new MenuFunctionScriptWrapImpl(item, refRes));
						}
					}
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}
		
		return items.toArray(new IMenuFunctionScriptWrap[0]);
	}
	
	/**
	 * 获取菜单与功能对应对象
	 * 
	 * @return
	 */
	public IFunctionProxyScriptWrap[] getFunctionProxys(){
		List<IFunctionProxyScriptWrap> items = new ArrayList<IFunctionProxyScriptWrap>();
		List<FunctionProxy> functionProxys = new ArrayList<FunctionProxy>();
		getAllFunctionProxy(getOriginalInfo().getItems(), functionProxys);
		for(FunctionProxy fun : functionProxys){
			items.add(new FunctionProxyScriptWrapImpl(fun, resource));
		}
		return items.toArray(new IFunctionProxyScriptWrap[0]);
	}
	
	public IMenuFunctionScriptWrap addFunction(){
		Function fun = MetadataFactory.eINSTANCE.createFunction();
		getOriginalInfo().getFunctions().add(fun);
		save();
		return new MenuFunctionScriptWrapImpl(fun, resource);
	}
	
	private void getAllFunctionProxy(EList<MenuItem> items,List<FunctionProxy> fps){
		
		for(MenuItem item : items){
			fps.addAll(item.getFunctionProxys());
			if (item.getSubItems().size() > 0) {
				getAllFunctionProxy(item.getSubItems() ,fps);
			}
		}
	}
	
	private void getAllMenuItem(EList<MenuItem> items,List<MenuItem> itemTotal){
		
		for(MenuItem item : items){
			itemTotal.add(item);
			if (item.getSubItems().size() > 0) {
				getAllMenuItem(item.getSubItems() ,itemTotal);
			}
		}
	}
	
}
