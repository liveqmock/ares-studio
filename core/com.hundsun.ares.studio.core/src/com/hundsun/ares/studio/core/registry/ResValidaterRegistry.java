/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.builder.DependencyBuilderUtil;
import com.hundsun.ares.studio.core.util.Util;
import com.hundsun.ares.studio.core.validate.DefaultContextProvider;
import com.hundsun.ares.studio.core.validate.IAresContext;
import com.hundsun.ares.studio.core.validate.IProjectPropertyValidator;
import com.hundsun.ares.studio.core.validate.IResValidator;
import com.hundsun.ares.studio.core.validate.ReferenceLibValidator;

/**
 * 
 * @author sundl
 */
public class ResValidaterRegistry extends CommonMapRegistry<ResValidatorDescriptor> {
	
	private static ResValidaterRegistry instance;
	
	public static ResValidaterRegistry getInstance() {
		if (instance == null) {
			instance = new ResValidaterRegistry();
		}
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#getExtensionPointId()
	 */
	@Override
	public String getExtensionPointId() {
		return "resValidator";
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.CommonMapRegistry#handleConfigElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@Override
	protected void handleConfigElement(IConfigurationElement element) {
		ResValidatorDescriptor desc = new ResValidatorDescriptor(element);
		for (String type : desc.getResTypes()) {
			map.put(type, desc);
		}
	}

	public Collection<IResValidator> getValidators(String type) {
		Collection<ResValidatorDescriptor> descriptors = map.get(type);
		List<IResValidator> validators = new ArrayList<IResValidator>();
		for (ResValidatorDescriptor desc : descriptors) {
			validators.add(desc.getValidator());
		}
		return validators;
	}
	
	public Collection<IARESProblem> validateProject(IARESProject project, IProgressMonitor monitor) {
		IProgressMonitor pm = Util.monitorFor(monitor);
		
		List<IARESProblem> problems = new ArrayList<IARESProblem>();
		ARESContextRegistry contextReg = ARESContextRegistry.getInstance();
		Map<String, IAresContext> contexts = contextReg.createContexts(project);
		
		DefaultContextProvider context = (DefaultContextProvider)contexts.get(IAresContext.DEFAULT_CONTEXT);
		pm.beginTask("检查项目", context.getResources().size() + 1);
		if (monitor.isCanceled())
			return problems;
		
		pm.subTask("验证依赖项...");
		problems.addAll(ReferenceLibValidator.validateProjectDependencies(project, new SubProgressMonitor(monitor, 1)));

		pm.subTask("验证资源...");
		for (IARESResource res : context.getResources().values()) {
			if (monitor.isCanceled())
				return problems;
			
			problems.addAll(validateResource(res, contexts));
			pm.worked(1);
		}
		
		if (monitor.isCanceled())
			return problems;
		
		pm.subTask("检查项目属性...");
		problems.addAll(checkProjectProperty(project.getProject(), contexts));
		//检查命名空间
		IARESProblem problem = ReferenceLibValidator.validateProjectNamespace(project);
		if(null != problem){
			problems.add(problem);
		}
		
		pm.worked(1);
		pm.done();
		return problems;
	}
	
	protected Collection<IARESProblem> validateResource(IARESResource res, Map<String, IAresContext> contexts) {
		ResValidaterRegistry validatorReg = ResValidaterRegistry.getInstance();

		List<IARESProblem> problems = new ArrayList<IARESProblem>();
		
		// 引用包不检查
		if (res.getRoot().isArchive())
			return problems;
		
		for (IResValidator validator : validatorReg.getValidators(res.getType())) {
			Collection<IARESProblem> pro = validator.validate(res, contexts);
			problems.addAll(pro);
		}
		
		return problems;
	}
	
	private Collection<IARESProblem> checkProjectProperty(IProject project, Map<String, IAresContext> contexts) {
		List<IARESProblem> results = new ArrayList<IARESProblem>();
		IARESProject aresProject = ARESCore.create(project);
		IARESProjectProperty property = null;;
		try {
			property = aresProject.getProjectProperty();
		} catch (ARESModelException e1) {
			e1.printStackTrace();
		}
		
		if (property == null)
			return results; 
		
		IProjectDescription desc;
		try {
			desc = project.getDescription();
			String[] natures = desc.getNatureIds();
			ProjectValidatorRegistry reg = ProjectValidatorRegistry.getInstance();
			for (String nature : natures) {
				Collection<IProjectValidatorDescriptor> validators = reg.get(nature);
				for (IProjectValidatorDescriptor descriptor : validators) {
					IProjectPropertyValidator validator = descriptor.getValidator();
					Collection<IARESProblem> problems = validator.validate(property, contexts);
					results.addAll(problems);
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return results;
	}
}
