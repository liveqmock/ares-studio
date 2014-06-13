/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.database.compiler;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.compiler.IResourceCompiler;
import com.hundsun.ares.studio.jres.compiler.IResourceCompilerFactory;
import com.hundsun.ares.studio.jres.database.constant.IDBConstant;

/**
 * @author liaogc
 *
 */
public class DatabaseFullFKCompilerFactory implements IResourceCompilerFactory {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.core.compiler.IResourceCompilerFactory#create(java.lang.Object)
	 */
	@Override
	public IResourceCompiler create(Object resource) {
		return new DatabaseInfoCompiler(IDBConstant.JS_GEN_INFO_FOREIGN_KEY_FUNCTION);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.core.compiler.IResourceCompilerFactory#isSupport(com.hundsun.ares.studio.jres.core.compiler.IARESProject)
	 */
	@Override
	public boolean isSupport(IARESProject project) {
		return true;
	}
}
