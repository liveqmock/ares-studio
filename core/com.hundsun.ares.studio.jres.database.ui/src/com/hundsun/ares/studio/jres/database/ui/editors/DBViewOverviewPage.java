/**
 * 源程序名称：DBViewOverviewPage.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.editors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EventObject;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.core.IARESModule;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleConstant;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleRefType;
import com.hundsun.ares.studio.jres.database.utils.IRevHistoryVersionCompartor;
import com.hundsun.ares.studio.jres.model.database.DatabasePackage;
import com.hundsun.ares.studio.jres.model.database.ViewResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.OraclePackage;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.DataBindingFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.editor.editable.JresDefaultEditableUnit;
import com.hundsun.ares.studio.ui.editor.extend.ExtensibleModelComposite;

/**
 * @author qinyuan
 *
 */
public class DBViewOverviewPage  extends DataBindingFormPage {
	
	private ExtensibleModelComposite emc;
	private Text txtName;
	private Text txtObjectID;
	private Text txtChineseName;
	private Text txtDescription;
	private Text txtSQL;
	private Combo comboCurTable;
	private Button txtIsHis;
	
	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public DBViewOverviewPage(EMFFormEditor editor, String id, String title) {
		super(editor, id, title);
		
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormPage#doCreateFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {
		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		managedForm.getForm().setText(getTitle());
		toolkit.decorateFormHeading(managedForm.getForm().getForm());
		
		Section baseSection = createBaseInfoSection(composite, toolkit);
		Section extendSection = createExtendedInfoSection(composite, toolkit);
		
		GridLayoutFactory.swtDefaults().applyTo(composite);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(baseSection);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(extendSection);
		
		composite.getParent().layout();
	}

	/**
	 * 创建基本信息页
	 * @param parent
	 * @param toolkit
	 * @return
	 */
	protected Section createBaseInfoSection(Composite parent, FormToolkit toolkit) {
		Section section = toolkit.createSection(parent, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("基本信息");
		
		// 创建控件
		Composite client = toolkit.createComposite(section,SWT.NONE);
		
		Label lblObjectID = toolkit.createLabel(client, "对象号：");
		txtObjectID = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		Label lblName = toolkit.createLabel(client, "名称：");
		txtName = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles()|SWT.READ_ONLY);
		
		Label lblChineseName = toolkit.createLabel(client, "中文名：");
		txtChineseName = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		Label lblCurTable = toolkit.createLabel(client, "表空间：");
		comboCurTable = new Combo(client, SWT.READ_ONLY);
		{
			List<String> items = new ArrayList<String>();
			List<ReferenceInfo> refs = ReferenceManager.getInstance().getReferenceInfos(getEditor().getARESResource().getARESProject(),IOracleRefType.Space , true);
			for(ReferenceInfo info : refs){
				items.add(info.getRefName());
			}
//			Collections.sort(items);//排序
			Collections.sort(items, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.toUpperCase().compareTo(o2.toUpperCase());//不区分大小写
				}
			});
			comboCurTable.setItems(items.toArray(new String[0]));
		}
		
		Label lblVersion = toolkit.createLabel(client, "版本号：");
		Text texVersion = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles()|SWT.READ_ONLY);
		texVersion.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		
		Label lblUpdate = toolkit.createLabel(client, "更新时间：");
		Text texUpdate = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles()|SWT.READ_ONLY);
		texUpdate.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		
		{
			//找出最新的版本号
			EObject obj = getEditor().getInfo();
			if (obj instanceof ViewResourceData) {
				List<RevisionHistory> hises = ((ViewResourceData) obj).getHistories();
				List<RevisionHistory> tempHis = (List<RevisionHistory>) EcoreUtil.copyAll(hises);
				Collections.sort(tempHis, new IRevHistoryVersionCompartor());
				if (hises.size() > 0) {
					texVersion.setText(tempHis.get(0).getVersion());
					texUpdate.setText(tempHis.get(0).getModifiedDate());
				}else {
					//2013年5月24日14:43:41 如果没有修改记录信息，则取所在子系统当前版本号+1
					IARESResource aresResource = getEditor().getARESResource();
					
					IARESModule topModule = null; 
					if (aresResource == null) {
						topModule = null; 
					} else {
						String rootType = aresResource.getRoot().getType(); 
						if (ARESElementUtil.isDatabaseRoot(rootType)) {
							topModule = ARESElementUtil.getTopModule(aresResource);
						} else if (ARESElementUtil.isMetadataRoot(rootType)) {
							// topModule为null的效果就是不计算模块
							topModule = null;
						} else {
							topModule = aresResource.getModule();
						}
					}
					
					// 当前已经保存的资源中的最大版本
					RevisionHistory his = RevisionHistoryUtil.getMaxVersionHisInfo(topModule);
					if (his != null) {
						String currentVersion = his.getVersion();
						// 项目属性
						String projectVersion = RevisionHistoryUtil.getProjectPropertyVersion(aresResource.getARESProject());
						
						// 找上述3者最大值
						String versionStr = RevisionHistoryUtil.max(Arrays.asList(currentVersion, projectVersion));
						// 第一次找不到任何记录的时候
						if (StringUtils.equals(currentVersion, versionStr)) {
							texUpdate.setText(his.getModifiedDate());
						}
						if (StringUtils.isEmpty(versionStr)) {
							versionStr = "1.0.0.0";
						} 
						try{
							texVersion.setText(RevisionHistoryUtil.increase(versionStr));
							
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		Label lbIsHis = new Label(client, SWT.NONE);
		lbIsHis.setText("存在历史表：");
		txtIsHis = new Button(client, SWT.CHECK);
		
		Label lblDescription = toolkit.createLabel(client, "说明：");
		txtDescription = toolkit.createText(client, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		Label lblSQL = toolkit.createLabel(client, "SQL语句：");
		
		//增加这一层，是为了让错误提示的图标正常显示，而不是显示半个，这也是测试提的一个BUG
		Composite textClient = toolkit.createComposite(client,SWT.NONE);
		txtSQL = toolkit.createText(textClient, StringUtils.EMPTY, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
		
		// 只读控制
		txtName.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtObjectID));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtIsHis));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(comboCurTable));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtChineseName));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtDescription));
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtSQL));
		
		// 布局
		GridLayout aa = new GridLayout();
		aa.marginLeft = 1;
		textClient.setLayout(aa);
		
		GridLayoutFactory.swtDefaults().numColumns(4).applyTo(client);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lblObjectID);
		GridDataFactory.fillDefaults().grab(true, false).hint(10, SWT.DEFAULT).applyTo(txtObjectID);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lblName);
		GridDataFactory.fillDefaults().grab(true, false).hint(10, SWT.DEFAULT).applyTo(txtName);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lblChineseName);
		GridDataFactory.fillDefaults().grab(true, false).hint(10, SWT.DEFAULT).applyTo(txtChineseName);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lblCurTable);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(comboCurTable);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lblVersion);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(texVersion);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lblUpdate);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(texUpdate);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbIsHis);
		GridDataFactory.fillDefaults().grab(true, false).span(3, 1).applyTo(txtIsHis);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lblDescription);
		GridDataFactory.fillDefaults().grab(true, false).span(3, 1).hint(10, SWT.DEFAULT).applyTo(txtDescription);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lblSQL);
		GridDataFactory.fillDefaults().grab(true, true).span(3, 1).align(SWT.FILL, SWT.FILL).applyTo(textClient);
		GridDataFactory.swtDefaults().hint(-1, 80).grab(true, true).align(SWT.FILL, SWT.TOP).applyTo(txtSQL);
		
		section.setClient(client);
		return section;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.pages.DataBindingFormPage#doDataBingingOnControls()
	 */
	@Override
	protected void doDataBingingOnControls() {
		bingText(txtObjectID, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__OBJECT_ID);
		bingSelection(comboCurTable, getInfo().getData2().get(IOracleConstant.TABLE_DATA2_KEY),OraclePackage.Literals.ORACLE_TABLE_PROPERTY__SPACE );
		bingSelection(txtIsHis, getInfo(), DatabasePackage.Literals.VIEW_RESOURCE_DATA__IS_HISTORY);
		bingText(txtName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__NAME);
		bingText(txtChineseName, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__CHINESE_NAME);
		bingText(txtDescription, getInfo(), CorePackage.Literals.BASIC_RESOURCE_INFO__DESCRIPTION);
		bingText(txtSQL, getInfo(), DatabasePackage.Literals.VIEW_RESOURCE_DATA__SQL);
	}
	
	@Override
	public void infoChange() {
		emc.setInput(getEditor().getARESResource(), getInfo());
		super.infoChange();
	}
	
	@Override
	public void commandStackChanged(EventObject event) {
		super.commandStackChanged(event);
		emc.refresh();
	}
	
	protected Section createExtendedInfoSection(Composite parent, FormToolkit toolkit) {
		Section section = toolkit.createSection(parent, FormWidgetUtils.getDefaultSectionStyles());
		section.setText("扩展信息");
		
		emc = new ExtensibleModelComposite(section, toolkit);
		emc.setProblemPool(getProblemPool());
//		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(emc));
		
		section.setClient(emc);
		return section;
	}
	
}