/**
 * 源程序名称：EMFKeyConstructor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor;

import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;

import com.hundsun.ares.studio.ui.validate.IKeyConstructor;
import com.hundsun.ares.studio.ui.validate.KeyParameter;

/**
 * @author gongyf
 *
 */
public class EMFKeyConstructor implements IKeyConstructor {

	private static Object NULL = new Object();
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.validate.IKeyConstructor#constructKey(java.lang.Object)
	 */
	@Override
	public KeyParameter constructKey(Object problem) {
		if (problem instanceof Diagnostic) {
			List<?> dataList = ((Diagnostic) problem).getData();
			if (dataList.size() >= 2) {
				return new KeyParameter(new Object[]{dataList.get(0), dataList.get(1)});
			}
			
		}
		return new KeyParameter(NULL);
	}

}
