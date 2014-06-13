package com.hundsun.ares.studio.jres.metadata.resources.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IObjectProvider;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.model.metadata.MenuItem;
import com.hundsun.ares.studio.jres.model.metadata.MenuList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.jres.model.metadata.util.MenuUtils;
import com.hundsun.ares.studio.model.reference.ReferenceFactory;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.EMFReferenceObjectProvider;
import com.hundsun.ares.studio.reference.IReferenceInfoProvider;

public class MenuReferenceInfoProvider implements IReferenceInfoProvider {

	
	@Override
	public List<ReferenceInfo> getReferenceInfos(IARESResource resource,
			Map<Object, Object> context) {
		List<ReferenceInfo> infoList = new ArrayList<ReferenceInfo>();
		try {
			MenuList info = resource.getInfo(MenuList.class);
			if(info==null){
				return infoList;
			}
			List<MenuItem> items = MenuUtils.getMenuItems(info);
			for (int i=0 ; i<items.size(); i++) {
				MenuItem item = items.get(i);
				ReferenceInfo refInfo = ReferenceFactory.eINSTANCE.createReferenceInfo();
				
				refInfo.setResource(resource);
				refInfo.setRefName(item.getName());
				refInfo.setRefType(IMetadataRefType.Menu);
				refInfo.setRefNamespace(resource.getBundle().getId());
				refInfo.setObjectProvider(new MenuReferenceObjectProvider(i));
				
				infoList.add(refInfo);
			}
			
			List<Function> funcs = info.getFunctions();
			for(int i=0 ; i<funcs.size(); i++){
				Function func = funcs.get(i);
				ReferenceInfo refInfo = ReferenceFactory.eINSTANCE.createReferenceInfo();
				
				refInfo.setResource(resource);
				refInfo.setRefName(func.getName());
				refInfo.setRefType(IMetadataRefType.Menu_Function);
				refInfo.setRefNamespace(resource.getBundle().getId());
				refInfo.setObjectProvider((new EMFReferenceObjectProvider(
						CorePackage.Literals.JRES_RESOURCE_INFO, MetadataPackage.Literals.MENU_LIST__FUNCTIONS, i)));
				
				infoList.add(refInfo);
			}
			
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return infoList;
	}
	
	private class MenuReferenceObjectProvider implements IObjectProvider{

		private Logger logger = Logger.getLogger(MenuReferenceObjectProvider.class);
		int index;
		public MenuReferenceObjectProvider(int index) {
			this.index = index;
		}

		@Override
		public Object getObject(IARESResource resource) {
			try {
				MenuList info = resource.getInfo(MenuList.class);
				return MenuUtils.getMenuItems(info).get(index);
			} catch (ARESModelException e) {
				e.printStackTrace();
				logger.error(String.format("引用信息读取资源%s具体info的时候出错...", resource.getElementName()), e);
			}
			return null;
		}
		
	}

}
