/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.token;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procedure.compiler.constant.IProcedureEngineContextConstant;

/**
 * 过程返回类型声明
 * <br>目前证券一部强制返回number类型，过程声明也统一为"as";
 * 证券二部有其他用法，需注意。例如二部可以返回其他类型，或者不返回，
 * @author qinyuan
 */
public class ProcedureReturnDefineToken implements ICodeToken {

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
		Procedure procedure = (Procedure)context.get(IProcedureEngineContextConstant.ResourceModel);
		// 过程返回类型声明
		StringBuffer result = new StringBuffer();
		
		//输入输出参数不存在，不需要括号
		int inputParamNum = procedure.getInputParameters().size();//输入参数个数
		int outputParamNum = procedure.getOutputParameters().size();//输出参数个数
		if(inputParamNum <= 0 && outputParamNum <= 0 && !procedure.isOutputCollection()){//结果集返回要申明游标
		}else {
			result.append(")" + NEWLINE);
		}
		result.append(NEWLINE);
		
		if(StringUtils.isBlank(procedure.getBizType()) ||
				StringUtils.equalsIgnoreCase(procedure.getBizType(), IProcedureEngineContextConstant.function)){
			result.append("return ");
			result.append(StringUtils.isBlank(procedure.getReturnType())?"number":procedure.getReturnType());
			result.append(NEWLINE);
		}

		//定义类型处理
		if(StringUtils.isBlank(procedure.getDefineType()) ||
				StringUtils.equalsIgnoreCase(procedure.getDefineType(), as)){
			result.append(as);
		}else {
			result.append(PIPELINED_IS);
		}
		result.append(NEWLINE);
		//自治事务
		if(procedure.isAutoTransaction()) {
			result.append(AUTO_TRANSCTION);
			result.append(NEWLINE);
		}
		
		return result.toString();
	}

	//定义类型，证券二部扩展需要使用
	private static final String as = "as";
	private static final String PIPELINED_IS = "PIPELINED IS";
	
	private static final String AUTO_TRANSCTION = "pragmaautonomous_transaction;  -- 定义事务";
}
