package com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.model.metadata.util.MenuUtils;
import com.hundsun.ares.studio.ui.cellEditor.AresContentProposalProvider;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

public class FuncProxyContentProposalProvider extends
		AresContentProposalProvider {

	IARESResource resource;
	public FuncProxyContentProposalProvider(IContentProposalProviderHelper helper,IARESResource resource) {
		super(helper);
		this.resource = resource;
	}
	@Override
	public void updateContent(Object element) {
		Map<String, Function> map = new HashMap<String, Function>();
		List<String>names = new ArrayList<String>();
		for(Function func : MenuUtils.getFunctions(resource)){
			String name = func.getName();
			String code = func.getSubTransCode();
			if(MenuUtils.isStockDepartment()){
				if(names.contains(name)){
					map.remove(name);
				}else{
					names.add(name);
					map.put(name, func);
				}
			}else{
				if(names.contains(code)){
					map.remove(code);
				}else{
					names.add(code);
					map.put(code, func);
				}
			}
		}
		
		setInput(map.values().toArray());
	}

}
