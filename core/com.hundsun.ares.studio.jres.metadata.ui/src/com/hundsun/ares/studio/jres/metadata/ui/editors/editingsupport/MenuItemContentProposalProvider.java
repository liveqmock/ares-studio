package com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.util.MenuUtils;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

public class MenuItemContentProposalProvider extends
		MetadataContentProposalProvider {

	IARESResource resource;
	
	public MenuItemContentProposalProvider(
			IContentProposalProviderHelper helper,IARESResource resource) {
		super(helper, IMetadataRefType.Menu, resource.getARESProject());
		this.resource = resource;
	}

	@Override
	public void updateContent(Object element) {
		ReferenceInfo[] referenceInfos = (ReferenceInfo[]) MenuUtils.getRefableMenuItems(resource).values().toArray();
		if (ArrayUtils.isEmpty(referenceInfos )) {
			setInput(new Object[0]);
		} else {
			// 过滤掉重复的标准字段对象
			
			Set<String> errorSet = new HashSet<String>();
			Set<String> processedSet = new HashSet<String>();
			Map<String, Object> processedObjects = new HashMap<String, Object>();
			
			for (ReferenceInfo referenceInfo : referenceInfos) {
					MetadataItem item = (MetadataItem) referenceInfo.getObject();
					if (!errorSet.contains(item.getName())) {
						if (processedSet.contains(item.getName())) {
							processedSet.remove(item.getName());
							errorSet.add(item.getName());
							processedObjects.remove(item.getName());
						} else {
							processedSet.add(item.getName());
							processedObjects.put(item.getName(), referenceInfo);
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
