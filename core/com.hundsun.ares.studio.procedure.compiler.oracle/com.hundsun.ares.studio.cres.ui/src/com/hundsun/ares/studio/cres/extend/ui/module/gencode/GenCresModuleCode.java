/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.extend.ui.module.gencode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.ModuleGeneratorHelper;
import com.hundsun.ares.studio.engin.logic.ResourceEngin;

/**
 * @author qinyuan
 *
 */
public abstract class GenCresModuleCode implements IGenCresModuleCode{

	private static final Logger logger = Logger.getLogger(GenCresModuleCode.class);
	
	/**
	 * 是否生成前置代码
	 */
	protected boolean isHeadCode = false;
	/**
	 * 是否生成后置代码
	 */
	protected boolean isEndCode = false;
	
	public void setIsHeadCode(boolean isHeadCode){
		this.isHeadCode = isHeadCode;
	}
	
	public void setIsEndCode(boolean isEndCode){
		this.isEndCode = isEndCode;
	}
	
	protected boolean isHeadCode(){
		return isHeadCode;
	}
	
	protected boolean isEndCode(){
		return isEndCode;
	}
	
	//记录错误信息
	protected StringBuffer errLog;
	
	protected Map<String, StringBuffer> resCodeCache = new HashMap<String, StringBuffer>();//缓存过程代码,key为“过程对象号&&过程全名”
	
	/**
	 * 清空缓存
	 */
	public void clearCache() {
		resCodeCache.clear();
	}
	
	/**
	 * 获取模块名
	 * 如果模块的英文名为自动生成的，如“m+[z中文名hash码]”，则返回模块中文名，
	 * 否则返回模块英文名
	 * @param module
	 * @return
	 */
	protected String getModuleNameFromAutoCreateModuleName(IARESModule module) {
		String name = module.getShortName();
		Pattern p = Pattern.compile("m\\d+");
		Matcher m = p.matcher(name);
		if(m.matches()) {
			try {
				return ModuleGeneratorHelper.getModuleProperty(module).getString(ICommonModel.CNAME);
			} catch (Exception e) {
				e.printStackTrace();
				return name;
			}
		}else {
			return name;
		}
		
	}

	/**
	 * @param errLog the errLog to set
	 */
	public void setErrLog(StringBuffer errLog) {
		this.errLog = errLog;
	}
	
	/**
	 * 获取代码生成的上下文
	 * @param project
	 * @return
	 * 	默认返回一个空的上下文
	 */
	public Map<Object, Object> getContext(IARESProject project) {
		return new HashMap<Object, Object>();
	}

	
	/**
	 * 编译命令文件
	 * @param gccName 编译文件名
	 * @param path 文件生成路径
	 * @param isWithPath 生成代码是否带目录
	 */
	protected void createMakeOrderFile(String gccName, String path,boolean isWithPath,String charset) {
		if(isWithPath){//带目录才需要生成
			String fileName = path + "makeall";//makefile改为makeall
			StringBuffer content = new StringBuffer();
			content.append("make -f " + gccName + " ORA_VER=10");//-f后不要带空格，否则会找不到目标编译的GCC文件
			
			//写入文件
			writeToFile(fileName, content.toString(),charset);
			
			String fileName2 = path + "makeclean";//makefile改为makeall
			StringBuffer content2 = new StringBuffer();
			content2.append("make -f " + gccName + " ORA_VER=10 clean");//-f
			//写入文件
			writeToFile(fileName2, content2.toString(),charset);
			
		}
	}
	
	/**
	 * 创建文件目录
	 * @param path 
	 */
	protected void createFilePath(String path) {
		File codeFolder = new File(path);
		if (!codeFolder.exists()) {
			codeFolder.mkdirs();
		}
	}
	
	/**
	 * 将字符内容写入指定文件
	 * @param fileName
	 * @param contant
	 */
	protected void writeToFile(String fileName,String contant,String charset) {
		charset = StringUtils.defaultString(charset, "GB2312");
		try {
			FileUtils.writeStringToFile(new File(fileName), contant, charset);
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	/**
	 * @param msgQueue
	 * @param errorLogFileName
	 * @param path 
	 * @param charset 
	 */
	protected void createErrLog(Queue<IARESProblem> msgQueue,
			String errorLogFileName, String path,String charset) {
		StringBuffer errBuffer = new StringBuffer();
		if(msgQueue.size() > 0){
			
			errBuffer.append("/***" + "\r\n");
			errBuffer.append("错误提示信息：\r\n");
			for(IARESProblem pItem: msgQueue){
				errBuffer.append(ResourceEngin.instance.getCodeErrorMessage(pItem));
				errBuffer.append("\r\n");
			}
			errBuffer.append("***/" + "\r\n");
			writeToFile(path + errorLogFileName, errBuffer.toString(),charset);
			
			if(errLog != null){
				errLog.append(errorLogFileName + "文件:\n");
				errLog.append(errBuffer);
				errLog.append("\n\n");
			}
		}else{
			//没有错误信息，删除日志文件
			File errorLogFile = new File(path + errorLogFileName);
			if(errorLogFile.exists()){
				errorLogFile.delete();
			}
		}
	}
}
