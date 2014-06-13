/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.token;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;

/**
 * @author liaogc
 *
 */
public class PROCGetRecordEndToken implements ICodeToken{
	public static final String NL = ITokenConstant.NL;
	private IMacroToken macroToken;//PROCGetRecordEnd宏
	private String lastId ;//结果集Id
	
	public PROCGetRecordEndToken(IMacroToken token,String lastId){
		this.macroToken = token;
		this.lastId = lastId;
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
		StringBuffer code = new StringBuffer(100);
		code.append("lpResultSet" + lastId + "->Next();\r\n");
		code.append("}\r\n");
		return code.toString();
	}

	
}
