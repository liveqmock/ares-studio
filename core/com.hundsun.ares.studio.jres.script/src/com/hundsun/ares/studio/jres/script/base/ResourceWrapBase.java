/**
 * 
 */
package com.hundsun.ares.studio.jres.script.base;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.NullProgressMonitor;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ConsoleHelper;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.script.api.model.IResourceModelWrap;
import com.hundsun.ares.studio.jres.script.api.wrap.IARESModuleWrap;
import com.hundsun.ares.studio.jres.script.util.impl.ARESModuleWrap;

/**
 * @author yanwj06282
 *
 */
public abstract class ResourceWrapBase<T> extends ScriptModelWrapBase<T> implements IResourceModelWrap{

	private static Logger logger = Logger.getLogger(ResourceWrapBase.class);
	
	private boolean isWorkingCopy = false;
	private T info;
	
	public ResourceWrapBase(IARESResource resource) {
		super(resource);
	}

	public void becomeWorkingCopy() {
		isWorkingCopy = true;
	}

	public abstract Class<T> getInfoClass();
	
	@Override
	public T getOriginalInfo() {
		if (info == null) {
			try {
				if (isWorkingCopy) {
					info = resource.getWorkingCopy(getInfoClass());
				}else {
					info = resource.getInfo(getInfoClass());
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
		}
		return info;
	}
	
	public void save(){
		try {
			resource.save(getOriginalInfo(), true, new NullProgressMonitor());
		} catch (ARESModelException e) {
			ConsoleHelper.getLogger().error("保存资源[" + resource.getPath() + "]时发生错误，可能是资源不存在或只读。", e);
		}
	}
	
	public String getFullyQualifiedName() {
		return resource.getFullyQualifiedName();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.model.IResourceModelWrap#getTopModule()
	 */
	@Override
	public IARESModuleWrap getTopModule() {
		IARESModule top = ARESElementUtil.getTopModule(resource);
		return new ARESModuleWrap(top);
	}

}
