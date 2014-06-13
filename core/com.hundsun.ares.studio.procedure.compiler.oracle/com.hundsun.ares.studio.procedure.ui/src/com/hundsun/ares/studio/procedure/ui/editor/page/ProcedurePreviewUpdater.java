/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.editor.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.logic.ResourceEngin;
import com.hundsun.ares.studio.procdure.provider.ProcedureUI;
import com.hundsun.ares.studio.procedure.compiler.constant.IProcedureEngineContextConstant;
import com.hundsun.ares.studio.ui.editor.text.TextEditorInput;

/**
 * @author qinyuan
 *
 */
public class ProcedurePreviewUpdater implements IPageChangedListener {
	
	private List<String> skeletonList = new ArrayList<String>();
	
	public ProcedurePreviewUpdater(String skeleton){
		skeletonList.add(skeleton);
	}
	
	public ProcedurePreviewUpdater(String[] skeletons) {
		skeletonList.addAll(Arrays.asList(skeletons));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IPageChangedListener#pageChanged(org.eclipse.jface.dialogs.PageChangedEvent)
	 */
	@Override
	public void pageChanged(PageChangedEvent event) {
		if (event.getSelectedPage() instanceof ProcedurePreViewPage) {
			ProcedurePreViewPage page = (ProcedurePreViewPage) event.getSelectedPage();
			ResourceEngin engin = new ResourceEngin();
			Map<Object, Object> context = new HashMap<Object, Object>();
			IARESResource resource = page.getEditor().getARESResource();
			context.put(IProcedureEngineContextConstant.ResourceModel, page.getEditor().getInfo());
//			context.put(IProcedureEngineContextConstant.has_stock_two_ex, ProcedurePageHelper.hasProcedureStock2ExtendPage());
			context.put(IProcedureEngineContextConstant.auto_define_input_param, 
					ProcedureUI.getPlugin().getPreferenceStore().getBoolean(ProcedureUI.PER_AUTO_DEFINE_PROC_INPARAM));
			context.put(IProcedureEngineContextConstant.not_define_connect_type, 
					ProcedureUI.getPlugin().getPreferenceStore().getBoolean(ProcedureUI.PER_NOT_DEFINE_CONNECT_TYPE));
			context.put(IProcedureEngineContextConstant.return_error_info, 
					ProcedureUI.getPlugin().getPreferenceStore().getBoolean(ProcedureUI.PER_RETURN_ERROR_INFO));
			context.put(IProcedureEngineContextConstant.gen_related_info, 
					ProcedureUI.getPlugin().getPreferenceStore().getBoolean(ProcedureUI.PER_GEN_RELATED_INFO));
			
			if(ProcedureUI.isStock2Procedure()) {
				context.put(IProcedureEngineContextConstant.gen_begin_code, 
						ProcedureUI.getPlugin().getPreferenceStore().getBoolean(ProcedureUI.PER_GEN_BEGIN_CODE));
				context.put(IProcedureEngineContextConstant.gen_end_code, 
						ProcedureUI.getPlugin().getPreferenceStore().getBoolean(ProcedureUI.PER_GEN_END_CODE));
			}else {
				context.put(IProcedureEngineContextConstant.gen_begin_code, false);
				context.put(IProcedureEngineContextConstant.gen_end_code, false);

			}

			
//			IARESProject project = resource.getARESProject();
//			context.put(IEngineContextConstant.Aresproject, project);
//			IFunctionMacroTokenService funcService = DataServiceManager.getInstance().getService(project, IFunctionMacroTokenService.class);
//			context.put(IEngineContextConstant.Function_Macro_Service, funcService);
//			context.put(IEngineContextConstant.UserMacro_Service, DataServiceManager.getInstance().getService(project,IUserMacroTokenService.class));
			String text = "正在生成代码......";
			page.setInput(new TextEditorInput(text));
			ProcedureGenCodeThread procedureGenCodeThread = new ProcedureGenCodeThread(page,skeletonList,engin,resource,context);
			procedureGenCodeThread.genCode();
		}
	}


}
