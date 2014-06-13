/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.compiler.mysql.constant.IAtomEngineContextConstantMySQL;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.mysql.token.PackAddFieldBodyToken;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IDomainHandler;
import com.hundsun.ares.studio.engin.token.ITokenDomain;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;

/**
 * @author zhuyf
 *
 */
public class PackAddValueByHandworkMacroHandler implements IMacroTokenHandler {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#getKey()
	 */
	@Override
	public String getKey() {
		return MacroConstant.PACK_ADDVALUE_HANDWORK_MACRONAME;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler#handle(com.hundsun.ares.studio.engin.token.macro.IMacroToken, java.util.Map)
	 */
	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		/*输入描述：
		[手工打包体]
		处理流程：
		1.检查是否与[手工打包头]成对匹配
		2.从成对的[手工打包头]中获取字段列表，并遍历字段，且每一字段生成：
			lpOutPacker->Add[字段类型]([字段名]); //[字段注释]
		3.[手工打包体][client_id][@unpack_sett]，可指定打包器，生成以下语句：
			v_unpack_sett->Add[字段类型]([字段名]); //[字段注释]
			v_unpack_sett->EndPack();
		4.[手工打包体][client_id][@unpack_sett][N]，不生成v_unpack_sett->EndPack();
		
	 * 
	 */
		List<ICodeToken> tokens = new ArrayList<ICodeToken>();
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(IAtomEngineContextConstantMySQL.SKELETON_ATTRIBUTE_HELPER);
		helper.addAttribute(IAtomEngineContextConstantMySQL.IS_ALREADY_RETURN_RESULTSET, "true");
		IDomainHandler handler = (IDomainHandler) context.get(IEngineContextConstant.DOMAIN_HANDLER);
		ITokenDomain packAddFieldHead =handler.getDomain(MacroConstant.PACK_ADDFIELD_HANDWORK_MACRONAME);
		AtomFunction atomFunction = (AtomFunction) context.get(IAtomEngineContextConstantMySQL.ResourceModel);
		helper.addAttribute(IAtomEngineContextConstantMySQL.ATTR_ACK_ADDVALUE_HANDWORK_LIST,atomFunction.getObjectId());//手工打包体列表
		if (packAddFieldHead == null) {
			//throw new RuntimeException(getKey() + " 不存在打包头");
			//由于在条件分支中，打包头与打包体未必成对出现，故去除该检查。
		}
		
		String param_0 = "";
		String param_1 = "";
		String param_2 = "";
		if (token.getParameters().length > 1) {
			param_0 = StringUtils.trim(token.getParameters()[1]);
			if(!StringUtils.endsWith(param_0, "ResultSet")){
				param_0+="ResultSet";
			}
		}
		if (token.getParameters().length > 2) {
			param_2 = StringUtils.trim(token.getParameters()[2]);
		}
		if (token.getParameters().length > 0) {
			param_1 = StringUtils.trim(token.getParameters()[0]);
		}
		 String headFeilds = "";
		if (packAddFieldHead != null && packAddFieldHead.getArgs()!=null) {
			headFeilds = (String) packAddFieldHead.getArgs()[0];
		}
		
		tokens.add(new PackAddFieldBodyToken(param_0, param_1,param_2,headFeilds));
		
		//删除域
		handler.removeDomain(MacroConstant.PACK_ADDFIELD_HANDWORK_MACRONAME);
		return tokens.iterator();
	}

}
