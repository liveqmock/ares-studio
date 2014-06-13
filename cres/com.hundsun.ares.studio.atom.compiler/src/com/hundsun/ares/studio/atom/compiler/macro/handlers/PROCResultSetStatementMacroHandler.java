/**
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.atom.compiler.macro.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.exception.MacroParameterErrorException;
import com.hundsun.ares.studio.atom.compiler.exception.TableNotFoundException;
import com.hundsun.ares.studio.atom.compiler.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.macro.TokenDomain;
import com.hundsun.ares.studio.atom.compiler.skeleton.util.TableResourceUtil;
import com.hundsun.ares.studio.atom.compiler.token.PROCResultSetStatementToken;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IDomainHandler;
import com.hundsun.ares.studio.engin.token.ITokenDomain;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.jres.model.database.TableColumn;

/**
 * @author zhuyf
 * 
 */
public class PROCResultSetStatementMacroHandler implements IMacroTokenHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return MacroConstant.PROC_RESULTSET_STATEMENT_MACRONAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(
	 * com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		/*
		 * 输入描述： [PRO*C结果集语句][ sql查询语句] 处理流程： 1.获取select语句的查询字段
		 * 主要是*的处理，不管表别名是否存在，都可正常获取字段，且考虑select嵌套的情况，以最里面层select字段为准 去除注释语句的*的影响
		 * 其次是字段有可能带有函数，如nvl 字段可能用as定义别名，那么在结果集返回时，使用as别名作为AddField的字段名
		 * 2.查询字段存入中，做后续的宏处理，如[POR*C结果集返回] 3.生成如下语句： EXEC SQL DECLARE
		 * cursor[对象号]+[编号] CURSOR FOR [select语句]; EXEC SQL OPEN
		 * cursor[对象号]+[编号];
		 * 
		 * if (CheckDbLinkMethod(lpConn,SQLCODE) < 0) { if ((SQLCODE<=
		 * ERR_DB_NO_CONTINUE_FETCH) && (SQLCODE>=
		 * ERR_DB_FAILOVER_NETWORK_OPER_FAIL)) { iReturnCode = SQLCODE;
		 * v_error_no = SQLCODE;
		 * hs_strncpy(v_error_info,sqlca.sqlerrm.sqlerrmc,sqlca
		 * .sqlerrm.sqlerrml); v_error_id = SQLCODE;
		 * hs_strncpy(v_error_sysinfo,sqlca
		 * .sqlerrm.sqlerrmc,sqlca.sqlerrm.sqlerrml); EXEC SQL rollback;
		 * 
		 * goto svr_end; }
		 * 
		 * lpConn->setErrMessage(HSDB_CONNECTION_STATUS_DISCONN,SQLCODE,sqlca.
		 * sqlerrm.sqlerrmc); }
		 * 
		 * if (SQLCODE == OK_SUCCESS) 其中[select语句]以5个字段为一行，最外层select中追加对象号的注释信息
		 * 4.加入游标到声明列表中 5.SQL语句支持历史、归档、冗余、冗余上日表 [PRO*C结果集语句][select * from
		 * his_clientjour][clientjour] [PRO*C结果集语句][select * from
		 * fil_clientjour][clientjour] [PRO*C结果集语句][select * from
		 * r_clientjour][clientjour] [PRO*C结果集语句][select * from
		 * rl_clientjour][clientjour]
		 */

		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper) context
				.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		addMacroNameToMacroList(token, context);// 把宏名加到入数据库列表以及proc列表中
		Set<String> cursorList = helper
				.getAttribute(IAtomEngineContextConstant.ATTR_CURSOR_LIST);
		AtomFunction func = (AtomFunction) context
				.get(IAtomEngineContextConstant.ResourceModel);
		int cursorTotalSizeAdd1 = cursorList.size() + 1;
		String cursorID = func.getObjectId() + cursorTotalSizeAdd1;
		helper.addAttribute(IAtomEngineContextConstant.ATTR_CURSOR_LIST,
				cursorID);
		String sql = "";
		List<ICodeToken> codeList = new ArrayList<ICodeToken>();
		String[] sqlFields = {};
		if (token.getParameters().length == 3) {// 如果用户列出了字段
			sql =  token.getParameters()[0];
			String[] tempSqlFields = StringUtils.split(token.getParameters()[2], ",");
			sqlFields = new String[tempSqlFields.length];
			for (int i = 0; i < tempSqlFields.length; i++) {
				sqlFields[i] = StringUtils.trim(tempSqlFields[i]);
			}
			
			
		} else {// 没有列出字段则解析sql语句,从sql语句中获得字段
			sql = this.getSqlStatement(token, context);
			sqlFields = getAllFieldsFromSqlStr(getFieldString(token, context));// sql语句中的字段
			
		}
		addVarList(token, context, sqlFields);// 把变量加到porc变量列表以及伪代码变量列表中
		addDomain(token, context, sqlFields);// 添加域
		String cursorName = getCursorName(context);
		 codeList.add(new
		 PROCResultSetStatementToken(token,cursorName,sql));//添加codeToken
		return codeList.iterator();
	}

	/**
	 * 把变量添加到proc列表中去
	 * 
	 * @param procVarList
	 */
	private void addVarList(IMacroToken token, Map<Object, Object> context,
			String[] sqlField) {
		List<String> popVarList = (List<String>) context
				.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper) context
				.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		Pattern p = Pattern.compile("@[\\w\\d_]+");
		// sql语句在第一参数中
		Matcher m = p.matcher(token.getParameters()[0]);
		while (m.find()) {
			String fieldName = m.group().substring(1);
			// 加入到proc变量列表中，所有proc变量必须在proc定义区声明
			helper.addAttribute(
					IAtomEngineContextConstant.ATTR_PROC_VARIABLE_LIST,
					fieldName);
			// 变量替换列表，注意这里不能是helper.addAttribute(IAtomEngineContextConstant.PSEUDO_CODE_PARA_LIST,
			// field);
			popVarList.add(fieldName);
		}
		for (String field : sqlField) {
			// 加入到proc游标变量列表中，所有proc游标变量必须在proc定义区声明
			helper.addAttribute(
					IAtomEngineContextConstant.ATTR_CURSOR_PROC_VARIABLE_LIST,
					field);// 注意这里不需要加_cur，定义变量时，需要用到原名，所以保持原名，在变量定义时，才加_cur
			// 变量替换列表，注意这里不能是helper.addAttribute(IAtomEngineContextConstant.PSEUDO_CODE_PARA_LIST,
			// field);
			// 游标变量后缀加_cur
			popVarList.add(field + "_cur");
		}
	}

	/**
	 * 添加域
	 */
	private void addDomain(IMacroToken token, Map<Object, Object> context,
			String[] sqlFields) {
		IDomainHandler handler = (IDomainHandler) context
				.get(IEngineContextConstant.DOMAIN_HANDLER);
		String key = getKey();
		Object[] args = new Object[2];
		args[0] = getCursorName(context);// 添加游标名cursor[对象号]+[编号]域参数
		args[1] = sqlFields;// 游标字段名称
		ITokenDomain tokenDomain = new TokenDomain(key, args);
		handler.addDomain(tokenDomain);
	}

	// /**
	// * 把宏名加入到宏列表中
	// */
	private void addMacroNameToMacroList(IMacroToken token,
			Map<Object, Object> context) {
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper) context
				.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		helper.addAttribute(IAtomEngineContextConstant.ATTR_DATABASE_MACRO,
				token.getKeyword());// 添加到数据库列表中
		helper.addAttribute(IAtomEngineContextConstant.ATTR_PROC_MACRO,
				token.getKeyword());// 添加到proc宏列表中
	}

	/**
	 *获得sql语句
	 */
	private String getSqlStatement(IMacroToken token,Map<Object, Object> context) throws TableNotFoundException,MacroParameterErrorException {
		String sqlStr = getSqlStrReplaceComm(token);
		String sTableName = "";
		Stack<String> addHead = new Stack<String>();
		if (token.getParameters().length >= 2) {
			sTableName = token.getParameters()[1];
		}
		// 去掉嵌套的select
		Pattern p = Pattern.compile("\\s*select\\s+\\*\\s+from\\s*\\(");
		Matcher m = p.matcher(sqlStr);
		while (m.find()) {
			if (m.start() == 0) {
				addHead.add(sqlStr.substring(0, m.end()));
				sqlStr = sqlStr.substring(m.end());
				m = p.matcher(sqlStr);
			}
		}
		// 取得select和from中间的变量部分
		String fieldStr = sqlStr.substring(sqlStr.indexOf("select ") + 7);
		p = Pattern.compile("(\\s+from\\s+)");
		m = p.matcher(fieldStr);
		if (m.find()) {
			fieldStr = fieldStr.substring(0, m.start()).trim();
		}
		// 最后加一个，这样所有的变量最后都有逗号了
		String sFieldStr = fieldStr + ",";
		// 处理某个变量
		int k = 0;
		while (sFieldStr.indexOf(",") >= 0) {
			// 表的别名变量
			String sOtherTableName = "";
			// 替换*的字段变量
			String sOtherFieldStr = "";

			String sTempField = sFieldStr.substring(0, sFieldStr.indexOf(","))
					.trim();

			if (sTempField.indexOf("*/") != -1
					&& sTempField.indexOf("/*") != -1) {
				sTempField = sTempField.substring(sTempField.indexOf("*/") + 2);
			}

			// 如果是带函数的字段，则先去除函数
			if (sTempField.indexOf("(") >= 0) {
				if (sTempField.toLowerCase().indexOf(" as ") < 0) {
					sFieldStr = sFieldStr.substring(sFieldStr.indexOf(",") + 1)
							.trim();
					do {
						if (sFieldStr.indexOf(",") >= 0) {
							sTempField = sFieldStr.substring(0,
									sFieldStr.indexOf(",")).trim();
							if (sTempField.toLowerCase().indexOf(" as ") >= 0) {
								break;
							} else {
								sFieldStr = sFieldStr.substring(
										sFieldStr.indexOf(",") + 1).trim();
							}
						} else {
							break;
						}
					} while (true);
				}
			}

			// 有可能是去除函数前半部分以后余下的字段，此时应有AS单词
			if (sTempField.toLowerCase().indexOf(" as ") >= 0) {
				sTempField = sTempField.substring(sTempField.toLowerCase()
						.indexOf(" as ") + 4);
			}

			// 如果是带别名的字段，则先去除别名
			if (sTempField.indexOf(".") >= 0) {
				// 取别名
				sOtherTableName = sTempField.substring(0,
						sTempField.indexOf("."));
				sTempField = sTempField.substring(sTempField.toLowerCase()
						.indexOf(".") + 1);
			}

			// 如果取得的字段中带*号，表明需要从表中取字段
			if (sTempField.trim().endsWith("*") && sTempField.indexOf("/*") < 0) {
				if (!sTableName.equals("")) {
					if (!sTableName.substring(1).equals(",")) {
						sTableName = sTableName + ",";
					}
					if (sTableName.indexOf(",") >= 0) {
						String sTempTableName = sTableName.substring(0,
								sTableName.indexOf(","));
						if (!sTempTableName.equals("")) {
							// 从表结构中获取结果集的字段列表
							IARESProject project = (IARESProject) context
									.get(IAtomEngineContextConstant.Aresproject);
							List<TableColumn> fields = TableResourceUtil
									.getFieldsWithoutFlag("H", sTempTableName,
											project);

							if (fields.size() == 0) {

								if ("1" == null) {
									// 视图情况另外再处理的话通过视图获得fields
								} else {
									String errorInfo = "";

									for (int index = 0; index < fields.size(); index++) {
										TableColumn field = fields.get(index);

										if (sOtherTableName.equals("")) {
											if (index == fields.size() - 1) {
												sOtherFieldStr = sOtherFieldStr
														+ field.getName();
											} else {
												sOtherFieldStr = sOtherFieldStr
														+ field.getName() + ",";
											}
										} else {
											if (index == fields.size() - 1) {
												sOtherFieldStr = sOtherFieldStr
														+ sOtherTableName + "."
														+ field.getName();
											} else {
												sOtherFieldStr = sOtherFieldStr
														+ sOtherTableName + "."
														+ field.getName() + ",";
											}
										}
										k++;
										// 每4个字段换行
										if (k % 4 == 0)
											sOtherFieldStr += "\n";
									}

								}

							} else {

								// 将表字段追加到游标字段列表中
								for (int index = 0; index < fields.size(); index++) {
									TableColumn field = fields.get(index);
									if (sOtherTableName.equals("")) {
										if (index == fields.size() - 1) {
											sOtherFieldStr = sOtherFieldStr
													+ field.getName();
										} else {
											sOtherFieldStr = sOtherFieldStr
													+ field.getName() + ",";
										}
									} else {
										if (index == fields.size() - 1) {
											sOtherFieldStr = sOtherFieldStr
													+ sOtherTableName + "."
													+ field.getName();
										} else {
											sOtherFieldStr = sOtherFieldStr
													+ sOtherTableName + "."
													+ field.getName() + ",";
										}
									}
									k++;
									// 每4个字段换行
									if (k % 4 == 0)
										sOtherFieldStr += "\n";
								}
							}
						}
						// 替换.*
						if (!sOtherFieldStr.equals("")) {
							if (sOtherTableName.equals("")) {
								sqlStr = sqlStr.replace("*", sOtherFieldStr);
							} else {
								sqlStr = sqlStr.replace(sOtherTableName + ".*",
										sOtherFieldStr);
							}
						}
						sTableName = sTableName.substring(sTableName.length()
								- sTableName.indexOf(","));
					}
				}
			}
			if (sFieldStr.length() > sFieldStr.indexOf(",")) {
				sFieldStr = sFieldStr.substring(sFieldStr.indexOf(",") + 1);
			} else {
				sFieldStr = "";
			}
			k++;
			// 每4个字段换行
			if (k % 4 == 0)
				sFieldStr += "\n";
		}

		while (addHead.size() > 0) {
			sqlStr = addHead.pop().replace("*",
					removeOtherName(getFieldString(token, context)))
					+ sqlStr;
		}
		// 对where语句，每一个and换一行
		sqlStr = sqlStr.replaceAll(" and ", "\n and ");
		sqlStr = sqlStr.replaceAll(" order by ", "\n order by ");
		return sqlStr;
	}

	/**
	 * 处理[PRO*C结果集语句]SQL语句
	 * 
	 * @return 处理后的sql语句
	 * @throws TableNotFoundException
	 */

	private String getFieldString(IMacroToken token, Map<Object, Object> context)
			throws TableNotFoundException, MacroParameterErrorException {
		String sqlStr = getSqlStrReplaceComm(token);
		String sTableName = "";
		if (token.getParameters().length >= 2) {
			sTableName = token.getParameters()[1];
		}
		//如果有嵌套的select * from(，则去除
		Pattern p = Pattern.compile("\\s*(select|SELECT)\\s+\\*\\s+(from|FROM)\\s*\\(");
		Matcher m = p.matcher(sqlStr);
		while (m.find()) {
			if (m.start() == 0) {
				sqlStr = sqlStr.substring(m.end());
				m = p.matcher(sqlStr);
			}
		}
		String fieldStr = sqlStr.substring(sqlStr.indexOf("select ") + 7);
		p = Pattern.compile("\\s+(from|FROM)\\s+");
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
							IARESProject project = (IARESProject) context.get(IAtomEngineContextConstant.Aresproject);
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

	// 去掉变量前的表别名
	public String removeOtherName(String str) {
		String result = "";
		List<String> para = new ArrayList<String>();
		str = str + ",";
		int k = 0;
		while (str.indexOf(",") != -1) {
			String sTempField = str.substring(0, str.indexOf(",")).trim();
			str = str.substring(str.indexOf(",") + 1).trim();
			para.add(sTempField);
		}
		for (int i = 0; i < para.size(); i++) {
			if (para.get(i).indexOf(".") != -1) {
				result = result
						+ para.get(i).substring(para.get(i).indexOf(".") + 1)
								.trim();
			} else {
				result = result + para.get(i).trim();
			}
			if (i != para.size() - 1)
				result = result + ",";
			if (i % 4 == 0)
				result += "\n";
		}

		return result;

	}

	/**
	 * 返回表字段
	 * 
	 * @param sqlStr
	 * @return
	 */
	private String[] getAllFieldsFromSqlStr(String sqlStr) {
		int selectIndex = sqlStr.toLowerCase().indexOf("select");
		int fromIndex = sqlStr.toLowerCase().indexOf("from");
		if (selectIndex >= 0 && fromIndex >= 0) {
			sqlStr = sqlStr.substring(selectIndex + 6, fromIndex).trim();// 选取select和from之间的文字
		}
		String[] tfields = sqlStr.split(",");
		List<String> fields = new ArrayList<String>();
		for (int i = 0; i < tfields.length; i++) {
			// 去掉as
			int asIndex = tfields[i].indexOf(" as ");
			if (asIndex >= 0) {
				String name = tfields[i].substring(asIndex + 4).trim();
				fields.add(name);

			} else {
				int dotIndex = tfields[i].indexOf(".");
				if (dotIndex >= 0) {
					String name = tfields[i].substring(dotIndex + 1).trim();
					fields.add(name);
				} else {
					String name = tfields[i].trim();
					fields.add(name);
				}
			}
		}
		return fields.toArray(new String[fields.size()]);
	}

	// 获得替换常量后的SQL语句
	private String getSqlStrReplaceComm(IMacroToken token)
			throws MacroParameterErrorException {
		String str = token.getParameters()[0];
		if (str.indexOf("insert into") >= 0) {
			throw new MacroParameterErrorException("[PRO*C结果集语句]", str,
					"不能使用INSERT。");
		} else if (str.indexOf("update ") >= 0) {
			throw new MacroParameterErrorException("[PRO*C结果集语句]", str,
					"不能使用UPDATE。");
		}

		// str = EngineUtil.replaceConstant(str, this.getFunction(),
		// true);去除非常工具还有移植
		str = str.replaceAll("\\-\\-[^\n]*\n", "\n");
		return str;
	}

	/**
	 * 获得游标字段串
	 * 
	 * @return
	 */
	private String getCursorName(Map<Object, Object> context) {
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper) context
				.get(IAtomEngineContextConstant.SKELETON_ATTRIBUTE_HELPER);
		Set<String> cursorList = helper
				.getAttribute(IAtomEngineContextConstant.ATTR_CURSOR_LIST);
		if (cursorList.size() > 0) {
			return "cursor"
					+ (cursorList.toArray(new String[cursorList.size()]))[cursorList
							.size() - 1];// LinkHashSet结构，最新的在最后
		}
		return "cursor";
	}

}
