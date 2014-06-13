/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.editor.outline;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.extendpoint.manager.AresExtendPointFieldDetail;
import com.hundsun.ares.studio.ui.extendpoint.manager.AresExtendPointModelDetail;
import com.hundsun.ares.studio.ui.extendpoint.manager.ExtendFieldDetailManager;
import com.hundsun.ares.studio.ui.util.KeyValue;

public class AresExtendPointOutline extends ContentOutlinePage implements PropertyChangeListener{
	AresExtendPointOutlineProvider provider = new AresExtendPointOutlineProvider();
	Object info;
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		getTreeViewer().setContentProvider(provider);
		getTreeViewer().setLabelProvider(provider);
		getTreeViewer().getTree().setBackground(ARESEditorPlugin.getDefault().getColorManager().getColor(new RGB(225,240,255)));
		getTreeViewer().setInput(info);
	}
	
	public void setInput(Object info) {
		this.info = info;
	}
	
	public void refresh(){
		getTreeViewer().refresh();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
	}
}

class AresExtendPointOutlineProvider  implements ITreeContentProvider, ILabelProvider{

	public Object[] getChildren(Object parentElement) {
		try {
			if(parentElement instanceof KeyValue){
				Object obj = ((KeyValue)parentElement).getKey();
				if(((KeyValue)parentElement).getValue() instanceof AresExtendPointFieldDetail){
					AresExtendPointFieldDetail field = (AresExtendPointFieldDetail)((KeyValue)parentElement).getValue();
					Object value = getFieldValue(obj,field.getFieldName());
					if(value instanceof Object[]){
						Object[] array = (Object[])value;
						List<KeyValue> infos = new ArrayList<KeyValue>();
						for(Object o:array){
							AresExtendPointModelDetail modelInfo = ExtendFieldDetailManager.getDefault().getMap().get(o.getClass());
							if(modelInfo != null){
								infos.add(new KeyValue(o,modelInfo));
							}
						}
						return infos.toArray();
					}else if(value instanceof Collection){
						Collection collection = (Collection)value;
						List<KeyValue> infos = new ArrayList<KeyValue>();
						for(Object o:collection){
							AresExtendPointModelDetail modelInfo = ExtendFieldDetailManager.getDefault().getMap().get(o.getClass());
							if(modelInfo != null){
								infos.add(new KeyValue(o,modelInfo));
							}
						}
						return infos.toArray();
					}
				}
				if(((KeyValue)parentElement).getValue() instanceof AresExtendPointModelDetail){
					AresExtendPointModelDetail model = (AresExtendPointModelDetail)((KeyValue)parentElement).getValue();
					List<KeyValue> result = new ArrayList<KeyValue>();
					for(AresExtendPointFieldDetail field:model.getFields()){
						result.add(new KeyValue(obj,field));
					} 
					return result.toArray();
				}
				return new Object[]{};
			}
		} catch (Exception e) {
		}
		return null;

	}

	public Object getParent(Object element) {
		return null;
	}

	public boolean hasChildren(Object element) {
		Object[] children = getChildren(element);
		return (children != null)&&(children.length>0);
	}

	public Object[] getElements(Object inputElement) {
		Class type = inputElement.getClass();
		AresExtendPointModelDetail info = ExtendFieldDetailManager.getDefault().getMap().get(type);
		if(info != null){
			List<KeyValue> result = new ArrayList<KeyValue>();
			for(AresExtendPointFieldDetail field:info.getFields()){
				result.add(new KeyValue(inputElement,field));
			} 
			return result.toArray();
		}
		return new Object[]{};
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	public Image getImage(Object element) {
		if(element instanceof KeyValue){
			
		}
		return null;
	}

	public String getText(Object element) {
		if(element instanceof KeyValue){
			Object obj = ((KeyValue)element).getKey();
			if(((KeyValue)element).getValue() instanceof AresExtendPointFieldDetail){
				AresExtendPointFieldDetail field = (AresExtendPointFieldDetail)((KeyValue)element).getValue();
				Object value = getFieldValue(obj,field.getFieldName());
				if(value instanceof Object[]){
					return field.getShowName();
				}else if(value instanceof Collection){
					return field.getShowName();
				}else{
					return field.getShowName() + ":" + value.toString();
				}
			}else if(((KeyValue)element).getValue() instanceof AresExtendPointModelDetail){
				AresExtendPointModelDetail model = (AresExtendPointModelDetail)((KeyValue)element).getValue();
				if(StringUtil.isEmpty(model.getMainGetMethod())){
					return model.getShowName();
				}
				try {
					Method method = obj.getClass().getMethod(model.getMainGetMethod());
					if(method != null){
						Object value = method.invoke(obj);
						return model.getShowName() + ":" + value.toString();
					}
				} catch (Exception e) {
				}
				return model.getShowName();
			}
		}
		return null;
	}

	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}
	
	Object getFieldValue(Object obj,String fieldName){
		Field m = getField(obj.getClass(),fieldName);
		Object result = null;
		if(m != null){
			boolean flag = m.isAccessible();
			try {
				m.setAccessible(true);
				result = m.get(obj);
			} catch (Exception e) {
			}finally{
				m.setAccessible(flag);
			}
		}
		return result;
	}
	
	Field getField(Class cls,String fieldName){
		for(Field m:cls.getDeclaredFields()){
			if(m.getName().equals(fieldName)){
				return m;
			}
		}
		if(cls.getSuperclass() != null){
			return getField(cls.getSuperclass(),fieldName);
		}
		return null;
	}
}

