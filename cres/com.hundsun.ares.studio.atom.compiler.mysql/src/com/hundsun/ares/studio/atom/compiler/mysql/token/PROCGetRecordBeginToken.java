/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.token;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.AtomFunctionCompilerUtil;
import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;

/**
 * @author liaogc
 *
 */
public class PROCGetRecordBeginToken implements ICodeToken{
	public static final String NL = ITokenConstant.NL;
	private IMacroToken macroToken ;//当前处理的宏
	private Map<Object, Object> context;//当前处理宏的上下文
	private String rsId ;//结果集Id
	List<String> sqlFields;//字段列表
	
	public PROCGetRecordBeginToken(IMacroToken macroToken,Map<Object, Object> context,String rsId,List<String> sqlFields){
		this.macroToken = macroToken;
		this.context =context;
		this.rsId = rsId;
		this.sqlFields = sqlFields;
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
		StringBuffer code = new StringBuffer(100);
		boolean flagR = false;
		Object obj = context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		if (obj instanceof BizInterface && StringUtils.equalsIgnoreCase(((BizInterface) obj).getInterfaceFlag(), "R")) {
			flagR = true;
		}
		if (!flagR) {
			
			code.append("while(!lpResultSet" + rsId + "->IsEOF())\r\n");
			code.append("{\r\n");
			Pattern p = Pattern.compile("@[\\w\\d_]+");
			//sql语句在第一参数中
			Matcher m = p.matcher(macroToken.getParameters()[0]);
			while (m.find()) {
				String field_name = m.group().substring(1);
				for(int i = 0;i < this.sqlFields.size();i++){
					if(StringUtils.equals(this.sqlFields.get(i), field_name)){
						String dataType = getFieldDataType(sqlFields.get(i),context);
						if(TypeRule.typeRuleCharArray(dataType)){//字符串
							String len = TypeRule.getCharLength(dataType);
							//由于用户可能采用简易写法，即select a.cache_mod_times,a.cache_type from clientcacheinfo a where (operator_no = @operator_no or (operator_no = '0' and cache_type in ('2','3'))) and (cache_type = @cache_type or @cache_type = 0)
							//此时，用名字会取不到数据，按序号来获取值
							code.append("hs_strncpy(@" + sqlFields.get(i) + ",conversion((char *)lpResultSet" + rsId + "->GetStrByIndex(" + i + "))," + len + ");\r\n");
						}else if(TypeRule.typeRuleChar(dataType)){//字符
							code.append("@" + sqlFields.get(i) + " = lpResultSet" + rsId + "->GetCharByIndex(" + i + ");\r\n");
						}else if(TypeRule.typeRuleDouble(dataType)){//浮点数
							code.append("@" + sqlFields.get(i) + " = lpResultSet" + rsId + "->GetDoubleByIndex(" + i + ");\r\n");
						}else if(TypeRule.typeRuleInt(dataType)){//整数
							code.append("@" + sqlFields.get(i) + " = lpResultSet" + rsId + "->GetIntByIndex(" + i + ");\r\n");
						}
					}
				}
			}
		}
		return code.toString();
	}
	
	/**
	 * 获得宏后的变量
	 * @return
	 */
	private String getVarString(){
		return getMacroToken().getParameters()[0];
	}
	
	
	/**
	 * 获得宏
	 * @return IMacroToken
	 */
	private IMacroToken getMacroToken(){
		return this.macroToken;
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


