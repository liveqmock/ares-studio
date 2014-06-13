/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.AtomService;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.exception.MacroParameterErrorException;
import com.hundsun.ares.studio.atom.compiler.mysql.exception.TableNotFoundException;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.TokenDomain;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.TableResourceUtil;
import com.hundsun.ares.studio.atom.compiler.mysql.token.PROCResultSetStatementToken;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IDomainHandler;
import com.hundsun.ares.studio.engin.token.ITokenDomain;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.jres.model.database.TableColumn;

/**
 * @author zhuyf
 *
 */
public class PROCResultSetStatementMacroHandler implements IMacroTokenHandler {
	
	private List<String> queryFieldList;//用于保存Select语句的查询字段
	
	private List<String> inFieldList;//用于保存动态语句的条件字段
	

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return MacroConstant.PROC_RESULTSET_STATEMENT_MACRONAME;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		/*
		 * 输入描述：
			[PRO*C结果集语句][ sql查询语句]
			处理流程：
			1.获取select语句的查询字段
				主要是*的处理，不管表别名是否存在，都可正常获取字段，且考虑select嵌套的情况，以最里面层select字段为准
				去除注释语句的*的影响
				其次是字段有可能带有函数，如nvl
				字段可能用as定义别名，那么在结果集返回时，使用as别名作为AddField的字段名
			2.查询字段存入中，做后续的宏处理，如[POR*C结果集返回]
			3.生成如下语句：
			EXEC SQL DECLARE cursor[对象号]+[编号] CURSOR FOR [select语句];
			EXEC SQL OPEN cursor[对象号]+[编号];

			if (CheckDbLinkMethod(lpConn,SQLCODE) < 0)
			{
				if ((SQLCODE<= ERR_DB_NO_CONTINUE_FETCH) && (SQLCODE>= ERR_DB_FAILOVER_NETWORK_OPER_FAIL))
				{
					iReturnCode = SQLCODE;
					v_error_no = SQLCODE;
					hs_strncpy(v_error_info,sqlca.sqlerrm.sqlerrmc,sqlca.sqlerrm.sqlerrml);
					v_error_id = SQLCODE;
					hs_strncpy(v_error_sysinfo,sqlca.sqlerrm.sqlerrmc,sqlca.sqlerrm.sqlerrml);
					EXEC SQL rollback;

					goto svr_end;
				}

				lpConn->setErrMessage(HSDB_CONNECTION_STATUS_DISCONN,SQLCODE,sqlca.sqlerrm.sqlerrmc);
			}

			if (SQLCODE == OK_SUCCESS)
			其中[select语句]以5个字段为一行，最外层select中追加对象号的注释信息
			4.加入游标到声明列表中
			5.SQL语句支持历史、归档、冗余、冗余上日表
			[PRO*C结果集语句][select * from his_clientjour][clientjour]
			[PRO*C结果集语句][select * from fil_clientjour][clientjour]
			[PRO*C结果集语句][select * from r_clientjour][clientjour]
			[PRO*C结果集语句][select * from rl_clientjour][clientjour]
		 */
		queryFieldList = new ArrayList<String>();
		inFieldList = new ArrayList<String>();
		addMacroNameToMacroList(token,context);//把宏名加到入数据库列表以及proc列表中
		List<ICodeToken> codeList = new ArrayList<ICodeToken>();
		if(token.getParameters().length==3){//如果用户列出了字段
			String[] tempSqlFields = StringUtils.split(token.getParameters()[2], ",");
			for(int i = 0;i<tempSqlFields.length;i++){
				queryFieldList.add(StringUtils.trim(tempSqlFields[i]));
			}
		}else{//没有列出字段则解析sql语句,从sql语句中获得字段
			getAllFieldsFromSqlStr(getFieldString(token,context),context);
			splitFieldList(token,context);
		}	
		
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		Set<String> rsList = helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_RESULTSET_LIST);
		Set<String> stateList = helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_STATEMENT_LIST);
		AtomFunction func = (AtomFunction)context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		int rsTotalSizeAdd1 = rsList.size() + 1;
		int stateTotalSizeAdd1 = stateList.size() + 1;
		String objectId = func.getObjectId();
		if(StringUtils.isBlank(objectId) && (func instanceof AtomService)){
			ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
			String message = "获取功能号失败,无法正常生成lpResultSet";
			manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
		}else if(StringUtils.isBlank(objectId) && !(func instanceof AtomService)){
			objectId = func.getName();
		}
		String rsID = objectId + rsTotalSizeAdd1;
		addDomain(token,context,queryFieldList,rsID);//添加域
		String stateID = objectId + stateTotalSizeAdd1;
		helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_STATEMENT_LIST,stateID);//所需释放的动态语句列表
		helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_RESULTSET_LIST,rsID);//所需释放的结果集列表
		helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_GETLAST_RESULTSET,rsID);//
		codeList.add(new PROCResultSetStatementToken(token,rsID,stateID,queryFieldList,inFieldList));//添加codeToken
		return codeList.iterator();
	}
	
	/**
	 * 添加域
	 */
	private void addDomain(IMacroToken token,
			Map<Object, Object> context,List<String> sqlFields,String rdId){
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		String key = getKey();
		Object[] args = new Object[2];
		args[0] = rdId;//添加结果集Id，后续结果集返回或PRO*C记录获取开始与PRO*C记录获取结束宏生成代码时需要使用。
		args[1] = sqlFields;//游标字段名称
		ITokenDomain tokenDomain = new TokenDomain(key,args);
		handler.addDomain(tokenDomain);
	}
	
	/**
	 * 把宏名加入到宏列表中
	 */
	private void addMacroNameToMacroList(IMacroToken token, Map<Object, Object> context){
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_DATABASE_MACRO,token.getKeyword());//添加到数据库列表中
	    helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_MACRO,token.getKeyword());//添加到proc宏列表中
	}
	
	/**
	 * 处理[PRO*C结果集语句]SQL语句
	 * 
	 * @return 处理后的sql语句
	 * @throws TableNotFoundException 
	 */
	
	private String getFieldString(IMacroToken token, Map<Object, Object> context) throws TableNotFoundException,MacroParameterErrorException{
		String sqlStr = getSqlStrReplaceComm(token);
		String sTableName = "";
		if (token.getParameters().length >= 2) {
			sTableName = token.getParameters()[1];
		}
		Pattern p = Pattern.compile("\\s*select\\s+\\*\\s+from\\s*\\(");
		Matcher m = p.matcher(sqlStr);
		while (m.find()) {
			if (m.start() == 0) {
				sqlStr = sqlStr.substring(m.end());
				m = p.matcher(sqlStr);
			}
		}
		String fieldStr = sqlStr.substring(sqlStr.indexOf("select ") + 7);
		p = Pattern.compile("(\\s+from\\s+)");
		m = p.matcher(fieldStr);
		if (m.find()) {
			fieldStr = fieldStr.substring(0, m.start()).trim();
		}
		String sFieldStr = fieldStr + ",";
		String result = "";
		while (sFieldStr.indexOf(",") >= 0) {
			// 表的别名变量
			String sOtherTableName = "";
			// 替换*的字段变量
			String sOtherFieldStr = "";

			String sTempField = sFieldStr.substring(0, sFieldStr.indexOf(",")).trim();
			
			if(sTempField.indexOf ("*/") != -1 && sTempField.indexOf ("/*") != -1){
				sTempField = sTempField.substring (sTempField.indexOf ("*/")+2);
			}
			
			if(sTempField.trim().startsWith("distinct") || sTempField.trim().startsWith("DISTINCT") || sTempField.trim().startsWith("Distinct")){
				sTempField = sTempField.trim().substring(8);
			}

			// 如果是带函数的字段，则先去除函数
			if (sTempField.indexOf("(") >= 0) {
				if (sTempField.toLowerCase().indexOf(" as ") < 0) {
					sFieldStr = sFieldStr.substring(sFieldStr.indexOf(",") + 1).trim();
					do {
						if (sFieldStr.indexOf(",") >= 0) {
							sTempField = sFieldStr.substring(0, sFieldStr.indexOf(",")).trim();
							if (sTempField.toLowerCase().indexOf(" as ") >= 0) {
								break;
							} else {
								sFieldStr = sFieldStr.substring(sFieldStr.indexOf(",") + 1).trim();
							}
						} else {
							break;
						}
					} while (true);
				}
			}

			// 有可能是去除函数前半部分以后余下的字段，此时应有AS单词
			if (sTempField.toLowerCase().indexOf(" as ") >= 0) {
				sTempField = sTempField.substring(sTempField.toLowerCase().indexOf(" as ") + 4);
			}

			// 如果是带别名的字段，则先去除别名
			if (sTempField.indexOf(".") >= 0) {
				// 取别名
				sOtherTableName = sTempField.substring(0, sTempField.indexOf("."));
				sTempField = sTempField.substring(sTempField.toLowerCase().indexOf(".") + 1);
			}

			// 如果取得的字段中带*号，表明需要从表中取字段
			if (sTempField.trim().endsWith("*")&& sTempField.indexOf("/*") < 0) {
				if(token.getParameters().length<2 || StringUtils.isBlank(sTableName) ){
					throw new RuntimeException("请输入宏的第二个参数[表名]!");
				}
				if (!sTableName.equals("")) {
					if (!sTableName.substring(1).equals(",")) {
						sTableName = sTableName + ",";
					}
					if (sTableName.indexOf(",") >= 0) {
						String sTempTableName = sTableName.substring(0, sTableName.indexOf(","));
						if (!sTempTableName.equals("")) {
							// 将表字段追加到游标字段列表中
							IARESProject project = (IARESProject) context.get(IAtomEngineContextConstantMySQL.Aresproject);
							List<TableColumn> fields = TableResourceUtil.getFieldsWithoutFlag("H",sTempTableName,project);
							for (int index = 0; index < fields.size(); index++) {
								TableColumn field = fields.get(index);
								if (sOtherTableName.equals("")) {
									if (index == fields.size() - 1) {
										sOtherFieldStr = sOtherFieldStr + field.getFieldName();
									} else {
										sOtherFieldStr = sOtherFieldStr + field.getFieldName() + ",";
									}
								} else {
									if (index == fields.size() - 1) {
										sOtherFieldStr = sOtherFieldStr + sOtherTableName + "." + field.getFieldName();
									} else {
										sOtherFieldStr = sOtherFieldStr + sOtherTableName + "." + field.getFieldName() + ",";
									}
								}
							}
							
							//视图情况另外再处理
						}
						if (result.equals("")) {
							result += sOtherFieldStr;
						} else {
							result += "," + sOtherFieldStr;
						}
						sTableName = sTableName.substring(sTableName.length() - sTableName.indexOf(","));
					}
				}
			} else {
				if (sOtherTableName.equals("")) {
					if (result.equals("")) {
						result += sTempField;
					} else {
						result += "," + sTempField;
					}
				} else {
					if (result.equals("")) {
						result += sOtherTableName + "." + sTempField;
					} else {
						result += "," + sOtherTableName + "." + sTempField;
					}
				}
			}
			if (sFieldStr.length() > sFieldStr.indexOf(",")) {
				sFieldStr = sFieldStr.substring(sFieldStr.indexOf(",") + 1);
			} else {
				sFieldStr = "";
			}
		}
		return result;
	}
	
	/**
	 * 返回表字段
	 * @param sqlStr
	 * @return
	 */
	private  void getAllFieldsFromSqlStr(String sqlStr,Map<Object, Object> context)  {
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		int selectIndex = sqlStr.toLowerCase().indexOf("select");
		int fromIndex = sqlStr.toLowerCase().indexOf("from");
		if (selectIndex >= 0 && fromIndex >= 0) {
			sqlStr = sqlStr.substring(selectIndex + 6, fromIndex).trim();// 选取select和from之间的文字
		}
		String[] tfields = sqlStr.split(",");
		//List<String> fields = new ArrayList<String>();
		for (int i = 0; i < tfields.length; i++) {
			// 去掉as
			int asIndex = tfields[i].indexOf(" as ");
			if (asIndex >= 0) {
				String name = tfields[i].substring(asIndex + 4).trim();
				queryFieldList.add(name);
				popVarList.add(name);
			} else {
				int dotIndex = tfields[i].indexOf(".");
				if (dotIndex >= 0) {
					String name = tfields[i].substring(dotIndex + 1).trim();
					queryFieldList.add(name);
					popVarList.add(name);
				} else {
					String name = tfields[i].trim();
					queryFieldList.add(name);
					popVarList.add(name);
				}
			}
		}
	}
	//获得替换常量后的SQL语句
	private String getSqlStrReplaceComm(IMacroToken token) throws MacroParameterErrorException{
		String str = token.getParameters()[0];
		if(str.indexOf("insert into") >= 0){
			throw new MacroParameterErrorException("[PRO*C结果集语句]",str,"不能使用INSERT。");
		}else if(str.indexOf("update ") >= 0){
			throw new MacroParameterErrorException("[PRO*C结果集语句]",str,"不能使用UPDATE。");
		}
		
		//str = EngineUtil.replaceConstant(str, this.getFunction(), true);去除非常工具还有移植
		str = str.replaceAll("\\-\\-[^\n]*\n", "\n");
		return str; 
	}
	
	/**
	 * 解析SQL语句中的@变量，加入到set列表中
	 * @param procVarList
	 */
	private void splitFieldList(IMacroToken token,Map<Object, Object> context){
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		String sql = token.getParameters()[0];
		Pattern p = Pattern.compile("@[\\w\\d_]+");
		Matcher m = p.matcher(sql);
		while (m.find()) {
			int index = m.group().indexOf("@");
			String field = m.group().substring(index + 1);
			inFieldList.add(field);//这里有多少个@就加多少个，重复项不用去除
			//变量替换列表，注意这里不能是helper.addAttribute(IAtomEngineContextConstant.PSEUDO_CODE_PARA_LIST, field);
			popVarList.add(field);//把变量加到porc变量列表以及伪代码变量列表中
		}
		
		
	}

}
