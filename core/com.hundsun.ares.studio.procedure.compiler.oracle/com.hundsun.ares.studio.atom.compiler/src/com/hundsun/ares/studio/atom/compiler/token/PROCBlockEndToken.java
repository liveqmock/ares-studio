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
import com.hundsun.ares.studio.engin.constant.MarkConfig;
import com.hundsun.ares.studio.engin.token.ICodeToken;

/**
 * @author liaogc
 *
 */
public class PROCBlockEndToken implements ICodeToken{
	public static final String NL = ITokenConstant.NL;
	

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
		Object obj = context.get(IAtomEngineContextConstant.ResourceModel);
		boolean flagR = false;
		if (obj instanceof BizInterface && StringUtils.equalsIgnoreCase(((BizInterface) obj).getInterfaceFlag(), MarkConfig.MARK_IFLAG_R)) {
			flagR = true;
		}
		StringBuffer code= new StringBuffer();
		code.append("<<svr_end>>").append(NL);
		code.append(":iReturnCode := :@error_no;").append(NL);
		code.append("END;").append(NL);
		code.append("END-EXEC;").append(NL);
		
		if (!flagR) {
			code.append(NL).append("if (CheckDbLinkMethod(lpConn,SQLCODE) < 0) ").append(NL);//前面加空行，否则会被格式化工具去除该行
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
			code.append(NL).append(NL);
			code.append("if(iReturnCode != OK_SUCCESS)").append(NL);
			code.append("{").append(NL);
			code.append("goto svr_end;").append(NL);
			code.append("}").append(NL);
			code.append("if (SQLCODE != OK_SUCCESS)").append(NL);
			code.append("{").append(NL);
			code.append("@error_no = 119;").append(NL);
			code.append("iReturnCode = 119;").append(NL);
			code.append("hs_strcpy(@error_info,\"PROC语句块内部错误\");").append(NL);
			code.append("@error_id = SQLCODE;").append(NL);
			code.append("hs_strcpy(@error_sysinfo,sqlca.sqlerrm.sqlerrmc);").append(NL);
			code.append("EXEC SQL rollback;").append(NL);
			code.append("goto svr_end;").append(NL);
			code.append("}").append(NL);
		}
		return code.toString();
		
	}
	
}
