/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.script;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.script.api.biz.cres.IProcedureWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IProcedureScriptWrap;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.procdure.constants.IProcedureRefType;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author qinyuan
 *
 */
public class ProcedureScriptWrap implements IProcedureScriptWrap{

	private static final Logger logger = Logger.getLogger(ProcedureScriptWrap.class);
	
	private IARESProject project;
	
	/**
	 * @param project
	 */
	public ProcedureScriptWrap(IARESProject project) {
		this.project = project;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IProcedureScriptWrap#getProcedure()
	 */
	@Override
	public IProcedureWrap[] getProcedures() {
		List<IProcedureWrap> services = new ArrayList<IProcedureWrap>();
		long t0 = System.currentTimeMillis();
		ReferenceManager manager = ReferenceManager.getInstance();
		List<ReferenceInfo> refList = manager.getReferenceInfos(project, IProcedureRefType.PROCEDURE, true);
		for (ReferenceInfo ref : refList) {
			try {
				services.add(new ProcedureWrap(ref.getResource()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		long t1 = System.currentTimeMillis();
		Collections.sort(services, new Comparator<IProcedureWrap>() {
			@Override
			public int compare(IProcedureWrap o1, IProcedureWrap o2) {
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
		return services.toArray(new IProcedureWrap[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.IProcedureScriptWrap#getProcedureByCName(java.lang.String)
	 */
	@Override
	public IProcedureWrap getProcedureByCName(String name) {
		ReferenceManager manager = ReferenceManager.getInstance();
		ReferenceInfo ref = manager.getFirstReferenceInfo(project, IProcedureRefType.PROCEDURE_CNAME, name, true);
		if (ref != null) {
			return new ProcedureWrap(ref.getResource());
		}
		return null;
	}

}
