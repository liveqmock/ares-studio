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
package com.hundsun.ares.studio.jres.database.ui.support;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.fieldassist.IContentProposal;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.metadata.service.IStandardField;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.ui.assist.JRESContentPorposalHelper;
import com.hundsun.ares.studio.ui.cellEditor.ARESContentProposal;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

/**
 * 表空间提示
 * @author yanwj06282
 */
public class TableFieldContentProposalHelper extends JRESContentPorposalHelper implements IContentProposalProviderHelper{

	@Override
	protected IContentProposal getProposal(String contents, int position, EObject item, IARESResource resource) {
		if (item instanceof TableColumn && resource != null) {
			TableColumn column = (TableColumn) item;
			String content = column.getName();
			String chineseName = StringUtils.EMPTY;
			if (content == null || !content.toUpperCase().contains(contents.toUpperCase())) {
				return null;
			}
			
			IMetadataService metadataService = DataServiceManager.getInstance().getService(resource.getARESProject(), IMetadataService.class);
			IStandardField stdField = metadataService.getStandardField(column.getFieldName());
			if (stdField != null) {
				chineseName = stdField.getChineseName();
			}
			return new ARESContentProposal(content, chineseName);
		}
		return null;
	}

}
