/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.logic.ui.pages;

import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.engin.format.CodeFormater;
import com.hundsun.ares.studio.engin.logic.ResourceEngin;
import com.hundsun.ares.studio.engin.skeleton.DefaultSkeletonInput;
import com.hundsun.ares.studio.ui.editor.text.TextEditorInput;

/**
 * @author liaogc
 *逻辑预览时生成代码线程
 */
public class LogicGenCodeThread {
	private LogicPreViewPage page;
	private List<String> skeletonList;
	private Thread genThread = null;
	private  ResourceEngin engin;
	private IARESResource resource;
	private  Map<Object, Object> context ;
	private boolean isCancel = false;
	
	
	public  LogicGenCodeThread(LogicPreViewPage page,List<String> skeletonList, ResourceEngin engin,IARESResource resource, Map<Object, Object> context){
		this.page = page;
		this.skeletonList = skeletonList;
		this.engin = engin;
		this.resource = resource;
		this.context = context;
		this.genThread = new Thread(new LogicGenRun());
		
	}
	
	public void genCode(boolean isCancel ){
		this.isCancel = isCancel;
		if(!isCancel){
		 genThread.start();
		}
		
	}
	
	private class LogicGenRun implements Runnable{
		String text = "";
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			
			for(String skeleton : skeletonList ){
				if(skeletonList.size()>1){
//					String fileName = UFTUtil.getFileName(resource.getName(), skeleton);
//					text += "/******************************" + fileName + "******************************/\r\n";
				}
				try {
					if(!isCancel){
						StringBuffer sb = new StringBuffer(engin.generate(
								new DefaultSkeletonInput(
										skeleton,
										resource), context));
						text += CodeFormater.formatCByForce(sb);
					}
					
//					text += sb.toString();
				} catch (Exception e) {
					e.printStackTrace();
					text += e.getMessage();
				}
				text += "\r\n";
				
				
			}
			try {
				if (page != null && page.getEditor() != null) {
					page.getEditor().getSite().getShell().getDisplay()
							.asyncExec(new Runnable() {

								@Override
								public void run() {
									if (page.getDocumentProvider() != null) {
										if(!isCancel){
											page.setInput(new TextEditorInput(text));
										}
									
									}

								}
							});
				}

			} catch (Exception e) {

			}
			
			
		}
		
	}
	

}
