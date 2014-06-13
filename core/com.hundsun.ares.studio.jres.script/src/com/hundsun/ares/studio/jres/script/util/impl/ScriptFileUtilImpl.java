/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.jres.script.ScriptPlugin;
import com.hundsun.ares.studio.jres.script.util.IScriptFileUtil;

/**
 * @author lvgao
 *
 */
public class ScriptFileUtilImpl  implements IScriptFileUtil{
	
	public static ScriptFileUtilImpl instance = new ScriptFileUtilImpl();
	public String absolutePath = StringUtils.EMPTY;
	

	static final Logger console = ConsoleHelper.getLogger();
	
	/**
	 * 生成文件。
	 * 
	 * @param fileName 带全路径的文件名。
	 * @param content 文件内容。
	 * @param charsetName 编码格式。
	 * @param isisRecursive 是否递归创建父目录。
	 * 
	 * @return
	 */
	public static IStatus genFile(String fileName, String content, String errLogInfo, String charsetName, boolean isRecursive) {
		File file = createFile(fileName, isRecursive);
		if (file == null) {
			String message = "文件创建失败！创建路径：" + fileName;
			return new Status(IStatus.ERROR, ScriptPlugin.PLUGIN_ID, message);
		}
		
		boolean result = writeToFile(file, content, charsetName);
		if (!result) {
			String message = "写文件失败！文件路径：" + fileName;
			return new Status(IStatus.ERROR, ScriptPlugin.PLUGIN_ID, message);
		}
		return Status.OK_STATUS;
	}
	
	
	/**
	 * 将指定文本写入文件。
	 * 
	 * @param file 待写入文件。
	 * @param content 文件内容。
	 * @param charsetName 编码格式。
	 * 
	 * @return 是否写文件成功。
	 */
	public static boolean writeToFile(File file, String content, String charsetName) {
		try {
			PrintWriter print = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charsetName)));
			print.print(content);
			print.flush();
			print.close();
			console.warn(String.format("写文件成功:[%s]",file.getPath().toString()));
			return true;
		} catch (Exception e) {
			console.error(String.format("写文件：[%s]出错，原因:[%s]",file.getPath().toString(),e.getMessage()), e);
		} 
		return false;
	}
	
	/**
	 * 创建文件，创建失败后返回null。
	 * 
	 * @param fileName 带全路径的文件名。
	 * @param isRecursive 是否向上递归创建。
	 * @return
	 */
	public static File createFile(String fileName, boolean isRecursive) {
		File file = new File(fileName);
		if(!file.exists()) {
			try {
				if (isRecursive) {
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
				console.debug(String.format("创建文件成功:[%s]",file.getPath().toString()));
			} catch (IOException e) {
				console.error(String.format("创建文件：[%s]出错:原因：[%s]",file.getPath().toString(),e.getMessage()));
				file = null;
			}
		}
		return file;
	}

	@Override
	public boolean write(String filepath, String content, String charsetName) {
		File file = createFile(filepath,true);
		if ( file != null ) {
			this.absolutePath = file.getAbsolutePath();
			return writeToFile(file,content,charsetName);
		}
		
		return false;
	}


	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.util.IScriptFileUtil#getAbsolutePath()
	 */
	@Override
	public String getAbsolutePath() {
		return this.absolutePath;
	}
	
}
