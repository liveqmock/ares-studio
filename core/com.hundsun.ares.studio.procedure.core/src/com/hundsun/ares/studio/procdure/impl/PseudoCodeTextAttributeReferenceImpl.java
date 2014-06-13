/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procdure.impl;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.model.impl.ConditionReference;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceWithNamespaceImpl;

/**
 * @author liaogc
 *
 */
public class PseudoCodeTextAttributeReferenceImpl extends TextAttributeReferenceWithNamespaceImpl implements ConditionReference{
	private EObject ower;
	private EAttribute pseudoCode;
	private String stdOldValue;
	private boolean canDo = false;
	/**
	 * @param type
	 * @param object
	 * @param attribute
	 */
	public PseudoCodeTextAttributeReferenceImpl(String type, EObject object,
			EAttribute attribute) {
		super(type, object, attribute);
		ower = object;
		pseudoCode = attribute;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceImpl#setValue(java.lang.String)
	 */
	@Override
	public void setValue(String value) {//这里的值是标准字段的新值
		if(StringUtils.isNotBlank(value)){
			String code = getValue();
			String newCode = replaceVariable(code,stdOldValue,"@"+value);
			ower.eSet(pseudoCode, newCode);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.impl.ConditionReference#canDo(java.util.Map)
	 */
	@Override
	public boolean canDo(Map<Object, Object> parameters) {
		if(parameters==null || parameters.size()==0 ) {
			return true;
		}
		if(parameters.get("newValue")!=null && parameters.get("oldValue")!=null){
			 stdOldValue = (String) parameters.get("oldValue");
			 String stdNewValue = (String) parameters.get("newValue");
			Pattern p = Pattern.compile("@" + stdOldValue+"[^\\@w\\d_]+");
			String code = getValue();
			Matcher m = p.matcher(code);
			canDo= m.find() && !StringUtils.equals(stdOldValue, stdNewValue);
			return canDo;
		}
		
		return false;
	}
	/**
	 * 替换标准字段
	 * @param code
	 * @param varName
	 * @param replaceName
	 * @return
	 */
	private  String replaceVariable(String code, String varName, String replaceName) {
		Pattern p = Pattern.compile("@" + varName+"[^\\@w\\d_]+");
		StringBuffer sbRet = new StringBuffer();
		Matcher m = p.matcher(code);
		int lastPos = 0;
		while (m.find()) {
				if (m.start() > lastPos) {
					sbRet.append(code.substring(lastPos, m.start()));
				}
				sbRet.append(m.group().replaceAll("@" + varName, replaceName));
				lastPos = m.end();
			
		}
		if (lastPos < code.length()) {
			sbRet.append(code.substring(lastPos));
		}
		
		return sbRet.toString();
	}


}
