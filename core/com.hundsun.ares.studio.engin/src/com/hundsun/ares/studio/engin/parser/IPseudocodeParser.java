package com.hundsun.ares.studio.engin.parser;

import java.util.Iterator;
import java.util.Map;

import com.hundsun.ares.studio.engin.token.ICodeToken;

public interface IPseudocodeParser {

	/**
	 * 
	 * @param pseudocode  伪代码
	 * @param i 
	 * @param context     上下文
	 * @return
	 */
	public Iterator<ICodeToken> parse(String pseudocode,int commentType, Map<Object, Object> context)throws Exception;
	
}
