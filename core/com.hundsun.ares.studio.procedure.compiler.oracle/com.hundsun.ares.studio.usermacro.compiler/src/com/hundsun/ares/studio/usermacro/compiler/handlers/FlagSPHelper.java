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
 * SP标记的处理
 * 将SP标记处理的信息，拼接上参数，拼接的结构，取决于是否存在于PROC语句块
 * 
 * @author yanwj06282
 *
 */
public class FlagSPHelper implements IUserMacroFlagHelper {

	private String prefix = "@";
	
	@Override
	public String genFlag(UserMacroToken token ,Map<Object, Object> context ,String content) {
		//取得當变量的下标位，例如：<SP>%1$s，我们要取得里面的“1”，表示当前改变量是第一位
		int index = Integer.parseInt(StringUtils.substring(content, StringUtils.indexOfIgnoreCase(content, "<SP>")+5, StringUtils.indexOfIgnoreCase(content, "<SP>")+6));
		//伪代码中的参数
		int paramsSize = token.getUmParams().length;
		//用户宏中定义的参数
		int varSize = token.getVars().length;
		
		if (paramsSize > 1) {
			String errorCons = token.getUmParams()[paramsSize - 2];
			ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(token.getProject(), IMetadataRefType.ErrNo, errorCons, true);
			if (info != null && info.getObject() != null) {
				ErrorNoItem noItem = (ErrorNoItem) info.getObject();
				if (noItem != null) {
					token.getUmParams()[paramsSize - 1] = noItem.getMessage();
				}
			}
		}
		
		//如果处于proc语句块中
		if (token.getParent().inProc) {
			prefix = "p_";
			//当伪代码输入参数满足用户宏定义的参数，两者能一一对应
			if(paramsSize > varSize){
				StringBuffer sb = new StringBuffer();
				sb.append("[");
				for(String param : token.getUmParams()[varSize].split(",")){
					param = StringUtils.replace(param.trim(), "@", "");
					if (token.getInoutParams().contains(param)) {
						sb.append(":p_");
						prefix = ":p_";
					}else {
						sb.append(":v_");
						prefix = ":v_";
					}
					sb.append(param);
					sb.append("='||");
					sb.append(prefix + param);
					sb.append("||'");
				}
				//'查询用户操作限制失败[p_operator_no='||p_operator_no||',v_limit_type='||v_limit_type||']', 1, 500);
				String param1 = sb.substring(0, sb.length() -1);
				param1 = param1 + "']";
				if (index<=token.getUmParams().length && StringUtils.indexOf(token.getUmParams()[index-1], param1) > -1) {
					content = content.substring(0, StringUtils.indexOf(content, "<SP>")) + content.substring(StringUtils.indexOf(content, "<SP>")+4, content.length());
					return content;
				}
				if(index<=token.getUmParams().length){
					token.getUmParams()[index-1] = "'" + token.getUmParams()[index-1]+param1 + "'";
				}else{
					throw new RuntimeException("自定义宏：["+token.getItem().getName()+"] ,参数信息不匹配!");
				}
				
			}else {//如果伪代码输入参数和用户宏参数不匹配
				token.getUmParams()[index-1] = "'" + token.getUmParams()[index-1] + "'";
			}
			content = content.substring(0, StringUtils.indexOf(content, "<SP>")) + content.substring(StringUtils.indexOf(content, "<SP>")+4, content.length());
		}else {//不在PROC语句快中
			if(paramsSize > varSize){
				StringBuffer sb = new StringBuffer();
				StringBuffer sb1 = new StringBuffer();
				sb.append("[");
				for(String param : token.getUmParams()[varSize].split(",")){
					param = StringUtils.replace(param.trim(), "@", "");
					sb1.append(prefix + param);
					sb.append(param);
					sb.append("=");
					if (token.getBusinessType() != null) {
						sb.append(UserMacroUtil.getDataType(token.getProject() , token.getBusinessType(), param));
					}else {
						sb.append("%s");
					}
					sb.append(",");
					sb1.append(",");
				}
				String param1 = sb.substring(0, sb.length() -1);
				String param2 = sb1.substring(0, sb1.length() -1);
				param1 = param1 + "]";
				if (index<=token.getUmParams().length && StringUtils.indexOf(token.getUmParams()[index-1], param1) > -1) {
					content = content.substring(0, StringUtils.indexOf(content, "<SP>")) + content.substring(StringUtils.indexOf(content, "<SP>")+4, content.length());
					return content;
				}

			token.getUmParams()[index-1] = "\"" + token.getUmParams()[index-1]+param1 + "\","+param2;
			content = content.substring(0, StringUtils.indexOf(content, "<SP>")) + content.substring(StringUtils.indexOf(content, "<SP>")+4, content.length());
		}else{
			if(index<=token.getUmParams().length){
				if (!StringUtils.startsWith(token.getUmParams()[index-1], "\"%s\",\"")) {
					token.getUmParams()[index-1] = "\"%s\",\"" + token.getUmParams()[index-1] + "\"";
				}
			}else{
				throw new RuntimeException("自定义宏：["+token.getItem().getName()+"] ,参数信息不匹配!");
			}
			
			content = content.substring(0, StringUtils.indexOf(content, "<SP>")) + content.substring(StringUtils.indexOf(content, "<SP>")+4, content.length());
			}
		}
		
		return content;
	}

}
