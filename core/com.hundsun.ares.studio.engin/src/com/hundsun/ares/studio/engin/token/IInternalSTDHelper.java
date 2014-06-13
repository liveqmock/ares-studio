package com.hundsun.ares.studio.engin.token;

public interface IInternalSTDHelper {

	/**
	 * 是否是标准字段类型的内部变量
	 * @param paraname
	 * @return
	 */
	public boolean isInternalSTD(String paraname);
	
	/**
	 * 是否是使用过的标准类型参数（标准字段和标准数据类型的临时变量）
	 * @param paraname
	 * @return
	 */
	public boolean isUsedSTD(String paraname);
	
	/**
	 * 是否是临时记录
	 * @param paraname
	 * @return
	 */
	public boolean isInternalRecord(String paraname);
	
	/**
	 * 是否是组件类型内部变量
	 * @param paraname
	 * @return
	 */
	public boolean isInternalComponent(String paraname);
	
	/**
	 * 是否是组件类型内部变量的类型
	 * @param paraname
	 * @return
	 */
	public String getInternalComponentType(String paraname);

	/**
	 * 是否是组件类型输入输出变量
	 * @param paraname
	 * @return
	 */
	public boolean isInOutComponent(String paraname);
	
	/**
	 * 获取临时变量类型
	 * @param paraname
	 * @return
	 */
	public String getInternalSTDType(String paraname);
}
