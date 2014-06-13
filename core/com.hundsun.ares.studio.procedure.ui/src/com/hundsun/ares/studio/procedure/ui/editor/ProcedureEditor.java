package com.hundsun.ares.studio.procedure.ui.editor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.ui.PartInitException;

import com.hundsun.ares.studio.biz.ui.editor.page.InterfacePage;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.jres.database.utils.ProjectSettingUtil;
import com.hundsun.ares.studio.procdure.ProcdurePackage;
import com.hundsun.ares.studio.procdure.Procedure;
import com.hundsun.ares.studio.procdure.constants.IProcedureResType;
import com.hundsun.ares.studio.procedure.compiler.mysql.constant.IProcedureSkeletonFactoryConstantMySQL;
import com.hundsun.ares.studio.procedure.compiler.oracle.constant.IProcedureSkeletonFactoryConstantOracle;
import com.hundsun.ares.studio.procedure.ui.editor.page.ProcedureInterfacePage;
import com.hundsun.ares.studio.procedure.ui.editor.page.ProcedurePreViewPage;
import com.hundsun.ares.studio.procedure.ui.editor.page.ProcedurePreviewUpdater;
import com.hundsun.ares.studio.procedure.ui.editor.page.ProcedureRelatedInfoPage;
import com.hundsun.ares.studio.procedure.ui.editor.page.ProcedureTextEditor;
import com.hundsun.ares.studio.ui.CommonElementContentProvider;
import com.hundsun.ares.studio.ui.CommonElementLabelProvider;
import com.hundsun.ares.studio.ui.editor.EMFFormEditor;
import com.hundsun.ares.studio.ui.editor.blocks.RevisionHistoryListPage;
import com.hundsun.ares.studio.ui.editor.text.TextEditorInput;
import com.hundsun.ares.studio.ui.editor.textbase.ITextBased;
import com.hundsun.ares.studio.ui.editor.textbase.TextBasedEditorInput;

public class ProcedureEditor extends EMFFormEditor {

	private InterfacePage interfacePage;
	private ProcedureTextEditor sourcePage;
	private ProcedurePreViewPage preViewPage;
	private int index;
	
	static CommonElementContentProvider cp = new CommonElementContentProvider();
	static CommonElementLabelProvider provider = new CommonElementLabelProvider(cp);
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.ui.form.EMFFormEditor#getInfoClass()
	 */
	@Override
	protected EClass getInfoClass() {
		return ProcdurePackage.Literals.PROCEDURE;
	}
	

	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#addPages()
	 */
	@Override
	protected void addPages() {
		interfacePage = new ProcedureInterfacePage(null, this, "interface", "接口");
		
		try {
			//基本信息
			addPage(interfacePage);
			
			// 伪代码页面
			final Procedure procedure = (Procedure) getInfo();
			sourcePage = new ProcedureTextEditor(getARESResource());
			TextBasedEditorInput input = new TextBasedEditorInput(new ITextBased() {
				@Override
				public void setText(String text) {
					Command command =  SetCommand.create(getEditingDomain(), procedure, ProcdurePackage.Literals.PROCEDURE__PSEUDO_CODE, text);
					getEditingDomain().getCommandStack().execute(command);
				}
				@Override
				public String getText() {
					return procedure.getPseudoCode();
				}
			}, getARESResource());
			index = addPage(sourcePage, input);
			sourcePage.setEditor(this);
			setPageText(index, "实现-伪代码");
			
			//扩展页面
			createExtendPage();
			
			//预览页面
			addPage(preViewPage=new ProcedurePreViewPage(this, "preview", "预览"), new TextEditorInput());
			//关联信息
			addPage(new ProcedureRelatedInfoPage(this, "relatedInfo", "关联信息"));
			//修改记录
			addPage(new RevisionHistoryListPage(this, "revisionHistory", "修订信息"));
			
			String databaseType = ProjectSettingUtil.getDatabaseType(getARESResource().getARESProject());
			if(databaseType.equalsIgnoreCase(ProjectSettingUtil.MYSQL)){
				addPageChangedListener(new ProcedurePreviewUpdater(IProcedureSkeletonFactoryConstantMySQL.SKELETONTYPE_CRES_PROCEDURE_MYSQL,databaseType));
			}
			else{
				addPageChangedListener(new ProcedurePreviewUpdater(IProcedureSkeletonFactoryConstantOracle.SKELETONTYPE_CRES_PROCEDURE_ORACLE,databaseType));
			}
			
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 保存创建时间
	 * 
	 */
	private void saveCreateDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		EObject info = getInfo();
		String value = "";
		if(null != info && info instanceof Procedure) {
			Procedure pro = (Procedure)info;
			String oldCreateDate = pro.getCreateDate();
			if(StringUtils.startsWith(oldCreateDate, date)) {
				int modifyNum = Integer.parseInt(oldCreateDate.substring(8));
				value = date + (modifyNum+1);
			}else {
				value = date + 1;
			}
		}
		Command command = new SetCommand(getEditingDomain(), getInfo(), ProcdurePackage.Literals.PROCEDURE__CREATE_DATE, value);
		getEditingDomain().getCommandStack().execute(command);
	}
	
	@Override
	protected void handleBeforeSave() {
		sourcePage.doSave(new NullProgressMonitor());
		setPartName(getEditorTitle());
		saveCreateDate();
	}

	@Override
	protected void handleAfterSave() {
		setPartName(getEditorTitle());
	}
	@Override
	protected String getEditorTitle() {
		String partName = provider.getText(getARESResource());
		if (isReadOnly()) {
			partName += "(只读)";
		}
		return partName;
	}
	@Override
	public boolean isDirty() {
		if (sourcePage.isDirty()) {
			return true;
		}
		return super.isDirty();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.forms.editor.FormEditor#pageChange(int)
	 */
	@Override
	protected void pageChange(int newPageIndex) {
		if(newPageIndex!=2){//保存预览编辑编辑器位置信息
			preViewPage.saveLocation();
		}
		super.pageChange(newPageIndex);
		if (newPageIndex==2){//恢复预览编辑编辑器位置
			preViewPage.restoreLocation();
		}
	}

}
