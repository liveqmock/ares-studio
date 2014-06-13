package com.hundsun.ares.studio.biz.ui.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.StructuredSelection;

import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.resources.MetadataResourcesUtils;
import com.hundsun.ares.studio.jres.model.metadata.MetadataFactory;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.ui.util.DialogHelper;

/**
 * 将输入输出参数添加到标准字段，
 * 使用EMF的Command
 * @author xuzhen
 *
 */
public class AddToStdFieldAction extends Action {
	private static final Logger log = Logger.getLogger(AddToStdFieldAction.class);
	
	private IARESProject project = null;
	private ColumnViewer viewer = null;
	
	final static String ACTION_NAME = "添加到标准字段";
	
	private EditingDomain editingDomain;
	
	public AddToStdFieldAction( IARESProject project, ColumnViewer viewer,EditingDomain editingDomain) {
		super(ACTION_NAME);
		this.project = project;
		this.viewer = viewer;
		this.editingDomain = editingDomain;
		setId(IBizActionIDConstants.ADD_TO_STD_FIELD);
	}

	@Override
	public void run() {
		try {
			if (viewer.getSelection().isEmpty()) {
				throw new Exception("没有选中元素");
			}	
			final List<Parameter> list = ((StructuredSelection) viewer.getSelection()).toList();
			checkParams(list);
			
			RecordingCommand addCommand = new RecordingCommand((TransactionalEditingDomain) editingDomain, ACTION_NAME) {
				@Override
				protected void doExecute() {
					
					List<StandardField> stdFields = new ArrayList<StandardField>();
					for (Parameter currentParam : list) {
						try {
							StandardField field = MetadataFactory.eINSTANCE.createStandardField();
							field.setName(currentParam.getId());
							field.setChineseName(currentParam.getName());
							field.setDataType(currentParam.getType());
							stdFields.add(field);
							currentParam.setParamType(ParamType.STD_FIELD);
						} catch (Exception e) {
							DialogHelper.showMessage("错误", e.getMessage());
							throw new OperationCanceledException(e.getMessage());
						}
					}
					try {
						MetadataResourcesUtils.addStandardField(project, stdFields.toArray(new StandardField[0]));
					} catch (ARESModelException e) {
						e.printStackTrace();
						log.error(e);
						DialogHelper.showMessage("错误", e.getMessage());
					}
				}
			};
			
			editingDomain.getCommandStack().execute(addCommand);
			viewer.refresh();
		} catch (Exception e) {
			DialogHelper.showMessage("错误", e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param params
	 * @return 检查结果，正确返回true，否则直接抛出异常
	 */
	private boolean checkParams(List<Parameter> params) throws Exception {
		for (Parameter param : params) {
			// FIXME 暂时不支持系统参数
//			if (param instanceof SystemInputParameterDefine) {
//				throw new Exception("系统参数不能进行操作");
//			}
			ReferenceInfo refInfo = ReferenceManager.getInstance().getFirstReferenceInfo(project, IMetadataRefType.StdField, param.getId(), true);
			if (refInfo != null) {
				throw new Exception("标准字段\"" + param.getId() + "\"已存在");
			}
			if (param.getType().equals("")) {
				throw new Exception("选中参数\"" + param.getId() + "\"的类型不能为空");
			}
			if (param.getRealType().equals("")) {
				throw new Exception("选中参数\"" + param.getId() + "\"的Java类型不能为空");
			}
		}
		return true;
	}
	
}
