/**
 * 源程序名称：JRESContextManager.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.context;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.core.util.ResourcesUtil;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author sundl
 * @Deprecated 使用ReferenceManager和相关的方法代替
 */
@Deprecated 
public class JRESContextManager{
//	private static Map<IARESProject, StatisticProviderProxy> statisticMap = new HashMap<IARESProject, StatisticProviderProxy>();
	
	
	/**
	 * 获取统计信息提供类
	 * 包括引用工程
	 * @param project
	 * @return
	 * @Deprecated 使用ReferenceManager
	 */
	@Deprecated
	public static IResStatisticProvider getStatisticProvider(IARESProject project){
		return new JRESStatisticProvider(project, true);
	}
	
//	/**
//	 * 清除指定project的代理proxy,并执行代理的缓存清理操作（主要是删除其创建的View）
//	 * @param aresProject
//	 */
//	public static void removeStatisticProvider(IARESProject aresProject) {
//		StatisticProviderProxy proxy = statisticMap.get(aresProject);
//		if (proxy != null) {
//			proxy.dispose();
//		}
//		statisticMap.remove(aresProject);
//	}
	
	/**
	 * 获取统计信息提供类
	 * @param project
	 * @return
	 */
	public static IResStatisticProvider getCurrentStatisticProvider(IARESProject project){
		return new JRESStatisticProvider(project, false);
	}
	
	/**
	 * 获取引用上下文
	 * @param project
	 * @return
	 */
	public static IResReferenceProvider getReferencProvider(IARESProject project){
		return new JRESReferencProvider(project);
	}
	
	/**
	 * 查找一个数据
	 * @param bundle
	 * @param name
	 * @param type
	 * @return
	 * @Deprecated 使用ReferenceManager相关的接口代替
	 */
	@Deprecated
	public static <T extends EObject> Pair<T , IARESResource> findResource(IARESBundle bundle, String name, String type, boolean useNameSpace) {
		IARESProject project = ResourcesUtil.getARESProject(bundle);
		
		String nameSpace = useNameSpace ? bundle.getInfo().getId() : IResourceTable.Scope_IGNORE_NAMESPACE;
		// FIXME JRES目前不使用命名空间
		List<ReferenceInfo> infoListList =  ReferenceManager.getInstance().getReferenceInfos(project, type, name, true);
		if (infoListList!=null && infoListList.size()>0) {
			return new Pair<T, IARESResource>((T)infoListList.get(0).getObject(), infoListList.get(0).getResource());
		}
		return null;
	}
}
