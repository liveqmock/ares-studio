/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.script.util.impl;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.jres.script.engin.ScriptUtils;
import com.hundsun.ares.studio.jres.script.util.IScriptOutUtil;

/**
 * @author lvgao
 * 
 */
public class ScriptOutUtilImpl implements IScriptOutUtil {
	private int mode = ScriptUtils .MODE_CONTEXT_MENU;
	static final Logger console = ConsoleHelper.getLogger();
	
	public ScriptOutUtilImpl(int mode){
		this.mode = mode;
	}
	@Override
	public void dialog(final String message) {
		if(this.mode!=ScriptUtils.MODE_CMD_BUILDER){
			Display.getDefault().syncExec(new Runnable() {
				@Override
				public void run() {
					MessageDialog.openInformation(Display.getDefault().getActiveShell(), "提示", message);
				}
			});
		} else {//如果命令行
			this.info(message);
		}
	}

	@Override
	public void dialogError(final String message) {
		if(this.mode!=ScriptUtils.MODE_CMD_BUILDER){
			Display.getDefault().syncExec(new Runnable() {
				@Override
				public void run() {
					MessageDialog.openError(Display.getDefault().getActiveShell(), "错误", message);
				}
			});
		}else{//如果命令行
			console.error(message);
		}
		
	}
	
	@Override
	public void info(String message) {
		console.info(message);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.util.IScriptOutUtil#warn(java.lang.String)
	 */
	@Override
	public void warn(String message) {
		// TODO Auto-generated method stub
		console.warn(message);
	}

}
