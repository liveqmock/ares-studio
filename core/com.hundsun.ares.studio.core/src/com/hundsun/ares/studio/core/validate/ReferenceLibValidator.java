/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.validate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESProblem;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IDependenceDescriptor;
import com.hundsun.ares.studio.core.IDependencyUnit;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.builder.DependencyBuilderUtil;
import com.hundsun.ares.studio.core.builder.IAresMarkers;
import com.hundsun.ares.studio.core.util.Util;

/**
 * 引用，依赖相关的检查。
 */
// * 开始的时候只有引用包，后来加入了引用工程，所以这个名字不大合适，但是由于涉及到代码重构，暂时没改。
public class ReferenceLibValidator {

	/**
	 * 检查一个项目的依赖方面的错误，比如命名空间冲突，依赖项不存在等...
	 * 增加这个方法主要为在导出引用包的地方验证整个项目是否存在错误； 而真正的builder中，用到的逻辑和这个方法类似，但是为了方便添加
	 * Marker，用的是另外一个方法{@link DependencyBuilderUtil#validateDependencies()}}
	 * @see DependencyBuilderUtil
	 * @param project
	 * @param monitor
	 * @return
	 */
	// 2012-2-22 sundl 增加这个方法，因为导出引用资源包的功能还是在调用老方法检查错误，改为用这个新方法和新逻辑。
	public static List<IARESProblem> validateProjectDependencies(IARESProject project, IProgressMonitor monitor) {
		List<IDependencyUnit> dependencies = project.getDependencies();
		List<IARESProblem> result = new ArrayList<IARESProblem>();
		
		monitor = Util.monitorFor(monitor);
		monitor.beginTask("", dependencies.size() + 1);
		
		// 初始化，把依赖信息转成Map的形式，方便使用，效率也更好
		Multimap<String , IDependencyUnit> units = ArrayListMultimap.create();
		for (IDependencyUnit unit : dependencies) {
			units.put(unit.getId(), unit);
		}
		
		monitor.subTask("检查项目属性ID");
		IARESProblem problem = ReferenceLibValidator.validateProjectNamespace(project);
		if (problem != null) {
			result.add(problem);
		}
		
		monitor.worked(1);
		
		for (IDependencyUnit unit : dependencies) {
			if (monitor.isCanceled())
				break;
			
			monitor.subTask("正在检查" + unit.getDescriptionStr());
			List<IARESProblem> problems = new ArrayList<IARESProblem>();
			
			// 1. 命名空间是否和其他依赖项冲突			
			List<IARESProblem> conflicts = ReferenceLibValidator.validateIdConfilicts(unit, project, units);
			problems.addAll(conflicts);
			
			// 2. 自己的依赖是否满足
			List<IARESProblem> dependencyProblems = ReferenceLibValidator.validateDependencies(unit, units);
			problems.addAll(dependencyProblems);
			
			IResource resToMark = null;
			if (unit instanceof IReferencedLibrary) {
				resToMark = ((IReferencedLibrary) unit).getResource();
			} 
			
			// 如果找不到合适的地方添加Marker，就添加到项目上
			if (resToMark == null) {
				resToMark = project.getProject();
			}
			
			result.addAll(problems);
			monitor.worked(1);
		}
		
		monitor.done();
		return result;
	}

	/**
	 * 检查依赖项的命名空间的冲突。
	 * @param units
	 * @param unitToValidate
	 * @return
	 * @author sundl
	 */
	public static IARESProblem validateNameSpace(Collection<IDependencyUnit> units, IDependencyUnit unitToValidate) {
		for (IDependencyUnit unit : units) {
			if (unit == unitToValidate)
				continue;
			
			if (StringUtils.equals(unit.getId(), unitToValidate.getId())) {
				IARESProblem problem = ARESProblem.createError(IAresMarkers.REFERLIB_MARKER_ID);
				problem.setMessage(String.format("%s和%s的命名空间冲突！", unitToValidate.getDescriptionStr(), unit.getDescriptionStr()));
			}
		}
		return null;
	}
	
	/**
	 * 检查引用资源包的命名空间
	 * 
	 * @param liblist
	 *            引用资源包列表
	 * @param slib
	 *            被检查引用资源包
	 * @return
	 */
	public static IARESProblem validateNameSpace(List<IReferencedLibrary> liblist, IReferencedLibrary slib) {
		String sid = slib.getId();
		for (IReferencedLibrary tlib : liblist) {
			if (!slib.equals(tlib)) { // 不同的引用资源包
				String tid = tlib.getId();
				if (StringUtils.equals(tid, sid) && StringUtils.equals(slib.getType(), tlib.getType())) {
					// 命名空间冲突
					IARESProblem problem = ARESProblem.createError(IAresMarkers.REFERLIB_MARKER_ID);
					problem.setMessage(String.format("引用资源包:%s和引用资源包:%s命名空间冲突。", slib.getElementName(), tlib.getElementName()));
					return problem;
				}
			}
		}
		return null;

	}
	
	

	/**
	 * 这个方法检查项目的命名空间设置是否和引用资源包(或引用工程)冲突
	 * @param aresProject 需要检查的项目
	 * @return
	 */
	public static IARESProblem validateProjectNamespace(IARESProject aresProject) {
		try {
			IARESProjectProperty property = aresProject.getProjectProperty();
			String pid = property.getId();
			if (!StringUtils.isEmpty(pid)) {
				for (IDependencyUnit dependency : aresProject.getDependencies()) {
					if (StringUtils.equals(pid, dependency.getId())
							// 2011-08-25 sundl 增加类型验证（通过nature来判断）
							&& aresProject.getProject().hasNature(String.valueOf(dependency.getType()))) {
						// 命名空间冲突
						IARESProblem problem = ARESProblem.createError(IAresMarkers.REFERLIB_MARKER_ID);
						problem.setMessage(String.format("项目和:%s命名空间冲突。", dependency.getDescriptionStr()));
						return problem;
					}
				}
			}
		} catch (ARESModelException e1) {
			e1.printStackTrace();
		} catch (CoreException e2) {
			e2.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 检查指定的依赖项的命名冲突
	 * @param unitToValidate
	 * @param units
	 * @return
	 */
	public static List<IARESProblem> validateIdConfilicts(IDependencyUnit unitToValidate, IARESProject project, Multimap<String, IDependencyUnit> units) {
		List<IARESProblem> problems = new ArrayList<IARESProblem>();
		
		// 2012-2-17 sundl BUG #2500 命名空间为空为合法，不再检查。
		if (StringUtils.isEmpty(unitToValidate.getId())) {
			return problems;
		}
		
		// 首先检查是否和当前项目冲突
		try {
			if (StringUtils.equals(project.getInfo().getId(), unitToValidate.getId())
					&& project.getProject().hasNature(unitToValidate.getType())) {
				IARESProblem problem = ARESProblem.createError(IAresMarkers.REFERLIB_MARKER_ID);
				problem.setMessage(String.format("%s和当前所在项目的命名空间冲突！", unitToValidate.getDescriptionStr()));
				problems.add(problem);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		// 检查和其他依赖项的冲突，获取所有ID相同的依赖项，如果类型也相同，则为冲突
		Collection<IDependencyUnit> unitsWithSameId = units.get(unitToValidate.getId());
		//unitsWithSameId.remove(unitToValidate);
		for (IDependencyUnit unit : unitsWithSameId) {
			if (StringUtils.equals(unitToValidate.getType(), unit.getType())
					&& !(unit.equals(unitToValidate))) {
				IARESProblem problem = ARESProblem.createError(IAresMarkers.REFERLIB_MARKER_ID);
				problem.setMessage(String.format("%s和%s的命名空间冲突！", unitToValidate.getDescriptionStr(), unit.getDescriptionStr()));
				problems.add(problem);
			}
		}
		return problems;
	}
	
	/**
	 * 检查指定的单元的依赖是否满足
	 * @param unitToValidate
	 * @param units 上下文（当前已经添加的所有的依赖项）
	 * @return
	 */
	public static List<IARESProblem> validateDependencies(IDependencyUnit unitToValidate, Multimap<String, IDependencyUnit> units) {
		List<IARESProblem> problems = new ArrayList<IARESProblem>();
		
		List<IDependenceDescriptor> dependencyDescriptors = unitToValidate.getDependencyDescriptors();
		for (IDependenceDescriptor desc : dependencyDescriptors) {
			// 在上下文中找指定的依赖，找不到即添加错误
			Collection<IDependencyUnit> unitsWithSameId = units.get(desc.getId());
			boolean dependencyFound = false;
			for (IDependencyUnit unit : unitsWithSameId) {
				if (StringUtils.equals(desc.getType(), unit.getType())
						&& StringUtils.equals(desc.getVersionConstraint(), unit.getVersion())) {
					dependencyFound = true;
					break;
				}
			}
			if (!dependencyFound) {
				IARESProblem problem = ARESProblem.createError(IAresMarkers.REFERLIB_MARKER_ID);
				problem.setMessage(String.format("%s的依赖项(ID:%s, Version:%s, Type:%s)没有找到!", 
											unitToValidate.getDescriptionStr(), 
											desc.getId(),
											desc.getVersionConstraint(),
											desc.getType()));
				problems.add(problem);
			}
		}
		return problems;
	}
}
