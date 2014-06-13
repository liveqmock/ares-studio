package com.hundsun.ares.studio.cres.text.assistant;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import com.hundsun.ares.studio.core.IARESResource;

public class PublicFuncAssistantLoader extends AbstractAssistantLoader {
	
	IARESResource resource;
	
	public PublicFuncAssistantLoader (IARESResource resource){
		this.resource = resource;
	}
	
	@Override
	public List<String> loadAssitant(String text,IDocument doc,int offset) {
		List<String> allproposals = new ArrayList<String>();
		try {
			int preOffset = offset-text.length()-1;
			if(preOffset < 0){
				allproposals.addAll(CustomFunctionFactory.eINSTANCE.getAllPublicFunctions(resource.getARESProject()));
			}else{
				char c = doc.getChar(preOffset);
				if (c == '\r' || c == '\t' || c == ' ' || c == '\n'){
					allproposals.addAll(CustomFunctionFactory.eINSTANCE.getAllPublicFunctions(resource.getARESProject()));
				}
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return allproposals;
	}
	
	@Override
	public String getReplacement(String display) {
		//大小写开头，接着可以有大小写字母、数字、下划线，接着可以由空格，以(结尾
		Pattern pattern = Pattern.compile("\\b[a-zA-Z]+[a-zA-Z0-9_]*\\s*\\(");
		Matcher matcher = pattern.matcher(display);
		if(matcher.find()){
			String replace = matcher.group();
			List<String> params = getParams(display.substring(matcher.start()));
			for(String param : params){
				replace += param;
			}
			return replace;
		}
		return display;
	}

	/**
	 * @param display
	 * @return
	 */
	private List<String> getParams(String display) {
		List<String> list = new ArrayList<String>();
		//大小写字母开头，接着可以有大小写字母、数字、下划线，接着可以由空格，以逗号或者)结尾
		Pattern pattern = Pattern.compile("\\b[a-zA-Z]+[a-zA-Z0-9_]*\\s*[,|\\)]");
		Matcher matcher = pattern.matcher(display);
		while(matcher.find()){
			list.add(matcher.group());
		}
		if(list.isEmpty()){
			list.add(")");
		}
		return list;
	}

}
