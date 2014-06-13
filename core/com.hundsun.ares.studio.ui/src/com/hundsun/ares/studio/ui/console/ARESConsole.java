/*
 * 系统名称: ARES 应用快速开发企业套件
 * 模块名称:
 * 类 名 称: ARESConsole.java
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
package com.hundsun.ares.studio.ui.console;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import com.hundsun.ares.studio.core.ConsoleHelper;


/**
 * ARES控制台。
 * 
 * @author mawb
 */
public class ARESConsole extends MessageConsole {
	
	protected MessageConsoleStream infoStream;
	protected MessageConsoleStream warningStream;
	protected MessageConsoleStream errorStream;
	
	public ARESConsole() {
		super(ConsoleHelper.CONSOLE_ID, null);
		
		this.setTabWidth(4);
		
		this.infoStream = this.newMessageStream();
		this.warningStream = this.newMessageStream();
		this.errorStream = this.newMessageStream();
		
		this.loadPreferences();
	}
	
	protected void loadPreferences() {
		// 2011-09-13 sundl 修改为使用系统颜色
		// 设置信息流颜色
		
		// UI操作必须由UI线程执行，否则将抛出 org.eclipse.swt.SWTException: Invalid thread access
		// by xuzhen at 2011-09-19
		// TODO 需要测试代码
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				infoStream.setColor(Display.getDefault().getSystemColor(SWT.COLOR_BLACK));
				
				// 设置警告流颜色
				warningStream.setColor(Display.getDefault().getSystemColor(SWT.COLOR_DARK_YELLOW));
				
				// 设置错误流颜色
				errorStream.setColor(Display.getDefault().getSystemColor(SWT.COLOR_RED));
			}
		});

	}
	
	/**
	 * @return the infoStream
	 */
	public MessageConsoleStream getInfoStream() {
		return infoStream;
	}

	/**
	 * @return the warningStream
	 */
	public MessageConsoleStream getWarningStream() {
		return warningStream;
	}

	/**
	 * @return the errorStream
	 */
	public MessageConsoleStream getErrorStream() {
		return errorStream;
	}

	public void shutdown() {
		super.dispose();
		
		ConsolePlugin.getDefault().getConsoleManager().removeConsoles(new IConsole[] {this});
		
		// unsupported in Eclipse IDE 3.0
//		try {this.infoStream.close();} catch (Exception ex) {}
//		try {this.warningStream.close();} catch (Exception ex) {}
//		try {this.errorStream.close();} catch (Exception ex) {}
		
		// UI操作必须由UI线程执行，否则将抛出 org.eclipse.swt.SWTException: Invalid thread access
		// by xuzhen at 2011-09-19
		// TODO 需要测试代码
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				try {infoStream.close();} catch (Exception ex) {}
				try {warningStream.close();} catch (Exception ex) {}
				try {errorStream.close();} catch (Exception ex) {}
			}
		});
	
	}

}
