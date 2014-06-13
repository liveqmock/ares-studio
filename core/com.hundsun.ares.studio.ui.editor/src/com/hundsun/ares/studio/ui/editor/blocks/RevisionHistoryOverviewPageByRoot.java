/**
 * 源程序名称：RevisionHistoryOverviewPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.uft.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.poifs.property.RootProperty;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.ModuleProperty;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.ui.page.ExtendPageWithMyDirtySystem;

/**
 * 修改记录总览页面
 * @author sundl
 *
 */
public class RevisionHistoryOverviewPageByRoot extends ExtendPageWithMyDirtySystem<RootProperty> {
	
	private RevisionHistoryOverviewBlock block;

	public RevisionHistoryOverviewPageByRoot(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}
	
	@Override
	protected void createFormContent(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		block = new RevisionHistoryOverviewBlock(getResource()){
			protected org.eclipse.jface.viewers.IContentProvider getColumnViewerContentProvider() {
				return new IStructuredContentProvider() {
					@Override
					public void dispose() {
					}
					@Override
					public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
						
					}
					@Override
					public Object[] getElements(Object inputElement) {
						List<RevisionHistoryOverviewElement> result = new ArrayList<RevisionHistoryOverviewElement>();
						IARESModuleRoot root = resource.getRoot();//模块根
						try {
							IARESResource[] resources = root.getResources();
							for (IARESResource res : resources) {
								JRESResourceInfo info = res.getInfo(JRESResourceInfo.class);
								if (info == null) {
									ModuleProperty mp = res.getInfo(ModuleProperty.class);
									if (mp != null) {
										info = (JRESResourceInfo) mp.getMap().get(Constants.RevisionHistory.MODULE_REVISION_EXT_KEY);
									}
								}
								if (info != null) {
									List<RevisionHistory> histories = info.getHistories();
									for (RevisionHistory his : histories) {
										result.add(new RevisionHistoryOverviewElement(res, his));
									}
								}
							}
						} catch (ARESModelException e1) {
							e1.printStackTrace();
						}
						
						Collections.sort(result);
						// 倒序显示
						Collections.reverse(result);
						return result.toArray();
					}
				};	
			};
		};
		block.createControl(composite, toolkit);
		block.setInput(new Object());
		
		GridLayoutFactory.swtDefaults().applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(block.getControl());
	}
	
	@Override
	public boolean shouldLoad() {
		return true;
	}

}
