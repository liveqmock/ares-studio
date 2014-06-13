/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.grid;

import java.text.MessageFormat;
import java.util.Set;

import org.eclipse.core.databinding.observable.map.IMapChangeListener;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.map.MapChangeEvent;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.graphics.Image;

/**
 * 
 * @author wangdong
 */
public class GenericObservableMapCellLabelProvider extends ColumnLabelProvider{
	/**
	 * Template text
	 */
	private String templateText;

	/**
	 * The attribute maps
	 */
	private final IObservableMap[] attributeMaps;

	/**
	 * Change listener to track changes
	 */
	private IMapChangeListener mapChangeListener = new IMapChangeListener() {
		public void handleMapChange(MapChangeEvent event) {
			Set affectedElements = event.diff.getChangedKeys();
			LabelProviderChangedEvent newEvent = new LabelProviderChangedEvent(
					GenericObservableMapCellLabelProvider.this, affectedElements.toArray());
			fireLabelProviderChanged(newEvent);
		}
	};

	/**
	 * @param attributeMap
	 * @param templateText 
	 */
	public GenericObservableMapCellLabelProvider(IObservableMap attributeMap,
			String templateText) {
		this(new IObservableMap[] { attributeMap },templateText);
	}

	/**
	 * @param attributeMaps
	 * @param templateText 
	 */
	public GenericObservableMapCellLabelProvider(IObservableMap[] attributeMaps,String templateText) {
		this.attributeMaps = attributeMaps;
		for (int i = 0; i < attributeMaps.length; i++) {
			attributeMaps[i].addMapChangeListener(mapChangeListener);
		}
		this.templateText = templateText;
	}

	public void dispose() {
		for (int i = 0; i < attributeMaps.length; i++) {
			attributeMaps[i].removeMapChangeListener(mapChangeListener);
		}
		super.dispose();
	}

	public Image getImage(Object element) {
		return null;
	}

	public String getText(Object element) {
		if (templateText != null && templateText.length() > 0) {
			Object[] elements = new Object[attributeMaps.length];
			for (int i = 0; i < elements.length; i++) {
				elements[i] = attributeMaps[i].get(element);
			}
			return MessageFormat.format(templateText, elements);
		}
		
		return null;
	}
}
