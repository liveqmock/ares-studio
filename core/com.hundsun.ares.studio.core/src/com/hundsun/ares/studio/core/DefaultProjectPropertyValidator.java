package com.hundsun.ares.studio.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IStatus;

import com.hundsun.ares.studio.core.validate.IAresContext;
import com.hundsun.ares.studio.core.validate.IProjectPropertyValidator;

public class DefaultProjectPropertyValidator implements IProjectPropertyValidator {

	public DefaultProjectPropertyValidator() {
	}

	public Collection<IARESProblem> validate(IARESProjectProperty property, Map<String, IAresContext> contexts) {
		List<IARESProblem> results = new ArrayList<IARESProblem>();
		
		if (StringUtils.isEmpty(property.getId())) {
			// id ÔÊÐíÎª¿Õ...
		} else {
			IStatus status = AresConventions.checkProjectId(property.getId());
			if (!status.isOK()) {
				IARESProblem problem = ARESProblem.createFrom(status);
				if (problem != null) {
					problem.setMessage(status.getMessage());
					results.add(problem);
				}
			}
		}
		return results;
	}

}
