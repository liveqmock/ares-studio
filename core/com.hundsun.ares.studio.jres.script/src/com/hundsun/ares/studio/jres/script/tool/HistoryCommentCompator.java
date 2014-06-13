/**
 * 
 */
package com.hundsun.ares.studio.jres.script.tool;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.model.RevisionHistory;

/**
 * @author yanwj06282
 * 
 */
public class HistoryCommentCompator implements Comparator<RevisionHistory> {

	@Override
	public int compare(RevisionHistory o1, RevisionHistory o2) {
		String v1 = o1.getVersion();
		String v2 = o2.getVersion();
		
		if (StringUtils.equals(v1, v2)) {
			return compareDate(o1.getModifiedDate(), o2.getModifiedDate() ,-1);
		}
		
		if (compareVersion(v2, v1)) {
			return 1;
		} else {
			return -1;
		}
	}

	public static int compareDate(String v1 , String v2 , int st){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date d1 = sdf.parse(v1);
			Date d2 = sdf.parse(v2);
			if (d1.getTime() > d2.getTime()) {
				return st;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -st;
	}
	
	public static int foramtVersion(String version) {
		StringBuffer sb = new StringBuffer("0");
		for (char v : version.toCharArray()) {
			if (Character.isDigit(v)) {
				sb.append(v);
			}
		}
		return Integer.parseInt(sb.toString());
	}

	/**
	 * 字符串比较大小，v1>=v2 返回true ,反之返回false
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static boolean compareVersion(String v1, String v2) {
		if (StringUtils.equals(v1, v2)) {
			return true;
		}
		String[] v1s = StringUtils.split(v1, ".");
		String[] v2s = StringUtils.split(v2, ".");
		if (v1 == null || v2 == null) {
			return false;
		}
		try {
			String[] tempVs = null;
			if (v1s.length > v2s.length) {
				tempVs = new String[v1s.length];
				System.arraycopy(v2s, 0, tempVs, 0, v2s.length);
				for (int i = 0; i < tempVs.length - v2s.length; i++) {
					tempVs[v2s.length + i] = "0";
				}
				return compareInt(v1s, tempVs);
			} else {
				tempVs = new String[v2s.length];
				System.arraycopy(v1s, 0, tempVs, 0, v1s.length);
				for (int i = 0; i < tempVs.length - v1s.length; i++) {
					tempVs[v1s.length + i] = "0";
				}
				return compareInt(tempVs, v2s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean compareInt(String[] v1, String[] v2) {
		for (int i = 0; i < v1.length; i++) {
			int v1i = Integer.parseInt(v1[i]);
			int v2i = Integer.parseInt(v2[i]);
			if (v1i > v2i) {
				return true;
			} else if (v1i < v2i) {
				return false;
			}
		}
		return false;
	}

}
