/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.procedure.ui.extend.gencode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.constants.IBizResType;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.model.UserExtensibleProperty;
import com.hundsun.ares.studio.engin.exception.HSException;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.procdure.BizType;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.constants.IProcedureResType;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * 包头包体文件生成器
 * @author qinyuan
 * @version 1.0
 * @history
 */
public class PackageGenerator {
	private IARESProject project;
	// 模块
	
	/**包头定义*/
	private static String PACKAGE_HEAD_CODE = "create or replace package %s authid %s\n" +
			"is\n" +
			"%s" +
			"end %s;\n" +
			"/";
	/**包体定义*/
	private static String PACKAGE_BODY_CODE = "create or replace package body %s\n" +
	"is\n" +
	"%s" +
	"end %s;\n" +
	"/";
	/**包头函数定义*/
	private static String PACKAGE_HEAD_FUNCTION_CODE = "FUNCTION %s(\n" +
			"%s\n" +
			")RETURN %s";
	/**包头过程定义*/
	private static String PACKAGE_HEAD_PROCEDURE_CODE = "procedure %s(\n" +
	"%s\n" +
	")";
	
	///////////////////////////对象数据类型
	//record类型
	private static String createRecordTypeCode = "TYPE %s IS RECORD( \n"+
	"%s" +
	");\n\n";
	private static String createRecordFieldCode = "%-10s	hstype.%s";

	//object类型
	private static String createObjectTypeCode = "TYPE  %s  is table of  object(\n"+
	" %s \n"+
	");\n\n";
	private static String createOtherTypeCode = "TYPE  %s  is table of  %s;\n\n";
	
	//分号和换行
	private final static String SEMICOLON_AND_NEW_LINE = ";\n\n";

	public PackageGenerator(IARESProject project){
		this.project = project;
	}
	
	/**
	 * 对象数据类型文件中与模块（包）同名的包信息
	 * @param moduleName 模块名对应对象数据类型中的包名
	 * @return
	 * @throws ARESModelException 
	 */
	private String getObjectDataTypeCode(IARESModule procedureModule) throws ARESModelException {
		IARESModuleRoot root = project.getModuleRoot("objects");
		String moduleName = procedureModule.getElementName();
		IARESModule module = root.getModule(moduleName);
		if (module == null || !module.exists()) {
			moduleName = getModuleChineseName(procedureModule);
			if (StringUtils.isNotBlank(moduleName)) {
				for(IARESModule m : root.getModules()){
					String tempMn = getModuleChineseName(m);
					if (StringUtils.equals(tempMn, moduleName)) {
						module = m;
						break;
					}
				}
			}
			if (module == null || !module.exists()) {
				return StringUtils.EMPTY;
			}
		}
		
		// 对象数据类型中有相同的包定义，也需要生成到当前包文件中。
		StringBuffer typeBuffer = new StringBuffer();
		for (IARESResource res : module.getARESResources(IBizResType.Object, true)) {
			ARESObject object = res.getInfo(ARESObject.class);
			if (object == null) {
				continue;
			}
			EList<Parameter> fields = object.getProperties();
			StringBuffer fldBuffer = new StringBuffer();
			String objectType = StringUtils.EMPTY;
			EMap<String, EObject> extendsObj = object.getData2();
			if (extendsObj != null) {
				EObject exo = extendsObj.get("user");
				if (exo instanceof UserExtensibleProperty) {
					objectType = ((UserExtensibleProperty) exo).getMap().get(ProcedureGeneratorHelper.OBJECT_TYPE);
				}
			}
			int index = fields.size();
			if (StringUtils.equalsIgnoreCase(objectType, "RECORD")) {
				for (int i=0;i<index;i++) {
					Parameter field = fields.get(i);
					String dataType = field.getType();
					if (field.getParamType() == ParamType.STD_FIELD) {
						ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(project, IMetadataRefType.StdField, field.getId(), false);
						if (info != null) {
							StandardField std = (StandardField) info.getObject();
							dataType = std.getDataType();
						}
					}else if (field.getParamType() == ParamType.NON_STD_FIELD) {
						dataType = field.getType();
					}
					String fi = field.getId();
					if (StringUtils.isBlank(fi)) {
						fi = StringUtils.substringAfterLast(dataType, ".");
						if (StringUtils.isBlank(fi)) {
							fi = dataType;
						}
					}
					fldBuffer.append(String.format(createRecordFieldCode,fi,dataType));
					if(i < index -1){
						fldBuffer.append(",");
					}
					fldBuffer.append("\n");
				}
				typeBuffer.append(String.format(createRecordTypeCode,res.getName(),fldBuffer.toString()));
			}else if(StringUtils.equalsIgnoreCase(objectType, "OBJECT")) {//对象
				for (int i=0;i<index;i++) {
					Parameter field = fields.get(i);
					fldBuffer.append(field.getName());
					if(i < index -1){
						fldBuffer.append(",");
					}
				}
				typeBuffer.append(String.format(createObjectTypeCode,res.getName(),fldBuffer.toString()));
			}else {
				typeBuffer.append(String.format(createOtherTypeCode,res.getName(),objectType));
			}
		}
		return typeBuffer.toString();
	}
	
	private String getModuleChineseName(IARESModule module){
		String moduleName = StringUtils.EMPTY;
		IARESResource property = module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
		if (property != null && property.exists()) {
			try {
				ModuleProperty info = property.getInfo(ModuleProperty.class);
				if (info != null) {
					Object obj = info.getValue(ICommonModel.CNAME);
					if (obj != null) {
						moduleName = obj.toString();
					}
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		return moduleName;
	}
	
	/**
	 *	将代码生成到文件
	 * @param returnCodeMap
	 */
	private void printCodeToFile(String pathAndFileName ,String code,String charset) {

		File codeFile = new File(pathAndFileName);
		try {
			if (!codeFile.exists()) {
				codeFile.createNewFile();
			}
			if(StringUtils.isBlank(charset)){
				charset = "GB2312";
			}
			FileOutputStream os = new FileOutputStream(codeFile);
			OutputStreamWriter wrinter = new OutputStreamWriter(os, charset);
			PrintWriter out = new PrintWriter(wrinter);
			try {
				out.print(code);
			} catch (Exception ex) {
				out.println(ex.getMessage());
			}
			out.flush();
			out.close();
			os.close();
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		}
	
	}
	
	/**
	 * 生成单个包头包体模块代码
	 * @param project
	 * @param module
	 * @param path
	 * @param authId
	 * @throws ARESModelException 
	 */
	public void generateCode(IARESProject project,IARESModule module,String path , String authId ,String charset) throws ARESModelException{
		StringBuffer pkgBuffer = new StringBuffer();//包定义信息
		StringBuffer headBuffer = new StringBuffer();//包头信息
		StringBuffer bodyBuffer = new StringBuffer();//包体信息
		String moduleName = module.getElementName();//模块名,也即包名
//		 对象数据类型中有相同的包定义，也需要生成到当前包文件中。
		headBuffer.append(getObjectDataTypeCode(module));
		
		//模块过程
		IARESResource[] procReses = module.getARESResources(IProcedureResType.PROCEDURE, true);
		for (IARESResource procRes : procReses) {
			Procedure procedure = procRes.getInfo(Procedure.class);
			//包头处理
			StringBuffer paras = new StringBuffer();//参数定义
			
			//内部变量
			StringBuffer varParas = new StringBuffer();
			
			//2012年5月22日16:46:28 变量初始化 包括：输出参数初始化、变量初始、init_pathinfo
			StringBuffer initVarParams = new StringBuffer();
			
			try {
				//输入参数
				paras.append(ProcedureGeneratorHelper.declareInputCode(project, procedure));
				
				//输出参数
				paras.append(ProcedureGeneratorHelper.declareOutputCode(project, procedure));
				
				//内部变量
				varParas.append(ProcedureGeneratorHelper.declareVariableCode(project,module , procedure));
				
				//输出参数初始化引擎
				initVarParams.append(ProcedureGeneratorHelper.initOutputParam(project, procedure));
				//内部变量初始化引擎
				initVarParams.append(ProcedureGeneratorHelper.initVariableParam(project, procedure));
				//init_pathinfo初始化引擎
				initVarParams.append(ProcedureGeneratorHelper.initVariable(project, procedure));
				
			} catch (HSException e) {
				e.printStackTrace();
			}
			
			String funcCode = "";
			
			if(StringUtils.equals(procedure.getBizType(), BizType.FUNCTION.getLiteral())){
				funcCode = String.format(PACKAGE_HEAD_FUNCTION_CODE,procedure.getName(), paras.toString(),procedure.getReturnType());
				headBuffer.append(funcCode);
				
				//包体处理
				bodyBuffer.append(funcCode);
				bodyBuffer.append("\nIS\n");
				bodyBuffer.append(varParas.toString());
				bodyBuffer.append("\nBEGIN\n");
				bodyBuffer.append(initVarParams);
				bodyBuffer.append("\n");
				bodyBuffer.append(procedure.getPseudoCode());
				bodyBuffer.append("\nEND " + procRes.getName());
			}else {
				funcCode = String.format(PACKAGE_HEAD_PROCEDURE_CODE,procRes.getName(), paras.toString());
				headBuffer.append(funcCode);
				
				//包体处理
				bodyBuffer.append(funcCode);
				bodyBuffer.append("\nis\n");
				bodyBuffer.append(varParas.toString());
				bodyBuffer.append("\nBEGIN\n");
				bodyBuffer.append(initVarParams);
				bodyBuffer.append("\n");
				bodyBuffer.append(procedure.getPseudoCode());
				bodyBuffer.append("\nEND " + procRes.getName());
			}
			
			//加换行
			headBuffer.append(SEMICOLON_AND_NEW_LINE);
			bodyBuffer.append(SEMICOLON_AND_NEW_LINE);
			
			//2012年5月22日17:20:34 @替换
			replaceAtStandField(bodyBuffer, procedure, project);
			
		}
		pkgBuffer.append(String.format(PACKAGE_HEAD_CODE, moduleName,authId,headBuffer.toString(),moduleName));
		pkgBuffer.append("\n\n");
		pkgBuffer.append(String.format(PACKAGE_BODY_CODE, moduleName,bodyBuffer.toString(),moduleName));
		pkgBuffer.append("\n\n");
		
		//生成文件
		String chname = getModuleChineseName(module);
		if (StringUtils.isBlank(chname)) {
			chname = module.getShortName();
		}
		File codeFolder = new File(path);
		if (!codeFolder.exists()) {
			codeFolder.mkdirs();
		}
		String pathName = path + chname + ".sql";
		printCodeToFile(pathName,pkgBuffer.toString(), charset);
	}
	 
	public static StringBuffer replaceAtStandField(StringBuffer resultCode, Procedure procedure, IARESProject project) {
		EList<Parameter> params_in = procedure.getInputParameters();
		for (Parameter input : params_in) {
			replace(resultCode, input ,"p");
		}
		
		EList<Parameter> outputs = procedure.getOutputParameters();
		for (Parameter output : outputs) {
			if(!procedure.isOutputCollection()||output.getFlags().indexOf("IO")!=-1){
				replace(resultCode, output, "p");
			}
		}
		
		EList<Parameter> variables = procedure.getInternalVariables();
		for (Parameter var : variables) {
			replace(resultCode, var, "v");
		}
		
		return resultCode;
	}
	
	public static void replace(StringBuffer code, Parameter p , String prov) {
		String SQL_BEGIN = "EXEC SQL";
		String SQL_END = ";";

		int var_start, var_end = 0, sql_start, sql_end;		
		
		while (true) {
			//2011年8月1日10:22:44二部要求 单引号内的@符号不进行p_，v_变量转译
			Pattern pattern = Pattern.compile("(@" + p.getId() + "[^\\w\\d_])");
			Matcher m = pattern.matcher(code.subSequence(var_end, code.length() - 1));
			var_start = m.find() ? m.toMatchResult().start() + var_end : -1;
			if (var_start == -1) {
				break;
			}
			var_end = var_start + 1 + p.getId().length();

			sql_end = code.lastIndexOf(SQL_END, var_start);
			sql_start = code.indexOf(SQL_BEGIN, sql_end);
			
			// var_start var_end 
			if (sql_start == -1 || sql_start > var_end ) {
				code.replace(var_start, var_end, prov + "_" + p.getId());
			} else {
				code.replace(var_start, var_end, ":" + prov + "_" + p.getId());
			}
		}
	}
	
}
