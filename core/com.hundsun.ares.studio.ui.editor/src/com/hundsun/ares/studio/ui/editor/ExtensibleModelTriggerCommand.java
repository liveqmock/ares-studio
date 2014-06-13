/**
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 */
package com.hundsun.ares.studio.ui.editor;

import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;

public class ExtensibleModelTriggerCommand extends RecordingCommand {

	private IARESResource resource;
	private List<ExtensibleModel> models;
	

	/* (non-Javadoc)
	 * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
	 */
	/**
	 * @param domain
	 * @param resource
	 * @param models
	 */
	public ExtensibleModelTriggerCommand(TransactionalEditingDomain domain,
			IARESResource resource, List<ExtensibleModel> models) {
		super(domain);
		this.resource = resource;
		this.models = models;
	}


	@Override
	protected void doExecute() {
		for (ExtensibleModel model : models) {
			ExtensibleModelUtils.extend(resource, model, false);
		}
	}
	
}