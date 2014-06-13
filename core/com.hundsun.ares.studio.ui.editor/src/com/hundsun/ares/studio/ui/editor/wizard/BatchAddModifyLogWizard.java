package com.hundsun.ares.studio.ui.editor.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.model.RevisionHistory;

public class BatchAddModifyLogWizard extends Wizard {

	BatchAddModifyLogPage page;
	IARESProject project ;
	public BatchAddModifyLogWizard(IARESProject project) {
		this.project = project;
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		page = new BatchAddModifyLogPage("批量添加修改记录",project);
		addPage(page);
	}
	
	@Override
	public boolean performFinish() {
		final List<Object>resources = page.getSelection();
		final List<RevisionHistory> input = page.getViewerInput();
		if(input.size() <= 0){
			MessageDialog.openError(getShell(), "修改记录为空", "修改记录为空，请添加修改记录！");
			return false;
		}
		
		try {
			getContainer().run(true, false, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					monitor.beginTask("批量添加修改记录", resources.size());
					addHistory(resources,input,monitor);
				}
				
			});
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		return true;
}



	public void addHistory(List<Object>resources,List<RevisionHistory> input,IProgressMonitor monitor){
		for(Object obj : resources){
			//资源
			if(obj instanceof IARESResource){
				IARESResource res = (IARESResource)obj;
				try{
					//普通资源
					JRESResourceInfo info = res.getInfo(JRESResourceInfo.class);
					if(info != null){
						for(int index = input.size() -1;index >= 0;index--){
							info.getHistories().add(0, input.get(index));
						}
						res.save(info, true, new NullProgressMonitor());
					}
					
				}catch(ARESModelException e){
					e.printStackTrace();
				}
			}
			
				//模块处理
			if(obj instanceof IARESModule){
				IARESModule module = (IARESModule)obj;
				IARESResource res = module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
				try {
					ModuleProperty mp = res.getInfo(ModuleProperty.class);
					JRESResourceInfo info = (JRESResourceInfo) mp.getMap().get(Constants.RevisionHistory.MODULE_REVISION_EXT_KEY);
					if(null == info){
						info =   CoreFactory.eINSTANCE.createModuleRevisionHistoryList();
						mp.getMap().put(Constants.RevisionHistory.MODULE_REVISION_EXT_KEY, info);
					}
					
					for(int index = input.size() -1;index >= 0;index--){
						info.getHistories().add(0, input.get(index));
					}
					
					//保存
					res.save(mp, true, new NullProgressMonitor());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			monitor.worked(1);
		}
		monitor.done();
		
	}
	
}
