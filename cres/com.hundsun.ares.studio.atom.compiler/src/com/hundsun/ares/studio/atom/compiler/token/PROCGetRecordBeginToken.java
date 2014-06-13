/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.token;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;

/**
 * @author liaogc
 *
 */
public class PROCGetRecordBeginToken implements ICodeToken{
	public static final String NL = ITokenConstant.NL;
	private IMacroToken macroToken ;//当前处理的宏
	private Map<Object, Object> context;//当前处理宏的上下文
	private String cursorName ;//游标变量名称
	
	public PROCGetRecordBeginToken(IMacroToken macroToken,Map<Object, Object> context,String cursorName){
		this.macroToken = macroToken;
		this.context =context;
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
		boolean flagR = false;
		Object obj = context.get(IAtomEngineContextConstant.ResourceModel);
		if (obj instanceof BizInterface && StringUtils.equalsIgnoreCase(((BizInterface) obj).getInterfaceFlag(), "R")) {
			flagR = true;
		}
		if (!flagR) {
			String cursorVars = StringUtils.trim(getVarString());//获得游标参数
			code.append("EXEC SQL FETCH ").append(cursorName).append(" INTO ").append(cursorVars).append(";").append(NL);
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
			code.append("if (SQLCODE == OK_SUCCESS)").append(NL);
			code.append("{").append(NL);
			code.append("do").append(NL);
			code.append("{").append(NL);
		}
		return code.toString();
	}
	
	/**
	 * 获得宏后的变量
	 * @return
	 */
	private String getVarString(){
		return getMacroToken().getParameters()[0];
	}
	
	
	/**
	 * 获得宏
	 * @return IMacroToken
	 */
	private IMacroToken getMacroToken(){
		return this.macroToken;
	}
	
	
}


