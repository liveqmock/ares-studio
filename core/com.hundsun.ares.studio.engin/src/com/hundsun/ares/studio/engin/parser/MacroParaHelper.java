package com.hundsun.ares.studio.engin.parser;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.engin.constant.ITokenConstant;

public class MacroParaHelper {

	/**
	 * 解析参数键值对
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static Map<String,String> parseParaKeyValue(String content)throws Exception{
		Map<String,String> tmap = new LinkedHashMap<String, String>();
		if(!StringUtils.isBlank(content)){
			String[] pairs = StringUtils.split(content,ITokenConstant.para_seprator);
			for(String item:pairs){
				String[] tset = StringUtils.split(item,ITokenConstant.key_value_seprator);
				if(tset.length == 2){
					tmap.put(tset[0].trim(), tset[1].trim());
				}else{
					throw new Exception(String.format("错误的参数键值对[%s]", item));
				}
			}
		}
		return tmap;
	}
}
