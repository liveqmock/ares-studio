package com.hundsun.ares.studio.jres.metadata.ui.refactor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.RenameParticipant;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.internal.core.ARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.utils.MetadataHelper;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.model.reference.RelationInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

public class MetadataItemRenameParticipant extends RenameParticipant {

	private MetadataItem item; 		//重命名的item
	private MetadataItemAndAresResourceModel model;
	private ARESResource resource;		//重命名的item所在的资源
	
	public MetadataItemRenameParticipant() {
	}

	@Override
	protected boolean initialize(Object element) {
		
		if(element instanceof MetadataItemAndAresResourceModel) {
			model = (MetadataItemAndAresResourceModel)element;
			item = model.getItem();
			resource = model.getRes();
			return true;
		}
		
		return false;
	}

	@Override
	public String getName() {
		return item.getName();
	}

	@Override
	public RefactoringStatus checkConditions(IProgressMonitor pm,
			CheckConditionsContext context) throws OperationCanceledException {
		return new RefactoringStatus();
	}

	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {
		IResDescriptor desc = ARESResRegistry.getInstance().getResDescriptor(resource);
		CompositeChange result = new CompositeChange(desc.getName());
		result.addAll(createReferenceChange().toArray(new Change[0]));
		return result;
	}

	/**
	 * 根据引用体系取得相关联的资源，用SET数组去重复
	 * 
	 * @return
	 */
	private Set<IARESResource> getARESResource() {
		Set<IARESResource> reses = new HashSet<IARESResource>();
		List<RelationInfo> relationInfos = ReferenceManager
				.getInstance()
				.getRelationInfoByTarget(
						MetadataHelper.getRefTypeByResource(resource.getType()),
						getName(), resource.getARESProject());
		for (RelationInfo relation : relationInfos) {
			IARESResource res = relation.getHostResource();
			if (!res.isReadOnly()) {
				reses.add(relation.getHostResource());
			}
		}
		return reses;
	}
	
	/**
	 * 创建所有引用资源的change对象
	 * 
	 * @return
	 */
	private List<Change> createReferenceChange(){
		List<Change> changes = new ArrayList<Change>();
		String newName = getArguments().getNewName();
		for (IARESResource res : getARESResource()) {
			IResDescriptor desc = ARESResRegistry.getInstance().getResDescriptor(res);
			CompositeChange result = new CompositeChange(desc.getName() + ":"+res.getFullyQualifiedName());
			result.add(new MetadataItemRefResourceChange(item.getName(),newName,resource , res));
			changes.add(result);
		}
		return changes;
	}
	
	
	
}
