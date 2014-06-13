/**
 * 源程序名称：DefaultContextUpdateSource.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.core.context;

/**
 * @author lvgao
 *
 */
public class DefaultContextUpdateSource implements IContextUpdateSource{
	String type;
	Object[] objs;
	
	public DefaultContextUpdateSource(String type,Object[] objs){
		this.type = type;
		this.objs = objs;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.context.IContextUpdateSource#getType()
	 */
	@Override
	public String getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.context.IContextUpdateSource#getContent()
	 */
	@Override
	public Object[] getContent() {
		return objs;
	}

}
