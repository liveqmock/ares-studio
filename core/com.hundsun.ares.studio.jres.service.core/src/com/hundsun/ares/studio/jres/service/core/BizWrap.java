package com.hundsun.ares.studio.jres.service.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.script.api.biz.IBizScriptWrap;
import com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap;
import com.hundsun.ares.studio.jres.service.Service;
import com.hundsun.ares.studio.jres.service.script.BizServiceWrap;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author sundl
 *
 */
public class BizWrap implements IBizScriptWrap {
	private static final Logger logger = Logger.getLogger(BizWrap.class);
	
	private IARESProject project;
	
	public BizWrap(IARESProject project) {
		this.project = project;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IBizScriptWrap#getServices()
	 */
	@Override
	public IBizServiceWrap[] getServices() {
		List<IBizServiceWrap> services = new ArrayList<IBizServiceWrap>();
		long t0 = System.currentTimeMillis();
		ReferenceManager manager = ReferenceManager.getInstance();
		List<ReferenceInfo> refList = manager.getReferenceInfos(project, IBizRefType.Service, true);
		for (ReferenceInfo ref : refList) {
			services.add(new BizServiceWrap(ref.getResource()));
		}
		long t1 = System.currentTimeMillis();
		Collections.sort(services, new Comparator<IBizServiceWrap>() {
			@Override
			public int compare(IBizServiceWrap o1, IBizServiceWrap o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		long t2 = System.currentTimeMillis();
		if (logger.isDebugEnabled())
			logger.debug(String.format("获取数据用时：%s, 排序用时 %s", (t1-t0), (t2-t1)));
		return services.toArray(new IBizServiceWrap[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IBizScriptWrap#getServiceByName(java.lang.String)
	 */
	@Override
	public IBizServiceWrap getServiceByName(String name) {
		ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, IBizRefType.Service, name, true);
		if (ref != null)
			return new BizServiceWrap(ref.getResource());
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IBizScriptWrap#getServiceByCName(java.lang.String)
	 */
	@Override
	public IBizServiceWrap getServiceByCName(String name) {
		ReferenceManager manager = ReferenceManager.getInstance();
		List<ReferenceInfo> refList = manager.getReferenceInfos(project, IBizRefType.Service, true);
		for (ReferenceInfo ref : refList) {
			Service service = (Service) ref.getObject();
			if (service != null && StringUtils.equals(name, service.getChineseName())) 
				return new BizServiceWrap(ref.getResource());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IBizScriptWrap#getServicesBySubsys(java.lang.String)
	 */
	@Override
	public IBizServiceWrap[] getServicesBySubsys(String subsysName) {
		List<IBizServiceWrap> services = new ArrayList<IBizServiceWrap>();
		ReferenceManager manager = ReferenceManager.getInstance();
		List<ReferenceInfo> refList = manager.getReferenceInfos(project, IBizRefType.Service, true);
		for (ReferenceInfo ref : refList) {
			IARESResource res = ref.getResource();
			IARESModule module = ARESElementUtil.getTopModule(res);
			if (module.getElementName().equals(subsysName)) {
				services.add(new BizServiceWrap(res));
			}
		}
		return services.toArray(new IBizServiceWrap[0]);
	}

	public IBizServiceWrap[] getServicesByModule(String moduleName) {
		List<IBizServiceWrap> services = new ArrayList<IBizServiceWrap>();
		ReferenceManager manager = ReferenceManager.getInstance();
		List<ReferenceInfo> refList = manager.getReferenceInfos(project, IBizRefType.Service, true);
		for (ReferenceInfo ref : refList) {
			IARESResource res = ref.getResource();
			if (StringUtils.equals(res.getModule().getElementName(), moduleName)) {
				services.add(new BizServiceWrap(res));
			}
		}
		return services.toArray(new IBizServiceWrap[0]);
	}
	
}
