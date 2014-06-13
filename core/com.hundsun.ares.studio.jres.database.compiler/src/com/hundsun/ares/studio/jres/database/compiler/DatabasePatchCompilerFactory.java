/**
 * 源程序名称：DatabaseResourceCompilerFactory.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.compiler
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.compiler;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.compiler.IResourceCompiler;
import com.hundsun.ares.studio.jres.compiler.IResourceCompilerFactory;
import com.hundsun.ares.studio.jres.database.constant.IDBConstant;

/**
 * @author gongyf
 *
 */
public class DatabasePatchCompilerFactory implements
		IResourceCompilerFactory {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.core.compiler.IResourceCompilerFactory#create(java.lang.Object)
	 */
	@Override
	public IResourceCompiler create(Object resource) {
		return new DatabaseInfoCompiler(IDBConstant.JS_GEN_INFO_PATCH_FUNCTION);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.core.compiler.IResourceCompilerFactory#isSupport(com.hundsun.ares.studio.jres.core.compiler.IARESProject)
	 */
	@Override
	public boolean isSupport(IARESProject project) {
		return true;
	}

}
