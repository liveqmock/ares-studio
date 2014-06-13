/**
 * 源程序名称：MetadataContentProposalProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.support;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.assist.JresResourceRefConentProposalPovider;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

/**
 * 提示元数据引用的时候使用的ProposalContentProvider
 * @author yanwj06282
 */
public class TableFieldContentProposalProvider extends JresResourceRefConentProposalPovider {

	public TableFieldContentProposalProvider(IContentProposalProviderHelper helper, String resType, IARESProject project) {
		super(helper, resType, project);
	}

	@Override
	protected boolean filter(Object inputItem, Object element) {
		if (inputItem instanceof Map && element instanceof TableResourceData) {
			@SuppressWarnings("rawtypes")
			Map map = (Map) inputItem;
			IARESResource res = (IARESResource) map.get(IResourceTable.TARGET_RESOURCE);
			TableResourceData tdb;
			try {
				tdb = res.getInfo(TableResourceData.class);
				if (res.getARESProject().equals(project) && StringUtils.equals(((TableResourceData)element).getFullyQualifiedName(), tdb.getFullyQualifiedName())) { //同资源才需要过滤
					return true;
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
}
