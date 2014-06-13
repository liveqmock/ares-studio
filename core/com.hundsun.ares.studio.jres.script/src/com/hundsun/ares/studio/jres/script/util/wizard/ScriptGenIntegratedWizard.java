/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionConfigReader;
import com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionRoot;

/**
 *  统一生成向导:第一个向导界面列出脚本清单,第二界面列出所选择脚本中相应的配置项
 * @author liaogc
 *
 */
public class ScriptGenIntegratedWizard extends DynamicPageWizard{
	static final Logger console = ConsoleHelper.getLogger();//日志
	private ScriptGenIntegratedPage integratedPage;//向导中的第一页(列出所有的脚本清单以便供用户选择)
	private ScriptGenIntegratedDetailPage detailPage;//向导中的详细界面(列出脚本清单中对应的有xml配置的界面)
	private IARESProject project;//脚本所在项目
	private Map<String, Object>context = new HashMap<String, Object>();//基础上下文
	private Map<IARESResource,Map<String, Object>>contexts =  new HashMap<IARESResource,Map<String, Object>>();// 总的上下文
	private List<ScriptGenInteWizardModel>  inputScriptList = new ArrayList<ScriptGenInteWizardModel>();//对应选择树上的节点
	private boolean isOK = false;//用户是否点击完成按纽


	

	public ScriptGenIntegratedWizard() {
		setWindowTitle("统一生成向导");
		setNeedsProgressMonitor(true);
	}
	
	@Override
	public boolean performFinish() {
		/*根据用户对相应脚本的配置,封装成脚本的上下文环境*/
		for(ScriptGenInteWizardModel model :detailPage.getSelectedHasXmlConfig()){

			Map<String, Object>jsContext = new HashMap<String, Object>();
			if(!contexts.containsKey(model.getJsResource())){
				
				if(context!=null){
					jsContext.putAll(context);
					model.getOptionRoot().setOptionValue();
					jsContext.putAll(model.getOptionRoot().getOptionValue());
					contexts.put(model.getJsResource(), jsContext);
				}
			}else{
			
				if(model.getOptionRoot()!=null){
					 model.getOptionRoot().setOptionValue();
					jsContext.putAll(model.getOptionRoot().getOptionValue());
				}
				contexts.put(model.getJsResource(), jsContext);
			}
		
		}
		isOK = true;//用户点击完成
		return true;
		
	}
	
	/**
	 * @return the detailPage
	 */
	public ScriptGenIntegratedDetailPage getDetailPage() {
		return detailPage;
	}

	/**
	 * @param detailPage the detailPage to set
	 */
	public void setDetailPage(ScriptGenIntegratedDetailPage detailPage) {
		this.detailPage = detailPage;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performCancel()
	 */
	@Override
	public boolean performCancel() {
		return super.performCancel();
	}
	@Override
	public void addPages() {
		loadJSXML();
		integratedPage = new ScriptGenIntegratedPage("统一生成向导",inputScriptList);
		addPage(integratedPage);
		detailPage = new ScriptGenIntegratedDetailPage("生成详细信息",project);
		addPage(detailPage);
	}
	
	

	

	@Override
	public boolean canFinish() {
		return getContainer().getCurrentPage().equals(detailPage) && detailPage.isPageComplete();
	}
	
	/**
	 * 加载脚本脚本对应的配置信息
	 */
	private void loadJSXML(){
		if(inputScriptList==null || inputScriptList.size()==0){
			return ;
		}
		for(ScriptGenInteWizardModel model :inputScriptList ){
			Map<String, Object>context = new HashMap<String, Object>();
			String filepath = String.format("/%s.xml", model.getJsResource().getName());
			
			//2013年5月13日9:27:22 脚本带模块，xml文件可能不在默认模块下面，需要取脚本直接所在的模块目录
			IFolder rootFolder = (IFolder) model.getJsResource().getParent().getResource();
			
			if(rootFolder == null){
				console.error(String.format(".respath中不存在扩展点[%s]对应的模块根配置。", "com.hundsun.ares.studio.jres.moduleroot.tools"));
				return ;
			}
			IFile file =  rootFolder.getFile(filepath);
			
			if(!file.exists()){
				
				Map<String, Object>jsContext = new HashMap<String, Object>();
				if(context!=null){
					jsContext.putAll(context);
					contexts.put(model.getJsResource(), jsContext);
				}
				continue;
			}
			UserOptionConfigReader instance = new UserOptionConfigReader();
			try {
				final UserOptionRoot root = instance.read(file.getContents());
				model.setOptionRoot(root);
				
				
			} catch (Exception e) {
				console.error(String.format("获取用户输入失败，详细信息:%s", e.getMessage()));
			}
				
		}
	}
	
	public void setProject(IARESProject project) {
		this.project = project;
	}
	
	/**
	 * @return the context
	 */
	public Map<String, Object> getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(Map<String, Object> context) {
		this.context = context;
	}
	/**
	 * @param inputScriptList the inputScriptList to set
	 */
	public void setInputScriptList(List<ScriptGenInteWizardModel> inputScriptList) {
		this.inputScriptList = inputScriptList;
	}
	
	/**
	 * 获取用户选择的脚本
	 * @return
	 */
	public List<ScriptGenInteWizardModel> getSelectedElements() {
		return integratedPage.getSelectedElements();
	}

	/**
	 * @return the isOK
	 */
	public boolean isOK() {
		return isOK;
	}
	/**
	 * 根据脚本获取对应的脚本上下文环境
	 * @param jsResource
	 * @return
	 */
	public Map<String, Object> getContextByJsResource(IARESResource jsResource){
		return  contexts.get(jsResource);
	}
	/**
	 * @return the project
	 */
	public IARESProject getProject() {
		return project;
	}

	
}
