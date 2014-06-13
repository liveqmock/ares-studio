/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.page;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.core.model.extendable.IExtendAbleModel;
import com.hundsun.ares.studio.core.model.extendable.UserConfigMap;
import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.ui.control.CheckButtonAdaptor;
import com.hundsun.ares.studio.ui.control.ComboAdaptor;
import com.hundsun.ares.studio.ui.control.TextAdaptor;
import com.hundsun.ares.studio.ui.userdialog.DialogInterfaceGroup;
import com.hundsun.ares.studio.ui.userdialog.DialogInterfaceItem;
import com.hundsun.ares.studio.ui.userdialog.DialogInterfaceXml;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;

/**
 * 
 * @author maxh
 */
public class UserConfigPage extends ExtendSectionScrolledFormPage {
	DialogInterfaceXml xmlDialog;
	/**
	 * 
	 */
	public UserConfigPage(FormEditor editor, String id, String title,DialogInterfaceXml xmlDialog) {
		super(editor, id, title);
		this.xmlDialog = xmlDialog;
	}
	UserConfigMap map;
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendSectionScrolledFormPage#createSections(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createSections(IManagedForm managedForm) {
		if(info instanceof IExtendAbleModel){
			String id = xmlDialog.getFileId().equals("")?getEditor().getSite().getId():xmlDialog.getFileId();
			Object o = ((IExtendAbleModel)info).getMap().get(id);
			if((o == null) || (!(o instanceof UserConfigMap))){
				map = new UserConfigMap();
				((IExtendAbleModel)info).getMap().put(id, map);
			}
			map = (UserConfigMap)((IExtendAbleModel)info).getMap().get(id);
			for (int groupIndex = 0; groupIndex < xmlDialog.getLstMenuInterfaceGroup().size (); groupIndex++) {
				DialogInterfaceGroup group = xmlDialog.getLstMenuInterfaceGroup().get (groupIndex);
				final List<DialogInterfaceItem> lstItem = group.getLstMenuInterfaceItem ();
				if (group.isUse ()) {
					Section section = createCompositeSection(group.getGroupName(),true);
					ImporveControlWithDitryStateContext context = createContext((Composite)section.getClient(), managedForm);
					for (int i = 0; i < lstItem.size (); i++) {
						final DialogInterfaceItem item = lstItem.get (i);
						if (item.getSwtType ().equalsIgnoreCase ("CHECK")) {
							CheckButtonAdaptor adaptor = new CheckButtonAdaptor(item.getLableName (),SWT.CHECK,context){
								@Override
								public void syncControl() {
									if(getControl() != null){
										setValue(StringUtil.getStringSafely(map.get(item.getId())));
									}
								}
								@Override
								public void syncModel() {
									if(getControl() != null){
										map.put(item.getId(), getValue().toString());
									}
								}
							};
							Button but = adaptor.getControl();
							but.setData (item.getId());
						}else if (item.getSwtType ().equalsIgnoreCase ("TEXT")) {
							TextAdaptor adaptor = new TextAdaptor(item.getLableName (),SWT.BORDER,context){
								@Override
								public void syncControl() {
									if(getControl() != null){
										setValue(StringUtil.getStringSafely(map.get(item.getId())));
									}
								}
								@Override
								public void syncModel() {
									if(getControl() != null){
										map.put(item.getId(), getValue());
									}
								}
							};
							Text text = adaptor.getControl();
							text.setData (item.getId());
						}else if (item.getSwtType ().equalsIgnoreCase ("COMBO")) {
							String[] values = item.getValue ().split (",");
							ComboAdaptor adaptor = new ComboAdaptor(item.getLableName (),SWT.BORDER|SWT.READ_ONLY,context,values){
								/* (non-Javadoc)
								 * @see com.hundsun.ares.studio.ui.control.ControlWithDataBind#syncControl()
								 */
								@Override
								public void syncControl() {
									if(getControl() != null){
										setValue(StringUtil.getStringSafely(map.get(item.getId())));
									}
								}
								/* (non-Javadoc)
								 * @see com.hundsun.ares.studio.ui.control.ControlWithDataBind#syncModel()
								 */
								@Override
								public void syncModel() {
									if(getControl() != null){
										map.put(item.getId(), getValue().toString());
									}
								}
							};
							Combo combo = adaptor.getControl();
							combo.setData (item.getId());
						}
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendPageWithMyDirtySystem#shouldLoad()
	 */
	@Override
	public boolean shouldLoad() {
		return true;
	}

}
