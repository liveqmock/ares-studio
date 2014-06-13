/**
 * 源程序名称：ErrnoColumnLabelProvider.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.ui.editor.page;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.biz.ErrorInfo;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.editor.viewers.EObjectColumnLabelProvider;

/**
 * @author sundl
 *
 */
public class ErrnoColumnLabelProvider extends EObjectColumnLabelProvider {

	private IARESProject project;
	/**
	 * @param attribute
	 */
	public ErrnoColumnLabelProvider(EStructuralFeature attribute, IARESProject project) {
		super(attribute);
		this.project = project;
	}

	protected EObject getOwner(Object element) {
		if (element instanceof ErrorInfo) {
			ErrorNoItem item = (ErrorNoItem) element;
			ReferenceManager manager = ReferenceManager.getInstance();
			ReferenceInfo refInfo = manager.getFirstReferenceInfo(project, IMetadataRefType.ErrNo_No, item.getNo(), false);
			if (refInfo != null) {
				ErrorNoItem refObj = (ErrorNoItem) refInfo.getObject();
				if (refObj != null) {
					return refObj;
				}
			}
		}
		return null;
	}
	
}
