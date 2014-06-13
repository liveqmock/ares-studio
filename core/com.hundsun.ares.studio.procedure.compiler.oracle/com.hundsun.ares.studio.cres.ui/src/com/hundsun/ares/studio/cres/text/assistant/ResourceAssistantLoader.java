/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.cres.text.assistant;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.text.IDocument;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.editor.text.TextUtil;

/**
 * @author wangxh
 * 根据类型加载智能提示项
 */
public class ResourceAssistantLoader extends AbstractAssistantLoader {
	private String PREFIX = "[";
	private String SUFFIX = "]";
	
	private IARESResource resource;
	private String type = "";
	
	public ResourceAssistantLoader(IARESResource resource, String type){
		this.resource = resource;
		this.type = type;
	}
	
	@Override
	public List<String> loadAssitant(String text,IDocument doc,int offset) {
		List<String> allproposals = new ArrayList<String>();
		if(StringUtils.startsWith(text, PREFIX) && !TextUtil.isAfterMacro(doc, offset)){
			List<ReferenceInfo> references = ReferenceManager.getInstance().getReferenceInfos(resource.getARESProject(), type, true);
			if(references != null){
				if(filter != null){
					//过滤前的初始化操作，比如查找模块依赖项等
					filter.init();
				}
				for(ReferenceInfo info : references){
					if(info != null){
						if(filter == null){
							allproposals.add(PREFIX + info.getRefName() + SUFFIX);
						}else if(filter.filter(info.getResource())){
							//需要进行过滤，主要是有以下几种：
							//LS中提示LF（只提示依赖模块）
							//LF提示LF（只提示依赖模块）
							//AS提示AF,AP（只提示依赖模块）
							//AF提示AF,AP（只提示依赖模块）
							//AP提示AP（只提示依赖模块）
							allproposals.add(PREFIX + info.getRefName() + SUFFIX);
						}
					}
				}
			}
		}
		return allproposals;
	}
}
