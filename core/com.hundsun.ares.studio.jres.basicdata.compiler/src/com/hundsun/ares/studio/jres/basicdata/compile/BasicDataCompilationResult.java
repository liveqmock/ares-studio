package com.hundsun.ares.studio.jres.basicdata.compile;

import com.hundsun.ares.studio.jres.compiler.CompilationResult;
import com.hundsun.ares.studio.jres.compiler.ICompilationResultExtension;

public class BasicDataCompilationResult extends CompilationResult implements
		ICompilationResultExtension {
	
	private String sql;
	
	@Override
	public String getTextResult() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

}
