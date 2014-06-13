package com.hundsun.ares.studio.jres.obj.ui.refactor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;

import com.hundsun.ares.studio.biz.constants.IBizResType;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.refactoring.AresElementRenameParicipant;

public class StdObjectListRenamePaticipant extends AresElementRenameParicipant {

	/// 为什么使用PreChange:
	/// 比如重命名一个模块, 如果等模块已经改名完成,那么这个模块下的资源相当于被移动过了, 
	/// 而在之前创建的Change对象都无法正确执行了.
	/// 所以如果重命名模块A,那模块A下的所有资源都必须用PreChange; 
	/// 那么索性所有的资源统一都用PrechangeC了.
	@Override
	public Change createPreChange(IProgressMonitor pm) throws CoreException,	OperationCanceledException {
		if (!getArguments().getUpdateReferences()) {
			return null;
		}
		String newName = getArguments().getNewName();
		if (element instanceof IARESModule) {
			IARESModule module = (IARESModule) element;
			IARESModule parentModule = module.getParentModule();
			String newModuleName = null;
			if (parentModule != null) {
				newModuleName = parentModule.getElementName() + "." + newName;
			} else {
				newModuleName = newName;
			}
			return createChange(module, newModuleName);
		} else if (element instanceof IARESResource) { 	// 此处没有再进行判断资源类型，因为在plugin.xml中已经有判断, Object的重命名才会触发这个,所以不必重复判断
			IARESResource res = (IARESResource) element;
			IARESModule module = res.getModule();
			String newRefName = module.getElementName() + "." + newName;
			List<Change> changes = ObjectRefactorUtil.createChanges(res, newRefName);
			if (!changes.isEmpty())
				return new CompositeChange("对象属性列表中的对象类型的参数引用更新", changes.toArray(new Change[0]));
			return null;
		}
		return null;
	}

	private Change createChange(IARESModule module, String newName) {
		List<Change> changes = new ArrayList<Change>();
		IARESResource[] objects = module.getARESResources(IBizResType.Object, true);
		// 这些都是全名会被改变的对象
		for (IARESResource object : objects) {
			String newObjName = newName + "." + object.getName();
			changes.addAll(ObjectRefactorUtil.createChanges(object, newObjName));
		}
		if (!changes.isEmpty())
			return new CompositeChange("对象属性列表中的对象类型的参数引用更新", changes.toArray(new Change[0]));
		else 
			return null;
	}
	
	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		return null;
	}


}
