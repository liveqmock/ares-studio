package com.hundsun.ares.studio.jres.script.util.impl;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.script.api.biz.IBizObjectWrap;
import com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap;
import com.hundsun.ares.studio.jres.script.api.biz.cres.ILogicFunctionWrap;
import com.hundsun.ares.studio.jres.script.api.biz.cres.ILogicModuleWrap;
import com.hundsun.ares.studio.jres.script.api.biz.cres.ILogicServiceWrap;
import com.hundsun.ares.studio.jres.script.api.factory.IScriptPoxyFactory;
import com.hundsun.ares.studio.jres.script.api.wrap.IARESModuleWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IBizModuleWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IObjectModuleWrap;
import com.hundsun.ares.studio.jres.script.wrap.ModelScriptWrapManager;

public class ARESModuleWrap implements IARESModuleWrap, IObjectModuleWrap, IBizModuleWrap, ILogicModuleWrap{

	private IARESModule module;
	IBizModuleWrap bizProxy;
	IObjectModuleWrap objProxy;
	ILogicModuleWrap logicProxy;
	
	public ARESModuleWrap(IARESModule module) {
		this.module = module;
		
		IScriptPoxyFactory factory = ModelScriptWrapManager.getInstance().getScriptPoxyFactory("jres.biz");
		if (factory != null) {
			bizProxy = (IBizModuleWrap) factory.createModuleProxy(module);
		}
		
		factory =  ModelScriptWrapManager.getInstance().getScriptPoxyFactory("jres.obj");
		if (factory != null) {
			objProxy = (IObjectModuleWrap) factory.createModuleProxy(module);
		}
		
		factory = ModelScriptWrapManager.getInstance().getScriptPoxyFactory("cres.logic");
		if (factory != null) {
			logicProxy = (ILogicModuleWrap) factory.createModuleProxy(module);
		}
	}
	
	@Override
	public String getName() {
		return this.module.getShortName();
	}

	@Override
	public String getFullyQualifiedName() {
		return this.module.getElementName();
	}

	@Override
	public String getCName() {
		IARESResource pro = module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
		if (pro != null && pro.exists()) {
			try {
				ModuleProperty property = pro.getInfo(ModuleProperty.class);
				if (property != null) {
					String cname = property.getString(ICommonModel.CNAME);
					return cname == null ? StringUtils.EMPTY : cname;
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		return StringUtils.EMPTY;
	}

	@Override
	public IARESModuleWrap getParent() {
		IARESModule parentModule = module.getParentModule();
		return new ARESModuleWrap(parentModule);
	}	

//	@Override
//	public IARESModuleWrap[] getSubModules() {
//		for (IARESModule sub : module.get)
//		return null;
//	}

	@Override
	public IARESModuleWrap getTopModule() {
		if (ARESElementUtil.isTopModule(module))
			return this;
		
		IARESModule top = null;
		while ((top = module.getParentModule()) != null) {
			if (ARESElementUtil.isTopModule(top))
				break;
		}
		if (top != null)
			return new ARESModuleWrap(top);
		
		// should not happen
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ARESModuleWrap other = (ARESModuleWrap) obj;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		return true;
	}

	@Override
	public IBizServiceWrap[] getServices(boolean recursive) {
		if (bizProxy != null) {
			return bizProxy.getServices(recursive);
		}
		return new IBizServiceWrap[0];
	}

	@Override
	public IBizObjectWrap[] getObjects(boolean recursive) {
		if (objProxy != null) {
			return objProxy.getObjects(recursive);
		}
		return new IBizObjectWrap[0];
	}

	@Override
	public ILogicServiceWrap[] getLogicServices(boolean recursive) {
		if (logicProxy != null) {
			return logicProxy.getLogicServices(recursive);
		}
		return null;
	}

	@Override
	public ILogicFunctionWrap[] getLogicFunctions(boolean recursive) {
		if (logicProxy != null)
			return logicProxy.getLogicFunctions(recursive);
		return null;
	}
}
