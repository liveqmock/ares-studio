/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ListSelectionDialog;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IResPathEntry;
import com.hundsun.ares.studio.ui.ARESElementLabelProvider;
import com.hundsun.ares.studio.ui.ARESElementSorter;


/**
 * 添加引用工程（项目）
 * @author sundl
 */
public class AddProjectToResPathAction extends AddToResPathAction {

	public AddProjectToResPathAction(Shell shell) {
		super(shell, "添加引用工程");
	}
	
	public void run() {
		Object[] projects = getNotYetRequiredProjects();
		ListSelectionDialog dialog = new ListSelectionDialog(shell, projects, new ArrayContentProvider(), new ARESElementLabelProvider(), "选择依赖项目") {
		    // 增加排序
			protected Control createDialogArea(Composite parent) {
		    	Control ctrl = super.createDialogArea(parent);
		    	getViewer().setSorter(new ARESElementSorter());
		    	return ctrl;
		    }
		};
		dialog.setTitle("依赖项目");
		if (dialog.open() == Dialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length > 0) {
				List<IResPathEntry> entries = new ArrayList<IResPathEntry>();
				for (Object obj : result) {
					IARESProject aresProject = (IARESProject) obj;
					IPath path = aresProject.getPath();
					entries.add(ARESCore.newProjectEntry(path, getProjectType(aresProject)));
				}
				addResEntryToResPath(entries.toArray(new IResPathEntry[0]), ARESCore.create(project));
			}
		}
	}

	private Object[] getNotYetRequiredProjects() {
		IARESProject currentProject = ARESCore.create(project);
		List<IARESProject> selectable= new ArrayList<IARESProject>();
		try {
			selectable.addAll(Arrays.asList(currentProject.getARESModel().getARESProjects()));
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		selectable.remove(currentProject);

		IARESProject[] alreadyRequired = currentProject.getRequiredProjects();
		for (int i= 0; i < alreadyRequired.length; i++) {
			selectable.remove(alreadyRequired[i]);
		}
		
		// 只能添加指定类型的project
		for (Iterator<IARESProject> it = selectable.iterator(); it.hasNext();) {
			IARESProject project = it.next();
			if (!isProjectAllowed(project)) {
				it.remove();
			}
		}
		
		Object[] selectArr= selectable.toArray();
		return selectArr;
	}
	
	/**
	 * 判断一个project是否可以添加到依赖中
	 * @param project
	 * @return
	 */
	protected boolean isProjectAllowed(IARESProject project) {
		return true;
	}
	
	protected String getProjectType(IARESProject project) {
		return null;
	}
}
