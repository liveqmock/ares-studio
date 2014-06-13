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
public class NestPackAddValueToken  implements ICodeToken{
	private String param1;//打包对象
	private String param2;//打包字段
	public NestPackAddValueToken(String param1,String param2){
		this.param1 = param1;
		this.param2 = param2;
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
		if(StringUtils.equals("lpOutPacker", param1)){
			code.append(""+param1+"->AddRaw("+"p_"+param2+", pi_"+param2+");\r\n");
		}else{
			code.append("v_"+param1+"->AddRaw("+"p_"+param2+", pi_"+param2+");\r\n");
		}
		
		return code.toString();
	}
}
