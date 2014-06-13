package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.model.metadata.util.MenuUtils;
import com.hundsun.ares.studio.ui.validate.KeyParameter;

public class MenuColumnViewerProblemView extends
		MetadataColumnViewerProblemView {

	public MenuColumnViewerProblemView(ColumnViewer viewer) {
		super(viewer);
	}

	@Override
	protected Set<Object> getAllCategoriesAndSelf(Collection<Object> objs,
			ColumnViewer viewer) {
		Set<Object> result = new HashSet<Object>();
		
		Stack<Object> stack = new Stack<Object>();
		stack.addAll(objs);
		
		while (!stack.isEmpty()) {
			Object top = stack.pop();
			//获取菜单的分类
			if (top instanceof MenuItem) {
				EList<MetadataCategory> categories =  MenuUtils.getCategories((MenuItem) top);
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
					continue;
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
	
	@Override
	public Diagnostic getDiagnostic(EObject object, EStructuralFeature feature,
			Object... objects) {
		try{
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
						Diagnostic d = getDiagnostic(child, feature);
						if (d != null) {
							return d;
						}
					}
					
				} else if (object instanceof MenuItem /*&& MetadataPackage.Literals.NAMED_ELEMENT__NAME.equals(feature)*/) {
					for (Iterator<EObject> iterator = EcoreUtil.getAllContents(object, true); iterator.hasNext(); ) {
						EObject child = iterator.next();
						//如果是子菜单错误则显示在对应列上
						if(child instanceof MenuItem){
							Object[] childProblems = getPool().getProblem(new KeyParameter(new Object[]{child,feature}));
							if (childProblems != null && childProblems.length > 0) {
								return (Diagnostic) childProblems[0];
							}
						//除子菜单，下级错误都显示在ID列上
						}else if(MetadataPackage.Literals.NAMED_ELEMENT__NAME.equals(feature)){
							Object[] childProblems = getPool().getProblem(new KeyParameter(new Object[]{child}));
							if (childProblems != null && childProblems.length > 0) {
								return (Diagnostic) childProblems[0];
							}
						}
					}
				}
				

			}

		}catch (Exception e) {
			return null;
		}
		
		return null;
	}
}
