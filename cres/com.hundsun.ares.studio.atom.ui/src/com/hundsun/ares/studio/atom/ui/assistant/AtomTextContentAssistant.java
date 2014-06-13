/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.ui.assistant;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;

/**
 * @author liaogc
 *
 */
public class AtomTextContentAssistant extends ContentAssistant {
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.contentassist.ContentAssistant#getContentAssistProcessor(java.lang.String)
	 */
	@Override
	public IContentAssistProcessor getContentAssistProcessor(String contentType) {
		IContentAssistProcessor processor = null;
		processor = super.getContentAssistProcessor(contentType);
		if(!StringUtils.equalsIgnoreCase(contentType, IDocument.DEFAULT_CONTENT_TYPE) && processor==null){
			processor = super.getContentAssistProcessor(IDocument.DEFAULT_CONTENT_TYPE);
		}
		
		return processor;
	}

}
