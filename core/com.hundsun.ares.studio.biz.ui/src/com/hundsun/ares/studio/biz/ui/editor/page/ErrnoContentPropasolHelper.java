/**
 * 源程序名称：ErrnoContentPropasolHelper.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl	
 */
package com.hundsun.ares.studio.biz.ui.editor.page;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.fieldassist.IContentProposal;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.ui.assist.JRESContentPorposalHelper;
import com.hundsun.ares.studio.ui.cellEditor.ARESContentProposal;

/**
 * @author sundl
 *
 */
public class ErrnoContentPropasolHelper extends JRESContentPorposalHelper{

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.assist.JRESContentPorposalHelper#getProposal(java.lang.String, int, org.eclipse.emf.ecore.EObject, com.hundsun.ares.studio.core.IARESResource)
	 */
	@Override
	protected IContentProposal getProposal(String contents, int position, EObject item, IARESResource resource) {
		if (item instanceof ErrorNoItem) {
			ErrorNoItem errornoItem = (ErrorNoItem) item;
			String no = errornoItem.getNo();
			String msg = errornoItem.getMessage();
			String level = errornoItem.getLevel();
			String desc = errornoItem.getDescription();
			String description = String.format("错误信息：%s\n错误级别:%s\n说明：%s\n", msg, level, desc);
			return new ARESContentProposal(no, null, description);
		}
		return null;
	}

}
