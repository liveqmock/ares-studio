package com.hundsun.ares.studio.jres.basicdata.logic.util;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

public class JRESResProviderUtil {

	/**
	 * 按类型获取资源
	 * @param project
	 * @param restype
	 * @return
	 */
	public static IARESResource[] getResoruceByType(IARESProject project,String restype){
		List<IARESResource> tList = new ArrayList<IARESResource>();
		List<ReferenceInfo> infoList = ReferenceManager.getInstance().getReferenceInfos(project, restype, true);
		for(ReferenceInfo referenceInfo:infoList){
			tList.add(referenceInfo.getResource());
		}
		return tList.toArray(new IARESResource[0]);
	}
	
	
	/**
	 * 获取元数据模型
	 * @param <T>
	 * @param project
	 * @param name
	 * @param restype
	 * @param clazz
	 * @return
	 */
	public static <T> T  getMetadataModel(IARESProject project,String name,String restype,Class<T> clazz){
		ReferenceInfo referenceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project, restype, name, true);
		if(referenceInfo!=null ){
			return (T)referenceInfo.getObject();
		}
		return null;
	}
	
	/**
	 * 获取资源模型
	 * @param <T>
	 * @param project
	 * @param name
	 * @param restype
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T  getResourceModel(IARESProject project,String name,String restype,Class<T> clazz)throws Exception{
		ReferenceInfo referenceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project, restype, name, true);
		if(referenceInfo!=null ){
			IARESResource resource = referenceInfo.getResource();
			return resource.getInfo(clazz);
		}
		return null;
	}
	
}
