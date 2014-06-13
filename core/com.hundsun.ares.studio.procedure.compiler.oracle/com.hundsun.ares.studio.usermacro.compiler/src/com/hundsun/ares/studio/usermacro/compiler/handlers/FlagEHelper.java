/**
 * 
 */
package com.hundsun.ares.studio.usermacro.compiler.handlers;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * E标记处理（E标记的信息为错误号的常量定义），将E标记替换成错误号
 * 
 * @author yanwj06282
 *
 */
public class FlagEHelper implements IUserMacroFlagHelper {

	
	@Override
	public String genFlag(UserMacroToken token ,Map<Object, Object> context ,String content) {
		//取得當变量的下标位，例如：<E>%1$s，我们要取得里面的“1”，表示当前改变量是第一位
		
		int index = Integer.parseInt(StringUtils.substring(content, StringUtils.indexOfIgnoreCase(content, "<E>")+4, StringUtils.indexOfIgnoreCase(content, "<E>")+5));
		if(index<=token.getUmParams().length){
			String errorName = token.getUmParams()[index-1];
			ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(token.getProject(), IMetadataRefType.ErrNo, errorName, true);
			if (info != null && info.getObject() != null) {
				ErrorNoItem noItem = (ErrorNoItem) info.getObject();
				if (noItem != null) {
					errorName = noItem.getNo();
				}
			}
			if (!StringUtils.equals(token.getUmParams()[index-1], errorName)) {
				token.getUmParams()[index-1] = errorName;
			}
		}else{
			throw new RuntimeException("自定义宏：["+token.getItem().getName()+"] ,参数信息不匹配!");
		}
		
		content = content.substring(0, StringUtils.indexOfIgnoreCase(content, "<E>")) + content.substring(StringUtils.indexOfIgnoreCase(content, "<E>")+3, content.length());
		return content;
	}

}
