/**
 * 源程序名称：DBModuleDatabaseSelectionDialog.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.jres.database.ui.editors.dialog.SelectDialog;
import com.hundsun.ares.studio.jres.model.database.DBModuleCommonProperty;

/**
 * @author wangbin
 *
 */
public class DBModuleDatabaseSelectionDialog extends SelectDialog{
	
	/**数据库种类*/
	static List<String> databaseTypeList = Arrays.asList("Oracle","DB2","MySQL","SQL Sever","Informix","Sybase");

	/**
	 * @param parentShell
	 * @param Title
	 */
	protected DBModuleDatabaseSelectionDialog(Shell parentShell, 
			String Title, 
			DBModuleCommonProperty model) {
		super(parentShell, Title, getChoiceInput(model), getResultInput(model), getLabelProvider());
	}
	
	protected static List<String> getResultInput(DBModuleCommonProperty model) {
		return getData(model);
	}
	
	/**
	 * 将获取到的database列表装换成List
	 */
	protected static List<String> getData(DBModuleCommonProperty model){
		List<String> dbType = new ArrayList<String>();
		String database = model.getSupportDatabases();
		if(StringUtils.isNotBlank(database)){
			if(StringUtils.contains(database, ",")){
				dbType.addAll(Arrays.asList(StringUtils.split(database, ",")));
			}else{
				dbType.add(database);	
			}
		}
		return dbType;
	}
	
	protected static List<String> getChoiceInput(DBModuleCommonProperty model) {
		
		List<String> choiceInput = new ArrayList<String>();
		
		List<String> dataType = getData(model);
		
		for(int i = 0; i<databaseTypeList.size(); i++ ){
			
			String databaseType = databaseTypeList.get(i);
			
			if(!dataType.contains(databaseType)){
				choiceInput.add(databaseType);
			}
		}
		return choiceInput;
	}

	protected static ILabelProvider getLabelProvider() {
		return new ILabelProvider() {
			
			@Override
			public void removeListener(ILabelProviderListener listener) {
				
			}
			
			@Override
			public boolean isLabelProperty(Object element, String property) {
				return false;
			}
			
			@Override
			public void dispose() {
			}
			
			@Override
			public void addListener(ILabelProviderListener listener) {
				
			}
			
			@Override
			public String getText(Object element) {
				return (String) element;
			}
			
			@Override
			public Image getImage(Object element) {
				return null;
			}
		};
	}
	
}
