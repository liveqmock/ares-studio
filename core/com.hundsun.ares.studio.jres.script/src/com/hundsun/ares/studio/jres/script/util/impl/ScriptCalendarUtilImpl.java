/**
 * 
 */
package com.hundsun.ares.studio.jres.script.util.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.jres.script.util.IScriptCalendarUtil;

/**
 * @author yanwj06282
 *
 */
public class ScriptCalendarUtilImpl implements IScriptCalendarUtil {

	public static ScriptCalendarUtilImpl instance = new ScriptCalendarUtilImpl();;
	
	private static final Logger console = ConsoleHelper.getLogger();
	
	@Override
	public Date addMonth(String date, int pi) {
		Calendar calendar = null;
		try {
			String year = StringUtils.substring(date, 0, 4);
			String month = StringUtils.substring(date, 4);
			calendar = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), 0);
			calendar.add(Calendar.MONTH, pi);
		} catch (Exception e) {
			console.error(String.format("时间格式错误  [s%]", date), e);
		}
		return calendar.getTime();
	}

	@SuppressWarnings("static-access")
	public int getMonthLastDay(String date, int pi){

		Calendar calendar = null;
		try {
			String year = StringUtils.substring(date, 0, 4);
			String month = StringUtils.substring(date, 4);
			calendar = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), 0);//月份从0开始，需要自动减1
			calendar.add(Calendar.MONTH, pi);
			return calendar.getActualMaximum(calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			console.error(String.format("时间格式错误  [s%]", date), e);
		}
		return 30;
	}
	
	@Override
	public String format(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public Date now(){
		return new Date();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.util.IScriptCalendarUtil#addDay(java.lang.String, int)
	 */
	@Override
	public Date addDay(String date, int pi) {
		Calendar calendar = null;
		try {
			String year = StringUtils.substring(date, 0, 4);
			String month = StringUtils.substring(date, 4,6);
			String day = StringUtils.substring(date, 6);
			calendar = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));//月份从0开始，需要自动减1
			calendar.add(Calendar.DATE, pi);
		} catch (Exception e) {
			console.error(String.format("时间格式错误  [s%]", date), e);
		}
		return calendar.getTime();
	}

}
