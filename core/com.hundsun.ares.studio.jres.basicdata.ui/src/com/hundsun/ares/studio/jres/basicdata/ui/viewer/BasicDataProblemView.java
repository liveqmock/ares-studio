package com.hundsun.ares.studio.jres.basicdata.ui.viewer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.jres.metadata.ui.viewer.MetadataViewerUtil;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnViewerProblemView;
import com.hundsun.ares.studio.ui.editor.viewers.RefreshViewerJob;
import com.hundsun.ares.studio.ui.validate.KeyParameter;
import com.hundsun.ares.studio.ui.validate.ProblemPoolChangeEvent;

public class BasicDataProblemView extends EObjectColumnViewerProblemView {
	EAttribute[] attrs;
	public BasicDataProblemView(ColumnViewer viewer,EAttribute[] attrs) {
		super(viewer);
		this.attrs = attrs;
	}

	@Override
	protected void doRefresh(ProblemPoolChangeEvent event) {
		// 或者增加或删除的问题BasicDiagnostic
		Object[] problems = ArrayUtils.addAll(event.getAddProblems(), event.getRemoveedProblems());
		Set<Object> objects = new HashSet<Object>();
		for (Object problem : problems) {
			EObject eObj = (EObject) ((Diagnostic)problem).getData().get(0);
			objects.add( eObj );
			
		}
		
		objects = getAllCategoriesAndSelf(objects, getViewer());
		
		RefreshViewerJob.refresh(getViewer(), objects.toArray(), true);
	}

	/**
	 * 获取对对象和其所在的所有分组
	 * @param objs
	 * @return
	 */
	protected Set<Object> getAllCategoriesAndSelf(Collection<Object> objs, ColumnViewer viewer) {
		
		Set<Object> result = new HashSet<Object>();
		
		Stack<Object> stack = new Stack<Object>();
		stack.addAll(objs);
		
		while (!stack.isEmpty()) {
			Object top = stack.pop();
			if (top instanceof MetadataItem) {
				EList<MetadataCategory> categories =  MetadataUtil.getCategories((MetadataItem) top);
				if (categories.isEmpty()) {
					result.add(MetadataViewerUtil.getUncategorizedCategory(viewer));
				} else {
					for (MetadataCategory category : categories) {
						stack.push(category);
					}
				}

			} else if (top instanceof MetadataCategory) {
				MetadataCategory parent = ((MetadataCategory) top).getParent();
				// 不添加null，也不添加顶级的分类
				if (parent == null || parent.eContainer() instanceof MetadataResourceData<?>) {
//					continue;
				} else {
					stack.push(parent);
				}
				
				
			} else if (top instanceof EObject) {
				EObject container = ((EObject) top).eContainer();
				if (container != null) {
					stack.push(container);
				}
				
			}
			
			result.add(top);
		}
		
		return result;
	}
	
	/**
	 * 获取儿子对应的属性，分组和条目一一对应
	 * @param feature
	 * @param child
	 * @return
	 */
	private EStructuralFeature getChildEAttribute(EStructuralFeature feature,EObject child){
		if(child instanceof MetadataItem){
			if(feature == MetadataPackage.Literals.NAMED_ELEMENT__NAME){
				return attrs[0];
			}else if(feature == MetadataPackage.Literals.NAMED_ELEMENT__CHINESE_NAME && attrs.length>1){
				return attrs[1];
			}else if(feature == MetadataPackage.Literals.NAMED_ELEMENT__DESCRIPTION && attrs.length>2){
				return attrs[2];
			}
		}
		return feature;
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.IDiagnosticProvider#getDiagnostic(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@Override
	public Diagnostic getDiagnostic(EObject object, EStructuralFeature feature, Object... objects ) {
		if (getPool() != null) {
			Object[] problems = getPool().getProblem(new KeyParameter(ArrayUtils.addAll(new Object[]{object, feature}, objects)));
			if (problems != null && problems.length > 0) {
				return (Diagnostic) problems[0];
			}
			
			
			if (object instanceof MetadataCategory) {
				Set<EObject> allChildren = new HashSet<EObject>();
				
				allChildren.addAll(((MetadataCategory) object).getItems()) ;
				allChildren.addAll(((MetadataCategory) object).getChildren());
				
				for (EObject child : allChildren) {
					Diagnostic d = getDiagnostic(child, getChildEAttribute(feature, child));
					if (d != null) {
						return d;
					}
				}
				
			} else if (object instanceof MetadataItem && (MetadataPackage.Literals.NAMED_ELEMENT__NAME.equals(feature) || attrs[0].equals(feature))) {
				// 下级的错误只显示在ID这一列
				for (Iterator<EObject> iterator = EcoreUtil.getAllContents(object, true); iterator.hasNext(); ) {
					EObject child = iterator.next();
					
					Object[] childProblems = getPool().getProblem(new KeyParameter(new Object[]{child}));
					if (childProblems != null && childProblems.length > 0) {
						return (Diagnostic) childProblems[0];
					}
				}
			}
			

		}

		return null;
	}
}
