/**
 * 源程序名称：Log.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.util.log;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * 解析或处理Excel过程中的日志处理器
 * @author sundl
 *
 */
public class Log {
	
	private static Logger logger = Logger.getLogger(Log.class);
	
	/**
	 * 记录出错日志的时候的一个定位信息。
	 * @author sundl
	 */
	public static class Location {
		public String file;
		public String sheet;
		public int row;
		public int column;
		
		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			if (file != null) {
				sb.append("File:\t" + file + ",\n");
			}
			if (sheet != null) {
				sb.append("Sheet页:\t");
				sb.append(sheet);
				sb.append(",\n");
			}
			sb.append("行号:\t" + (row + 1));
			return sb.toString();
		}
	}
	
	public static Location createLocation() {
		return new Location();
	}
	
	public static Location createLocation(String sheet) {
		return createLocation(sheet, -1, -1);
	}
	
	public static Location createLocation(String sheet, int row, int column) {
		Location loc = new Location();
		loc.sheet = sheet;
		loc.row = row;
		loc.column = column;
		return loc;
	}
	
	public static class LogEntry {
		int level;
		String msg;
		Location location;
		
		/**
		 * @param msg
		 * @param location
		 */
		public LogEntry(String msg, Location location) {
			super();
			this.msg = msg;
			this.location = location;
		}
		
	}
	
	public static class LogGroup {
		private List<LogEntry> errors = new ArrayList<Log.LogEntry>();
		private List<LogEntry> warns = new ArrayList<Log.LogEntry>();
		private List<LogEntry> infos = new ArrayList<Log.LogEntry>();
		
		public void info(String msg, Location location) {
			infos.add(new LogEntry(msg, location));
			logger.info(msg);
		}
		
		public void warn(String msg, Location location) {
			warns.add(new LogEntry(msg, location));
			logger.warn(msg);
		}
		
		public void error(String msg, Location location) {
			errors.add(new LogEntry(msg, location));
			logger.error(msg);
		}
	}
	
	// 错误级别
	public static final int LEVEL_INFO = 0;
	public static final int LEVEL_WARN = 1;
	public static final int LEVEL_ERROR = 2;
	
	
	protected List<LogEntry> errors = new ArrayList<Log.LogEntry>();
	protected List<LogEntry> warns = new ArrayList<Log.LogEntry>();
	protected List<LogEntry> infos = new ArrayList<Log.LogEntry>();
	
	public void info(String msg, Location location) {
		infos.add(new LogEntry(msg, location));
		logger.info(msg);
	}
	
	public void warn(String msg, Location location) {
		warns.add(new LogEntry(msg, location));
		logger.warn(msg);
	}
	
	public void error(String msg, Location location) {
		errors.add(new LogEntry(msg, location));
		logger.error(msg);
	}
	
	
	protected String genErrorInfo(List<LogEntry> errors, List<LogEntry> warns) {
		StringBuffer sb = new StringBuffer();
		sb.append("<tbody>");
		int num = 1;
		
		for (LogEntry entry : errors) {
			// 条目太多，只显示前2000条
			if (num > 2000)
				break;
			
			sb.append("<tr class=\"danger\">");
			if (entry.location == null) {
				sb.append(String.format("<td>%s</td>", num));
				sb.append(String.format("<td>%s</td>", "错误"));
				sb.append(String.format("<td>%s</td>", StringUtils.replace(entry.msg, "\n", "<br>")));
				sb.append(String.format("<td colspan=\"2\">%s</td>", StringUtils.EMPTY));
				sb.append("</tr>");
			} else {
				sb.append(String.format("<td rowspan=\"4\">%s</td>", num));
				sb.append(String.format("<td rowspan=\"4\">%s</td>", "错误"));
				sb.append(String.format("<td rowspan=\"4\">%s</td>", StringUtils.replace(entry.msg, "\n", "<br>")));
				sb.append("<tr class=\"danger\">");
				sb.append("<th>文件</th>");
				sb.append(String.format("<td>%s</td>", StringUtils.defaultString(entry.location.file)));
				sb.append("</tr>");
				sb.append("<tr class=\"danger\">");
				sb.append("<th>Sheet页</th>");
				sb.append(String.format("<td>%s</td>", StringUtils.defaultString(entry.location.sheet)));
				sb.append("</tr>");
				sb.append("<tr class=\"danger\">");
				sb.append("<th>行号</th>");
				sb.append(String.format("<td>%s</td>", StringUtils.defaultString(String.valueOf(entry.location.row))));
				sb.append("</tr>");
				sb.append("</tr>");
			}
			num++;
		} 
		
		for (LogEntry entry : warns) {
			if (num > 2000)
				break;
			
			sb.append("<tr class=\"warning\">");
			if (entry.location == null) {
				sb.append(String.format("<td>%s</td>", num));
				sb.append(String.format("<td>%s</td>", "警告"));
				sb.append(String.format("<td>%s</td>", StringUtils.replace(entry.msg, "\n", "<br>")));
				sb.append(String.format("<td colspan=\"2\">%s</td>", StringUtils.EMPTY));
				sb.append("</tr>");
			} else {
				sb.append(String.format("<td rowspan=\"4\">%s</td>", num));
				sb.append(String.format("<td rowspan=\"4\">%s</td>", "警告"));
				sb.append(String.format("<td rowspan=\"4\">%s</td>", StringUtils.replace(entry.msg, "\n", "<br>")));
				sb.append("<tr class=\"warning\">");
				sb.append("<th>文件</th>");
				sb.append(String.format("<td>%s</td>", StringUtils.defaultString(entry.location.file)));
				sb.append("</tr>");
				sb.append("<tr class=\"warning\">");
				sb.append("<th>Sheet页</th>");
				sb.append(String.format("<td>%s</td>", StringUtils.defaultString(entry.location.sheet)));
				sb.append("</tr>");
				sb.append("<tr class=\"warning\">");
				sb.append("<th>行号</th>");
				sb.append(String.format("<td>%s</td>", StringUtils.defaultString(String.valueOf(entry.location.row))));
				sb.append("</tr>");
				sb.append("</tr>");
			}
			num++;
		} 

		sb.append("</tbody>");
		return sb.toString();
	}

}
