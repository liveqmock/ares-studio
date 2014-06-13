/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.compiler.token;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseRefType;
import com.hundsun.ares.studio.jres.database.utils.DBTableGenCodeUtils;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.RelatedInfo;
import com.hundsun.ares.studio.procedure.compiler.constant.IProcedureEngineContextConstant;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * 生成过程关联表信息
 * @author qinyuan
 *
 */
public class ProcedureRelatedTableInfoCodeToken implements ICodeToken {

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
		
		Procedure procedure = (Procedure)context.get(IProcedureEngineContextConstant.ResourceModel);
		IARESProject project = (IARESProject)context.get(IProcedureEngineContextConstant.Aresproject);
		//关联表
		EList<RelatedInfo> relatedTables = procedure.getRelatedTableInfo();
		if(relatedTables.size() <= 0) {
			return StringUtils.EMPTY;
		}
		
		result.append(NEWLINE);
		result.append(begin_code_desc_begin);
		for (RelatedInfo relatedInfo : relatedTables) {
			result.append(genRelatedTableCode(project, relatedInfo));
		}
		result.append(NEWLINE);
		result.append(begin_code_desc_end);
		result.append(NEWLINE);
		return result.toString();
	}
	
	private String genRelatedTableCode(IARESProject project,RelatedInfo relatedInfo) {
		StringBuffer ret = new StringBuffer();
		String tableName = relatedInfo.getId();
		ReferenceInfo referneceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project, IDatabaseRefType.Table, tableName, true);
		
		if(referneceInfo != null) {
			TableResourceData table = (TableResourceData)referneceInfo.getObject();
			IARESResource resource = referneceInfo.getResource();
			ret.append(DBTableGenCodeUtils.genTableFullCode(resource, table));
		}
		
		return ret.toString();
	}
	
	private final static String begin_code_desc_begin = 
		"/*****************************************************/\r\n" +
		"/*   关联表生成代码 --开始                                              */\r\n" +
		"/*****************************************************/\r\n";

	private final static String begin_code_desc_end = 
		"/*****************************************************/\r\n" +
		"/*   关联表生成代码 --结束                                              */\r\n" +
		"/*****************************************************/\r\n";
}
