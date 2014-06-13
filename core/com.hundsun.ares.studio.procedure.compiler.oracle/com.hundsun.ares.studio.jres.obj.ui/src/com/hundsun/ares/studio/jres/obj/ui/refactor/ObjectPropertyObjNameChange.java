package com.hundsun.ares.studio.jres.obj.ui.refactor;

import org.apache.commons.lang.StringUtils;
import org.eclipse.ltk.core.refactoring.Change;

import com.hundsun.ares.studio.biz.ARESObject;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.refactoring.AresResourceChange;

/**
 * 对象属性里面的对象类型的属性改名用的Change.
 * @author sundl
 *
 */
public class ObjectPropertyObjNameChange extends AresResourceChange {
	
	private String oldObjName;
	private String newObjName;
	
	public ObjectPropertyObjNameChange(IARESResource element, String oldName, String newName) {
		super(element);
	}

	@Override
	protected Class<?> getInfoClassType() {
		return ARESObject.class;
	}

	@Override
	protected Change createUndoChange() {
		return new ObjectPropertyObjNameChange(resource, newObjName, oldObjName);
	}

	@Override
	protected void modifyInfo(Object info) {
		ARESObject obj = (ARESObject) info;
		for (Parameter p : obj.getProperties()) {
			if (p.getParamType() == ParamType.OBJECT && StringUtils.equals(p.getType(), oldObjName)) {
				p.setType(newObjName);
			}
		}
	}
	

}
