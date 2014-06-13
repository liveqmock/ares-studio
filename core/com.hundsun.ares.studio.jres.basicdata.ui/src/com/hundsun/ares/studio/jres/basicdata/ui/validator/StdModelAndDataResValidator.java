package com.hundsun.ares.studio.jres.basicdata.ui.validator;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ui.ide.IDE;

import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.validate.IAresContext;
import com.hundsun.ares.studio.core.validate.IResValidator;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.StandardFieldModelAndData;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.BasicDataViewerValidator;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.IKeyTableLocator;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.InfoTableLocatorHelper;
import com.hundsun.ares.studio.validate.ValidateUtil;

public class StdModelAndDataResValidator  implements IResValidator {

	protected Collection<IARESProblem> doValidate(IARESResource resource, Map<String, IAresContext> contexts) {
		BasicDiagnostic basicDiagnostic = new BasicDiagnostic();
		Map<Object, Object> context = ValidateUtil.getValidateContext(resource);
		try {
			String editorID = IDE.getEditorDescriptor(resource.getElementName()).getId();
			EObject info = resource.getInfo(StandardFieldModelAndData.class);
			EStructuralFeature reference = info.eClass().getEStructuralFeature(IBasicDataEpacakgeConstant.Attr_Info_Items);
			//∫¨–≈œ¢±Ì
			if(reference != null){
				IKeyTableLocator helper = new InfoTableLocatorHelper(info);
				helper.reset();
				context.put(IBasicDataEpacakgeConstant.Info_Table_Locator_Helper,helper);
			}
			BasicDataViewerValidator.INSTANCE.validate(info,IBasicDataEpacakgeConstant.StandardField_Package_Define, basicDiagnostic, context);
			Collection<IARESProblem> problems = ValidateUtil.diagnosticChainToARESProblem(basicDiagnostic);
			if(!StringUtils.isEmpty(editorID)){
				for(IARESProblem problem:problems){
					problem.setAttribute(IDE.EDITOR_ID_ATTR, editorID);
				}
			}
			return problems;
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
