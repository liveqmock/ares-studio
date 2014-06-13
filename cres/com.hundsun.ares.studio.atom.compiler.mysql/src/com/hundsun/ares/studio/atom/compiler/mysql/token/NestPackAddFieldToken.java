/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.token;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.engin.token.ICodeToken;

/**
 * @author liaogc
 *
 */
public class NestPackAddFieldToken implements ICodeToken{
	private String parentPackName;//打包对象
	private String paramName;//被打包字段(别名)
	private String packName ;//真实的被打段字段值
	public NestPackAddFieldToken(String parentPackName,String paramName,String packName){
		this.parentPackName = parentPackName;
		this.paramName = paramName;
		this.packName = packName;
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
		if(StringUtils.equals("lpOutPacker", parentPackName)){
			code.append(""+parentPackName+"->AddField(\""+paramName+"\", 'R',"+"pi_"+packName+");\r\n");
		}else{
			code.append("v_"+parentPackName+"->AddField(\""+paramName+"\", 'R',"+"pi_"+packName+");\r\n");
		}
		
		return code.toString();
	}

}
