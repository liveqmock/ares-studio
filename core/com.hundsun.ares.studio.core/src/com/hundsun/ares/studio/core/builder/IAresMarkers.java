/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.builder;

import com.hundsun.ares.studio.core.ARESCore;

/**
 * 
 * @author sundl
 */
public interface IAresMarkers {

	String MARKER_ID = ARESCore.PLUGIN_ID + ".aresproblemmarker";
	
	/**
	 * 编辑器跳转用标记
	 */
	String BOOK_MARKER_ID = ARESCore.PLUGIN_ID + ".aresbookmarker";
	
	/**
	 * 引用资源包问题marker
	 */
	String REFERLIB_MARKER_ID = ARESCore.PLUGIN_ID + ".aresreferlibproblemmarker";
}
