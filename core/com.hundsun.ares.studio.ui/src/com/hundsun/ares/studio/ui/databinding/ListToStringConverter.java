/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.databinding;

import java.util.List;

import org.eclipse.core.databinding.conversion.IConverter;

/**
 * 列表到字符串转换类。
 * 
 * @author mawb
 */
public class ListToStringConverter implements IConverter {
	
	private String separator;
	
	public ListToStringConverter(String separator) {
		this.separator = separator;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#convert(java.lang.Object)
	 */
	public Object convert(Object fromObject) {
		StringBuffer sb = new StringBuffer();
		for (String source : (List<String>)fromObject) {
			sb.append(source);
			sb.append(separator);
		}
		return sb.length() > 0 ? sb.substring(0, sb.length() - separator.length()) : "";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#getFromType()
	 */
	public Object getFromType() {
		return List.class;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.databinding.conversion.IConverter#getToType()
	 */
	public Object getToType() {
		return String.class;
	}

}
