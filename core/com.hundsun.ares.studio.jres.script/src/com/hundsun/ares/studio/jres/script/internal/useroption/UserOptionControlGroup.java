/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.internal.useroption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

/**
 * @author lvgao
 *
 */
public class UserOptionControlGroup  extends UserOptionControl implements IControlContainer{

	List<IControl> cList = new ArrayList<IControl>();
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionControl#setDefaultValue(java.lang.String)
	 */
	@Override
	public void setDefaultValue(String defaultValue) {
		IControl firstRadio = null;
		for(IControl item:cList){
			if(StringUtils.equals(IControl.TYPE_RADIO, item.getType())){
				//设置第一个Radio
				if(null != firstRadio){
					firstRadio = item;
				}
				
				//默认值和Radio控件匹配
				if(StringUtils.equals(item.getValue(), defaultValue)){
					this.defaultValue = defaultValue;
				}
			}
		}
		
		//默认值和Radio控件不匹配，取第一个
		if(null == this.defaultValue){
			if(null != firstRadio) {
				if(null != firstRadio.getControl()){  
					((Button)firstRadio.getControl()).setSelection(true);
					this.defaultValue = firstRadio.getValue();
				}
			}else {//防止group下面没有radio控件时，默认值为空
				this.defaultValue =defaultValue;
			}
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionControl#setControl(java.lang.Object)
	 */
	@Override
	public void setControl(Object control) {
		super.setControl(control);
		
		List<String> vList = null;
		if(null != defaultValue && defaultValue.contains(",")){
			String[] values = defaultValue.split(",");
			vList = Arrays.asList(values);//
		}
		for(IControl item:cList){
			//针对chkeck控件
			if(StringUtils.equals(IControl.TYPE_CHECK, item.getType())){
				if((null != vList && vList.contains(item.getValue()))	//多个
						|| StringUtils.equals(item.getValue(), defaultValue)){//一个
					if(null != item.getControl()){  
						((Button)item.getControl()).setSelection(true);
					}
				}
			}
			
			if(StringUtils.equals(IControl.TYPE_RADIO, item.getType())){
				//默认值和Radio控件匹配
				if(StringUtils.equals(item.getValue(), defaultValue)){
					if(null != item.getControl()){  
						((Button)item.getControl()).setSelection(true);
					}
				}
			}
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionControl#setOptionValue(java.util.Map)
	 */
	@Override
	public void setOptionValue(Map<String, Object> option) {
		super.setOptionValue(option);
		StringBuffer valueBuffer = new StringBuffer();//保存为check控件的选择值
		boolean hasCheckCtr = false;
		for(IControl item:cList){
			if (StringUtils.equals(IControl.TYPE_MODULE, item.getType())) {
				option.put(getID(),((Text)item.getControl()).getText());
			}
			if(StringUtils.equals(IControl.TYPE_RADIO, item.getType())){
				if(null != item.getControl()){  //处理控件为空的情况
					//获取radio的值
					if(((Button)item.getControl()).getSelection()){
						option.put(getID(), item.getValue());
					}
				}
			}else{
				item.setOptionValue(option);
				
				if(StringUtils.equals(IControl.TYPE_CHECK, item.getType())){
					if(null != item.getControl()){  //处理控件为空的情况
						hasCheckCtr = true;
						//获取CHECK的值
						if(((Button)item.getControl()).getSelection()){
							valueBuffer.append(",");
							valueBuffer.append(item.getValue());
						}
					}
				}
			}
		}
		String value = valueBuffer.toString();
		if(value.length() > 0) {
			option.put(getID(), value.substring(1));//需要去除第一“,”
		}else if(hasCheckCtr){
			option.put(getID(), "");//存在check控件，但是一个都没有选中
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.internal.useroption.IControlContainer#addChildren(com.hundsun.ares.studio.jres.script.internal.useroption.IControl)
	 */
	@Override
	public void addChildren(IControl control) {
		cList.add(control);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.internal.useroption.IControlContainer#getChildren()
	 */
	@Override
	public IControl[] getChildren() {
		return cList.toArray(new IControl[0]);
	}

	

}
