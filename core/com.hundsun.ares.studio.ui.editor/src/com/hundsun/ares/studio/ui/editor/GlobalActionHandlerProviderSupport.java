/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.editor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author gongyf
 *
 */
public class GlobalActionHandlerProviderSupport {
	
	private List<IGlobalActionHandlerProviderListener> listeners = new ArrayList<IGlobalActionHandlerProviderListener>();
	private IGlobalActionHandlerProvider provider;
	
	/**
	 * @param provider
	 */
	public GlobalActionHandlerProviderSupport(
			IGlobalActionHandlerProvider provider) {
		super();
		this.provider = provider;
	}

	public synchronized void addListener(IGlobalActionHandlerProviderListener listener) {
		listeners.add(listener);
	}
	
	public synchronized void removeListener(IGlobalActionHandlerProviderListener listener){
		listeners.remove(listener);
	}
	
	/**
	 * 通知所有监听器
	 */
	public void fireProviderActived() {
		for (Iterator<IGlobalActionHandlerProviderListener> iterator = listeners.iterator(); iterator.hasNext();) {
			IGlobalActionHandlerProviderListener listener = iterator.next();
			listener.activated(provider);
		}
	}
	
	/**
	 * 通知所有监听器
	 */
	public void fireProviderDeactived() {
		for (Iterator<IGlobalActionHandlerProviderListener> iterator = listeners.iterator(); iterator.hasNext();) {
			IGlobalActionHandlerProviderListener listener = iterator.next();
			listener.deactivated(provider);
		}
	}
}
