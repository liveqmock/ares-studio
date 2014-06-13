/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.editor;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;

import com.hundsun.ares.studio.ui.extendpoint.manager.ExtendPageInfo;
import com.hundsun.ares.studio.ui.page.ExtendPageWithMyDirtySystem;
import com.hundsun.ares.studio.ui.page.UserConfigPage;
import com.hundsun.ares.studio.ui.userdialog.DialogInterfaceXml;
import com.hundsun.ares.studio.ui.userdialog.XmlConfigInterface;
import com.hundsun.ares.studio.ui.userdialog.XmlConfigInterfaceConverter;

/**
 * 可以通过拓展点增加页面的编辑器
 * 
 * @author maxh
 * 
 */
public abstract class AbstractHSExtendPointFormEditor<T> extends BasicAresFormEditor<T> {
	
	private static Logger logger = Logger.getLogger(AbstractHSExtendPointFormEditor.class);
	/**
	 * 保存从拓展点创建的页面 KEY为拓展点中配置的页面的ID
	 */
	protected Map<String, IFormPage> extendsPointPages = new HashMap<String, IFormPage>();

	public Map<String, IFormPage> getExtendsPointPages() {
		return extendsPointPages;
	}

	@Override
	protected void addPages() {
		createExtendPage();
		createUserConfigPage();
	}

	/**
	 * 读取拓展页面
	 */
	private void createExtendPage() {
		for(ExtendPageInfo info:ARESEditorPlugin.getExtendPageManager().getPageInfo(getSite().getId())){
			try {
				Class cls = info.getPageClass();
				Constructor cst = cls.getConstructor(new Class[] { FormEditor.class, String.class, String.class });
				ExtendPageWithMyDirtySystem page = (ExtendPageWithMyDirtySystem) cst.newInstance(new Object[] { this, info.getPageId(), info.getPageName() });
				if (page.shouldLoad()) {
					addPageContext(page);
					addPage(page);
					extendsPointPages.put(info.getPageId(), page);
				}

			} catch (Exception e) {
				logger.error("读取拓展页面异常", e);
			}
		}
	}

	private void createUserConfigPage() {
		if(getARESProject()!=null){
			try {
				XmlConfigInterface config = XmlConfigInterfaceConverter.getConverter().getConfig(getARESProject());
				if(config != null){
					DialogInterfaceXml dialogInterfaceXml = config.getMenuInterfaceXml(getSite().getId());
					if(dialogInterfaceXml != null){
						UserConfigPage page = new UserConfigPage(this,dialogInterfaceXml.getTitle(),dialogInterfaceXml.getTitle(),dialogInterfaceXml);
						addPageContext(page);
						addPage(page);
						extendsPointPages.put(dialogInterfaceXml.getTitle(), page);
					}
				}
			} catch (Exception e) {
				logger.error("读取用户配置页面异常",e);
			}
		}
	}
}
