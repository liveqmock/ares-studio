/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import java.util.List;

/**
 * 依赖项，比如一个引用包，或者一个依赖项目。
 * @author sundl
 */
public interface IDependencyUnit{

	// 查找资源相关
	IARESModuleRoot[] getRoots() throws ARESModelException;
	
	/**
	 * 这个依赖项的依赖描述； 这里返回的仅仅是一个依赖的描述。
	 * 这里只描述需要一个ID和Version确定的依赖项，在具体的环境中可以解析成不同的IDependencyUnit
	 * @return
	 */
	public abstract List<IDependenceDescriptor> getDependencyDescriptors();
	
	/**
	 * 依赖的IDependencyUnit对象
	 * @return
	 */
	//public List<IDependencyUnit> getDependencies();

	// 基本信息
	public abstract String getPublishTime();

	public abstract String getNote();

	public abstract String getName();

	public abstract String getPublisher();

	public abstract String getContact();

	public abstract String getProvider();

	public abstract String getVersion();

	public abstract String getId();
	
	public abstract String getType();
	
	/**
	 * 描述信息，比如"引用资源包- lib.jar", "项目- project1"
	 * @return 
	 */
	public abstract String getDescriptionStr();
}
