/**
 * 
 */
package com.hundsun.ares.studio.cres.ui.action;

import org.apache.commons.lang.StringUtils;



/**
 * @author yanwj06282
 *
 */
public enum GenModuleCodeType {

	CH_DIR_GENMODULE(1 ,"生成模块代码(带中文目录)"),
	
	EN_DIR_GENMODULE(2 ,"生成模块代码(带英文目录)"),
	
	NODIR_GENMODULE(3 ,"生成模块代码(不带目录)");
	
	private static GenModuleCodeType[] types = new GenModuleCodeType[]{CH_DIR_GENMODULE ,EN_DIR_GENMODULE ,NODIR_GENMODULE};
	
	private int type;
	private String desc;
	
	private GenModuleCodeType(int type , String desc){
		this.type = type;
		this.desc = desc;
	}
	
	public int getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public static GenModuleCodeType[] getEnumTypes(){
		return types;
	}
	
	public static int getType(String desc){
		for (int i = 0; i < types.length; ++i) {
			GenModuleCodeType result = types[i];
			if (result.getDesc().equals(desc)) {
				return result.getType();
			}
		}
		return 0;
	}
	
	public static String getDesc(int type){
		for (int i = 0; i < types.length; ++i) {
			GenModuleCodeType result = types[i];
			if (result.getType() == type) {
				return result.getDesc();
			}
		}
		return StringUtils.EMPTY;
	}
	
}
