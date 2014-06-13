package com.hundsun.ares.studio.jres.basicdata.ui.validator;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.ui.ide.IDE;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.validate.IAresContext;
import com.hundsun.ares.studio.core.validate.IResValidator;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.EpacakgeDefineList;
import com.hundsun.ares.studio.validate.ValidateUtil;

public class EPackageResValidator implements IResValidator {

	protected Collection<IARESProblem> doValidate(IARESResource resource, Map<String, IAresContext> contexts) {
		BasicDiagnostic basicDiagnostic = new BasicDiagnostic();
		Map<Object, Object> context = ValidateUtil.getValidateContext(resource);
		try {
			String editorID = IDE.getEditorDescriptor(resource.getElementName()).getId();
			Diagnostician.INSTANCE.validate(resource.getInfo(EpacakgeDefineList.class), basicDiagnostic, context);
			Collection<IARESProblem> problems = ValidateUtil.diagnosticChainToARESProblem(basicDiagnostic);
			if(!StringUtils.isEmpty(editorID)){
				for(IARESProblem problem:problems){
					problem.setAttribute(IDE.EDITOR_ID_ATTR, editorID);
				}
			}
			return problems;
		} catch (ARESModelException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Collections.EMPTY_LIST;
	}

	@Override
	public Collection<IARESProblem> validate(IARESResource resource, Map<String, IAresContext> contexts) {
		return doValidate(resource, contexts);
	}

}