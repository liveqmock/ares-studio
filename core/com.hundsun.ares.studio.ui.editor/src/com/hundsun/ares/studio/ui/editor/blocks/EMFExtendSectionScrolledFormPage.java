/**
 * 
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TriggerListener;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.part.FileEditorInput;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.extendable.IExtendAbleModel;
import com.hundsun.ares.studio.ui.IARESResourceEditorInput;
import com.hundsun.ares.studio.ui.editor.ExtensibleModelTriggerCommand;
import com.hundsun.ares.studio.ui.page.ExtendSectionScrolledFormPage;

/**
 * @author gongyf
 *
 */
public abstract class EMFExtendSectionScrolledFormPage<T> extends
		ExtendSectionScrolledFormPage<T> implements CommandStackListener{

	private IARESProject project;
	private TransactionalEditingDomain editingDomain;
	private EObject model;
	private DataBindingContext bindingContext = new DataBindingContext();
	
	//在EMF模型下，控件一般都是直接用swt的，而非之前的集成自IEditable的空间 by wangxh
	List<Control> editControls = new ArrayList<Control>();
	

	private ResourceSetListener resourceSetListener = new ResourceSetListenerImpl(){
		/* (non-Javadoc)
		 * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)
		 */
		@Override
		public void resourceSetChanged(ResourceSetChangeEvent event) {
			EMFExtendSectionScrolledFormPage.this.resourceSetChanged(event);
		}
	};
	
	public List<Control> getEditControls() {
		return editControls;
	}
	
	@Override
	public void updateEditableState() {
		super.updateEditableState();
		for(Control control : getEditControls()){
			if(control != null){
				control.setEnabled(isEditable());
			}
		}
	}
	
	public EMFExtendSectionScrolledFormPage(FormEditor editor, String id,
			String title) {
		super(editor, id, title);
		
		initializeEditingDomain();
		
		if (editor.getEditorInput() instanceof FileEditorInput) {
			FileEditorInput input = (FileEditorInput) editor.getEditorInput();
			project = ARESCore.create(input.getFile().getProject());
		} else if (editor.getEditorInput() instanceof IARESResourceEditorInput) {
			IARESResource aresRes = ((IARESResourceEditorInput) editor
					.getEditorInput()).getARESResource();
			project = aresRes.getARESProject();
		}
	}


	
	public DataBindingContext getBindingContext() {
		return bindingContext;
	}
	
	public TransactionalEditingDomain getEditingDomain() {
		return editingDomain;
	}
	
	public IARESProject getARESProject() {
		return project;
	}
	
	/**
	 * 扩展模型的EMF类
	 * @return
	 */
	protected abstract EClass getEClass();
	
	/**
	 * 获取扩展模型在map中的key
	 * @return
	 */
	protected abstract String getMapKey();
	
	protected EObject getModel() {
		if (model == null) {
			model = createModel();
		}
		return model;
	}
	
	protected EObject createModel() {
		if (info != null) {
			IExtendAbleModel inputmodel = (IExtendAbleModel) info;
			EObject model = (EObject) inputmodel.getMap().get(getMapKey());
			if (null == model) {
				model = getEClass().getEPackage().getEFactoryInstance().create(getEClass());
				inputmodel.getMap().put(getMapKey(), model);
			}
			
			// 创建EMF资源，URI中字符串任意，无意义，但不要为空。
			Resource resource = new XMLResourceImpl(URI.createURI(getMapKey()));
			
			processModelOnCreate(model);
			
			resource.getContents().add(model);
			getEditingDomain().getResourceSet().getResources().add(resource);
			return model;
		}
		return null;
	}
	
	protected void processModelOnCreate(EObject model) {
		// do nothing
	}
	
	protected void resourceSetChanged(ResourceSetChangeEvent event) {
		
	}
	
	protected void initializeEditingDomain() {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		
		editingDomain = new TransactionalEditingDomainImpl(adapterFactory) {
			@Override
			public boolean isReadOnly(Resource resource) {
				if (isInReferencedLibrary()) {
					// 引用包中为只读
					return true;
				}
				
				if (resource == null || resource.getURI() == null) {
					return false;
				}
				
				if (getResource() != null && getResource().isReadOnly()) {
					return true;
				}

				return super.isReadOnly(resource);
			}

//			@Override
//			public Collection<Object> getClipboard() {
//				return EMFFormEditor.myclipboard;
//			}
//
//			@Override
//			public void setClipboard(Collection<Object> clipboard) {
//				EMFFormEditor.myclipboard = clipboard;
//			};
			
			
		};
		
		// 添加一些注册的监听器
//		EditingDomainManager.getInstance().configureListeners(getEditingDomainID(), editingDomain);
		
		editingDomain.getCommandStack().addCommandStackListener(this);
		
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
				return new ExtensibleModelTriggerCommand(domain, getResource(), 
						modelList);
			}
		});
		
		
		// 添加模型监听器
		editingDomain.addResourceSetListener(resourceSetListener);
	}
	
	/**
	 * 判断当前是否在引用资源包中打开
	 * @return
	 */
	public boolean isInReferencedLibrary() {
		if (getResource() != null) {
			return getResource().getLib() != null;
		}
		return false;
	}
	
	@Override
	public void commandStackChanged(EventObject event) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				setDirty(true); // 让编辑器变脏
			}
			
		});
	}
	
	@Override
	public void dispose() {
		getBindingContext().dispose();
		super.dispose();
	}
	
	protected void setDirty(boolean dirty) {
		this.dirty.setValue(dirty);
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}
}
