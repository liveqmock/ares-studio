/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.extend;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.ExtensibleModel;

/**
 * 扩展配置树根
 * @author gongyf
 *
 */
public class ExtensibleModelEditingRoot {
	private IARESElement resource;
	private EClass eClass;
	private ExtensibleModel model;
	
	// Groups描述通过扩展点注册的信息，group对应一个EditingSupport扩展点
	private List<ExtensibleModelEditingGroup> groups;
	// category则描述最终显示的时候的分组信息
	private List<ExtensibleModelEditingCategory> categories;
	
	
	public ExtensibleModelEditingRoot(IARESElement resource, ExtensibleModel model) {
		super();
		this.resource = resource;
		this.eClass = model.eClass();
		this.model = model;
	}

	public IARESElement getARESElement() {
		return resource;
	}
	
	public EClass getEClass() {
		return eClass;
	}
	
	public ExtensibleModel getModel() {
		return model;
	}
	
	public List<ExtensibleModelEditingGroup> getGroups() {
		if (groups == null) {
			init();
		}
		return groups;
	}
	
	/**
	 * 经过计算后，显示用的分类
	 * @return
	 */
	public List<ExtensibleModelEditingCategory> getCategories() {
		if (categories == null) {
			init();
		}
		return categories;
	}
	
	/**
	 * 初始化，构建Groups和Category结构信息
	 */
	private void init() {
		groups = new ArrayList<ExtensibleModelEditingGroup>();
		categories = new ArrayList<ExtensibleModelEditingCategory>();
		for (IExtensibleModelEditingSupport es : ExtensibleModelUtils.getEndabledEditingSupports(getARESElement(), getEClass())) {
			if (!StringUtils.equals(getARESElement().getElementName(), "module.xml") && !StringUtils.equalsIgnoreCase(es.getKey(), Constants.USER_DATA2_KEY)) {
				continue;
			}
			ExtensibleModelEditingGroup group = new ExtensibleModelEditingGroup(this, es);
			groups.add(group);
			
			// 遍历所有的扩展属性描述，计算其展示的时候应该属于哪个分类
			for (ExtensibleModelEditingEntry entry : group.getEntries()) {
				// 如果扩展属性描述自己提供了分类信息，则使用这个分类；如果没有提供，则使用所属的EditingSupport的name作为分类
				String category = entry.getDescriptor().getCategory();
				if (StringUtils.isEmpty(category)) {
					category = group.getEditingSupport().getName();
				}
				
				ExtensibleModelEditingCategory cate = findCategory(category, categories);
				if (cate == null) {
					cate = new ExtensibleModelEditingCategory(category, this);
					categories.add(cate);
				}
				entry.setCategory(cate);
				cate.getEntries().add(entry);
			}
		}
	}
	
	private ExtensibleModelEditingCategory findCategory(String category, List<ExtensibleModelEditingCategory> catetories) {
		for (ExtensibleModelEditingCategory cate : catetories) {
			if (StringUtils.equals(category, cate.getName()))
				return cate;
		}
		return null;
	}

}
