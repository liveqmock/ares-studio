package com.hundsun.ares.studio.biz.ui.action;

import org.eclipse.swt.widgets.Item;

import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.ui.action.LinkOpenResourceAction;

/**
 * 打开参数对应的对象资源的Action
 * @author sundl
 *
 */
public class ParamLinkOpenObjectAction extends LinkOpenResourceAction {

	public ParamLinkOpenObjectAction(IARESProject project) {
		setProject(project);
		setRefType(IBizRefType.Object);
	}
	
	@Override
	protected String getRefText(Item item) {
		Object obj = item.getData();
		if (obj instanceof Parameter) {
			return ((Parameter) obj).getType();
		}
		return null;
	}

}
