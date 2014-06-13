/**
 * 源程序名称：IRefExtendPropertyProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.model.extend;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * 
 * @author sundl
 */
public interface IRefExtendPropertyProvider {
	void config(Map<String, String> config, IARESProject project);

	String getValue(ExtensibleModel model);
	void setValue(ExtensibleModel model, String value);
}
