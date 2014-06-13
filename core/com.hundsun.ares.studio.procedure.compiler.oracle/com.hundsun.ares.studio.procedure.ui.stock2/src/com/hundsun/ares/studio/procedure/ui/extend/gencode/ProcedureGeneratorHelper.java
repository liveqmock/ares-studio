/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.procedure.ui.extend.gencode;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.UserExtensibleProperty;
import com.hundsun.ares.studio.engin.exception.HSException;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.BusinessDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardDataType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.impl.TypeDefaultValueImpl;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * type comments here.
 * 
 * @author yanwj06282
 * @version 1.0
 * @history
 */
public class ProcedureGeneratorHelper {

	public static final String OBJECT_TYPE = "type";
	
	/**
	 * 装饰输入参数,生成代码
	 * 
	 * @param project
	 * @param procedure
	 * @return
	 */
	public static String declareInputCode(IARESProject project,
			Procedure procedure) {
		StringBuffer declareCode = new StringBuffer();
		int index = 0;
		ReferenceManager rm = ReferenceManager.getInstance();
		int inputLength = procedure.getInputParameters().size();
		int outputLength = procedure.getOutputParameters().size();
		for (Parameter input : procedure.getInputParameters()) {
			ReferenceInfo info = rm.getFirstReferenceInfo(project,
					IMetadataRefType.StdField, input.getId(), false);
			if (info == null) {
				continue;
			}
			StandardField std = (StandardField) info.getObject();
			if (std == null) {
				continue;
			}
			String defaultValue = input.getDefaultValue();
			String dt = std.getDataType();
			ReferenceInfo dtInfo = rm.getFirstReferenceInfo(project,
					IMetadataRefType.BizType, dt, false);
			if (dtInfo != null) {
				BusinessDataType bdt = (BusinessDataType) dtInfo.getObject();
				if (StringUtils.isBlank(defaultValue)) {
					ReferenceInfo devInfo = rm.getFirstReferenceInfo(project,
							IMetadataRefType.DefValue, bdt.getDefaultValue(), false);
					if (devInfo != null) {
						TypeDefaultValueImpl dev = (TypeDefaultValueImpl) devInfo.getObject();
						defaultValue = dev.getValue("oracle");
					}
				}
			}
			String tableFieldType = "hstype." + std.getDataType().trim();

			if (input.getFlags().indexOf("IO") != -1) {
				declareCode.append("  p_" + input.getId() + "  in  out  "
						+ tableFieldType + "  DEFAULT  " + defaultValue + "  ");
			} else {
				declareCode.append("  p_" + input.getId() + "  "
						+ tableFieldType + "  DEFAULT  " + defaultValue + "  ");
			}
			if (index < inputLength - 1 || outputLength > 0) {
				declareCode.append(",");
			}
			// 添加说明信息
			String comment = std.getDescription();
			if (StringUtils.isBlank(comment) || StringUtils.equalsIgnoreCase(comment, "null")) {
				declareCode.append("--" + std.getChineseName() + "\n");
			} else {
				declareCode.append("--" + comment + "\n");
			}
			index++;
		}
		return declareCode.toString();
	}

	public static String declareOutputCode(IARESProject project,
			Procedure procedure) {
		StringBuffer declareCode = new StringBuffer();
		int imputLength = procedure.getInputParameters().size();
		int outputLength = procedure.getOutputParameters().size();
		ReferenceManager rm = ReferenceManager.getInstance();
		if (procedure.isOutputCollection()) {// 返回结果集为Ture，则声明游标输出参数，其它输出参数不声明
			for (Parameter output : procedure.getOutputParameters()) {
				ReferenceInfo info = rm.getFirstReferenceInfo(project,
						IMetadataRefType.StdField, output.getId(), false);
				if (info == null) {
					continue;
				}
				StandardField std = (StandardField) info.getObject();
				if (std == null) {
					continue;
				}
				String comment = output.getDescription();
				String tableFieldType = "hstype." + std.getDataType().trim();

				// 如果是IO参数还是要声明的
				if (output.getFlags().indexOf("IO") != -1) {
					declareCode.append("  p_" + output.getId() + "  in  out  "
							+ tableFieldType + "  ");
					declareCode.append(",");

					// 添加说明信息
					if (StringUtils.isBlank(comment) || StringUtils.equalsIgnoreCase(comment, "null")) {
						declareCode.append("--" + std.getChineseName() + "\n");
					} else {
						declareCode.append("--" + comment + "\n");
					}
				}
			}
			if (!findInInOutList(procedure, "cursor")) {
				declareCode
						.append("  p_cursor  out  hstype.t_cursor  -- 输出结果集游标\n");
			}
		} else {
			int index = 1;
			for (Parameter output : procedure.getOutputParameters()) {

				ReferenceInfo info = rm.getFirstReferenceInfo(project,
						IMetadataRefType.StdField, output.getId(), false);
				if (info == null) {
					continue;
				}
				StandardField std = (StandardField) info.getObject();
				if (std == null) {
					continue;
				}

				String comment = output.getDescription();
				String tableFieldType = "hstype." + std.getDataType().trim();

				if (output.getFlags().indexOf("IO") != -1) {
					declareCode.append("  p_" + output.getId() + "  in  out  "
							+ tableFieldType + "  ");
					if (imputLength + index >= 1 && index < outputLength) {
						declareCode.append(",");
					}
					
				} else {
					declareCode.append("  p_" + output.getId() + "  out  "
							+ tableFieldType + " ");
					if (imputLength + index >= 1 && index < outputLength) {
						declareCode.append(",");
					}
				}
				// 添加说明信息
				if (StringUtils.isBlank(comment) || StringUtils.equalsIgnoreCase(comment, "null")) {
					declareCode.append("--" + std.getChineseName() + "\n");
				} else {
					declareCode.append("--" + comment + "\n");
				}
				index++;
			}
		}
		return declareCode.toString();
	}

	String[] sysParameters = new String[]{"error_no","error_info","error_id","error_sysinfo","error_pathinfo"};
	
	static List<StandardField> declareVariabls = new ArrayList<StandardField>();
	
	public static String declareVariableCode(IARESProject project ,IARESModule module , Procedure procedure) throws ARESModelException, HSException{
		StringBuffer declareCode = new StringBuffer();
		declareVariabls = new ArrayList<StandardField>();
		ReferenceManager rm = ReferenceManager.getInstance();
		//必须声明的变量
		if(!findInParaList(procedure,"error_no")){
			ReferenceInfo info = rm.getFirstReferenceInfo(project, IMetadataRefType.StdField, "error_no", false);
			if (info!=null){
				StandardField std = (StandardField) info.getObject();
				declareVariabls.add(std);
			}
		}
		if(!findInParaList(procedure,"error_info")){
			ReferenceInfo info = rm.getFirstReferenceInfo(project, IMetadataRefType.StdField, "error_info", false);
			if (info!=null){
				StandardField std = (StandardField) info.getObject();
				declareVariabls.add(std);
			}
		}
		//如果是08版的还要额外增加几个必须的变量
		if(!findInParaList(procedure,"error_id")){
			ReferenceInfo info = rm.getFirstReferenceInfo(project, IMetadataRefType.StdField, "error_id", false);
			if (info!=null){
				StandardField std = (StandardField) info.getObject();
				declareVariabls.add(std);
			}
		}
		if(!findInParaList(procedure,"error_sysinfo")){
			ReferenceInfo info = rm.getFirstReferenceInfo(project, IMetadataRefType.StdField, "error_sysinfo", false);
			if (info!=null){
				StandardField std = (StandardField) info.getObject();
				declareVariabls.add(std);
			}
		}
		if(!findInParaList(procedure,"error_pathinfo")){
			ReferenceInfo info = rm.getFirstReferenceInfo(project, IMetadataRefType.StdField, "error_pathinfo", false);
			if (info!=null){
				StandardField std = (StandardField) info.getObject();
				declareVariabls.add(std);
			}
		}
		EList<Parameter> variables = procedure.getInternalVariables();
		//====================================================================================
		EList<Parameter> quotes = procedure.getInputParameters();
		List<String> stdFieldQuoteName = new ArrayList<String>();
		for (Parameter input : quotes) {
			
			ReferenceInfo info = rm.getFirstReferenceInfo(project, IMetadataRefType.StdField, input.getId(), false);
			if (info == null) {
				continue;
			}
			StandardField std = (StandardField) info.getObject();
			if (std == null) {
				continue;
			}
			
			String fieldType = StringUtils.EMPTY;
			stdFieldQuoteName.add(input.getName());
			String defaultValue = input.getDefaultValue();
			String dt = std.getDataType();
			ReferenceInfo dtInfo = rm.getFirstReferenceInfo(project, IMetadataRefType.BizType, dt, false);
			if (dtInfo != null) {
				BusinessDataType bdt = (BusinessDataType) dtInfo.getObject();
				String stdType = bdt.getStdType();
				ReferenceInfo stdTypeInfo = rm.getFirstReferenceInfo(project, IMetadataRefType.StdType, stdType, false);
				if (stdTypeInfo != null) {
					StandardDataType sdt = (StandardDataType) stdTypeInfo.getObject();
					fieldType = sdt.getValue("oracle");
				}
				if (StringUtils.isBlank(defaultValue)) {
					ReferenceInfo devInfo = rm.getFirstReferenceInfo(project, IMetadataRefType.DefValue, bdt.getDefaultValue(), false);
					if (devInfo != null) {
						TypeDefaultValue tdv = (TypeDefaultValue) devInfo.getObject();
						defaultValue = tdv.getValue("oracle");
					}
				}
			}
			
			String tableFieldType = "hstype." + dt;
			
			if(null != fieldType && fieldType.toLowerCase().contains("char")) {
				declareCode.append("  v_" + input.getId());
				if (input.getFlags().indexOf("IO") != -1) {
					declareCode.append("  in  out  " );
				}else {
					declareCode.append("  ");
				}
				declareCode.append(tableFieldType + "  := nvl(trim(p_" + input.getId() + ")," + defaultValue + ");  ");
			}else {
				declareCode.append("  v_" + input.getId());
				if (input.getFlags().indexOf("IO") != -1) {
					declareCode.append("  in  out  ");
				}else {
					declareCode.append("  ");
				}
				declareCode.append(tableFieldType + "  := nvl(p_" + input.getId() + "," + defaultValue + ");  ");
			}
			
			//添加说明信息
			String comment = input.getDescription();
			if(StringUtils.isBlank(comment) || StringUtils.equalsIgnoreCase(comment, "null")) {
				declareCode.append("--" + std.getChineseName() + "\n");
			}else {
				declareCode.append("--" + comment + "\n");
			}
		}
		
		//====================================================================================
		
		for (Parameter variable : variables) {
			if(!stdFieldQuoteName.contains(variable.getId())) {//去除与输入变量同名的内部变量
				String realType = StringUtils.EMPTY;
				String dataType = StringUtils.EMPTY;
				String chineseName = StringUtils.EMPTY;
				String packageName = StringUtils.EMPTY;
				IARESResource res = null;
				ARESObject object = null;
				String objectType = StringUtils.EMPTY;
				if (variable.getParamType() == ParamType.STD_FIELD) {
					ReferenceInfo info = rm.getFirstReferenceInfo(project, IMetadataRefType.StdField, variable.getId(), false);
					if (info != null) {
						StandardField std = (StandardField) info.getObject();
						dataType = std.getDataType();
						chineseName = std.getChineseName();
						ReferenceInfo dtInfo = rm.getFirstReferenceInfo(project, IMetadataRefType.BizType, std.getDataType(), false);
						if (dtInfo != null) {
							BusinessDataType bdt = (BusinessDataType) dtInfo.getObject();
							String stdType = bdt.getStdType();
							if (StringUtils.isNotBlank(stdType)) {
								ReferenceInfo stdInfo = rm.getFirstReferenceInfo(project, IMetadataRefType.StdType, stdType, false);
								if (stdInfo != null) {
									StandardDataType sdt = (StandardDataType) stdInfo.getObject();
									realType = sdt.getValue("oracle");
								}
							}
						}
					}
				}else if (variable.getParamType() == ParamType.OBJECT) {
					ReferenceInfo info = rm.getFirstReferenceInfo(project, IBizRefType.Object, variable.getType(), false);
					if (info != null) {
						res = info.getResource();
						Object obj = info.getObject();
						if (obj instanceof ARESObject) {
							object = (ARESObject) obj;
							if (object != null) {
								EMap<String, EObject> extendsObj = object.getData2();
								if (extendsObj != null) {
									EObject exo = extendsObj.get("user");
									if (exo instanceof UserExtensibleProperty) {
										objectType = ((UserExtensibleProperty) exo).getMap().get(OBJECT_TYPE);
									}
								}
							}
							chineseName = object.getChineseName();
						}
					}
					dataType = variable.getType();
					
				}else {
					dataType = variable.getType();
					chineseName = variable.getName();
					ReferenceInfo dtInfo = rm.getFirstReferenceInfo(project, IMetadataRefType.BizType, dataType, false);
					if (dtInfo != null) {
						BusinessDataType bdt = (BusinessDataType) dtInfo.getObject();
						String stdType = bdt.getStdType();
						if (StringUtils.isNotBlank(stdType)) {
							ReferenceInfo stdInfo = rm.getFirstReferenceInfo(project, IMetadataRefType.StdType, stdType, false);
							if (stdInfo != null) {
								StandardDataType sdt = (StandardDataType) stdInfo.getObject();
								realType = sdt.getValue("oracle");
							}
						}
					}
				}
			
				if (variable.getParamType() == ParamType.OBJECT) {
					if (object != null) {
						if(StringUtils.equalsIgnoreCase(res.getParent().getElementName(), "hstype") && StringUtils.equalsIgnoreCase(objectType, "record")){
							declareCode.append(handleObjectDataRecordType(object, project, res.getName()));
							declareCode.append("  v_" + variable.getId() + "  " + object.getName() + ";  --"
									+ chineseName + "\n");
						}else {
							declareCode.append("  v_" + variable.getId() + "  " + dataType + ";  --"
									+ chineseName + "\n");
						}
					}
				}else{
					declareCode.append("  v_" + variable.getId() + "  hstype." + dataType + ";  --"
							+ chineseName + "\n");
				}
			}
		}
		for (StandardField std : declareVariabls) {
			String dataType = "hstype." + std.getDataType();
			declareCode.append("  v_" + std.getName() +"  " + dataType + ";  --"
					+  std.getChineseName() + "\n");
		}
		if(!findInParaList(procedure,"error_pathinfo_tmp")){
			ReferenceInfo info = rm.getFirstReferenceInfo(project, IMetadataRefType.StdField, "error_pathinfo", false);
			if (info != null) {
				StandardField std = (StandardField) info.getObject();
				declareCode.append("  v_error_pathinfo_tmp  hstype." +std.getDataType()+";  --错误路径临时变量");
			}
		}
		return declareCode.toString();
	}
	
	public static String initVariable(IARESProject project , Procedure procedure)
			throws HSException {
		StringBuffer initCode = new StringBuffer();
		initCode.append("  v_error_pathinfo := substr(v_error_pathinfo || '-->"+procedure.getName()+"',1,500);\n");
		initCode.append("  v_error_pathinfo_tmp := v_error_pathinfo;\n");
		return initCode.toString();
	}

	public static String initOutputParam(IARESProject project ,Procedure procedure){
		StringBuffer initCode = new StringBuffer();
		EList<Parameter> quotes = procedure.getOutputParameters();
		ReferenceManager rm = ReferenceManager.getInstance();
		if (!procedure.isOutputCollection()) {// 返回结果集为false,才需要对输出参数进行初始化
			for (Parameter quote : quotes) {
				String defValue = quote.getDefaultValue();
				String defType = StringUtils.EMPTY;
				
				ReferenceInfo info = rm.getFirstReferenceInfo(project, IMetadataRefType.StdField, quote.getId(), false);
				if (info != null) {
					StandardField std = (StandardField) info.getObject();
					ReferenceInfo dt = rm.getFirstReferenceInfo(project, IMetadataRefType.BizType, std.getDataType(), false);
					if (dt != null) {
						BusinessDataType bizType = (BusinessDataType) dt.getObject();
						String sdt = bizType.getStdType();
						String dv = bizType.getDefaultValue();
						ReferenceInfo st = rm.getFirstReferenceInfo(project, IMetadataRefType.StdType, sdt, false);
						if (st != null) {
							StandardDataType stt = (StandardDataType) st.getObject();
							defType = stt.getValue("oracle");
						}
						if (StringUtils.isNotBlank(dv)) {
							ReferenceInfo dvInfo = rm.getFirstReferenceInfo(project, IMetadataRefType.DefValue, dv, false);
							if (dvInfo != null) {
								TypeDefaultValue tdv = (TypeDefaultValue) dvInfo.getObject();
								defValue = tdv.getValue("oracle");
							}
						}
					}
				}
				if (StringUtils.indexOf(quote.getFlags(), "IO") == -1) {// 只有输出参数才需要初始化
					if(!defType.equalsIgnoreCase("cursor") || !defValue.equalsIgnoreCase("null")) {
						initCode.append("  p_" + quote.getId() + "  := " + defValue + ";\n");
					}
				}
			}
		}
		return initCode.toString();
	}
	
	public static String initVariableParam(IARESProject project, Procedure procedure) {
		StringBuffer initCode = new StringBuffer();
		EList<Parameter> variables = procedure.getInternalVariables();
		//变量声明中做过初始化的，不再初始化
		EList<Parameter> quotes = procedure.getInputParameters();
		List<String> stdFieldQuoteName = new ArrayList<String>();
		for (Parameter quote : quotes) {
			stdFieldQuoteName.add(quote.getName());
		}
		ReferenceManager rm = ReferenceManager.getInstance();
		for (Parameter variable : variables) {
			if(!stdFieldQuoteName.contains(variable.getId())) {//变量声明中做过初始化的，不再初始化
				String defValue = StringUtils.defaultIfBlank(variable.getDefaultValue(), "null");
				if (variable.getParamType() == ParamType.STD_FIELD) {
					ReferenceInfo info = rm.getFirstReferenceInfo(project, IMetadataRefType.StdField, variable.getId(), false);
					if (info != null) {
						StandardField std = (StandardField) info.getObject();
						ReferenceInfo dt = rm.getFirstReferenceInfo(project, IMetadataRefType.BizType, std.getDataType(), false);
						if (dt != null) {
							BusinessDataType bizType = (BusinessDataType) dt.getObject();
							String dv = bizType.getDefaultValue();
							ReferenceInfo dvInfo = rm.getFirstReferenceInfo(project, IMetadataRefType.DefValue, dv, false);
							if (dvInfo != null) {
								TypeDefaultValue tdv = (TypeDefaultValue) dvInfo.getObject();
								if (StringUtils.isBlank(defValue)) {
									defValue = tdv.getValue("oracle");
								}
							}
						}
					}
				}else {
					ReferenceInfo dt = rm.getFirstReferenceInfo(project, IMetadataRefType.BizType, variable.getType(), false);
					if (dt != null) {
						BusinessDataType bizType = (BusinessDataType) dt.getObject();
						String dv = bizType.getDefaultValue();
						ReferenceInfo dvInfo = rm.getFirstReferenceInfo(project, IMetadataRefType.DefValue, dv, false);
						if (dvInfo != null) {
							TypeDefaultValue tdv = (TypeDefaultValue) dvInfo.getObject();
							if (StringUtils.isBlank(defValue)) {
								defValue = tdv.getValue("oracle");
							}
						}
					}
				}
				
				initCode.append("  v_" + variable.getId() + "  := " + defValue + ";\n");
			}
		}
		
		for(StandardField std : declareVariabls){
			String defValue = StringUtils.EMPTY;
			ReferenceInfo dt = rm.getFirstReferenceInfo(project, IMetadataRefType.BizType, std.getDataType(), false);
			if (dt != null) {
				BusinessDataType bizType = (BusinessDataType) dt.getObject();
				String dv = bizType.getDefaultValue();
				ReferenceInfo dvInfo = rm.getFirstReferenceInfo(project, IMetadataRefType.DefValue, dv, false);
				if (dvInfo != null) {
					TypeDefaultValue tdv = (TypeDefaultValue) dvInfo.getObject();
					defValue = tdv.getValue("oracle");
				}
			}
			initCode.append("  v_" + std.getName() + "  := " + defValue + ";\n");
		}
		return initCode.toString();
	}
	
	// record类型
	private static String createRecordTypeCode = "  TYPE %s IS RECORD( \n"
			+ "  %s" + "  );\n";

	/**
	 * 处理对象数据类型中记录类型数据类型
	 * 
	 * @param object
	 * @param project
	 * @param variableFieldType
	 * @return
	 * @throws HSException
	 */
	private static String handleObjectDataRecordType(ARESObject object,
			IARESProject project, String variableFieldType) throws HSException {
		List<Parameter> fields = object.getProperties();
		StringBuffer fldBuffer = new StringBuffer();
		int index = 1;
		int fldSize = fields.size();
		ReferenceManager rm = ReferenceManager.getInstance();
		for (Parameter field : fields) {

			String dataType = StringUtils.EMPTY;
			if (field.getParamType() == ParamType.STD_FIELD) {
				ReferenceInfo info = rm.getFirstReferenceInfo(project,
						IMetadataRefType.StdField, field.getId(), false);
				if (info == null) {
					continue;
				}
				StandardField std = (StandardField) info.getObject();
				if (std == null) {
					continue;
				}
				dataType = std.getDataType();
			} else {
				dataType = field.getType();
			}
			fldBuffer.append(String.format("  %-15s	%s", field.getId(),
					dataType));
			if (index != fldSize) {
				fldBuffer.append(",");
				index++;
			}
			fldBuffer.append("\n");
		}
		return String.format(createRecordTypeCode, variableFieldType,
				fldBuffer.toString());
	}

	private static boolean findInInOutList(Procedure procedure, String name) {
		for (Parameter input : procedure.getInputParameters()) {
			if (StringUtils.equals(input.getId(), name)) {
				return true;
			}
		}

		for (Parameter output : procedure.getOutputParameters()) {
			if (StringUtils.equals(output.getId(), name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 在输入，输出，内部变量，带声明变量中寻找是否存在某个变量
	 * 
	 * @param procedure
	 *            当前过程
	 * @param name
	 *            要寻找的变量名
	 * @return
	 */
	private static boolean findInParaList(Procedure procedure, String name) {
		name = name.trim();
		for (Parameter input : procedure.getInputParameters()) {
			if (StringUtils.equals(input.getId(), name)) {
				return true;
			}
		}
		for (Parameter output : procedure.getOutputParameters()) {
			if (StringUtils.equals(output.getId(), name)) {
				return true;
			}
		}
		for (Parameter variable : procedure.getInternalVariables()) {
			if (StringUtils.equals(variable.getId(), name)) {
				return true;
			}
		}
		for (StandardField std : declareVariabls) {
			if (StringUtils.equals(std.getName(), name)) {
				return true;
			}
		}
		return false;
	}

}
