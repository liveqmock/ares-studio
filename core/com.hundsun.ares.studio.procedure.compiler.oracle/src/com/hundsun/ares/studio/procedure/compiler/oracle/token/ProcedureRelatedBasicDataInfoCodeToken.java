/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.oracle.token;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.scripting.AresScriptEngineManager;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicDataRestypes;
import com.hundsun.ares.studio.jres.basicdata.core.basicdata.BasicDataBaseModel;
import com.hundsun.ares.studio.jres.basicdata.ui.editor.BasicDataGenCodeUtils;
import com.hundsun.ares.studio.jres.model.metadata.Operation;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataScriptUtil;
import com.hundsun.ares.studio.jres.script.engin.ScriptUtils;
import com.hundsun.ares.studio.jres.script.util.impl.ScriptInUtilImpl;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.RelatedInfo;
import com.hundsun.ares.studio.procedure.compiler.oracle.constant.IProcedureEngineContextConstantOracle;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * 生成过程关联基础数据
 * @author qinyuan
 *
 */
public class ProcedureRelatedBasicDataInfoCodeToken implements ICodeToken {

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
		return CODE_TEXT;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ICodeToken#genCode(java.util.Map)
	 */
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		StringBuffer result = new StringBuffer();
		
		Procedure procedure = (Procedure)context.get(IProcedureEngineContextConstantOracle.ResourceModel);
		IARESProject project = (IARESProject)context.get(IProcedureEngineContextConstantOracle.Aresproject);
		//关联表
		EList<RelatedInfo> relatedTables = procedure.getRelatedBasicDataInfo();
		if(relatedTables.size() <= 0) {
			return StringUtils.EMPTY;
		}
		
		result.append(NEWLINE);
		result.append(begin_code_desc_begin);
		for (RelatedInfo relatedInfo : relatedTables) {
			result.append(genRelatedBasicDataCode(project, relatedInfo));
		}
		result.append(NEWLINE);
		result.append(begin_code_desc_end);
		result.append(NEWLINE);
		return result.toString();
	}
	
	private String genRelatedBasicDataCode(IARESProject project,RelatedInfo relatedInfo) {
		StringBuffer ret = new StringBuffer();
		String tableName = relatedInfo.getId();
		ReferenceInfo referneceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project, IBasicDataRestypes.singleTable, tableName, true);
		
		if(referneceInfo != null) {
			IARESResource resource = referneceInfo.getResource();
			Object obj = referneceInfo.getObject();
			if(obj instanceof BasicDataBaseModel){
				BasicDataBaseModel eObj = (BasicDataBaseModel)obj;
				//安装模式
				ret.append(BasicDataGenCodeUtils.genBasicDataFullCode(resource, eObj));
				//升级模式
				ret.append(BasicDataGenCodeUtils.genBasicDataPatchCode(resource, eObj));
			}
		}
		
		return ret.toString();
	}
	
	private final static String begin_code_desc_begin = 
		"/*****************************************************/\r\n" +
		"/*   关联基础数据生成代码 --开始                                              */\r\n" +
		"/*****************************************************/\r\n";

	private final static String begin_code_desc_end = 
		"/*****************************************************/\r\n" +
		"/*   关联基础数据生成代码 --结束                                              */\r\n" +
		"/*****************************************************/\r\n";
}
