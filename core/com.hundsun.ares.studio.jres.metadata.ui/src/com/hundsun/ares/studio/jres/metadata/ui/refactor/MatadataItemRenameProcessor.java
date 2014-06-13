/**
 * 源程序名称：MatadataItemRenameProcessor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.refactor;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.ParticipantManager;
import org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant;
import org.eclipse.ltk.core.refactoring.participants.RenameArguments;
import org.eclipse.ltk.core.refactoring.participants.SharableParticipants;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.ResourceTypeMapping;
import com.hundsun.ares.studio.core.context.INamespaceHelper;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;
import com.hundsun.ares.studio.internal.core.ARESResource;
import com.hundsun.ares.studio.internal.ui.refactoring.ARESRenameProcessor;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.impl.ErrorNoListImpl;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;


/**
 * @author qinyuan
 *
 */
public class MatadataItemRenameProcessor extends ARESRenameProcessor{
	
	private MetadataItem item;
	private EditingDomain editingDomain;

	/**
	 * @param editingDomain 
	 * @param element
	 */
	public MatadataItemRenameProcessor(MetadataItem item,IARESResource aresResource, EditingDomain editingDomain) {
		super(aresResource);
		this.item = item;
		this.editingDomain = editingDomain;
		if(null != item)
			this.newElementName = item.getName();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.ARESRenameProcessor#getElements()
	 */
	@Override
	public Object[] getElements() {
		return new Object[] {item};
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return "com.hundsun.ares.studio.jres.metadata.ui.refactor.metadataitem.rename";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#getProcessorName()
	 */
	@Override
	public String getProcessorName() {
		return "重命名元数据字段";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#isApplicable()
	 */
	@Override
	public boolean isApplicable() throws CoreException {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#checkInitialConditions(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public RefactoringStatus checkInitialConditions(IProgressMonitor pm)
			throws CoreException, OperationCanceledException {
		return new RefactoringStatus();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.ARESRenameProcessor#checkFinalConditions(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext)
	 */
	@Override
	public RefactoringStatus checkFinalConditions(IProgressMonitor pm,
			CheckConditionsContext context) throws CoreException,
			OperationCanceledException {
		renameArguments = new RenameArguments(newElementName, getUpdateReferences());	
		return new RefactoringStatus();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#createChange(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {
		String oldName = item.getName();
		String newName = getNewElementName();
		
		return new MetadataItemResourceChange(oldName,newName,item,(IARESResource)element,editingDomain);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.ARESRenameProcessor#getCurrentElementName()
	 */
	@Override
	public String getCurrentElementName() {
		return item.getName();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.ARESRenameProcessor#checkNewElementName(java.lang.String)
	 */
	@Override
	public RefactoringStatus checkNewElementName(String name) {
		
		RefactoringStatus status = new RefactoringStatus();
		
		if(item.eContainer() instanceof ErrorNoListImpl) {
			Pattern pattern = Pattern.compile("[A-Z_0-9]{1,30}");
			boolean match = pattern.matcher(name).matches();
			if (!match) 
				status.addFatalError("名字不合法( " + pattern.toString() + " )");
		}else {
			Pattern pattern = Pattern.compile("[A-Za-z_0-9]{1,30}");
			boolean match = pattern.matcher(name).matches();
			if (!match) 
				status.addFatalError("名字不合法( " + pattern.toString() + " )");
		}
		
		if (!status.hasFatalError()) {
			
			String namespace = INamespaceHelper.INSTANCE.getResourceNamespace((IARESResource)element);
			String restype = ResourceTypeMapping.getInstance().getReferenceType(((IARESResource)element).getType());
			
			if(isResouceExist(((IARESResource)element).getARESProject(),getNewElementName(), namespace, restype)) {
				status.merge(RefactoringStatus.createFatalErrorStatus("资源" + getNewElementName() + "已经存在!"));
			}
			
		}
		return status;
	}

	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.ARESRenameProcessor#loadParticipants(org.eclipse.ltk.core.refactoring.RefactoringStatus, org.eclipse.ltk.core.refactoring.participants.SharableParticipants)
	 */
	@Override
	public RefactoringParticipant[] loadParticipants(RefactoringStatus status,
			SharableParticipants sharedParticipants) throws CoreException {
		if (item != null && element != null) {
			
			MetadataItemAndAresResourceModel m = new MetadataItemAndAresResourceModel(item,(ARESResource)element);
			
			IResource resource = element.getResource();
			String[] natures = resource.getProject().getDescription().getNatureIds();
			return ParticipantManager.loadRenameParticipants(status, this, m, renameArguments, natures, sharedParticipants);
		}
	
		return null;
	}

	// 2012-03-22 sundl 添加接口以后关联修改，空实现，目前元数据的重命名不会调用这个方法。
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.INameUpdating#getNewElement()
	 */
	@Override
	public IARESElement getNewElement() {
		return null;
	}
	private boolean isResouceExist(IARESProject project,String name,String namespace, String restype ){
		ReferenceInfo referenceInfo = null;
		if (StringUtils.equals(namespace, IResourceTable.Scope_IGNORE_NAMESPACE)) {
			referenceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project, restype, name, true);
		} else {
			referenceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project, restype, name, namespace, true);
		}
		return referenceInfo!=null;
	}
}
