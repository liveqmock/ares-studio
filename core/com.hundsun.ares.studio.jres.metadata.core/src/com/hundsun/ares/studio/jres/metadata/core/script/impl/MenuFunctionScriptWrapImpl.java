/**
 * 
 */
package com.hundsun.ares.studio.jres.metadata.core.script.impl;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.Function;
import com.hundsun.ares.studio.jres.script.api.metadata.IMenuFunctionScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;

/**
 * @author yanwj06282
 *
 */
public class MenuFunctionScriptWrapImpl extends CommonScriptWrap<Function> implements
		IMenuFunctionScriptWrap {

	public MenuFunctionScriptWrapImpl(Function function ,IARESResource resource) {
		super(function ,resource);
	}

	@Override
	public String getName() {
		return StringUtils.EMPTY;
	}
	
	@Override
	public String getChineseName() {
		return StringUtils.EMPTY;
	}
	
	@Override
	public String getDescription() {
		return getOriginalInfo().getDescription();
	}

	@Override
	public String getFunctionCode() {
		return getOriginalInfo().getName();
	}

	@Override
	public String getSubTransCode() {
		return getOriginalInfo().getSubTransCode();
	}

	@Override
	public String getFunctionName() {
		return getOriginalInfo().getChineseName();
	}

	@Override
	public String getServiceName() {
		return getOriginalInfo().getName();
	}

	@Override
	public void setFunctionCode(String functionCode) {
		getOriginalInfo().setName(functionCode);
	}

	@Override
	public void setFunctionName(String functionName) {
		getOriginalInfo().setChineseName(functionName);
	}

	@Override
	public void setServiceName(String serviceName) {
		getOriginalInfo().setName(serviceName);
	}

	@Override
	public void setSubTransCode(String subTransCode) {
		getOriginalInfo().setSubTransCode(subTransCode);
	}
	
}
