package com.hundsun.ares.studio.atom.ui.editor.page;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.atom.constants.IAtomResType;
import com.hundsun.ares.studio.biz.ui.action.IBizActionIDConstants;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.util.RevisionHistoryUtil;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.editor.editable.JresDefaultEditableUnit;

public class AtomServiceInterfacePage extends AtomFunctionInterfacePage {

	/**
	 * 超时时间
	 */
	Text txtTimeout;
	
	public AtomServiceInterfacePage(EStructuralFeature interfaceFeature,
			EMFFormEditor editor, String id, String title) {
		super(interfaceFeature, editor, id, title);
	}
	
	@Override
	protected void doDataBingingOnControls() {
		super.doDataBingingOnControls();
		bingText(txtTimeout, getInfo(), AtomPackage.Literals.ATOM_SERVICE__TIMEOUT);
	}
	
	@Override
	protected Section createBasicInfoSection(Composite composite,
			FormToolkit toolkit) {
		Section section = super.createBasicInfoSection(composite, toolkit);
		
		{
			//找出最新的版本号
			RevisionHistory his = RevisionHistoryUtil.getMaxVersionHisInfo(getEditor().getARESResource().getModule(),IAtomResType.ATOM_SERVICE);
			if (his != null) {
				txtVersion.setText(his.getVersion());
				txtUpdateTime.setText(his.getModifiedDate());
			}else {
				his = RevisionHistoryUtil.getMaxVersionHisInfo(getEditor().getARESResource().getModule());
				if (his != null) {
					txtVersion.setText(his.getVersion());
					txtUpdateTime.setText(his.getModifiedDate());
				}else {
					String projectVersion = RevisionHistoryUtil.getProjectPropertyVersion(getEditor().getARESResource().getARESProject());
					if (StringUtils.isBlank(projectVersion)) {
						projectVersion = "1.0.0.1";
					}
					txtVersion.setText(projectVersion);
				}
			}
		}
		
		// 追加控件
		
		Composite client = (Composite)section.getClient();
		Composite timeoutItem = createGridComposite(client,toolkit);
		
		Label lbTimeout = toolkit.createLabel(timeoutItem, "超时时间：");
		txtTimeout = toolkit.createText(timeoutItem, StringUtils.EMPTY, FormWidgetUtils.getDefaultSingleLineTextStyles());
		
		
		// 只读控制
		getEditableControl().addEditableUnit(new JresDefaultEditableUnit(txtTimeout));
		
		// 布局
		GridDataFactory.fillDefaults().grab(false, false).applyTo(lbTimeout);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(txtTimeout);

		//section.setClient(client);
		
		return section;
	}
	
	@Override
	protected void customizeInputParamBlock() {
		inputParamBlock.setAddActionIds(new String[] {IBizActionIDConstants.CV_ADD,
				IBizActionIDConstants.ADD_OBJECT_PARAM, IBizActionIDConstants.ADD_PARAM_GROUP});
	}
	@Override
	protected void customizeOutputParamBlock() {
		outputParamBlock.setAddActionIds(new String[] {IBizActionIDConstants.CV_ADD,
				IBizActionIDConstants.ADD_OBJECT_PARAM, IBizActionIDConstants.ADD_PARAM_GROUP});
	}
	

}
