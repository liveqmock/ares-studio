/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.database.oracle.ui.viewer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.fieldassist.IContentProposal;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleUser;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.cellEditor.AresContentProposalProvider;

/**
 * @author qinyuan
 *
 */
public class OracleUserProposalProvider extends AresContentProposalProvider{
	
	protected String resType;
	protected IARESProject project;
	
	/**
	 * 
	 */
	public OracleUserProposalProvider(String resType, IARESProject project) {
		super();
		this.resType = resType;
		this.project = project;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.cellEditor.AresContentProposalProvider#updateContent(java.lang.Object)
	 */
	@Override
	public void updateContent(Object element) {
		List<ReferenceInfo> infoList = ReferenceManager.getInstance().getReferenceInfos(project, resType, true);
		List<OracleUser> inputItems = new ArrayList<OracleUser>();
		if (infoList != null) {
			for (Object inputItem : infoList) {
				
				if(inputItem instanceof ReferenceInfo){
					Object obj = ((ReferenceInfo)inputItem).getObject();
					
					if(obj instanceof OracleUser) {
						OracleUser user = (OracleUser)obj;
						if (user.isEnable()) {
							inputItems.add(user);
						}
					}
					
				}
			}
		}
		
		setInput(inputItems.toArray(new OracleUser[0]));
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.cellEditor.AresContentProposalProvider#getProposals(java.lang.String, int)
	 */
	@Override
	public IContentProposal[] getProposals(String contents, int position) {
		List<IContentProposal> proposals = new ArrayList<IContentProposal>();
		
		if (input != null) {
			for (Object obj : input) {
				if(obj instanceof OracleUser) {
					OracleUser user = (OracleUser)obj;
					String name = user.getName();
					String desc = user.getDecription();
					if (StringUtils.startsWithIgnoreCase(name, contents)) {
						IContentProposal proposal = new OracleUserContentProposal(name,desc);
						if (proposal != null) {
							proposals.add(proposal);
						}
					}

					
				}
			}
		}

		return proposals.toArray(new IContentProposal[0]);
	}

	
	class OracleUserContentProposal implements IContentProposal {
		String name;
		String label;

		/**
		 * @param name
		 * @param desc
		 */
		public OracleUserContentProposal(String name, String desc) {
			this.name = name;
			this.label = name + "--->" + desc;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.fieldassist.IContentProposal#getContent()
		 */
		@Override
		public String getContent() {
			return name;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.fieldassist.IContentProposal#getCursorPosition()
		 */
		@Override
		public int getCursorPosition() {
			return name.length();
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
		 */
		@Override
		public String getLabel() {
			return label;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.fieldassist.IContentProposal#getDescription()
		 */
		@Override
		public String getDescription() {
			return null;
		}
		
	}
}
