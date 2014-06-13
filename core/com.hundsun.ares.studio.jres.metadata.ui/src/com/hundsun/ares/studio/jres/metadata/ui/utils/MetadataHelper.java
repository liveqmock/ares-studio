/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.ui.utils;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataResType;

/**
 * 元数据帮助类型
 * @author qinyuan
 *
 */
public class MetadataHelper {
	
	/**
	 * 根据资源类型获取资源的引用类型
	 * @param type
	 * @return
	 */
	public static String getRefTypeByResource(String type){
		if(StringUtils.equals(type, IMetadataResType.BizType)){
			return IMetadataRefType.BizType;
		}else if(StringUtils.equals(type, IMetadataResType.Const)){
			return IMetadataRefType.Const;
		}else if(StringUtils.equals(type, IMetadataResType.DefValue)){
			return IMetadataRefType.DefValue;
		}else if(StringUtils.equals(type, IMetadataResType.Dict)){
			return IMetadataRefType.Dict;
		}else if(StringUtils.equals(type, IMetadataResType.ErrNo)){
			return IMetadataRefType.ErrNo;
		}else if(StringUtils.equals(type, IMetadataResType.Menu)){
			return IMetadataRefType.Menu;
		}else if(StringUtils.equals(type, IMetadataResType.StdField)){
			return IMetadataRefType.StdField;
		}else if(StringUtils.equals(type, IMetadataResType.StdType)){
			return IMetadataRefType.StdType;
		}
		
		return "";
	}

}
