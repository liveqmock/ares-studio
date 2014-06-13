/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.jres.script.api.metadata.IErrorItemScriptWrap;


/**
 * @author yanwj06282
 *
 */
public class ErrorItemScriptWrapImpl extends MetadataItemScriptWrapImpl implements IErrorItemScriptWrap {

	private ErrorNoItem errorItem;
	
	public ErrorItemScriptWrapImpl(ErrorNoItem item, IARESResource resource) {
		super(item, resource);
		this.errorItem = item;
	}

	@Override
	public String getName() {
		return getOriginalInfo().getName();
	}

	public String getChineseName(){
		return getOriginalInfo().getChineseName();
	}

	@Override
	public ErrorNoItem getOriginalInfo() {
		return errorItem;
	}

	@Override
	public String getErrorNo() {
		return getOriginalInfo().getNo();
	}

	@Override
	public String getErrorInfo() {
		return getOriginalInfo().getMessage();
	}

	@Override
	public String getErrorLevel() {
		return getOriginalInfo().getLevel();
	}

	@Override
	public String getCnstName() {
		return getOriginalInfo().getConstantName();
	}
	
}
