/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.token;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.engin.token.ICodeToken;

/**
 * @author liaogc
 *
 */
public class NestObjectResultsetToken implements ICodeToken{
	
	private String parent;//外层对象
	private String child;//内层对象
	
	public NestObjectResultsetToken(String parent,String child){
		this.parent  = parent;
		this.child = child;
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
		code.append("p_"+child+" = v_"+parent+"ResultSet->GetRaw(\""+child+"\""+",&pi_"+child+");\r\n");
		code.append("v_"+child+"ResultSet = lpPackService->GetUnPacker("+"p_"+child+",pi_"+child+");\r\n");
		return code.toString();
	}

}
