/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.ui.editor.pages;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.assist.IARESResourceAssistantProvider;

/**
 * @author liaogc
 *
 */
public class BasicDataResourceAssistantProvider extends IARESResourceAssistantProvider{

	/**
	 * @param project
	 * @param refType
	 */
	public BasicDataResourceAssistantProvider(IARESProject project,
			String refType) {
		super(project, refType);
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.assist.IARESResourceAssistantProvider#getContent(java.lang.Object)
	 */
	@Override
	public String getContent(Object obj) {
		if (obj instanceof IARESResource) {
			return ((IARESResource) obj).getName();
		}
		return null;
	}
	
	
	@Override
	public String getLabel(Object obj) {
		if (obj instanceof IARESResource) {
			return ((IARESResource) obj).getFullyQualifiedName();
		}
		return "";
	}

}
