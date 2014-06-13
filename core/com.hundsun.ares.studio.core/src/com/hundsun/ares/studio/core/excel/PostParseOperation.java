/**
 * 源程序名称：PostParseOperation.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.core.util.log.Log;


/**
 * 解析完成后进行的操作。 
 * 有些操作必须在全部解析完成后才能进行，所以设置这个类来完成类似的工作。
 * @author sundl
 *
 */
public class PostParseOperation {
	
	protected Log log;
	
	public PostParseOperation(Log log) {
		this.log = log;
	}
	
	public class Command {
		public void excute() {
		}
	}
	
	private List<Command> commands = new ArrayList<Command>();
	
	public void addCommand(Command command) {
		this.commands.add(command);
	}
	
	public void run() {
		for (Command cmd : commands) {
			cmd.excute();
		}
	}
	
}
