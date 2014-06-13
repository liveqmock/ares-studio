/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import org.eclipse.core.runtime.IPath;

import com.hundsun.ares.studio.core.IExternalResPathEntry;
import com.hundsun.ares.studio.core.registry.IRespathProviderDescriptor;

/**
 * 由外部的Provider提供的RepathEntry
 * @author sundl
 */
public class ExternalResPathEntry extends ResPathEntry implements IExternalResPathEntry{

	private IRespathProviderDescriptor provider;
	
	public ExternalResPathEntry(int entryKind, int contentKind, String type, IPath path) {
		super(entryKind, contentKind, type, path);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IExternalResPathEntry#getProvider()
	 */
	@Override
	public IRespathProviderDescriptor getProvider() {
		return this.provider;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IExternalResPathEntry#setProvider(com.hundsun.ares.studio.core.registry.IRespathProviderDescriptor)
	 */
	@Override
	public void setProvider(IRespathProviderDescriptor provider) {
		this.provider = provider;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExternalResPathEntry other = (ExternalResPathEntry) obj;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		return true;
	}

}
