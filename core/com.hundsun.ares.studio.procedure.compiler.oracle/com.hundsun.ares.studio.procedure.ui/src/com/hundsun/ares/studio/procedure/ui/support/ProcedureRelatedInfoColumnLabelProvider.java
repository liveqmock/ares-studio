/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.support;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.procdure.ProcdurePackage;
import com.hundsun.ares.studio.procdure.RelatedInfo;
import com.hundsun.ares.studio.procedure.ui.assistant.ProcedurePageHelper;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.editor.blocks.DisplayItem;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * @author qinyuan
 *
 */
public class ProcedureRelatedInfoColumnLabelProvider extends EObjectColumnLabelProvider {
	
	private IARESProject project;
	protected EReference reference;

	public ProcedureRelatedInfoColumnLabelProvider(IARESResource resource, EReference reference, EStructuralFeature attribute) {
		super(attribute);
		
		this.project = resource.getARESProject();
		this.reference = reference;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.viewers.BaseEObjectColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {

		if(element instanceof RelatedInfo) {
			RelatedInfo relatedInfo = (RelatedInfo)element;
			EStructuralFeature feature = getEStructuralFeature(element);
			
			if(ProcdurePackage.Literals.PROCEDURE__RELATED_TABLE_INFO.equals(reference)){//关联表
				ReferenceInfo  referenceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project,IDatabaseRefType.Table,relatedInfo.getId(),true);
				if(null != referenceInfo) {
					TableResourceData table = (TableResourceData)referenceInfo.getObject();
					if(ProcdurePackage.Literals.RELATED_INFO__ID.equals(feature)){
						return relatedInfo.getId();
					}else if(ProcdurePackage.Literals.RELATED_INFO__COMMENT.equals(feature)) {
						return table.getChineseName();
					}else if(ProcdurePackage.Literals.RELATED_INFO__PATH.equals(feature)) {
						return referenceInfo.getResource().getResource().getProjectRelativePath().toOSString();
					}
				}
			}else if(ProcdurePackage.Literals.PROCEDURE__RELATED_BASIC_DATA_INFO.equals(reference)) {//关联基础数据
				ReferenceInfo  referenceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project,IBasicDataRestypes.singleTable,relatedInfo.getId(),true);
				if(null != referenceInfo) {
					BasicDataBaseModel table = (BasicDataBaseModel)referenceInfo.getObject();
					if(ProcdurePackage.Literals.RELATED_INFO__ID.equals(feature)){
						return relatedInfo.getId();
					}else if(ProcdurePackage.Literals.RELATED_INFO__COMMENT.equals(feature)) {
						return table.getChineseName();
					}else if(ProcdurePackage.Literals.RELATED_INFO__PATH.equals(feature)) {
						return referenceInfo.getResource().getResource().getProjectRelativePath().toOSString();
					}
				}
			}
			
		}
		return StringUtils.EMPTY;
	}
}
