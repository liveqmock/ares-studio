/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.script.util.wizard;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * 统一向导第一向导页对应的WizardContentProvider
 * @author liaogc
 *
 */
public class ScriptGenInteWizardContentProvider implements ITreeContentProvider {
	protected static final Object[] NO_CHILDREN = new Object[0];

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement == null) {
			return NO_CHILDREN;
		}

		if ((parentElement instanceof ScriptGenInteWizardModel)) {
			ScriptGenInteWizardModel wpe = (ScriptGenInteWizardModel) parentElement;
			return wpe.getChildren();
		}
		return NO_CHILDREN;
	}

	public Object getParent(Object element) {
		if (element == null) {
			return null;
		}
		if ((element instanceof ScriptGenInteWizardModel)) {
			ScriptGenInteWizardModel wpe = (ScriptGenInteWizardModel) element;
			return wpe.getParent();
		}

		return null;
	}

	public boolean hasChildren(Object element) {
		if (element == null) {
			return false;
		}
		if ((element instanceof ScriptGenInteWizardModel)) {
			ScriptGenInteWizardModel wpe = (ScriptGenInteWizardModel) element;
			return wpe.isHasChild();
		}
		return false;
	}

	public Object[] getElements(Object element) {
		if (element == null) {
			return NO_CHILDREN;
		}
		if ((element instanceof Object[])) {
			Object[] new_name = (Object[]) element;
			return new_name;
		}else if(element instanceof List){
			return ((List)element).toArray(new ScriptGenInteWizardModel[((List) element).size()]);
			
		}
		return NO_CHILDREN;
	}

	@Override
	public void dispose() {
	}
}
