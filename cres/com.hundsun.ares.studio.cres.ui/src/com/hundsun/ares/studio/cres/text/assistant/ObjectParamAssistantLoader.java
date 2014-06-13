package com.hundsun.ares.studio.cres.text.assistant;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.text.IDocument;

import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;

//智能提示输入输出对象参数
public class ObjectParamAssistantLoader extends AbstractAssistantLoader {

	private final static String PREFIX = "@";
	
	private IARESResource resource;
	
	public ObjectParamAssistantLoader(IARESResource resource) {
		this.resource = resource;
	}



	@Override
	public List<String> loadAssitant(String text, IDocument doc, int offset) {
		List<String> list = new ArrayList<String>();
		try {
			BizInterface info = resource.getInfo(BizInterface.class);
			if(info != null){
				for(Parameter param : info.getInputParameters()){
					if(param.getParamType().equals(ParamType.OBJECT) && StringUtils.isNotBlank(param.getId())){
						list.add(PREFIX + param.getId());
					}
				}
				for(Parameter param : info.getOutputParameters()){
					if(param.getParamType().equals(ParamType.OBJECT) && StringUtils.isNotBlank(param.getId())){
						list.add(PREFIX + param.getId());
					}
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return list;
	}

}