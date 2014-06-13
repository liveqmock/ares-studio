package com.hundsun.ares.studio.ui;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.WriterAppender;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.framework.BundleContext;

import com.hundsun.ares.studio.core.logging.ARESLevelMatchFilter;
import com.hundsun.ares.studio.ui.console.ARESConsole;
import com.hundsun.ares.studio.ui.console.ARESConsoleFactory;

/**
 * The activator class controls the plug-in life cycle
 */
public class ARESUI extends EMFPlugin {
	
	public static final String MODULE_FLAT = "module-flat";
	public static final String SHOW_CATEGORY = "show-category";

	// The plug-in ID
	public static final String PLUGIN_ID = "com.hundsun.ares.studio.ui";

	public static final String IMG_PROJECT = "obj16/aresproject.gif";
	public static final String IMG_DEFAULT_ARES_RESOURCE = "obj16/def_res.gif";
	public static final String IMG_PATH_MODULE = "obj16/module.gif";
	public static final String IMG_PATH_SUB_MODULE = "obj16/subsys.gif";
	public static final String IMG_PATH_REFLIB = "obj16/ref_lib.gif";
	public static final String IMG_PATH_CATE = "obj16/folder.gif";
	public static final String IMG_PATH_PROJECT_PRO = "obj16/project_pro.gif";
	public static final String IMG_PATH_ERROR = "error_co.gif";
	public static final String IMG_PATH_WARNING = "warning_co.gif";
	public static final String IMG_PATH_LIB_CONTAINER = "obj16/libiray.gif";
	
	public static final String PRE_CELLEDITOR_ACTIVE_MODE_DOUBLECLICK = "doubleClick";
	public static final String PRE_CELLEDITOR_ACTIVE_MODE_SINGLECLICK = "singleClick";
	/** 代码生成路径 */
	public static final String PRE_GENERATE_PATH = "jres_gen_path";
	
	public static final String PRE_GENERATE_CHARSET = "jres_gen_charset";

	/**
	 * 表格中激活单元格的方式，单击还是双击
	 */
	public static final String PRE_CELLEDITOR_ACTIVE_MODE = "jres_celleditor_active_mode";
	
	
	
	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ARESUI INSTANCE = new ARESUI();
	
    /**
     * Storage for preferences.
     */
    private ScopedPreferenceStore preferenceStore;
	
	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Implementation plugin;
	
	/**
	 * Create the instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ARESUI() {
		super
		  (new ResourceLocator [] {
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

	public static Implementation getDefault() {
		return getPlugin();
	}
	
	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public static class Implementation extends EclipseUIPlugin {
		
		private ARESColorManager colorManager = new ARESColorManager();
		private ARESConsole console;
		private ImageDescriptorRegistry imageDescriptorRegistry;

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
		
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
		 */
		public void start(BundleContext context) throws Exception {
			super.start(context);
			plugin = this;
			try {
				if (PlatformUI.isWorkbenchRunning())
					console = new ARESConsole();
			} catch (Exception e) {
			}
			setUpConsoleLogger();
			ResPathChecker.getInstance().resPathCheck();
		}
		
		private void setUpConsoleLogger() {
			Logger consoleLogger = Logger.getLogger("com.hundsun.ares.studio.core.Console");
			consoleLogger.setAdditivity(false);
			WriterAppender appender = (WriterAppender) consoleLogger.getAppender("ARESConsole");
			if (appender != null) {
				// 设置info对应的appender
				ARESConsole console = ARESUI.INSTANCE.getConsole();
				OutputStream infoStream = null;
				OutputStream warnStream = null;
				OutputStream errorStream = null;
				
				if (console != null) {
					infoStream = console.getInfoStream();
					warnStream = console.getWarningStream();
					errorStream = console.getErrorStream();
					
					appender.setWriter(new PrintWriter(infoStream));
					appender.addFilter(new ARESLevelMatchFilter(Level.INFO));
					
					PatternLayout layout = (PatternLayout) appender.getLayout();
					
					// 添加error对应的appender
					WriterAppender errorAppender = new WriterAppender(
							new PatternLayout(layout.getConversionPattern()), 
							new PrintWriter(errorStream));
					errorAppender.addFilter(new ARESLevelMatchFilter(Level.ERROR));
					consoleLogger.addAppender(errorAppender);
					
					// 添加warn对应的appender
					WriterAppender warnAppender = new WriterAppender(
							new PatternLayout(layout.getConversionPattern()), 
							new PrintWriter(warnStream));
					warnAppender.addFilter(new ARESLevelMatchFilter(Level.WARN));
					consoleLogger.addAppender(warnAppender);
				} 
			}
		}

		/*
		 * (non-Javadoc)
		 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
		 */
		public void stop(BundleContext context) throws Exception {
			if (imageDescriptorRegistry != null) {
				imageDescriptorRegistry.dispose();
			}
			
			if (console != null)
				console.shutdown();
			
			if (colorManager != null)
				colorManager.dispose();
			
			plugin = null;
			super.stop(context);
		}
		
		protected synchronized ImageDescriptorRegistry internalGetImageDescriptorRegistry() {
			if (imageDescriptorRegistry == null)
				imageDescriptorRegistry= new ImageDescriptorRegistry();
			return imageDescriptorRegistry;
		}
		
		/**
		 * @return the console
		 */
		public ARESConsole getConsole() {
			return console;
		}
		
		public ARESColorManager getColorManager() {
			return colorManager;
		}

		/**
		 * @param pageName
		 * @return
		 */
		public IDialogSettings getDialogSettingsSection(String name) {
			IDialogSettings dialogSettings = getDialogSettings();
			IDialogSettings section = dialogSettings.getSection(name);
			if (section == null) {
				section = dialogSettings.addNewSection(name);
			}
			return section;
		}
	}
	
	/**
	 * @return the console
	 */
	public ARESConsole getConsole() {
		return plugin.console;
	}
	
    public IPreferenceStore getPreferenceStore() {
    	return plugin.getPreferenceStore();
    }
	
	public static void showARESConsole() {
		ARESConsole console = (ARESConsole) ARESConsoleFactory.getConsole();
		ConsolePlugin.getDefault().getConsoleManager().showConsoleView( console );
	}
	
	public static ImageDescriptorRegistry getImageDescriptorRegistry() {
		return plugin.internalGetImageDescriptorRegistry();
	}
	
	public static ImageDescriptor getImageDescriptor(String path) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
//	public static Image getImage(String path) {
//		ImageDescriptor desc = INSTANCE.getImageDescriptor(path);
//		return getImageDescriptorRegistry().get(desc);
//	}
	
	public Object getImage(String key) {
		return super.getImage(key);
	}
	
	/**
	 * 因EMF的插件类本身已经有个同名方法，所以改名； 这个方法返回值类型为Image更加方便使用
	 * @param key 路径
	 * @return
	 */
	public static Image getImage2(String key) {
		Object obj = INSTANCE.getImage(key);
		return ExtendedImageRegistry.getInstance().getImage(obj);
	}
	
	/**
	 * 获取表格中单元格激活方式
	 * @return
	 */
	public static String getCellEdiorActiveMode() {
		IPreferencesService service = Platform.getPreferencesService();
		return service.getString(PLUGIN_ID, PRE_CELLEDITOR_ACTIVE_MODE, StringUtils.EMPTY, null);
	}
	

}
