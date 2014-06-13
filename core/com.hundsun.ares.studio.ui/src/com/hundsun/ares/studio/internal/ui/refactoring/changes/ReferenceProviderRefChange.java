/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring.changes;

import org.apache.commons.lang.StringUtils;
import org.eclipse.ltk.core.refactoring.Change;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.IReferenceProvider;
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.util.ReferenceUtil;
import com.hundsun.ares.studio.ui.refactoring.AresResourceChange;

/**
 * 
 * @author sundl
 */
public class ReferenceProviderRefChange extends AresResourceChange{

	private String refType;
	private String oldRefName;
	private String newRefName;
	
	public ReferenceProviderRefChange(IARESResource element, String refType, String oldName, String newName) {
		super(element);
		this.refType = refType;
		this.oldRefName = oldName;
		this.newRefName = newName;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.Change#getName()
	 */
	@Override
	public String getName() {
		return String.format("资源%s中的引用%s更新为%s", resource.getElementName(), oldRefName, newRefName);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.refactoring.AresResourceChange#getInfoClassType()
	 */
	@Override
	protected Class<?> getInfoClassType() {
		return IReferenceProvider.class;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.refactoring.AresResourceChange#createUndoChange()
	 */
	@Override
	protected Change createUndoChange() {
		return new ReferenceProviderRefChange(resource, refType, newRefName, oldRefName);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.refactoring.AresResourceChange#modifyInfo(java.lang.Object)
	 */
	@Override
	protected void modifyInfo(Object info) {
		for (Reference ref : ReferenceUtil.INSTANCE.getReferences(info, resource.getARESProject())) {
			if (StringUtils.equals(refType, ref.getType()) && StringUtils.equals(ref.getValue(), oldRefName)) {
				ref.setValue(newRefName);
			}
		}
	}

}
