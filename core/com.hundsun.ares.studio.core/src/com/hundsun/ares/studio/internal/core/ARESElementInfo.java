/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.util.List;

import com.hundsun.ares.studio.core.IARESElement;

/**
 * ARESElement的Info类。
 * @author sundl
 */
public class ARESElementInfo {

	protected IARESElement[] children = ARESElement.NO_CHILDREN;
	
	protected ARESElementInfo() {}
	
	public void addChild(IARESElement child) {
		if(children.length == 0) {
			children = new IARESElement[] {child};
		}
		else {
			int length = children.length;
			for (int i = 0; i < length; i++) {
				if (children[i].equals(child))
					return; // already included
			}
			
			IARESElement[] newChildren = new IARESElement[length + 1];
			System.arraycopy(children, 0, newChildren, 0, length);
			newChildren[length] = child; 
			this.children = newChildren;
		}
	}
	
	public IARESElement[] getChildren() {
		return this.children;
	}
	
	public void removeChild(IARESElement child) {
		for (int i = 0, length = this.children.length; i < length; i++) {
			IARESElement element = this.children[i];
			if (element.equals(child)) {
				if (length == 1) {
					this.children = ARESElement.NO_CHILDREN;
				} else {
					IARESElement[] newChildren = new IARESElement[length-1];
					System.arraycopy(this.children, 0, newChildren , 0, i);
					if (i < length-1)
						System.arraycopy(this.children, i+1, newChildren, i, length-1-i);
					this.children = newChildren;
				}
				break;
			}
		}
	}
	
	public void setChildren(List<IARESElement> children) {
		this.children = children.toArray(new IARESElement[children.size()]);
	}
	
	public void setChildren(IARESElement[] children) {
		this.children = children;
	}
	
}
