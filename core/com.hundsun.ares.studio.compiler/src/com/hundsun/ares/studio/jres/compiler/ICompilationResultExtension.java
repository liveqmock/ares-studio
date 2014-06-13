/**
 * 源程序名称：ICompilationResultExtension.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.jres.compiler;

/**
 * 编译结果的一种扩展，这种编译结果是一个文本串，一般不生成文件
 * @author gongyf
 *
 */
public interface ICompilationResultExtension {
	
	/**
	 * 返回编译的文本结果
	 * @return
	 */
	String getTextResult();
}
