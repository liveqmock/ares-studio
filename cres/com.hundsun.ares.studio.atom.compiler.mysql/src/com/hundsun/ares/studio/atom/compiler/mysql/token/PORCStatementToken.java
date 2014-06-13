/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.token;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.InternalParam;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.AtomFunctionCompilerUtil;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.MarkConfig;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.ParamReplaceUtil;
import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;

/**
 * @author liaogc
 *
 */
public class PORCStatementToken implements ICodeToken{
	public static final String NL = ITokenConstant.NL;
	private String sql;//sql语句
	private String rsId;//结果集Id
	private String stateId;//lpSP Id
	private List<String> queryFieldList;
	private List<String> inFieldList;
	/**
	 * @param context
	 */
	public PORCStatementToken(String sql,String rsId,String stateId,List<String> queryFieldList,List<String> inFieldList) {
		this.sql = sql;
		this.rsId = rsId;
		this.stateId = stateId;
		this.queryFieldList = queryFieldList;
		this.inFieldList = inFieldList;
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
		Object obj = context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		StringBuffer code = new StringBuffer();
		if(sql.startsWith("select") || sql.startsWith("SELECT")){//处理PRO*C语句select into
			code.append("lpSP" + stateId + " = lpConn->createCallableStatement();\r\n");
			code.append("lpSP" + stateId + "->prepare(\"" + getSelectRunSql(sql) + "\");\r\n");
			for(int i = 0;i < inFieldList.size();i++){
				String dataType = getFieldDataType(inFieldList.get(i),context);
				if(TypeRule.typeRuleCharArray(dataType)){//字符串
					code.append("lpSP" + stateId + "->setString(" + (i + 1) + ", @" + inFieldList.get(i) + ");\r\n");//从1开始计数
				}else if(TypeRule.typeRuleChar(dataType)){//字符
					code.append("lpSP" + stateId + "->setChar(" + (i + 1) + ", @" + inFieldList.get(i) + ");\r\n");//从1开始计数
				}else if(TypeRule.typeRuleDouble(dataType)){//浮点数
					code.append("lpSP" + stateId + "->setDouble(" + (i + 1) + ", @" + inFieldList.get(i) + ");\r\n");//从1开始计数
				}else if(TypeRule.typeRuleInt(dataType)){//整数
					code.append("lpSP" + stateId + "->setInt(" + (i + 1) + ", @" + inFieldList.get(i) + ");\r\n");//从1开始计数
				}else{//其余一律字符串处理
					code.append("lpSP" + stateId + "->setString(" + (i + 1) + ", @" + inFieldList.get(i) + ");\r\n");//从1开始计数
				}
			}
			code.append("lpResultSet" + rsId + " = lpSP" + rsId + "->open();\r\n");
			code.append("if(lpResultSet" + rsId + " != NULL)\r\n");
			code.append("{\r\n");
			code.append("if (!lpResultSet" + rsId + "->IsEOF()){\r\n");
			for(int i = 0;i < queryFieldList.size();i++){
				String dataType = getFieldDataType(queryFieldList.get(i),context);
				if(TypeRule.typeRuleCharArray(dataType)){//字符串
					String len = TypeRule.getCharLength(dataType);
					code.append("hs_strncpy(@" + queryFieldList.get(i) + ",conversion((char *)lpResultSet" + rsId + "->GetStr(\"" + queryFieldList.get(i) + "\"))," + len + ");\r\n");
				}else if(TypeRule.typeRuleChar(dataType)){//字符
					code.append("@" + queryFieldList.get(i) + " = lpResultSet" + rsId + "->GetChar(\"" + queryFieldList.get(i) + "\");\r\n");
				}else if(TypeRule.typeRuleDouble(dataType)){//浮点数
					code.append("@" + queryFieldList.get(i) + " = lpResultSet" + rsId + "->GetDouble(\"" + queryFieldList.get(i) + "\");\r\n");
				}else if(TypeRule.typeRuleInt(dataType)){//整数
					code.append("@" + queryFieldList.get(i) + " = lpResultSet" + rsId + "->GetInt(\"" + queryFieldList.get(i) + "\");\r\n");
				}
			}
			code.append("}\r\n");
			code.append("}\r\n");
			code.append("if(lpResultSet" + rsId + " != NULL)\r\n");
		}else if(sql.startsWith("update") || sql.startsWith("UPDATE")){
			code.append("lpSP" + stateId + " = lpConn->createCallableStatement();\r\n");
			code.append("lpSP" + stateId + "->prepare(\"" + getSelectRunSql(sql) + "\");\r\n");
			for(int i = 0;i < inFieldList.size();i++){
				String dataType = getFieldDataType(inFieldList.get(i),context);
				if(TypeRule.typeRuleCharArray(dataType)){//字符串
					code.append("lpSP" + stateId + "->setString(" + (i + 1) + ", @" + inFieldList.get(i) + ");\r\n");//从1开始计数
				}else if(TypeRule.typeRuleChar(dataType)){//字符
					code.append("lpSP" + stateId + "->setChar(" + (i + 1) + ", @" + inFieldList.get(i) + ");\r\n");//从1开始计数
				}else if(TypeRule.typeRuleDouble(dataType)){//浮点数
					code.append("lpSP" + stateId + "->setDouble(" + (i + 1) + ", @" + inFieldList.get(i) + ");\r\n");//从1开始计数
				}else if(TypeRule.typeRuleInt(dataType)){//整数
					code.append("lpSP" + stateId + "->setInt(" + (i + 1) + ", @" + inFieldList.get(i) + ");\r\n");//从1开始计数
				}else{//其余一律字符串处理
					code.append("lpSP" + stateId + "->setString(" + (i + 1) + ", @" + inFieldList.get(i) + ");\r\n");//从1开始计数
				}
			}
			code.append("iReturnCode = lpSP" + stateId + "->exec();\r\n");
			code.append("if(!iReturnCode)\r\n");
		}
		return code.toString();
	}
	
	/**
	 * 进一步处理SQL，使之符合MySQL运行要求
	 * 处理信息如下：
	 * 1、queryField去除@
	 * 2、inField转换为?
	 * 注意：into转换为as，由业务开发人员手工完成，并去除与MySQL语法不符合的部分
	 * @param sql 原有SQL
	 * @return 处理后的SQL
	 */
	private String getSelectRunSql(String sql){
		for(int i = 0;i < queryFieldList.size();i++){
			sql = sql.replaceAll("\\s+as\\s+@" + queryFieldList.get(i), " as " + queryFieldList.get(i));//去除@
		}
		for(int i = 0;i < inFieldList.size();i++){
			sql = sql.replace("@" + inFieldList.get(i), "?");
		}
		Pattern p = Pattern.compile("@[\\w\\d_]+");
		Matcher m = p.matcher(sql);
		while (m.find()) {
			sql = sql.replace(m.group(), "?");//如果还存在标准字段，当作入参处理
			//比如如下语句，@password需要特殊处理：
			//select decode(password,' ',@password,password) as @password_b,fundpwd as @fundpwd,password_errors as @password_errors,lock_flag as @lock_flag from fundaccountauth where fund_account  =  @fund_account
			String field = m.group().substring(1);
			List<String> newList = new ArrayList<String>();
			newList.add(field);//这里漏下的入参，也要添加到入参列表中，否则不会set值
			newList.addAll(this.inFieldList);
			this.inFieldList = newList;//由于漏掉的标准字段，一般在最前面，故解析的入参列表不动，在前面加
		}
		return sql;
	}
	
	/**
	 * 根据字段名获取数据类型
	 * @param fieldName 字段名
	 * @param context 上下文 
	 * @return String 字段真实的数据类型
	 */
	private String getFieldDataType(String fieldName,Map<Object,Object> context){
		AtomFunction atomFunction = (AtomFunction) context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		IARESProject project = (IARESProject)context.get(IAtomEngineContextConstantMySQL.Aresproject);
		Parameter param = AtomFunctionCompilerUtil.getParameterINAtomFunctionParameterByName(atomFunction,fieldName);
		if(param != null){
			return AtomFunctionCompilerUtil.getRealDataType(param,project,context);
		}else{
			StandardField stdfield = MetadataServiceProvider.getStandardFieldByNameNoExp(project, fieldName);//getId为参数名，getName为中文名
			if(stdfield == null){
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = String.format("参数[%1$s]对应的标准字段不存在。", fieldName);
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
				return "";//标准字段不存在时，继续生成，但最后将所有错误信息都抛出。
			}
			String bizTypeName = stdfield.getDataType();//标准字段时，取标准字段对应业务类型
			int length = 0;
			BusinessDataType bizType = MetadataServiceProvider.getBusinessDataTypeByNameNoExp(project, bizTypeName);//这里不能使用param.getType()，否则会引起非标时，取不到业务类型的异常
			if(bizType == null){
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = String.format("参数[%1$s]对应的业务类型[%2$s]不存在。", fieldName,bizTypeName);
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
				return "";//业务类型不存在时，继续生成，但最后将所有错误信息都抛出。
			}
			try {
				length = Integer.parseInt(bizType.getLength()) + 1;//声明Char数组变量时，长度要加1
			} catch (Exception e) {
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = String.format("参数[%1$s]对应的业务类型:%2$s的长度为非法数字：%3$s。", fieldName,bizTypeName,bizType.getLength());
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
				return "";//业务类型长度为非法数字时，继续生成，但最后将所有错误信息都抛出。
			}
			StandardDataType stdType = MetadataServiceProvider.getStandardDataTypeByNameNoExp(project, bizType.getStdType());
			if(stdType == null){
				ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
				String message = String.format("参数[%1$s]对应的业务类型[%2$s]中的标准类型[%3$s]不存在。", fieldName,bizTypeName,bizType.getStdType());
				manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
				return "";//标准类型不存在时，继续生成，但最后将所有错误信息都抛出。
			}
			String dataType = StringUtils.defaultIfBlank(stdType.getValue(MetadataServiceProvider.C_TYPE), "");
			dataType = dataType.replace("$L", length + "");
			return dataType;
		}
		
	}
	
	
}
