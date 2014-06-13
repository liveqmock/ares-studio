package com.hundsun.ares.studio.logic.script;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap;
import com.hundsun.ares.studio.jres.script.api.biz.cres.ILogicFunctionWrap;
import com.hundsun.ares.studio.jres.script.api.biz.cres.ILogicServiceWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.ILogicScriptWrap;
import com.hundsun.ares.studio.logic.constants.ILogicRefType;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author sundl
 *
 */
public class LogicScriptWrap implements ILogicScriptWrap{
	
	private static final Logger logger = Logger.getLogger(LogicScriptWrap.class);
	
	private IARESProject project;
	
	public LogicScriptWrap(IARESProject project) {
		this.project = project;
	}

	@Override
	public ILogicServiceWrap[] getLogicServices() {
		List<ILogicServiceWrap> services = new ArrayList<ILogicServiceWrap>();
		long t0 = System.currentTimeMillis();
		ReferenceManager manager = ReferenceManager.getInstance();
		List<ReferenceInfo> refList = manager.getReferenceInfos(project, ILogicRefType.LOGIC_SERVICE, true);
		for (ReferenceInfo ref : refList) {
			try {
				services.add(new LogicServiceWrap(ref.getResource()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		long t1 = System.currentTimeMillis();
		Collections.sort(services, new Comparator<ILogicServiceWrap>() {
			@Override
			public int compare(ILogicServiceWrap o1, ILogicServiceWrap o2) {
				try {
					String id1 = o1.getId();
					String id2 = o2.getId();
					return id1 == null ? 1 : id1.compareTo(id2);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 0;
			}
		});
		long t2 = System.currentTimeMillis();
		if (logger.isDebugEnabled())
			logger.debug(String.format("获取数据用时：%s, 排序用时 %s", (t1-t0), (t2-t1)));
		return services.toArray(new ILogicServiceWrap[0]);
	}

	@Override
	public ILogicFunctionWrap[] getLogicFunctions() {
		List<ILogicFunctionWrap> services = new ArrayList<ILogicFunctionWrap>();
		long t0 = System.currentTimeMillis();
		ReferenceManager manager = ReferenceManager.getInstance();
		List<ReferenceInfo> refList = manager.getReferenceInfos(project, ILogicRefType.LOGIC_FUNCTION, true);
		for (ReferenceInfo ref : refList) {
			services.add(new LogicFunctionWrap(ref.getResource()));
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
		return services.toArray(new ILogicFunctionWrap[0]);
	}

	@Override
	public ILogicServiceWrap getLSByCName(String name) {
		ReferenceManager manager = ReferenceManager.getInstance();
		ReferenceInfo ref = manager.getFirstReferenceInfo(project, ILogicRefType.LOGIC_SERVICE_CNAME, name, true);
		if (ref != null) {
			return new LogicServiceWrap(ref.getResource());
		}
		return null;
	}

	@Override
	public ILogicFunctionWrap getLFByCName(String name) {
		ReferenceManager manager = ReferenceManager.getInstance();
		ReferenceInfo ref = manager.getFirstReferenceInfo(project, ILogicRefType.LOGIC_FUNCTION_CNAME, name, true);
		if (ref != null) {
			return new LogicFunctionWrap(ref.getResource());
		}
		return null;
	}

}
