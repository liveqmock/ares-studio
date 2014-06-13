/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.token;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.skeleton.util.ParamReplaceUtil;
import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.parser.PseudoCodeParser;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;

/**
 * @author liaogc
 *
 */
public class PROCResultSetStatementToken implements ICodeToken{
	public static final String NL = ITokenConstant.NL;
	private IMacroToken macroToken ;//当前处理的宏
	private String cursorName;//游标名称

	public PROCResultSetStatementToken(IMacroToken macroToken,String cursorName){
		this.macroToken = macroToken;
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
		StringBuffer code = new StringBuffer();
		Object obj = context.get(IAtomEngineContextConstant.ResourceModel);
		boolean flagR = false;
		if (obj instanceof BizInterface && StringUtils.equalsIgnoreCase(((BizInterface) obj).getInterfaceFlag(), "R")) {
			flagR = true;
		}
		if (!flagR) {
			BasicResourceInfo brInfo = (BasicResourceInfo) context.get(IAtomEngineContextConstant.ResourceModel);
			String sql = PseudoCodeParser.insertCommonForSql(getSqlStatement(context), brInfo.getObjectId());
			code.append("EXEC SQL DECLARE ").append(cursorName).append(" CURSOR FOR ").append(sql).append(";").append(NL);
			code.append("EXEC SQL OPEN ").append(cursorName).append(";").append(NL);
			code.append("if (CheckDbLinkMethod(lpConn,SQLCODE) < 0)").append(NL);
			code.append("{").append(NL);
			code.append("if ((SQLCODE<= ERR_DB_NO_CONTINUE_FETCH) && (SQLCODE>= ERR_DB_FAILOVER_NETWORK_OPER_FAIL))").append(NL);
			code.append("{").append(NL);
			code.append("iReturnCode = SQLCODE;").append(NL);
			code.append("@error_no = SQLCODE;").append(NL);
			code.append("hs_strncpy(v_error_info,sqlca.sqlerrm.sqlerrmc,sqlca.sqlerrm.sqlerrml);").append(NL);
			code.append("@error_id = SQLCODE;").append(NL);
			code.append("hs_strncpy(v_error_sysinfo,sqlca.sqlerrm.sqlerrmc,sqlca.sqlerrm.sqlerrml);").append(NL);
			code.append("EXEC SQL rollback;").append(NL).append(NL);
			code.append("goto svr_end;").append(NL);
			code.append("}").append(NL).append(NL);
			code.append("lpConn->setErrMessage(HSDB_CONNECTION_STATUS_DISCONN,SQLCODE,sqlca.sqlerrm.sqlerrmc);").append(NL);
			code.append("}").append(NL).append(NL);
		}
		code.append("if (SQLCODE == OK_SUCCESS)");
		return code.toString();
	}
	
	/**
	 * 获得输入的sql语句
	 * @return
	 */
	private String getSqlStatement(Map<Object, Object> context){
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		String[] params =  helper.getAttribute(IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST).toArray(new String[0]);
		Set<String> inoutParamList = helper.getAttribute(IAtomEngineContextConstant.ATTR_IN_OUT_PARAM_LIST);
		return ParamReplaceUtil.handleParams(":",macroToken.getParameters()[0], params, inoutParamList);
	}
	


}
