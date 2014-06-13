/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.ui.block;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.editor.FormPage;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.DictionaryConflictContentProvider;
import com.hundsun.ares.studio.ui.validate.IProblemPool;

/**
 * @author liaogc
 *
 */
public class DictionaryListConflictViewerBlock extends DictionaryListOverviewViewerBlock{

	/**
	 * @param page
	 * @param editingDomain
	 * @param site
	 * @param resource
	 * @param problemPool
	 */
	public DictionaryListConflictViewerBlock(FormPage page,
			EditingDomain editingDomain, IWorkbenchPartSite site,
			IARESResource resource, IProblemPool problemPool) {
		super(page, editingDomain, site, resource, problemPool);
	}
	@Override
	protected IContentProvider getColumnViewerContentProvider() {
		return new DictionaryConflictContentProvider(getARESResource(),IMetadataRefType.Dict);
	}

}
