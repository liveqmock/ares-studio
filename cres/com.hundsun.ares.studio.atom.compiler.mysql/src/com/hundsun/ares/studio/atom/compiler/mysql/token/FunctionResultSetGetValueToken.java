/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.token;

import java.util.Map;

import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;

/**
 * @author zhuyf
 *
 */
public class FunctionResultSetGetValueToken implements ICodeToken {
	
	String params;
	
	String packName;
	
	public FunctionResultSetGetValueToken(String params,String packName){
		this.params = params;
		this.packName = packName;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getContent()
	 */
	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getType()
	 */
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		StringBuffer sb = new StringBuffer();
		String[] pArray = params.split(",");
		//参数
		for(String keyvalue : pArray){
			keyvalue = keyvalue.trim();
			String[] kArray = keyvalue.split("="); 
			String key = kArray[0].trim();
			String value = kArray[1].trim();
			sb.append(getValueCodeStr(PackAddFieldHeadToken.getDataType(context, value.replace("@", ""),MetadataServiceProvider.C_TYPE),key,value,packName));
		}
		return sb.toString();
	}
	
	/**
	 * 根据相应的类型参数，获取对应的方法
	 * 
	 * @param type
	 * @return
	 */
	private String getValueCodeStr (String type,String param_name,String param_value,String packName){
		if (TypeRule.typeRuleCharArray(type)) {
			return String.format(PARAM_INIT_STR,param_name,param_value,TypeRule.getCharLength(type),packName);
		}else if (TypeRule.typeRuleChar(type)) {
			return String.format(PARAM_INIT_CHAR, param_name,param_value,packName);
		}
		else if (TypeRule.typeRuleInt(type)) {
			return String.format(PARAM_INIT, "Int", param_name,param_value,packName);
		}
		else if (TypeRule.typeRuleDouble(type)) {
			return String.format(PARAM_INIT, "Double", param_name,param_value,packName);
		}
		return "";
	}
	
	private final static String PARAM_INIT = "%3$s = %4$s->Get%1$s(\"%2$s\");" + ITokenConstant.NL;
	
	private final static String PARAM_INIT_CHAR = "%2$s = conversion(%3$s->GetChar(\"%1$s\"));" + ITokenConstant.NL;
	
	private final static String PARAM_INIT_STR = "hs_strncpy(%2$s,conversion((char *)%4$s->GetStr(\"%1$s\")),%3$s);" + ITokenConstant.NL;

}
