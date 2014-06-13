/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.databinding;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.databinding.conversion.IConverter;

/**
 * 字符串到列表转换类。
 * 
 * @author mawb
 */
public class StringToListConverter implements IConverter {
	
	private String separator;
	
	public StringToListConverter(String separator) {
		this.separator = separator;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#convert(java.lang.Object)
	 */
	public Object convert(Object fromObject) {
		List<String> targetList = new ArrayList<String>();
		if (fromObject != null) {
			String[] segments = ((String)fromObject).split(separator);
			for (String segment : segments) {
				if (!StringUtils.isEmpty(segment)) {
					targetList.add(segment);
				}
			}
		}
		return targetList;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#getFromType()
	 */
	public Object getFromType() {
		return String.class;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#getToType()
	 */
	public Object getToType() {
		return List.class;
	}

}
