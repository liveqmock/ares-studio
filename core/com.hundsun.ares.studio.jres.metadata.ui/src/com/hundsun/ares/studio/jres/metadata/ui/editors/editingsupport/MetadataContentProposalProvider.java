/**
 * 源程序名称：MetadataContentProposalProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.assist.JresResourceRefConentProposalPovider;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

/**
 * 提示元数据引用的时候使用的ProposalContentProvider
 * @author sundl
 */
public class MetadataContentProposalProvider extends JresResourceRefConentProposalPovider {

	public MetadataContentProposalProvider(IContentProposalProviderHelper helper, String resType, IARESProject project) {
		super(helper, resType, project);
	}

	@Override
	protected boolean filter(Object inputItem, Object element) {
		if (inputItem instanceof Map && element instanceof MetadataItem) {
			@SuppressWarnings("rawtypes")
			Map map = (Map) inputItem;
			IARESResource res = (IARESResource) map.get(IResourceTable.TARGET_RESOURCE);
			MetadataItem item = (MetadataItem) map.get(IResourceTable.TARGET_OWNER);
			MetadataItem elementItem = (MetadataItem) element;
			if (res.getARESProject().equals(project) && item.getName().equals(elementItem.getName()) 
					&& item.getParent().getFullyQualifiedName().equals(elementItem.getParent().getFullyQualifiedName())) { //同资源才需要过滤
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
				if (object instanceof MetadataItem) {
					MetadataItem item = (MetadataItem) object;
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
		
		
		if(helper instanceof MetadataContentProposalHelper){
			((MetadataContentProposalHelper)helper).initDuplicateList(input);
		}
	}

}
