package com.hundsun.ares.studio.jres.basicdata.ui.editor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.basicdata.constant.IBasicdataConstants;
import com.hundsun.ares.studio.jres.basicdata.ui.BasicDataUI;
import com.hundsun.ares.studio.jres.script.ScriptPlugin;
import com.hundsun.ares.studio.ui.CommonElementContentProvider;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;
import com.hundsun.ares.studio.ui.editor.IProjectExtensibleStatus;
import com.hundsun.ares.studio.ui.editor.TextExtensionFieldEditor;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;

public class ScriptDirEditor extends TextExtensionFieldEditor implements IProjectExtensibleStatus{
	
	
	private IARESProject project;
	Text txtPath;
	Button btnFile;
	
	public ScriptDirEditor() {
		setId(IBasicdataConstants.BASICDATA_SCRIPT_DIR_ID);
		setName("基础数据脚本:");
	}

	@Override
	public void createControls(FormToolkit toolkit,
			final ImporveControlWithDitryStateContext context) {
		Label label = toolkit.createLabel(context.getParent(), getName());
		final Composite com = new Composite(context.getParent(), SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(com);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(com);
		txtPath = toolkit.createText(com, "", SWT.BORDER);
		txtPath.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				if (!StringUtils.equals(txtPath.getText(), properties.getString(getId()))) {
					properties.setValue(getId(), txtPath.getText());
					context.getDirtyStatus().setValue(true);
				}
				
			}
		});
		txtPath.setText(StringUtils.defaultString(properties.getString(getId())));
		btnFile = new Button(com, SWT.NONE);
		GridDataFactory.swtDefaults().applyTo(label);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtPath);
		GridDataFactory.swtDefaults().applyTo(btnFile);
		btnFile.setText("浏览");
		btnFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				CommonElementContentProvider cp = new CommonElementContentProvider(){
					/* (non-Javadoc)
					 * @see com.hundsun.ares.studio.ui.CommonElementContentProvider#getModuleRoots(com.hundsun.ares.studio.core.IARESProject)
					 */
					@Override
					protected Object[] getProjectChildren(
							IARESProject project) {
						return ScriptDirEditor.this.getProjectChildren(project);
					}
				};
				CommonElementLabelProvider lp = new CommonElementLabelProvider(cp);
				ElementTreeSelectionDialog fileDialog = new ElementTreeSelectionDialog(
						com.getShell(),
						lp,//WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider(),
						cp);//new WorkbenchContentProvider());
				fileDialog.addFilter(new JSViewerFilter());
				fileDialog.setTitle("选择基础数据脚本");
				fileDialog.setImage(AbstractUIPlugin.imageDescriptorFromPlugin("com.hundsun.ares.studio.jres.script", "icons/user_script.png").createImage());
				
				fileDialog.setInput(project);
				int returnValue = fileDialog.open();
				if (Dialog.OK == returnValue) {
					Object result = fileDialog.getFirstResult();
					if(result instanceof IARESResource){
						txtPath.setText(((IARESResource)(result)).getResource().getProjectRelativePath().toString());
					}
				}
			}
		});
	}
	
	protected IARESModuleRoot[] getProjectChildren(
			IARESProject project) {
		List<IARESModuleRoot> roots = new ArrayList<IARESModuleRoot>();
		try {
			for(IARESModuleRoot root : project.getModuleRoots()){
				if(StringUtils.equals(root.getType(), ScriptPlugin.TOOL_MODULE_ROOT_TYPE)){
					roots.add(root);
				}
			}
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return roots.toArray(new IARESModuleRoot[roots.size()]);
	}
	
	@Override
	public void setEditable(boolean editable) {
		if(txtPath != null && !txtPath.isDisposed()){
			txtPath.setEnabled(editable);
		}
		if(btnFile != null && !btnFile.isDisposed()){
			btnFile.setEnabled(editable);
		}
	}
	
	class JSViewerFilter extends ViewerFilter {

		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
				if (element instanceof IARESResource) {
					String type = ((IARESResource) element).getType();
					if ("js".equalsIgnoreCase(type)) {
						return true;
					} else {
						return false;
					}
				}
			return true;
		}
		
	}

	@Override
	public void setAresProject(IARESProject project) {
		this.project = project;
		
	}
	
}
