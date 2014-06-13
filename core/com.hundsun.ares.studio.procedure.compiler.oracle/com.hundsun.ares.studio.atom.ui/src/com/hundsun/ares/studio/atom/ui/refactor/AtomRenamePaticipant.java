package com.hundsun.ares.studio.atom.ui.refactor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;

import com.hundsun.ares.studio.atom.constants.IAtomResType;
import com.hundsun.ares.studio.biz.constants.IBizResType;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.refactoring.AresElementRenameParicipant;

public class AtomRenamePaticipant extends AresElementRenameParicipant {
   
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
			List<Change> changes = AtomRefactorUtil.createChanges(res, newRefName,getResTypes());
			if (!changes.isEmpty())
				return new CompositeChange(getDesc(), changes.toArray(new Change[0]));
			return null;
		}
		return null;
	}
	
	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		return null;
	}
	
	
	
	protected Change createChange(IARESModule module, String newName) {
		List<Change> changes = new ArrayList<Change>();
		IARESResource[] objects = module.getARESResources(IBizResType.Object, true);
		// 这些都是全名会被改变的对象
		List<String> resTypes = getResTypes();
		for (IARESResource object : objects) {
			String newObjName = newName + "." + object.getName();
			changes.addAll(AtomRefactorUtil.createChanges(object, newObjName,resTypes));
		}
		if (!changes.isEmpty())
			return new CompositeChange(getDesc(), changes.toArray(new Change[0]));
		else 
			return null;
	}
	/**
	 * 返回类型类型
	 * @return
	 */
	protected List<String> getResTypes(){
		List<String> resTypes = new ArrayList<String>();
		resTypes.add(IAtomResType.ATOM_FUNCTION);
		resTypes.add(IAtomResType.ATOM_SERVICE);
		return resTypes;
	}
	
	/**
	 * 返回描述信息
	 * @return
	 */
	protected String getDesc(){
		return "CRES原子中对象类型的参数引用更新";
	}
	
}
