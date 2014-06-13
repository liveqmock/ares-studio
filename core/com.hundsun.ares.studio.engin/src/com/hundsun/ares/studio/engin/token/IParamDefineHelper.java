package com.hundsun.ares.studio.engin.token;

public interface IParamDefineHelper {

	//参数类型
	public static final int STD  = 0;
	
	public static final int RECORD_OBJECT  = 1;
	
	public static final int RECORD_INTERFACE  = 2;
	
	public static final int RECORD_GROUP = 3;//参数组
	
	/**
	 * 添加初始化参数
	 * 如果添加成功表示没有初始化
	 * 添加失败表示已经初始化
	 * @param type
	 * @param paraName
	 * @return
	 */
	public void addInit(int type,String paraName);
	//defineHelper.addInit(IParamDefineHelper.STD, para.getName());//判断该变量是否已经定义
	
	public boolean canInit(int type,String paraName);
}
