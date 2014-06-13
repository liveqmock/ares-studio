/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem;
import com.hundsun.ares.studio.ui.celleditor.TextAndDialogCellEditor;

/**
 * @author liaogc
 *
 */
public class BasicDataDictDialogCellEditor extends TextAndDialogCellEditor{
	private List<DeDictionaryItem> dictElements = new ArrayList<DeDictionaryItem>();
	
	public BasicDataDictDialogCellEditor( List<DeDictionaryItem> dictElements,Composite parent){
		super(parent);
		this.dictElements = dictElements;
		
	}
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.celleditor.TextAndDialogCellEditor#openCellEditorDialog(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	protected void openCellEditorDialog(Shell shell) {
		DictDialog dialog = new DictDialog(shell,text.getText());
		if (dialog.open() == Dialog.OK) {
			text.setText(dialog.getResult().toString());
			BasicDataDictDialogCellEditor.this.focusLost();
			text.setSelection(text.getText().length());
		}
	}
	
	private class DictDialog extends Dialog{
		private List<String> dictItem = new ArrayList<String>();
		private List<Button> btDictItems = new ArrayList<Button>();
		private String textValue = "";
		private String result = "";
		/**
		 * @return the result
		 */
		public String getResult() {
			return result;
		}
		/**
		 * @param parentShell
		 */
		protected DictDialog(Shell parentShell,String value) {
			super(parentShell);
			textValue = StringUtils.defaultIfBlank(value, "");
			dictItem = Arrays.asList(StringUtils.split(textValue, ","));
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
		 */
		@Override
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setText("选择条目对应的子项");
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.window.Window#setShellStyle(int)
		 */
		@Override
		protected void setShellStyle(int newShellStyle) {
			super.setShellStyle(newShellStyle| SWT.RESIZE) ;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.dialogs.Dialog#isResizable()
		 */
		@Override
		protected boolean isResizable() {
			return true;
		}
		
		@Override
		protected Point getInitialSize() {
			Point shellSize = new Point(300,400);
			return shellSize;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
		 */
		@Override
		protected Control createDialogArea(Composite parent) {
			Composite content = (Composite) super.createDialogArea(parent);
	        content.setLayout(new FillLayout());

	        ScrolledComposite sc = new ScrolledComposite(content, SWT.H_SCROLL
	                | SWT.V_SCROLL | SWT.BORDER);

	        Composite composite = new Composite(sc, SWT.NONE);
	        RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
	        composite.setLayout(rowLayout);
	        composite.setLayout(rowLayout);
	    	for(DeDictionaryItem item:dictElements){
				Button btItem = createRowButton(composite, SWT.CHECK, item.getValue()+"_"+item.getChineseName(), item.getValue(), false);
				btDictItems.add(btItem);
			}
	    	setButtonState();
	        sc.setContent(composite);
	        sc.setExpandHorizontal(true);
	        sc.setExpandVertical(true);
	        sc.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			return composite;
		}
		
		/**
		 * 创建Button
		 * @param parent
		 * @param style
		 * @param text
		 * @param data
		 * @param isSelected
		 * @return
		 */
		private Button createRowButton(Composite parent, int style, String text, Object data, boolean isSelected) {
			Button button= new Button(parent, style);
			button.setText(text);
			button.setData(data);
			button.setLayoutData(new RowData());
			button.setSelection(isSelected);
			return button;
		}
		
		private void setButtonState(){
			for(Button btItem:btDictItems){
				String value =(String) btItem.getData();
				if(dictItem.contains(value)){
					btItem.setSelection(true);
				}
			}
		}
		@Override
		protected void buttonPressed(int buttonId) {
			List<String> values = new ArrayList<String>();
			StringBuffer returnValue = new StringBuffer();
			if (buttonId == Window.OK) {
				for(Button btItem:btDictItems){
					if(btItem.getSelection()){
						String value =(String) btItem.getData();
						values.add(value);
					}
				}
				for(int i = 0;i< values.size();i++){
					if(i!= values.size()-1){
						returnValue.append(values.get(i));
						returnValue.append(",");
					}else{
						returnValue.append(values.get(i));
					}
				}
			}
			this.result = returnValue.toString();
			super.buttonPressed(buttonId);
		}
		
		
		
	}

}
