/**
 * 源程序名称：IndexColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：王彬
 */
package com.hundsun.ares.studio.jres.database.oracle.ui.viewer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege;
import com.hundsun.ares.studio.jres.model.database.oracle.impl.OracleUserImpl;

/**
 * @author wangbin
 *
 */
public class UserPrivilegeLabelProvider extends ColumnLabelProvider {
	
	private int featureID;
	private IARESResource resource;
	
	public UserPrivilegeLabelProvider(int featureID , IARESResource resource) {
		this.featureID = featureID;
		this.resource = resource;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		if(element instanceof OracleUserImpl){
			if(featureID == OraclePackage.ORACLE_USER__PRIVILEGES){
				EList<OraclePrivilege> oraclePrivilege  = ((OracleUserImpl)element).getPrivileges();
				StringBuffer buf = new StringBuffer();
				for(int i = 0 , length = oraclePrivilege.size() ; i < oraclePrivilege.size(); i++){
					buf.append(oraclePrivilege.get(i).getName());
					if(i < length - 1){
						buf.append(",");
					}
				}
				return buf.toString();
			}
		}
		
		return "";
	}

	@Override
	public Color getBackground(Object element) {
		if (resource.isReadOnly()) {
			return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
		}
		return super.getBackground(element);
	}


	@Override
	public String getToolTipText(Object element) {
		return getText(element);
	}
	
	@Override
	public int getToolTipDisplayDelayTime(Object object) {
		return 100;
	}

}
