/**
 * 源程序名称：JresARESResourceAssistantAndSelectionField.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.assist;

import org.apache.log4j.Logger;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Hyperlink;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESBundle;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.ui.dialog.ARESResourceSelectionDialog;
import com.hundsun.ares.studio.ui.util.ARESUIUtil;

/**
 * @author yanwj06282
 *
 */
public abstract class JresARESResourceAssistantAndSelectionField {
	
	public static final Logger LOGGER=ConsoleHelper.getLogger();

	private Text text;
	private Hyperlink hyperlink;
	
	/**
	 * @param parent
	 * @param labelStr
	 * @param txtStyle
	 * @param resType
	 * @param dialogTitle
	 */
	public JresARESResourceAssistantAndSelectionField(Composite parent,
			String labelStr, int txtStyle, String resType, String dialogTitle) {
		createField(parent, labelStr, txtStyle, resType, dialogTitle);
	}

	private void createField(Composite parent, String labelStr, int txtStyle, final String resType,
			final String dialogTitle) {
		hyperlink = new Hyperlink(parent, SWT.NONE);
		hyperlink.setUnderlined(true);
		hyperlink.setText(labelStr);
		hyperlink.addHyperlinkListener(new HyperlinkAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.ui.forms.events.HyperlinkAdapter#linkActivated(org
			 * .eclipse.ui.forms.events.HyperlinkEvent)
			 */
			@Override
			public void linkActivated(HyperlinkEvent e) {
				linkARESResource(resType);
			}
		});
		text = new TextContentAssistEx(parent, txtStyle, new ARESResourceAssistantProvider(resType));
		Button btnBorwser = new Button(parent, SWT.PUSH);
		btnBorwser.setText("浏览...");
		btnBorwser.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				openARESResourceSelectionDialog(resType, dialogTitle);
			}
		});
	}

	/**
	 * 打开ARES资源选择对话框。
	 */
	private void openARESResourceSelectionDialog(String resType, String title) {
		IARESProject project = getARESProject();
		if (project != null) {
			ARESResourceSelectionDialog dialog = createDialog(project, resType);
			dialog.setTitle(title);
			String pattern = "**";
			String temp = text.getText();
			if (!temp.equals("") && !temp.equals("default")) {
				pattern = temp;
			}
			dialog.setInitialPattern(pattern);

			int returnCode = dialog.open();
			if (returnCode == Window.OK) {
				okPressed((IARESResource) dialog.getFirstResult());
			}
		}
	}

	protected ARESResourceSelectionDialog createDialog(IARESProject project, String resType) {
		return new ARESResourceSelectionDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				project, resType);
	}

	/**
	 * 处理ok按钮事件。
	 * 
	 * @param result
	 */
	protected void okPressed(IARESResource result) {
		if (!result.getFullyQualifiedName().equals(text.getText())) {
			text.setText(result.getFullyQualifiedName());
			text.setFocus();
			text.setSelection(result.getFullyQualifiedName().length());
		}
	}

	/**
	 * 链接ARES资源。
	 */
	private void linkARESResource(String resType) {
		try {
			if (!text.getText().equals("")) {
				IARESBundle[] elements = ARESElementUtil.getRefARESProjects(getARESProject());
				boolean hasRes=false;
				for (IARESBundle ele : elements){
					IARESResource resource = ele.findResource(text.getText(), resType);
					if(resource != null && resource.exists()){
						ARESUIUtil.openEditor(resource);
						hasRes=true;
						break;
					}
				}
				if(!hasRes){
					LOGGER.error("引用的资源不存在，资源路径:"+text.getText());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Hyperlink getLabel() {
		return hyperlink;
	}

	public Text getControl() {
		return text;
	}

	/**
	 * 获取当前ARESProject。
	 */
	protected abstract IARESProject getARESProject();

	private class ARESResourceAssistantProvider implements IAssistantProvider {
		protected String resType;

		public ARESResourceAssistantProvider(String resType) {
			this.resType = resType;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hundsun.ares.studio.ui.control.IAssistantProvider#getContent(
		 * java.lang.Object)
		 */
		public String getContent(Object obj) {
			if (obj instanceof IARESResource) {
				return ((IARESResource) obj).getFullyQualifiedName();
			}
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hundsun.ares.studio.ui.control.IAssistantProvider#getDescription
		 * (java.lang.Object)
		 */
		public String getDescription(Object obj) {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hundsun.ares.studio.ui.control.IAssistantProvider#getLabel(java
		 * .lang.Object)
		 */
		public String getLabel(Object obj) {
			return getContent(obj);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hundsun.ares.studio.ui.control.IAssistantProvider#getProposals()
		 */
		public Object[] getProposals() {
			IARESProject project = getARESProject();
			if (project != null) {
				return ARESElementUtil.getAllResourceFromRefInType(project,new String[]{resType});
			}
			return new Object[] {};
		}
	}
	
}
