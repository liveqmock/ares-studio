/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.text.assistant;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.text.IDocument;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.ui.editor.text.TextUtil;
import com.hundsun.ares.studio.usermacro.UserMacro;
import com.hundsun.ares.studio.usermacro.UserMacroItem;
import com.hundsun.ares.studio.usermacro.constants.IUserMacroRefType;

/**
 * @author wangxh
 *
 */
public class UserMacroAssistantLoader extends AbstractAssistantLoader {
	private final static String PREFIX = "[";
	private final static String SUFFIX = "]";
	
	IARESResource resource;
	
	public UserMacroAssistantLoader(IARESResource resource) {
		this.resource = resource;
	}
	
	@Override
	public List<String> loadAssitant(String text,IDocument doc,int offset) {
		List<String> allproposals = new ArrayList<String>();
		if(StringUtils.startsWith(text, PREFIX) && !TextUtil.isAfterMacro(doc, offset)){
			IARESProject pro = resource.getARESProject();
			try {
				//获取用户自定义宏资源
				IARESResource[] resources = pro.getResources(new String[]{IUserMacroRefType.USER_MACRO,IUserMacroRefType.SYSTEM_MACRO});
				if(resources != null && resources.length > 0){
					for(IARESResource res : resources){
						UserMacro macro = res.getInfo(UserMacro.class);
						if(macro != null){
							for(UserMacroItem item : macro.getMacroItems()){
								if(filter == null){
									allproposals.add(getProposal(item));
								}else if(filter.filter(item)){
									allproposals.add(getProposal(item));
								}
							}
						}
					}
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		return allproposals;
	}
	
	private String getProposal(UserMacroItem item){
		String text = PREFIX + item.getName() + SUFFIX + item.getSequence();
//		String sequence = item.getSequence();
//		if(StringUtils.isNotBlank(sequence)){
//			text += sequence;
//		}
		return text;
	}

}
