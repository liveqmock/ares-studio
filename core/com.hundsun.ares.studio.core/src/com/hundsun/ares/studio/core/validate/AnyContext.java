/**
 * 
 */
package com.hundsun.ares.studio.core.validate;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;

/**
 * 一个保存任意信息的上下文对象
 * @author gongyf
 *
 */
public class AnyContext implements IAresContext {

	private Object data;
	
	/**
	 * @param data
	 */
	public AnyContext(Object data) {
		super();
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.validate.IAresContext#init(com.hundsun.ares.studio.core.IARESProject)
	 */
	@Override
	public void init(IARESProject project) throws ARESModelException {

	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}

}
