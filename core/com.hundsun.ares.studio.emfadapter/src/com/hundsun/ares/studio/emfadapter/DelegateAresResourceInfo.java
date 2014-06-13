package com.hundsun.ares.studio.emfadapter;

public abstract class DelegateAresResourceInfo {

	private Object delegatedInfo;	// real info
	
	public Object getDelegatedInfo() {
		return delegatedInfo;
	}
	
	public void setDelegatedInfo(Object info) {
		this.delegatedInfo = info;
	}
	
}
