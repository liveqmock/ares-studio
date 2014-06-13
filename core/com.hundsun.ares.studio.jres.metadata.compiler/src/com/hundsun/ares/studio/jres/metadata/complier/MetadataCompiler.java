package com.hundsun.ares.studio.jres.metadata.complier;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class MetadataCompiler implements BundleActivator {

	public static final String PLUGIN_ID = "com.hundsun.ares.studio.jres.metadata.compiler";
	
	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		MetadataCompiler.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		MetadataCompiler.context = null;
	}

}
