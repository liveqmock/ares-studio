/**
 * 源程序名称：OracleSpaceContentProposalHelper
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
// 2012-2-22 sundl 修改用新的ContentProposal对象，可以实现用不同颜色显示中文名
// 另外删除无用代码语句。
package com.hundsun.ares.studio.jres.database.oracle.ui.viewer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.fieldassist.IContentProposal;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpace;
import com.hundsun.ares.studio.ui.assist.JRESContentPorposalHelper;
import com.hundsun.ares.studio.ui.cellEditor.ARESContentProposal;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

/**
 * 表空间提示
 */
// 2012-2-23 sundl 修改： 从基类继承，简化实现； 修改文档注释
public class OracleSpaceContentProposalHelper extends JRESContentPorposalHelper implements IContentProposalProviderHelper{

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.contentassist.JRESContentPorposalHelper#getProposal(java.lang.String, int, org.eclipse.emf.ecore.EObject)
	 */
	@Override
	protected IContentProposal getProposal(String contents, int position, EObject item, IARESResource resource) {
		if (item instanceof TableSpace && resource != null) {
			TableSpace tableSpace = (TableSpace) item;
			String content = tableSpace.getName();
			if (!content.toUpperCase().contains(contents.toUpperCase())) {
				return null;
			}
			
			String shortDesc = tableSpace.getChineseName();
			return new ARESContentProposal(content, shortDesc);
		}
		return null;
	}

}
