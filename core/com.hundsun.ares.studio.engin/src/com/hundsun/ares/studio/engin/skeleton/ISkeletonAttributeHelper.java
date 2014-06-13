package com.hundsun.ares.studio.engin.skeleton;

import java.util.Set;

/**
 * 蓝图占位处理类
 * @author lvgao
 *
 */
public interface ISkeletonAttributeHelper {

	/**
	 * 添加
	 * @param key
	 * @param value
	 */
	public void addAttribute(String key,String value);
	
	/**
	 * 添加所有
	 * @param key
	 * @param tset
	 */
	public void addAllAttribute(String key,Set<String> tset);
	
	/**
	 * 获取属性
	 * @param key
	 * @return
	 */
	public Set<String> getAttribute(String key);
}
