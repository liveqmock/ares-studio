package com.hundsun.ares.studio.atom.ui.refactor;

import org.apache.commons.lang.StringUtils;
import org.eclipse.ltk.core.refactoring.Change;

import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.refactoring.AresResourceChange;

/**
 * CRES输入输出参数中对象类型的参数，改变对象全名的Change对象
 * @author liao
 *
 */
public class AtomParameterObjectChange extends AresResourceChange{
	
	private String oldObjName;
	private String newObjName;

	public AtomParameterObjectChange(IARESResource resource, String oldObjName, String newObjName) {
		super(resource);
		this.oldObjName = oldObjName;
		this.newObjName = newObjName;
	}

	@Override
	protected Class<?> getInfoClassType() {
		return BizInterface.class;
	}

	@Override
	protected Change createUndoChange() {
		return new AtomParameterObjectChange(resource, newObjName, oldObjName);
	}

	@Override
	protected void modifyInfo(Object info) {
		BizInterface function = (BizInterface) info;
		for(Parameter p : function.getInputParameters()) {
			if ((p.getParamType() == ParamType.OBJECT || p.getParamType() == ParamType.PARAM_GROUP) && StringUtils.equals(p.getType(), oldObjName)) {
				p.setType(newObjName);
			}
		}
		for(Parameter p : function.getOutputParameters()) {
			if ((p.getParamType() == ParamType.OBJECT || p.getParamType() == ParamType.PARAM_GROUP) && StringUtils.equals(p.getType(), oldObjName)) {
				p.setType(newObjName);
			}
		}
	}

}
