/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.cres.extend.ui.module.gencode.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.model.ModuleRevisionHistoryList;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.cres.extend.core.constants.ICresExtendConstants;
import com.hundsun.ares.studio.cres.extend.cresextend.CresMoudleExtendProperty;
import com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty;
import com.hundsun.ares.studio.cres.extend.cresextend.CresextendFactory;
import com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend;

/**
 * 生成器工具类
 * @author 
 * @version 1.0
 * @history
 */
public class ModuleGeneratorHelper {
	
	public static final String FILE_OUTPUT_LOCATION = "com.hundsun.ares.studio.preference.fileoutputlocation";
	
	private static Pattern pCppMethodSignature = Pattern.compile("(//[^\\r\\n]*\\r?\\n\\s*)?int\\s+FUNCTION_CALL_MODE\\s+\\w+\\(IAS2Context \\* lpContext,IF2UnPacker \\* lpInUnPacker,IF2Packer \\* lpOutPacker\\)");
		//Pattern.compile("(//[^\\r\\n]*\\r?\\n\\s*)?int\\s+\\w+\\s+F\\d+\\(.*?\\)");
	private static String FORMAT_ALL = 
		"#ifndef %1$s \n" +
		"#define %1$s \n" +
		"%2$s \n" +
		"%3$s\n" +
		"#endif /* %1$s */\r\n";
	
	private static String FORMAT_INCLUDE =
		"#include \"%1$s\" \n";
	
	/**
	 * 资源统计格式化
	 */
	private static String RES_HEADER_FORMAT = "*%-15s%-50s%-50s%-100s*";
	
	/**
	 * 模块代码生成资源统计信息
	 * @param infos
	 * @return
	 */
	public static StringBuffer getResourceStatisticsInfo(List<ModuleResourceStatisticsInfo> infos) {
		StringBuffer ret = new StringBuffer();
		//按功能好排序
		Collections.sort(infos,new Comparator<ModuleResourceStatisticsInfo>() {
			@Override
			public int compare(ModuleResourceStatisticsInfo o1,
					ModuleResourceStatisticsInfo o2) {
				return o1.getObjectID().compareTo(o2.getObjectID());
			}
		});
		
		ret.append("/");
		ret.append(String.format(RES_HEADER_FORMAT, "***************","*****************************************","*****************************************","********************************************************************************"));
		ret.append("\r\n");
		ret.append(String.format(RES_HEADER_FORMAT, " 对象号","英文名","中文名","说明信息"));
		ret.append("\r\n");
		ret.append(String.format(RES_HEADER_FORMAT, "---------------","-----------------------------------------","-----------------------------------------","---------------------------------------------------------------------------------"));
		ret.append("\r\n");

		for (ModuleResourceStatisticsInfo info : infos) {
			ret.append(String.format(RES_HEADER_FORMAT, " "+info.getObjectID(),
					info.getName(),info.getcName(),info.getDesc().replaceAll("\n", "").replaceAll("\r", "")));
			ret.append("\r\n");
		}
		ret.append(String.format(RES_HEADER_FORMAT, "*******************","*****************************************","*****************************************","********************************************************************************"));
		ret.append("/");
		ret.append("\r\n");
		
		return ret;
	}

	/**
	 * 获取首选项的生成文件路径
	 * 
	 */
	public static String getFileOutPutPath() {
		String id = "com.hundsun.ares.studio.ui.jres_gen_path";

		int index = StringUtils.lastIndexOf(id, '.');
		if(index <= 0 || index == id.length()){
			return null;
		}
		
		String qualifer = StringUtils.substring(id,0, index);
		String key = StringUtils.substring(id,index + 1, id.length());
		String text = Platform.getPreferencesService().getString(qualifer, key, "", null); 
		return text;
	}
	
	/**
	 * 写文件的包含信息
	 * 
	 * @param sb
	 * @param includes
	 */
	public static void writeIncludeSection(StringBuffer sb, List<String> includes) {

		HashSet<String> includeSet = new HashSet<String>();
		includeSet.addAll(includes);
		
		sb.append("\n");
		for (String file : includeSet) {
			sb.append(String.format(FORMAT_INCLUDE, file));
		}

	}
	
	/**
	 * 获取模块的父模块路径 （英文名路径）
	 * <br>例如：
	 * 模块关系 A->B->C,传入C模块：
	 * 返回：A\B
	 * @param module
	 * @return
	 * @throws Exception
	 */
	public static String getParentModulePath(IARESModule module) throws Exception{
		List<IARESModule> parents = new ArrayList<IARESModule>();
		getParentModules(module, parents);
		int parentSize = parents.size();
		if(parentSize > 0){//存在父模块
			StringBuffer path = new StringBuffer();
			for (int i = parentSize - 1; i >= 0; i--) {
				path.append(parents.get(i).getShortName());
				path.append("\\");
			}
			return path.toString();
		}
		return "";
	}
	
	/**
	 * 获取模块的父模块路径 (中文名路径，指定分隔符)
	 * <br>例如：
	 * 模块关系 A->B->C,传入C模块：
	 * @param module
	 * @segment 分隔符 
	 * @return 分隔符如-、返回A-B；
	 * @throws Exception
	 */
	public static String getParentModuleCnamePath(IARESModule module,String segment) throws Exception{
		List<IARESModule> parents = new ArrayList<IARESModule>();
		getParentModules(module, parents);
		int parentSize = parents.size();
		if(parentSize > 0){//存在父模块
			StringBuffer path = new StringBuffer();
			for (int i = parentSize - 1; i >= 0; i--) {
				path.append(getModuleProperty(parents.get(i)).getString(ICommonModel.CNAME));
				path.append(segment);
			}
			return path.toString();
		}
		return "";
	}
	
	/**
	 * 获取模块的父模块路径 (中文名路径)
	 * <br>例如：
	 * 模块关系 A->B->C,传入C模块：
	 * 返回：A\B
	 * @param module
	 * @return
	 * @throws Exception
	 */
	public static String getParentModuleCnamePath(IARESModule module) throws Exception{
		List<IARESModule> parents = new ArrayList<IARESModule>();
		getParentModules(module, parents);
		int parentSize = parents.size();
		if(parentSize > 0){//存在父模块
			StringBuffer path = new StringBuffer();
			for (int i = parentSize - 1; i >= 0; i--) {
				path.append(getModuleProperty(parents.get(i)).getString(ICommonModel.CNAME));
				path.append("\\");
			}
			return path.toString();
		}
		return "";
	}
	
	/**
	 * 获取模块的父模块路径 (中文名路径)
	 * 带指定前缀、如逻辑_、原子_等
	 * <br>例如：
	 * 模块关系 A->B->C,传入C模块：
	 * 返回：A\B
	 * @param module
	 * @return
	 * @throws Exception
	 */
	public static String getParentModuleCnamePathWithPrefix(IARESModule module,String prefix) throws Exception{
		List<IARESModule> parents = new ArrayList<IARESModule>();
		getParentModules(module, parents);
		int parentSize = parents.size();
		if(parentSize > 0){//存在父模块
			StringBuffer path = new StringBuffer();
			for (int i = parentSize - 1; i >= 0; i--) {
				path.append(prefix + getModuleProperty(parents.get(i)).getString(ICommonModel.CNAME));
				path.append("\\");
			}
			return path.toString();
		}
		return "";
	}
	
	/**
	 * 获取模块的所有父模块
	 * @param module
	 * @param parents
	 */
	public static void getParentModules(IARESModule module,List<IARESModule> parents){
		IARESModule parent = module.getParentModule();
		if(null != parent) {
			parents.add(parent);
			getParentModules(parent, parents);
		}
	}
	
	/**
	 * 获取代码生成路径
	 * @param module
	 * @return
	 */
	public static String getModuleGenCodePath(IARESProject project) {
		String path = getFileOutPutPath();
		if (path == null || path.isEmpty()) {
			path = "c:\\generate\\";
		} else {
			if (!(path.endsWith("\\") || path.endsWith("/"))) {
				path += "\\";
			}
		}
		// 2014-3-27 sundl TASK #9687 首选项增加Cres、UFT代码生成目录   CRES业务逻辑生成代码目录自动创建“CRES”子目录
		// 2014-4-14 sundl TASK #9885 存储过程工具中，模块代码生成时，不需要加CRES
		return path = path + project.getElementName() + File.separator;
	}
	
	/**
	 * 获取所有依赖项
	 * 
	 * @param module
	 * @return
	 */
	public static List<MoudleDepend> getAllDepends(IARESModule module) throws Exception{
		List<MoudleDepend> depends = new ArrayList<MoudleDepend>();
		getAllDepends(module,depends);
		return depends;
	}

	private static void getAllDepends(IARESModule module, List<MoudleDepend> depends) throws Exception{
		EList<MoudleDepend> mds = getCresMoudleExtendProperty(module).getDepends();
		depends.addAll(mds);
		for (MoudleDepend md : mds) {
			//根据moudleDepend找到对应的模块
			for (IARESModule m : module.getARESProject().getModules()) {
				if(StringUtils.equals(m.getElementName(), md.getModulePath())){
					getAllDepends(m, depends);
					break;
				}
			}
		}
	}

	
	/**
	 * 获取cres模块扩展属性
	 * @param module
	 * @return
	 * @throws Exception
	 */
	public static CresMoudleExtendProperty getCresMoudleExtendProperty(IARESModule module) throws Exception{
		ModuleProperty mp = getModuleProperty(module);
		Object mProperty = mp.getMap().get(ICresExtendConstants.CRES_EXTEND_MOUDLE_PROPERTY);
		if(mProperty instanceof CresMoudleExtendProperty) {
			return (CresMoudleExtendProperty)mProperty;
		}
		return CresextendFactory.eINSTANCE.createCresMoudleExtendProperty();
	}
	
	/**
	 * 获取cres工程扩展属性
	 * @param module
	 * @return
	 * @throws Exception
	 */
	public static CresProjectExtendProperty getCresProjectExtendProperty(IARESModule module) throws Exception{
		return getCresProjectExtendProperty(module.getARESProject());
	}
	
	/**
	 * 获取cres工程扩展属性
	 * @param project
	 * @return
	 * @throws Exception
	 */
	public static CresProjectExtendProperty getCresProjectExtendProperty(IARESProject project) throws Exception{
		Object pProperty = project.getProjectProperty().getMap().get(ICresExtendConstants.CRES_EXTEND_PROJECT_PROPERTY);
		if(pProperty instanceof CresProjectExtendProperty) {
			return (CresProjectExtendProperty)pProperty;
		}
			return CresextendFactory.eINSTANCE.createCresProjectExtendProperty();
	}
	
	/**
	 * 获取模块属性
	 * @param module
	 * @return
	 * @throws Exception
	 */
	public static ModuleProperty getModuleProperty(IARESModule module) throws Exception{
		IARESResource res = module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
		return res.getInfo(ModuleProperty.class);
	}
	
	public static void writeStartupMethods(StringBuffer sb, String ProjectVersion) {
		sb.append("const char *  ASFC_CALL_MODE GetLibVersion()\n");
		sb.append("{\n");
		sb.append("return \"");
		sb.append(ProjectVersion);
		sb.append("\";\n");
		sb.append("}\n");
		sb.append("void  ASFC_CALL_MODE OnLoad(IAppContext * pContext,char * arg)\n");
		sb.append("{\n");
		sb.append("//@todo 在此插入响应组件加载事件代码\n");
		sb.append("}\n");
		sb.append("void  ASFC_CALL_MODE OnUnload(IAppContext * pContext)\n");
		sb.append("{\n");
		sb.append("//@todo 在此插入响应组件卸载事件代码\n");
		sb.append("}\n");
		sb.append("void  ASFC_CALL_MODE OnStart(IAppContext * pContext,char * arg)\n");
		sb.append("{\n");
		sb.append("//@todo 在此插入响应AS启动事件代码\n");
		sb.append("}\n");
		sb.append("void  ASFC_CALL_MODE OnStop(IAppContext * pContext)\n");
		sb.append("{\n");
		sb.append("//@todo 在此插入响应AS停止事件代码\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("\n");
	}
	
	public static void writeStartupMethods2(StringBuffer sb, String ProjectVersion) {
		sb.append("char *  FUNCTION_CALL_MODE GetLibVersion()\n");
		sb.append("{\n");
		sb.append("return \"");
		sb.append(ProjectVersion);
		sb.append("\";\n");
		sb.append("}\n");
		sb.append("void  FUNCTION_CALL_MODE OnLoad(char * arg)\n");
		sb.append("{\n");
		sb.append("//@todo 在此插入响应组件加载事件代码\n");
		sb.append("}\n");
		sb.append("void  FUNCTION_CALL_MODE OnUnload()\n");
		sb.append("{\n");
		sb.append("//@todo 在此插入响应组件卸载事件代码\n");
		sb.append("}\n");
		sb.append("void  FUNCTION_CALL_MODE OnStart()\n");
		sb.append("{\n");
		sb.append("//@todo 在此插入响应AS启动事件代码\n");
		sb.append("}\n");
		sb.append("void  FUNCTION_CALL_MODE OnStop()\n");
		sb.append("{\n");
		sb.append("//@todo 在此插入响应AS停止事件代码\n");
		sb.append("}\n");
		sb.append("\n");
		sb.append("\n");
	}
	
	/**
	 * 写入中间件枚举方法1.0
	 * 
	 * @param sb
	 * @param businesses
	 */
	public static void writeMiddlewareEnumerateMethod(StringBuffer sb, List<BasicResourceInfo> businesses) {
		sb.append("int ASFC_CALL_MODE GetCompsInfo(int Index, LPFUNC_INFO  ppv )\n");
		writeMiddlewareEnumerateMethodbody(sb, businesses);
	}
	
	/**
	 * 写入中间件枚举方法2.0
	 * 
	 * @param sb
	 * @param businesses
	 */
	public static void writeMiddlewareEnumerateMethod2(StringBuffer sb, List<BasicResourceInfo> businesses) {
		sb.append("int FUNCTION_CALL_MODE GetBizFunctionsInfo(int Index, LPBIZ_FUNC_INFO  ppv )\n");
		writeMiddlewareEnumerateMethodbody(sb, businesses);
	}
	
	private static void writeMiddlewareEnumerateMethodbody(StringBuffer sb, List<BasicResourceInfo> businesses) {

		sb.append("{\n");
		sb.append("int iReturnCode = ASFC_EXISTS;\n");
		sb.append("switch (Index)\n");
		sb.append("{\n");
		for (int i = 0; i < businesses.size(); i++) {
			sb.append("case ");
			sb.append(i);
			sb.append(":\n");
			sb.append("if  (ppv!=NULL)\n");
			sb.append("{\n");
			// ppv->dwFunctionNo = 433001;
			sb.append("ppv->dwFunctionNo = ");
			sb.append(businesses.get(i).getObjectId());
			sb.append(";\n");
			// ppv->iVersion = 20070821;
			sb.append("ppv->iVersion = ");
			sb.append(new SimpleDateFormat("yyyyMMdd").format(new Date()));

			// String ver = services.get(i).getVersion();
			// if (ver == null || ver.isEmpty()) {
			// sb.append(0);
			// } else {
			// if (ver.indexOf(".") != -1) {
			// String vv[] = ver.split("\\.");
			// sb.append(vv[vv.length - 1]);
			// } else {
			// sb.append(ver);
			// }
			// }

			sb.append(";\n");
			// ppv->ReqProc = F433001;
			sb.append("ppv->ReqProc = ");
			sb.append("F" + businesses.get(i).getObjectId());
			sb.append(";\n");
			// ppv->AnsProc = NULL;
			sb.append("ppv->AnsProc = NULL;\n");
			// ppv->Caption = "服务_客户基本信息表";
			sb.append("ppv->Caption = \"");
			sb.append(businesses.get(i).getChineseName());
			sb.append("\";\n");
			sb.append("}\n");
			sb.append("break;\n");
		}
		// default:iReturnCode = ASFC_NONE;
		sb.append("default:iReturnCode = ASFC_NONE;\n");
		sb.append("}\n");
		sb.append("return iReturnCode;\n");
		sb.append("}\n");
	}

	/**
	 * 根据cpp文件产生头文件
	 * 
	 * @param Cppbody
	 * @param headerFileName
	 * @param includeFiles
	 * @param defaults
	 * @return
	 */
	public static StringBuffer generateHeaderFile(StringBuffer Cppbody, String headerFileName, String includeFiles[], String defaults[]) {

		String macroName = headerFileName.toUpperCase().replaceAll("\\.", "_");
		macroName = "_" + macroName + "_";
		
		StringBuffer include = new StringBuffer();
		if (includeFiles != null) {
			for (String file : includeFiles) {
				include.append( String.format(FORMAT_INCLUDE, file) );
			}
		}
		
		StringBuffer body = new StringBuffer();
		Matcher m = pCppMethodSignature.matcher(Cppbody);
		int iFindPostion = 0;
		while (m.find(iFindPostion)) {
			MatchResult result = m.toMatchResult();
			body.append("#ifdef __cplusplus \nextern \"C\" { \n#endif \n\n");
			if (defaults != null) {
				String function = result.group();
				int beginIndex = function.indexOf('(') + 1;
				int endIndex = function.lastIndexOf(')');
				
				String s1 = function.substring(0, beginIndex);
				String s2 = function.substring(beginIndex, endIndex);
				String s3 = function.substring(endIndex);
				
				String splitString[] = s2.split(",");
				for (int i = 0; i < defaults.length; i++) {
					splitString[splitString.length - 1 - i ] += " = " + defaults[i];
				}
				body.append(s1);
				body.append(splitString[0]);
				for (int i = 1; i < splitString.length; i++) {
					body.append(", ");
					body.append(splitString[i]);
				}

				body.append(s3);
			} else {
				body.append(result.group());
			}
			body.append(";\n");
			body.append("#ifdef __cplusplus \n} \n#endif\n\n");
			iFindPostion = result.end();
		}
		
		return new StringBuffer(String.format(FORMAT_ALL, macroName, include, body));
	}
	
	/**
	 * 写入文件注释头信息
	 * 
	 * @param commentHeader
	 * @param fileName
	 * @param date
	 */
	public static void writeCommentHeader(StringBuffer sb, String commentHeader, String fileName, String moduleName, Date date) {
		if(commentHeader == null) return;
		// 支持宏替换 $(FILE) $(DATE) $(MODULE)
		commentHeader = commentHeader.replaceAll("\\$\\(FILE\\)", fileName);
		commentHeader = commentHeader.replaceAll("\\$\\(MODULE\\)", moduleName);
		String theDate = new SimpleDateFormat("yyyy/MM/dd").format(date);
		commentHeader = commentHeader.replaceAll("\\$\\(DATE\\)", theDate);
		
		sb.append(commentHeader);
		sb.append("\n");
	}
	
	/**
	 * 获取模块修改记录
	 * @param module
	 * @return
	 * @throws Exception
	 */
	protected static List<RevisionHistory> getModuleHistorys(IARESModule module) throws Exception{
		ModuleProperty property = ModuleGeneratorHelper.getModuleProperty(module);//模块属性
		//模块修改记录
		ModuleRevisionHistoryList modRev = (ModuleRevisionHistoryList) property.getMap().get(Constants.RevisionHistory.MODULE_REVISION_EXT_KEY);
		
		if(null != modRev)
		{
			return modRev.getHistories();
		}else {
			return new ArrayList<RevisionHistory>();
		}
	}
	
	/**
	 * 对修改记录按版本号排序
	 * @param hiss
	 */
	protected static void sortHistoryByVersion(List<RevisionHistory> hiss) {
		Collections.sort(hiss, new Comparator<RevisionHistory>() {

			@Override
			public int compare(RevisionHistory log1, RevisionHistory log2) {
				if(log1.getVersion().compareToIgnoreCase(log2.getVersion()) != 0){
					if(log1.getVersion().trim().isEmpty()){
						return 1;
					}
					if(log2.getVersion().trim().isEmpty()){
						return -1;
					}
					String[] v1 = log1.getVersion().split("\\.");
					String[] v2 = log2.getVersion().split("\\.");
					int i = 0;
					while(i < v1.length && i < v2.length){
						if(v1[i].compareToIgnoreCase(v2[i]) != 0){
							try{
								return Integer.valueOf(removeFirst(v2[i])) - Integer.valueOf(removeFirst(v1[i]));
							}catch(Exception e){
								return removeFirst(v1[i]).compareToIgnoreCase(removeFirst(v2[i]));
							}
						}else{
							i++;
						}
					}
					return 1;
				}else{
						return -1;
				}
			}
		});
	}

	protected static String removeFirst(String str){
		for(char c:str.toCharArray()){
			if(c<'0' || c > '9'){
				str = str.substring(1);
			}else{
				break;
			}
		}
		return str;
	}
}
