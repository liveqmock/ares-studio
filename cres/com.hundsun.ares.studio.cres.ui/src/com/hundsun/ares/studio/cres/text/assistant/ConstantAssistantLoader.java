package com.hundsun.ares.studio.cres.text.assistant;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

public class ConstantAssistantLoader extends AbstractAssistantLoader {
	
	IARESResource resource;
	
	public ConstantAssistantLoader (IARESResource resource){
		this.resource = resource;
	}
	
	@Override
	public List<String> loadAssitant(String text,IDocument doc,int offset) {
		try {
			int preOffset = offset-text.length()-1;
			if(preOffset < 0){
				//伪代码开始处
				return getAllConstants("");
			}else{
				char c = doc.getChar(preOffset);
				//如果前一个字段是换行空格
				if (c == '\r' || c == '\t' || c == ' ' || c == '\n'){
					return getAllConstants("");
				}else if(doc.getChar(preOffset+1) == '['){
					//方便提示这种形式：[函数报错返回][ERR_630001][新增系统操作日志失败]
					return getAllConstants("[");
				}
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}
	
	/**
	 * 获取所有常量
	 * @param prefix 前缀，如[（以后可能会增加=之类的）
	 * @return
	 */
	private List<String> getAllConstants(String prefix){
		List<String> contants = new ArrayList<String>();
		//用户常量
		List<ReferenceInfo> infos = ReferenceManager.getInstance().getReferenceInfos(resource.getARESProject(), IMetadataRefType.Const, true);
		//错误号常量
		infos.addAll(ReferenceManager.getInstance().getReferenceInfos(resource.getARESProject(), IMetadataRefType.ErrNo, true));
		//数据字典常量
		infos.addAll(ReferenceManager.getInstance().getReferenceInfos(resource.getARESProject(), IMetadataRefType.Dictionary_Const, true));
		for(ReferenceInfo info : infos){
			contants.add(prefix + info.getRefName());
		}
		return contants;
	}

}
