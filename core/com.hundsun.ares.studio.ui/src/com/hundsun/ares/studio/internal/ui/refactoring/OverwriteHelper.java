package com.hundsun.ares.studio.internal.ui.refactoring;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.ltk.internal.core.refactoring.BasicElementLabels;
import org.eclipse.ui.ide.ResourceUtil;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.ui.refactoring.IConfirmQuery;
import com.hundsun.ares.studio.ui.refactoring.IReorgQueries;
import com.hundsun.ares.studio.ui.refactoring.RefactoringUtil;

class OverwriteHelper {
	private Object fDestination;
	private IARESResource[] aresResources;
	private IARESModule[] modules;

	public void confirmOverwriting(IReorgQueries reorgQueries, Object destination) {
		Assert.isNotNull(destination);
		Assert.isNotNull(reorgQueries);
		fDestination= destination;
		confirmOverwritting(reorgQueries);
	}

	public IARESModule[] getModules() {
		return modules;
	}

	public void setModules(IARESModule[] modules) {
		this.modules = modules;
	}

	public IARESResource[] getAresResources() {
		return aresResources;
	}

	public void setAresResources(IARESResource[] aresResources) {
		this.aresResources = aresResources;
	}
	
	private void confirmOverwritting(IReorgQueries reorgQueries) {
		IConfirmQuery overwriteQuery= reorgQueries.createYesYesToAllNoNoToAllQuery("Confirm Overwriting", true, IReorgQueries.CONFIRM_OVERWRITING);
		if (aresResources != null)
			confirmAresResourceOverwritting(overwriteQuery);
		if (modules != null)
			confirmModuleOverwritting(overwriteQuery);
	}

	private void confirmAresResourceOverwritting(IConfirmQuery overwriteQuery) {
		List<IARESResource> resToNotOverwrite= new ArrayList<IARESResource>(1);
		for (int i= 0; i < aresResources.length; i++) {
			IARESResource res= aresResources[i];
			if (canOverwrite(res) && ! overwrite(res, overwriteQuery))
				resToNotOverwrite.add(res);
		}
		IARESResource[] resources= resToNotOverwrite.toArray(new IARESResource[resToNotOverwrite.size()]);
		this.aresResources= ARESElementUtil.toARESResource(RefactoringUtil.setMinus(this.aresResources, resources));
	}

//	private void confirmPackageOverwritting(IConfirmQuery overwriteQuery){
//		List toNotOverwrite= new ArrayList(1);
//		for (int i= 0; i < fPackageFragments.length; i++) {
//			IPackageFragment pack= fPackageFragments[i];
//			if (canOverwrite(pack) && ! overwrite(pack, overwriteQuery))
//				toNotOverwrite.add(pack);
//		}
//		IPackageFragment[] packages= (IPackageFragment[]) toNotOverwrite.toArray(new IPackageFragment[toNotOverwrite.size()]);
//		fPackageFragments= ArrayTypeConverter.toPackageArray(ReorgUtils.setMinus(fPackageFragments, packages));
//	}
	
	private void confirmModuleOverwritting(IConfirmQuery overwrittingQuery) {
		List<IARESModule> modulesToNotOverwrite= new ArrayList<IARESModule>();
		if (modules != null) {
			for (IARESModule module : modules) {
				if (canOverwrite(module) && !overwrite(module, overwrittingQuery)) 
					modulesToNotOverwrite.add(module);
			}
		}
		IARESModule[] modules = modulesToNotOverwrite.toArray(new IARESModule[modulesToNotOverwrite.size()]);
		this.modules = ARESElementUtil.toARESModule(RefactoringUtil.setMinus(this.modules, modules));
	}

	/*
	 * Will resource override a member of destination?
	 */
	private boolean willOverwrite(IResource resource) {
		if (resource == null)
			return false;

		IResource destinationResource= ResourceUtil.getResource(fDestination);
		if (destinationResource.equals(resource.getParent()))
			return false;

		if (destinationResource instanceof IContainer) {
			IContainer container= (IContainer)destinationResource;
			IResource member=  container.findMember(resource.getName());
			if (member == null || !member.exists())
				return false;

			return true;
		}
		return false;
	}

	private boolean canOverwrite(IARESResource aresResource) {
		if (fDestination instanceof IARESModule){
			IARESModule destination= (IARESModule)fDestination;
			return ! destination.equals(aresResource.getParent()) && destination.getARESResource(aresResource.getElementName()).exists();
		} else {
			return willOverwrite(aresResource.getResource());
		}
	}
	
	private boolean canOverwrite(IARESModule module) {
		if (fDestination instanceof IARESModule){
			IARESModule destination= (IARESModule) fDestination;
			String shortName = module.getShortName();
			IARESModuleRoot root = destination.getRoot();
			boolean sameParent = destination.equals(module.getParent());
			boolean existing = root.getModule(destination.getElementName() + "." + shortName).exists();
			return (!sameParent) && existing;
		} else {
			return willOverwrite(module.getResource());
		}
	}

	private static boolean overwrite(IResource resource, IConfirmQuery overwriteQuery){
		return overwrite(BasicElementLabels.getResourceName(resource), overwriteQuery);
	}

	private static boolean overwrite(IARESElement element, IConfirmQuery overwriteQuery){
		return overwrite(element.getElementName(), overwriteQuery);
	}

	private static boolean overwrite(String name, IConfirmQuery overwriteQuery){
		String question= name + "已经存在，是否覆盖？";
		return overwriteQuery.confirm(question);
	}
//	private static boolean skip(String name, IConfirmQuery overwriteQuery){
//		String question= Messages.format(RefactoringCoreMessages.OverwriteHelper_3, BasicElementLabels.getJavaElementName(name));
//		return overwriteQuery.confirm(question);
//	}
}
