/**
 * 源程序名称：BizInterfaceWrap.java
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

import com.hundsun.ares.studio.biz.BizInterface;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.jres.script.api.biz.IBizInterfaceWrap;
import com.hundsun.ares.studio.jres.script.api.biz.IParameterWrap;
import com.hundsun.ares.studio.jres.script.base.ResourceWrapBase;

/**
 * @author sundl
 *
 */
public abstract class BizInterfaceWrap<T extends JRESResourceInfo> extends ResourceWrapBase<T> implements IBizInterfaceWrap{
	
	private IParameterWrap[] inputList;
	private IParameterWrap[] outputList;
	
	public BizInterfaceWrap(IARESResource resource) {
		super(resource);
	}
	
	protected abstract BizInterface getInterface();

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap#getInputParameters()
	 */
	@Override
	public IParameterWrap[] getInputParameters() {
		if (inputList == null) {
			List<IParameterWrap> params = new ArrayList<IParameterWrap>();
			for (Parameter p : getInterface().getInputParameters()) {
				params.add(new ParameterWrap(p, this.resource));
			}
			inputList = (IParameterWrap[]) params.toArray(new IParameterWrap[params.size()]);
		}
		return inputList;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap#getOutputParameters()
	 */
	@Override
	public IParameterWrap[] getOutputParameters() {
		if (outputList == null) {
			List<IParameterWrap> params = new ArrayList<IParameterWrap>();
			for (Parameter p : getInterface().getOutputParameters()) {
				params.add(new ParameterWrap(p, this.resource));
			}
			outputList = (IParameterWrap[]) params.toArray(new IParameterWrap[params.size()]);
		}
		return outputList;
	}

	@Override
	public void deleteInputParameter(String id){
		for (int i = 0; i < getInterface().getInputParameters().size(); i++) {
			Parameter p = getInterface().getInputParameters().get(i);
			if (StringUtils.equals(p.getId(), id)) {
				getInterface().getInputParameters().remove(p);
				return;
			}
		}
	}
	
	@Override
	public void deleteOutputParameter(String id){
		for (int i = 0; i < getInterface().getOutputParameters().size(); i++) {
			Parameter p = getInterface().getOutputParameters().get(i);
			if (StringUtils.equals(p.getId(), id)) {
				getInterface().getOutputParameters().remove(p);
				return;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap#isInputCollection()
	 */
	@Override
	public boolean isInputCollection() {
		return getInterface().isInputCollection();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap#isOutputCollection()
	 */
	@Override
	public boolean isOutputCollection() {
		return getInterface().isOutputCollection();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap#getId()
	 */
	@Override
	public String getId() {
		return getOriginalInfo().getObjectId();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap#getCName()
	 */
	@Override
	public String getChineseName() {
		return getOriginalInfo().getChineseName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap#getInterfaceFlag()
	 */
	@Override
	public String getInterfaceFlag() {
		return getInterface().getInterfaceFlag();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap#getDescription()
	 */
	@Override
	public String getDescription() {
		return getOriginalInfo().getDescription();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap#getVersion()
	 */
	@Override
	public String getVersion() {
		return RevisionHistoryUtil.getMaxVersion(getOriginalInfo().getHistories());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.biz.IBizServiceWrap#getUpdateDate()
	 */
	@Override
	public String getUpdateDate() {
		return RevisionHistoryUtil.getLatestUpdateDate(getOriginalInfo().getHistories());
	}

}
