/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.aresaction;

import com.hundsun.ares.studio.core.registry.ICommonDescriptor;

/**
 * ARES操作的注册描述信息接口
 * @author sundl
 */
public interface IARESActionDescriptor extends ICommonDescriptor {
	
	String getResType();
	
	IARESAction createAction();
}
