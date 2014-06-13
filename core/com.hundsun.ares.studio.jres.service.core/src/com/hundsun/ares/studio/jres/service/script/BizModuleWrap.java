/**
 * 源程序名称：ServiceModuleWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.service.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.service.script;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.biz.constants.IBizResType;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IBizModuleWrap;

/**
 * @author sundl
 *
 */
public class BizModuleWrap implements IBizModuleWrap{
	
	private IARESModule module;
	
	public BizModuleWrap(IARESModule module) {
		this.module = module;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IBizModuleWrap#getServices(boolean)
	 */
	@Override
	public IBizServiceWrap[] getServices(boolean recursive) {
		List<IBizServiceWrap> services = new ArrayList<IBizServiceWrap>();
		IARESResource[] resources = module.getARESResources(IBizResType.Service, recursive);
		for (IARESResource res : resources) {
			services.add(new BizServiceWrap(res));
		}
		return services.toArray(new IBizServiceWrap[0]);
	}

}
