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
import com.hundsun.ares.studio.atom.constants.IAtomResType;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.constant.MarkConfig;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;

/**
 * @author zhuyf
 *
 */
public class FunctionDatabaseConnectionBeginToken implements ICodeToken {

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
		 * IConnection * lpConn = NULL;
		 * if (lpParentConn)
		     lpConn = lpParentConn;
		   else
		     lpConn = lpContext->GetDBConnection([数据库连接名]);
		   if(lpConn)
           {
           	 ctx = lpConn->getSQLContext();//判断是否存在Pro*c宏，存在则生成
			 if (ctx)//判断是否存在Pro*c宏，存在则生成
             {//判断是否存在Pro*c宏，存在则生成
               if (!lpParentConn)
			   {
                 EXEC SQL CONTEXT USE :ctx;//判断是否存在Pro*c宏，存在则生成
               }
		 * */
		//lpConn声明(当使用的数据库操作宏列表中宏的数量大于0时，即需声明)
		StringBuffer sb = new StringBuffer();
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		Set<String> databaseMacroList = helper.getAttribute(IAtomEngineContextConstant.ATTR_DATABASE_MACRO);
		Set<String> procMacroList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROC_MACRO);
		Set<String> funcCallList = helper.getAttribute(IAtomEngineContextConstant.ATTR_FUNC_CALL);
		Set<String> nrsProcedureCallList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROCEDURE_CALL_NOTRSRETURN);
		Set<String> rsProcedureCallList = helper.getAttribute(IAtomEngineContextConstant.ATTR_PROCEDURE_CALL_RSRETURN);
		
		//数据库宏，proc宏，AF调用，AP调用都要使用数据库连接
		if ((databaseMacroList.size() > 0) || (procMacroList.size() > 0) || (funcCallList.size() > 0) || (nrsProcedureCallList.size() > 0) || (rsProcedureCallList.size() > 0)) {
			Object obj = context.get(IAtomEngineContextConstant.ResourceModel);
			boolean flagR = false;
			AtomFunction af = null;
			String database = "";
			if (obj != null) {
				af = (AtomFunction) obj;
				IARESProject project = (IARESProject)context.get(IAtomEngineContextConstant.Aresproject);
				database = AtomFunctionCompilerUtil.getAtomDatabase(project, af.getDatabase(), af.getName(), IAtomRefType.ATOM_FUNCTION ,af.getInterfaceFlag());
				if (StringUtils.equalsIgnoreCase(af.getInterfaceFlag(), MarkConfig.MARK_IFLAG_R)) {
					flagR = true;
				}
			}
			if(StringUtils.isNotBlank(database)){//如果数据库没有设置则不需要声明数据库连接
				if (!flagR) {
					sb.append("IConnection * lpConn = NULL;\r\n"
							+ "if (lpParentConn)\r\n"
							+ "  lpConn = lpParentConn;\r\n"
							+ "else\r\n"
							+ "  lpConn = lpContext->GetDBConnection("+database+");\r\n"
							+ "if(lpConn)\r\n"
							+ "{\r\n");
					if (procMacroList.size() > 0 || nrsProcedureCallList.size() > 0) {
						sb.append("	 ctx = lpConn->getSQLContext();\r\n"
								+ "  if (ctx)\r\n"
								+ "  {\r\n"
								+ "    if (!lpParentConn)\r\n"
								+ "   {\r\n"
								+ "      EXEC SQL CONTEXT USE :ctx;\r\n"
								+ "    }\r\n");
					}
					helper.addAttribute(IAtomEngineContextConstant.ATTR_DATABASE_CONN_VARIABLE_LIST, "lpConn");
				}
			}else if(!flagR && procMacroList.size() > 0 || (databaseMacroList.size() > 0)||(nrsProcedureCallList.size() > 0)||(rsProcedureCallList.size() > 0)){
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = "必须设置数据库";
				if (af != null) {
					message = af.getFullyQualifiedName()+"."+IAtomResType.ATOM_FUNCTION + message;
				}
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
			}
			
		}
		return sb.toString();
	}

}
