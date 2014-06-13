/**
 * 源程序名称：DependencyBuilderUtil.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IDependencyUnit;
import com.hundsun.ares.studio.core.IReferencedLibrary;
import com.hundsun.ares.studio.core.util.Util;
import com.hundsun.ares.studio.core.validate.ReferenceLibValidator;
import com.hundsun.ares.studio.internal.core.ARESProject;

/**
 * 依赖检查的Build逻辑
 * @author sundl
 */
public class DependencyBuilderUtil {
	
	/**
	 * 检查项目的依赖
	 * @param project
	 * @param monitor
	 */
	public static void validateDependencies(IARESProject project, IProgressMonitor monitor) {
		List<IDependencyUnit> dependencies = project.getDependencies();
		
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
			// 命名空间冲突的错误添加到工程属性上
			AresProjectBuilderUtil.markProblems(project.getProperty().getResource(), Arrays.asList(problem));
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
			
			AresProjectBuilderUtil.clearMarkers(resToMark,IAresMarkers.REFERLIB_MARKER_ID,false);
			AresProjectBuilderUtil.markProblems(resToMark, problems);
			
			monitor.worked(1);
		}
		monitor.done();
	}

	/**
	 * @param project
	 * @param delta
	 * @return
	 */
	public static boolean needBuild(IARESProject project, IResourceDelta delta) {
		try {
			if (delta.findMember(new Path(ARESProject.RES_PATH_FILE)) != null
					|| delta.findMember(new Path(IARESProjectProperty.PRO_FILE)) != null) {
				return true;
			} else {
				List<IReferencedLibrary> libs = Arrays.asList(project.getReferencedLibs());
				for (IReferencedLibrary lib : libs) {
					IResourceDelta subDelta = delta.findMember(lib.getPath().makeRelativeTo(project.getProject().getFullPath()));
					if (subDelta != null) {
						// 目前的逻辑，如果有一个引用包发生了变化，就必须重新检查所有的包，为了检查命名空间
						return true;
					}
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
