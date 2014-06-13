/**
 * 源程序名称：TableColumnStdFieldProposalProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.support;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.ui.assist.JresResourceRefConentProposalPovider;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

/**
 * @author sundl
 *
 */
public class TableColumnStdFieldProposalProvider extends JresResourceRefConentProposalPovider {

	/**
	 * @param helper
	 * @param resType
	 * @param project
	 */
	public TableColumnStdFieldProposalProvider(IContentProposalProviderHelper helper, String resType, IARESProject project) {
		super(helper, resType, project);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.assist.JresResourceRefConentProposalPovider#filter(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected boolean filter(Object inputItem, Object element) {
		return false;
	}

}
