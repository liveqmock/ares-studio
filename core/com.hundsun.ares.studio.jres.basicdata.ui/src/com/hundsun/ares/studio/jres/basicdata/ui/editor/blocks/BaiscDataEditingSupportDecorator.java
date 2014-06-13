package com.hundsun.ares.studio.jres.basicdata.ui.editor.blocks;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.CellEditor;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.ui.viewer.UncategorizedItemsCategoryImpl;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.ui.editor.editingsupport.IEditingSupportDecorator;
import com.hundsun.ares.studio.ui.editor.viewers.IEStructuralFeatureProvider;

public class BaiscDataEditingSupportDecorator implements
IEditingSupportDecorator{

	private IEStructuralFeatureProvider provider;
	private IARESResource resource;
	
	
	
	public BaiscDataEditingSupportDecorator(EStructuralFeature feature,IARESResource resource) {
		this(new IEStructuralFeatureProvider.Impl(feature),resource);
	}
	
	public BaiscDataEditingSupportDecorator(IEStructuralFeatureProvider provider,IARESResource resource) {
		this.provider = provider;
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editingsupports.IEditingSupportDecorator#decorateGetCellEditor(org.eclipse.jface.viewers.CellEditor, java.lang.Object)
	 */
	@Override
	public CellEditor decorateGetCellEditor(CellEditor cellEditor,
			Object element) {
//		String stdName =  provider.getFeature(element).getName(); //标准字段名
//		StandardField stdField = JRESResProviderUtil.getMetadataModel(resource.getARESProject(), stdName, IMetadataRefType.StdField, StandardField.class);
//		stdField.getDictionaryType();  //数据字典
		return cellEditor;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.editingsupports.IEditingSupportDecorator#decorateCanEdit(boolean, java.lang.Object)
	 */
	@Override
	public boolean decorateCanEdit(boolean canEdit, Object element) {
		if (element instanceof MetadataItem) {
		} else if (element instanceof UncategorizedItemsCategoryImpl) {
			return false;
		}
		return canEdit;
	}

}
