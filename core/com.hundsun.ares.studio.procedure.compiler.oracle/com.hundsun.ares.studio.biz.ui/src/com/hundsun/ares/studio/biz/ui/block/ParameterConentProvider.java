/**
 * 源程序名称：ViewerConentProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.ui.block;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.core.BizUtil;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.ui.editor.blocks.DisplayItem;
import com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider;

/**
 * @author sundl
 *
 */
public class ParameterConentProvider extends ReferenceTreeContentProvider {

	private IARESProject aresProject;
	
	/**
	 * @param reference
	 */
	public ParameterConentProvider(EReference reference, IARESProject aresProject) {
		super(reference);
		this.aresProject = aresProject;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof Parameter) {
			Parameter p = (Parameter) parentElement;
			if (p.getParamType() == ParamType.OBJECT || p.getParamType() == ParamType.PARAM_GROUP) {
//				ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(aresProject, IBizRefType.Object, p.getType(), true);
				ARESObject obj = BizUtil.getObject(p, aresProject);	//(ARESObject) ref.getObject();
				if(obj != null){
					return toDisplayItems(obj.getProperties()).toArray();
				}
			}
		} else if (parentElement instanceof DisplayItem) {
			return getChildren(((DisplayItem) parentElement).eObject);
		}
 		return super.getChildren(parentElement);
	}
	
	protected List<Object> toDisplayItems(List<? extends EObject> eObjects) {
		List<Object> result = new ArrayList<Object>();
		for (EObject eObj : eObjects) {
			result.add(new DisplayItem(eObj));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider#getParent(java.lang.Object)
	 */
	@Override
	public Object getParent(Object element) {
		if (element instanceof DisplayItem) {
			return null;
		}
		return super.getParent(element);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.viewers.ReferenceTreeContentProvider#hasChildren(java.lang.Object)
	 */
	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Parameter) {
			Parameter p = (Parameter) element;
			if (p.getParamType() == ParamType.OBJECT || p.getParamType() == ParamType.PARAM_GROUP) {
//				ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(aresProject, IBizRefType.Object, p.getType(), true);
//				if (ref != null) {
				ARESObject obj = BizUtil.getObject(p, aresProject);//(ARESObject) ref.getObject();
				if(obj != null){
					return obj.getProperties().size() > 0;
				}
//				} else {
//					return false;
//				}
			}
		} else if (element instanceof DisplayItem) {
			return hasChildren(((DisplayItem) element).eObject);
		}
		return super.hasChildren(element);
	}

}
