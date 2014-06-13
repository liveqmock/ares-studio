package com.hundsun.ares.studio.jres.script.util.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.script.api.wrap.IARESModuleRootWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IARESModuleWrap;

public class ARESModuleRootWrap implements IARESModuleRootWrap {

	private IARESModuleRoot root;
	
	public ARESModuleRootWrap(IARESModuleRoot root) {
		this.root = root;
	}
	
	@Override
	public IARESModuleWrap[] getSubSystems() {
		List<IARESModuleWrap> subSystems = new ArrayList<IARESModuleWrap>();
		try {
			for (IARESElement element : root.getChildren()) {
				if (element instanceof IARESModule) {
					IARESModule module = (IARESModule) element;
					String elementName = module.getElementName();
					if (StringUtils.isEmpty(elementName))
						continue;
					
					if (ARESElementUtil.isTopModule(module)) {
						subSystems.add(new ARESModuleWrap(module));
					}
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return subSystems.toArray(new IARESModuleWrap[0]);
	}

	@Override
	public IARESModuleWrap getSubModuleByName(String moduleName) {
		try {
			for (IARESElement element : root.getChildren()) {
				if (element instanceof IARESModule) {
					IARESModule module = (IARESModule) element;
					String elementName = module.getElementName();
					if (StringUtils.isEmpty(elementName))
						continue;
					
					if (StringUtils.equals(module.getElementName(), moduleName)) {
						return new ARESModuleWrap(module);
					}
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
