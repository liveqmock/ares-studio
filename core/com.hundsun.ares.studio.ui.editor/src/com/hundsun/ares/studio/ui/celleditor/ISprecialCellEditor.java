/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.celleditor;

import org.eclipse.swt.graphics.Image;

/**
 * 一些特殊的cellEditor
 * 可能要展现图片
 * 可能展现的字符串和内存模型的字符串不一样
 * 
 * 如果要实现这些需求，那就要用该接口中的方法做转换
 * @author maxh
 */
public interface ISprecialCellEditor {

	public abstract Image getImage(Object value);

	public abstract String getText(Object value);
	
	public abstract Object getRealGetValue(Object value);

	public abstract Object getRealSetValue(Object value);
}
