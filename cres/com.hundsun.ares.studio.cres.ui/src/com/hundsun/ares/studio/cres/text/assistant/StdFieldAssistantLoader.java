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
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author wangxh
 * 提示标准字段
 */
public class StdFieldAssistantLoader extends AbstractAssistantLoader {

	private final static String PREFIX = "@";
	private final static String SPLIT = " - ";
	
	IARESResource resource;
	
	public StdFieldAssistantLoader(IARESResource resource){
		this.resource = resource;
	}
	
	@Override
	public List<String> loadAssitant(String text,IDocument doc,int offset) {
		List<String> allproposals = new ArrayList<String>();
		if(text.startsWith(PREFIX)){
			//提示所有的标准字段
			List<ReferenceInfo> refers = ReferenceManager.getInstance().getReferenceInfos(resource.getARESProject(), IMetadataRefType.StdField, true);
			for(ReferenceInfo info : refers){
				if(info != null){
					Object obj = info.getObject();
					if(obj instanceof StandardField){
						StandardField field = (StandardField)obj;
						allproposals.add(PREFIX + info.getRefName() + SPLIT + field.getChineseName());
					}
				}
			}
		}
		return allproposals;
	}
	
	@Override
	public String getReplacement(String display) {
		int index = display.indexOf(SPLIT);
		if(index > 0){
			display = display.substring(0,index);
		}
		return display;
	}
	
	@Override
	public String getDescription(String display) {
		if(display.startsWith(PREFIX)){
			display = display.substring(1);
		}
		ReferenceInfo info = ReferenceManager.getInstance().getFirstReferenceInfo(resource.getARESProject(), IMetadataRefType.StdField, getReplacement(display), true);
		if(info == null){
			return null;
		}
		Object obj = info.getObject();
		if(obj instanceof StandardField){
			StandardField field = (StandardField)obj;
			return getStdDesc(field);
		}
		return null;
	}
	
	//标准字段说明信息（数据字典信息+说明）
	private String getStdDesc(StandardField field){
		StringBuffer text = new StringBuffer();
		String dictTypeStr = field.getDictionaryType();
		if(StringUtils.isNotBlank(dictTypeStr)){
			ReferenceInfo  dictReferenceInfo = ReferenceManager.getInstance().getFirstReferenceInfo(resource.getARESProject(),IMetadataRefType.Dict,dictTypeStr,true);
			if(dictReferenceInfo != null){
				DictionaryType objDictionaryType = (DictionaryType) dictReferenceInfo.getObject();
				if(objDictionaryType!=null){
					for(DictionaryItem item : objDictionaryType.getItems()){
						String value = StringUtils.defaultString(item.getValue());
						String chineseName = StringUtils.defaultString(item.getChineseName());
						text.append(objDictionaryType.getName());
						text.append(":");
						text.append(objDictionaryType.getChineseName());
						text.append("-");
						text.append(value);
						text.append(":");
						text.append(chineseName);
						text.append("\r\n");
					}
				}
			}
		}
		if(StringUtils.isNotBlank(text.toString()) && StringUtils.isNotBlank(field.getDescription())){
				text.append("\r\n");
				text.append(field.getDescription());
		}
		return StringUtils.defaultString(StringUtils.defaultIfBlank(text.toString(), field.getDescription())) ;
	}
}
