package com.hundsun.ares.studio.jres.service.ui.refactor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;

import com.hundsun.ares.studio.biz.StandardObjField;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.biz.constants.IBizResType;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.internal.ui.refactoring.changes.ReferenceProviderRefChange;
import com.hundsun.ares.studio.jres.metadata.ui.refactor.MetadataItemAndAresResourceModel;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.model.reference.RelationInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.refactoring.AresElementRenameParicipant;

public class StdObjRenamePaticipant extends AresElementRenameParicipant {
	
	private MetadataItemAndAresResourceModel model;
	private MetadataItem item; 		//重命名的item
	
	@Override
	protected boolean initialize(Object element) {
		if (!getArguments().getUpdateReferences())
			return false;
		
		if(element instanceof MetadataItemAndAresResourceModel) {
			model = (MetadataItemAndAresResourceModel)element;
			this.element = model.getRes();
			item = model.getItem();
			if (item instanceof StandardObjField) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String getName() {
		return item.getName();
	}
	
	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		List<Change> changes = new ArrayList<Change>();
		String newName = getArguments().getNewName();
		IARESProject project = model.getRes().getARESProject();
		List<RelationInfo> relations = ReferenceManager.getInstance().getRelationInfoByTarget(IBizRefType.Std_Obj, item.getName(), project);
		for (RelationInfo rel : relations) {
			if (rel.getHostResource().getType().equals(IBizResType.Service)) {
				changes.add(new ReferenceProviderRefChange(rel.getHostResource(), IBizRefType.Std_Obj, item.getName(), newName));
			}
		}
		if (changes.isEmpty())
			return null;
		
		return new CompositeChange("服务资源中引用的对象标准字段更新", changes.toArray(new Change[0]));
	}

}
