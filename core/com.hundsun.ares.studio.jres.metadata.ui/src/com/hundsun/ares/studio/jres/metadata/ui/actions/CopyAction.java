/**
 * 源程序名称：CopyAction.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.actions;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.ColumnViewer;

import com.hundsun.ares.studio.ui.editor.actions.ColumnViewerCopyAction;

/**
 * 复制
 * @author gongyf
 *
 */
public class CopyAction extends ColumnViewerCopyAction {

	
	/*
	 * DESIGN#复制粘贴#龚叶峰#困难#孙道林#实现编辑器的复制粘贴
	 *
	 * 剪切复制粘贴是针对ColumnVewer通用的
	 * 需要有2个Transfer，一个是内部的，可以直接放置内存对象，也就是EObject对象，EObject对象应该是副本
	 * 要防止复制了一个对象但没有粘贴的情况下，这个对象进行了修改，导致了粘贴后的对象不是复制时的状态
	 * 也就是复制是对对象进行一个快照
	 * 
	 * 当用户在外部粘贴的时候，应该可以粘贴出表格中这一行，每列以tab分隔的字符串
	 * 需要使用TextTransfer
	 * 
	 */
	

	/**
	 * @param viewer
	 */
	public CopyAction(ColumnViewer viewer) {
		super(viewer);
	}

	
	// 不能同时复制分类和Item
	protected boolean calculateEnabled() {
		List<EObject> objects = getSelectedObjects();
		return !objects.isEmpty() && isSameConainer(objects);
	}
	
	/**
	 * 判断是否在一个容器的一个引用中
	 * @param eObjs
	 * @return
	 */
	boolean isSameConainer(List<EObject> eObjs) {
		EObject contaier = eObjs.get(0).eContainer();
		EReference reference = eObjs.get(0).eContainmentFeature();
		
		for (EObject eObject : eObjs) {
			if (eObject.eContainer() != contaier || eObject.eContainmentFeature() != reference) {
				return false;
			}
		}
		
		return true;
	}
	
}
