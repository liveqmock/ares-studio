/**
 * 
 */
package com.hundsun.ares.studio.usermacro.compiler.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.usermacro.UserMacroItem;

/**
 * @author yanwj06282
 *
 */
public class UserMacroToken implements ICodeToken {

	private Map<String,String> businessType;
	private IARESProject project;
	private String cur;
	private String lastResId;
	private List<String> inoutParams;
	private String[] umParams;
	private String[] varFlags;
	private List<String> vars;
	private UserMacroItem item;
	private UserMacroHandler parent;
	
	public UserMacroToken(UserMacroHandler parent ,UserMacroItem item ,Map<String,String> businessType, Map<Object, Object> context ,String cur,String lastResId ,String[] umParams ,List<String> inoutParams) {
		this.businessType = businessType;
		this.umParams = umParams;
		this.inoutParams = inoutParams;
		this.cur = cur;
		this.lastResId = lastResId;
		this.item = item;
		this.parent = parent;
		project = (IARESProject)context.get("当前项目");
	}
	
	public String getCur(){
		return cur;
	}
	
	public UserMacroHandler getParent(){
		return parent;
	}
	
	/**
	 * @return the item
	 */
	public UserMacroItem getItem() {
		return item;
	}

	public IARESProject getProject(){
		return project;
	}
	
	public Map<String, String> getBusinessType() {
		return businessType;
	}

	public List<String> getInoutParams(){
		return inoutParams;
	}
	
	public String[] getUmParams() {
		return umParams;
	}

	public void setUmParams(String[] umParams){
		this.umParams = umParams;
	}
	
	@Override
	public String getContent() {
		return null;
	}

	@Override
	public int getType() {
		return 0;
	}

	public String[] getVarFlags(){
		return varFlags;
	}
	
	public String[] getVars(){
		return vars.toArray(new String[0]);
	}
	
	@Override
	public String genCode(Map<Object, Object> context) throws Exception {
		
		String content = item.getSequence();
		Set<String> paraIndex = UserMacroUtil.getParamSet(content);
		vars = new ArrayList<String>();
		vars.addAll(paraIndex);
		String str = UserMacroUtil.getFormatStr(item.getContent(), vars);
		List<String> tlist = (List<String>) context.get(IEngineContextConstant.PSEUDO_CODE_PARA_LIST);
		if(parent.inProc){
			str = UserMacroUtil.handleParams(":", str, tlist.toArray(new String[tlist.size()]), inoutParams);
		}
		checkParams(item.getName(), str, getUmParams(), vars);
		
		if(StringUtils.indexOfIgnoreCase(str,"lpResultSet") > -1) {
			if(StringUtils.isNotBlank(lastResId)){
				str = str.replaceAll("lpResultSet", "lpResultSet"+lastResId);
			}
		}
		//找出用户宏中所有的标记
		varFlags = UserMacroUtil.getFormatFlag(str);
		//处理所有的标记，T标记第一个处理掉
		for (String v : varFlags) {
			IUserMacroFlagHelper helper = UserMacroFlagHelperFactory.getInstance(v);
			str = helper.genFlag(this,context, str);
		}
		return UserMacroUtil.genCode(str, vars, getUmParams()) + "\r\n";
	}

	/**
	 * 用户宏，参数校验
	 * 
	 * @param userMacroName
	 * @param str
	 * @param paras
	 * @param params
	 */
	private void checkParams(String userMacroName ,String str ,String[] paras ,List<String> params){
		boolean status_ok = false;
		if (StringUtils.indexOfIgnoreCase(str, "<SP>") > -1) {
			if (paras.length >= params.size() || paras.length+1 >= params.size()) {
				status_ok = true;
			}
		}else {
			if (paras.length >= params.size()) {
				status_ok = true;
			}

		}
		if (!status_ok) {
			throw new RuntimeException("自定义宏：["+userMacroName+"] ,参数信息不匹配!");
		}
	}
	
}
