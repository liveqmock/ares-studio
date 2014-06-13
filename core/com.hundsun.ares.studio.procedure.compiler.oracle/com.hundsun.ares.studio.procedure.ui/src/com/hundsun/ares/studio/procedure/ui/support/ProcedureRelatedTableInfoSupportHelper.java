/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.procedure.ui.support;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.fieldassist.IContentProposal;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.ui.assist.JRESContentPorposalHelper;
import com.hundsun.ares.studio.ui.cellEditor.ARESContentProposal;
import com.hundsun.ares.studio.ui.cellEditor.IContentProposalProviderHelper;

/**
 * @author qinyuan
 *
 */
public class ProcedureRelatedTableInfoSupportHelper extends JRESContentPorposalHelper implements IContentProposalProviderHelper{

	@Override
	protected IContentProposal getProposal(String contents, int position, EObject item, IARESResource resource) {
		if (item instanceof TableResourceData && resource != null) {
			TableResourceData table = (TableResourceData) item;
			String content = table.getName();
			String chineseName = table.getChineseName();
			if (content == null || !content.toUpperCase().contains(contents.toUpperCase())) {
				return null;
			}
			return new ARESContentProposal(content, chineseName);
		}
		return null;
	}

}
