/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.aresaction;

import java.util.HashMap;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * 上下文
 * @author sundl
 */
public class AresActionExecuteContext implements IAresActionExcuteContext {

	private String actionId;

	private Object entryPoint;
	private IARESResource currentRes;
	
	public void setCurrentRes(IARESResource currentRes) {
		this.currentRes = currentRes;
	}

	private Map<String, Object> data = new HashMap<String, Object>();
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.aresaction.IAresActionExcuteContext#getEntryPoint()
	 */
	public Object getEntryPoint() {
		return entryPoint;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.aresaction.IAresActionExcuteContext#getData(java.lang.String)
	 */
	public Object getData(String key) {
		return data.get(key);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.aresaction.IAresActionExcuteContext#setData(java.lang.String, java.lang.Object)
	 */
	public void setData(String key, Object value) {
		data.put(key, value);
	}

	public void setEntryPoint(Object entryPoint) {
		this.entryPoint = entryPoint;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.aresaction.IAresActionExcuteContext#getCurrentResource()
	 */
	public IARESResource getCurrentResource() {
		return currentRes;
	}

}
