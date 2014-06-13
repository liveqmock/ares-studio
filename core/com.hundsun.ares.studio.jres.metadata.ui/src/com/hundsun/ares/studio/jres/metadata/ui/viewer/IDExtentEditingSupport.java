/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.ui.viewer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.util.PutCommand;
import com.hundsun.ares.studio.core.registry.ModuleRootType2ResTypeMap;
import com.hundsun.ares.studio.jres.model.metadata.IDRangeItem;
import com.hundsun.ares.studio.jres.model.metadata.IDRangeList;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;
import com.hundsun.ares.studio.ui.editor.editingsupport.EMFEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelEditingSupport;
import com.hundsun.ares.studio.ui.editor.extend.IExtensibleModelPropertyDescriptor;

/**
 * @author wangxh
 *
 */
public class IDExtentEditingSupport extends EMFEditingSupport {

	IExtensibleModelEditingSupport editingSupport;
	IExtensibleModelPropertyDescriptor descriptor;
	IDRangeList info;
	/**
	 * @param viewer
	 * @param editingSupport
	 * @param descriptor
	 */
	public IDExtentEditingSupport(ColumnViewer viewer,
			IExtensibleModelEditingSupport editingSupport,
			IExtensibleModelPropertyDescriptor descriptor,
			IDRangeList info) {
		super(viewer, descriptor.getStructuralFeature());
		this.editingSupport = editingSupport;
		this.descriptor = descriptor;
		this.info = info;
	}

	@Override
	protected EObject getOwner(Object element) {
		IDRangeItem item = getIDRangeItem(element);
		if(item != null){
			return item.getData2().get(editingSupport.getKey());
		}
		return null;
	}
	
	private IDRangeItem getIDRangeItem(Object element){
		if(element instanceof IARESElement){
			String path = ((IARESElement)element).getResource().getProjectRelativePath().toPortableString();
			for(IDRangeItem item : info.getItems()){
				if(StringUtils.equals(item.getName(), path)){
					return item;
				}
			}
		}
		return null;
	}
	
	@Override
	protected boolean doCanEdit(Object element) {
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(info);
		if(editingDomain != null && editingDomain.isReadOnly(info.eResource())){
			return false;
		}
		//这里有一个默认约束，name为资源的类型组成，多个以逗号隔开
		String[] types = StringUtils.split(editingSupport.getName(), ",");
		if(element instanceof IARESModule){
			String type = ((IARESModule)element).getRoot().getType();
			for(String resType : types){
				String[] rootTypes = ModuleRootType2ResTypeMap.getInstance().getAllowedRootTypes(resType);
				for(String rootType : rootTypes){
					if(StringUtils.equals(type, rootType)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	protected Object getValue(Object element) {
		Object value = ObjectUtils.defaultIfNull(super.getValue(element), descriptor.getStructuralFeature().getDefaultValue());
		return ObjectUtils.defaultIfNull(value, StringUtils.EMPTY);
	}

	@Override
	protected CellEditor createCellEditor() {
		return descriptor.createPropertyEditor((Composite) getViewer().getControl());
	}
	
	private String getRealRange(String value){
		if(StringUtils.isBlank(value)){
			return descriptor.getStructuralFeature().getDefaultValueLiteral();
		}
		String range = "";
		Pattern pattern = Pattern.compile("^[0-9]+-[0-9]+$");
		String[] tmps = StringUtils.split(value, ",");
		for(int index=0; index<tmps.length; index++){
			try {
				String tmp = tmps[index];
				Matcher matcher = pattern.matcher(tmp);
				if(matcher.matches()){
					if(index > 0){
						range += ",";
					}
					String[] ranges = StringUtils.split(tmp, "-");
					int min = Integer.parseInt(ranges[0]);
					int max = Integer.parseInt(ranges[1]);
					range +=(min + "-" + max);
				}else{
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return range;
	}
	
	@Override
	protected void setValue(Object element, Object val) {
		String value = getRealRange(val.toString());
		if(value == null){
			return;
		}
		// 如果没有修改的必要，直接返回
		Object oldValue = getValue(element);
		if (ObjectUtils.equals(oldValue, value)) {
			return;
		}
		
		IDRangeItem model = getIDRangeItem(element);
		EStructuralFeature feature = descriptor.getStructuralFeature();
		String key = editingSupport.getKey();
		
		CompoundCommand command = new CompoundCommand();
		EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(info);
		if(model == null){
			model = MetadataFactory.eINSTANCE.createIDRangeItem();
			model.setName(((IARESElement)element).getResource().getProjectRelativePath().toPortableString());
			EObject data2 = editingSupport.createMapValueObject();
			data2.eSet(feature, value);
			model.getData2().put(key, data2);
			if (editingDomain != null) {
				command.append(new AddCommand(editingDomain, info, MetadataPackage.Literals.METADATA_RESOURCE_DATA__ITEMS, model));
			}else{
				info.getItems().add(model);
			}
		}else{
			EObject data2 = model.getData2().get(key);
			if(data2 == null){
				data2 = editingSupport.createMapValueObject();
				data2.eSet(feature, value);
				if(editingDomain != null){
					command.append(new PutCommand(model, CorePackage.Literals.EXTENSIBLE_MODEL__DATA2, key, data2));
				}else{
					model.getData2().put(key, data2);
				}
			}else{
				if(editingDomain != null){
					command.append(new SetCommand(editingDomain, data2, feature, value));
				}else{
					data2.eSet(feature, value);
				}
			}
		}
		if(editingDomain != null){
			editingDomain.getCommandStack().execute(command.unwrap());
		}
		// 如果使用RefreshViewerJob会延迟显示正确内容
		getViewer().update(element, null);
	}
}
