/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.compiler.tokens;

import java.util.Map;

import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.logic.LogicService;
import com.hundsun.ares.studio.logic.compiler.constant.ILogicEngineContextConstant;

/**
 * 
 * @author qinyuan
 *
 */
public class LogicServiceCheckLicenseToken implements ICodeToken {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getContent()
	 */
	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getType()
	 */
	@Override
	public int getType() {
		return CODE_TEXT;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		LogicService logicSer = (LogicService)context.get(ILogicEngineContextConstant.ResourceModel);
		if(logicSer.isIsCheckAccess()) {
			return CHECK_LINENCE;
		}
		return BlackString;
	}

	private final static String CHECK_LINENCE = "//检查接入许可证\n"+
	"iReturnCode = CheckAccessLicense(lpContext);\n"+
    "if ( 0 != iReturnCode && iReturnCode != ERR_SYSWARNING )\n"+
    "{\n"+
     "  v_error_no=iReturnCode;\n"+
     "  if (v_error_no == ERR_EXT_LIC_NOTEXISTS)\n"+
     "      hs_snprintf(v_error_info,CNST_ERRORINFO_LEN, \"%s\", \"接入许可证不存在\");\n"+
     "  else\n"+
     "  if(v_error_no == ERR_EXT_IN_LIC_NO_FUNC)\n"+
     "      hs_snprintf(v_error_info,CNST_ERRORINFO_LEN, \"%s\", \"接入许可证不允许执行此功能\");\n"+
     "  else\n"+
     "      hs_snprintf(v_error_info,CNST_ERRORINFO_LEN, \"%s\", \"接入许可证异常\");\n"+
     "  //GetErrorInfo(lpContext, @error_no, @error_info);\n"+
     "  goto svr_end;\r\n"+
   " }\n";
}
