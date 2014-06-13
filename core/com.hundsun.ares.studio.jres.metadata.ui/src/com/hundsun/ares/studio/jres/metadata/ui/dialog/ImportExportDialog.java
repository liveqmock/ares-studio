/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.metadata.ui.dialog;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public abstract class ImportExportDialog extends TitleAreaDialog {
	protected String title;
	protected Composite contentPane;
	protected String filePath; 
	protected Map<String, String> fileExtensionMap = new HashMap<String, String>(); 
	public ImportExportDialog(String title, Shell parentShell) {
		super(parentShell);
		this.title = title;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		
		// TitleArea中的Title
		setTitle(getAreaTitle());

		// TitleArea中的Message
		setMessage(getAreaMessage());
		
		// 自定义要创建的内容
		createContentPane(area);
		createContentControls();
		
		return area;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		
		// Dialog Title
		newShell.setText(title);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(400, 275);
	}

	/**
	 * 自定义区标题。
	 * @return title
	 */
	protected abstract String getAreaTitle();
	
	/**
	 * 自定义区信息提示。
	 * @return message
	 */
	protected abstract String getAreaMessage();
	
	/**
	 * 自定义面板。
	 */ 
	protected void createContentPane(Composite parent) {
		contentPane = new Composite(parent, SWT.NULL);

		// 采用四列的GridLayout
		GridLayout layout = new GridLayout(4, false);
		// 设置位置参数
		layout.marginHeight = 20;
		layout.marginWidth = 20;
//		layout.verticalSpacing = 5;
		layout.horizontalSpacing = 10;
		contentPane.setLayout(layout);
		
		// 设置布局方式
		contentPane.setLayoutData(new GridData(GridData.FILL_BOTH));
		contentPane.setFont(parent.getFont());
	}
	/**
	 * 绘制自定义面板。
	 */
	/**
	 * 绘制自定义面板。
	 */
	protected void createContentControls() {
		// 文件类型标签
		Label fileType = new Label(contentPane, SWT.NONE);
		
		GridData layoutData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		fileType.setLayoutData(layoutData);
		fileType.setText("文件类型");

		// 类型选择框
		final Combo typeCombo = new Combo(contentPane, SWT.NONE);
		
		layoutData = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
		layoutData.horizontalSpan = 3;
		typeCombo.setLayoutData(layoutData);
		fillTypeCombo(typeCombo);
		
		// 文件路径标签
		Label fileDir = new Label(contentPane, SWT.NONE);

		layoutData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		fileDir.setLayoutData(layoutData);
		fileDir.setText("文件路径");

		// 文件路径选择框
		final Text fileDirText = new Text(contentPane, SWT.BORDER | SWT.LEAD);
		fileDirText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				validate(fileDirText.getText());
			}

		});
		layoutData = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
		layoutData.horizontalSpan = 2;
		fileDirText.setLayoutData(layoutData);
		fileDirText.setToolTipText("dialog.login.passwordTooltip");
		// 文件路径选择按钮
		Button dirButton = new Button(contentPane, SWT.NONE);
		dirButton.setText("浏览...");
		dirButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
		        FileDialog dialog = new FileDialog(contentPane.getShell(), SWT.OPEN);
		        String filterExtension = (String) typeCombo.getData(typeCombo.getText());
		        dialog.setFilterExtensions(new String[] { filterExtension });
		        dialog.setFilterNames(new String[] { fileExtensionMap.get(filterExtension) });
		    	filePath = dialog.open();
				fileDirText.setText(filePath);
			
		        // 处理文件路径不带类型后缀的情况
		        if(filterExtension != "All") {
			        String fileSuffix = filterExtension.substring(1);
		        	if(!filePath.endsWith(fileSuffix)) {
		        		filePath = filePath + fileSuffix;
		        	}
		        }
		    }

		});
		
		// 添加子类自定义的面板
		Composite additionComp = createExtendContentPane(contentPane);
		if(additionComp != null) {
			layoutData = new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
			layoutData.horizontalSpan = 4;
			additionComp.setLayoutData(layoutData);
		}
	} 
	public abstract boolean validate(String fileText) ;
	/**
	 * 填充下拉框。
	 * @param combo 下拉框对象。
	 */
	protected void fillTypeCombo(Combo combo) {
		// 目前只支持“Excel”文件类型
		combo.add("Excel");
		combo.setData("Excel", new String("*.xls"));
		fileExtensionMap.put("*.xls", "Excel文件(*.xls)");
		
		combo.add("All");
		combo.setData("All", new String("*.*"));
		fileExtensionMap.put("*.*", "全部文件(*.*)");
		
		combo.select(0);
	}
	
	/**
	 * 扩展的自定义面板。
	 * @param parent
	 * @return
	 */
	protected abstract Composite createExtendContentPane(Composite parent);

	/**
	 * 获取文件路径。
	 * @return 文件路径
	 */
	public String getFilePath() {
		return filePath;
	}
	
}
