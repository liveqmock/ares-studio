package com.hundsun.ares.studio.logic.compiler.tokens;

import java.util.Map;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.biz.util.BizInterfaceParameterUtil;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.logic.compiler.constant.ILogicEngineContextConstant;
import com.hundsun.ares.studio.logic.compiler.util.LogicResourceCompilerUtil;

/**
 * @author qinyuan
 *
 */
public class LogicSubCallWithMFlagToken implements ICodeToken {

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
		
		
		IARESProject project = (IARESProject)context.get(ILogicEngineContextConstant.Aresproject);
		Object subCallWithM = context.get(ILogicEngineContextConstant.ATTR_SUB_CALL_WITH_M);
		AtomFunction ls = (AtomFunction)context.get(ILogicEngineContextConstant.ResourceModel);
		if(null != subCallWithM){
			StringBuffer ret = new StringBuffer();
			int pathSize = LogicResourceCompilerUtil.getErrorPathInfoLen(project);
			ret.append("char v_error_pathinfo1["+String.valueOf(pathSize+1)+"] = {0};");
			ret.append(NEWLINE);
			
			//逻辑层error_pathinfo如果是IO，同时逻辑中有<M>  
			//hs_strncpy(v_error_pathinfo1,p_error_pathinfo,500); 
			//	应该修改为 
			//	hs_strncpy(v_error_pathinfo1,v_error_pathinfo,500); 
			if(BizInterfaceParameterUtil.isOutputParameterWithIO(ls, "error_pathinfo",project)){
				ret.append("hs_strncpy(v_error_pathinfo1,v_error_pathinfo,"+String.valueOf(pathSize)+");\n");
			}else {
				ret.append("hs_strncpy(v_error_pathinfo1,@error_pathinfo,"+String.valueOf(pathSize)+");\n");
			}
			return ret.toString();
		}

 		return BlackString;
	}
}
