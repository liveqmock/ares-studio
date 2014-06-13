/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.token.service;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.token.function.FunctionEndToken;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;

/**
 * @author zhuyf
 *
 */
public class ServiceEndToken implements ICodeToken {

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
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		/*
		 * 	goto svr_end;
			svr_end:
			
				if (iReturnCode == OK_SUCCESS || iReturnCode == ERR_SYSWARNING)
				{
					lpOutPacker->AddField("init_date");
					lpOutPacker->AddField("log_serial_no");
					lpOutPacker->AddInt(p_init_date); //init_date
					lpOutPacker->AddInt(p_log_serial_no); //log_serial_no
			
				}
				else
				{
					GetErrorInfo(lpContext, v_error_no, v_error_info);
					SystemErrorPacker(lpOutPacker,v_error_pathinfo,v_error_no,v_error_info);
					WriteSystemLog(lpContext,v_error_pathinfo,v_error_no,v_error_info,v_error_id,v_error_sysinfo);
				}
				[lpFuncInPacker释放]
				[lpOut释放]
				[lpSP释放]
				[pack变量释放]
				if (lpConn)//当使用的数据库操作宏列表中宏的数量大于0时，需释放
					lpConn->toFree();//当使用的数据库操作宏列表中宏的数量大于0时，需释放
				return iReturnCode;
			}
		 */
		Object obj = context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		if(obj != null && obj instanceof AtomService){
			ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
			Set<String> dbConns =helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_DATABASE_CONN_VARIABLE_LIST);
			if(dbConns.size()>0){
				return FunctionEndToken.genEndCodes(context,FREE_CONN_SERVICE);
			}else{
				return FunctionEndToken.genEndCodes(context,"");
			}
		}
		return StringUtils.EMPTY;
	}
	
	private final static String FREE_CONN_SERVICE = "if (lpConn)\r\n"
		+"lpConn->toFree();\r\n";
	
}
