/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.extendpoint;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;

import com.hundsun.ares.studio.ui.control.IEditable;
import com.hundsun.ares.studio.ui.control.TextAdaptor;


public class TextControlProvider extends PageExtendPointControlProvider {

	@Override
	public IEditable getControl() {
		// TODO Auto-generated method stub
		return new TextAdaptor(getShowName(),SWT.NONE,getContext(),getBindName()){
			@Override
			protected void adjustControl() {
				GridData data = new GridData(SWT.FILL,SWT.FILL,true,true,3,1);
				control.setLayoutData(data);
				super.adjustControl();
			}
		};
	}

}
