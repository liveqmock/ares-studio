/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.editor;


import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.hundsun.ares.studio.ui.editor.text.sql.SQLColorProvider;
import com.hundsun.ares.studio.ui.extendpoint.manager.ExtendPageManager;
import com.hundsun.ares.studio.ui.util.HSColorManager;


/**
 * The activator class controls the plug-in life cycle
 */
public class ARESEditorPlugin extends AbstractUIPlugin {
	
	private HSColorManager colorManager;
	private static SQLColorProvider sqlColorProvider;
	
	public HSColorManager getColorManager() {
		if (this.colorManager == null) {
			this.colorManager = new HSColorManager();
		}
		return this.colorManager;
	}
	// The plug-in ID
	public static final String PLUGIN_ID = "com.hundsun.ares.studio.ui.editor";
	/** 项目属性编辑器ID */
	public static final String PROJECT_PROPERTY_ID = "com.hundsun.ares.studio.ui.editor.projecteditor";
	
	static final public String PAGE_EXTEND_ID = PLUGIN_ID + ".ares_page";
	
	static final public String EXTEND_FIELD_ID = PLUGIN_ID + ".ares_extend_field";
	
	static final public String FIELD_DETAIL_ID = PLUGIN_ID + ".ares_field_detail";

	// The shared instance
	private static ARESEditorPlugin plugin;
	
	/**
	 * The constructor
	 */
	public ARESEditorPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		sqlColorProvider = new SQLColorProvider();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		if (colorManager != null) {
			colorManager.dispose();
			colorManager = null;
		}
		if (sqlColorProvider != null){
			sqlColorProvider.dispose();
		}
		plugin = null;
		super.stop(context);
	}

	public static SQLColorProvider getSqlColorProvider() {
		return sqlColorProvider;
	}
	
	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static ARESEditorPlugin getDefault() {
		return plugin;
	}
	
	public static ImageDescriptor getImageDescriptor(String name) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, "icons/" + name);
	}
	
	public static Image getImage(String key) {
		ImageRegistry reg = plugin.getImageRegistry();
		Image image = reg.get(key); 
		if(image == null) {
			ImageDescriptor desc = imageDescriptorFromPlugin(PLUGIN_ID, "icons/" + key);
			reg.put(key, desc);
			image = reg.get(key);
		}
		return image;
	}
	
	public static Image getImage(String key,URL url) {
		ImageRegistry reg = plugin.getImageRegistry();
		Image image = reg.get(key); 
		if(image == null) {
			ImageDescriptor desc = ImageDescriptor.createFromURL(url);
			reg.put(key, desc);
			image = reg.get(key);
		}
		return image;
	}

	
	static public ExtendPageManager getExtendPageManager(){
		return ExtendPageManager.getDefault();
	}
}
