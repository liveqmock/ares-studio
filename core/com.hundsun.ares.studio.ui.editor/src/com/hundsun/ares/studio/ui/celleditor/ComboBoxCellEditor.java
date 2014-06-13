/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.celleditor;

import java.util.Arrays;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

/**
 * 如果需要在表格和树控件中使用combo的celleditor
 * 最好用我们的而不要用jface的 这样就不用重写getValue和setValue了
 * 
 * @author maxh
 *
 */
public class ComboBoxCellEditor extends org.eclipse.jface.viewers.ComboBoxCellEditor implements ISprecialCellEditor{

	public ComboBoxCellEditor(Composite parent, String[] items, int style) {
		super(parent,items,style);
	}
	
	public Image getImage(Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getRealGetValue(Object value) {
		if(value instanceof String){
			return Arrays.asList(getItems()).indexOf(value);
		}
		return value;
	}

	public Object getRealSetValue(Object value) {
		if(value instanceof Integer){
			if((Integer)value != -1){
				Integer i = (Integer)value;
				return getItems()[i];
			}
		}
		return value;
	}

	public String getText(Object value) {
		// TODO Auto-generated method stub
		return value.toString();
	}


}
