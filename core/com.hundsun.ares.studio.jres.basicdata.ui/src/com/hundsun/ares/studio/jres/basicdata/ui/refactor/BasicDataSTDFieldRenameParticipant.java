package com.hundsun.ares.studio.jres.basicdata.ui.refactor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
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
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.ui.refactor.MetadataItemAndAresResourceModel;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.model.reference.RelationInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * 
 * @deprecated 废弃掉此方案，但暂时将实现保留
 * @author Administrator
 *
 */
public class BasicDataSTDFieldRenameParticipant extends RenameParticipant {

	private MetadataItem item; 		//重命名的item
	private MetadataItemAndAresResourceModel model;
	private ARESResource resource;		//重命名的item所在的资源
	
	public BasicDataSTDFieldRenameParticipant() {
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
	private Set<IARESResource> getARESResource (){
		Set<IARESResource> reses = new HashSet<IARESResource>();
		List<RelationInfo> relationInfos = ReferenceManager.getInstance().getRelationInfoByTarget(IMetadataRefType.StdField, getName(), resource.getARESProject());
		for (RelationInfo relation : relationInfos) {
			IARESResource res = relation.getHostResource();
			if (!res.isReadOnly() && shouldChange(res.getType())) {
				reses.add(relation.getHostResource());
			}
		}
		return  reses;
	}
	
	
	/**
	 * 只修改基础数据文件
	 * @param restype
	 * @return
	 */
	private boolean shouldChange(String restype){
		if(StringUtils.equals(IBasicDataRestypes.singleTable, restype)
				||StringUtils.equals(IBasicDataRestypes.MasterSlaveTable, restype)
				||StringUtils.equals(IBasicDataRestypes.MasterSlaveLinkTable, restype)){
			return true;
		}
		return false;
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
			result.add(new BasicDataSTDFieldChange(res,item.getName(),newName));
			changes.add(result);
		}
		return changes;
	}
	
	
	
}
