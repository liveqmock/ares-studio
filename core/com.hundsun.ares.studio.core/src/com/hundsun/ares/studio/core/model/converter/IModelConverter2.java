/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.model.converter;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * 
 * @author gongyf
 */
public interface IModelConverter2 extends IModelConverter {
	
	/**
	 * 读取资源内容，应该返回注册的info-class类型的对象
	 * @param resource
	 * @return
	 * @throws Exception
	 */
	public Object read(IARESResource resource) throws Exception;

	
	/**
	 * 写入资源内容，传入的info不一定是info-class类型的对象
	 * @param resource
	 * @param info
	 * @throws Exception
	 */
	public byte[] write(IARESResource resource, Object info) throws Exception;
}
