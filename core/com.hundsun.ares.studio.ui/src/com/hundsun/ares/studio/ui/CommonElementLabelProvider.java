package com.hundsun.ares.studio.ui;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESModuleRoot;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.CommonModel;
import com.hundsun.ares.studio.core.model.ICommonModel;
import com.hundsun.ares.studio.core.model.ResGroup;
import com.hundsun.ares.studio.core.registry.ARESResRegistry;
import com.hundsun.ares.studio.core.registry.IModuleRootDescriptor;
import com.hundsun.ares.studio.core.registry.IResDescriptor;
import com.hundsun.ares.studio.core.registry.ModulesRootTypeRegistry;

public class CommonElementLabelProvider extends ARESElementLabelProvider{
	
	private static final String TAG_CNAME = "{cname}";
	private static final String TAG_ENAME = "{ename}";
	private static final String TAG_ID = "{id}";
	
	protected CommonElementContentProvider cp;
	private boolean flatLayout;
	
	/*
	 *  2014年2月19日 sundl TASK #9493 首选项配置资源树显示，未按对象号排序.
	 *  切换首选项中LabelPattern设置的时候，比如从{id} {ename}改成{ename}后，排序错误的问题
	 *  原因是在Sorter中，没有直接获取Viewer的LabelProvder获取界面显示值(为什么不？ 可以参考ARESElementComparater中的注释说明)，
	 *  而是重新new了一个，这样导致CommonElementLabelProvider会有多个实例，如果这样的话，第一个LabelProvder会监听到首选项变化，
	 *  然后刷新，刷新会触发Sorter重新排序，而此时，Sorter那里自己new出来的另外一个LP实例还没有收到首选项变化的通知，所以还会返回老的文本，
	 *  导致Sorter也就按老的文本进行排序。
	 *  将这个变量改成static可以解决这个问题
	 */
	private static String PATTERN_RESOURCE = TAG_ENAME + "(" + TAG_CNAME + ")";
	// 2014-2-19 sundl TASK #9492 模块名显示，模块名首选项配置单独配置
	private static String PATTERN_MODULE = TAG_ENAME + "(" + TAG_CNAME + ")";
	
	private IPropertyChangeListener preferenceListener;
	
	public CommonElementLabelProvider(final CommonElementContentProvider cp) {
		this.cp = cp;
		if (cp == null)
			this.cp = new CommonElementContentProvider(false);
		
		flatLayout = ARESUI.getDefault().getPreferenceStore().getBoolean(ARESPreferences.FLATLAYOUT);
		
		String prefRes = ARESUI.getDefault().getPreferenceStore().getString(ARESPreferences.LABEL_RESOURCE);
		if (!StringUtils.isEmpty(prefRes)) {
			PATTERN_RESOURCE = prefRes;
		}
		
		String prefModule = ARESUI.getDefault().getPreferenceStore().getString(ARESPreferences.LABEL_MODULE);
		if (!StringUtils.isEmpty(prefModule)) {
			PATTERN_MODULE = prefModule;
		}
		
		preferenceListener = new IPropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				if (event.getProperty().equals(ARESPreferences.FLATLAYOUT)) 
					flatLayout = Boolean.parseBoolean(String.valueOf(event.getNewValue()));
				else if (event.getProperty().equals(ARESPreferences.LABEL_RESOURCE)) {
					PATTERN_RESOURCE = String.valueOf(event.getNewValue());
					if (CommonElementLabelProvider.this.cp != null)
						CommonElementLabelProvider.this.cp.safeRefresh();
				} else if (event.getProperty().equals(ARESPreferences.LABEL_MODULE)) {
					PATTERN_MODULE = String.valueOf(event.getNewValue());
					if (CommonElementLabelProvider.this.cp != null)
						CommonElementLabelProvider.this.cp.safeRefresh();
				}
			}
		};
		
		ARESUI.getDefault().getPreferenceStore().addPropertyChangeListener(preferenceListener);
	}
	
	public String getText(Object element) {
		if (!flatLayout && element instanceof IARESModule) {
			IARESModule module = (IARESModule) element;
			if (module.isDefaultModule())
				return super.getText(element);
			
			String eName = null;
			Object parent = CommonElementContentProvider.getHierarchicalModuleParent((IARESModule) element);
			if (parent instanceof IARESModule) {
				eName = getNameDelta((IARESModule) parent, (IARESModule) element);
			} else {
				eName = super.getText(element);
			}
			
			IARESResource property = module.getARESResource(IARESModule.MODULE_PROPERTY_FILE);
			if (property != null && property.exists()) {
				try {
					Object info = property.getInfo(Object.class);
					if (info != null) {
						ICommonModel model = getCommonModel(info);
						if (model != null) {
							DelegateCommonModel common = new DelegateCommonModel(model, eName);
							return getPreferencedLabel(common, PATTERN_MODULE);
						}
					}
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}
			
			return eName;
		} else if (element instanceof IARESResource) {
			IARESResource ares = (IARESResource)element;
			
			IResDescriptor descriptor = ARESResRegistry.getInstance().getResDescriptor(ares);
			if (descriptor != null && !StringUtils.isEmpty(descriptor.getFileName())) {
				return descriptor.getName();
			}
			
			// 获取配置显示的中文名
			try {
				Object info = ares.getInfo(Object.class);
				ICommonModel model = getCommonModel(info);
				if (model != null) {
					DelegateCommonModel delegated = new DelegateCommonModel(model, ares.getName());
					return getPreferencedLabel(delegated, PATTERN_RESOURCE);
				}
			} catch (ARESModelException e) {
				// e.printStackTrace();
			}
		} else if (element instanceof IARESModuleRoot) {
			String type = ((IARESModuleRoot)element).getType();
			IModuleRootDescriptor rootDesc = ModulesRootTypeRegistry.getInstance().getModuleRootDescriptor(type);
			if (rootDesc != null && !StringUtils.isEmpty(rootDesc.getName()))
				return rootDesc.getName();
			
			IARESModuleRoot root = (IARESModuleRoot) element;
			IARESModule module = root.getModule("");
			IARESResource property = module.getARESResource(IARESModuleRoot.PROPERTY_FILE);
			if (property != null && property.exists()) {
				try {
					Object info = property.getInfo(Object.class);
					if (info != null) {
						ICommonModel model = getCommonModel(info);
						if (model != null) {
							DelegateCommonModel common = new DelegateCommonModel(model, root.getElementName());
							return getPreferencedLabel(common, PATTERN_MODULE);
						}
					}
				} catch (ARESModelException e) {
					e.printStackTrace();
				}
			}
		} else if (element instanceof ResGroup) {
			return ((ResGroup) element).getName();
		}
		
		return super.getText(element);
	}

	// 将给定的对象转换为
	private ICommonModel getCommonModel(Object info) {
		ICommonModel commonModel = null;
		if (info == null) {
			commonModel = null;
		} else if (info instanceof ICommonModel) {
			commonModel = (ICommonModel)info;
		} else if (info instanceof IAdaptable) {
			IAdaptable adapter = (IAdaptable)info;
			commonModel = (ICommonModel) adapter.getAdapter(ICommonModel.class);
		} else {
			IAdapterManager manager = Platform.getAdapterManager();
			commonModel = (ICommonModel)manager.loadAdapter(info, ICommonModel.class.getName());
		}
		return commonModel;
	}
	
	private String getPreferencedLabel(ICommonModel model, String pattern) {
		String cName = model.getString(ICommonModel.CNAME);
		String name = model.getString(ICommonModel.NAME);
		String id = model.getString(ICommonModel.ID);
		String result = pattern.replace(TAG_CNAME, cName).replace(TAG_ENAME, name).replace(TAG_ID, id);
		if (result.length() == 0)
			result = name;
		return result;
	}
	
	private String getNameDelta(IARESModule parent, IARESModule fragment) {
		String prefix = parent.getElementName() + '.';
		String fullName = fragment.getElementName();
		if (fullName.startsWith(prefix)) {
			return fullName.substring(prefix.length());
		}
		return fullName;
	}
	
	public void dispose() {
		super.dispose();
		ARESUI.getDefault().getPreferenceStore().removePropertyChangeListener(preferenceListener);
	}
	
}

/**
 * 英文名在资源模型里一般取不到，所以要在外部传入一个；并且把中文名等信息的请求转发到代理模型处理。
 * @author sundl
 */
class DelegateCommonModel extends CommonModel {
	private static Logger logger = Logger.getLogger(DelegateCommonModel.class);
	
	private ICommonModel delegate;
	private String eName;
	
	public DelegateCommonModel(ICommonModel delegate, String eName) {
		this.delegate = delegate;
		this.eName = eName;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.ICommonModel#getValue(java.lang.String)
	 */
	public Object getValue(String key) {
		if (key.equals(NAME))
			return eName;
		
		try {
			return delegate.getValue(key);
		} catch (Exception e) {
			logger.error("", e);
		}
		return StringUtils.EMPTY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.ICommonModel#setValue(java.lang.String, java.lang.Object)
	 */
	public void setValue(String key, Object value) {
		delegate.setValue(key, value);
	}
	
}
