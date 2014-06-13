/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.model.metadata.MetadataCategory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataCategoryScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataItemScriptWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IMetadataResScriptWrap;
import com.hundsun.ares.studio.jres.script.base.ResourceWrapBase;
import com.hundsun.ares.studio.jres.script.tool.HistoryCommentHelper;

/**
 * @author yanwj06282
 *
 */
public class MetadataResScriptWrapImpl extends ResourceWrapBase<MetadataResourceData> implements IMetadataResScriptWrap {

	private static Logger logger = Logger.getLogger(MetadataResScriptWrapImpl.class);
	
	boolean includeRequiredBundles;
	
	// 2014-3-28 sundl 添加参数includeRequiredProjects 表示是否包含引用工程和引用包
	public MetadataResScriptWrapImpl(MetadataResourceData metadata , IARESResource resource, boolean includeRequiredBundles) {
		super(resource);
		this.includeRequiredBundles = includeRequiredBundles;
	}

	@Override
	public IMetadataItemScriptWrap[] getItems() {
		List<IMetadataItemScriptWrap> items = new ArrayList<IMetadataItemScriptWrap>();

		for(Object item : getOriginalInfo().getItems()){
			items.add(new MetadataItemScriptWrapImpl((MetadataItem)item, resource));
		}
		
		if (includeRequiredBundles) {
			IARESBundle[] bundles = ARESElementUtil.getRefARESProjects(resource.getARESProject());
			for (IARESBundle bundle : bundles) {
				try {
					IARESResource refRes = bundle.findResource(resource.getName(), resource.getType());
					// getRefARESProjects()方法返回的包含了自身
					if (refRes != null && !refRes.equals(resource)) {
						MetadataResourceData info = refRes.getInfo(getInfoClass());
						for(Object item : info.getItems()){
							items.add(new MetadataItemScriptWrapImpl((MetadataItem)item, refRes));
						}
					}
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}
		
		return items.toArray(new IMetadataItemScriptWrap[0]);
	}

	public IMetadataCategoryScriptWrap[] getCategories (){
		List<IMetadataCategoryScriptWrap> cates = new ArrayList<IMetadataCategoryScriptWrap>();
		List<MetadataCategory> metaList =  getOriginalInfo().getRoot().getChildren();
		for(MetadataCategory cate : metaList){
			cates.add(new MetadataCategoryScriptWrapImpl(cate ,resource));
		}
		
		if (includeRequiredBundles) {
			IARESBundle[] bundles = ARESElementUtil.getRefARESProjects(resource.getARESProject());
			for (IARESBundle bundle : bundles) {
				try {
					IARESResource refRes = bundle.findResource(resource.getName(), resource.getType());
					// getRefARESProjects()方法返回的包含了自身
					if (refRes != null && !refRes.equals(resource)) {
						MetadataResourceData info = refRes.getInfo(getInfoClass());
						for(MetadataCategory cate : info.getRoot().getChildren()){
							cates.add(new MetadataCategoryScriptWrapImpl(cate ,resource));
						}
					}
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}
		return cates.toArray(new IMetadataCategoryScriptWrap[0]);
	}
	
	public IMetadataItemScriptWrap[] getNotCateItems (){
		List<IMetadataItemScriptWrap> items = new ArrayList<IMetadataItemScriptWrap>();
		for(MetadataItem item : MetadataUtil.getUncategorizedItems(getOriginalInfo())){
			items.add(new MetadataItemScriptWrapImpl(item, resource));
		}
		if (includeRequiredBundles) {
			IARESBundle[] bundles = ARESElementUtil.getRefARESProjects(resource.getARESProject());
			for (IARESBundle bundle : bundles) {
				try {
					IARESResource refRes = bundle.findResource(resource.getName(), resource.getType());
					// getRefARESProjects()方法返回的包含了自身
					if (refRes != null && !refRes.equals(resource)) {
						MetadataResourceData info = refRes.getInfo(getInfoClass());
						for(MetadataItem item : MetadataUtil.getUncategorizedItems(info)){
							items.add(new MetadataItemScriptWrapImpl(item, resource));
						}
					}
				} catch (Exception e) {
					logger.error(e);
				}
			}
		}
		return items.toArray(new IMetadataItemScriptWrap[0]);
	}
	
	@Override
	public String getModifyHistory(String commentMark) {
		return HistoryCommentHelper.getHistoryCommentForMetadata(getOriginalInfo(),commentMark);
	}

	@Override
	public IMetadataItemScriptWrap findItemByName(String name , boolean ignoreCase) {
		for(IMetadataItemScriptWrap item : getItems()){
			if (!ignoreCase) {
				if (StringUtils.equals(name, item.getName())) {
					return item;
				}
			}else {
				if (StringUtils.equalsIgnoreCase(name, item.getName())) {
					return item;
				}
			}
		}
		return null;
	}

	public IMetadataItemScriptWrap addItem(){
		MetadataItem item = MetadataFactory.eINSTANCE.createMetadataItem();
		getOriginalInfo().getItems().add(item);
		return new MetadataItemScriptWrapImpl(item, resource);
	}

	public boolean removeItemById(String id){
		for(Object obj : getOriginalInfo().getItems()){
			if (obj instanceof MetadataItem && StringUtils.equals(((MetadataItem) obj).getName(), id)) {
				getOriginalInfo().getItems().remove(obj);
				return true;
			}
		}
		return false;
	}
	
	public void removeItem(IMetadataItemScriptWrap item) {
		if (item instanceof MetadataItemScriptWrapImpl) {
			MetadataItemScriptWrapImpl meta = (MetadataItemScriptWrapImpl) item;
			getOriginalInfo().getItems().remove(meta);
		}

	}
	
	@Override
	public Class<MetadataResourceData> getInfoClass() {
		return MetadataResourceData.class;
	}
	
}
