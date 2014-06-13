package com.hundsun.ares.studio.ui.editor.extend;

import java.util.ArrayList;
import java.util.List;


/**
 * 扩展模型显示分组
 * @author sundl
 *
 */
public class ExtensibleModelEditingCategory {

	private ExtensibleModelEditingRoot root;
	private String name;
	private List<ExtensibleModelEditingEntry> entries = new ArrayList<ExtensibleModelEditingEntry>();
	
	public ExtensibleModelEditingCategory(String name, ExtensibleModelEditingRoot root) {
		this.name = name;
		this.root = root;
	}
	
	public ExtensibleModelEditingRoot getRoot() {
		return this.root;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<ExtensibleModelEditingEntry> getEntries() {
		return entries;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExtensibleModelEditingCategory other = (ExtensibleModelEditingCategory) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
