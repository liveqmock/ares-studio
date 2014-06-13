/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.registry;

import com.hundsun.ares.studio.core.IRespathProvider;

/**
 * 描述Res-path Provider.
 * @author sundl
 */
public interface IRespathProviderDescriptor extends ICommonDescriptor {

	IRespathProvider getProvider();
}
