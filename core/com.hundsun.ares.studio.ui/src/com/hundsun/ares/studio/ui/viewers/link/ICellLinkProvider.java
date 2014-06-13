/**
 * 源程序名称：ICellLinkProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.viewers.link;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ViewerCell;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.util.Pair;


/**
 * @author gongyf
 *
 */
public interface ICellLinkProvider {
	Pair<EObject, IARESResource> getLinkedObject(ViewerCell cell);
}
