/**
 * 
 */
package com.hundsun.ares.studio.jres.database.resources;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.jres.modelconvert.EMFModelConverter;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;

/**
 * 数据库的模型转换类，目前只是临时处理扩展模型没有初始化的问题
 * @author gongyf
 *
 */
public class DBModelConverter extends EMFModelConverter {

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.modelconvert.EMFModelConverter#processInfoOnRead(org.eclipse.emf.ecore.EObject, com.hundsun.ares.studio.core.IARESResource)
	 */
	@Override
	protected void processInfoOnRead(Object info, IARESResource resource) {
		super.processInfoOnRead(info, resource);
		if (info instanceof ExtensibleModel) {
			ExtensibleModelUtils.extend(resource, (ExtensibleModel) info, false);
		}
	}
}
