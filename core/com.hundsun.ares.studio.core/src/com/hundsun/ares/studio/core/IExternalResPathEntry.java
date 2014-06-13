/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import com.hundsun.ares.studio.core.registry.IRespathProviderDescriptor;

/**
 * 外部提供的RespathEntry。比如Maven提供的，这个接口用于区分依赖项的来源，比如是工具的.respath文件还是
 * 由其他插件通过IRespathProvider扩展点提供的。
 * @author sundl
 */
public interface IExternalResPathEntry extends IResPathEntry{
	/**
	 * 返回这个RespathEntry的来源
	 * @return
	 */
	IRespathProviderDescriptor getProvider();
	
	public void setProvider(IRespathProviderDescriptor provider);
}
