/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.search;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.util.OpenStrategy;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.search.ui.ISearchResultPage;
import org.eclipse.search.ui.text.AbstractTextSearchResult;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;
import org.eclipse.search.ui.text.Match;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.internal.core.ArchiveARESResource;
import com.hundsun.ares.studio.ui.ARESElementSorter;
import com.hundsun.ares.studio.ui.ARESResourceEditorInput;
import com.hundsun.ares.studio.ui.CommonElementContentProvider;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;

/**
 * 
 * @author liaogc
 */
public class ARESSearchResultPage extends AbstractTextSearchViewPage implements ISearchResultPage  {

	/**
	 * 使用树结构显示结果
	 */
	public ARESSearchResultPage() {
		super(FLAG_LAYOUT_TREE);
	}

	@Override
	protected void clear() {
		// TODO 似乎不实现也没关系
	}

	@Override
	protected void configureTableViewer(TableViewer viewer) {
		// TODO 目前不使用表格
		
	}

	@Override
	protected void configureTreeViewer(TreeViewer viewer) {
		// 配置树结构
		CommonElementContentProvider cp = new CommonElementContentProvider();
		viewer.setContentProvider(new TreeContentProvider());
		viewer.setLabelProvider(new CommonElementLabelProvider(cp));//(new ACElementLabelProvider() );
		viewer.setComparator(new ARESElementSorter());
	}

	@Override
	protected void elementsChanged(Object[] objects) {
		getViewer().refresh();
		
	}
	
	/**
	 * 结果集的内容提供器
	 */
	private class TreeContentProvider implements ITreeContentProvider {

		@Override
		public Object[] getChildren(Object parentElement) {
			Match[] matchs = getInput().getMatches(parentElement);
			if (matchs.length > 0) {
				return ((ARESSearchElementMatch)(matchs[0])).getChildren().toArray();
			}
			return new Object[0];
		}

		@Override
		public Object getParent(Object element) {
			return ((IARESElement)element).getParent();
		}

		@Override
		public boolean hasChildren(Object element) {
			Match[] matchs = getInput().getMatches(element);
			return matchs.length > 0 && !((ARESSearchElementMatch)(matchs[0])).getChildren().isEmpty();
		}

		@Override
		public Object[] getElements(Object inputElement) {
			// 以工程为一级节点
			List<IARESElement> ret = new ArrayList<IARESElement>();
			try {
				for (IARESProject project : ARESCore.getModel().getARESProjects() ) {
					Match[] matchs = ((AbstractTextSearchResult)inputElement).getMatches(project);
					for (Match match : matchs) {
						ret.add( (IARESElement)match.getElement() );
					}
				}
			} catch (ARESModelException e) {
				e.printStackTrace();
			}
			
			return ret.toArray();
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO Auto-generated method stub
			
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.search.ui.text.AbstractTextSearchViewPage#showMatch(org.eclipse.search.ui.text.Match, int, int, boolean)
	 */
	@Override
	protected void showMatch(Match match, int currentOffset, int currentLength, boolean activate) throws PartInitException {
		// 资源的定位
		Object e = match.getElement();
		if (e instanceof IARESElement) {
			if (e instanceof IARESResource) {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				if(e instanceof ArchiveARESResource){
					ArchiveARESResource aresfile = (ArchiveARESResource)e;
					ARESResourceEditorInput input = new ARESResourceEditorInput(aresfile);
					boolean active = OpenStrategy.activateOnOpen();
					try {
						IEditorDescriptor editor = IDE.getEditorDescriptor(aresfile.getElementName());
						if (editor != null) {
							IDE.openEditor(page, input, editor.getId(), active);
						}
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				}else{
					IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), (IFile) ((IARESResource)e).getResource());
				}
			} else {
				IResource resource = ((IARESElement) e).getResource();
				if (resource instanceof IFile) {
					IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), (IFile) resource);
				}
			}

		}
		
	}


}