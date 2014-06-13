package com.hundsun.ares.studio.jres.database.ui;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.hundsun.ares.studio.ui.ARESUI;
import com.hundsun.ares.studio.ui.editor.text.sql.SQLColorProvider;

/**
 * The activator class controls the plug-in life cycle
 */
public class DatabaseUI extends EMFPlugin {

	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final DatabaseUI INSTANCE = new DatabaseUI();

	// The plug-in ID
	public static final String PLUGIN_ID = "com.hundsun.ares.studio.jres.database.ui"; //$NON-NLS-1$

	private static Implementation plugin;

	/**
	 * Create the instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatabaseUI() {
		super
		  (new ResourceLocator [] {
		     ARESUI.INSTANCE,
		   });
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
	@Override
	public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
	public static Implementation getPlugin() {
		return plugin;
	}

	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public static class Implementation extends EclipseUIPlugin {
		
		/**
		 * @generated NOT
		 */
		private static SQLColorProvider sqlColorProvider;
		
		/**
		 * Creates an instance.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public Implementation() {
			super();

			// Remember the static instance.
			//
			plugin = this;
		}
		
		/**
		 * @generated NOT
		 */
		public void start(BundleContext context) throws Exception {
			super.start(context);
			plugin = this;
			sqlColorProvider = new SQLColorProvider();
		}
		
		/**
		 * @generated NOT
		 */
		public void stop(BundleContext context) throws Exception {
			if (sqlColorProvider != null)
				sqlColorProvider.dispose();
			
			plugin = null;
			super.stop(context);
		}
		
		/**
		 * @generated NOT
		 */
		public static SQLColorProvider getSqlColorProvider() {
			return sqlColorProvider;
		}

	}
	
	public static SQLColorProvider getSqlColorProvider() {
		return Implementation.getSqlColorProvider();
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Implementation getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	/**
	 * 取图片的方法，key是一个相对于icons目录的图片路径，比如
	 * full/obj16/param.gif； 如果图片类型是gif还可以省略扩展名full/obj16/param
	 * 这个方法不会重复创建图片资源，不需要手动Dispose
	 * @param key
	 * @return
	 */
	public static Image image(String key) {
		Object obj = DatabaseUI.INSTANCE.getImage(key);
		return ExtendedImageRegistry.getInstance().getImage(obj);
	}
}
