/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

/**
 * 显示Ares模型树的TreeViewer，自动设置了ContentProvider,LabelProvider,Sorter
 * @author sundl
 */
public class AresModelViewer extends TreeViewer {

	/**
	 * 创建显示Ares模型树的
	 * @param parent
	 * @param style
	 */
	public AresModelViewer(Composite parent, int style) {
		super(parent, style);
		init();
	}
	
	private void init() {
		CommonElementContentProvider cp = new CommonElementContentProvider();
		setContentProvider(cp);
		setLabelProvider(new CommonElementLabelProvider(cp));
		setComparator(new ARESElementSorter());
	}
	
}
