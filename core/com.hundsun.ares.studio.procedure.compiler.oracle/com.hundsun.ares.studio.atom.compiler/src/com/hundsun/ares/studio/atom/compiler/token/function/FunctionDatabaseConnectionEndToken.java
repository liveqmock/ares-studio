/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.token.function;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.skeleton.util.AtomFunctionCompilerUtil;
import com.hundsun.ares.studio.atom.constants.IAtomRefType;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.constant.MarkConfig;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;

/**
 * @author zhuyf
 *
 */
public class FunctionDatabaseConnectionEndToken implements ICodeToken {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getContent()
	 */
	@Override
	public String getContent() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getType()
	 */
	@Override
	public int getType() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		/*
		 *      }
				else
				{
					iReturnCode = ERR_SYS_BUSI_GET_CONTEXT_FAIL;
					@error_no = ERR_SYS_BUSI_GET_CONTEXT_FAIL;
					hs_strcpy(@error_info, "系统忙(上下文获取失败)");
					@error_id   = lpConn->getErrNo();
					sprintf(@error_sysinfo,"%s", lpConn->getErrInfo());
					goto svr_end;
				}
			}
			else
			{
			  iReturnCode = ERR_SYS_BUSI_GET_DBCONN_FAIL;
			  @error_no = ERR_SYS_BUSI_GET_DBCONN_FAIL;
			  sprintf(@error_info, "系统忙(数据库连接[%s]无法获取).", [数据库连接名]);
			  @error_id = ERR_SYS_BUSI_GET_DBCONN_FAIL;
			  goto svr_end;
			}
		 */
		StringBuffer sb = new StringBuffer();
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		Set<String> databaseMacroList = helper.getAttribute(IAtomEngineContextConstant.ATTR_DATABASE_MACRO);
		Set<String> procMacroList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROC_MACRO);
		Set<String> funcCallList = helper.getAttribute(IAtomEngineContextConstant.ATTR_FUNC_CALL);
		Set<String> rsProcedureCallList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROCEDURE_CALL_RSRETURN);
		Set<String> nrsProcedureCallList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROCEDURE_CALL_NOTRSRETURN);
		//数据库宏，proc宏，AF调用，AP调用都要使用数据库连接
		if ((databaseMacroList.size() > 0) || (procMacroList.size() > 0) || (funcCallList.size() > 0) || (rsProcedureCallList.size() > 0) || (nrsProcedureCallList.size() > 0)) {
			Object obj = context.get(IAtomEngineContextConstant.ResourceModel);
			String database = "";
			boolean flagR = false;
			if (obj != null) {
				AtomFunction af = (AtomFunction) obj;
				if (StringUtils.equalsIgnoreCase(af.getInterfaceFlag(), MarkConfig.MARK_IFLAG_R)) {
					flagR = true;
				}
				IARESProject project = (IARESProject)context.get(IAtomEngineContextConstant.Aresproject);
				database = AtomFunctionCompilerUtil.getAtomDatabase(project, af.getDatabase(), af.getName(), IAtomRefType.ATOM_FUNCTION ,af.getInterfaceFlag());
			}
			if(StringUtils.isNotBlank(database)){////如果数据没有,则不用处理数据库连接
				if (!flagR) {
					if (procMacroList.size() > 0 || nrsProcedureCallList.size() > 0) {
						sb.append("\r\n");
						sb.append("}\r\n"
								+ "else\r\n"
								+ "{\r\n"
								+ "	iReturnCode = ERR_SYS_BUSI_GET_CONTEXT_FAIL;\r\n"
								+ "	@error_no = ERR_SYS_BUSI_GET_CONTEXT_FAIL;\r\n"
								+ "	hs_strcpy(@error_info, \"系统忙(上下文获取失败)\");\r\n"
								+ "	@error_id   = lpConn->getErrNo();\r\n"
								+ "	sprintf(@error_sysinfo,\"%s\", lpConn->getErrInfo());\r\n"
								+ "	goto svr_end;\r\n"
								+ "	}\r\n");
					}
					sb.append("\r\n");
					sb.append("}\r\n"
							+ "else\r\n"
							+ "{\r\n"
							+ "  iReturnCode = ERR_SYS_BUSI_GET_DBCONN_FAIL;\r\n"
							+ "  @error_no = ERR_SYS_BUSI_GET_DBCONN_FAIL;\r\n"
							+ "  sprintf(@error_info, \"系统忙(数据库连接[%s]无法获取).\", "+database+");\r\n"
							+ "  @error_id = ERR_SYS_BUSI_GET_DBCONN_FAIL;\r\n"
							+ "  goto svr_end;\r\n"
							+ "}\r\n");
				}
			}
			
		}
		return sb.toString();
	}

}
