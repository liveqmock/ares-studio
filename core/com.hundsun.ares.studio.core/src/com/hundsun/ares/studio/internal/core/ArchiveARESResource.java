/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESModelStatus;
import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.core.util.Util;

/**
 * 包里的资源
 * @author sundl
 */
public class ArchiveARESResource extends ARESResource {

	private String name;
	
	public ArchiveARESResource(IARESElement parent, String name) {
		super(parent);
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.devtool.v2.internal.core.Openable#validateExistence(org.eclipse.core.resources.IResource)
	 */
	protected IStatus validateExistence(IResource underlyingResource) {
		return ARESModelStatus.VERIFIED_OK;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESResource#openStream()
	 */
	@Override
	public InputStream openStream() throws ARESModelException {
		ReferencedLibrary lib = (ReferencedLibrary) getLib();
		
		ZipFile zipFile = null;
		InputStream result = null;
		InputStream temp = null;
		try {
			zipFile = lib.getZipFile();
			ZipEntry entry = zipFile.getEntry(getZipEntryName());
			if (entry != null)
				temp = zipFile.getInputStream(entry);
			
			if (temp != null) {
				byte[] bytes = IOUtils.toByteArray(temp);
				result = new ByteArrayInputStream(bytes);
			} else {
				result = new ByteArrayInputStream(new byte[0]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (zipFile != null)
					zipFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public String getElementName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESResource#getName()
	 */
	public String getName() {
		ARESResRegistry reg = ARESResRegistry.getInstance();
		IResDescriptor desc = reg.getResDescriptor(getElementName());
		if (desc != null)
			return name;
		
		return StringUtils.substringBefore(name, ".");
	}

	private String getZipEntryName() {
		IARESModuleRoot root = getRoot();
		ARESModule module = (ARESModule)getModule();
		String[] names = Util.arrayConcat(module.names, name);
		return root.getElementName() + "/" + Util.concatWith(names, '/');
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getResource()
	 */
	@Override
	public IResource getResource() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getPath()
	 */
	public IPath getPath() {
		return getParent().getPath();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESResource#getBundle()
	 */
	@Override
	public IARESBundle getBundle() {
		return getLib();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IARESElement#getCorrespondingResource()
	 */
	@Override
	public IResource getCorrespondingResource() throws ARESModelException {
		return null;
	}
	
	@Override
	public String getType() {
		ARESResRegistry reg = ARESResRegistry.getInstance();
		IResDescriptor desc = reg.getResDescriptor(getElementName());
		if (desc != null)
			return name;
		
		if (name != null && !StringUtils.isEmpty(name)) {
			return StringUtil.removePrefixBefore(name, ".");
		}
		return StringUtil.EMPTY_STR;
	}
	
	@Override
	public boolean isReadOnly() {
		return true;
	}
	
	@Override
	public boolean exists() {
		IARESModule module = getModule();
		IARESResource[] resources = module.getARESResources();
		
		for (IARESResource res : resources) {
			if (res.getName().equals(getName()) && StringUtils.equals(getType(), res.getType()))
				return true;
		}
		
		return false;
	}
	
}
