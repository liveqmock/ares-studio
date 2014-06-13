/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.assistant;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.cres.text.assistant.ConstantAssistantLoader;
import com.hundsun.ares.studio.cres.text.assistant.IAssistantFilter;
import com.hundsun.ares.studio.cres.text.assistant.IAssistantLoader;
import com.hundsun.ares.studio.cres.text.assistant.KeywordsAssistantLoader;
import com.hundsun.ares.studio.cres.text.assistant.ModuleAssistantFilter;
import com.hundsun.ares.studio.cres.text.assistant.ObjectParamAssistantLoader;
import com.hundsun.ares.studio.cres.text.assistant.PublicFuncAssistantLoader;
import com.hundsun.ares.studio.cres.text.assistant.ResourceAssistantLoader;
import com.hundsun.ares.studio.cres.text.assistant.StdFieldAssistantLoader;
import com.hundsun.ares.studio.cres.text.assistant.StructAssistantLoader;
import com.hundsun.ares.studio.cres.text.assistant.TextAssistant;
import com.hundsun.ares.studio.cres.text.assistant.UserMacroAssistantFilter;
import com.hundsun.ares.studio.cres.text.assistant.UserMacroAssistantLoader;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.constants.IProcedureRefType;
import com.hundsun.ares.studio.procdure.provider.ProcedureUI;

/**
 * @author wangxh
 * AP伪代码智能提示
 */
public class ProcedureTextAssistant extends TextAssistant {

	public ProcedureTextAssistant(IARESResource resource) {
		super(resource);
	}
	
	@Override
	protected void createAssistantLoader() {
		//所有标准字段和本资源内部变量的非标准字段
		IAssistantLoader loader = new StdFieldAssistantLoader(getResource());
		getLoaders().add(loader);
		
		loader = new InternalAssistantLoader(getResource());
		getLoaders().add(loader);
		//AP
		loader = new ResourceAssistantLoader(getResource(), IProcedureRefType.PROCEDURE_CNAME);
		//模块依赖过滤
		IAssistantFilter filter = new ModuleAssistantFilter(getResource().getModule()){
			/* (non-Javadoc)
			 * @see com.hundsun.ares.studio.cres.text.assistant.ModuleAssistantFilter#filter(java.lang.Object)
			 */
			@Override
			public boolean filter(Object obj) {
				//存储过程不做模块依赖过滤
				if(obj instanceof Procedure){
					if(ProcedureUI.isStock2Procedure()) {
						return true;
					}
				}
				return super.filter(obj);
			}
		};
		loader.setFilter(filter);
		getLoaders().add(loader);
		
		loader = new UserMacroAssistantLoader(getResource());
		loader.setFilter(new UserMacroAssistantFilter(UserMacroAssistantFilter.PROCEDURE_TYPE));
		getLoaders().add(loader);
		
		//公共函数
		loader = new PublicFuncAssistantLoader(getResource());
		getLoaders().add(loader);
		
		//公共函数
		loader = new StructAssistantLoader(getResource());
		getLoaders().add(loader);
		
		//输入输出对象参数
		loader = new ObjectParamAssistantLoader(getResource());
		getLoaders().add(loader);
		
		//关键词提示（如lpResultSet）
		loader = new KeywordsAssistantLoader();
		getLoaders().add(loader);
		
		//常量提示
		loader = new ConstantAssistantLoader(getResource());
		getLoaders().add(loader);
	}
}
