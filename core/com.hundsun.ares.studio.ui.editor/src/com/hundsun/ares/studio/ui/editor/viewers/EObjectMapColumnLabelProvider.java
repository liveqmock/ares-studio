/**
 * 源程序名称：EObjectMapColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.viewers;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.ColumnLabelProvider;

/**
 * @author gongyf
 *
 */
public class EObjectMapColumnLabelProvider extends ColumnLabelProvider {
	private EReference reference;
	private Object key;
	/**
	 * @param reference
	 * @param key
	 */
	public EObjectMapColumnLabelProvider(EReference reference, Object key) {
		super();
		this.reference = reference;
		this.key = key;
	}
	
	/**
	 * 获取需要操作的EObject
	 * @param element
	 * @return
	 */
	protected EObject getOwner(Object element) {
		return (EObject) element;
	}
	
	/**
	 * @return the reference
	 */
	protected EReference getReference() {
		return reference;
	}
	
	@Override
	public String getText(Object element) {
		EObject owner = getOwner(element);
		if (!owner.eClass().getEAllReferences().contains(reference)) {
			return "";
		}
		Object value = ((EMap)owner.eGet(reference)).get(key);
		if (value == null) {
			value = "";
		}
		return String.valueOf(value);
	}
}
