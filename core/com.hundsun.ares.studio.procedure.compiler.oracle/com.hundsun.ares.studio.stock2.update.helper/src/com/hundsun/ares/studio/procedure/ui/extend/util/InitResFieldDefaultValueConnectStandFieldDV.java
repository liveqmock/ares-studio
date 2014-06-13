/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.extend.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.UserExtensibleProperty;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.StandardFieldList;
import com.hundsun.ares.studio.procdure.Procedure;

/**
 * 资源字段关联标准字段默认值
 *			如果资源字段（表字段、过程输入输出参数）默认值不存在，
 *			而使用的标准字段有默认值，则将字段
 *			默认值关联为标准字段的默认值
 * @author qinyuan
 *
 */
public class InitResFieldDefaultValueConnectStandFieldDV {
	
	public static void initFieldDefaultValue(IARESProject project,StringBuffer msg, IProgressMonitor monitor) {
		
		try {
			Map<String, String> stdFldAndDftValue = getStdFldAndDftValue(project,msg);
			//表
			IARESResource[] tables = project.getResources(new String[]{"table"});
			//过程
			IARESResource[] procs = project.getResources(new String[]{"procedure"});
			
			monitor.beginTask("", tables.length + procs.length);
			
			//表
			for (IARESResource table : tables) {
				if(monitor.isCanceled()) {
					break;
				}
				monitor.setTaskName("初始化表" + table.getElementName());
				TableResourceData info = table.getInfo(TableResourceData.class);
				EList<TableColumn> columns = info.getColumns();//表字段
				boolean needSave = false;
				for (TableColumn tableColumn : columns) {
					if(StringUtils.isBlank(tableColumn.getDefaultValue())){//表字段默认值为空
						if(stdFldAndDftValue.containsKey(tableColumn.getColumnName())){
							tableColumn.setDefaultValue(stdFldAndDftValue.get(tableColumn.getColumnName()));
							needSave = true;
							msg.append("表("+info.getName() +")" + info.getChineseName() +
									"的表字段[" +tableColumn.getColumnName() +  "]的默认值初始化为{"
									+stdFldAndDftValue.get(tableColumn.getColumnName())+ "};\n");
						}
					}
				}
				if(needSave) {
					table.save(info, true, null);
				}
				monitor.worked(1);
			}
			
			//过程
			for (IARESResource res : procs) {
				if(monitor.isCanceled()) {
					break;
				}
				monitor.setTaskName("初始化过程" + res.getElementName());
				boolean needSave = false;
				Procedure proc = res.getInfo(Procedure.class);
				EList<Parameter> inputParams = proc.getInputParameters();//输入参数
				for (Parameter parameter : inputParams) {
					if(StringUtils.isBlank(parameter.getDefaultValue())) {
						if(stdFldAndDftValue.containsKey(parameter.getId())){
							parameter.setDefaultValue(stdFldAndDftValue.get(parameter.getId()));
							needSave = true;
							msg.append("过程("+proc.getName() +")" + proc.getChineseName() +
									"的输入参数[" +parameter.getId() +  "]的默认值初始化为{"
									+stdFldAndDftValue.get(parameter.getId())+ "};\n");
						}
					}
				}
				
				EList<Parameter> outputParams = proc.getOutputParameters();//输出参数
				for (Parameter parameter : outputParams) {
					if(StringUtils.isBlank(parameter.getDefaultValue())) {
						if(stdFldAndDftValue.containsKey(parameter.getId())){
							parameter.setDefaultValue(stdFldAndDftValue.get(parameter.getId()));
							needSave = true;
							msg.append("过程("+proc.getName() +")" + proc.getChineseName() +
									"的输出参数[" +parameter.getId() +  "]的默认值初始化为{"
									+stdFldAndDftValue.get(parameter.getId())+ "};\n");
						}
					}
				}
				if(needSave){
					res.save(proc, true, null);
				}
				monitor.worked(1);
			}
			monitor.done();
		} catch (ARESModelException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Map<String, String> getStdFldAndDftValue(IARESProject project,StringBuffer msg) throws Exception{
		Map<String, String> stdFldAndDftValue = new HashMap<String, String>();
		
		IARESResource[] ress = project.getResources("stdfield.stdfield");
		for (IARESResource res : ress) {
			StandardFieldList list = res.getInfo(StandardFieldList.class);
			EList<StandardField> items = list.getItems();
			for (StandardField item : items) {
				UserExtensibleProperty userExtend = (UserExtensibleProperty)item.getData2().get("user");//用户扩展属性
				String dv = userExtend.getMap().get("dv");//默认值
				if(!StringUtils.isBlank(dv)){
					stdFldAndDftValue.put(item.getName(), dv);
				}
			}
		}
		
//		try {
//			IFile file = project.getProject().getFile("standardFieldDefaultValue.txt");
//			InputStreamReader in = new InputStreamReader(file.getContents());
//			BufferedReader reader = new BufferedReader(in);
//			String ss = reader.readLine();
//			while(ss != null) {
//				if(!StringUtils.isBlank(ss)){
//					String[] keyvaluse = ss.split("=");
//					stdFldAndDftValue.put(keyvaluse[0].trim(), keyvaluse[1].trim());
//				}
//				ss = reader.readLine();
//			}
//			reader.close();
//			in.close();
//		} catch (FileNotFoundException e) {
//			msg.append("<错误>：找不到文件：standardFieldDefaultValue.txt");
//			e.printStackTrace();
//		} catch (IOException e) {
//			msg.append(e.getMessage());
//			e.printStackTrace();
//		} catch (CoreException e) {
//			msg.append(e.getMessage());
//			e.printStackTrace();
//		}
		
		return stdFldAndDftValue;
	}
}
