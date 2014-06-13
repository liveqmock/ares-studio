package com.hundsun.ares.studio.engin.skeleton;

public interface ISkeletonInput {

	/**
	 * 获取蓝图的类型
	 * @return
	 */
	public String  getType();
	
	/**
	 * 获取蓝图输入
	 * @return
	 */
	public Object getInput();
}
