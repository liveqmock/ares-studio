/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.text.assistant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

/**
 * @author wangxh
 * 增加一些关键字的提示
 */
public class KeywordsAssistantLoader extends AbstractAssistantLoader {

	//目前只有lpResultSet,以后有需要可以增加
	private final static String[] keywords = new String[]{"lpResultSet"};
	
	@Override
	public List<String> loadAssitant(String text, IDocument doc, int offset) {
		List<String> allproposals = new ArrayList<String>();
		try {
			int preOffset = offset-text.length()-1;
			if(preOffset < 0){
				//伪代码起始位置
				allproposals.addAll(Arrays.asList(keywords));
			}else{
				char c = doc.getChar(preOffset);
				if (c == '\r' || c == '\t' || c == ' ' || c == '\n'){
					allproposals.addAll(Arrays.asList(keywords));
				}
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return allproposals;
	}

}
