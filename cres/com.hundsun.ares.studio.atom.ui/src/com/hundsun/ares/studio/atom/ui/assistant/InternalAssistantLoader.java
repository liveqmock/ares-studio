package com.hundsun.ares.studio.atom.ui.assistant;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.text.IDocument;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.cres.text.assistant.AbstractAssistantLoader;

public class InternalAssistantLoader extends AbstractAssistantLoader {
	
	private final static String PREFIX = "@";
	
	IARESResource resource;
	
	public InternalAssistantLoader(IARESResource resource){
		this.resource = resource;
	}
	@Override
	public List<String> loadAssitant(String text, IDocument doc, int offset) {
		List<String> allproposals = new ArrayList<String>();
		if(StringUtils.startsWith(text, PREFIX)){
			try {
				//提示内部变量里的所有非标准字段
				List<Parameter> params = new ArrayList<Parameter>();
				AtomFunction obj = resource.getInfo(AtomFunction.class);
				if(obj != null){
					params.addAll(obj.getInternalVariables());
				}
				for(Parameter param : params){
					if(param.getParamType() == ParamType.NON_STD_FIELD){
						String id = param.getId();
						if(!allproposals.contains(id)){
							allproposals.add(PREFIX + id);
						}
					}
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		return allproposals;
	}
	
}
