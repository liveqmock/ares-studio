/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IResourcePropertyConstants;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
 * 外部引用资源包的PropertySource
 * @author sundl
 */
public class ExternalRefLibPropertySource implements IPropertySource {

	private static final String PRO_PATH = "pro_path";
	private ReferencedLibPropertySource basicSource;
	
	private static IPropertyDescriptor[] PRO_DESCRIPTORS = new IPropertyDescriptor[1];
	
	static {
		PropertyDescriptor descriptor = null;
		
		descriptor = new PropertyDescriptor(PRO_PATH, "Path");
		descriptor.setAlwaysIncompatible(true);
		descriptor.setCategory(IResourcePropertyConstants.P_FILE_SYSTEM_CATEGORY);
		PRO_DESCRIPTORS[0] = descriptor;
	}
	
	protected ExternalRefLibPropertySource(ReferencedLibPropertySource basicSource) {
		this.basicSource = basicSource;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
	 */
	@Override
	public Object getEditableValue() {
		return basicSource.getEditableValue();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
	 */
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
        IPropertyDescriptor[] basiceDescriptors = basicSource.getPropertyDescriptors();
        int basicLength = basiceDescriptors.length;
        IPropertyDescriptor[] result = new IPropertyDescriptor[basicLength + PRO_DESCRIPTORS.length];
        System.arraycopy(basiceDescriptors, 0, result, 0, basicLength);
        System.arraycopy(PRO_DESCRIPTORS, 0, result, basicLength, PRO_DESCRIPTORS.length);

        return result;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(PRO_PATH))
			return basicSource.lib.getPath().toOSString();
		return basicSource.getPropertyValue(id);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
	 */
	@Override
	public boolean isPropertySet(Object id) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
	 */
	@Override
	public void resetPropertyValue(Object id) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
	}

}
