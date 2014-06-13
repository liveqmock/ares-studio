package com.hundsun.ares.studio.jres.service.script;

import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.script.BizInterfaceWrap;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap;
import com.hundsun.ares.studio.jres.service.Service;

/**
 * @author sundl
 *
 */
public class BizServiceWrap extends BizInterfaceWrap<Service> implements IBizServiceWrap{

	public BizServiceWrap(IARESResource resource) {
		super(resource);
	}

	@Override
	public Class<Service> getInfoClass() {
		return Service.class;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.script.BizInterfaceWrap#getInterface()
	 */
	@Override
	protected BizInterface getInterface() {
		return getOriginalInfo().getInterface();
	}

}
