/**
 * 
 */
package com.hundsun.ares.studio.jres.database.compiler;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.compiler.IResourceCompiler;
import com.hundsun.ares.studio.jres.compiler.IResourceCompilerFactory;
import com.hundsun.ares.studio.jres.database.constant.IDBConstant;

/**
 * @author yanwj06282
 *
 */
public class TablespaceFullCompilerFactory implements IResourceCompilerFactory {

	@Override
	public boolean isSupport(IARESProject project) {
		return true;
	}

	@Override
	public IResourceCompiler create(Object resource) {
		return new TablespaceInfoCompiler(IDBConstant.JS_GEN_TABLE_SPACE_INFO_FUNCTION);
	}

}
