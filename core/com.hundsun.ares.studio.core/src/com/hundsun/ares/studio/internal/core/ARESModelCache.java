/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESElement;

/**
 * 缓存。
 * @author sundl
 */
public class ARESModelCache {

	private Map<IARESElement, SoftReference<Object> > openableCache;
	private Map<IARESElement, SoftReference<Object>> resCache;
		
	public ARESModelCache() {
		openableCache = new HashMap<IARESElement, SoftReference<Object>>();
		resCache = new HashMap<IARESElement, SoftReference<Object>>(3000 * 5);
	}
	
	public void putInfo(IARESElement element, Object info) {
		if (element == null)
			return;
		
		switch (element.getElementType()) {
		case IARESElement.ARES_RESOURCE:
			resCache.put(element, new SoftReference<Object>(info));
			break;
		default:
			openableCache.put(element, new SoftReference<Object>(info));
		}
	}
	
	public void removeInfo(IARESElement element) {
		if (element == null)
			return;
		
		switch (element.getElementType()) {
		case IARESElement.ARES_RESOURCE:
			resCache.remove(element);
			break;
		default:
			openableCache.remove(element);
		}
	}
	
	public Object getInfo(IARESElement element) {
		if (element == null)
			return null;
		
		SoftReference<Object> ref = null;
		switch (element.getElementType()) {
		case IARESElement.ARES_RESOURCE:
			ref = resCache.get(element);
			break;
		default:
			ref = openableCache.get(element);
		}
		
		if (ref == null) {
			return null;
		} else {
			return ref.get();
		}
	}
	
}
