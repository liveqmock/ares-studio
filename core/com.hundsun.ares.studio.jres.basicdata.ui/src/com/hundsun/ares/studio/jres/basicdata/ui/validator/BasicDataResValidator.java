package com.hundsun.ares.studio.jres.basicdata.ui.validator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

import com.hundsun.ares.studio.core.ARESProblem;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.validate.IAresContext;
import com.hundsun.ares.studio.core.validate.IResValidator;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataEpacakgeConstant;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.BasicDataViewerValidator;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.IKeyTableLocator;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util.InfoTableLocatorHelper;
import com.hundsun.ares.studio.jres.basicdata.logic.epackage.BasicDataEpackageFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.validate.ValidateUtil;

public class BasicDataResValidator  implements IResValidator {

	protected Collection<IARESProblem> doValidate(IARESResource resource, Map<String, IAresContext> contexts) {
		
		/**
		 * 先读取EPackage，如果未抛出异常再进行具体的错误检查
		 * 这个过程不会增加很多时间消耗，因为如果是对应的数据库表发生变化
		 * 会将ares缓存中的info删除，在错误检查获取模型过程中还是会读取EPackage
		 * 而如果ares缓存中的info未删除，则EPackage也是保存在一个map中，只是增加了从map中读取的时间
		 */
		try {
			BasicDataEpackageFactory.eINSTANCE.createEPackage(resource);
		} catch (Exception e1) {
			IARESProblem tmpitem = ARESProblem.createError();
			tmpitem.setAttribute(ValidateUtil.VALIDATE_KEY_UI_SEVERITY, Diagnostic.ERROR);
			tmpitem.setAttribute(IMarker.MESSAGE, "基础数据模型构建失败：" + e1.getMessage());
			tmpitem.setAttribute(IMarker.PRIORITY, 0);
			return Arrays.asList(new IARESProblem[]{tmpitem});
		}
		
		BasicDiagnostic basicDiagnostic = new BasicDiagnostic();
		Map<Object, Object> context = ValidateUtil.getValidateContext(resource);
		try {
			String editorID = IDE.getEditorDescriptor(resource.getElementName()).getId();
			EObject info = resource.getInfo(MetadataResourceData.class);
			EStructuralFeature reference = info.eClass().getEStructuralFeature(IBasicDataEpacakgeConstant.Attr_Info_Items);
			//含信息表
			if(reference != null){
				IKeyTableLocator helper = new InfoTableLocatorHelper(info);
				helper.reset();
				context.put(IBasicDataEpacakgeConstant.Info_Table_Locator_Helper,helper);
			}
			BasicDataViewerValidator.INSTANCE.validate(info,IBasicDataEpacakgeConstant.Attr_Master_Items, basicDiagnostic, context);
			BasicDataViewerValidator.INSTANCE.validate(info,IBasicDataEpacakgeConstant.Attr_Info_Items, basicDiagnostic, context);
			Collection<IARESProblem> problems = ValidateUtil.diagnosticChainToARESProblem(basicDiagnostic);
			if(!StringUtils.isEmpty(editorID)){
				for(IARESProblem problem:problems){
					problem.setAttribute(IDE.EDITOR_ID_ATTR, editorID);
				}
			}
			return problems;
		}catch (PartInitException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.EMPTY_LIST;
	}

	@Override
	public Collection<IARESProblem> validate(IARESResource resource, Map<String, IAresContext> contexts) {
		return doValidate(resource, contexts);
	}


}
