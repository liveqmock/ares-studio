/**
 * 源程序名称：ObjectScriptWrapImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.script;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.script.api.biz.IBizObjectWrap;
import com.hundsun.ares.studio.jres.script.api.biz.IObjScriptWrap;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * 
 * @author sundl
 *
 */
public class ObjectScriptWrapImpl implements IObjScriptWrap{
	
	private IARESProject project;
	
	public ObjectScriptWrapImpl(IARESProject project) {
		this.project = project;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IObjScriptWrap#getObjects()
	 */
	@Override
	public IBizObjectWrap[] getObjects() {
		List<IBizObjectWrap> objects = new ArrayList<IBizObjectWrap>();
		List<ReferenceInfo> refList = ReferenceManager.getInstance().getReferenceInfos(project, IBizRefType.Object, true);
		for (ReferenceInfo ref : refList) {
			objects.add(new BizObjectWrap(ref.getResource()));
		}
		return objects.toArray(new IBizObjectWrap[0]);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IObjScriptWrap#getObjectByName(java.lang.String)
	 */
	@Override
	public IBizObjectWrap getObjectByName(String name) {
		ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, IBizRefType.Object, name, true);
		if (ref != null)
			return new BizObjectWrap(ref.getResource());
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IObjScriptWrap#getObjectsBySubsys(java.lang.String)
	 */
	@Override
	public IBizObjectWrap[] getObjectsBySubsys(String subsysName) {
		List<IBizObjectWrap> objects = new ArrayList<IBizObjectWrap>();
		List<ReferenceInfo> refList = ReferenceManager.getInstance().getReferenceInfos(project, IBizRefType.Object, true);
		for (ReferenceInfo ref : refList) {
			IARESResource res = ref.getResource();
			IARESModule module = ARESElementUtil.getTopModule(res);
			if (module.getElementName().equals(subsysName))
				objects.add(new BizObjectWrap(res));
		}
		return objects.toArray(new IBizObjectWrap[0]);
	}

	@Override
	public IBizObjectWrap[] getObjectsByModule(String moduleName) {
		List<IBizObjectWrap> objects = new ArrayList<IBizObjectWrap>();
		List<ReferenceInfo> refList = ReferenceManager.getInstance().getReferenceInfos(project, IBizRefType.Object, true);
		for (ReferenceInfo ref : refList) {
			IARESResource res = ref.getResource();
			if (StringUtils.equals(res.getModule().getElementName(), moduleName))
				objects.add(new BizObjectWrap(res));
		}
		return objects.toArray(new IBizObjectWrap[0]);
	}
	
}
