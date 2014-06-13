package com.hundsun.ares.studio.jres.metadata.ui.refactor;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.RenameParticipant;

import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.internal.core.ARESModelManager;
import com.hundsun.ares.studio.internal.core.ARESProject;
import com.hundsun.ares.studio.jres.metadata.constant.IMDConstant;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.model.metadata.Operation;

/**
 * @author wangxh
 *
 */
public class ProjectRenameParticipant extends RenameParticipant {

	private IProject project;
	private IPath newPath;
	
	@Override
	protected boolean initialize(Object element) {
		if(element instanceof IARESProject){
			project = ((IARESProject)element).getProject();
		}
		if (element instanceof IProject) {
			project = (IProject) element;
		}
		if(project != null){
			// 只处理ARESProject
			if (ARESProject.hasARESNature(project)) {
				String newName = getArguments().getNewName();
				newPath = project.getFullPath().removeLastSegments(1).append(newName);
				return true;
			}
		}
		return false;
	}

	@Override
	public String getName() {
		return "更新元数据脚本路径";
	}

	@Override
	public RefactoringStatus checkConditions(IProgressMonitor pm,
			CheckConditionsContext context) throws OperationCanceledException {
		return new RefactoringStatus();
	}

	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {
		CompositeChange changes = new CompositeChange("更新元数据脚本路径");
		//元数据操作列表的脚本可以选择其他工程的脚本，所以要查找整个工作区间的工程
		for (IARESProject aresProj : ARESModelManager.getManager().getModel().getARESProjects()) {
			for(IARESModuleRoot root : aresProj.getModuleRoots()){
				if(StringUtils.equals(IMDConstant.METADATA_MODULE_ROOT_ID, root.getType())){
					for(IARESResource res : root.getResources()){
						MetadataResourceData info = res.getInfo(MetadataResourceData.class);
						if(info != null && hasReference(info)){
							changes.add(new ProjectChange(res,project.getFullPath().toString(),newPath.toString()));
						}
					}
				}
			}
		}
		return changes;
	}
	
	//是否引用了待修改工程里的脚本
	private boolean hasReference(MetadataResourceData info) {
		EList<Operation> ops = info.getOperations();
		for(Operation op : ops){
			String file = op.getFile();
			if(StringUtils.isNotBlank(file) && project.getFullPath().isPrefixOf(Path.fromPortableString(file))){
				return true;
			}
		}
		return false;
	}
}
