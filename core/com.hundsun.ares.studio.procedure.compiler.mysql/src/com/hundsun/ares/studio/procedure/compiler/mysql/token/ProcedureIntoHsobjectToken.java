/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.mysql.token;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.core.scripting.AresScriptEngineManager;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleResType;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleDBService;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleSpace;
import com.hundsun.ares.studio.jres.database.oracle.service.ITableSpace;
import com.hundsun.ares.studio.jres.script.engin.ScriptUtils;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.constants.IProcedureConstant;
import com.hundsun.ares.studio.procedure.compiler.mysql.constant.IProcedureEngineContextConstantMySQL;

/**
 * 将过程写入数据库sql脚本
 * <br>此处二部也有特殊处理情况，需注意
 * 
 * @author qinyuan
 *
 */
public class ProcedureIntoHsobjectToken implements ICodeToken {
	
	IARESResource resource;
	
	public ProcedureIntoHsobjectToken(IARESResource resource){
		this.resource = resource;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getContent()
	 */
	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#getType()
	 */
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		Procedure procedure = (Procedure) context.get(IProcedureEngineContextConstantMySQL.ResourceModel);
		if(StringUtils.isBlank(procedure.getDatabase())){
			throw new Exception("数据库不能为空！");
		}
		//脚本文件
		IARESResource jsResource = getJSResource();
		//优先使用绑定的脚本实现
		if(jsResource != null) {
			return genCodeByScript(context,jsResource);
		}
		
		IOracleDBService oracleService = DataServiceManager.getInstance().getService((IARESProject)context.get(IProcedureEngineContextConstantMySQL.Aresproject), IOracleDBService.class);
		
		String dbuser = StringUtils.EMPTY;
		String english = procedure.getName();
		String objId = procedure.getObjectId();
		String dbName = procedure.getDatabase();
		String version = StringUtils.defaultIfBlank(RevisionHistoryUtil.getMaxVersion(procedure.getHistories()), "1.0.0.0");
		
		try{
			IOracleSpace os = oracleService.getSpace(IOracleResType.Space);
			if (os != null) {
				ITableSpace ts = os.getTableSpace(procedure.getDatabase());
				if (ts != null) {
					dbuser = ts.getUser();
					if(StringUtils.isNotBlank(dbuser)){
						dbuser += ".";
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		List<String> params = new ArrayList<String>();
		for(String v : StringUtils.split(version, "\\.")){
			params.add(v);
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n");
		sb.append(String.format("DELETE FROM %1$shsobjects WHERE object_name = \'", dbuser));
		sb.append(english);
		sb.append("\' AND object_type = \'P\';\r\n");
		sb.append(String.format("INSERT into %1$shsobjects (\r\n",dbuser));
		sb.append("\t\tobject_id,        object_name,        own_base,        object_type,\r\n");
		sb.append("\t\t master_ver,        second_ver,        third_ver,        build_ver)\r\n");
		sb.append("\tVALUES(\r\n");
		sb.append("\t\t" + objId + ",");
		sb.append("\t\t'" + english + "',");
		sb.append("\t\t'" + dbName + "',");
		sb.append("\t\t\'P',\r\n");
		
		if(params.size() == 4){
			for (int i = 0; i < params.size(); i++) {
				sb.append("\t\t" + params.get(i));
				if(i < params.size() - 1){
					sb.append(",");
				}
			}
		}else {
			sb.append("\t\t1,");
			sb.append("\t\t0,");
			sb.append("\t\t0,");
			sb.append("\t\t0");
		}
		sb.append(");\r\n");
		sb.append("\r\n");
		sb.append("\r\n");
		
		return sb.toString();
	}
	
	//通过读取固定脚本方法来实现最终代码
	private String genCodeByScript(Map<Object, Object> context,IARESResource jsResource) {
		StringBuffer ret = new StringBuffer();
		
		IOracleDBService oracleService = DataServiceManager.getInstance().getService((IARESProject)context.get(IProcedureEngineContextConstantMySQL.Aresproject), IOracleDBService.class);
		//过程模型
		Procedure procedure = (Procedure) context.get(IProcedureEngineContextConstantMySQL.ResourceModel);
		
		String dbuser = StringUtils.EMPTY;
		String version = StringUtils.defaultIfBlank(RevisionHistoryUtil.getMaxVersion(procedure.getHistories()), StringUtils.EMPTY);
		
		try{
			IOracleSpace os = oracleService.getSpace(IOracleResType.Space);
			if (os != null) {
				ITableSpace ts = os.getTableSpace(procedure.getDatabase());
				if (ts != null) {
					dbuser = ts.getUser();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//获取脚本引擎
		ScriptEngine engine = AresScriptEngineManager.getScriptEngineManager().getEngineByName(ARESCore.SCRIPT_ENGINE_NAME);
		InputStream stream = null;
		try {
			stream = jsResource.openStream();
			String script = null;
			IResource jsRes = jsResource.getResource();
			
			if (jsRes instanceof IFile) {
				script = IOUtils.toString(stream, ((IFile) jsRes).getCharset());
			} else {
				script = IOUtils.toString(stream, "UTF-8");
			}
			// 创建默认脚本上下文
			Map<String, Object> defaultContext = ScriptUtils.createDefaultScriptContext(ScriptUtils.MODE_EDITOR_BUTTON,jsResource, resource,procedure, getClass().getClassLoader());
			defaultContext.put("dbuser", dbuser);
			defaultContext.put("version", version);
			defaultContext.put("procedure", procedure);
			
			engine.setBindings(ScriptUtils.toScriptBindings(defaultContext), ScriptContext.ENGINE_SCOPE);
			engine.eval(script);
			
			//执行脚本方法
			ret.append(((Invocable) engine).invokeFunction("genCodeEnd",defaultContext));
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(stream);
		}
		return ret.toString();
	}

	
	/**
	 * 获取指定的脚本文件
	 * @return
	 * 	不存在返回null
	 */
	private IARESResource getJSResource() {
		try {
			IARESProjectProperty property = resource.getARESProject().getProjectProperty();
			String fileName = property.getString(IProcedureConstant.PROC_REG_HSOBJECT_DIR);
			if (StringUtils.isNotBlank(fileName)) {
				IFile file = resource.getARESProject().getProject().getFile(fileName);
				return (IARESResource) ARESCore.create(file);
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return null;
		
//		IFile file = resource.getARESProject().getProject().getFile("tools/Stock2/proc_gencode.js");
//		if(file.isAccessible()) {
//			return (IARESResource) ARESCore.create(file);
//		}
//		return null;
	}
}
