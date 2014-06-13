/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.token;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.token.ICodeToken;

/**
 * @author liaogc
 *
 */
public class PROCResultSetReturnToken implements ICodeToken{
	
	public static final String NL = ITokenConstant.NL;
	
	
	private String cursorName;//游标名称
	private String []sqlFields;//打包字段
	
	public PROCResultSetReturnToken(String cursorName,String[]sqlFields){
		this.cursorName = cursorName;
		this.sqlFields = sqlFields;
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
		PROCResultSetReturnTokenHelper helper = new PROCResultSetReturnTokenHelper(context,cursorName,sqlFields);
		String packAddField = helper.getPackAddField();
		String packAddFieldValue =  helper.getPackAddFieldValue();
		String fetchCursor = helper.getFetchCursor();
		code.append("@result_num =  0;").append(NL);
		code.append(packAddField).append(NL);
		code.append("while ( SQLCODE == OK_SUCCESS )").append(NL);
		code.append("{").append(NL);
		code.append(fetchCursor).append(NL);
		code.append("if (CheckDbLinkMethod(lpConn,SQLCODE) < 0) ").append(NL);
		code.append("{").append(NL);
		code.append("if ((SQLCODE<= ERR_DB_NO_CONTINUE_FETCH) && (SQLCODE>= ERR_DB_FAILOVER_NETWORK_OPER_FAIL))").append(NL);
		code.append("{").append(NL);
		code.append("iReturnCode = SQLCODE;").append(NL);
		code.append(" @error_no = SQLCODE; ").append(NL);
		code.append("hs_strncpy(@error_info,sqlca.sqlerrm.sqlerrmc,sqlca.sqlerrm.sqlerrml);").append(NL);
		code.append(" @error_id = SQLCODE; ").append(NL);
		code.append("hs_strncpy(@error_sysinfo,sqlca.sqlerrm.sqlerrmc,sqlca.sqlerrm.sqlerrml);").append(NL);
		code.append("EXEC SQL rollback;").append(NL);
		code.append(NL);
		code.append("goto svr_end;").append(NL);
		code.append("}").append(NL);
		code.append("lpConn->setErrMessage(HSDB_CONNECTION_STATUS_DISCONN,SQLCODE,sqlca.sqlerrm.sqlerrmc); ").append(NL);
		code.append("}").append(NL);
		code.append("if ( SQLCODE == OK_SUCCESS)").append(NL);
		code.append("{").append(NL);
		code.append("@result_num = @result_num + 1;").append(NL);
		code.append(packAddFieldValue).append(NL);
		code.append("}").append(NL);
		code.append("else").append(NL);
		code.append("break;").append(NL);
		code.append("}").append(NL);
		code.append("EXEC SQL CLOSE ").append(cursorName);
		code.append(";");
		return code.toString();
	}
	
	
	
	
	
}
