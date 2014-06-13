/**
 * 
 */
package com.hundsun.ares.studio.jres.model.chouse.util;

import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.jres.script.tool.HistoryCommentCompator;

/**
 * @author yanwj06282
 *
 */
public class RevHistoryCompator implements Comparator<RevisionHistory> {

	@Override
	public int compare(RevisionHistory o1, RevisionHistory o2) {
		String v1 = o1.getVersion();
		String v2 = o2.getVersion();
		
		if (StringUtils.equals(v1, v2)) {
			return HistoryCommentCompator.compareDate(o1.getModifiedDate(), o2.getModifiedDate() ,1);
		}
		
		if (HistoryCommentCompator.compareVersion(v2, v1)) {
			return -1;
		}else {
			return 1;
		}
	}
}
