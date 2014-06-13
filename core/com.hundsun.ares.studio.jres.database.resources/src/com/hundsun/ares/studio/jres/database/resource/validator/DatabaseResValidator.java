package com.hundsun.ares.studio.jres.database.resource.validator;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.PlatformUI;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.validate.AnyContext;
import com.hundsun.ares.studio.core.validate.IAresContext;
import com.hundsun.ares.studio.core.validate.IResValidator;
import com.hundsun.ares.studio.validate.ValidateUtil;

public class DatabaseResValidator implements IResValidator {

	private static final String ID_CONTEXT = "数据库检查";
	
	public DatabaseResValidator() {
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.core.util.validate.ModuleProjectResValidator#doValidate(com.hundsun.ares.studio.core.IARESResource, java.util.Map)
	 */
	protected Collection<IARESProblem> doValidate(IARESResource resource,	Map<String, IAresContext> contexts) {
		BasicDiagnostic basicDiagnostic = new BasicDiagnostic();
		
		AnyContext anyContext = (AnyContext) contexts.get(ID_CONTEXT);
		if (anyContext == null) {
			contexts.put(ID_CONTEXT, anyContext = new AnyContext(new HashMap<Object, Object>()));
		}
		
		// 复用一些上下文
		Map<Object, Object> context = (Map<Object, Object>) anyContext.getData();
		context.putAll(ValidateUtil.getValidateContext(resource));
		
		String editorID = getSuitableEditorDescriptorID(resource);
		try {
			EObject object = resource.getInfo(EObject.class);
			if (object != null) {
				Diagnostician.INSTANCE.validate(object, basicDiagnostic, context);
				Collection<IARESProblem> problems = ValidateUtil.diagnosticChainToARESProblem(basicDiagnostic);
				if(!StringUtils.isEmpty(editorID)){
					for(IARESProblem problem:problems){
						problem.setAttribute("org.eclipse.ui.editorID", editorID);
					}
				}
				return problems;
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		
		return Collections.EMPTY_LIST;
	}
	
	public static IEditorDescriptor getSuitableEditorDescriptor(IARESResource resource){
		if(null != resource.getResource()){
			try {
					String name = resource.getResource().getName();
					IEditorRegistry editorReg = PlatformUI.getWorkbench().getEditorRegistry();
					IContentType contentType =  Platform.getContentTypeManager().findContentTypeFor(name);
					IEditorDescriptor[] tarray = editorReg.getEditors(name, contentType);
					if(tarray.length > 0){
						for(IEditorDescriptor item:tarray){
							if(StringUtils.startsWith(item.getId(), "")){
								return item;
							}
						}
						return tarray[0];
					}
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	/**
	 * 获取11版本的编辑器ID
	 * @param file
	 * @return
	 */
	public static String getSuitableEditorDescriptorID(IARESResource resource){
		IEditorDescriptor dis = getSuitableEditorDescriptor(resource);
		if(null != dis){
			return dis.getId();
		}else{
			return null;
		}
	}
	

	@Override
	public Collection<IARESProblem> validate(IARESResource resource, Map<String, IAresContext> contexts) {
		return doValidate(resource, contexts);
	}

}
