/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.token;

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
	private String cursorName ;//游标名称
	
	public PROCGetRecordEndToken(IMacroToken token,String cursorName){
		this.macroToken = token;
		this.cursorName = cursorName;
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
		String cursorVars  =getVarString();
			code.append("EXEC SQL FETCH ").append(cursorName).append(" INTO ").append(cursorVars).append(";").append(NL);
			code.append("if ((SQLCODE != OK_SUCCESS) && (SQLCODE != 100) && (SQLCODE != 1403))").append(NL);
			code.append("{").append(NL);
			code.append("iReturnCode = SQLCODE;").append(NL);
			code.append("@error_no = SQLCODE;").append(NL);
			code.append(NL);
			code.append("hs_strcpy(@error_info, sqlca.sqlerrm.sqlerrmc);").append(NL);
			code.append("@error_id = 0;").append(NL);
			code.append("}").append(NL);
			code.append("}while (SQLCODE == OK_SUCCESS);").append(NL);
			code.append("}").append(NL);
			code.append("else if ((SQLCODE != 100) && (SQLCODE != 1403))").append(NL);
			code.append("{").append(NL);
			code.append("iReturnCode = SQLCODE;").append(NL);
			code.append("@error_no = SQLCODE;").append(NL);
			code.append("hs_strcpy(@error_info, sqlca.sqlerrm.sqlerrmc);").append(NL);
			code.append("@error_id = 0;").append(NL);
			code.append("}").append(NL);
			code.append("EXEC SQL CLOSE ").append(cursorName).append(";").append(NL);
			code.append("if ( (iReturnCode != 0) || (SQLCODE != OK_SUCCESS) )").append(NL);
			code.append("goto svr_end;").append(NL);
		return code.toString();
	}
	/**
	 * 获得宏后的变量
	 * @return
	 */
	private String getVarString(){
		return this.macroToken.getParameters()[0];
	}
	
}
