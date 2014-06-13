/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.engin.format;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hundsun.ares.studio.engin.trace.EngineTraceFlagHelper;

//import com.hundsun.hdt.internal.generate.procedure.util.CodeUtil;

/**
 *代码格式化工具
 *
 */
public class CodeFormater {

	private final static Logger logger = Logger.getLogger(CodeFormater.class);

	final public static int CODE_PROC = 0;
	final public static String procBegin = "EXEC SQL EXECUTE\r\n";
	final public static String procEnd = "END-EXEC;\r\n";
	final public static String procReplace = "//proc语句块替换啦啦啦";
	final public static String PROC_CMT = "--";
	final public static String PROC_CMT_REPLACE = "//sql注释替换啦啦啦";
	
	public static String commandString = "format/Uncrustify.exe -q -c format/default.cfg -l c ";
	
	public static void format(StringBuffer sb, int type){
		switch (type) {
		case CODE_PROC:
			formatProc(sb);
			break;
		default:
			break;
		}
	}
	
	public static StringBuffer formatProc(StringBuffer sb) {
//		return sb;
		List<String> replaceList = new ArrayList<String>();
		Runtime rt = Runtime.getRuntime();
		
		//对proc块进行整体替换 防止格式化工具胡乱操作
		while(sb.indexOf(procBegin) != -1){
			if(sb.indexOf(procEnd) != -1){
				int start = sb.indexOf(procBegin);
				int end = sb.indexOf(procEnd);
				replaceList.add(sb.substring(start, end+procEnd.length()));
				sb.replace(start, end+procEnd.length(), procReplace);
			}else{
				break;
			}

		}
		//目前先将注释符--替换掉格式化完之后再替换回--（以后可以寻求一个sql格式化工具）
		while(sb.indexOf(PROC_CMT) != -1){
			int start = sb.indexOf(PROC_CMT);
			sb.replace(start, start + PROC_CMT.length(), PROC_CMT_REPLACE);
		}
		StringBuffer buffer = new StringBuffer();
		try {
			Process ps = rt.exec(commandString);//"astyle --style=ansi");
			ps.getOutputStream().write(sb.toString().getBytes());
			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
			ps.getOutputStream().close();
			String s = br.readLine();
			while(s != null) {
				if(s.indexOf(procReplace) != -1){
					buffer.append(FormaterUtil.getFirstBlank(s));
					buffer.append(replaceList.get(0).replaceAll("\n", "\n"+FormaterUtil.getFirstBlank(s)));
					replaceList.remove(0);
				}else if(s.indexOf(PROC_CMT_REPLACE) != -1){
					//重新替换为--注释符
					buffer.append(s.replaceAll(PROC_CMT_REPLACE, PROC_CMT));
				}else{
					buffer.append(s);
				}
				
				buffer.append("\n");
				s = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return buffer;
	}
	//逻辑层使用这种格式化方法
	//不用处理PROC语句块格式化
	//可以缩短一些时间
	public static StringBuffer formatCByForce(StringBuffer sb) {
		Runtime rt = Runtime.getRuntime();
		
		StringBuffer buffer = new StringBuffer();
		try {
			Process ps = rt.exec(commandString);//"astyle --style=ansi");
			ps.getOutputStream().write(sb.toString().getBytes());
			BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
			ps.getOutputStream().close();
			String s = br.readLine();
			while(s != null) {
				buffer.append(s);
				buffer.append("\n");
				s = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}

		return buffer;
	}
	
	/**
	 * 
	 * @param sb
	 * @param isNeedFormat
	 * @return
	 */
	public static StringBuffer formatC(StringBuffer sb) {
		//代码格式化
		if(EngineTraceFlagHelper.getDebugTraceFlag()){
			return sb;
		}else{
			return CodeFormater.formatCByForce(sb);
		}
	}
	
	
	
	
	
}
