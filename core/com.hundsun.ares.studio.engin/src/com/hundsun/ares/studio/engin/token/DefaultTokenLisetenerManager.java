package com.hundsun.ares.studio.engin.token;

import java.util.ArrayList;
import java.util.List;

public class DefaultTokenLisetenerManager implements ITokenListenerManager{

	
	List<ITokenListener> list = new ArrayList<ITokenListener>();
	
	@Override
	public void addListener(ITokenListener listener) {
		if(!list.contains(listener)){
			list.add(listener);
		}
	}

	@Override
	public void removeListener(ITokenListener listener) {
		if(list.contains(listener)){
			list.remove(listener);
		}
	}

	@Override
	public void fireEvent(ITokenEvent event) {
		for(ITokenListener item:list){
			item.handle(event);
		}
	}

}
