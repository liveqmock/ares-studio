/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.page.masterdetail;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.hundsun.ares.studio.core.util.StringUtil;
import com.hundsun.ares.studio.ui.control.ComboAdaptor;
import com.hundsun.ares.studio.ui.control.DateAdaptor;
import com.hundsun.ares.studio.ui.control.TextAdaptor;
import com.hundsun.ares.studio.ui.extendpoint.manager.AresExtendPointFieldDetail;
import com.hundsun.ares.studio.ui.extendpoint.manager.AresExtendPointModelDetail;
import com.hundsun.ares.studio.ui.extendpoint.manager.ExtendFieldDetailManager;
import com.hundsun.ares.studio.ui.util.ImporveControlWithDitryStateContext;

/**
 * 模型生成页面
 * @author maxh
 *
 */
public class ModelBindDetailPage extends AresDetailsPage {

	String description;
	String sectionName;
	Class type;
	
	GridData small = new GridData(SWT.FILL,SWT.FILL,true,false);
	GridData normal = new GridData(SWT.FILL,SWT.FILL,true,false);
	GridData large = new GridData(SWT.FILL,SWT.FILL,true,false);
	
	public ModelBindDetailPage(String description,String sectionName,Class type) {
		super();
		this.description = description;
		this.sectionName = sectionName;
		this.type = type;
		small.heightHint = 75;
		normal.heightHint = 150;
		large.heightHint = 250;
	}
	
	@Override
	protected void createSectionContents(Composite client) {
		ImporveControlWithDitryStateContext context = page.createContext(client, form);
		AresExtendPointModelDetail modelDetail = ExtendFieldDetailManager.getDefault().getMap().get(type);
		for(AresExtendPointFieldDetail fieldDetail:modelDetail.getFields()){
			if(fieldDetail.isGenUi()){
				String showName = StringUtil.getStringSafely(fieldDetail.getShowName());
				String fieldName = StringUtil.getStringSafely(fieldDetail.getFieldName());
				String[] value = StringUtil.getStringSafely(fieldDetail.getValue()).split(",");
				if(fieldDetail.getShowControlType().equals(AresExtendPointFieldDetail.TEXT_TYPE)){
					TextAdaptor text = new TextAdaptor(showName,SWT.NULL,context,fieldName);
					contrllist.add(text);
				}else if(fieldDetail.getShowControlType().equals(AresExtendPointFieldDetail.COMBO_TYPE)){
					ComboAdaptor combo = new ComboAdaptor(showName,SWT.NULL,context,value,fieldName);
					contrllist.add(combo);
				}else if(fieldDetail.getShowControlType().equals(AresExtendPointFieldDetail.DATE_TYPE)){
					DateAdaptor date = new DateAdaptor(showName,SWT.NULL,context,fieldName);
					contrllist.add(date);
				}else if(fieldDetail.getShowControlType().equals(AresExtendPointFieldDetail.MULTI_TEXT_SMALL)){
					TextAdaptor text = new TextAdaptor(showName,SWT.MULTI,context,fieldName){
						@Override
						protected void adjustControl() {
							getControl().setLayoutData(small);
						}
					};
					contrllist.add(text);
				}else if(fieldDetail.getShowControlType().equals(AresExtendPointFieldDetail.MULTI_TEXT_NORMAL)){
					TextAdaptor text = new TextAdaptor(showName,SWT.MULTI,context,fieldName){
						@Override
						protected void adjustControl() {
							getControl().setLayoutData(normal);
						}
					};
					contrllist.add(text);
				}else if(fieldDetail.getShowControlType().equals(AresExtendPointFieldDetail.MULTI_TEXT_LARGE)){
					TextAdaptor text = new TextAdaptor(showName,SWT.MULTI,context,fieldName){
						@Override
						protected void adjustControl() {
							getControl().setLayoutData(large);
						}
					};
					contrllist.add(text);
				}
			}
		}
	}

	@Override
	protected String getDescription() {
		return StringUtil.getStringSafely(description);
	}

	@Override
	protected String getSectionName() {
		return StringUtil.getStringSafely(sectionName);
	}

}
