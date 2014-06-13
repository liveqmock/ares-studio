/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.builder.AresProjectBuilderUtil;
import com.hundsun.ares.studio.core.preferences.ErrorCheckPreferenceHelper;
import com.hundsun.ares.studio.core.registry.ARESContextRegistry;
import com.hundsun.ares.studio.core.registry.RefResourcesProviderRegistry;
import com.hundsun.ares.studio.core.registry.ResValidaterRegistry;
import com.hundsun.ares.studio.core.validate.IAresContext;
import com.hundsun.ares.studio.core.validate.IRefResourceProvider;
import com.hundsun.ares.studio.core.validate.IResValidator;

/**
 * 错误检查
 * @author liaogc
 */
public class ErrorCheckAction extends PopupAction {
	private static final Logger logger = Logger.getLogger(ErrorCheckAction.class);

	private Map<String, IAresContext> contexts = new HashMap<String, IAresContext>();
	private Set<IARESResource> selectARESResources = new HashSet<IARESResource>();
	private Set<IARESModule> selectIARESModules = new HashSet<IARESModule>();
	private IARESProject project =null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		initContexts(project);
		Set<IARESResource> validatetARESResources = new HashSet<IARESResource>();
		for(IARESModule module :selectIARESModules){
			validatetARESResources.addAll(Arrays.asList(module.getARESResources(true)));
		}
		validatetARESResources.addAll(selectARESResources);
		for(IARESResource resource:validatetARESResources){
			validateResource(resource);//主资源错误检查
			if(ErrorCheckPreferenceHelper.getInstance().isRelationCheck()){
				for(IARESResource relationResource:getRelationResources(resource)){
					validateResource(relationResource);//相关资源错误检查
				}
			}
			
		}
	}
	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		super.selectionChanged(action, selection);
		selectIARESModules.clear();
		selectARESResources.clear();
		Iterator i = ((IStructuredSelection)selection).iterator();
		while (i.hasNext()) {
			Object obj = i.next();
			if (obj instanceof IARESModule) {
				selectIARESModules.add((IARESModule) obj);
				if(project==null){
					project = ((IARESModule) obj).getARESProject();	
				}
				
				
			}else if(obj instanceof IARESResource){
				selectARESResources.add((IARESResource) obj);
				if(project==null){
					project = ((IARESResource) obj).getARESProject();	
				}
			}
		}
		if(selectARESResources.size()==0 && selectIARESModules.size()==0){
			action.setEnabled(false);
		}else if ((selectARESResources.size()>0 ||  selectIARESModules.size()>0) && project!=null){
			
			action.setEnabled(true);
		}
	}
	/**
	 * 错误检查
	 * 
	 * @param res
	 */
	private void validateResource(IARESResource res) {
		ResValidaterRegistry validatorReg = ResValidaterRegistry.getInstance();

		// 引用包不检查
		if (res.getRoot().isArchive())
			return;

		List<IARESProblem> problems = new ArrayList<IARESProblem>();
		for (IResValidator validator : validatorReg
				.getValidators(res.getType())) {
			if (validator != null) {
				Collection<IARESProblem> pro = validator
						.validate(res, contexts);
				problems.addAll(pro);
			} else {
				logger.error(String.format("资源类型: %s 的Validator为null",
						res.getType()));
			}

		}

		// clear markers
		AresProjectBuilderUtil.clearMarkers(res);
		// create new markers
		if (!problems.isEmpty()) {
			AresProjectBuilderUtil.markProblems(res, problems);
		}

	}
	/**
	 * 获得相关的资源
	 * @param aresResource
	 * @return
	 */
	private Set<IARESResource> getRelationResources(IARESResource aresResource){
		Set<IARESResource> relationResources = new HashSet<IARESResource>();
			RefResourcesProviderRegistry reg = RefResourcesProviderRegistry.getInstance();
			for (IRefResourceProvider provider : reg.getProviders(aresResource.getType())) {
				relationResources.addAll(provider.getRefResources(aresResource, null, contexts));
			}
		return relationResources;
	}

	/**
	 * 初始化上下文
	 * @param project
	 */
	private void initContexts(IARESProject project) {
		ARESContextRegistry contextReg = ARESContextRegistry.getInstance();
		contexts = contextReg.createContexts(project);
	}

	

}
