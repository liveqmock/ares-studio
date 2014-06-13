/**
 * 源程序名称：DisplayItem.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import org.eclipse.emf.ecore.EObject;

/**
 * 显示用的对象，在某些情况下，不希望直接显示对应的EObject，从而需要在外层包装一下，以区分显示对象和原生对象，从而在需要的时候，进行不同的处理。
 * 比如输入输出对象的Block中显示对象类型的属性参数的时候，这些字节点原生类型都是一样的Parameter类，但是，他们的属性是否可以编辑，背景色等方面和其他
 * 的Parameter都不同，这时候可以用这个类进行包装。
 * 
 * @author sundl
 * @see ParameterConentProvider
 */
public class DisplayItem {
	
	public EObject eObject;

	public DisplayItem(EObject eObject) {
		this.eObject = eObject;
	}
	
}
