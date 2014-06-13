package com.hundsun.ares.studio.atom.compiler.mysql.macro;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.CommonSelectStatementMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.ErrorMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.FuncResultObjectReturnMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.FunctionResultSetGetValueHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.InsertTableInProcBlockMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.NestObjectResultsetMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.NestPackAddFieldMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.NestPackAddValueMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.PROCBlockEndMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.PROCCommitHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.PROCGetRecordBeginMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.PROCGetRecordEndMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.PROCInserTableMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.PROCResultSetReturnMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.PROCResultSetStatementMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.PROCStatementMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.PackAddFieldByHandworkMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.PackAddValueByHandworkMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.PackerDefineMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.ProcBlockBeginMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.ResultSetObjectReturnMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.ResultSetObjectSetValueMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.SelectInsertTableInProcBlockMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.UnPackerDefineMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.UnpackAddValueByHandworkMacroHandler;
import com.hundsun.ares.studio.atom.compiler.mysql.macro.handlers.UnpackerInitHandler;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandler;
import com.hundsun.ares.studio.engin.macrohandler.IMacroTokenHandlerFactory;

public class MacroHandlerFactory implements IMacroTokenHandlerFactory{

	private  Map<String, IMacroTokenHandler> macroMap = new ConcurrentHashMap<String, IMacroTokenHandler>();
	private  Set<IMacroTokenHandler> handlerSet = new HashSet<IMacroTokenHandler>();
	
	/**
	 * 是否为原子调用
	 */
	private boolean isAtomCalled = true;

	/**
	 * @param isAtomCalled the isAtomCalled to set
	 */
	public void setAtomCalled(boolean isAtomCalled) {
		this.isAtomCalled = isAtomCalled;
	}



	private static MacroHandlerFactory instance;
	
	public static MacroHandlerFactory getInstance(){
		if(null == instance){
			instance = new MacroHandlerFactory();
		}
		return instance;
	}
	
	
	
	private MacroHandlerFactory(){
		init();
	}
	
	private void init(){
		
		
		//默认只添加手工打包
		handlerSet.add(new UnpackAddValueByHandworkMacroHandler());//手工解包体
		handlerSet.add(new PackAddFieldByHandworkMacroHandler());//手工打包头
		handlerSet.add(new PackAddValueByHandworkMacroHandler());//手工打包体
		handlerSet.add(new NestObjectResultsetMacroHandler());//获取嵌套对象结果集
		handlerSet.add(new NestPackAddFieldMacroHandler());//嵌套结果集手工打包头
		handlerSet.add(new NestPackAddValueMacroHandler());//嵌套结果集手工打包体
		handlerSet.add(new FuncResultObjectReturnMacroHandler());//函数结果集对象返回
		handlerSet.add(new FunctionResultSetGetValueHandler());//函数结果集对象返回
		handlerSet.add(new PackerDefineMacroHandler());//打包器声明
		handlerSet.add(new UnPackerDefineMacroHandler());//解包器声明
		
		//原子层调用，还需要添加其他系统宏
		if(isAtomCalled) {
			handlerSet.add(new ErrorMacroHandler());
			handlerSet.add(new PROCStatementMacroHandler());//Proc语句
			handlerSet.add(new InsertTableInProcBlockMacroHandler());//插入表记录
			handlerSet.add(new SelectInsertTableInProcBlockMacroHandler());//select插入表记录
			handlerSet.add(new CommonSelectStatementMacroHandler());//通用select
			handlerSet.add(new ProcBlockBeginMacroHandler());//proc语句块开始
			handlerSet.add(new PROCBlockEndMacroHandler());//proc语句块结束
			handlerSet.add(new PROCResultSetStatementMacroHandler());//PRO*C结果集语句
			handlerSet.add(new PROCResultSetReturnMacroHandler());//PRO*C结果集返回
			handlerSet.add(new PROCGetRecordBeginMacroHandler());//PRO*C记录获取开始
			handlerSet.add(new PROCGetRecordEndMacroHandler());//PRO*C记录获取结束
			handlerSet.add(new PROCInserTableMacroHandler());//PRO*C插入表记录
			handlerSet.add(new PROCCommitHandler());//PRO*C事务提交
			handlerSet.add(new ResultSetObjectReturnMacroHandler());//结果集对象返回
			handlerSet.add(new ResultSetObjectSetValueMacroHandler());//结果集对象赋值
			handlerSet.add(new UnpackerInitHandler());//解包器对象初始化
			
		}
		
		for(IMacroTokenHandler handler:handlerSet){
			macroMap.put(handler.getKey(), handler);
		}
	}
	
	@Override
	public boolean canHandle(String key) {
		return macroMap.containsKey(key.toUpperCase());
	}

	@Override
	public IMacroTokenHandler create(String key) {
		if(macroMap.containsKey(key.toUpperCase())){
			return macroMap.get(key.toUpperCase());
		}
		return null;
	}
	
	

	@Override
	public Set<IMacroTokenHandler> getHandledMacros() {
		return handlerSet;
	}
}
