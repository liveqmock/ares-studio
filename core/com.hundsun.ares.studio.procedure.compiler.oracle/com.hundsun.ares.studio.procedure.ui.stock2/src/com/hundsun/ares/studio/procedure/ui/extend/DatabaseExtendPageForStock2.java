/**
 * 
 */
package com.hundsun.ares.studio.procedure.ui.extend;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;

import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.ExtensibleModelConfigProperty;
import com.hundsun.ares.studio.core.model.UserExtensibleProperty;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.DatabaseResourceData;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.EMFExtendSectionScrolledFormPage;

/**
 * @author yanwj06282
 *
 */
public class DatabaseExtendPageForStock2 extends
		EMFExtendSectionScrolledFormPage<DatabaseResourceData> {

	private static final String USER_EXTEND_KEY = "stock2_database_version";
	CommandStackListener commandStackListener;
	
	public DatabaseExtendPageForStock2(FormEditor editor, String id,
			String title) {
		super(editor, id, title);
	}

	@Override
	protected EClass getEClass() {
		return DatabasePackage.Literals.DATABASE_RESOURCE_DATA;
	}

	@Override
	protected String getMapKey() {
		return "DatabaseResourceData";
	}

	@Override
	protected void createSections(IManagedForm managedForm) {
		//因为是隐藏页面，所以不需要页面元素
	}
	
	/**
	 * 保存创建时间
	 * 
	 */
	private String getCurrentVersion() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		EObject info = getInfo();
		String value = "";
		if(null != info && info instanceof DatabaseResourceData) {
			DatabaseResourceData pro = (DatabaseResourceData)info;
			EObject obj = pro.getData2().get("user");
			String oldCreateDate = StringUtils.EMPTY;
			if (obj instanceof UserExtensibleProperty) {
				oldCreateDate = ((UserExtensibleProperty) obj).getMap().get(USER_EXTEND_KEY);
			}
			if(StringUtils.startsWith(oldCreateDate, date)) {
				int modifyNum = Integer.parseInt(oldCreateDate.substring(8));
				value = date + (modifyNum+1);
			}else {
				value = date + 1;
			}
		}
		return value;
	}
	
	@Override
	public boolean doSave() {
		if (isEnable(DatabasePackage.Literals.DATABASE_RESOURCE_DATA)) {
			EObject obj = getInfo().getData2().get("user");
			UserExtensibleProperty property = CoreFactory.eINSTANCE.createUserExtensibleProperty();
			UserExtensibleProperty oldProperty;
			String currentVersion = getCurrentVersion();
			if (StringUtils.isNotBlank(currentVersion)) {
				if (obj != null) {
					oldProperty = (UserExtensibleProperty) obj;
					property.getMap().putAll(oldProperty.getMap());
				}
				EMap<String, String> map = property.getMap();
				map.put(USER_EXTEND_KEY, getCurrentVersion());
				Command command = new SetCommand(getEditingDomain(), getInfo().getData2().get("user"), CorePackage.Literals.USER_EXTENSIBLE_PROPERTY__MAP, map);
				((EMFFormEditor)getEditor()).getEditingDomain().getCommandStack().execute(command);
			}
		}
		return super.doSave();
	}
	
	@Override
	public boolean shouldLoad() {
		return false;
	}

	private boolean isEnable(EClass eClass) {
		for (Element element : getExsibleModelConfigList()) {
			EClass clazz = getEClass(element.attributeValue("uri"), element.attributeValue("class"));
			if (clazz != null && clazz.isSuperTypeOf(eClass)) {
				return true;
			}
		}
		return false;
	}
	
	private EClass getEClass(String uri, String className) {
		EPackage p = EPackage.Registry.INSTANCE.getEPackage(uri);
		if (p != null) {
			return (EClass) p.getEClassifier(className);
		}
		return null;
	}
	
	private List<Element> getExsibleModelConfigList() {
		try {
			ExtensibleModelConfigProperty config = (ExtensibleModelConfigProperty) getARESProject().getInfo().getMap().get("ExtensibleModelConfigProperty");
			if (config == null) {
				config = CoreFactory.eINSTANCE.createExtensibleModelConfigProperty();
			}
			
			String xml = config.getXml();
			
			if (StringUtils.isNotBlank(xml)) {
				Document doc = config.getXmlCache();
				if (doc != null) {
					return doc.getRootElement().elements("ExtensibleModel");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}
	
}
