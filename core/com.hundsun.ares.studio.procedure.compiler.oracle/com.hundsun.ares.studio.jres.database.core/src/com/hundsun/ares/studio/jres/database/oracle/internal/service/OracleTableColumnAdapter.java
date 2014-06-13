/**
 * 源程序名称：OracleTableColumn.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：王小恒
 */
package com.hundsun.ares.studio.jres.database.oracle.internal.service;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.database.internal.service.TableColumnAdapter;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleTableColumn;
import com.hundsun.ares.studio.jres.model.database.TableColumn;

/**
 * @author wangxh
 *
 */
public class OracleTableColumnAdapter extends TableColumnAdapter implements IOracleTableColumn {

	public OracleTableColumnAdapter(TableColumn column,IARESProject project) {
		super(column,project);
	}

}
