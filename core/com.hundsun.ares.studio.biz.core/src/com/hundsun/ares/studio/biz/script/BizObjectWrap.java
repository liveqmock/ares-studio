/**
 * 源程序名称：ObjectWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.script;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.script.api.biz.IAttributeWrap;
import com.hundsun.ares.studio.jres.script.api.biz.IBizObjectWrap;
import com.hundsun.ares.studio.jres.script.base.ResourceWrapBase;

/**
 * @author sundl
 *
 */
public class BizObjectWrap extends ResourceWrapBase<ARESObject> implements IBizObjectWrap {

	private IAttributeWrap[] attributes;
	public BizObjectWrap(IARESResource resource) {
		super(resource);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.base.ResourceWrapBase#getInfoClass()
	 */
	@Override
	public Class<ARESObject> getInfoClass() {
		return ARESObject.class;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IBizObjectWrap#getAttributes()
	 */
	@Override
	public IAttributeWrap[] getAttributes() {
		if (attributes == null) {
			List<IAttributeWrap> attrs = new ArrayList<IAttributeWrap>();
			for (Parameter p : getOriginalInfo().getProperties()) {
				attrs.add(new AttributeWrap(p, resource));
			}
			attributes = attrs.toArray(new IAttributeWrap[0]);
		}
		return attributes;
	}
	
	public String getChineseName() {
		return getOriginalInfo().getChineseName();
	}

}
