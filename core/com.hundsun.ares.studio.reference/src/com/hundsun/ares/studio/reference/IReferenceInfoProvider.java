/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.reference;

import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;

/**
 * @author gongyf
 *
 */
public interface IReferenceInfoProvider {
	
	/**
	 * 将一个ARES资源转化为引用信息
	 * @param resource
	 * @return
	 */
	List<ReferenceInfo> getReferenceInfos(IARESResource resource, Map<Object, Object> context);
}
