/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.token;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.engin.token.ICodeToken;

/**
 * @author liaogc
 *
 */
public class FuncResultObjectReturnToken implements ICodeToken{
	private String objectId;//对象号
	private String objectParam;//参数名称
	public FuncResultObjectReturnToken(String objectId,String objectParam){
		this.objectId = objectId;
		this.objectParam = objectParam;
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getContent()
	 */
	@Override
	public String getContent() {
		return StringUtils.EMPTY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getType()
	 */
	@Override
	public int getType() {
		return ICodeToken.CODE_TEXT;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		StringBuffer code = new StringBuffer();
		String lpoutName = "lpOut"+objectId;
		if(StringUtils.endsWith(objectParam, "ResultSet")){
			objectParam = StringUtils.removeEndIgnoreCase(objectParam, "ResultSet") ;
		}
		code.append("p_"+objectParam+" = "+lpoutName+"->GetPackBuf();\r\n");
		code.append("pi_"+objectParam+" = "+lpoutName+"->GetPackLen();\r\n");
		return code.toString();
	}

}
