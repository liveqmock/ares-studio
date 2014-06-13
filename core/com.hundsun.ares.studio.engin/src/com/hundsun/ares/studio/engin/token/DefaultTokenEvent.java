package com.hundsun.ares.studio.engin.token;

public class DefaultTokenEvent  implements ITokenEvent{

	String key = "";
	Object data = null;
	public DefaultTokenEvent(String key,Object data){
		this.key = key;
		this.data = data;
	}
	
	@Override
	public String getKey() {
		return key;
	}

	@Override
	public Object getData() {
		return data;
	}

}
