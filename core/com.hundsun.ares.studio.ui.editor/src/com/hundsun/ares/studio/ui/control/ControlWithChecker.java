/**
 * <p>Copyright: Copyright   2010</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.control;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.IMessageManager;

import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;

/**
 * 为控件提供错误检查功能
 * @author maxh
 *
 * @param <T>
 */
public abstract class ControlWithChecker<T extends Control> extends ControlWithDataBind <T>{
	public ControlWithChecker(ImporveControlWithDitryStateContext context,int controlStyle) {
		super(context, controlStyle);
		this.message = context.getMessage();
		addChecker();
	}
	
	public ControlWithChecker(ImporveControlWithDitryStateContext context,int controlStyle,String beanFieldName) {
		super(context, controlStyle,beanFieldName);
		this.message = context.getMessage();
		addChecker();
	}
	
	public ControlWithChecker(ImporveControlWithDitryStateContext context,int controlStyle,Object model,String beanFieldName) {
		super(context, controlStyle,model,beanFieldName);
		this.message = context.getMessage();
		addChecker();
	}
	
	void addChecker(){
		addFocusChecker();
		innerCheck();
	}
	
	@Override
	protected void fireControlValueChange() {
		super.fireControlValueChange();
		if((checkModel & ControlWithChecker.MODIFY_CHECK) == ControlWithChecker.MODIFY_CHECK){
			innerCheck();
		}
	}
	
	/**
	 * 表单信息管理器 主要用来显示错误信息的
	 */
	IMessageManager message;
	
	public static final int MODIFY_CHECK = 0x01;
	public static final int FOCUS_GAINED_CHECK = 0x02;
	public static final int FOCUS_LOST_CHECK = 0x04;
	
	int checkModel = MODIFY_CHECK;
	
	public int getCheckModel() {
		return checkModel;
	}

	public void setCheckModel(int checkModel) {
		this.checkModel = checkModel;
	}

	/**
	 * 默认不进行错误检查 
	 * 如果需要错误检查 子类自己实现该方法
	 * @param errorBuffer
	 * @return
	 * @see AresControlError
	 */
	public IARESProblem check(){
		return null;
	}
	
	/**
	 * 错误检查
	 */
	public void innerCheck(){
		if(message != null){
			IARESProblem error = check();
			if(error != null){
				//对于同一个控件 后一次添加的会把前一次添加的覆盖
				//不用担心会重复添加
				int messageType = IMessageProvider.NONE;
				if (error.isError()) {
					messageType = IMessageProvider.ERROR;
				} else if (error.isWarning()) {
					messageType = IMessageProvider.WARNING;
				}
				message.addMessage(this, error.getMessage(), null, messageType, this.getControl());
			}else{
				message.removeMessage(this,this.getControl());
			}
		}
	}
	
	protected void addFocusChecker(){
		getControl().addFocusListener(new FocusAdapter(){
			@Override
			public void focusGained(FocusEvent e) {
				if((checkModel & ControlWithChecker.FOCUS_GAINED_CHECK) == ControlWithChecker.FOCUS_GAINED_CHECK){
					innerCheck();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if((checkModel & ControlWithChecker.FOCUS_LOST_CHECK) == ControlWithChecker.FOCUS_LOST_CHECK){
					innerCheck();
				}
			}
		});
	}
}
