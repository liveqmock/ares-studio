package com.hundsun.ares.studio.jres.basicdata.core.basicdata.validate.util;

import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.jres.basicdata.Activator;
import com.hundsun.ares.studio.ui.validate.IProblemPool;
import com.hundsun.ares.studio.ui.validate.IValidateUnit;

public class BasicDataValidateUnit implements IValidateUnit {

	private EObject obj;
	private String featureName;
	public BasicDataValidateUnit(EObject object, String featureName) {
		this.obj = object;
		this.featureName = featureName;
	}
	
	@Override
	public void updateProblemPool(IProblemPool pool, Map<Object, Object> context)
			throws Exception {
		BasicDiagnostic basicDiagnostic =
			new BasicDiagnostic
				(Diagnostic.ERROR,
				 Activator.PLUGIN_ID,
				 0,
				 "检查内容",
				 new Object [] { obj });
		
		
		// 检查所有内容
		BasicDataViewerValidator.INSTANCE.validate(obj,featureName, basicDiagnostic, context);
		
		pool.beginChange();
		pool.clear();
		// 将检查结果收集
		for (Diagnostic child : basicDiagnostic.getChildren()) {
			pool.addProblem(child);
		}
		pool.endChange();
	}

}
