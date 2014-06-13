/*
 * 系统名称: ARES 应用快速开发企业套件
 * 模块名称:
 * 类 名 称: ConsoleHelper.java
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

import org.apache.log4j.Logger;


/**
 * 控制台
 * @author sundl
 */
public class ConsoleHelper {

	public static final String CONSOLE_ID = "ARES控制台";
	private static final Logger consoleLogger = Logger.getLogger("com.hundsun.ares.studio.core.Console");
	
	public static void println(String message) {
		consoleLogger.info(message);
	}
	
	public static void print(String message) {
		consoleLogger.info(message);
	}
	
//	public static void logException(Throwable e) {
//		e.printStackTrace(ARESCore.getDefault().getSystemConsoleWriter());
//	}
	
//	public static void logException(String message, Throwable e) {
//		println(message);
//		logException(e);
//	}
	
	public static Logger getLogger() {
		return consoleLogger;
	}
	
}
