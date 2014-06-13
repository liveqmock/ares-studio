/**
 * 源程序名称：BizPropertyControl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.jres.metadata.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.jres.metadata.ui.script.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfig;
import com.hundsun.ares.studio.jres.script.internal.useroption.MultiSelectionCheckControl;
import com.hundsun.ares.studio.jres.script.internal.useroption.UserOptionControl;

/**
 * @author sundl
 *
 */
public class BizPropertyControl extends UserOptionControl {
	
	public void setOptionValue(Map<String, Object> option) {
		if (control != null) {
			MultiSelectionCheckControl multiSelectionCheckControl = (MultiSelectionCheckControl) control;
			Object[] selection = multiSelectionCheckControl.getSelected();
			List<String> ids = new ArrayList<String>();
			for (Object obj : selection) {
				if (obj instanceof BizPropertyConfig) {
					BizPropertyConfig bizConfig = (BizPropertyConfig) obj;
					ids.add(bizConfig.getName());
				}
			}
			option.put(getID(), StringUtils.join(ids, ','));
		}
	}
	
}
