/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.extendpoint;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.ui.control.ComboAdaptor;
import com.hundsun.ares.studio.ui.control.IEditable;
import com.hundsun.ares.studio.ui.control.TextAdaptor;
import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.grid.EditorComponent;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;
import com.hundsun.ares.studio.ui.util.ReflectInvokeHelper;

public class ExtendPointPageHelper {
	
	/**
	 * 页面拓展点的ID
	 */
	static final public String PAGE_EXTEND_DETAIL_ID = ARESEditorPlugin.PLUGIN_ID + ".ares_detail_page";
	
	/**
	 * 编辑器的ID
	 */
	final static public String EDITOR_ID = "editor_id";
	
	/**
	 * 页面的ID
	 */
	final static public String PAGE_ID = "pageid";
	/**
	 * 页面展现的名称
	 */
	final static public String PAGE_NAME = "name";

	/**
	 * 控件填充的section
	 */
	final static public String SECTION_FOR_CONTROL = "sectionForControl";
	/**
	 * table或tree填充的section
	 */
	final static public String SECTION_FOR_COMPONENT = "sectionForComponent";
	
	/**
	 * section的名称
	 */
	final static public String SECTION_NAME = "name";
	/**
	 * section在界面初始化的时候是否展开
	 */
	final static public String SECTION_EXPEND = "expand";
	/**
	 * section的id
	 */
	final static public String SECTION_ID = "id";
	
	/**
	 * 控件绑定的模型字段
	 */
	final static public String BIND_FIELD = "bind_field";
	
	/**
	 * text控件
	 */
	final static public String TEXT = "Text";
	/**
	 * combo控件
	 */
	final static public String COMBO = "Combo";
	/**
	 * 用户自定义控件
	 */
	final static public String CONTROL = "Control";
	
	/**
	 * 用户自定义控件的展现名称
	 */
	final static public String CONTROL_SHOWNAME = "showname";
	
	/**
	 * 控件ID
	 */
	final static public String CONTROL_ID = "id";
	/**
	 * 控件前面LABEL的展现字段
	 */
	final static public String CONTROL_LABEL = "label";
	
	/**
	 * combo的选项
	 */
	final static public String COMBO_ITEMS = "items";
	/**
	 * combo是否只读
	 */
	final static public String COMBO_READ_ONLY = "readonly";
	
	/**
	 * 每行的控件数
	 */
	final static public String SECTION_COL_NUM = "col_num";
	
	final static public String GRIDDATA = "gridData";
	final static public String HA = "horizontalAlignment";
	final static public String VA = "verticalAlignment";
	final static public String GEHS = "grabExcessHorizontalSpace";
	final static public String GEVS = "grabExcessVerticalSpace";
	final static public String HS = "horizontalSpan";
	final static public String VS = "verticalSpan";
	final static public String WH = "widthHint";
	final static public String HH = "heightHint";
	
	IConfigurationElement ce;
	
	String pageId;
	String pageName;
	Object info;
	/**
	 * FIXME 换成IARESResurce
	 */
	IResource resource;
	
	
	
	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public ExtendPointPageHelper(IConfigurationElement ce,Object info,IResource resource) {
		this.ce = ce;
		this.info = info;
		this.resource = resource;
		init();
	}
	
	public String getEditorId(){
		try {
			return ce.getAttribute(EDITOR_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void init(){
		try {
			pageId = ce.getAttribute(PAGE_ID);
			pageName = ce.getAttribute(PAGE_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 缓存页面中所有的控件实例
	 * KEY为控件ID
	 */
	Map<String,Object> items = new HashMap<String,Object>();
	
	public Map<String, Object> getItems() {
		return items;
	}

	public void createSections(ExtendPointPage extendPointPage,
			IManagedForm managedForm) {
		for(IConfigurationElement child:ce.getChildren()){
			try {
				if(child.getName().equals(SECTION_FOR_COMPONENT)){
					createControlSection(extendPointPage,child);
				}else if(child.getName().equals(SECTION_FOR_CONTROL)){
					createComponentSection(extendPointPage,child,managedForm);
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

		}
	}
	
	private Section createControlSection(ExtendPointPage extendPointPage,IConfigurationElement child) throws Exception{
		String sectionName = child.getAttribute(SECTION_NAME);
		String sectionExpend= child.getAttribute(SECTION_EXPEND);
		String sectionId= child.getAttribute(SECTION_ID);
		EditorComponent component = (EditorComponent)child.createExecutableExtension("class");
		String fieldName = child.getAttribute(BIND_FIELD);
		ReflectInvokeHelper reflect = new ReflectInvokeHelper(info,fieldName);
		Section section = extendPointPage.createEditorComponentSection(sectionName, 
				"false".equals(sectionExpend)?false:true,
				component,
				(List)reflect.invokeGetMothod());
		items.put(sectionId, section);
		return section;
	}
	
	GridData data;
	
	private Section createComponentSection(ExtendPointPage extendPointPage,IConfigurationElement child,IManagedForm managedForm) throws Exception{
		
		String sectionName = child.getAttribute(SECTION_NAME);
		String sectionExpend= child.getAttribute(SECTION_EXPEND);
		String sectionId= child.getAttribute(SECTION_ID);
		int col_num = 4;
		try {
			col_num = Integer.valueOf(child.getAttribute(SECTION_COL_NUM));
		} catch (Exception e) {
			col_num = 4;
		}
		
		Section section = extendPointPage.createCompositeSection(sectionName,"false".equals(sectionExpend)?false:true,new GridLayout(col_num,false));
		ImporveControlWithDitryStateContext context = extendPointPage.createImporveControlWithDitryStateContext((Composite)section.getClient(), managedForm);
		for(IConfigurationElement control:child.getChildren()){
			boolean exist = false;
			for(IConfigurationElement layoutElement:control.getChildren()){
				if(layoutElement.getName().equals(GRIDDATA)){
					data = getGridDataFromExtendPoint(layoutElement);
					exist = true;
					break;
				}
			}
			if(!exist){
				data = null;
			}
			if(control.getName().equals(TEXT)){
				String label = control.getAttribute(CONTROL_LABEL);
				String controlId = control.getAttribute(CONTROL_ID);
				String fieldName = control.getAttribute(BIND_FIELD);
				TextAdaptor textAdaptor = new TextAdaptor(label,SWT.BORDER,context,fieldName){
					@Override
					protected void adjustControl() {
						if(data != null){
							this.getControl().setLayoutData(data);
						}
						super.adjustControl();
					}
				};
				extendPointPage.getEditableComponent().add(textAdaptor);
				items.put(controlId, textAdaptor);
			}else if(control.getName().equals(COMBO)){
				String label = control.getAttribute(CONTROL_LABEL);
				String controlId = control.getAttribute(CONTROL_ID);
				String fieldName = control.getAttribute(BIND_FIELD);
				String readonly = control.getAttribute(COMBO_READ_ONLY);
				String[] comboItems = control.getAttribute(COMBO_ITEMS).split(",");
				ComboAdaptor comboAdaptor = new ComboAdaptor(
						label,
						readonly.equals("false")?SWT.NULL:SWT.READ_ONLY,
						context,
						comboItems,
						fieldName
						){
					@Override
					protected void adjustControl() {
						if(data != null){
							this.getControl().setLayoutData(data);
						}
						super.adjustControl();
					}
				};
				extendPointPage.getEditableComponent().add(comboAdaptor);
				items.put(controlId, comboAdaptor);
			}else if(control.getName().equals(CONTROL)){
				String controlId = control.getAttribute(CONTROL_ID);
				String fieldName = control.getAttribute(BIND_FIELD);
				String showName = control.getAttribute(CONTROL_SHOWNAME);
				PageExtendPointControlProvider provider = (PageExtendPointControlProvider)control.createExecutableExtension("class");
				provider.setBindName(fieldName);
				provider.setContext(context);
				provider.setShowName(showName);
				provider.setInfo(info);
				provider.setResource(resource);
				IEditable crol = provider.getControl();
				extendPointPage.getEditableComponent().add(crol);
				items.put(controlId, crol);
			}
		}
		items.put(sectionId, section);
		return section;
	}
	
	GridData getGridDataFromExtendPoint(IConfigurationElement element){
		GridData data = new GridData();
		try {
			data.horizontalAlignment = getSwtMode(element.getAttribute(HA));
		} catch (Exception e) {}
		try {
			data.verticalAlignment = getSwtMode(element.getAttribute(VA));
		} catch (Exception e) {}
		try {
			data.grabExcessHorizontalSpace = Boolean.valueOf(element.getAttribute(GEHS));
		} catch (Exception e) {}
		try {
			data.grabExcessVerticalSpace = Boolean.valueOf(element.getAttribute(GEVS));
		} catch (Exception e) {}
		try {
			data.horizontalSpan = Integer.valueOf(element.getAttribute(HS));
		} catch (Exception e) {}
		try {
			data.verticalSpan = Integer.valueOf(element.getAttribute(VS));
		} catch (Exception e) {}
		try {
			if(element.getAttribute(HH) != null && element.getAttribute(HH).length() > 0){
				data.heightHint = Integer.valueOf(element.getAttribute(HH));
			}
		} catch (Exception e) {}
		try {
			if(element.getAttribute(WH) != null && element.getAttribute(WH).length() > 0){
				data.widthHint = Integer.valueOf(element.getAttribute(WH));
			}
		} catch (Exception e) {}
		return data;
	}
	
	int getSwtMode(String mode){
		if(mode.equalsIgnoreCase("SWT.FILL")){
			return SWT.FILL;
		}else if(mode.equalsIgnoreCase("SWT.BEGINNING")){
			return SWT.BEGINNING;
		}else if(mode.equalsIgnoreCase("SWT.END")){
			return SWT.END;
		}else if(mode.equalsIgnoreCase("SWT.CENTER")){
			return SWT.CENTER;
		}
		return SWT.FILL;
	}
}
