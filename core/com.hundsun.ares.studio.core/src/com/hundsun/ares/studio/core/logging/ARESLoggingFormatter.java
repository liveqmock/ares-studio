/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * 简化的日志Formatter
 * @author sundl
 */
public class ARESLoggingFormatter extends Formatter {

	private static final String FORMAT = "{1,time} [{2}]	{3}";
	private Date date = new Date();
	
	/* (non-Javadoc)
	 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
	 */
	@Override
	public String format(LogRecord record) {
		StringBuffer sb = new StringBuffer();
		String sequence = String.valueOf(record.getSequenceNumber());
		date.setTime(record.getMillis());
		String level = record.getLevel().getName();

		sb.append(MessageFormat.format(FORMAT, sequence, date, level, record.getMessage()));
		sb.append("\n");
		if (record.getThrown() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			Throwable t = record.getThrown();
			t.printStackTrace(pw);
			sb.append(sw.toString());
		}
		return sb.toString();
	}

}
