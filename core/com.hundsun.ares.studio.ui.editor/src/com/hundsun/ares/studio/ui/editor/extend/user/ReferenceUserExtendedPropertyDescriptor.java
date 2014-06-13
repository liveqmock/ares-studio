/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.extend.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.excel.handler.EMFPropertyHandler;
import com.hundsun.ares.studio.core.excel.handler.IPropertyHandler;
import com.hundsun.ares.studio.core.model.Constants;
import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.extend.UserExtendedPropertyUtils;
import com.hundsun.ares.studio.core.model.extendable.ExtensibleModelUtil;
import com.hundsun.ares.studio.core.model.util.PutCommand;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.editor.extend.AbstractMapEMPropertyDescriptor;

/**
 * 引用类型的用户扩展属性
 * @author sundl
 */
public class ReferenceUserExtendedPropertyDescriptor extends AbstractMapEMPropertyDescriptor implements IUserExtendedPropertyDescriptor{

	private static final Logger LOGGER = Logger.getLogger(ReferenceUserExtendedPropertyDescriptor.class);
	
	
	private IARESProject project;
	private String refIdFeature;
	private String targetRefType;
	private String targetFeature;
	private String targetDisplayPattern;
	
	/**
	 * @param project
	 * @param key 						用户属性的KEY
	 * @param refIdFeature				如果不为空，表示当前元素中的某个属性值作为ID去查找引用；为空的情况下表示不是查找而是自行选择
	 * @param targetRefType				要引用的refType类型
	 * @param targetFeature				要引用的目标对象的哪个属性
	 * @param targetDisplayPattern		refIdFeature为空的情况下，引用目标对象的哪个属性(暂时只支持单个属性，后续可以考虑支持多个的模式)
	 */
	public ReferenceUserExtendedPropertyDescriptor(IARESProject project, String key, String refIdFeature, String targetRefType, String targetFeature, String targetDisplayPattern) {
		super(CorePackage.Literals.USER_EXTENSIBLE_PROPERTY__MAP, key, Constants.USER_DATA2_KEY);
		this.project = project;
		this.refIdFeature = refIdFeature;
		this.targetRefType = targetRefType;
		this.targetFeature = targetFeature;
		this.targetDisplayPattern = targetDisplayPattern;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor#getLabelProvider()
	 */
	@Override
	public ILabelProvider getLabelProvider() {
		return new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof ExtensibleModel) {
					ExtensibleModel model = (ExtensibleModel) element;
					String value = getValue(model);
					if (StringUtils.isEmpty(value))
						return StringUtils.EMPTY;
					ReferenceManager manager = ReferenceManager.getInstance();
					List<ReferenceInfo> refrences = manager.getReferenceInfos(project, targetRefType, true);
					for (ReferenceInfo ref : refrences) {
						Object obj = ref.getObject();
						if (obj instanceof ExtensibleModel) {
							ExtensibleModel targetModel = (ExtensibleModel) obj;
							String v = getProperty(targetModel, targetFeature);
							if (StringUtils.equals(v, value)) {
								return getProperty(targetModel, targetDisplayPattern);
							}
						}
					}
				}
				return super.getText(element);
			}
		};
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		if (refIdFeature == null) {
			ReferenceManager manager = ReferenceManager.getInstance();
			List<ReferenceInfo> refrences = manager.getReferenceInfos(project, targetRefType, true);
			List<Object> input = new ArrayList<Object>();
			input.add(StringUtils.EMPTY);
			for (ReferenceInfo ref : refrences) {
				Object obj = ref.getObject();
				if (obj instanceof ExtensibleModel) {
					ExtensibleModel model = (ExtensibleModel) obj;
					input.add(model);
				}
			}
			UserExtendedPropertyComboCellEditor editor = new UserExtendedPropertyComboCellEditor(parent, SWT.READ_ONLY);
			editor.setContenProvider(new ArrayContentProvider());
			editor.setLabelProvider(new ReferenceUserCellEditorLabelProvider());
			editor.setInput(input.toArray());
			return editor;
		}
		return null;
	}
	
	private class UserExtendedPropertyComboCellEditor extends ComboBoxViewerCellEditor{
		public UserExtendedPropertyComboCellEditor(Composite parent, int style) {
			super(parent, style);
		}

		@Override
		protected Object doGetValue() {
			Object value = super.doGetValue();
			if (value instanceof ExtensibleModel) {
				ExtensibleModel model = (ExtensibleModel) value;
				IPropertyHandler handler = EMFPropertyHandler.getPropertyHandler(model.eClass(), targetFeature, project);
				if (handler != null) {
					value = handler.getValue(model);
				}
			}
			return value == null ? StringUtils.EMPTY : value;
		}

		@Override
		protected void doSetValue(Object value) {
			if (value == null || StringUtils.isEmpty(String.valueOf(value))) {
				super.doSetValue(value);
				return;
			}
			Object[] items = (Object[]) getViewer().getInput();
			for (Object object : items) {
				if (object instanceof ExtensibleModel) {
					ExtensibleModel model = (ExtensibleModel) object;
					IPropertyHandler handler = EMFPropertyHandler.getPropertyHandler(model.eClass(), targetFeature, project);
					if (handler != null) {
						String v = handler.getValue(model);
						if (StringUtils.equals(v, String.valueOf(value))) {
							super.doSetValue(object);
							return;
						}
					}
				}
			}
			super.doSetValue(value);
		}
		
	}
	
	private class ReferenceUserCellEditorLabelProvider extends LabelProvider {
		@Override
		public String getText(Object element) {
			if (element instanceof ExtensibleModel) {
				return getProperty(element, targetDisplayPattern);
			}
			return super.getText(element);
		}
	}
	
	private String getProperty(Object object, String property) {
		if (object instanceof ExtensibleModel) {
			ExtensibleModel model = (ExtensibleModel) object;
			IPropertyHandler handler = EMFPropertyHandler.getPropertyHandler(model.eClass(), property, project);
			if (handler != null) {
				return handler.getValue(model);
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor#getValue(com.hundsun.ares.studio.core.model.ExtensibleModel)
	 */
	@Override
	public String getValue(ExtensibleModel model) {
		if (refIdFeature == null) {
			return UserExtendedPropertyUtils.getUserExtendedProperty(model, (String) getKey());
		} else {
			ReferenceManager manager = ReferenceManager.getInstance();
			EClass srcEClass = model.eClass();
			// TODO： 此处复用PropertyHandler方便以字符串的方式统一指定某个属性；基于此考虑，后续PropertyHandler机制可以进一步向上层提取，作为更加通用的工具
			IPropertyHandler handler = EMFPropertyHandler.getPropertyHandler(srcEClass, refIdFeature, project);
			if (handler != null) {
				String id = handler.getValue(model);
				if (StringUtils.isNotEmpty(id)) {
					ReferenceInfo refTarget = manager.getFirstReferenceInfo(project, targetRefType, id, true);
					if (refTarget == null) {
						return StringUtils.EMPTY;
					}
					Object target = refTarget.getObject();
					if (target instanceof ExtensibleModel) {
						ExtensibleModel targetModel = (ExtensibleModel) target;
						EClass targetEClass = targetModel.eClass();
						IPropertyHandler targetHandler = EMFPropertyHandler.getPropertyHandler(targetEClass, targetFeature, project);
						if (targetHandler != null) {
							return targetHandler.getValue(targetModel);
						}
					}
				}
			}
		}
		return StringUtils.EMPTY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor#setValue(com.hundsun.ares.studio.core.model.ExtensibleModel, java.lang.String)
	 */
	@Override
	public void setValue(ExtensibleModel model, String value) {
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(model);
		// refIdFeature不为空代表的是只读引用
		if (refIdFeature == null) {
			if (editingDomain == null) {	// editingDomain一般情况下不会为空
				ExtensibleModelUtil.setUserExtendedProperty(model, (String) getKey(), value);
			} else {
				UserPropertyEditSetCommand command = new UserPropertyEditSetCommand(model, (String) getKey(), value);
				editingDomain.getCommandStack().execute(command);
			}
		}
	}
	
	private static class UserPropertyEditSetCommand extends ChangeCommand {
		private ExtensibleModel model;
		private String key;
		private String value;

		/**
		 * @param changeRecorder
		 * @param model
		 * @param key
		 * @param value
		 */
		public UserPropertyEditSetCommand(ExtensibleModel model, String key, String value) {
			super(model);
			this.model = model;
			this.key = key;
			this.value = value;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.emf.edit.command.ChangeCommand#doExecute()
		 */
		@Override
		protected void doExecute() {
			ExtensibleModelUtil.setUserExtendedProperty(model, key,  value);
		}

		@Override
		public Collection<?> getAffectedObjects() {
			Collection<Object> result = new ArrayList<Object>();
			result.add(model);
			return result;
		}
		
	}


}
