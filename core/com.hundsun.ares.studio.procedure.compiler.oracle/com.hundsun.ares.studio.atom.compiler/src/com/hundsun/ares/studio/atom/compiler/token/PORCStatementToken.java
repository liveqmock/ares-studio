/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.token;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.skeleton.util.MarkConfig;
import com.hundsun.ares.studio.atom.compiler.skeleton.util.ParamReplaceUtil;
import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;

/**
 * @author liaogc
 *
 */
public class PORCStatementToken implements ICodeToken{
	public static final String NL = ITokenConstant.NL;
	private String sql;//sql语句
	/**
	 * @param context
	 */
	public PORCStatementToken(String sql) {
		this.sql = sql;
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
		Object obj = context.get(IAtomEngineContextConstant.ResourceModel);
		boolean flagR = false;
		if (obj instanceof BizInterface) {
			if (StringUtils.equalsIgnoreCase(((BizInterface) obj).getInterfaceFlag(), "R")) {
				flagR = true;
			}
		}
		StringBuffer code = new StringBuffer();
		if (!flagR) {
			code.append("EXEC SQL").append(MarkConfig.MARK_BLANK);
			ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
			String[] params =  helper.getAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST).toArray(new String[0]);
			Set<String> inoutParamList = helper.getAttribute(IAtomEngineContextConstant.ATTR_IN_OUT_PARAM_LIST);
			//把sql中的变量(@varName)替换成Proc中的变量(:varName)
			code.append(ParamReplaceUtil.handleParams(":",sql, params, inoutParamList)).append(";").append(NL).append(NL);
			code.append("if (CheckDbLinkMethod(lpConn,SQLCODE) < 0)").append(NL);
			code.append("{").append(NL);
			code.append("if ((SQLCODE<= ERR_DB_NO_CONTINUE_FETCH) && (SQLCODE>= ERR_DB_FAILOVER_NETWORK_OPER_FAIL))").append(NL);
			code.append("{").append(NL);
			code.append("iReturnCode = SQLCODE;").append(NL);
			code.append("@error_no = SQLCODE;").append(NL);
			code.append("hs_strncpy(@error_info,sqlca.sqlerrm.sqlerrmc,sqlca.sqlerrm.sqlerrml);").append(NL);
			code.append("@error_id = SQLCODE;").append(NL);
			code.append("hs_strncpy(@error_sysinfo,sqlca.sqlerrm.sqlerrmc,sqlca.sqlerrm.sqlerrml);").append(NL);
			code.append("EXEC SQL rollback;").append(NL);
			code.append(NL);
			code.append("goto svr_end;").append(NL);
			code.append("}").append(NL);
			code.append(NL);
			code.append("lpConn->setErrMessage(HSDB_CONNECTION_STATUS_DISCONN,SQLCODE,sqlca.sqlerrm.sqlerrmc);").append(NL);
			code.append("}").append(NL);
		}
		code.append("if (SQLCODE == OK_SUCCESS || SQLCODE == ERR_SELECTNODATA || SQLCODE == ERR_GETPROCOUTPUTERROR)").append(NL);
		return code.toString();
	}
}
