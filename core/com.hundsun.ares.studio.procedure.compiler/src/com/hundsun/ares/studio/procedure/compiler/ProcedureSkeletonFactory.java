package com.hundsun.ares.studio.procedure.compiler;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.skeleton.ISkeleton;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonFactory;
import com.hundsun.ares.studio.procedure.compiler.skeleton.ProcedureSkeleton;

public class ProcedureSkeletonFactory implements ISkeletonFactory {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeletonFactory#createSkeleton(java.lang.Object, java.util.Map)
	 */
	@Override
	public ISkeleton createSkeleton(Object resource, Map<Object, Object> context) {
		if(resource instanceof IARESResource){
			return new ProcedureSkeleton((IARESResource)resource,context);
		}
		return null;
	}

}
