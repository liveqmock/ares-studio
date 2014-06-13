package com.hundsun.ares.studio.atom.compiler.macro.handlers;

import com.hundsun.ares.studio.engin.token.ITokenDomain;



public class DefaultTokenDomain implements ITokenDomain{

	String key;
	Object[] args;
	String type = ITokenDomain.GLOABL;
	
	public DefaultTokenDomain(String key,Object[] args){
		this.key = key;
		this.args = args;
	}
	
	public DefaultTokenDomain(String key,String type,Object[] args){
		this.key = key;
		this.args = args;
		this.type = type;
	}
	
	@Override
	public String getKey() {
		return key;
	}

	@Override
	public Object[] getArgs() {
		return args;
	}

	@Override
	public String getType() {
		return type;
	}

	
}
