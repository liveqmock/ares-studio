/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor;

import com.hundsun.ares.studio.core.IARESProject;

/**
 * 项目状态接口，实现改接口的类，都必须注入工程对象
 * 
 * @author yanwj06282
 */
public interface IProjectExtensibleStatus {

	public void setAresProject(IARESProject project);
	
}
