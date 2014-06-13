/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.assist.JresResourceRefConentProposalPovider;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

/**
 * @author qinyuan
 *
 */
public class ProcedureRelatedBasicDataInfoContentProposalProvider extends JresResourceRefConentProposalPovider {

	public ProcedureRelatedBasicDataInfoContentProposalProvider(IContentProposalProviderHelper helper, String resType, IARESProject project) {
		super(helper, resType, project);
	}

	@Override
	protected boolean filter(Object inputItem, Object element) {
		if (inputItem instanceof Map && element instanceof BasicDataBaseModel) {
			@SuppressWarnings("rawtypes")
			Map map = (Map) inputItem;
			IARESResource res = (IARESResource) map.get(IResourceTable.TARGET_RESOURCE);
			BasicDataBaseModel item = (BasicDataBaseModel) map.get(IResourceTable.TARGET_OWNER);
			BasicDataBaseModel elementItem = (BasicDataBaseModel) element;
			if (res.getARESProject().equals(project) && item.getName().equals(elementItem.getName()) ) { 
				return false;
			}
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.contentassist.JresResourceRefConentProposalPovider#updateContent(java.lang.Object)
	 */
	@Override
	public void updateContent(Object element) {
		List<ReferenceInfo> referenceInfoList =ReferenceManager.getInstance().getReferenceInfos(project,resType,true);
		if (referenceInfoList==null || referenceInfoList.size()==0) {
			setInput(new Object[0]);
			
		} else{
            // 过滤掉重复的标准字段对象
			
			Set<String> errorSet = new HashSet<String>();
			Set<String> processedSet = new HashSet<String>();
			Map<String, Object> processedObjects = new HashMap<String, Object>();
			for (ReferenceInfo refInfo : referenceInfoList) {
				Object object =refInfo.getObject();
				if (object instanceof BasicDataBaseModel) {
					BasicDataBaseModel item = (BasicDataBaseModel) object;
					if (!errorSet.contains(item.getName())) {
						if (processedSet.contains(item.getName())) {
							processedSet.remove(item.getName());
							errorSet.add(item.getName());
							processedObjects.remove(item.getName());
						} else {
							processedSet.add(item.getName());
							processedObjects.put(item.getName(), refInfo);
						}
					}
				}
			
			}
			// 因为createProposal()得不到element，所以提示元素的过滤必须在这里就处理掉
			List<Object> inputItems = new ArrayList<Object>();
			for (Object inputItem : processedObjects.values().toArray()) {
				if (filter(inputItem, element))
					inputItems.add(inputItem);
			}
			
			setInput(inputItems.toArray());
		}
	}
}
