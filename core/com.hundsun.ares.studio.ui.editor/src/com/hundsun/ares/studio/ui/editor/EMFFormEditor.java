/**
 * 源程序名称：EMFFormEditor.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TriggerListener;
import org.eclipse.emf.transaction.impl.EditingDomainManager;
import org.eclipse.emf.transaction.impl.TransactionalCommandStackImpl;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.editor.IFormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.ui.ARESElementLabelProvider;
import com.hundsun.ares.studio.ui.IARESResourceEditorInput;
import com.hundsun.ares.studio.ui.editor.editable.IEditableControl;
import com.hundsun.ares.studio.ui.editor.editable.JresDefaultEditableControler;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelUtils;
import com.hundsun.ares.studio.ui.editor.sync.IFileSyncnizeUnit;
import com.hundsun.ares.studio.ui.editor.sync.JRESDefaultSyncnizeUnit;
import com.hundsun.ares.studio.ui.editor.sync.JRESEditorSyncManager;
import com.hundsun.ares.studio.ui.extendpoint.manager.ExtendPageInfo;
import com.hundsun.ares.studio.ui.extendpoint.manager.ExtendPageManager;
import com.hundsun.ares.studio.ui.extendpoint.manager.IExtendedPage;
import com.hundsun.ares.studio.ui.page.ExtendPageWithMyDirtySystem;
import com.hundsun.ares.studio.ui.page.IExtendItemLoader;
import com.hundsun.ares.studio.ui.util.ARESUIUtil;

/**
 * <ul>
 * <li>创建{@link EditingDomain}作为编辑域并读取模型信息</li>
 * <li></li>
 * <li>脏状态控制由<code>CommandStack</code>来完成。</li>
 * <li>{@link #getAdapter}支持返回{@link EditingDomain}和{@link Resource}</li>
 * </ul>
 * 注意事项
 * <ul>
 * <li>本编辑器在注册时对应的资源在注册的时候info-class必须是{@link EObject}的子类型</li>
 * </ul>
 * @author gongyf
 *
 */
public abstract class EMFFormEditor extends FormEditor implements IEditingDomainProvider {

	public static ILabelProvider TITLE_LABEL_PROVIDER = new ARESElementLabelProvider();
	
	protected static final String ACTIVE_EDITOR_INDEX = "active_editor_index";//编辑器激动保存id
	private int editorIndex = 0;//最后激活的编辑器
	
	protected final Logger logger = Logger.getLogger(getClass());
	
	//文件同步单元
	protected IFileSyncnizeUnit fileSyncUnit;
	
	/**
	 * 使用统一剪贴板
	 */
	static protected Collection<Object> myclipboard;
	
	private TransactionalEditingDomain editingDomain;
	
	private EObject info = null;
	
	private IEditableControl editableControl;
	
	// 扩展页面列表
	protected List<IExtendedPage> extendsPages = new ArrayList<IExtendedPage>();
	
	public TransactionalEditingDomain getEditingDomain() {
		return editingDomain;
	}
	
	public EMFFormActionBarContributor getActionBarContributor() {
		return (EMFFormActionBarContributor) getEditorSite().getActionBarContributor();
	}
	
	/**
	 * @return the info
	 */
	public EObject getInfo() {
		return info;
	}
	

	
	/**
	 * 初始化editingDomain
	 * 可以添加其他adapterFactory
	 * @param adapterFactory
	 */
	protected void configureComposedAdapterFactory(ComposedAdapterFactory adapterFactory) {
		// do nothing
	}
	
	protected void createEditDomain() {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		configureComposedAdapterFactory(adapterFactory);
		
		// Create the editing domain with a special command stack.
		//
		
		TransactionalCommandStackImpl commandStack = new TransactionalCommandStackImpl(){
			@Override
			public void execute(Command command) {
	            if (command != null) {
	            	if (editingDomain.isReadOnly(getInfo().eResource())) {
						try{
							handleLock();
						}finally{
							command.dispose();
						}
		            }
	            }
	            super.execute(command);
		    }
		};
		
		editingDomain = new TransactionalEditingDomainImpl(adapterFactory,commandStack) {
			public boolean isReadOnly(Resource resource) {
				if (isInReferencedLibrary()) {
					// 引用包中为只读
					return true;
				}
				
				if (resource == null || resource.getURI() == null) {
					return false;
				}

				return super.isReadOnly(resource);
			}

			@Override
			public Collection<Object> getClipboard() {
				return EMFFormEditor.myclipboard;
			}

			@Override
			public void setClipboard(Collection<Object> clipboard) {
				EMFFormEditor.myclipboard = clipboard;
			};
			
		};
		
		// 添加一些注册的监听器
		EditingDomainManager.getInstance().configureListeners(getEditingDomainID(), editingDomain);
		
		
		// Add a listener to set the most recent command's affected objects to be the selection of the viewer with focus.
		//
		editingDomain.getCommandStack().addCommandStackListener
			(new CommandStackListener() {
				 public void commandStackChanged(final EventObject event) {
					 getSite().getShell().getDisplay().asyncExec
						 (new Runnable() {
							  public void run() {
								  firePropertyChange(IEditorPart.PROP_DIRTY);
							  }
						  });
				 }
			 });
		
		// 对于扩展模型的初始化支持，当一个新的ExtensibleModel被创建后，自动初始化map内容
		editingDomain.addResourceSetListener(new TriggerListener() {
			
			@Override
			protected Command trigger(TransactionalEditingDomain domain,
					Notification notification) {
				List<ExtensibleModel> modelList = new ArrayList<ExtensibleModel>();
				
				if (notification.getNewValue() instanceof ExtensibleModel) {
					modelList.add((ExtensibleModel) notification.getNewValue());
				} else if (notification.getNewValue() instanceof Collection<?>) {
					for (Object o : (Collection<?>)notification.getNewValue()) {
						if (o instanceof ExtensibleModel) {
							modelList.add((ExtensibleModel) o);
						}
					}
				}
				
				if (modelList.isEmpty()) {
					return null;
				}
				return new ExtensibleModelTriggerCommand(domain, getARESResource(), 
						modelList);
			}
		});

	}
	
	/**
	 * 处理锁定
	 */
	private void handleLock (){
		if (this.getARESResource().getResource() == null) {
			return;
		}
		IPath path = this.getARESResource().getResource().getProjectRelativePath();
		IFile file = getARESResource().getARESProject().getProject().getFile(path);
		if (file.exists()) {
			boolean readonly = file.isReadOnly();
			
			file.getWorkspace().validateEdit(new IFile[]{file}, null);
			
			if (file.isReadOnly() != readonly) {
				Display display = getSite().getShell().getDisplay();
				display.asyncExec(new Runnable() {
					public void run() {
						getSite().getPage().closeEditor(EMFFormEditor.this, false);
						try {
							ARESUIUtil.openEditor(getARESResource());
						} catch (PartInitException e) {
							e.printStackTrace();
						}
					}
				});
			}
		}
	}
	
	/**
	 * 获取编辑域的ID，返回null表示不需要id
	 * @return
	 */
	protected String getEditingDomainID() {
		return getEditorSite().getId();
	}
	
	/**
	 * 判断当前是否在引用资源包中打开
	 * @return
	 */
	public boolean isInReferencedLibrary() {
		return getARESResource().getLib() != null;
	}
	
	
	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);
		//添加文件同步
		fileSyncUnit = new JRESDefaultSyncnizeUnit(this);
		JRESEditorSyncManager.getInstance().addSyncUnit(fileSyncUnit);
	}

	public IARESResource getARESResource() {
		IARESResource aresResource = null;
		if (getEditorInput() instanceof IFileEditorInput) {
			aresResource = (IARESResource) ARESCore.create(((IFileEditorInput) getEditorInput()).getFile());
		} else if (getEditorInput() instanceof IARESResourceEditorInput) {
			aresResource = ((IARESResourceEditorInput)getEditorInput()).getARESResource();
		} // TODO: 远程资源处理
		return aresResource;
	}
	
	
	
	/**
	 * 获取编辑对象的EMF类
	 * @return
	 */
	protected abstract EClass getInfoClass();
	
	/**
	 * 创建编辑域和读取模型信息
	 * @throws ARESModelException 
	 */
	protected void createModel() throws ARESModelException {

		//try {
			// 先获取ARES资源类型
			IARESResource aresResource = getARESResource();
			
			Assert.isTrue(aresResource != null, "输入无法转换为IARESResource对象");
			
			if(getInfoClass() == null) {
				info = (EObject) aresResource.getWorkingCopy(getInfoClassInstance());
			}else {
				info = (EObject) aresResource.getWorkingCopy(getInfoClass().getInstanceClass());
			}
			
			// ----------------------------------------
			// 测试代码 begin
			// ----------------------------------------
			if (info == null) {
				logger.error("没有读取到模型内容，将自动创建一个全空模型。资源：" + aresResource.getElementName());
				Resource r = new XMLResourceImpl();
				info = getInfoClass().getEPackage().getEFactoryInstance().create(getInfoClass());
				r.getContents().add(info);
			}
			// ----------------------------------------
			// 测试代码 end
			// ----------------------------------------
			ExtensibleModelUtils.extendResource(getARESResource(), info.eResource(), false);
			editingDomain.getResourceSet().getResources().clear();
			try {
				//添加原resourceset中注册的package
				editingDomain.getResourceSet().getPackageRegistry().putAll(info.eResource().getResourceSet().getPackageRegistry());
			} catch (Exception e) {
			}
			((BasicCommandStack)editingDomain.getCommandStack()).flush();
			editingDomain.getResourceSet().getResources().add(info.eResource());
//		} catch (Exception e) {
//			//MessageDialog.openError(getSite().getShell(), "创建模型错误", e.getMessage());
//			logger.error(e.getMessage(), e);
//			throw e;
//		}
	}
	
	/**
	 * 直接获取Class对象 如用户元数据
	 * 此时方法getInfoClass()返回null
	 * @return
	 */
	protected Class<?> getInfoClassInstance() {
		return EObject.class;
	}

	
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == EditingDomain.class) {
			return getEditingDomain();
		} else if (adapter.isAssignableFrom(getInfo().getClass())) {
			return getInfo();
		}
		return super.getAdapter(adapter);
	}
	
	@Override
	public boolean isDirty() {
		return ((BasicCommandStack)editingDomain.getCommandStack()).isSaveNeeded();
	}
	
	/**
	 * 指示当前编辑器是否为应该为只读状态，这个方法将影响界面上的控件可编辑状态<BR>
	 * 调用必须在{@link #createModel()}后被调用
	 * @return
	 */
	public boolean isReadOnly() {
		 ResourceAttributes attributes = getARESResource().getResource().getResourceAttributes();
		 boolean resourceIsReadOnly = false;//文件系统中该系统是否为只读,默认不为
		 if(attributes!=null){
			 resourceIsReadOnly = attributes.isReadOnly();
		 }
		return editingDomain.isReadOnly(info.eResource()) || resourceIsReadOnly;
	}
	
	protected void createEditableControl() {
		//初始化只读控制器
		editableControl = new JresDefaultEditableControler(this);
		editableControl.refreshResourceReadonlyStatus();
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		
		try {
			createEditDomain();
			createModel();
			createEditableControl();
		} catch (Exception e) {
			throw new PartInitException(e.getMessage(), e);
		}
	}
	
	@Override
	protected void createPages() {
		super.createPages();
		
		for (IExtendedPage page : extendsPages) {
			page.onCreate();
		}
		setPartName(getEditorTitle());
		setActivePage(getEditorIndex());
	}
	
	protected String getEditorTitle() {
		// 设置编辑器标题
		String partName = TITLE_LABEL_PROVIDER.getText(getARESResource());
		if (isReadOnly()) {
			partName += "(只读)";
		}
		return partName;
	}
	

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#configurePage(int, org.eclipse.ui.forms.editor.IFormPage)
	 */
	@Override
	protected void configurePage(int index, IFormPage page) throws PartInitException {
		super.configurePage(index, page);
		if (page instanceof IEMFFormPage) {
			((IEMFFormPage) page).setEditableControl(editableControl);
		} 
	}
	
	/**
	 * 读取拓展页面
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void createExtendPage() {
		for(ExtendPageInfo info:ExtendPageManager.getDefault().getPageInfo(getSite().getId())){
			try {
				Class cls = info.getPageClass();
				Constructor cst = null;
				try {
					cst = cls.getConstructor(new Class[] { FormEditor.class, String.class, String.class });
				} catch (Exception e) {	}
				
				IExtendedPage page = null;
				if (cst != null) {
					page = (IExtendedPage) cst.newInstance(new Object[] { this, info.getPageId(), info.getPageName() });
				} else {
					page = (IExtendedPage) cls.newInstance();
					((IExtendedPage) page).init(this);
				}
				
				if (page instanceof ExtendPageWithMyDirtySystem) {
					((ExtendPageWithMyDirtySystem)page).setInfo(getInfo());
					if (!info.isHidden() && ((IExtendItemLoader) page).shouldLoad()) {
						addPage((FormPage)page);
					}
				}
				
				extendsPages.add(page);
			} catch (Exception e) {
				logger.error("读取拓展页面异常", e);
			}
		}
	}
	
	protected void handleBeforeSave() {
		for (IExtendedPage page : extendsPages) {
			page.beforeSave();
		}
	}
	
	protected void handleAfterSave() {
		for (IExtendedPage page : extendsPages) {
			page.afterSave();
		}
	}
	
	@Override
	final public void doSave(IProgressMonitor monitor) {
		fileSyncUnit.beforeSave();
		if (isReadOnly()) {
			MessageDialog.openInformation(getSite().getShell(), "无法保存", "当前资源是只读状态，无法进行保存");
		} else {
			handleBeforeSave();
			
			WorkspaceModifyOperation operation =
				new WorkspaceModifyOperation() {
					@Override
					public void execute(IProgressMonitor monitor) {
						try {
							IARESResource aresResource = getARESResource();
							aresResource.save(getInfo(), true, monitor);
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
					}
				};

			try {
				operation.run(monitor);
				((BasicCommandStack)editingDomain.getCommandStack()).saveIsDone();
				firePropertyChange(IEditorPart.PROP_DIRTY);
				handleAfterSave();
			} catch (Exception exception) {
				MessageDialog.openError(getSite().getShell(), "保存失败", "异常信息：" + exception.getMessage());
			}
		}
	}
	
	@Override
	public void doSaveAs() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#createToolkit(org.eclipse.swt.widgets.Display)
	 */
	@Override
	protected FormToolkit createToolkit(Display display) {
		return new FormToolkitEx(display);
	}
	
	/**
	 * 返回编辑器激活的编辑器
	 * @return
	 */
	protected IDialogSettings getDialogSettings() {
		IDialogSettings settings = ARESEditorPlugin.getDefault().getDialogSettings();
		EClass eClass =getInfoClass();
		IDialogSettings blockSettings = null;
		if(eClass!=null){
			 blockSettings = settings.getSection(eClass.getInstanceClassName());
			if (blockSettings == null) {
				blockSettings = settings.addNewSection(eClass.getInstanceClassName());
				blockSettings.put(ACTIVE_EDITOR_INDEX, 0);
			}
		}
		
		return blockSettings;
	}
	/**
	 * 返回编辑器最后的活动的编辑器
	 * @return
	 */
	protected int getEditorIndex() {
		IDialogSettings settings = getDialogSettings();
		if (settings != null) {
			try{
				editorIndex = settings.getInt(ACTIVE_EDITOR_INDEX);
			}catch(Exception e){
				editorIndex = 0;
			}
			
		}
		if(editorIndex <0){
			editorIndex = 0;
		}
		return editorIndex;
	}
	
	

	@Override
	public void dispose() {
		EditingDomainManager.getInstance().deconfigureListeners(getEditingDomainID(), editingDomain);
		JRESEditorSyncManager.getInstance().removeSyncUnit(fileSyncUnit);
		super.dispose();
		IDialogSettings settings = getDialogSettings();
		if(settings!=null){
			settings.put(ACTIVE_EDITOR_INDEX, getCurrentPage());
		}
	}
	
}
