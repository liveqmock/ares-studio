/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.editor.extend;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.model.ExtensibleModel;
import com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor;
import com.hundsun.ares.studio.core.model.extend.IRefExtendPropertyProvider;
import com.hundsun.ares.studio.core.model.extend.RefExtendedPropertyProviderManager;

/**
 * 引用类型的扩展列/扩展属性。 这种引用属性是根据当前字段，从其他地方获取引用数据的某个字段来进行显示。
 * @author sundl
 */
public class RefExtendedPropertyDescriptor extends AbstractEMPropertyDescriptor implements IExtensibleModelPropertyDescriptor, IBasicExtendPropertyDescriptor{
	
	private IARESProject project;
	private Map<String, String> config;
	
	/**
	 * @param structuralFeature
	 */
	public RefExtendedPropertyDescriptor(Map<String, String> config, IARESProject project) {
		super(null);
		this.config = config;
		this.project = project;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor#getLabelProvider()
	 */
	@Override
	public ILabelProvider getLabelProvider() {
		return new ExtendedPropertyLabelProvider(this);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor#createPropertyEditor(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor#getValue(com.hundsun.ares.studio.core.model.ExtensibleModel)
	 */
	@Override
	public String getValue(ExtensibleModel model) {
		String providerId = config.get("provider");
		if (StringUtils.isEmpty(providerId))
			return StringUtils.EMPTY;
		
		IRefExtendPropertyProvider provider = RefExtendedPropertyProviderManager.getInstance().getProvider(providerId);
		if (provider != null) {
			provider.config(config, project);
			return provider.getValue(model);
		}
		return StringUtils.EMPTY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.model.extend.IBasicExtendPropertyDescriptor#setValue(com.hundsun.ares.studio.core.model.ExtensibleModel, java.lang.String)
	 */
	@Override
	public void setValue(ExtensibleModel model, String value) {
	}
	
}
