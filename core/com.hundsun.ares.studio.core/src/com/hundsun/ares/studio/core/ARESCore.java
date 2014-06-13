/*
 * 系统名称: ARES 应用快速开发企业套件
 * 模块名称:
 * 类 名 称: ARESCore.java
 * 软件版权: 杭州恒生电子股份有限公司
 * 相关文档:
 * 修改记录:
 * 修改日期      修改人员                     修改说明<BR>
 * ========     ======  ============================================
 * 20110224     mawb	对应修改单号：20110128022
 * ========     ======  ============================================
 * 评审记录：
 * 
 * 评审人员：
 * 评审日期：
 * 发现问题：
 */
package com.hundsun.ares.studio.core;

import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.WriterAppender;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;

import com.hundsun.ares.studio.core.logging.ARESLevelMatchFilter;
import com.hundsun.ares.studio.internal.core.ARESModel;
import com.hundsun.ares.studio.internal.core.ARESModelManager;
import com.hundsun.ares.studio.internal.core.ExternalResPathEntry;
import com.hundsun.ares.studio.internal.core.MementoTokenizer;
import com.hundsun.ares.studio.internal.core.ResPathEntry;

/**
 * The activator class controls the plug-in life cycle
 */
public class ARESCore extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.hundsun.ares.studio.core";
	
	// The nature ID
	public static final String NATURE_ID = PLUGIN_ID + ".aresnature";
	public static final String BUILDER_ID = PLUGIN_ID + ".aresbuilder";

	/** 项目属性的扩展页的NatureID */
	public static final String EXTEND_NATURE = PLUGIN_ID + ".extendnature";
	public static final String REF_NATURE = PLUGIN_ID + ".refnature";
	
	// The shared instance
	private static ARESCore plugin;
	
//	private static MessageConsoleStream stream;
//	private static PrintWriter writer;
	
	
	public static Logger logger = Logger.getLogger(ARESCore.class.getName());

	/**
	 * 当前使用的脚步引擎名，当前是 JavaScript
	 */
	public static final String SCRIPT_ENGINE_NAME = "JavaScript";
		
	/**
	 * The constructor
	 */
	public ARESCore() {
	}
	
	public static IARESModel getModel() {
		return ARESModelManager.getManager().getModel();
	}
	
	public static IARESElement create(IResource resource) {
		return ARESModelManager.getManager().create(resource);
	}
	
	public static IARESProject create(IProject project) {
		return ARESModelManager.getManager().create(project);
	}
	
	public static IARESElement create(String handleIdentifier) {
		if (handleIdentifier == null)
			return null;
		MementoTokenizer memento = new MementoTokenizer(handleIdentifier);
		ARESModel model = (ARESModel) ARESModelManager.getManager().getModel();
		return model.getHandleFromMemento(memento);
	}

	public static void addElementListener(IARESElementChangeListener listener) {
		ARESModelManager.getManager().addElementChangeListener(listener);
	}
	
	public static void removeElementListener(IARESElementChangeListener listener) {
		ARESModelManager.getManager().addElementChangeListener(listener);
	}
	
	/**
	 * 获得一个控制台输出流，用完之后必须关闭。
	 * @return
	 */
//	public MessageConsoleStream newConsoleStream() {
//		MessageConsole console = ConsoleHelper.getConsole();
//		return console.newMessageStream();
//	}
	
	/**
	 * 获取框架管理的一个控制台输出流，框架负责这个流的生命周期的管理，使用者<b>不能</b>关闭这个流。
	 * @return
	 */
//	public MessageConsoleStream getSystemConsoleStream() {
//		if (stream == null)
//			stream = newConsoleStream();
//		return stream;
//	}
//	
//	public PrintWriter getSystemConsoleWriter() {
//		if (writer == null) {
//			MessageConsoleStream stream = getSystemConsoleStream();
//			writer = new PrintWriter(stream, true);
//		}
//		return writer;
//	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		

		// IMPORTANT: 其他初始化操作应该都在这个之后
		setUpLogger();
		ARESModelManager.getManager().startUp();
	}
	
	// 初始化自定义的日志配置。
	private void setUpLogger() {
//		try {			
//			// 由于class-loader的问题，在配置文件里配置都没有作用，暂时只能用程序方式进行配置。
//			Logger aresRootLogger = Logger.getLogger("com.hundsun.ares.studio");
//			ConsoleHandler handler = new ConsoleHandler();
//			handler.setFormatter(new ARESLoggingFormatter());
//			aresRootLogger.addHandler(handler);
//			aresRootLogger.setUseParentHandlers(false);
//			logger.fine("ARES Logger set up succesfully.");
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			logError("Setup ARES logger failed!", ex);
//			logger.log(Level.SEVERE, "Setup ARES logger failed!", ex);
//		}
		
		Logger consoleLogger = Logger.getLogger("com.hundsun.ares.studio.core.Console");
		consoleLogger.setAdditivity(false);
		WriterAppender appender = (WriterAppender) consoleLogger.getAppender("ARESConsole");
		if (appender != null) {
			// 设置info对应的appender
			OutputStream infoStream = System.out;
			OutputStream warnStream = System.err;
			OutputStream errorStream = System.err;
			
			appender.setWriter(new PrintWriter(infoStream) {
				@Override
				public void close() {
					// bug: 因为如果ares ui插件存在的时候，会重设，setWriter的时候，log4j会自动关闭原有的writter，导致system.out被关闭...
					// 所以此处close的时候先把out设成null，以免sys.out被关闭...
					out = null;
					super.close();
				}
			});
			
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

	public void logError(String message, Throwable e) {
		plugin.getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, -1, message, e));
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		ARESModelManager.getManager().shutDown();
//		if (writer != null)
//			writer.close();
//		if (stream != null && !stream.isClosed())
//			stream.close();
		
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static ARESCore getDefault() {
		return plugin;
	}
	
	/**
	 * 创建一个指向依赖项目的Entry.
	 * @param path 依赖项目的路径，例如/testproject
	 * @return 对应于指定路径项目的Entry
	 */
	public static IResPathEntry newProjectEntry(IPath path, String type) {
		return new ResPathEntry(IResPathEntry.RPE_PROJECT, IARESModuleRoot.KIND_SOURCE, type, path);
	}
	
	public static IResPathEntry newProjectEntry(IPath path) {
		return new ResPathEntry(IResPathEntry.RPE_PROJECT, IARESModuleRoot.KIND_SOURCE, null, path);
	}
	
	/**
	 * 创建一个指向Source文件夹的Entry.
	 * @param rootId 对应的ModuleRoot类型ID
	 * @param path 对应ModuleRoot的路径
	 * @return 对应path和类型的source Entry
	 */
	public static IResPathEntry newSourceEntry(String rootId, IPath path) {
		return new ResPathEntry(IResPathEntry.RPE_SOURCE, IARESModuleRoot.KIND_SOURCE, rootId, path);
	}
	
	/**
	 * 创建一个对应Zip包的Entry.
	 * @param path 包的文件path
	 * @return 对应指定的包的Entry
	 */
	public static IResPathEntry newLibEntry(IPath path) {
		return new ResPathEntry(IResPathEntry.RPE_LIBRAY, IARESModuleRoot.KIND_BINARY, null, path);
	}
	
	/**
	 * 创建一个指向依赖项目的Entry.
	 * @param path 依赖项目的路径，例如/testproject
	 * @return 对应于指定路径项目的Entry
	 */
	public static IExternalResPathEntry newExternalProjectEntry(IPath path, String type) {
		return new ExternalResPathEntry(IResPathEntry.RPE_PROJECT, IARESModuleRoot.KIND_SOURCE, type, path);
	}
	
	public static IExternalResPathEntry newExternalProjectEntry(IPath path) {
		return new ExternalResPathEntry(IResPathEntry.RPE_PROJECT, IARESModuleRoot.KIND_SOURCE, null, path);
	}
	
	/**
	 * 创建一个指向Source文件夹的Entry.
	 * @param rootId 对应的ModuleRoot类型ID
	 * @param path 对应ModuleRoot的路径
	 * @return 对应path和类型的source Entry
	 */
	public static IExternalResPathEntry newExternalSourceEntry(String rootId, IPath path) {
		return new ExternalResPathEntry(IResPathEntry.RPE_SOURCE, IARESModuleRoot.KIND_SOURCE, rootId, path);
	}
	
	/**
	 * 创建一个对应Zip包的Entry.
	 * @param path 包的文件path
	 * @return 对应指定的包的Entry
	 */
	public static IExternalResPathEntry newExternalLibEntry(IPath path) {
		return new ExternalResPathEntry(IResPathEntry.RPE_LIBRAY, IARESModuleRoot.KIND_BINARY, null, path);
	}
	
}
