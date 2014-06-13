/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.builder;

import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESResource;

/**
 * 
 * @author sundl
 */
public class AresProjectBuilderUtil {
	
	private static final Logger logger = Logger.getLogger(AresProjectBuilderUtil.class);
		
	public static void markProblems(IARESResource res, Collection<IARESProblem> problems) {
		IResource resource = res.getResource();
		markProblems(resource, problems);
	}
	
	public static void markProblems(IResource res, Collection<IARESProblem> problems) {
		for (IARESProblem problem : problems) {
			try {
				IMarker marker = res.createMarker(problem.getType());
				Map attritutes = problem.getAttributes();
				for (Object key : attritutes.keySet()) {
					String keyStr = String.valueOf(key);
					if (!keyStr.startsWith(IARESProblem.UNPERSISTENT_PROPERTY_PREFIX)) {
						marker.setAttribute(keyStr, problem.getAttribute(keyStr));
					}
				}
			} catch (CoreException e) {
				logger.error("Create Marker faild: " , e);
			} catch (Exception e) {
				logger.error("Create Marker faild: " , e);
			}
		}
	}
	
	public static void clearMarkers(IARESResource res) {
		clearMarkers(res.getResource());
	}
	
	public static void clearMarkers(IResource res) {
		try {
			if (res != null && res.exists())
				res.deleteMarkers(IAresMarkers.MARKER_ID, true, IResource.DEPTH_ZERO);
		} catch (CoreException e) {
			logger.warn("删除Marker失败, Resource: " + res.getFullPath(), e);
		}
	}
	
	/**
	 * 按类型删除marker
	 * @param res
	 * @param type  			marker类型
	 * @param includesubtype	是否删除子类型
	 */
	public static void clearMarkers(IResource res,String type,boolean includesubtype) {
		try {
			if (res != null && res.exists()){
				res.deleteMarkers(type, includesubtype, IResource.DEPTH_ZERO);
			}
		} catch (CoreException e) {
			logger.warn("删除Marker失败, Resource: " + res.getFullPath(), e);
		}
	}
	
	
	public static void clearMarkers(IResource res, boolean recursion) {
		if (recursion) {
			try {
				if (res != null && res.exists())
					res.deleteMarkers(IAresMarkers.MARKER_ID, true, IResource.DEPTH_INFINITE);
			} catch (CoreException e) {
				logger.warn("删除Marker失败, Resource: " + res.getFullPath(), e);
			}
		} else {
			try {
				if (res != null && res.exists())
					res.deleteMarkers(IAresMarkers.MARKER_ID, true, IResource.DEPTH_ZERO);
			} catch (CoreException e) {
				logger.warn("删除Marker失败, Resource: " + res.getFullPath(), e);
			}
		}
	}

}
