/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.macro;

import com.hundsun.ares.studio.engin.token.ITokenDomain;

/**
 * @author zhuyf
 *
 */
public class TokenDomain implements ITokenDomain {
	
	String key;
	
	Object[] args;
	
	public TokenDomain(String key,Object[] args){
		this.key = key;
		this.args = args;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ITokenDomain#getType()
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ITokenDomain#getKey()
	 */
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return this.key;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ITokenDomain#getArgs()
	 */
	@Override
	public Object[] getArgs() {
		// TODO Auto-generated method stub
		return this.args;
	}

}
