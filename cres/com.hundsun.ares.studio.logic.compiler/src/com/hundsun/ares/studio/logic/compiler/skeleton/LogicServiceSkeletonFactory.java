/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.skeleton;

import java.util.Map;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.skeleton.ISkeleton;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonFactory;

/**
 * @author qinyuan
 *
 */
public class LogicServiceSkeletonFactory implements ISkeletonFactory {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.skeleton.ISkeletonFactory#createSkeleton(java.lang.Object, java.util.Map)
	 */
	@Override
	public ISkeleton createSkeleton(Object resource, Map<Object, Object> context) {
		if(resource instanceof IARESResource){
			return new LogicServiceSkeleton((IARESResource)resource,context);
		}
		return null;
	}

}
