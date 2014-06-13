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
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.ViewResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.SequenceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.TriggerResourceData;

/**
 * @author gongyf
 *
 */
public class DatabaseFullCompilerFactory implements
		IResourceCompilerFactory {

	@Override
	public IResourceCompiler create(Object resource) {
		if (resource instanceof ViewResourceData) {
			return new DatabaseInfoCompiler(IDBConstant.JS_GEN_VIEW_INFO_FUNCTION);
		}else if (resource instanceof TableResourceData){
			return new DatabaseInfoCompiler(IDBConstant.JS_GEN_TABLE_INFO_FUNCTION);
		}else if (resource instanceof SequenceResourceData) {
			return new DatabaseInfoCompiler(IDBConstant.JS_GEN_OSEQUENCE_INFO_FUNCTION);
		}else if (resource instanceof TriggerResourceData) {
			return new DatabaseInfoCompiler(IDBConstant.JS_GEN_OTRIGGER_INFO_FUNCTION);
		}
		return null;
	}

	@Override
	public boolean isSupport(IARESProject project) {
		return true;
	}

}
