/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.token;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.compiler.constant.IAtomEngineContextConstant;
import com.hundsun.ares.studio.atom.compiler.skeleton.util.MarkConfig;
import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.token.ICodeToken;

/**
 * @author liaogc
 *
 */
public class CommonSelectStatementToken implements ICodeToken{
	public static final String NL = ITokenConstant.NL;
	private String resultSetVarName ;//结果集变量名
	private String sql;//sql语句
	public CommonSelectStatementToken(String resultSetVarName,String sql){
		this.resultSetVarName = resultSetVarName;
		this.sql = StringUtils.defaultIfBlank(sql, "");
		
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
		StringBuffer code = new StringBuffer();
		boolean flagR = false;
		Object obj = context.get(IAtomEngineContextConstant.ResourceModel);
		if (obj instanceof BizInterface && StringUtils.equalsIgnoreCase(((BizInterface) obj).getInterfaceFlag(), com.hundsun.ares.studio.engin.constant.MarkConfig.MARK_IFLAG_R)) {
			flagR = true;
		}
		if (!flagR) {
			if(!isSqlVariable(context)){//如果是变量,如:@sql
				code.append(resultSetVarName).append(MarkConfig.MARK_BLANK).append(MarkConfig.MARK_EQUAL).append(MarkConfig.MARK_BLANK).append(MarkConfig.MARK_BLANK).append("lpConn->executeQuery").append(MarkConfig.MARK_LEFTSIGN).append(MarkConfig.MARK_QUOTATION).append(sql).append(MarkConfig.MARK_QUOTATION).append(MarkConfig.MARK_RIGHTSIGN).append(";").append(NL);
			}else{
				code.append(resultSetVarName).append(MarkConfig.MARK_BLANK).append(MarkConfig.MARK_EQUAL).append(MarkConfig.MARK_BLANK).append(MarkConfig.MARK_BLANK).append("lpConn->executeQuery").append(MarkConfig.MARK_LEFTSIGN).append(sql).append(MarkConfig.MARK_RIGHTSIGN).append(";").append(NL);
			}
			code.append("if ").append(MarkConfig.MARK_LEFTSIGN).append(resultSetVarName).append(MarkConfig.MARK_RIGHTSIGN);
		}
		return code.toString();
	}
	/**
	 * 判断是否是变量
	 * @param context
	 * @return
	 */
	private boolean isSqlVariable(Map<Object, Object> context){
		List<String> popVarList = (List<String>)context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		if(StringUtils.startsWith(sql, "@")){
			if(popVarList.contains(StringUtils.remove(sql, "@"))){//为代码中有这个变量
				return true;
			}
		}
		return false;
	}
	
}
