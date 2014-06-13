/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.util;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;

/**
 * 
 * 用于运行时的控制台输出
 * @author maxh
 *
 */
public class AresConsole {
	static AresConsole console;
	MessageConsole messageConsole;
	MessageConsoleStream messageOut;
	MessageConsoleStream errorOut;
	
	private AresConsole() {
	   messageConsole = new MessageConsole("ARES控制台",null);
	   ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]{ messageConsole });
	   messageOut = messageConsole.newMessageStream();
	   errorOut = messageConsole.newMessageStream();
	   errorOut.setColor(ARESEditorPlugin.getDefault().getColorManager().getColor(HSColorManager.RED));
	}
	
	static public AresConsole getInstance(){
		if(console == null){
			console = new AresConsole();
		}
		return console;
	}
	
	/**
	 * 控制台输出一般信息
	 * @param outString
	 */
	public void consoleMessage(String outString){
		try {
			messageOut.print(outString);
			errorOut.print("\n");
			messageOut.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 控制台输出错误信息
	 * @param outString
	 */
	public void consoleError(String outString){
		try {		
			errorOut.print(outString);
			errorOut.print("\n");
			errorOut.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
