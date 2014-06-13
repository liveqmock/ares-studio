/**
 * 源程序名称：StdFieldDictDescColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：元数据用户编辑和UI展现相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeDictionaryType;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;

/**
 * @author gongyf
 *
 *字典条目说明的labelprovider，字典条目说明不可编辑，获取最终的引用的字典条目，比如其有 a b c d4个字典项，则显示说明内容是 
 * a.字典项(冒号)a.字典项说明(空格)b.字典项(冒号)b.字典项说明(空格)c.字典项(冒号)c.字典项说明(空格)d.字典项(冒号)d.字典项说明
 * 
 */

public class StdFieldDictDescColumnLabelProvider extends ColumnLabelProvider {
	
	private IARESResource resource;
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
	 */
	/**
	 * @param resource
	 */
	public StdFieldDictDescColumnLabelProvider(IARESResource resource) {
		super();
		this.resource = resource;
	}


	@Override
	public Color getBackground(Object element) {
		return Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
	}
	
	@Override
	public String getText(Object element) {
		StringBuffer text = new StringBuffer();
		if(element instanceof StandardField){
			StandardField std = (StandardField) element;
			if(StringUtils.isNotBlank(std.getDictionaryType())){
				DeDictionaryType dictionary = MetadataUtil.decrypt(std,resource).getDictionaryType();
				EList<DeDictionaryItem>items = dictionary.getItems();
				
				for(DeDictionaryItem item : items){
					String value = StringUtils.defaultString(item.getValue());
					String chineseName = StringUtils.defaultString(item.getChineseName());
					text.append(value);
					text.append(":");
					text.append(chineseName);
					text.append(" ");
				}
			}
			
		}
		return text.toString();
	}
}
