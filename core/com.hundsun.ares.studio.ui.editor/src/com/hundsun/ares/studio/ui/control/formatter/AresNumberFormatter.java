/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.control.formatter;

import org.eclipse.nebula.widgets.formattedtext.StringFormatter;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Text;

import com.hundsun.ares.studio.core.model.ICreateInstance;
import com.hundsun.ares.studio.core.util.StringUtil;

public class AresNumberFormatter extends StringFormatter implements ICreateInstance<AresNumberFormatter>, Cloneable{
	
	int min = 0;
	int max = Integer.MAX_VALUE;
	
	public AresNumberFormatter() {
	}
	
	public AresNumberFormatter(int min,int max) {
		this.min = min;
		this.max = max;
	}
	
	
	@Override
	public void verifyText(VerifyEvent e) {
		String newString = text.getText();
		newString = newString.substring(0,e.start)+ e.text+ newString.substring(e.end,newString.length());
		// 不要使用str.isEmpty()这样的1.6的函数
		if(StringUtil.isEmpty(newString)){
			return;
		}
		int i = 0;
		try {
			i = Integer.valueOf(newString);
		} catch (Exception ex) {
			e.doit = false;
			beep();
			return;
		}
		if(i > max){
			e.doit = false;
			beep();
			text.setText(String.valueOf(max));
			return;
		}
	}
	
	@Override
	public void setText(Text text) {
		super.setText(text);
		addFocusListener();
	}
	
	private void addFocusListener(){
		text.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent e) {
				int i = 0;
				try {
					i = Integer.valueOf(text.getText());
				} catch (Exception ex) {
					i = -1;
				}
				if(i < min){
					text.setText(String.valueOf(min));
					beep();
					return;
				}
				super.focusLost(e);
			}
		});
	}

	public AresNumberFormatter getNewInstance() {
		try {
			return (AresNumberFormatter) clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return new AresNumberFormatter();
	}
}
