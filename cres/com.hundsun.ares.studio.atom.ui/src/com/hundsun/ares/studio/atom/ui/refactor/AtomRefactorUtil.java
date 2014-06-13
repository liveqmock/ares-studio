package com.hundsun.ares.studio.atom.ui.refactor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ltk.core.refactoring.Change;

import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.internal.ui.refactoring.changes.ReferenceProviderRefChange;
import com.hundsun.ares.studio.model.reference.RelationInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

public class AtomRefactorUtil {

	/**
	 * 给定一个即将被重命名活移动的对象资源(意思是这个对象的全名将改变)，计算应该进行的相关的改变，返回一个CHange列表
	 * @param objRes 被改名的Object资源
	 * @param newName
	 * @param resTypes 资源类型
	 * @return
	 */
	public static List<Change> createChanges(IARESResource objRes, String newRefName,List<String> resTypes) {
		List<Change> changes = new ArrayList<Change>();
		// 对于全名将要改变的对象，找到所有引用它的资源
		IARESProject project = objRes.getARESProject();
		List<RelationInfo> relList = ReferenceManager.getInstance().getRelationInfoByTarget(IBizRefType.Object, objRes.getFullyQualifiedName(), project);
		for (RelationInfo rel : relList) {
			// 对于如果这个资源是服务，并且引用了那个对象，则需要更新引用
			IARESResource hostRes = rel.getHostResource();
			if (resTypes.contains(hostRes.getType())) {
				changes.add(new ReferenceProviderRefChange(hostRes, IBizRefType.Object, objRes.getFullyQualifiedName(), newRefName));
			}
			
		}
		return changes;
	}
	
}
