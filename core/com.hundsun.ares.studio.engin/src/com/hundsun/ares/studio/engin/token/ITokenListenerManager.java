package com.hundsun.ares.studio.engin.token;

public interface ITokenListenerManager {

	/**
	 * Ìí¼Ó¼àÌı
	 * @param listener
	 */
	public void addListener(ITokenListener listener);
	
	
	/**
	 * É¾³ı¼àÌı
	 * @param listener
	 */
	public void removeListener(ITokenListener listener);
	
	
	/**
	 * ´¥·¢ÊÂ¼ş
	 * @param event
	 */
	public void fireEvent(ITokenEvent event);
}
