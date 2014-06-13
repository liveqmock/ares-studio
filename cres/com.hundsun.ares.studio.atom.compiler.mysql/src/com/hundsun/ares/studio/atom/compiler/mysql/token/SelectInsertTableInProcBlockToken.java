/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.exception.MacroParameterDescNotDefineException;
import com.hundsun.ares.studio.atom.compiler.mysql.exception.TableFieldNotFoundInStdFieldException;
import com.hundsun.ares.studio.atom.compiler.mysql.exception.TableFieldNotSettingException;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.AtomFunctionCompilerUtil;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.CodeUtil;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.GenStringUtil;
import com.hundsun.ares.studio.atom.compiler.mysql.skeleton.util.TableResourceUtil;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.exception.ErrorParameterNumberException;
import com.hundsun.ares.studio.engin.exception.HSException;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.engin.util.TypeRule;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;

/**
 * @author liaogc
 *
 */
public class SelectInsertTableInProcBlockToken implements ICodeToken{
	
	private IMacroToken macroToken ;//当前处理的宏
	private Map<Object, Object> context;//当前处理宏的上下文
	private AtomFunction atomFunction;//原子函数模型
	private Map<String, String> defaultValueMap = new HashMap<String, String>();//默认值列表
	
	public SelectInsertTableInProcBlockToken(IMacroToken macroToken,Map<Object, Object> context,Map<String, String> defaultValueMap){
		this.macroToken = macroToken;
		this.context = context;
		this.atomFunction = (AtomFunction) context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		this.defaultValueMap = defaultValueMap;
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
		try{
		String sqlStr = getMacroToken().getParameters()[1];
		if(sqlStr.trim().toLowerCase().startsWith("select")){
			return genCodeSelect();//如果宏的第一第二参数都是表名时
		}else{
			return genCodeTable();//如果宏的第二个参数是sql语句时
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	
	}
	
	/**
	 * 如果宏的第一个,第二个参数都是表名时,生成相关代码
	 * @return
	 * @throws Exception
	 */
	private String genCodeTable()throws Exception {
		StringBuffer insertCode = new StringBuffer();
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstantMySQL.Aresproject);
		String targerTableName = getMacroToken().getParameters()[0];//目标表名
		String sourceTableName = getMacroToken().getParameters()[1];//源表名
		insertCode.append("//插入表" + targerTableName + "记录").append(NEWLINE);
		List<TableColumn>  targetColumns = TableResourceUtil.getFieldsWithoutFlag("H",targerTableName,project);//获取目标表所有列
		
		
		insertCode.append("insert "+"/*"+this.atomFunction.getObjectId()+"*/"+" into ");
		insertCode.append(targerTableName);
		insertCode.append(NEWLINE).append("(").append(NEWLINE+"\t");
		//遍历目标表是的各列
		for (int i = 0; i < targetColumns.size(); i++) {

			TableColumn targerTableColumn = targetColumns.get(i);
			StandardField tableFieldSTD  = MetadataServiceProvider.getStandardFieldByName((IARESProject)getContext().get(IAtomEngineContextConstantMySQL.Aresproject), targerTableColumn.getFieldName());
			if (tableFieldSTD != null) {
				if (i != 0) {
					insertCode.append(",");
					if (i % 4 == 0) {//每个字段换一行
						insertCode.append(NEWLINE+"\t");
					}
				}
				
				insertCode.append(CodeUtil.formatInsert(targerTableColumn.getFieldName()));//产生insert into中的一列
			} else {//如果表中的列不在标准字段中则分出导演
				insertCode.append(NEWLINE).append("表字段不存在：" + targerTableColumn.getFieldName() ).append(NEWLINE);
				throw new TableFieldNotFoundInStdFieldException(targerTableName,targerTableColumn.getFieldName());
			}
		}
		insertCode.append(NEWLINE).append(")");
		insertCode.append(NEWLINE).append("select").append(NEWLINE+"\t");
		for (int i = 0; i < targetColumns.size(); i++) {

			TableColumn targerTableColumn = targetColumns.get(i);
			String fieldDataType= AtomFunctionCompilerUtil.getRealDataType(targerTableColumn.getFieldName(), project, context);
				if (i != 0) {
					insertCode.append(",");
					if (i % 4 == 0) {//每个字段换一行
						insertCode.append(NEWLINE+"\t");
					}
				}
				if (defaultValueMap.get(targerTableColumn.getFieldName()) != null) {//如果字段中默认值列表中
					
					if(defaultValueMap.get(targerTableColumn.getFieldName()).startsWith("@") ){
						//字符与字符串变量加nvl函数
						if(TypeRule.typeRuleCharArray(fieldDataType)||TypeRule.typeRuleChar(fieldDataType)){
							insertCode.append(CodeUtil.formatInsert("nvl("+":"+defaultValueMap.get(targerTableColumn.getFieldName())+",' ') " + " as " + targerTableColumn.getFieldName()));
						}else{//其他变量不加函数处理
							insertCode.append(CodeUtil.formatInsert(":"+defaultValueMap.get(targerTableColumn.getFieldName())+ " as "+ targerTableColumn.getFieldName()));
						}
						
					}
						
					else {
						//在默认值是,但是是普通的值(非变量)
						insertCode.append(CodeUtil.formatInsert(defaultValueMap.get(targerTableColumn.getFieldName())+ " as "+ targerTableColumn.getFieldName()));
					}
				} else {
					TableColumn sourceTableColumn = null;
					// 根据名字在源表中查找列
					for (TableColumn f : TableResourceUtil.getFieldsWithoutFlag("H", sourceTableName,project)) {
						if (f.getFieldName().equals(targerTableColumn.getFieldName())) {
							sourceTableColumn = f;
							break;
						}
					}
					if (sourceTableColumn != null)// 源表中存在字段
					{
						insertCode.append(CodeUtil.formatInsert(targerTableColumn.getFieldName()));
					} else {//如果源表中不存在
						//如果在伪代码变量,proc变量输入,输出,内部变量中存在
						if (getProcVarList().contains(targerTableColumn.getFieldName())|| getProcVarList().contains(targerTableColumn.getFieldName())||AtomFunctionCompilerUtil.isParameterINAtomFunctionParameterByName(getAtomFunction(), targerTableColumn.getFieldName(),project)) {
							if(TypeRule.typeRuleCharArray(fieldDataType)||TypeRule.typeRuleChar(fieldDataType))
								insertCode.append(CodeUtil.formatInsert("nvl(:@" + targerTableColumn.getFieldName()+",' ')"));
							else {
								insertCode.append(CodeUtil.formatInsert(":@"+ targerTableColumn.getFieldName()));
							}
							
						} else {
							//其他情况取默认值
							TypeDefaultValue typeDefaultValue  = MetadataServiceProvider.getTypeDefaultValueOfStdFieldByName((IARESProject)getContext().get(IAtomEngineContextConstantMySQL.Aresproject), targerTableColumn.getFieldName());
							if(typeDefaultValue!=null){
								String value = typeDefaultValue.getValue(MetadataServiceProvider.C_TYPE);
								if(StringUtils.equals(value, "{0}")){
									value = "' '";
								}
								insertCode.append(CodeUtil.formatInsert(value) + " as "+ targerTableColumn.getFieldName());
							}
						}
					}
				}
			}
		insertCode.append(NEWLINE).append("from " ).append(sourceTableName).append(" ;").append(NEWLINE);

		
		return insertCode.toString();
	}
	


	
	private String genCodeSelect() throws Exception {
		StringBuffer insertCode = new StringBuffer();
		IARESProject project = (IARESProject) context.get(IAtomEngineContextConstantMySQL.Aresproject);
		String targerTableName = getMacroToken().getParameters()[0];//获得目标表名
		insertCode.append("-- select插入表"+targerTableName+"记录").append(NEWLINE);
		
		List<TableColumn>  targetColumns = TableResourceUtil.getFieldsWithoutFlag("H",targerTableName, project) ;//获取目标表所有列
		insertCode.append("insert "+"/*"+this.atomFunction.getObjectId()+"*/"+" into ");
		insertCode.append(targerTableName);
		insertCode.append(NEWLINE).append("(").append(NEWLINE);
		for (int i = 0; i < targetColumns.size(); i++) {

			TableColumn targerTableColumn = targetColumns.get(i);
			StandardField tableFieldSTD  = MetadataServiceProvider.getStandardFieldByName((IARESProject)getContext().get(IAtomEngineContextConstantMySQL.Aresproject), targerTableColumn.getFieldName());
			if (tableFieldSTD != null) {
				if (i != 0) {
					insertCode.append(",");
					if (i % 4 == 0) {
						insertCode.append(NEWLINE);//每四个字段换一行
					}
				}
				
				insertCode.append(CodeUtil.formatInsert(targerTableColumn.getFieldName()));
			} else {
				insertCode.append(NEWLINE).append("表字段不存在：" + targerTableColumn.getFieldName() ).append(NEWLINE);
				throw new TableFieldNotFoundInStdFieldException(targerTableName,targerTableColumn.getFieldName());
			}
		}
		insertCode.append(NEWLINE).append(")").append(NEWLINE);
		
		
		String sqlStr = getSqlMacroParameter();//取得宏参数是的sql语句
		String fieldStr = "";
		try {
			fieldStr = GenStringUtil.getMainSelectContent(sqlStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		OldSelectFieldsBean old_selectFields = new OldSelectFieldsBean(getFieldString().split(","),splitOldField(getOldField()));
		Map<String,String> selectFields1 = new HashMap<String,String>();
		for(int i = 0;i <old_selectFields.getSelectFileds().length;i++){
			String key = old_selectFields.getSelectFileds()[i];
			String newkey = key;
			if(key.indexOf(".") > 0){
				newkey = removeOtherName(key);
			}
			
			if(!selectFields1.containsKey(newkey)){
				selectFields1.put(newkey,old_selectFields.getOldFileds()[i]);
			}else{
				if(selectFields1.get(newkey).indexOf(".")>=0){
					selectFields1.remove(newkey);
					selectFields1.put(key,old_selectFields.getOldFileds()[i]);
				}
			}
		}
		
		StringBuffer realFields = new StringBuffer();
		//到处去找该插入值
		for (int i = 0; i < targetColumns.size(); i++) {

			TableColumn targerTableColumn = targetColumns.get(i);
			String fieldDataType= AtomFunctionCompilerUtil.getRealDataType(targerTableColumn.getFieldName(), project, context);
				if (i != 0) {
					realFields.append(",");
					if(i%4 == 0 ){
						realFields.append(NEWLINE);
					}
				}
				//从用户输入的默认值中去找
               if (defaultValueMap.get(targerTableColumn.getFieldName()) != null) {//如果字段中默认值列表中
					
					if(defaultValueMap.get(targerTableColumn.getFieldName()).startsWith("@") ){
						
						//字符与字符串变量加nvl函数
						if(TypeRule.typeRuleCharArray(fieldDataType)||TypeRule.typeRuleChar(fieldDataType)){
							realFields.append(CodeUtil.formatInsert("nvl("+":"+defaultValueMap.get(targerTableColumn.getFieldName())+",' ') " + " as " + targerTableColumn.getFieldName()));
						}else{//其他变量不加函数处理
							realFields.append(CodeUtil.formatInsert(":"+defaultValueMap.get(targerTableColumn.getFieldName())+ " as "+ targerTableColumn.getFieldName()));
						}
						
					}
						
					else {
						//在默认值是,但是是普通的值(非变量)
						realFields.append(CodeUtil.formatInsert(defaultValueMap.get(targerTableColumn.getFieldName())+ " as "+ targerTableColumn.getFieldName()));
					}
				}else{
					String sourceField = null;
					
					for (int k = 0;k<old_selectFields.getSelectFileds().length;k++) {
						if(selectFields1.containsKey(targerTableColumn.getFieldName())){
							sourceField = selectFields1.get(targerTableColumn.getFieldName()).trim();
							break;
						}
					}
					if (sourceField != null)// 源表中存在字段
					{
						realFields.append(CodeUtil.formatInsert(sourceField));
					} else {//如果源表中不存在
						//如果在伪代码变量,proc变量输入,输出,内部变量中存在
						if (getProcVarList().contains(targerTableColumn.getFieldName())|| getPopVarList().contains(targerTableColumn.getFieldName())||AtomFunctionCompilerUtil.isParameterINAtomFunctionParameterByName(getAtomFunction(), targerTableColumn.getFieldName(),project)) {
							if(TypeRule.typeRuleCharArray(fieldDataType)||TypeRule.typeRuleChar(fieldDataType))
								realFields.append(CodeUtil.formatInsert("nvl(:@" + targerTableColumn.getFieldName()+",' ')"));
							else {
								realFields.append(CodeUtil.formatInsert(":@"+ targerTableColumn.getFieldName()));
							}
							
						} else {
							//其他情况取默认值
							TypeDefaultValue typeDefaultValue  = MetadataServiceProvider.getTypeDefaultValueOfStdFieldByName((IARESProject)getContext().get(IAtomEngineContextConstantMySQL.Aresproject), targerTableColumn.getFieldName());
							if(typeDefaultValue!=null){
								realFields.append(CodeUtil.formatInsert(typeDefaultValue.getValue(MetadataServiceProvider.C_TYPE))+" as "+ targerTableColumn.getFieldName());
							}
						}
					}
				
				}
				
			}
		realFields.append(NEWLINE);
		
		int index = sqlStr.indexOf(fieldStr);
		insertCode.append(sqlStr.substring(0,index)+NEWLINE+realFields.toString()+sqlStr.substring(index+fieldStr.length(),sqlStr.length()));
		return insertCode.toString()+";";
	}
	

	
	/**
	 * 
	 * @return 返回宏的第一个参数中的sql(已经去除注释的sql)
	 */
	private String getSqlMacroParameter() {
		String str = getMacroToken().getParameters()[1];
		str = str.replaceAll("\\-\\-[^\n]*\n", "\n");
		return str; 
	}
	
	/**
	 * 从SQL语句中提取字段
	 * 
	 * @return 字段的字符串，用逗号分割
	 * @throws ErrorParameterNumberException
	 *          抛出宏参数个数错误的异常
	 * @throws MacroParameterDescNotDefineException
	 *          抛出参数说明信息没有配置的异常
	 */
	private String getFieldString() throws HSException {
		String sqlStr = getSqlMacroParameter();
		String sTableName = "";
		if (getMacroToken().getParameters().length > 2) {
			sTableName = getMacroToken().getParameters()[2];
		}
		Pattern p = Pattern.compile("\\s*select\\s+\\*\\s+from\\s*\\(");
		Matcher m = p.matcher(sqlStr);
		while (m.find()) {
			if (m.start() == 0) {
				sqlStr = sqlStr.substring(m.end());
				m = p.matcher(sqlStr);
			}
		}
		
		String fieldStr = "";
		try {
			fieldStr = GenStringUtil.getMainSelectContent(sqlStr);
		} catch (Exception e) {
			e.printStackTrace();
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
				if (!sTableName.equals("")) {
					if (!sTableName.substring(1).equals(",")) {
						sTableName = sTableName + ",";
					}
					if (sTableName.indexOf(",") >= 0) {
						String sTempTableName = sTableName.substring(0, sTableName.indexOf(","));
						if (!sTempTableName.equals("")) {
							// 从表结构中获取结果集的字段列表
							IARESProject project = (IARESProject) context.get(IAtomEngineContextConstantMySQL.Aresproject);
								List<TableColumn> fields = TableResourceUtil.getFieldsWithoutFlag("H",sTempTableName, project);
								if (fields.size() == 0) {
									throw new TableFieldNotSettingException(sTempTableName);
								}
								// 将表字段追加到游标字段列表中
								for (int index = 0; index < fields.size(); index++) {
									TableColumn tfield = fields.get(index);
									if (sOtherTableName.equals("")) {
										if (index == fields.size() - 1) {
											sOtherFieldStr = sOtherFieldStr + tfield.getName();
										} else {
											sOtherFieldStr = sOtherFieldStr + tfield.getName() + ",";
										}
									} else {
										if (index == fields.size() - 1) {
											sOtherFieldStr = sOtherFieldStr + sOtherTableName + "." + tfield.getName();
										} else {
											sOtherFieldStr = sOtherFieldStr + sOtherTableName + "." + tfield.getName() + ",";
										}
									}
								}
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
	private String removeOtherName(String str) {
		if(str.indexOf(".") == -1 || str.endsWith(".")){
			return str;
		}
		return str.substring(str.indexOf(".")+1);
	}
	
	

	String[] splitOldField(String fields){
		List<String> result = new ArrayList<String>();
		String temp = "";
		int i = 0;
		while(fields.indexOf(",") >= 0){
			i++;
			temp = "";
			temp = temp + fields.substring(0, fields.indexOf(","));
			fields = fields.substring(fields.indexOf(",")+1, fields.length());
			if(temp.indexOf("(") != -1){
				if(temp.indexOf(" as ") != -1) {
					//select as 函数情况
				}else {
					int next = fields.indexOf(',', fields.indexOf(" as ")+ 4);
					if(next == -1){
						temp = temp + "," + fields;
						fields = "";
						result.add(temp.trim());
						break;
					}
					temp = temp + ","  + fields.substring(0, next);
					fields = fields.substring(next+1, fields.length());
				}
			}
			result.add(temp.trim());
		}
		if(fields.length() != 0)
			result.add(fields);
		return result.toArray(new String[result.size()]);
	}
		
	
	private String getOldField() throws HSException {
		String sqlStr = getSqlMacroParameter();
		String sTableName = "";
		Stack<String> addHead = new Stack<String>();
		if (getMacroToken().getParameters().length > 2) {
			sTableName = getMacroToken().getParameters()[2];
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
		
		String fieldStr = "";
		try {
			fieldStr = GenStringUtil.getMainSelectContent(sqlStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sFieldStr = fieldStr + ",";
		// 处理某个变量
		int k = 0;
		while (sFieldStr.indexOf(",") >= 0) {
			// 表的别名变量
			String sOtherTableName = "";
			// 替换*的字段变量
			String sOtherFieldStr = "";

			String sTempField = sFieldStr.substring(0, sFieldStr.indexOf(",")).trim();
			
			if(sTempField.indexOf ("*/") != -1 && sTempField.indexOf ("/*") != -1){//处理注释
				sTempField = sTempField.substring (sTempField.indexOf ("*/")+2);
			}

			// 如果是带函数的字段，则先去除函数
			if (sTempField.indexOf("(") >= 0) {
				if (sTempField.toLowerCase().indexOf(" as ") < 0) {
					sFieldStr = sFieldStr.substring(sFieldStr.indexOf(",") + 1).trim();
					do {//因为可能有函数所以","逗可能有很多个
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
				if (!sTableName.equals("")) {
					if (!sTableName.substring(1).equals(",")) {
						sTableName = sTableName + ",";
					}
					if (sTableName.indexOf(",") >= 0) {
						String sTempTableName = sTableName.substring(0, sTableName.indexOf(","));
						if (!sTempTableName.equals("")) {
							// 从表结构中获取结果集的字段列表
								IARESProject project = (IARESProject) context.get(IAtomEngineContextConstantMySQL.Aresproject);
								
								List<TableColumn> fields = TableResourceUtil.getFieldsWithoutFlag("H",sTempTableName, project);
								if (fields.size() == 0) {
									throw new TableFieldNotSettingException(sTempTableName);
								}
								// 将表字段追加到游标字段列表中
								for (int index = 0; index < fields.size(); index++) {
									TableColumn tfield = fields.get(index);
									if (sOtherTableName.equals("")) {
										if (index == fields.size() - 1) {
											sOtherFieldStr = sOtherFieldStr + tfield.getName();
										} else {
											sOtherFieldStr = sOtherFieldStr + tfield.getName() + ",";
										}
									} else {
										if (index == fields.size() - 1) {
											sOtherFieldStr = sOtherFieldStr + sOtherTableName + "." + tfield.getName();
										} else {
											sOtherFieldStr = sOtherFieldStr + sOtherTableName + "." + tfield.getName() + ",";
										}
									}
								}
						}
						// 替换.*
						if (!sOtherFieldStr.equals("")) {
							if (sOtherTableName.equals("")) {
								sqlStr = sqlStr.replace("*", sOtherFieldStr);
							} else {
								sqlStr = sqlStr.replace(sOtherTableName + ".*", sOtherFieldStr);
							}
						}
						sTableName = sTableName.substring(sTableName.length() - sTableName.indexOf(","));
					}
				}
			}
			if (sFieldStr.length() > sFieldStr.indexOf(",")) {//处理下一个字段
				sFieldStr = sFieldStr.substring(sFieldStr.indexOf(",") + 1);
			} else {
				sFieldStr = "";
			}
		}
		String result = "";
		try {
			result = GenStringUtil.getMainSelectContent(sqlStr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	


	/**
	 * 返回伪代码使用变量列表
	 * @return
	 */
	private List<String> getPopVarList(){
		return  (List<String>)getContext().get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
	}
	
	/**
	 * 返回proc变量列表
	 * @return
	 */
	private Set<String> getProcVarList(){
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)getContext().get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		return  (Set<String>)helper.getAttribute(IAtomEngineContextConstantMySQL.ATTR_PROC_VARIABLE_LIST);
	}
	
	
	/**
	 * 返回原子函数模型
	 * @return
	 */
	private AtomFunction getAtomFunction(){
		return this.atomFunction;
		}
	/**
	 * 获得宏
	 * @return IMacroToken
	 */
	private IMacroToken getMacroToken(){
		return this.macroToken;
	}
	/**
	 * 获得上下文
	 * @return
	 */
	private Map<Object,Object> getContext(){
		return this.context;
	}
	
	/*
	 * 
	 */
	private class OldSelectFieldsBean {
		
		String[] oldFileds = {};
		
		String[] selectFileds = {};

		
		/**
		 * @return the oldFiledName
		 */
		public String[] getOldFileds() {
			return oldFileds;
		}


		/**
		 * @param oldFiledName the oldFiledName to set
		 */
		public void setOldFileds(String[] oldFileds) {
			this.oldFileds = oldFileds;
		}


		/**
		 * @return the selectFiledName
		 */
		public String[] getSelectFileds() {
			return selectFileds;
		}


		/**
		 * @param selectFiledName the selectFiledName to set
		 */
		public void setSelectFileds(String[] selectFileds) {
			this.selectFileds = selectFileds;
		}


		public OldSelectFieldsBean(String[] selectFileds,String[] oldFileds){
			this.oldFileds = oldFileds;
			this.selectFileds = selectFileds;
		}

	}

	

}
