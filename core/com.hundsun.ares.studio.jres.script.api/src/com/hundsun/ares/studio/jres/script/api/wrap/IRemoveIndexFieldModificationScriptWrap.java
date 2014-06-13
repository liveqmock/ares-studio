/**
 * 源程序名称：IRemoveIndexFieldModificationScriptWrap.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.script.api
 * 功能说明：$desc
 * 相关文档：
 * 作者：liaogc
 */
package com.hundsun.ares.studio.jres.script.api.wrap;

import com.hundsun.ares.studio.jres.script.api.database.ITableIndexScriptWrap;

/**
 * @author liaogc
 *
 */
public interface IRemoveIndexFieldModificationScriptWrap extends IModificationScriptWrap {
/**
 * 获取删除表索引的明细记录
 * @return
 */
public ITableIndexScriptWrap[] getDetails();
}
