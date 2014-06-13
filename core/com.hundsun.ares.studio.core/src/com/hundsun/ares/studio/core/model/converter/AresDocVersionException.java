/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.model.converter;

import java.io.PrintWriter;

/**
 * 转换器和文档的版本有冲突导致的读取异常
 * @author maxh
 */
public class AresDocVersionException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3581446075769761092L;

	String converterVersion;
	String docVersion;
	
	/**
	 * 
	 */
	public AresDocVersionException(String converterVersion,String docVersion) {
		this.converterVersion = converterVersion;
		this.docVersion = docVersion;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
	 */
	@Override
	public void printStackTrace(PrintWriter s) {
		s.println("/*************************************************************************/");
		s.println("文档版本不兼容，解析器无法解析");
		s.println("解析器版本:" + converterVersion);
		s.println("文档版本:" + docVersion);
		s.println("/*************************************************************************/");
		super.printStackTrace(s);
	}
}
