package com.hundsun.ares.studio.atom.compiler.mysql.skeleton;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.skeleton.ISkeleton;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonFactory;



public class ServiceSkeletonFactory implements ISkeletonFactory {

	public ISkeleton createSkeleton(Object resource, Map<Object, Object> context) {
		if(resource instanceof IARESResource){
			return new ServiceSkeleton((IARESResource)resource,context);
		}
		return null;
	}

}
