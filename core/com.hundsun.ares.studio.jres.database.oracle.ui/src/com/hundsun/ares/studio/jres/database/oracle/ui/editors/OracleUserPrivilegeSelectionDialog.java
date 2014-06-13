/**
 * 源程序名称：OracleUserPrivilegeSelectionDialog.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：王彬
 */
package com.hundsun.ares.studio.jres.database.oracle.ui.editors;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.jres.database.ui.editors.dialog.SelectDialog;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePrivilege;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUser;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUserResourceData;

/**
 * @author wangbin
 *
 */
public class OracleUserPrivilegeSelectionDialog extends SelectDialog{
	

	private static OracleUserResourceData resourceData;
	static List<OraclePrivilege> resultInput = new ArrayList<OraclePrivilege>();
	
	/**
	 * @param parentShell
	 * @param Title
	 */
	protected OracleUserPrivilegeSelectionDialog(Shell parentShell, String Title, ColumnViewer columnViewer) {
		super(parentShell, "选择Oracle用户权限", getChoiceInput(columnViewer), getResultInput(), getLabelProvider());
	}
	
	protected static List<OraclePrivilege> getResultInput() {
		return resultInput;
	}

	protected static List<OraclePrivilege> getChoiceInput(ColumnViewer columnViewer) {
		
		List<OraclePrivilege> choiceInput = new ArrayList<OraclePrivilege>();
		IStructuredSelection select = (IStructuredSelection) columnViewer.getSelection();
		Object obj = select.getFirstElement();
		if(obj instanceof OracleUser){
			resourceData = (OracleUserResourceData)((OracleUser)obj).eContainer();
			List<OraclePrivilege> privilege = resourceData.getPrivileges();
			if(resultInput.size()>0){
				resultInput.clear();
			}
			resultInput.addAll(((OracleUser)obj).getPrivileges());
			List<String> resultNames = new ArrayList<String>();
			for(OraclePrivilege userPrivilege : resultInput){
				resultNames.add(userPrivilege.getName());
			}
			for(OraclePrivilege privlieges : privilege){
				if(!resultNames.contains(privlieges.getName())){
					choiceInput.add(privlieges);
				}
			}
		}
		return choiceInput;
	}


	
	class privilegeLabelProvider implements ILabelProvider{

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
		 */
		@Override
		public void addListener(ILabelProviderListener listener) {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
		 */
		@Override
		public void dispose() {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
		 */
		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
		 */
		@Override
		public void removeListener(ILabelProviderListener listener) {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
		 */
		@Override
		public Image getImage(Object element) {
			return null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		@Override
		public String getText(Object element) {
			if(element instanceof OraclePrivilege){
				return ((OraclePrivilege)element).getName();
			}
			return null;
		}
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
				if(element instanceof OraclePrivilege){
					return ((OraclePrivilege)element).getName();
				}
				return StringUtils.EMPTY;
			}
			
			@Override
			public Image getImage(Object element) {
				return null;
			}
		};
	}

}
