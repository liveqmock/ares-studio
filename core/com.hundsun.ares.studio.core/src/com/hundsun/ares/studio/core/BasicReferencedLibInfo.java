/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import java.util.Arrays;
import java.util.List;

import com.hundsun.ares.studio.internal.core.ARESBundleInfo;

/**
 * 新接口IARESBundleInfo, 保留这个实现和老接口保持兼容。
 * @author sundl
 */
public class BasicReferencedLibInfo extends ARESBundleInfo implements IBasicReferencedLibInfo {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IBasicReferencedLibInfo#getDependencyDescriptors()
	 */
	@Override
	public List<IDependenceDescriptor> getDependencyDescriptors() {
		return Arrays.asList(getDependencies());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.IBasicReferencedLibInfo#getPublishTime()
	 */
	@Override
	public String getPublishTime() {
		return getPubTime();
	}
	
	public void setPublishTime(String time) {
		setPubTime(time);
	}
	
}
