/**
 * 源程序名称：DBCompilationResult.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.compiler
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.database.compiler;

import java.util.LinkedHashMap;
import java.util.Map;

import com.hundsun.ares.studio.jres.compiler.CompilationResult;
import com.hundsun.ares.studio.jres.compiler.ICompilationResultExtension;

/**
 * @author gongyf
 *
 */
public class DBCompilationResult extends CompilationResult implements
		ICompilationResultExtension {

	private String sql;
	
	private Map<String,StringBuffer> sqlByUser = new LinkedHashMap<String,StringBuffer>();
	
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	@Override
	public String getTextResult() {
		return sql;
	}

	public Map<String,StringBuffer> getSqlByUser() {
		return sqlByUser;
	}
	
	public void setSqlByUser(Map<String, StringBuffer> sqlByUser) {
		this.sqlByUser = sqlByUser;
	}

}
