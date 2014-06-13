/**
 * 源程序名称：IDRangeUtil.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：wangxh
 */
package com.hundsun.ares.studio.jres.model.metadata.util;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.IDRange;
import com.hundsun.ares.studio.jres.model.metadata.IDRangeItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author wangxh
 *
 */
public class IDRangeUtil {
	
	private final static String DEFAULT = MetadataPackage.Literals.ID_RANGE__RANGE.getDefaultValueLiteral();
	
	/**获取指定资源的对象号范围，key为扩展列的保存在data2中的key*/
	public static String getIDRange(IARESElement element,String key){
		if(element instanceof IARESResource){
			return getIDRange(((IARESResource)element).getModule(), key);
		}else if(element instanceof IARESModule){
			return getIDRange((IARESModule)element,key);
		}
		return DEFAULT;
	}

	/**
	 * @param module
	 * @param key
	 * @return
	 * 获取指定模块的对象号范围，key为扩展列的保存在data2中的key
	 */
	private static String getIDRange(IARESModule module, String key) {
		if(module == null || !module.exists()){
			return DEFAULT;
		}
		ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(module.getARESProject(), IMetadataRefType.IDRange, 
				module.getResource().getProjectRelativePath().toPortableString(), true);
		if(info != null){
			IDRangeItem item = (IDRangeItem) info.getObject();
			IDRange range = (IDRange) item.getData2().get(key);
			if(range != null){
				String rg = range.getRange();
				if(!StringUtils.equals(rg, DEFAULT)){
					return rg;
				}
			}
		}
		return getIDRange(module.getParentModule(), key);
	}
	
	/**对象号是否符合范围*/
	public static boolean isInRange(int value,String range){
		for(String tmp : StringUtils.split(range, ",")){
			if(StringUtils.equals(tmp, DEFAULT)){
				return true;
			}
			int min = getMinID(tmp);
			int max = getMaxID(tmp);
			if(min <= value && value <= max){
				return true;
			}
		}
		return false;
	}
	
	/**获取对象号最小值*/
	public static int getMinID(String range){
		String[] tmps = StringUtils.split(range, "-");
		try {
			return Integer.parseInt(tmps[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**获取对象号最大值*/
	public static int getMaxID(String range){
		String[] tmps = StringUtils.split(range, "-");
		try {
			if(tmps.length == 2){
				return Integer.parseInt(tmps[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
