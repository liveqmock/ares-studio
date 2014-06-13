/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.aresaction;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionGroup;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.registry.ModuleRootType2ResTypeMap;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.ui.aresaction.AresActionExecuteContext;
import com.hundsun.ares.studio.ui.aresaction.AresActionExecuter;

/**
 * 用于增加ARESAction的ActionGroup
 * @author sundl
 */
public class ARESActionActionGroup extends ActionGroup{

	private IWorkbenchPart part;
	private ARESActionRegistry registry;
	private List<IARESElement> elements;
	
	public ARESActionActionGroup(IWorkbenchPart part) {
		this.part = part;
		this.registry = ARESActionRegistry.getInstance();
	}
	
	public void setContext(ActionContext aContext) {
		super.setContext(aContext);
		if (aContext != null) {
			ISelection selection = aContext.getSelection();
			if (selection instanceof IStructuredSelection) {
				Object[] objs = ((IStructuredSelection)selection).toArray();
				elements = new ArrayList<IARESElement>();
				for (Object obj : objs) {
					if (obj instanceof IARESElement) {
						elements.add((IARESElement)obj);
					} else if (obj instanceof IProject) {
						IARESProject aresProj = ARESCore.create((IProject) obj);
						if (aresProj != null && aresProj.exists())
							elements.add(ARESCore.create((IProject) obj));
					}
				}
			}
		}
	}
	
	public void fillContextMenu(IMenuManager menu) {
		Collection<AresActionDelareDescriptor> allActions = registry.getActionDeclaretions();
		final IARESElement[] elements = this.elements.toArray(new IARESElement[0]);
		
		boolean allRes = ARESElementUtil.elementsIsOfType(elements, IARESElement.ARES_RESOURCE);
		boolean allModules = ARESElementUtil.elementsIsOfType(elements, IARESElement.COMMON_MODULE);
		boolean allRoots = ARESElementUtil.elementsIsOfType(elements, IARESElement.COMMON_MODULE_ROOT);
		boolean allProjects = ARESElementUtil.elementsIsOfType(elements, IARESElement.ARES_PROJECT);

		Collection<AresActionDelareDescriptor> actions = null;
		if (allRes) {
			IARESResource[] resources = ARESElementUtil.toARESResource(elements);
			String[] resTypes = ARESElementUtil.getResourcesTypes(resources);
			actions = registry.computeActionDeclaretions(resTypes);
		} else if (allModules) {
			IARESModule module = (IARESModule) elements[0];
			IARESModuleRoot root = module.getRoot();
			ModuleRootType2ResTypeMap map = ModuleRootType2ResTypeMap.getInstance();
			String[] resTypes = map.getAllowedResTypes(root.getType());	
			actions = registry.computeActionDeclaretions(resTypes);
		} else if (allRoots) {
			IARESModuleRoot root = (IARESModuleRoot) elements[0];
			ModuleRootType2ResTypeMap map = ModuleRootType2ResTypeMap.getInstance();
			String[] resTypes = map.getAllowedResTypes(root.getType());	
			actions = registry.computeActionDeclaretions(resTypes);
		} else if (allProjects) {
			actions = allActions;
		}
		
		// 比如资源分类文件夹
		if (actions == null) {
			return;
		}
		
		for (final AresActionDelareDescriptor action : actions) {
			menu.add(new Action(action.getName()) {

				@Override
				public void run() {
					

					IRunnableWithProgress op = new IRunnableWithProgress() {
						public void run(IProgressMonitor monitor) {
							AresActionExecuteContext context = new AresActionExecuteContext();
							context.setActionId(action.getId());
							context.setEntryPoint(ARESActionActionGroup.this.elements);
							AresActionExecuter excuter = new AresActionExecuter(context);
							excuter.excute(monitor);
						}
					};

					try {
						PlatformUI.getWorkbench().getProgressService().run(true, true, op);
					} catch (InterruptedException e) {
					} catch (InvocationTargetException e) {
					}
					
				}
				
			});
		}
	}
	
	
} 
