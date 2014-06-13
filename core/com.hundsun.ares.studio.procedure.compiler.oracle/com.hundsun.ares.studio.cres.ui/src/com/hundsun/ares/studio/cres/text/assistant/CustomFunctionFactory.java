/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.text.assistant;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.cres.extend.cresextend.FileDefine;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.ModuleGeneratorHelper;
import com.hundsun.ares.studio.ui.console.ARESConsoleFactory;

/**
 * @author wangxh
 *
 */
public class CustomFunctionFactory {
	//公共函数，key为文件相对路径，list为函数列表
	private final static  Map<String, List<String>> funcMap = new HashMap<String, List<String>>();
	//struct形式的函数，key为文件相对路径，value中的key为struct定义的第一句（即类似struct IF2Packer : public IKnown），list为对应struct下的函数列表
	private final static Map<String, Map<String, List<String>>> structMap = new HashMap<String, Map<String, List<String>>>();
	/***
	 * 头文件含类似 一下形式的匹配
	 * struct IF2Packer : public IKnown
	 * {
	 * .....
	 * } 
	 */
	private final static Pattern STRUCT_PATTERN = Pattern.compile(
			"^struct\\s+[a-zA-Z]+[a-zA-Z0-9_]*\\s*:\\s*public\\s+[a-zA-Z]+[a-zA-Z0-9_]*[\r\n]+\\{[\\w\\W]+\\};",Pattern.MULTILINE);
	
	private static Logger logger = ConsoleHelper.getLogger();
	
	public static CustomFunctionFactory eINSTANCE = new CustomFunctionFactory();
	
	private CustomFunctionFactory(){}
	
	//获取所有的公共函数
	public List<String> getAllPublicFunctions(IARESProject project){
		List<String> funcs = new ArrayList<String>();
		try {
			EList<FileDefine> funcDefines = ModuleGeneratorHelper.getCresProjectExtendProperty(project).getFuncDefine();
			for(FileDefine define : funcDefines){
				if(define.isIsUsed()){
					funcs.addAll(getFunctions(define.getValue(), project));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return funcs;
		}
		return funcs;
	}
	
	//获取所有struct下的函数（只能在->后提示）
	public Map<String, List<String>> getAllStructMap(IARESProject project){
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		try {
			EList<FileDefine> funcDefines = ModuleGeneratorHelper.getCresProjectExtendProperty(project).getFuncDefine();
			for(FileDefine define : funcDefines){
				if(define.isIsUsed()){
					map.putAll(getStructMap(define.getValue(), project));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return map;
		}
		return map;
	}
	
	//获取指定文件下的struct下定义的函数
	private Map<String,List<String>> getStructMap(String path, IARESProject project) {
		String key = getKey(path, project);
		if(!structMap.containsKey(key)){
			parseFile(path,project);
		}
		return structMap.get(key);
	}

	//获取指定文件下定义的公共函数
	public List<String> getFunctions(String path,IARESProject project){
		String key = getKey(path, project);
		if(!funcMap.containsKey(key)){
			parseFile(path,project);
		}
		return funcMap.get(key);
	}

	/**
	 * @param path
	 * @param project 
	 * 解析文件（这里默认头文件只能是定义公共函数或者struct下的函数）
	 */
	private void parseFile(String path, IARESProject project) {
		String text = getFileContent(path, project);
		
		List<String> structs = new ArrayList<String>();
		Matcher structMatcher = STRUCT_PATTERN.matcher(text);
		
		while(structMatcher.find()){
			structs.add(structMatcher.group());
		}
		
		String key = getKey(path, project);
		//有struct则公共函数列表为空，反之则struct下的函数列表为空
		if(structs.size()>0){
			structMap.put(key,parseStruct(structs));
			funcMap.put(key, new ArrayList<String>());
		}else{
			structMap.put(key, new HashMap<String, List<String>>());
			funcMap.put(key,parseFunctions(text));
		}
		String message = String.format("刷新头文件%s里定义的公共函数成功！",path);
		logger.info(message);
	}
	
	//解析struct下的所有函数
	private Map<String, List<String>> parseStruct(List<String>structs){
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		//获取第一行当key
		Pattern pattern = Pattern.compile("^struct\\s+[a-zA-Z]+[a-zA-Z0-9_]*\\s*:\\s*public\\s+[a-zA-Z]+[a-zA-Z0-9_]*");
		for(String struct : structs){
			Matcher matcher = pattern.matcher(struct);
			if(matcher.find()){
				map.put(matcher.group(), parseFunctions(struct));
			}
		}
		return map;
	}
	
	
	private List<String> parseMacros(String text) {
		List<String> funcs = new ArrayList<String>();
		//宏定义 #define FEQ(xasx,csa) ssdsd的形式
		String regex = "^#define\\s+\\w+(\\(.*\\))?\\s+[\\(\\)a-zA-Z0-9_]+";

		Pattern pattern = Pattern.compile(regex,Pattern.MULTILINE);
		
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()){
			String group = matcher.group();
			String regex1 = "^#define\\s+\\w+(\\(.*\\))?\\s+";
			Matcher matcher1 = Pattern.compile(regex1).matcher(group);
			if(matcher1.find()){
				funcs.add(StringUtils.replace(matcher1.group(), "#define", "").trim());
			}
		}
		return funcs;
	}

	private List<String> parseFunctions(String text){
		List<String> funcs = new ArrayList<String>();
		funcs.addAll(parseMacros(text));
		//函数作用域返回类型定义，大小写字母开头，后面可以接大小写数字下划线*号和空格
		String regex = "^\\s*[a-zA-Z]+[a-zA-Z0-9_\\*\\s]*";
		//函数名定义，大小写开头，后面可接大小写数字下划线，再后面可接空格
		regex += "[a-zA-Z]+[a-zA-Z0-9_]*\\s*";
		//参数定义，左小括号开头，右小括号加分号结尾，括号中间情况比较复杂，简单处理成匹配除换行符外的任意字符
		regex+= "\\(.*\\)\\s*";
		Pattern pattern = Pattern.compile(regex,Pattern.MULTILINE);
		
		Matcher matcher = pattern.matcher(text);
		while(matcher.find()){
			funcs.add(getDispalyString(matcher.group()).trim());
		}
		
		return funcs;
	
	}
	/**
	 * @param line
	 * @return
	 */
	private String getDispalyString(String line) {
		//函数名加参数匹配
		Pattern pattern = Pattern.compile("\\b\\s*[a-zA-Z]+[a-zA-Z0-9_]*\\s*\\(.*\\)");
		Matcher matcher = pattern.matcher(line);
		if(matcher.find()){
			String pre = matcher.group();
			int preIndex = matcher.start();
			return pre + " : " + line.substring(0, preIndex).trim();
		}
		return line;
	}
	
	//获取文件内容
	private String getFileContent(String path,IARESProject project){
		String text = "";
		FileInputStream in = null;
		try {
			IFile file = project.getProject().getFile(path);
			if(file.exists()){
				in = new FileInputStream(file.getLocation().toFile());
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				String line = reader.readLine();
				while(line != null){
					text += line;
					text += "\r\n";
					line = reader.readLine();
				}
			}else{
				ARESConsoleFactory.openARESConsole();
				String message = String.format("项目属性中定义的公共函数头文件%s不存在！",path);
				logger.info(message);
				throw new Exception(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			IOUtils.closeQuietly(in);
		}
		return text;
	}

	private String getKey(String path,IARESProject project){
		return project.getElementName() + "/" + path;
	}
	
	public void removeFuncs(String path,IARESProject project){
		String key = getKey(path, project);
		funcMap.remove(key);
		structMap.remove(key);
	}
	
	public void clear(){
		funcMap.clear();
		structMap.clear();
	}
}
