/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import com.hundsun.ares.studio.core.IARESElement;

/**
 * 重命名元素的基本操作。
 * @author sundl
 */
public class RenameResourceElementsOperation extends MoveResourceElementsOperation {

	public RenameResourceElementsOperation(IARESElement[] elementsToProcess, IARESElement[] containers, String[] newNames, boolean force) {
		super(elementsToProcess, containers, force);
		setRenamingList(newNames);
	}

}
