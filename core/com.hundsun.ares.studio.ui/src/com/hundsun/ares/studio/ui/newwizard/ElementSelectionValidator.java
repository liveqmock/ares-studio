/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.newwizard;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.ui.ARESResourceCategory;

/**
 * @author lvgao
 *
 */
public class ElementSelectionValidator extends BaseWizardPageValidator  implements IWizardPageValidator{
	public static final int CATEGORY = 8888888;
	public static final int UNKNOWN = Integer.MAX_VALUE;
	
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.wizards.pages.IWizardPageValidator#validate(java.lang.Object)
	 */
	@Override
	public IStatus validate(Map<Object, Object> context) {
		Object selection = context.get(CommonElementSelectionPageEX.CONTEXT_KEY_SELECTION);
		if (selection == null) {
			return getErrorStatus("选择不能为空");
		} else {
			
			String[][] selectingTypes = getSelctingElementTypes(selection);
			if (selectingTypes == null) {
				return Status.OK_STATUS;
			}
			
			int curType = getCurrentSelectionType(selection);
			for (String[] type : selectingTypes) {
				if (type[0].equals(String.valueOf(curType))) {
					return Status.OK_STATUS;
				}
 			}
			
			StringBuffer errorMsg = new StringBuffer();
			errorMsg.append("至少选择一个如下类型的节点： ");
			for (String[] type : selectingTypes) {
				errorMsg.append(type[1]);
				errorMsg.append("、");
			}
			errorMsg.deleteCharAt(errorMsg.length() - 1);
			
			return getErrorStatus(errorMsg.toString());
		}
	}
	
	
	
	
	protected int getCurrentSelectionType(Object selection) {
		if (selection instanceof IARESElement) {
			return ((IARESElement)selection).getElementType();
		} else if (selection instanceof ARESResourceCategory) {
			return CATEGORY;
		} else 
			return UNKNOWN;
	}
	
	/**
	 * 返回要用来选择的资源类型; 二位数组，第一列类型第二列名字。<br>
	 * 如果返回的是null，则认为任何选择均有效/合法
	 */
	protected String[][] getSelctingElementTypes(Object selection) {
		return null;
	}
	
	

}
