package com.hundsun.ares.studio.jres.metadata.ui.script.control;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.model.metadata.BizPropertyConfig;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.jres.script.internal.useroption.IControl;
import com.hundsun.ares.studio.jres.script.internal.useroption.MultiSelectionCheckControl;
import com.hundsun.ares.studio.jres.script.internal.useroption.control.IUserOptionControlProvider;

public class BizPropertyControlProvider implements IUserOptionControlProvider {

	@Override
	public IControl createControl() {
		return new BizPropertyControl();
	}

	@Override
	public Control createUIControl(Composite parent, IControl control, IARESProject project) {
		List<BizPropertyConfig> input = MetadataUtil.getBizProperties(project);
		MultiSelectionCheckControl uiControl = new MultiSelectionCheckControl(parent, input.toArray(), new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof BizPropertyConfig) {
					return ((BizPropertyConfig) element).getChineseName();
				}
				return super.getText(element);
			}
		});
		GridDataFactory.fillDefaults().grab(true, true).applyTo(uiControl);
		
		String defaultValue = control.getDefaultValue();
		List<BizPropertyConfig> selectedByDefault = new ArrayList<BizPropertyConfig>();
		if (StringUtils.isNotEmpty(defaultValue)) {
			String[] ids = StringUtils.split(defaultValue, ',');
			for (BizPropertyConfig config : input) {
				if (ArrayUtils.contains(ids, config.getName())) {
					selectedByDefault.add(config);
				}
			}
		}
		uiControl.setSelection(selectedByDefault.toArray());
		return uiControl;
	}
	
}
