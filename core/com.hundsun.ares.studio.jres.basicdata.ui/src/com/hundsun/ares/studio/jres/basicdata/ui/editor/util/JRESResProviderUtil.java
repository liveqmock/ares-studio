package com.hundsun.ares.studio.jres.basicdata.ui.editor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.context.JRESContextManager;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;

public class JRESResProviderUtil {

	/**
	 * 按类型获取资源
	 * @param project
	 * @param restype
	 * @return
	 */
	public static IARESResource[] getResoruceByType(IARESProject project,String restype){
		List<IARESResource> tList = new ArrayList<IARESResource>();
		Object[]  results = JRESContextManager.getStatisticProvider(project).getResouceByType(restype);
		for(Object obj:results){
			Map<Object, Object> tmap = (Map<Object, Object>)obj;
			tList.add((IARESResource)tmap.get(IResourceTable.TARGET_RESOURCE));
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
		Object[] objs =  JRESContextManager.getStatisticProvider(project).getResouce(name, IResourceTable.Scope_IGNORE_NAMESPACE, restype);
		if(objs.length > 0){
			Map<Object,Object> tmap = (Map<Object,Object>)objs[0];
			return (T)tmap.get(IResourceTable.TARGET_OWNER);
		}
		return null;
	}
	
}
