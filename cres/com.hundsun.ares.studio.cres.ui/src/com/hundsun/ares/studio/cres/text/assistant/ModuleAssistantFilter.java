package com.hundsun.ares.studio.cres.text.assistant;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.ModuleGeneratorHelper;

/**模块属性过滤，只提示依赖模块*/
public class ModuleAssistantFilter implements IAssistantFilter{
	
	IARESModule module;
	List<MoudleDepend> depends = new ArrayList<MoudleDepend>();
	
	public ModuleAssistantFilter(IARESModule module) {
		this.module = module;
	}
	
	/**
	 * 获取依赖的模块(本模块没依赖，查找上一级模块的依赖项)
	 */
	private void initDepends(IARESModule module) {
		try {
			depends.addAll(ModuleGeneratorHelper.getAllDepends(module));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(depends.isEmpty()){
			IARESModule parent = module.getParentModule();
			if(parent != null && parent.exists()){
				initDepends(parent);
			}
		}
	}

	@Override
	public boolean filter(Object obj) {
		if(obj instanceof IARESResource){
			IARESResource resource = (IARESResource)obj;
			//自身模块下也是应该提示的
			if(resource.getModule().equals(module)){
				return true;
			}
			//依赖模块
			for(MoudleDepend depend : depends){
				if(StringUtils.equals(depend.getModulePath(), resource.getModule().getElementName())){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void init() {
		depends.clear();
		initDepends(module);
	}
}
