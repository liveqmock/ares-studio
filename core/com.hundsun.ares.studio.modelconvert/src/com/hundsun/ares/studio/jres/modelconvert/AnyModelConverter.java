/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.jres.modelconvert;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * 对于AnyARESResourceInfo类型的序列化类
 * 
 * @author gongyf
 *
 */
public class AnyModelConverter extends AbstractModelConverter {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.IModelConverter2#read(com.hundsun.ares.studio.core.IARESResource)
	 */
	@Override
	public Object read(IARESResource resource) throws Exception {
		return new AnyARESResourceInfo();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.converter.IModelConverter2#write(com.hundsun.ares.studio.core.IARESResource, java.lang.Object)
	 */
	@Override
	public byte[] write(IARESResource resource, Object info) throws Exception {
		throw new UnsupportedOperationException("AnyModelConverter不支持序列化");
	}

}
