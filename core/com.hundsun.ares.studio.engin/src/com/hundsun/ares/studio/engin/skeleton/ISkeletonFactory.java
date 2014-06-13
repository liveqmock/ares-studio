package com.hundsun.ares.studio.engin.skeleton;

import java.util.Map;

public interface ISkeletonFactory {

	/**
	 * ´´½¨À¶Í¼
	 * @param resource
	 * @param context
	 * @return
	 */
	public ISkeleton createSkeleton(Object resource,Map<Object, Object> context);
}
