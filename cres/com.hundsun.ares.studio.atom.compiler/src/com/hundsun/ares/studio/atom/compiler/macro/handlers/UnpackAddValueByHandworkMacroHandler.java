package com.hundsun.ares.studio.atom.compiler.macro.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.atom.compiler.macro.MacroConstant;
import com.hundsun.ares.studio.atom.compiler.token.FunctionResultSetGetValueToken;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;

/**
 * [手工解包体][user_id = @user_id , user_name = @user_name][@usersResultSet]
 * 第二个参数为指定结果集
 * @author qinyuan
 *
 */
public class UnpackAddValueByHandworkMacroHandler implements IMacroTokenHandler {

	@Override
	public String getKey() {
		return MacroConstant.UNPACK_ADDVALUE_HANDWORK_MACRONAME;
	}

	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		List<ICodeToken> tokens = new ArrayList<ICodeToken>();
		String param_0 = "";
		if (token.getParameters().length >= 1) {
			param_0 = token.getParameters()[0];
			
		}
		String packName = "" ;
		if (token.getParameters().length >=1){
			 packName = StringUtils.trim(token.getParameters()[1].trim());
			if(!StringUtils.endsWith(packName,"ResultSet")){
				packName+="ResultSet";
			}
		}
		
		
		tokens.add(new FunctionResultSetGetValueToken(param_0,packName));
		return tokens.iterator();
	}

}
