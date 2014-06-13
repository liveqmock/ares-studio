/**
 * 源程序名称：EMFAllValidateUnit.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.validate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;

import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.validate.IProblemPool;
import com.hundsun.ares.studio.ui.validate.IValidateUnit;

/**
 * @author gongyf
 *
 */
public class EMFAllValidateUnit implements IValidateUnit {

	List<EObject> objects;
	
	/**
	 * @param objects
	 */
	public EMFAllValidateUnit(EObject object) {
		this(Arrays.asList(object));
	}
	
	
	/**
	 * @param objects
	 */
	public EMFAllValidateUnit(Collection<EObject> objects) {
		super();
		this.objects = new ArrayList<EObject>(objects);
	}




	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.validate.IValidateUnit#updateProblemPool(com.hundsun.ares.studio.jres.ui.validate.IProblemPool, java.util.Map)
	 */
	@Override
	public void updateProblemPool(IProblemPool pool, Map<Object, Object> context)
			throws Exception {
		BasicDiagnostic basicDiagnostic =
			new BasicDiagnostic
				(Diagnostic.ERROR,
				 ARESEditorPlugin.PLUGIN_ID,
				 0,
				 "检查内容",
				 new Object [] { objects });
		
		// 检查所有内容
		for (EObject content : objects) {
			Diagnostician.INSTANCE.validate(content, basicDiagnostic, context);
		}
		
		pool.beginChange();
		pool.clear();
		// 将检查结果收集
		for (Diagnostic child : basicDiagnostic.getChildren()) {
			pool.addProblem(child);
		}
		pool.endChange();
	}

}
