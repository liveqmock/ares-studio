/**
 * 
 */
package com.hundsun.ares.studio.jres.database.utils;

import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.model.RevisionHistory;

/**
 * @author yanwj06282
 *
 */
public class IRevHistoryVersionCompartor implements Comparator<RevisionHistory> {

	@Override
	public int compare(RevisionHistory o1, RevisionHistory o2) {
		String d1 = ((RevisionHistory)o1).getVersion();
		String d2 = ((RevisionHistory)o2).getVersion();
		
		if (compareDate(d1, d2)){
			return -1;
		}else {
			return 1;
		}
	}
	
	private boolean compareDate(String v1 , String v2){
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
