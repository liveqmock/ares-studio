package com.hundsun.ares.studio.core.validate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESProblem;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ModuleProperty;

public class ModulePropertyValidator implements IResValidator {

	public ModulePropertyValidator() {
	}

	public Collection<IARESProblem> validate(IARESResource resource, Map<String, IAresContext> contexts) {
		List<IARESProblem> result = new ArrayList<IARESProblem>();
		try {
			ModuleProperty modulePro = resource.getInfo(ModuleProperty.class);
			if (modulePro == null) {
				IARESProblem problem = ARESProblem.createWaring();
				problem.setMessage("模块属性读取出错：["+resource.getFullyQualifiedName()+"]");
				result.add(problem);
				return result;
			}
			String cName = (String) modulePro.getValue(ICommonModel.CNAME);
			if (StringUtils.isEmpty(cName)) {
				IARESProblem problem = ARESProblem.createWaring();
				problem.setMessage("中文名不能为空.");
				result.add(problem);
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return result;
	}

}
