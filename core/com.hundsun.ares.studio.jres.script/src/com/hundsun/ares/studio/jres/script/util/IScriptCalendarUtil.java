/**
 * 
 */
package com.hundsun.ares.studio.jres.script.util;

import java.util.Date;

/**
 * @author yanwj06282
 *
 */
public interface IScriptCalendarUtil {

	public Date addMonth(String date, int pi);
	
	public Date addDay(String date,int pi);
	
	public int getMonthLastDay(String date, int pi);
	
	public String format(Date date, String pattern);
	
	public Date now();
	
}
