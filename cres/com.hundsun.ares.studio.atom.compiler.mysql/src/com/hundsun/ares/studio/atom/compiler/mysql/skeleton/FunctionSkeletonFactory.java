package com.hundsun.ares.studio.atom.compiler.mysql.skeleton;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.skeleton.ISkeleton;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonFactory;

public class FunctionSkeletonFactory implements ISkeletonFactory{

	@Override
	public ISkeleton createSkeleton(Object resource, Map<Object, Object> context) {
		if(resource instanceof IARESResource){
			return new FunctionSkeleton((IARESResource)resource,context);
		}
		return null;
	}

}
