/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.token;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.engin.token.ICodeToken;

/**
 * @author liaogc
 * 结果集对象返回
 */
public class ResultSetObjectReturnToken implements ICodeToken {
	private String outOParamName;
	public ResultSetObjectReturnToken(String outOParamName){
		this.outOParamName = outOParamName;
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
		if(StringUtils.endsWith(outOParamName,"ResultSet" )){
			outOParamName = StringUtils.substring(outOParamName,0, outOParamName.lastIndexOf("ResultSet"));
		}
		code.append("p_"+outOParamName +" = v_"+outOParamName+"ResultSet->GetPackBuf();\r\n");
		code.append("pi_"+outOParamName +" = v_"+outOParamName+"ResultSet->GetPackLen();\r\n");
		return code.toString();
	}
}
