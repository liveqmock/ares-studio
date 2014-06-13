/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.aresaction;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.registry.ICommonExtensionConstants;
import com.hundsun.ares.studio.internal.core.registry.CommonDescriptor;
import com.hundsun.ares.studio.ui.aresaction.IARESAction;

/**
 * 
 * @author sundl
 */
public class AresActionImplementationDescriptor extends CommonDescriptor {
	
	private String[] resTypes;

	public AresActionImplementationDescriptor(IConfigurationElement e) {
		super(e);
	}

	protected void loadFromExtension() {
		super.loadFromExtension();
		String types = configElement.getAttribute("resTypes");
		this.resTypes = types.split(",");
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.ui.aresaction.IARESActionDescriptor#createAction()
	 */
	public IARESAction createAction() {
		try {
			return (IARESAction)configElement.createExecutableExtension(ICommonExtensionConstants.CLASS);
		} catch (CoreException e) {
			// e.printStackTrace();
			// cann't create instance, use default
			Logger.getLogger(AresActionImplementationDescriptor.class).error("", e);
		}
		return null;
	}
	
	public String[] getResTypes() {
		return resTypes;
	}
	
	/**
	 * 这个操作定义是否匹配指定的资源类型
	 * @param resType
	 * @return
	 */
	public boolean isMatch(String resType) {
		for (String type : resTypes) {
			if (type.equals(resType)) {
				return true;
			}
		}
		return false;
	}
	
}
