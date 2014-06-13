/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core.registry;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.registry.IARESProjectDescriptor;
import com.hundsun.ares.studio.core.registry.ICommonExtensionConstants;
import com.hundsun.ares.studio.core.util.StringUtil;

/**
 * 扩展ARES项目类型扩展描述信息
 * @author sundl
 */
public class ARESProjectDescriptor extends CommonDescriptor implements IARESProjectDescriptor{

	private List<String> natures;// = new ArrayList<String>();
	
	public ARESProjectDescriptor(IConfigurationElement e) {
		super(e);			
	}

	protected void loadFromExtension() {
		super.loadFromExtension();
		if (natures == null)
			natures = new ArrayList<String>();
		
		String natureString = configElement.getAttribute(ICommonExtensionConstants.NATURES);
		if (!StringUtil.isEmpty(natureString)) {
			String[] natures = natureString.split(",");
			for (String nature : natures) {
				this.natures.add(nature);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.registry.IARESProjectDescriptor#getNatures()
	 */
	public String[] getNatures() {
		return this.natures.toArray(new String[0]);
	}
}
