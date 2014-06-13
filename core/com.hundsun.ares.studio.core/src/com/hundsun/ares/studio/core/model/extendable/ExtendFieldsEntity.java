package com.hundsun.ares.studio.core.model.extendable;

import java.util.ArrayList;
import java.util.List;


/**
 * 一个拓展字段的集合
 * 一般对应一个页面的所有拓展字段
 * @author maxh
 *
 */
public class ExtendFieldsEntity {
	List<ExtendFieldsHeader> extendFields;
	
	public ExtendFieldsEntity() {
		extendFields = new ArrayList<ExtendFieldsHeader>();
	}
	
	public List<ExtendFieldsHeader> getExtendFields() {
		return extendFields;
	}
	
	public void setExtendFields(List<ExtendFieldsHeader> extendFields) {
		this.extendFields = extendFields;
	}
}
