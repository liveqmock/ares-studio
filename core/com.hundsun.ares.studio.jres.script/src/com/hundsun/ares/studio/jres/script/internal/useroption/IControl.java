/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.internal.useroption;

/**
 * @author lvgao
 *
 */
public interface IControl  extends IUserOption{


	
	//////////////////////////////////控件类型/////////////////////////////////////////////////
	public static final String TYPE_GROUP = "GROUP";
	public static final String TYPE_MODULE = "MODULE";
	public static final String TYPE_TEXT = "TEXT";
	public static final String TYPE_COMBO = "COMBO";
	public static final String TYPE_CHECK = "CHECK";
	public static final String TYPE_RADIO = "RADIO";
	
	/** 控件类型之业务属性 */
	public static final String TYPE_BIZ_PROPERTY = "BIZ_PROPERTY";
	
	public String getID();
	
	public String getText();
	
	public String getValue();
	
	public String getModuleRoot();
	
	public String getDefaultValue();
	
	public String getControlType();
	
	public String getType();
	
	public void setID(String value);
	
	public void setText(String value);
	
	public void setValue(String value);
	
	public void setDefaultValue(String value);
	
	public void setControlType(String itemType);
	
	public void setModuleRoot(String moduleRoot);
	
	public void setType(String value);
	
	public Object getControl();
	
	public void setControl(Object control);
}
