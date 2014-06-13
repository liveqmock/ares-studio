/**
 * 源程序名称：ExtensibleModelColumnViewerProblemView.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.extend;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.RefreshViewerJob;
import com.hundsun.ares.studio.ui.validate.ProblemPoolChangeEvent;

/**
 * @author gongyf
 *
 */
public class ExtensibleModelColumnViewerProblemView extends
		EObjectColumnViewerProblemView {

	/**
	 * @param viewer
	 */
	public ExtensibleModelColumnViewerProblemView(ColumnViewer viewer) {
		super(viewer);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.viewers.EObjectColumnViewerProblemView#doRefresh(com.hundsun.ares.studio.jres.ui.validate.ProblemPoolChangeEvent)
	 */
	@Override
	protected void doRefresh(ProblemPoolChangeEvent event) {
		// 或者增加或删除的问题BasicDiagnostic
		Object[] problems = ArrayUtils.addAll(event.getAddProblems(), event.getRemoveedProblems());
		Set<Object> objects = new HashSet<Object>();
		for (Object problem : problems) {
			EObject eObj = (EObject) ((Diagnostic)problem).getData().get(0);
			objects.add(eObj);
			// 假定扩展只会有一层，也就是说表格的行对象就是ExtensibleModel
			// 需要注意的是，出错的对象可能被删除，并不属于任何ExtensibleModel对象
			while (!(eObj instanceof ExtensibleModel) && eObj != null) {
				eObj = eObj.eContainer();
			}
			if (eObj != null) {
				objects.add(eObj);
			}
			
		}
		RefreshViewerJob.refresh(getViewer(), objects.toArray(), true);
	}
	
}
