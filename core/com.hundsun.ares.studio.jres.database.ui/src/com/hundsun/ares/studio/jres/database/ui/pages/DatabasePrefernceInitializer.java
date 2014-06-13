/**
 * 源程序名称：DatabasePrefernceInitializer.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.pages;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

import com.hundsun.ares.studio.jres.database.constant.IDBConstant;
import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;

/**
 * @author liaogc
 *
 */
public class DatabasePrefernceInitializer extends AbstractPreferenceInitializer {

	public DatabasePrefernceInitializer() {
	}

	@Override
	public void initializeDefaultPreferences() {
		IEclipsePreferences preferences = new DefaultScope().getNode(DatabaseUI.PLUGIN_ID);
		preferences.put(IDBConstant.TABLE_NAME_LENGTH, "26");
		preferences.put(IDBConstant.TABLE_COLUMN_LENGTH, "30");
		preferences.put(IDBConstant.INDEX_LENGTH, "26");
		preferences.put(IDBConstant.CONSTRAINT_LENGTH, "26");
		
	}
}
