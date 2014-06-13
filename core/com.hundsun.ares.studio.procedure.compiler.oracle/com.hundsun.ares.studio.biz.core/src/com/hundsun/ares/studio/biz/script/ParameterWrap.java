/**
 * 源程序名称：ParameterWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.service.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.script;

import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.script.api.biz.IParameterWrap;

/**
 * @author sundl
 *
 */
public class ParameterWrap extends AttributeWrap implements IParameterWrap{

	/**
	 * @param t
	 * @param resource
	 */
	public ParameterWrap(Parameter p, IARESResource resource) {
		super(p, resource);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IParameterWrap#getFlag()
	 */
	@Override
	public String getFlag() {
		return getOriginalInfo().getFlags();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IParameterWrap#setFlag()
	 */
	@Override
	public void setFlag(String flag) {
		getOriginalInfo().setFlags(flag);
	}
	
}
