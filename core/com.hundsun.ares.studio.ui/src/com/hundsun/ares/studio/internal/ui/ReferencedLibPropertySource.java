/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import com.hundsun.ares.studio.core.IBasicReferencedLibInfo;
import com.hundsun.ares.studio.core.IDependenceDescriptor;
import com.hundsun.ares.studio.core.IReferencedLibrary;

/**
 * 引用资源包的PropertySource
 * @author sundl
 */
public class ReferencedLibPropertySource implements IPropertySource {
	
	private static final String PRO_ID = "pro_id";
	private static final String PRO_VERSION = "pro_version";
	private static final String PRO_NAME = "pro_name";
	private static final String PRO_PROVIDER = "pro_provider";
	private static final String PRO_CONTACT = "pro_contact";
	private static final String PRO_NOTE = "pro_note";
	private static final String PRO_PUBLISHER = "pro_publisher";
	private static final String PRO_PUB_TIME = "pro_pub_time";
	private static final String PRO_DEPENDENCIES = "pro_dependencies";
	
	private static final String CATEGORY = "引用资源包";

	private static IPropertyDescriptor[] PRO_DESCRIPTORS = new IPropertyDescriptor[9];

	protected IReferencedLibrary lib;
	
	static {
		PropertyDescriptor descriptor = null;
		
		descriptor = new PropertyDescriptor(PRO_ID, "ID");
		descriptor.setAlwaysIncompatible(true);
		descriptor.setCategory(CATEGORY);
		PRO_DESCRIPTORS[0] = descriptor;
		
		descriptor = new PropertyDescriptor(PRO_NAME, "名字");
		descriptor.setAlwaysIncompatible(true);
		descriptor.setCategory(CATEGORY);
		PRO_DESCRIPTORS[1] = descriptor;
		
		descriptor = new PropertyDescriptor(PRO_VERSION, "版本");
		descriptor.setAlwaysIncompatible(true);
		descriptor.setCategory(CATEGORY);
		PRO_DESCRIPTORS[2] = descriptor;
		
		descriptor = new PropertyDescriptor(PRO_PROVIDER, "负责人");
		descriptor.setAlwaysIncompatible(true);
		descriptor.setCategory(CATEGORY);
		PRO_DESCRIPTORS[3] = descriptor;
		
		descriptor = new PropertyDescriptor(PRO_CONTACT, "联系方式");
		descriptor.setAlwaysIncompatible(true);
		descriptor.setCategory(CATEGORY);
		PRO_DESCRIPTORS[4] = descriptor;
		
		descriptor = new PropertyDescriptor(PRO_NOTE, "说明");
		descriptor.setAlwaysIncompatible(true);
		descriptor.setCategory(CATEGORY);
		PRO_DESCRIPTORS[5] = descriptor;
		
		descriptor = new PropertyDescriptor(PRO_PUBLISHER, "发布人");
		descriptor.setAlwaysIncompatible(true);
		descriptor.setCategory(CATEGORY);
		PRO_DESCRIPTORS[6] = descriptor;
		
		descriptor = new PropertyDescriptor(PRO_PUB_TIME, "发布时间");
		descriptor.setAlwaysIncompatible(true);
		descriptor.setCategory(CATEGORY);
		PRO_DESCRIPTORS[7] = descriptor;
		
		descriptor = new PropertyDescriptor(PRO_DEPENDENCIES, "依赖");
		descriptor.setAlwaysIncompatible(true);
		descriptor.setCategory(CATEGORY);
		PRO_DESCRIPTORS[8] = descriptor;
	}
	
	public ReferencedLibPropertySource(IReferencedLibrary lib) {
		this.lib = lib;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
	 */
	@Override
	public Object getEditableValue() {
		return lib;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
	 */
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return PRO_DESCRIPTORS;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		IBasicReferencedLibInfo basicInfo = lib.getBasicInfo();
		if (id.equals(PRO_ID)) {
			return basicInfo.getId();
		} else if (id.equals(PRO_NAME)) {
			return basicInfo.getName();
		} else if (id.equals(PRO_PROVIDER)) {
			return basicInfo.getProvider();
		} else if (id.equals(PRO_CONTACT)) {
			return basicInfo.getContact();
		} else if (id.equals(PRO_NOTE)) {
			return basicInfo.getNote();
		} else if (id.equals(PRO_PUBLISHER)) {
			return basicInfo.getPublisher();
		} else if (id.equals(PRO_PUB_TIME)) {
			return basicInfo.getPublishTime();
		} else if (id.equals(PRO_VERSION)) {
			return basicInfo.getVersion();
		} else if (id.equals(PRO_DEPENDENCIES)) {
			StringBuffer description = new StringBuffer();
			List<IDependenceDescriptor> dependencies = basicInfo.getDependencyDescriptors();
			if (dependencies != null) {
				for (int i = 0; i < dependencies.size(); i++) {
					IDependenceDescriptor dep = dependencies.get(i);
					description.append(dep.getId());
					if (i != dependencies.size() - 1) {
						description.append(',');
					}
				}
				return description;
			} 
			return StringUtils.EMPTY;
		}
		
		return "No Value";
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
