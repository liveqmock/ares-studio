package com.hundsun.ares.studio.logic.compiler.skeleton;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.skeleton.ISkeleton;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonFactory;

public class LogicFunctionSkeletonFactory implements ISkeletonFactory {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeletonFactory#createSkeleton(java.lang.Object, java.util.Map)
	 */
	@Override
	public ISkeleton createSkeleton(Object resource, Map<Object, Object> context) {
		if(resource instanceof IARESResource){
			return new LogicFunctionSkeleton((IARESResource)resource,context);
		}
		return null;
	}

}
