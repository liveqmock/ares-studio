/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import com.hundsun.ares.studio.core.model.extendable.IExtendAbleModel;

/**
 * 
 * @author sundl
 */
public interface IARESBundleInfo extends IExtendAbleModel {

	/**
	 * id
	 * @return
	 */
	String getId();
	
	/**
	 * 名字
	 * @return
	 */
	String getName();
	
	/**
	 * 版本号
	 * @return
	 */
	String getVersion();
	
	/**
	 * 提供者(责任人)的名字，可选。
	 * @return
	 */
	String getProvider();
	String getNote();
	String getPublisher();
	String getPubTime();
	
	String getString(String name);
	String getString(String name, String defaultValue);
	int getInt(String name);
	int getInt(String name, int defaultValue);
	boolean getBoolean(String name);
	boolean getBoolean(String name, boolean defaultValue);
	
	void setValue(String name, String value);
	void setValue(String name, int value);
	void setValue(String name, boolean value);
	
	/**
	 * 获取依赖项列表
	 * @return
	 */
	IDependenceDescriptor[] getDependencies();

	public abstract String getContact();
}
