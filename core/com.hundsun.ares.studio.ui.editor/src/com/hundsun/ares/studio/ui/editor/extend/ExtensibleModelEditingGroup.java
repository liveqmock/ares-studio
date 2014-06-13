/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.extend;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * sundl: 2012-08-01调整以后，这个类仅相当于扩展注册信息的一个描述类； 具体界面显示分类由
 * {@link ExtensibleModelEditingCategory} 来完成
 * @author gongyf
 * @author sundl
 *
 */
public class ExtensibleModelEditingGroup {
	private ExtensibleModelEditingRoot root;
	private IExtensibleModelEditingSupport editingSupport;
	private List<ExtensibleModelEditingEntry> entries;
	
	/**
	 * @param root
	 * @param editingSupport
	 */
	public ExtensibleModelEditingGroup(ExtensibleModelEditingRoot root,
			IExtensibleModelEditingSupport editingSupport) {
		super();
		this.root = root;
		this.editingSupport = editingSupport;
	}

	public ExtensibleModelEditingRoot getRoot() {
		return root;
	}
	
	public IExtensibleModelEditingSupport getEditingSupport() {
		return editingSupport;
	}
	
	public List<ExtensibleModelEditingEntry> getEntries() {
		if (entries == null) {
			entries = new ArrayList<ExtensibleModelEditingEntry>();
			for (IExtensibleModelPropertyDescriptor desc : getEditingSupport().getPropertyDescriptors(getRoot().getARESElement(), getRoot().getEClass())) {
				entries.add(new ExtensibleModelEditingEntry(this, desc));
			}
		}
		return entries;
	}
	
}
