package com.hundsun.ares.studio.jres.script;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ScriptPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.hundsun.ares.studio.jres.script"; //$NON-NLS-1$

	// The shared instance
	private static ScriptPlugin plugin;
	
	/**
	 * 工具资源
	 */
	public static final String TOOL_MODULE_ROOT_TYPE = "com.hundsun.ares.studio.jres.moduleroot.tools";
	public static final String OLD_TOOL_MODULE_ROOT_TYPE = "com.hundsun.ares.studio.jres.others";
	
	/**
	 * The constructor
	 */
	public ScriptPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static ScriptPlugin getDefault() {
		return plugin;
	}

}
