/**
 * 源程序名称：MetadataContentProposalHelper.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.editors.editingsupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.fieldassist.IContentProposal;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.context.INamespaceHelper;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;
import com.hundsun.ares.studio.internal.core.ArchiveARESResource;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.ui.assist.JRESContentPorposalHelper;
import com.hundsun.ares.studio.ui.cellEditor.ARESContentProposal;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

/**
 * 元数据引用的智能提示
 */
// 2012-2-23 sundl 从基类继承，简化实现
public class MetadataContentProposalHelper extends JRESContentPorposalHelper implements IContentProposalProviderHelper{

	/**
	 * 重复MAP
	 */
	protected List< String> duplicateList = new ArrayList<String>();
	
	protected IARESProject project;
	public MetadataContentProposalHelper(IARESProject project) {
		this.project = project;
	}
	
	public void initDuplicateList(Object[] input){
		//初始化重复列表
		this.duplicateList.clear();
		List< String> tlist = new ArrayList<String>();
		for(Object element:input){
			if(element instanceof MetadataItem){
				MetadataItem item = (MetadataItem) element;
				if(tlist.contains(item.getName())){
					if(!duplicateList.contains(item.getName())){
						duplicateList.add(item.getName());
					}
				}else{
					tlist.add(item.getName());
				}
			}
		}
		tlist.clear();
	}
	
	protected  String getContent(MetadataItem item, IARESResource resource) {
		if(duplicateList.contains(item.getName())){
			String nameSpace = "";
			if (resource != null){
				if(resource.getARESProject().equals(project)){
					if(resource instanceof ArchiveARESResource){
						//引用资源包
						nameSpace = INamespaceHelper.INSTANCE.getResourceNamespace(resource);
					}else{
						//当前工程
						nameSpace = "";
					}
				}else{
					//引用工程
					nameSpace = INamespaceHelper.INSTANCE.getResourceNamespace(resource);
				}
			}

			if(!StringUtils.isEmpty(nameSpace)){
				return String.format("%s.%s", nameSpace,item.getName());
			}
		}
		return item.getName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.contentassist.JRESContentPorposalHelper#getProposal(java.lang.String, int, org.eclipse.emf.ecore.EObject, com.hundsun.ares.studio.core.IARESResource)
	 */
	@Override
	protected IContentProposal getProposal(String contents, int position,
			EObject item, IARESResource resource) {
		if (item instanceof MetadataItem) {
			MetadataItem mdItem = (MetadataItem) item;
			String content = getContent(mdItem, resource);
			// 2012-04-27 sundl 如果为空不生成提示条目
			if (StringUtils.isEmpty(content))
				return null;
			if (!content.toUpperCase().contains(contents.toUpperCase())) {
				return null;
			}
			
			String shortDesc = mdItem.getChineseName();
			return new ARESContentProposal(content, shortDesc);
		}
		return null;
	}
	
}
