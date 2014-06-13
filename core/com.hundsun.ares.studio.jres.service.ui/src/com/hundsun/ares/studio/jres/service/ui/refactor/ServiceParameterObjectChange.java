package com.hundsun.ares.studio.jres.service.ui.refactor;

import org.apache.commons.lang.StringUtils;
import org.eclipse.ltk.core.refactoring.Change;

import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.service.Service;
import com.hundsun.ares.studio.ui.refactoring.AresResourceChange;

/**
 * 服务接口中对象类型的参数，改变对象全名的Change对象
 * @author sundl
 *
 */
public class ServiceParameterObjectChange extends AresResourceChange{
	
	private String oldObjName;
	private String newObjName;

	public ServiceParameterObjectChange(IARESResource resource, String oldObjName, String newObjName) {
		super(resource);
		this.oldObjName = oldObjName;
		this.newObjName = newObjName;
	}

	@Override
	protected Class<?> getInfoClassType() {
		return Service.class;
	}

	@Override
	protected Change createUndoChange() {
		return new ServiceParameterObjectChange(resource, newObjName, oldObjName);
	}

	@Override
	protected void modifyInfo(Object info) {
		Service service = (Service) info;
		for(Parameter p : service.getInterface().getInputParameters()) {
			if (p.getParamType() == ParamType.OBJECT && StringUtils.equals(p.getType(), oldObjName)) {
				p.setType(newObjName);
			}
		}
		for(Parameter p : service.getInterface().getOutputParameters()) {
			if (p.getParamType() == ParamType.OBJECT && StringUtils.equals(p.getType(), oldObjName)) {
				p.setType(newObjName);
			}
		}
	}

}
