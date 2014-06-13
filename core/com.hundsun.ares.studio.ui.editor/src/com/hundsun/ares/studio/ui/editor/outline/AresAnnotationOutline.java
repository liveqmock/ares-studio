/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.editor.outline;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
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

import com.hundsun.ares.studio.core.model.FieldDetail;
import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;

/**
 * ARES默认的大纲
 * 解析模型绑定的标注，生成大纲
 * @author maxh
 *
 */
public class AresAnnotationOutline extends ContentOutlinePage implements PropertyChangeListener{
	AresAnnotationOutlineProvider provider = new AresAnnotationOutlineProvider();
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
		if(getTreeViewer() != null){
			getTreeViewer().refresh();
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
	}
	
}

class AresAnnotationOutlineProvider  implements ITreeContentProvider, ILabelProvider{

	public Object[] getChildren(Object parentElement) {
		try {
			if(parentElement instanceof AresAnnotationOutlineInfo){
				AresAnnotationOutlineInfo info = (AresAnnotationOutlineInfo)parentElement;
				if(info.m != null){
					Object value = info.getFieldValue(info.obj);
					if(value instanceof Object[]){
						Object[] obj = (Object[])value;
						List<AresAnnotationOutlineInfo> infos = new ArrayList<AresAnnotationOutlineInfo>();
						for(Object o:obj){
							infos.addAll(getOutlineObject(o));
						}
						return infos.toArray();
					}else if(value instanceof Collection){
						Collection obj = (Collection)value;
						List<AresAnnotationOutlineInfo> infos = new ArrayList<AresAnnotationOutlineInfo>();
						for(Object o:obj){
							infos.addAll(getOutlineObject(o));
						}
						return infos.toArray();
					}
					return new Object[]{};
				}else{
					return getAllOutlineMethods(info.obj).toArray();
				}
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
		return getAllOutlineMethods(inputElement).toArray();
	}

	public void dispose() {
		
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	public Image getImage(Object element) {
		try {
			if(element instanceof AresAnnotationOutlineInfo){
				AresAnnotationOutlineInfo info = (AresAnnotationOutlineInfo)element;
				if(!StringUtil.isEmpty(info.annotation.showPic())){
					String key = info.annotation.showPic();
					URL keyUrl = info.obj.getClass().getClassLoader().getResource(key);
					return ARESEditorPlugin.getImage(key, keyUrl);
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

	public String getText(Object element) {
		try {
			if(element instanceof AresAnnotationOutlineInfo){
				AresAnnotationOutlineInfo info = (AresAnnotationOutlineInfo)element;
				String text = info.annotation.showName();
				if(info.m != null){
					Object value = info.getFieldValue(info.obj);
					if(value instanceof Object[] || value instanceof Collection){
						return text;
					}else{
						return text + ":" + value.toString();
					}
				}else{
					String add = "";
					Method method = info.obj.getClass().getMethod(info.annotation.mainGetMethod());
					if(method != null){
						add = method.invoke(info.obj).toString();
					}
					return text+add;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addListener(ILabelProviderListener listener) {
		
	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		
	}
	
	
	List<AresAnnotationOutlineInfo> getOutlineObject(Object obj){
		List<AresAnnotationOutlineInfo> methods = new ArrayList<AresAnnotationOutlineInfo>();
		FieldDetail annotation = obj.getClass().getAnnotation(FieldDetail.class);
		if(annotation != null && annotation.showInOutline()){
			methods.add(new AresAnnotationOutlineInfo(annotation,null,obj));
		}
		return methods;
	}
	
	List<AresAnnotationOutlineInfo> getAllOutlineMethods(Object obj){
		List<AresAnnotationOutlineInfo> methods = new ArrayList<AresAnnotationOutlineInfo>();
		getMethods(obj.getClass(),obj,methods);
		return methods;
	}
	
	void getMethods(Class cls,Object obj,List<AresAnnotationOutlineInfo> set){
		for(Field m:cls.getDeclaredFields()){
			FieldDetail annotation = m.getAnnotation(FieldDetail.class);
			if(annotation != null && annotation.showInOutline()){
				set.add(new AresAnnotationOutlineInfo(annotation,m,obj));
			}
		}
		if(cls.getSuperclass() != null){
			getMethods(cls.getSuperclass(),obj,set);
		}
	}
	
}

class AresAnnotationOutlineInfo{
	public FieldDetail annotation;
	public Field m;
	public Object obj;
	public AresAnnotationOutlineInfo(FieldDetail annotation,Field m,Object obj){
		this.annotation = annotation;
		this.m = m;
		this.obj = obj;
	}
	
	public Object getFieldValue(Object obj){
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
}
