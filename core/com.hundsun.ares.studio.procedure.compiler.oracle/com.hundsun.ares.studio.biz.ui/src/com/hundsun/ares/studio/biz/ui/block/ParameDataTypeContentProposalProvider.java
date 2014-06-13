package com.hundsun.ares.studio.biz.ui.block;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.biz.core.BizUtil;
import com.hundsun.ares.studio.biz.core.ObjectRefTypes;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.cellEditor.AresContentProposalComparator;
import com.hundsun.ares.studio.ui.cellEditor.AresContentProposalProvider;

public class ParameDataTypeContentProposalProvider extends AresContentProposalProvider implements IContentProposalProvider{
	
	private IARESProject project;
	
	public ParameDataTypeContentProposalProvider(IARESProject project) {
		this.project = project;
	}

	@Override
	public IContentProposal[] getProposals(String contents, int position) {
	List<IContentProposal> proposals = new ArrayList<IContentProposal>();
		
		if (input != null) {
			for (Object object : input) {
				ReferenceInfo info = (ReferenceInfo) object;
				final String name = info.getRefName();
				if (StringUtils.containsIgnoreCase(name, contents)) {
					Object obj = info.getObject();
					String cName = null;
					if (obj instanceof BusinessDataType) {
						cName = ((BusinessDataType) obj).getChineseName();
					} /*else if (obj instanceof HSObject) {
						cName = ((HSObject) obj).getChineseName();
					}*/
					
					final String desc = cName;
					IContentProposal proposal = new IContentProposal() {
						
						@Override
						public String getLabel() {
							return name;
						}
						
						@Override
						public String getDescription() {
							return desc;
						}
						
						@Override
						public int getCursorPosition() {
							return StringUtils.length(name);
						}
						
						@Override
						public String getContent() {
							return name;
						}
					};
					proposals.add(proposal);
				}
			}
		}
		
		Collections.sort(proposals, new AresContentProposalComparator(contents));
		return proposals.toArray(new IContentProposal[0]);
	}

	@Override
	public void updateContent(Object element) {
		if (element instanceof Parameter) {
			Parameter param = (Parameter) element;
			List<ReferenceInfo> allDataTypes = new ArrayList<ReferenceInfo>();
			if (param.getParamType() == ParamType.STD_FIELD) {
				List<ReferenceInfo> bizTypes = ReferenceManager.getInstance().getReferenceInfos(project, IMetadataRefType.BizType, true);
				allDataTypes.addAll(bizTypes);
			} else if (param.getParamType() == ParamType.OBJECT) {
				if(BizUtil.hasStdObjList(project)){
					//（目前业务类型一栏如果存在对象标准字段都不可编辑）
				}else{
					//提示对象名（如UFT中对象）
					Collection<ReferenceInfo> objects = ObjectRefTypes.INSTANCE.getObjectReferences(project, true);
					allDataTypes.addAll(objects);
				}
			}else if (param.getParamType() == ParamType.NON_STD_FIELD) {
				List<ReferenceInfo> bizTypes = ReferenceManager.getInstance().getReferenceInfos(project, IMetadataRefType.BizType, true);
				allDataTypes.addAll(bizTypes);
			} else if (param.getParamType() == ParamType.PARAM_GROUP) {
				List<ReferenceInfo> objects = ReferenceManager.getInstance().getReferenceInfos(project, IBizRefType.Object, true);
				allDataTypes.addAll(objects);
			}
			setInput(allDataTypes.toArray());
		}
	}

}
