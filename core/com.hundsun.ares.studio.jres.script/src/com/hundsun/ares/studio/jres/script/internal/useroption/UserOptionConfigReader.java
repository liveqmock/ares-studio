/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.internal.useroption;

import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hundsun.ares.studio.jres.script.internal.useroption.control.ControlManager;
import com.hundsun.ares.studio.jres.script.internal.useroption.control.IUserOptionControlProvider;

/**
 * @author lvgao
 *
 */
public class UserOptionConfigReader {

	private static Logger logger = Logger.getLogger(UserOptionConfigReader.class);
	////////////////////////////////////XML相关配置/////////////////////////////////////////////////////////
	public static final String ROOT = "useroption";
	public static final String ID = "id";
	public static final String TEXT = "text";
	public static final String VALUE = "value";
	public static final String MODULE_ROOT = "module_root";
	public static final String DEFAULT_VALUE = "default_value";
	public static final String TYPE = "type";
	public static final String GROUP = "group";
	public static final String ITEM = "item";
	public static final String MODULE = "module";

	public UserOptionRoot read(InputStream is)throws Exception{
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(is);
		} catch (DocumentException e) {
			logger.error(e);
			return null;
		}
		
		Element root =  document == null ? null : document.getRootElement();
		if(null == root || !StringUtils.equals(ROOT, root.getName())){
			throw new Exception(String.format("配置根节点错误，应为名称为[%s]的根节点。", ROOT));
		}
		
		UserOptionRoot option = new UserOptionRoot();
		
		for(Object item: root.elements()){
			Element itemElment = (Element)item;
			String name =  itemElment.getName();
			
			if(StringUtils.equals(name, MODULE)){
				readModuleControl(itemElment, option);
			}
			
			if(StringUtils.equals(name, ITEM)){
				readItemControl(itemElment, option);
			}
			
			if(StringUtils.equals(name, GROUP)){
				readGroupControl(itemElment, option);
			}
			
		}
		
		return option;
	}
	
	
	private void readGroupControl(Element itemElment,IControlContainer container){
		IControl contrl = createControl(IControl.TYPE_GROUP);
		if(null == contrl){
			return;
		}
		
		/**
		 * 这里的逻辑是有顺序的
		 * 因为有radio的关系，所以group必须先初始化下面的控件
		 * 然后再设置默认值等
		 * 
		 */
		for(Object item: itemElment.elements()){
			Element subElment = (Element)item;
			String name =  subElment.getName();
			
			if(StringUtils.equals(name, MODULE)){
				readModuleControl(subElment, (IControlContainer)contrl);
			}
			
			if(StringUtils.equals(name, ITEM)){
				readItemControl(subElment, (IControlContainer)contrl);
			}
			
			if(StringUtils.equals(name, GROUP)){
				readGroupControl(subElment, (IControlContainer)contrl);
			}
		}
		
		contrl.setType(IControl.TYPE_GROUP);
		contrl.setID(getAttributeValue(itemElment, ID, ""));
		contrl.setText(getAttributeValue(itemElment, TEXT, ""));
		contrl.setValue(getAttributeValue(itemElment, VALUE, ""));
		contrl.setDefaultValue(getAttributeValue(itemElment, DEFAULT_VALUE, ""));
		container.addChildren(contrl);
	}
	
	private void readModuleControl(Element itemElment,IControlContainer container){
		String itemType = getAttributeValue(itemElment, TYPE, "");
		//控件相关
		IControl contrl = createControl(IControl.TYPE_MODULE);
		if(null == contrl){
			return;
		}
		contrl.setType(IControl.TYPE_MODULE);
		contrl.setControlType(itemType);
		contrl.setID(getAttributeValue(itemElment, ID, ""));
		contrl.setText(getAttributeValue(itemElment, TEXT, ""));
		contrl.setValue(getAttributeValue(itemElment, VALUE, ""));
		contrl.setModuleRoot(getAttributeValue(itemElment, MODULE_ROOT, ""));
		contrl.setDefaultValue(getAttributeValue(itemElment, DEFAULT_VALUE, ""));
		container.addChildren(contrl);
	}
	
	private void readItemControl(Element itemElment,IControlContainer container){
		String type = getAttributeValue(itemElment, TYPE, "");
		//控件相关
		IControl contrl = createControl(type);
		if(null == contrl){
			return;
		}
		/***
		 * 这里的设置是有顺序的
		 * setValue必须在setDefaultValue前。
		 * 比如combo，setValue时初始化选项，设置默认值时要校验是否在选项中
		 */
		contrl.setType(type);
		contrl.setID(getAttributeValue(itemElment, ID, ""));
		contrl.setText(getAttributeValue(itemElment, TEXT, ""));
		contrl.setValue(getAttributeValue(itemElment, VALUE, ""));
		contrl.setDefaultValue(getAttributeValue(itemElment, DEFAULT_VALUE, ""));
		container.addChildren(contrl);
	}
	
	/**
	 * 获取属性的值
	 * @param itemElment
	 * @param key
	 * @param defalutValue
	 * @return
	 */
	public String getAttributeValue(Element itemElment,String key,String defalutValue){
		 Object attr =  itemElment.attribute(key);
		 if(null != attr){
			 return ((Attribute)attr).getValue();
		 }
		return defalutValue;
	}
	
	
	/**
	 * 创建相应的控件
	 * @param type
	 * @return
	 */
	public static IControl createControl(String type){
		if (StringUtils.equals(IControl.TYPE_TEXT, type)) {
			return new UserOptionControlText();
		}else if (StringUtils.equals(IControl.TYPE_COMBO, type)) {
			return new UserOptionControlCombo();
		}else if (StringUtils.equals(IControl.TYPE_CHECK, type)) {
			return new UserOptionControlCheck();
		}else if (StringUtils.equals(IControl.TYPE_RADIO, type)) {
			return new UserOptionControl();
		}else if (StringUtils.equals(IControl.TYPE_GROUP, type)) {
			return new UserOptionControlGroup();
		}else if (StringUtils.equals(IControl.TYPE_MODULE, type)) {
			return new UserOptionControlTree();
		} else {
			// 除了上述固定类型之外，其他可以认为都是通过扩展点扩展的
			IUserOptionControlProvider provider = ControlManager.getInstance().getTypeProvider(type);
			if (provider != null) {
				return provider.createControl();
			}
		}
		
		return null;
	}
	
}
