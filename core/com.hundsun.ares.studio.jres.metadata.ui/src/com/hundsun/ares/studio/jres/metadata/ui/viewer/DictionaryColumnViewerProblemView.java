/**
 * 源程序名称：DictionaryColumnViewerProblemView.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据编辑器相关
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.ui.editor.viewers.RefreshViewerJob;
import com.hundsun.ares.studio.ui.validate.ProblemPoolChangeEvent;

/**
 * @author wangxh
 *
 */
public class DictionaryColumnViewerProblemView extends MetadataColumnViewerProblemView {

	ColumnViewer parentViewer;
	
	public DictionaryColumnViewerProblemView(ColumnViewer viewer,ColumnViewer parentViewer) {
		super(viewer);
		this.parentViewer=parentViewer;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataColumnViewerProblemView#doRefresh(com.hundsun.ares.studio.jres.ui.validate.ProblemPoolChangeEvent)
	 */
	@Override
	protected void doRefresh(ProblemPoolChangeEvent event) {
		// 或者增加或删除的问题BasicDiagnostic
		Object[] problems = ArrayUtils.addAll(event.getAddProblems(), event.getRemoveedProblems());
		Set<Object> objects = new HashSet<Object>();
		for (Object problem : problems) {
			EObject eObj = (EObject) ((Diagnostic)problem).getData().get(0);
			objects.add( eObj );
			
		}
		
		RefreshViewerJob.refresh(getViewer(), objects.toArray(), true);
		
		// 获取父节点
		objects = getAllCategoriesAndSelf(objects, parentViewer);
		RefreshViewerJob.refresh(parentViewer, objects.toArray(), true);
	}

}
