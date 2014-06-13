/**
 * 
 */
package com.hundsun.ares.studio.atom.ui.util;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import com.hundsun.ares.studio.core.model.RevisionHistory;

/**
 * @author yanwj06282
 *
 */
public class IRevHistoryModifyDateCompartor implements Comparator<RevisionHistory> {

	@Override
	public int compare(RevisionHistory o1, RevisionHistory o2) {
		String d1 = ((RevisionHistory)o1).getModifiedDate();
		String d2 = ((RevisionHistory)o2).getModifiedDate();
		
		return compareDate(d1, d2 ,-1);
	}
	
	private int compareDate(String v1 , String v2 , int st){
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
}
