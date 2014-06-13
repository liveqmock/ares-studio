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
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.refactoring.AresElementMoveParticipant;


/**
 * CRES原子逻辑,原子服务,
 * @author liaogc
 *
 */
public class AtomMoveParticipant extends AresElementMoveParticipant {

	public AtomMoveParticipant() {
	}
	
	@Override
	public Change createPreChange(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {
		if (!getArguments().getUpdateReferences()) {
			return null;
		}
		
		Object newDestination = getArguments().getDestination();
		if (element instanceof IARESModule) {
			IARESModule module = (IARESModule) element;
			String newModuleName = null;
			if (newDestination instanceof IARESModule) {
				IARESModule destModule = (IARESModule) newDestination;
				newModuleName = destModule.getElementName() + "." + module.getShortName();
			} else if (newDestination instanceof IARESModuleRoot) {
				newModuleName = module.getShortName();
			}
			return createChange(module, newModuleName);
		} else if (element instanceof IARESResource) {
			IARESResource res = (IARESResource) element;
			String newObjName = null;
			if (newDestination instanceof IARESModule) {
				newObjName = ((IARESModule) newDestination).getElementName() + "." + res.getName();
			} else if (newDestination instanceof IARESModuleRoot) {
				newObjName = res.getName();
			}
			if (newObjName == null)
				return null;
			List<Change> changes = AtomRefactorUtil.createChanges(res, newObjName,getResTypes());
			if (!changes.isEmpty())
				return new CompositeChange(getDesc(), changes.toArray(new Change[0]));
		}
		return null;
	}
	
	private Change createChange(IARESModule module, String newModuleName) {
		List<Change> changes = new ArrayList<Change>();
		IARESResource[] objects = module.getARESResources(IBizResType.Object, true);
		// 这些都是全名会被改变的对象
		List<String> resTypes = getResTypes();
		for (IARESResource object : objects) {
			String newObjName = newModuleName + "." + object.getName();
			changes.addAll(AtomRefactorUtil.createChanges(object, newObjName,resTypes));
		}
		if (!changes.isEmpty())
			return new CompositeChange(getDesc(), changes.toArray(new Change[0]));
		else 
			return null;
	}
	
	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
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
