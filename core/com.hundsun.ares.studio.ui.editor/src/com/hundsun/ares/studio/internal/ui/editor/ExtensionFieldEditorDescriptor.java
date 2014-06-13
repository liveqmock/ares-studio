/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.editor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.hundsun.ares.studio.core.registry.ICommonExtensionConstants;
import com.hundsun.ares.studio.internal.core.registry.CommonDescriptor;
import com.hundsun.ares.studio.ui.editor.ExtensionFieldEditor;

/**
 * 
 * @author sundl
 */
public class ExtensionFieldEditorDescriptor extends CommonDescriptor {

	public ExtensionFieldEditorDescriptor(IConfigurationElement e) {
		super(e);		
	}
	
	public ExtensionFieldEditor createEditor() {
		String info_class = configElement.getAttribute(ICommonExtensionConstants.CLASS);
		if(info_class != null) {
			try {
				return (ExtensionFieldEditor)configElement.createExecutableExtension(ICommonExtensionConstants.CLASS);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
}
