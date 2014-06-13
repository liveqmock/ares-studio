/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.editor.page;

import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.logic.ResourceEngin;
import com.hundsun.ares.studio.engin.skeleton.DefaultSkeletonInput;
import com.hundsun.ares.studio.ui.editor.text.TextEditorInput;

/**
 * @author liaogc
 * 过程预览时生成代码线程
 */
public class ProcedureGenCodeThread {
	private ProcedurePreViewPage page;
	private List<String> skeletonList;
	private Thread genThread = null;
	private  ResourceEngin engin;
	private IARESResource resource;
	private  Map<Object, Object> context ;
	
	
	public  ProcedureGenCodeThread(ProcedurePreViewPage page,List<String> skeletonList, ResourceEngin engin,IARESResource resource, Map<Object, Object> context){
		this.page = page;
		this.skeletonList = skeletonList;
		this.engin = engin;
		this.resource = resource;
		this.context = context;
		this.genThread = new Thread(new ProcedureGenRun());
		
	}
	
	public void genCode(){
		genThread.start();
	}
	
	private class ProcedureGenRun implements Runnable{
		StringBuffer text = new StringBuffer();
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			
			for(String skeleton : skeletonList ){
				try {
					text.append(engin.generate(new DefaultSkeletonInput(
									skeleton,
									resource), context));
				} catch (Exception e) {
					e.printStackTrace();
					text.append(e.getMessage());
				}
				text.append("\r\n");
			}
			page.getEditor().getSite().getShell().getDisplay().asyncExec(new Runnable(){

				@Override
				public void run() {
					page.setInput(new TextEditorInput(text.toString()));
					
				}});	
		}
		
	}
	

}
