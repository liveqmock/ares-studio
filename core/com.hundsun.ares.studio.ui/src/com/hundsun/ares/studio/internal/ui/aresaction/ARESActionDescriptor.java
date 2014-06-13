/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.aresaction;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.registry.ICommonExtensionConstants;
import com.hundsun.ares.studio.internal.core.registry.CommonDescriptor;
import com.hundsun.ares.studio.ui.aresaction.IARESAction;
import com.hundsun.ares.studio.ui.aresaction.IARESActionDescriptor;

/**
 * ARES操作的注册描述信息
 * @author sundl
 */
public class ARESActionDescriptor extends CommonDescriptor implements IARESActionDescriptor{
	
	private static final String RES_TYPE = "restype";
	private String resType;

	public ARESActionDescriptor(IConfigurationElement e) {
		super(e);		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.ui.aresaction.IARESActionDescriptor#getResType()
	 */
	public String getResType() {
		return this.resType;
	}
	
	protected void loadFromExtension() {
		super.loadFromExtension();
		this.resType = configElement.getAttribute(RES_TYPE);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.ui.aresaction.IARESActionDescriptor#createAction()
	 */
	public IARESAction createAction() {
		try {
			return (IARESAction)configElement.createExecutableExtension(ICommonExtensionConstants.CLASS);
		} catch (CoreException e) {
			//e.printStackTrace();
			// cann't create instance, use default
		}
		return null;
	}
	
}
