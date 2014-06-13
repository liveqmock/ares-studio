package com.hundsun.ares.studio.engin.skeleton;

public class DefaultSkeletonInput implements ISkeletonInput{

	String skeleton = "";
	Object input;
	public DefaultSkeletonInput(String skeleton,Object input){
		this.skeleton = skeleton;
		this.input = input;
	}
	
	@Override
	public String getType() {
		return skeleton;
	}

	@Override
	public Object getInput() {
		return input;
	}

}
