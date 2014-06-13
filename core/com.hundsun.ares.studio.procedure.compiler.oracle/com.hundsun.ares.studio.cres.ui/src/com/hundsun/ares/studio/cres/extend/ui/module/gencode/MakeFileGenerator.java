/**
 * 源程序名称：MakeFileGenerator.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.cres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.cres.extend.ui.module.gencode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.cres.extend.cresextend.MoudleDepend;
import com.hundsun.ares.studio.cres.extend.ui.module.gencode.util.ModuleGeneratorHelper;
import com.hundsun.ares.studio.ui.ARESUI;

/**
 * @author sundl
 *
 */
public class MakeFileGenerator {
	
	private static Logger logger = Logger.getLogger(MakeFileGenerator.class);
	static final Logger console = ConsoleHelper.getLogger();

	private Map<String,String> cmdMap = new HashMap<String,String>();//存放命令，注意计算前先清空。
	private Map<String,List<String>> callList = new HashMap<String,List<String>>();//依赖检查，注意计算前先清空。

	private Set<IARESModule> modules = new HashSet<IARESModule>();
	
	private String charset;
	
	public MakeFileGenerator(Set<IARESModule> modules) {
		this.modules = modules;
		IPreferencesService service = Platform.getPreferencesService();
		charset = service.getString(ARESUI.PLUGIN_ID, ARESUI.PRE_GENERATE_CHARSET, StringUtils.EMPTY, null);
	}
	
	public void createMakeFileWithoutFolder(String path){
		cmdMap.clear();//启动后，如果不清空，第二次生成，则全部为空
		callList.clear();//调用记录清空。
		StringBuffer make_all_buffer = new StringBuffer();
		Iterator<IARESModule> ite = modules.iterator();
		while(ite.hasNext()){
			IARESModule module = ite.next();
			make_all_buffer.append(getModuleMakeFileCmdStr(module));
		}
		String fileName = path + "makeall";
		
		//写入文件
		writeToFile(fileName, make_all_buffer.toString(),charset);
		
		String fileName2 = path + "makeclean";
		
		String  make_all_cl = make_all_buffer.toString().replaceAll("flow.gcc ORA_VER=10", "flow.gcc ORA_VER=10 clean");
		//写入文件
		writeToFile(fileName2, make_all_cl,charset);
	}
	
	/**
	 * 将字符内容写入指定文件
	 * @param fileName
	 * @param contant
	 */
	protected void writeToFile(String fileName,String contant,String charset) {
		charset = StringUtils.defaultString(charset, "GB2312");
		try {
			FileUtils.writeStringToFile(new File(fileName), contant, charset);
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	/**
	 * 递归调用
	 * @param module
	 * @return
	 */
	private StringBuffer getModuleMakeFileCmdStr(IARESModule module){
		StringBuffer cmdStr = new StringBuffer();
		
		IARESModule[] submodules = null;
		try{
			submodules = module.getSubModules();//这里已经得到所有子模块，包括嵌套层次结构中的子模块
			if(submodules != null && submodules.length > 0){
				for(int i = 0;i < submodules.length;i++){
					cmdStr.append(getDependencyMakeFileCmdStr(submodules[i]));//由于已经获取了全部子模块，故这里不需再递归，否则会重复
				}
			}
			cmdStr.append(getDependencyMakeFileCmdStr(module));//当前模块及其依赖模块的编译命令
		}catch(Exception ex){
			
		}
		
		return cmdStr;
	}
	
	private StringBuffer getDependencyMakeFileCmdStr(IARESModule module) throws Exception{
		StringBuffer cmdStr = new StringBuffer();
		EList<MoudleDepend> depends = ModuleGeneratorHelper.getCresMoudleExtendProperty(module).getDepends();//模块扩展属性
		for (MoudleDepend moduleDepend : depends) {
			IARESModule dm = getModuleByPath(moduleDepend.getModulePath());//只有依赖模块在当前所要生成的模块列表中，才需要添加编译命令
			if(dm != null){//如果LS模块名与AS模块名同名，由于只判断名称，故会错加依赖
				if(!cmdMap.containsKey(dm.getElementName())){//为防止死循环如果已经添加，则直接跳过处理
					if(!callList.containsKey(dm.getElementName())){
						if(callList.get(dm.getElementName()) != null){
							callList.get(dm.getElementName()).add(module.getElementName());
						}else{
							List<String> callInfo = new ArrayList<String>();
							callInfo.add(module.getElementName());
							callList.put(dm.getElementName(),callInfo);//当前模块已经递归调用，记录后，判断是否重复调用
						}
						StringBuffer cmd = getDependencyMakeFileCmdStr(dm);
						if((cmd.toString() != "") &&(!cmdMap.containsValue(cmd))){
							cmdStr.append(cmd);//要先添加依赖模块的编译命令
							cmdMap.put(dm.getElementName(), cmd.toString());
						}else if((cmd.toString() != "") && cmdMap.containsValue(cmd)){//已经添加相同的编译命令，只有在不同路径下，有相同的模块名时，才可能出现
							
							console.warn(dm.getElementName() + "模块名重复，无法添加GCC命令。");
						}
					}else{
						List<String> callInfo = callList.get(dm.getElementName());
						StringBuffer callbuff = new StringBuffer();
						for(int i_call = 0;i_call < callInfo.size();i_call++){
							callbuff.append(callInfo.get(i_call) + "->" + dm.getElementName() + ";");
							List<String> becall = callList.get(callInfo.get(i_call));
							for(int i_becall = 0;i_becall < becall.size();i_becall++){
								callbuff.append(becall.get(i_becall) + "->" + callInfo.get(i_call) + ";");
							}
						}
						console.warn(dm.getElementName() + "模块存在循环调用，请检查以下路径：" + callbuff + "。");
					}
					
				}
			}
		}
		if(!cmdMap.containsKey(module.getElementName())){//不重复添加
			
			String cmd = getCmdStr(module);
			if((cmd != "") && (!cmdMap.containsValue(cmd))){
				cmdStr.append(cmd);//添加完依赖模块的编译命令，添加当前模块的编译命令
				cmdMap.put(module.getElementName(), cmd);
			}else if((cmd != "") && (cmdMap.containsValue(cmd))){//已经添加相同的编译命令，只有在不同路径下，有相同的模块名时，才可能出现
				String overlap_path = getOverLapKey(cmd);//查找重复的模块名
				console.warn(module.getElementName() + "模块名与" + overlap_path + "重复，无法添加GCC命令。");
			}
		}
		return cmdStr;
	}
	
	private String getCmdStr(IARESModule module){
		//本模块资源
		IARESResource[] mRess = module.getARESResources();
		if(mRess.length > 0) {//有资源才生成
			if(mRess.length == 1 && StringUtils.equals(mRess[0].getType(), "module.xml")) {//有可能是模块属性
				//无资源，则不生成
			}else {
				String root_type = module.getRoot().getType();
				if(root_type.equalsIgnoreCase("com.hundsun.ares.studio.atom.resources.atomroot")){
					return "make -f s_as_" + module.getShortName() + "flow.gcc ORA_VER=10\n";//注意这里是Linux下的换行符，一定要用\n，否则批量运行makeall会有问题				
				}else if(root_type.equalsIgnoreCase("com.hundsun.ares.studio.logic.resources.logicroot")){
					return "make -f s_ls_" + module.getShortName() + "flow.gcc ORA_VER=10\n";//注意这里是Linux下的换行符，一定要用\n，否则批量运行makeall会有问题
				}else{
					//该类型不需处理
				}
			}
		}
		return "";
	}
	
	private IARESModule getModuleByPath(String path){
		Iterator<IARESModule> ite = this.modules.iterator();
		while(ite.hasNext()){
			IARESModule module = ite.next();
			if(StringUtils.equals(module.getElementName(), path)){
				return module;//当前列表中存在，则直接返回
			}
			IARESModule[] submodules = null;
			try{
				submodules = module.getSubModules();//当前模块列表中不存在，则在子模块中查找,已获取所有嵌套子模块
				if(submodules != null && submodules.length > 0){
					for(int j =0;j < submodules.length;j++){
						IARESModule submodule = submodules[j];
						if(StringUtils.equals(submodule.getElementName(), path)){
							return submodule;
						}
					}
				}
			}catch(Exception ex){
				
			}			
		}
		return null;//找不到返回空
	}
	
	private String getOverLapKey(String cmd){
		Set<String> keys = cmdMap.keySet();
		Iterator<String> ite = keys.iterator();
		while(ite.hasNext()){
			String key = ite.next();
			String overlap_cmd = cmdMap.get(key);
			if(StringUtils.equals(overlap_cmd, cmd)){
				return key;
			}
		}
		return "";
	}
	
}
