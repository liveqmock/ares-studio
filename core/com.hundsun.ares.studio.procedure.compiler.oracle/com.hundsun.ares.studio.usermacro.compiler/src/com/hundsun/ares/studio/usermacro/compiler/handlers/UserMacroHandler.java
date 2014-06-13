package com.hundsun.ares.studio.usermacro.compiler.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.constant.IEngineContextConstant;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.parser.CodeParserUtil;
import com.hundsun.ares.studio.engin.parser.PseudoCodeParser;
import com.hundsun.ares.studio.engin.skeleton.ISkeletonAttributeHelper;
import com.hundsun.ares.studio.engin.token.DefaultTokenEvent;
import com.hundsun.ares.studio.engin.token.ICodeToken;
import com.hundsun.ares.studio.engin.token.IDomainHandler;
import com.hundsun.ares.studio.engin.token.ITokenDomain;
import com.hundsun.ares.studio.engin.token.ITokenListenerManager;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;
import com.hundsun.ares.studio.usermacro.UserMacroItem;
import com.hundsun.ares.studio.usermacro.compiler.contants.IUserMacroEnginConstant;

/**
 * 用户宏处理不同的用户宏有不同的宏名以及不同的参数,在调用的地方通过调用者setParamsMap方法设置参数信息
 * @author liaogc
 *
 */
public class UserMacroHandler implements IMacroTokenHandler{
	
	private static final String SKELETON_ATTRIBUTE_HELPER = "蓝图全局属性帮助类";
	private static final String ATTR_PROC_VARIABLE_LIST = "PRO*C变量列表";
	private static final String RESOURCE_MODEL = "资源模型";
	private Map<String,Object> paramsMap= new HashMap<String,Object>();//所有的参数map
	boolean inProc = false;  //是否在proc语句块当中
	
	String key;
	UserMacroItem item;
	public UserMacroHandler(UserMacroItem item){
		this.key = item.getName();
		this.item = item;
	}
	
	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}
	
	public Map<String, Object> getParamsMap(){
		return paramsMap;
	}
	
	@Override
	public String getKey() {
		return key;
	}

	@Override
	public Iterator<ICodeToken> handle(IMacroToken token,
			Map<Object, Object> context) throws Exception {
		
		IDomainHandler handler = (IDomainHandler)context.get(IEngineContextConstant.DOMAIN_HANDLER);
		ITokenDomain domain = handler.getDomain("PRO*C语句块开始");
		if(null == domain){//判断是否在pro*c语句块中
			//如果为过程资源，也说明是porc处理
			Object resource = context.get(IEngineContextConstant.CURR_RESOURCE);
			if(null != resource && resource instanceof IARESResource){
				IARESResource res = (IARESResource)resource;
				if(StringUtils.equalsIgnoreCase(res.getType(), "procedure")) {
					inProc = true;
				}
			}
		}else{
			inProc = true;
		}
		if(token.getKeyword().equals(IUserMacroEnginConstant.TRAN_BLOCK_BEGIN_MACRONAME)){//添加事务开始域
			addDomain(context, IUserMacroEnginConstant.TRAN_BLOCK_BEGIN_MACRONAME);
		}
		if(token.getKeyword().equals(IUserMacroEnginConstant.TRAN_BLOCK_END_MACRONAME)){
			IDomainHandler handler2 = (IDomainHandler)context.get(IEngineContextConstant.DOMAIN_HANDLER);
			ITokenDomain domain2 = handler.getDomain(IUserMacroEnginConstant.TRAN_BLOCK_BEGIN_MACRONAME);
			if(domain2==null){
				fireEvent(context);
			}else{
				removeDomain(context, IUserMacroEnginConstant.TRAN_BLOCK_BEGIN_MACRONAME);//删除事务开始域
			}
			
		}
		//如果在proc*c块中加入到pro*c变量列表中
		if(inProc){
			ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(SKELETON_ATTRIBUTE_HELPER);
			for (String param : token.getParameters()) {
				if (StringUtils.startsWith(param, "@") && StringUtils.indexOf(param, ",")<0) {//防止参数是[@operator_no,@trans_name]的情况
					param = StringUtils.replaceOnce(param, "@", "");
					helper.addAttribute("PRO*C变量列表", param);
				}else if(StringUtils.startsWith(param, "@") && StringUtils.indexOf(param, ",")>-1){//处理[@operator_no,@trans_name]
					String[] procParams = StringUtils.split(param,",");
					for(String procParam:procParams){
						procParam = StringUtils.trim(procParam);
						if(StringUtils.startsWith(procParam, "@")){
							procParam = StringUtils.replaceOnce(procParam, "@", "");
						}
						helper.addAttribute("PRO*C变量列表", procParam);
					}
				}
			}
		}
		
		//将用户宏中的带“@”的变量加入到PRO*C变量列表
		IUserMacroTokenService service = (IUserMacroTokenService)context.get(IUserMacroEnginConstant.UserMacro_Service);
		UserMacroItem userMacroItem = service.getUserMacro(token.getKeyword());
		Pattern p = Pattern.compile("@[\\w\\d_]+");
		//sql语句在第一参数中()
		Matcher m = p.matcher(userMacroItem.getContent());
		ISkeletonAttributeHelper helper = (ISkeletonAttributeHelper)context.get(SKELETON_ATTRIBUTE_HELPER);
		while (m.find()) {
			String varName = m.group().substring(1);
			helper.addAttribute(ATTR_PROC_VARIABLE_LIST, varName);
			//在具体的使用地方再判断是否为对象资源
			helper.addAttribute(IUserMacroEnginConstant.USER_MACRO_OBJ, varName);
		}
		
		List<ICodeToken> tList = new ArrayList<ICodeToken>();
		
		Map<String,String> realDataType = (Map<String, String>) paramsMap.get("dataRealType");//数据类型
		String curId = "";
		
		if (userMacroItem.getContent().indexOf("<T>") > -1) {
			Set<String> lastCurs = (Set<String>)getParamsMap().get("lastCur");
			Set<String> lastCurIds = helper.getAttribute(IEngineContextConstant.LASTEST_CUR_ID);
			//取
			if (lastCurIds.size() == 0 && lastCurs.size() > 0) {
				curId = lastCurs.toArray(new String[0])[lastCurs.size()-1];
			}else if (lastCurIds.size() > 0){
				curId = lastCurIds.toArray(new String[0])[lastCurIds.size()-1];
			}
			//存
			List<String> curs = new ArrayList<String>();
			curs.addAll(lastCurs);
			int index = curs.indexOf(curId);
			if (index > 0 && curs.size() > index-1) {
				helper.getAttribute(IEngineContextConstant.LASTEST_CUR_ID).clear();
				helper.addAttribute(IEngineContextConstant.LASTEST_CUR_ID, curs.get(index-1));
			}
		}
		String lastResId = (String) paramsMap.get("lastResId");
		List<String> inoutParams = (List<String>) paramsMap.get("inoutParams");//输入与输出变量
		
		//2013年11月28日17:17:34 用户宏参数去除@符号，
		//兼容UFT模式，UFT有些用户宏的参数带@符号，定义时也带@符号，导致冲突 
		//例如UFT中“获取记录”
		List<String> paraList = new ArrayList<String>();
		for(String item:token.getParameters()){
			//2014年3月28日09:51:57 此处部替换，放到用户宏代码解析后进行替换com.hundsun.ares.studio.usermacro.compiler.handlers.UserMacroUtil.genCode(String, List<String>, String[])
			//用户宏中参数定义的时候没有带@符号，用户宏使用的时候又带有@符号，导致最终生成代码前缀丢失
//			paraList.add(StringUtils.replaceOnce(item, "@", ""));
			paraList.add(item);
		}
		
		tList.add(new UserMacroToken(this, userMacroItem, realDataType, context, curId,lastResId ,paraList.toArray(new String[0]) ,inoutParams));
		
		return tList.iterator();
	}
	
	public boolean isInProc(){
		return inProc;
	}
	
	/**
	 * 添加区域
	 * @param context
	 * @param key
	 */
	private void addDomain(Map<Object, Object> context,final String key){
		IDomainHandler handler = (IDomainHandler)context.get(IEngineContextConstant.DOMAIN_HANDLER);
		ITokenDomain domain = handler.getDomain(key);
		if(domain == null){
			handler.addDomain(new ITokenDomain() {
				
				@Override
				public String getType() {
					return null;
				}
				@Override
				public String getKey() {
					return key;
				}
				@Override
				public Object[] getArgs() {
					return null;
				}
			});
		}
	}
	
	/**
	 * 删除域
	 * @param context
	 * @param key
	 */
	private void removeDomain(Map<Object, Object> context,final String key){
		IDomainHandler handler = (IDomainHandler)context.get(IEngineContextConstant.DOMAIN_HANDLER);
		ITokenDomain domain = handler.getDomain(key);
		if(domain != null){
			handler.removeDomain(key);
		}
	}
	/**
	 * 发送事务处理宏结束缺少事务处理开始宏事件
	 */
	private void fireEvent(Map<Object, Object> context){
		ITokenListenerManager  manager = (ITokenListenerManager)context.get(IEngineContextConstant.TOKEN_LISTENER_MANAGER);
		String message = String.format("宏[%1s]缺少宏[%2s]", IUserMacroEnginConstant.TRAN_BLOCK_END_MACRONAME,IUserMacroEnginConstant.TRAN_BLOCK_BEGIN_MACRONAME);
		manager.fireEvent(new DefaultTokenEvent(ITokenConstant.EVENT_ENGINE_WARNNING,message));
	}
}



