/**
 * 源程序名称：ExtensibleModelEditingEntry.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.extend;


/**
 * @author gongyf
 *
 */
public class ExtensibleModelEditingEntry {

	private ExtensibleModelEditingGroup group;
	private ExtensibleModelEditingCategory category;
	private IExtensibleModelPropertyDescriptor descriptor;
	
	/**
	 * @param group
	 * @param descriptor
	 */
	public ExtensibleModelEditingEntry(ExtensibleModelEditingGroup group,
			IExtensibleModelPropertyDescriptor descriptor) {
		super();
		this.group = group;
		this.descriptor = descriptor;
	}
	
	public ExtensibleModelEditingCategory getCategory() {
		return category;
	}

	public void setCategory(ExtensibleModelEditingCategory category) {
		this.category = category;
	}

	public ExtensibleModelEditingGroup getGroup() {
		return group;
	}
	/**
	 * @return the descriptor
	 */
	public IExtensibleModelPropertyDescriptor getDescriptor() {
		return descriptor;
	}


}
