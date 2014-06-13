/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IDependenceDescriptor;
import com.hundsun.ares.studio.core.IDependencyUnit;
import com.hundsun.ares.studio.core.IRequiredProject;

/**
 * 
 * @author sundl
 */
public class RequiredProject implements IRequiredProject {

	private IARESProject aresProject;
	private String type;
	
	public RequiredProject(IARESProject project, String type) {
		this.aresProject = project;
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependencyUnit#getRoots()
	 */
	@Override
	public IARESModuleRoot[] getRoots() throws ARESModelException {
		return aresProject.getModuleRoots();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependencyUnit#getDependencyDescriptors()
	 */
	@Override
	public List<IDependenceDescriptor> getDependencyDescriptors() {
		List<IDependencyUnit> dependencies = aresProject.getDependencies();
		List<IDependenceDescriptor> descriptors = new ArrayList<IDependenceDescriptor>();
		for (IDependencyUnit unit : dependencies) {
			String id = unit.getId();
			String type = unit.getType();
			String version = unit.getVersion();
			descriptors.add(new DependenceDescriptor(id, type, version));
		}
		return descriptors;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependencyUnit#getPublishTime()
	 */
	@Override
	public String getPublishTime() {
		IARESProjectProperty property;
		try {
			property = aresProject.getProjectProperty();
			if (property != null)
				return property.getPubTime();
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependencyUnit#getNote()
	 */
	@Override
	public String getNote() {
		IARESProjectProperty property;
		try {
			property = aresProject.getProjectProperty();
			if (property != null)
				return property.getNote();
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependencyUnit#getName()
	 */
	@Override
	public String getName() {
		IARESProjectProperty property;
		try {
			property = aresProject.getProjectProperty();
			if (property != null)
				return property.getName();
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependencyUnit#getPublisher()
	 */
	@Override
	public String getPublisher() {
		IARESProjectProperty property;
		try {
			property = aresProject.getProjectProperty();
			if (property != null)
				return property.getPublisher();
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependencyUnit#getContact()
	 */
	@Override
	public String getContact() {
		IARESProjectProperty property;
		try {
			property = aresProject.getProjectProperty();
			if (property != null)
				return property.getContact();
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependencyUnit#getProvider()
	 */
	@Override
	public String getProvider() {
		IARESProjectProperty property;
		try {
			property = aresProject.getProjectProperty();
			if (property != null)
				return property.getProvider();
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependencyUnit#getVersion()
	 */
	@Override
	public String getVersion() {
		IARESProjectProperty property;
		try {
			property = aresProject.getProjectProperty();
			if (property != null)
				return property.getVersion();
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependencyUnit#getId()
	 */
	@Override
	public String getId() {
		IARESProjectProperty property;
		try {
			property = aresProject.getProjectProperty();
			if (property != null)
				return property.getId();
		} catch (ARESModelException e) {
			//e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependencyUnit#getType()
	 */
	@Override
	public String getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IDependencyUnit#getDescriptionStr()
	 */
	@Override
	public String getDescriptionStr() {
		return "引用工程" + aresProject.getElementName();
	}
	
}
