package com.hundsun.ares.studio.jres.compiler;

public interface CompilerConstants {

	/**
	 * 模块编译类型
	 */
	public static final String Compile_Module = "#jres.module#";
	/**
	 * 用于EObject类型的格式定义<BR>
	 * 需要2个参数，前一个是所在Package的URI，第二个是EClass名
	 */
	public static final String Compile_EObject_Format = "#emf?uri=%s&class=%s#";

}
