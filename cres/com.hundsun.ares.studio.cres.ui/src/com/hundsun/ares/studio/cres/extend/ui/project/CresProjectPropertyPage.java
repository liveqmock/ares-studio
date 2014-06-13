package com.hundsun.ares.studio.cres.extend.ui.project;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

import com.hundsun.ares.studio.cres.extend.core.constants.ICresExtendConstants;
import com.hundsun.ares.studio.cres.extend.cresextend.CresProjectExtendProperty;
import com.hundsun.ares.studio.cres.extend.cresextend.CresextendFactory;
import com.hundsun.ares.studio.cres.extend.cresextend.CresextendPackage;
import com.hundsun.ares.studio.cres.extend.cresextend.FileDefine;
import com.hundsun.ares.studio.cres.extend.cresextend.GccDefine;
import com.hundsun.ares.studio.cres.extend.cresextend.MvcDefine;
import com.hundsun.ares.studio.cres.extend.cresextend.ProcDefine;
import com.hundsun.ares.studio.internal.core.ARESProjectProperty;
import com.hundsun.ares.studio.ui.editor.ProjectPropertyEditor;
import com.hundsun.ares.studio.ui.editor.blocks.EMFExtendSectionScrolledFormPage;
import com.hundsun.ares.studio.ui.editor.blocks.FormWidgetUtils;
import com.hundsun.ares.studio.ui.util.FormLayoutFactory;

public class CresProjectPropertyPage extends EMFExtendSectionScrolledFormPage<ARESProjectProperty> {

	private Text txtVersion;
	private Text txtCName;
	private Text txtShortCName;
	private Text txtID;
	private Text txtManager;
	private Text txtDeveloper;
	private Text txtUser;
	private Text txtRelation;
	private Text txtName;
	private Text txtWriter;
	private Text txtNote;
	private Text txtHeadFile;

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public CresProjectPropertyPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
		System.out.println(info);
		///
		ARESProjectProperty info1 = getInfo();
		System.out.println(info1);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.EMFExtendSectionScrolledFormPage#getEClass()
	 */
	@Override
	protected EClass getEClass() {
		return CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.editor.blocks.EMFExtendSectionScrolledFormPage#getMapKey()
	 */
	@Override
	protected String getMapKey() {
		return ICresExtendConstants.CRES_EXTEND_PROJECT_PROPERTY;
	}

	protected SashForm sf;
	protected Section procSection;
	protected Section gccSection;
	protected Section mvcSection;
	protected Section func;
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendSectionScrolledFormPage#createSections(org.eclipse.ui.forms.IManagedForm)
	 */
	@Override
	protected void createSections(IManagedForm managedForm) {

		Composite composite = managedForm.getForm().getBody();
		FormToolkit toolkit = managedForm.getToolkit();
		
		managedForm.getForm().setText(getTitle());
		toolkit.decorateFormHeading(managedForm.getForm().getForm());
		
		createBasicInfoSection(composite,toolkit,"基本信息");
		
		sf = new SashForm(composite, SWT.VERTICAL);
		TableWrapData twd = new TableWrapData(TableWrapData.FILL_GRAB);
		twd.heightHint = 650;
		sf.setLayoutData(twd);
		sf.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 1));
		
		createSection(toolkit);
		composite.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 1));
		
		composite.getParent().layout();
	}

	protected void createSection(FormToolkit toolkit) {
		procSection = createFileDefineSection(sf, toolkit,"Proc预处理设置",
				CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__PROC_DEFINE,
				CresextendPackage.Literals.PROC_DEFINE);
		gccSection = createFileDefineSection(sf, toolkit,"GCC文件自定义设置",
				CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__GCC_DEFINE,
				CresextendPackage.Literals.GCC_DEFINE);
		mvcSection = createFileDefineSection(sf,toolkit,"MVC文件自定义设置",
				CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__MVC_DEFINE,
				CresextendPackage.Literals.MVC_DEFINE);
		func = createFuncDefineSection(sf,toolkit,"公共函数头文件设置",
				CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE,
				CresextendPackage.Literals.FILE_DEFINE);
		
		sf.setWeights(new int[]{1,1,1,1});
		procSection.addExpansionListener(new ExpansionAdapter() {
			
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				refreshSFWeights();
			}
		});
		
		gccSection.addExpansionListener(new ExpansionAdapter() {
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				refreshSFWeights();
			}
		});
		
		mvcSection.addExpansionListener(new ExpansionAdapter() {
			
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				refreshSFWeights();
			}
		});
		
		func.addExpansionListener(new ExpansionAdapter() {
			
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				refreshSFWeights();
			}
		});
	}

	private void refreshSFWeights(){
		int unit = 31;
		int shrinkNum = 0;
		if(procSection != null && !procSection.isExpanded()){
			shrinkNum ++;
		}
		if(!gccSection.isExpanded()){
			shrinkNum ++;
		}
		if(!mvcSection.isExpanded()){
			shrinkNum ++;
		}
		if(!func.isExpanded()){
			shrinkNum ++;
		}
		if(shrinkNum == 4){
			sf.setWeights(new int[]{1,1,1,unit-3});
			return;
		}
		int inputWeight = procSection.isExpanded() ? (unit-shrinkNum)/(4-shrinkNum) : 1;
		int outputWeight = gccSection.isExpanded() ? (unit-shrinkNum)/(4-shrinkNum) : 1;
		int internalWeight = mvcSection.isExpanded() ? (unit-shrinkNum)/(4-shrinkNum) : 1;
		int funcWeight = func.isExpanded() ? (unit-shrinkNum)/(4-shrinkNum) : 1;
		sf.setWeights(new int[]{inputWeight,outputWeight,internalWeight,funcWeight});
	}
	/**
	 * @param sf
	 * @param toolkit
	 * @param title
	 * @param reference
	 * @param procDefine
	 * @return
	 */
	protected Section createFileDefineSection(SashForm sf, FormToolkit toolkit,
			String title, EReference reference,
			EClass eclass) {
		Section section = toolkit.createSection(sf, FormWidgetUtils.getDefaultSectionStyles());
		section.setText(title);
		
		Composite content = toolkit.createComposite(section);
		GridLayoutFactory.fillDefaults().applyTo(content);
		
		Composite comp = toolkit.createComposite(content, SWT.NONE);
		GridDataFactory.fillDefaults().indent(0, 0).grab(true, true).applyTo(comp);
		comp.setLayout(new FillLayout());
		
		CresProjectFileDefineBlock block = new CresProjectFileDefineBlock(
				reference, 
				getEditingDomain(), resource, 
				eclass,null);
		
		block.createControl(comp, toolkit);
		block.setInput(getInfo());
		
		addPropertyListener(block);
		getEditingDomain().getCommandStack().addCommandStackListener(block);
		
		section.setClient(content);
		toolkit.paintBordersFor(content);
		
		return section;
	}
	
	/**
	 * @param sf
	 * @param toolkit
	 * @param title
	 * @param reference
	 * @param procDefine
	 * @return
	 */
	protected Section createFuncDefineSection(SashForm sf, FormToolkit toolkit,
			String title, EReference reference,
			EClass eclass) {
		Section section = toolkit.createSection(sf, FormWidgetUtils.getDefaultSectionStyles());
		section.setText(title);
		
		Composite content = toolkit.createComposite(section);
		GridLayoutFactory.fillDefaults().applyTo(content);
		
		Composite comp = toolkit.createComposite(content, SWT.NONE);
		GridDataFactory.fillDefaults().indent(0, 0).grab(true, true).applyTo(comp);
		comp.setLayout(new FillLayout());
		
		PublicFunctionDefineBlock block = new PublicFunctionDefineBlock(
				reference, 
				getEditingDomain(), 
				((ProjectPropertyEditor)getEditor()).getARESProject(), 
				eclass,null);
		
		block.createControl(comp, toolkit);
		block.setInput(getInfo());
		
		addPropertyListener(block);
		getEditingDomain().getCommandStack().addCommandStackListener(block);
		
		section.setClient(content);
		toolkit.paintBordersFor(content);
		
		return section;
	}

	/**
	 * @param composite
	 * @param toolkit
	 * @param title
	 */
	private void createBasicInfoSection(Composite composite,
			FormToolkit toolkit, String title) {
		
		Section section = toolkit.createSection(composite, FormWidgetUtils.getDefaultSectionStyles());
		section.setText(title);

		// 创建控件
		Composite baseInfo = toolkit.createComposite(section);

		Label lbVersion = toolkit.createLabel(baseInfo, "产品版本：", SWT.NONE);
		txtVersion = toolkit.createText(baseInfo, "", SWT.BORDER);
		
		Label lbCName = toolkit.createLabel(baseInfo, "产品名称：", SWT.NONE);
		txtCName = toolkit.createText(baseInfo, "", SWT.BORDER);
		
		Label lbShortCName = toolkit.createLabel(baseInfo, "产品简称：", SWT.NONE);
		txtShortCName = toolkit.createText(baseInfo, "", SWT.BORDER);
		
		Label lbID = toolkit.createLabel(baseInfo, "项目编号：", SWT.NONE);
		txtID = toolkit.createText(baseInfo, "", SWT.BORDER);
		
		Label lbManager = toolkit.createLabel(baseInfo, "任务提出者：", SWT.NONE);
		txtManager = toolkit.createText(baseInfo, "", SWT.BORDER);
		
		Label lbDeveloper = toolkit.createLabel(baseInfo, "开发者：", SWT.NONE);
		txtDeveloper = toolkit.createText(baseInfo, "", SWT.BORDER);
		
		Label lbUser = toolkit.createLabel(baseInfo, "用户：", SWT.NONE);
		txtUser = toolkit.createText(baseInfo, "", SWT.BORDER);
		
		Label lbRelation = toolkit.createLabel(baseInfo, "同其他系统关系：", SWT.NONE);
		txtRelation = toolkit.createText(baseInfo, "", SWT.BORDER);

		Label lbName = toolkit.createLabel(baseInfo, "英文缩写：", SWT.NONE);
		txtName = toolkit.createText(baseInfo, "", SWT.BORDER);

		Label lbWriter = toolkit.createLabel(baseInfo, "编写人：", SWT.NONE);
		txtWriter = toolkit.createText(baseInfo, "", SWT.BORDER);

		Label lbNote = toolkit.createLabel(baseInfo, "编写说明：", SWT.NONE);
		txtNote = toolkit.createText(baseInfo, "", SWT.BORDER);

		Label lbHeadFile = toolkit.createLabel(baseInfo, "文件头注释：", SWT.NONE);
		txtHeadFile = toolkit.createText(baseInfo, "", SWT.BORDER | SWT.V_SCROLL | SWT.WRAP);
		
		//布局
		section.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		baseInfo.setLayout(new GridLayout(2, false));
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbVersion);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtVersion);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbCName);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtCName);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbShortCName);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtShortCName);

		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbID);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtID);

		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbManager);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtManager);

		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbDeveloper);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtDeveloper);

		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbUser);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtUser);

		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbRelation);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtRelation);

		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbName);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtName);

		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbWriter);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtWriter);

		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbNote);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(1, 1).applyTo(txtNote);
		
		GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(false, false).span(1, 1).applyTo(lbHeadFile);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).hint(20, 80).grab(true, false).span(1, 1).applyTo(txtHeadFile);
		
		section.setClient(baseInfo);
		toolkit.paintBordersFor(baseInfo);
		
		databinding();
	}

	/**
	 * 
	 */
	private void databinding() {
		//产品版本
		getBindingContext().bindValue(SWTObservables.observeText(txtVersion, SWT.Modify), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__VERSION));
		//产品名称
		getBindingContext().bindValue(SWTObservables.observeText(txtCName, SWT.Modify), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__CNAME));
		//产品简称
		getBindingContext().bindValue(SWTObservables.observeText(txtShortCName, SWT.Modify), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__SHORT_CNAME));
		//项目编号
		getBindingContext().bindValue(SWTObservables.observeText(txtID, SWT.Modify), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__ID));
		//任务提出者
		getBindingContext().bindValue(SWTObservables.observeText(txtManager, SWT.Modify), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__MANAGER));
		//开发者
		getBindingContext().bindValue(SWTObservables.observeText(txtDeveloper, SWT.Modify), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__DEVELOPER));
		//用户
		getBindingContext().bindValue(SWTObservables.observeText(txtUser, SWT.Modify), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__USER));
		//同其他系统关系
		getBindingContext().bindValue(SWTObservables.observeText(txtRelation, SWT.Modify), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__RELATION));
		//英文简称
		getBindingContext().bindValue(SWTObservables.observeText(txtName, SWT.Modify), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__NAME));
		//编写人
		getBindingContext().bindValue(SWTObservables.observeText(txtWriter, SWT.Modify), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__WRITER));
		//编写说明
		getBindingContext().bindValue(SWTObservables.observeText(txtNote, SWT.Modify), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__NOTE));
		//文件头注释
		getBindingContext().bindValue(SWTObservables.observeText(txtHeadFile, SWT.Modify), 
				EMFEditObservables.observeValue(getEditingDomain(), getModel(), 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__HEAD_FILE));
		
		////
		initInfo();
	}
	
	/**
	 * 创建完基本信息后，初始化模型
	 * @param owner
	 */
	protected void initInfo() {
		if(info instanceof ARESProjectProperty) {
			ARESProjectProperty pp = (ARESProjectProperty)info;
			CresProjectExtendProperty owner = (CresProjectExtendProperty)pp.getMap().get(ICresExtendConstants.CRES_EXTEND_PROJECT_PROPERTY);
			if(null == owner) {
				return;
			}
			//初始化
			//gcc
			EList<GccDefine> gccDefine = owner.getGccDefine();
			if(gccDefine.isEmpty()) {
				List<GccDefine> tmpGcc = new ArrayList<GccDefine>();
				//PROC_INCLUDE
				GccDefine gcc1 = CresextendFactory.eINSTANCE.createGccDefine();
				gcc1.setIsUsed(true);
				gcc1.setParameter("PROC_INCLUDE");
				gcc1.setValue("$(ORACLE_HOME)/precomp/public," +
						"$(ORACLE_HOME)/oci/include,"+
						"$(FBASE_HOME),"+
						"include=/usr/lib/gcc-lib/i386-redhat-linux/$(GCC_VER)/include");
				gcc1.setNote("Proc预处理器包含的目录，用逗号隔开");
				tmpGcc.add(gcc1);
				//CC_INCLUDE
				GccDefine gcc2 = CresextendFactory.eINSTANCE.createGccDefine();
				gcc2.setIsUsed(true);
				gcc2.setParameter("CC_INCLUDE");
				gcc2.setValue("$(FBASE_HOME),$(ORACLE_HOME)/precomp/public,$(ORACLE_HOME)/oci/include");
				gcc2.setNote("C/C++编译器的包含目录，逗号隔开");
				tmpGcc.add(gcc2);
				//"LIBS"
				GccDefine gcc3 = CresextendFactory.eINSTANCE.createGccDefine();
				gcc3.setIsUsed(true);
				gcc3.setParameter("LIBS");
				gcc3.setValue("");
				gcc3.setNote("需要连接的第三方库文件,逗号隔开");
				tmpGcc.add(gcc3);
				
				//"FC"
				GccDefine gcc4 = CresextendFactory.eINSTANCE.createGccDefine();
				gcc4.setIsUsed(true);
				gcc4.setParameter("FC");
				gcc4.setValue("");
				gcc4.setNote("要编译的组件名,构成组件的obj文件定义件,逗号隔开");
				tmpGcc.add(gcc4);
				
				//使用Command添加
				Command command = AddCommand.create(getEditingDomain(), owner, 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__GCC_DEFINE, tmpGcc);
				getEditingDomain().getCommandStack().execute(command);
			}
			//proc
			EList<ProcDefine> procDefine = owner.getProcDefine();
			if(procDefine.isEmpty()) {
				List<ProcDefine> tmpProc = new ArrayList<ProcDefine>();
				//ireclen
				ProcDefine proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("ireclen");
				proc.setValue("132");
				proc.setNote("");
				tmpProc.add(proc);
				//oreclen
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("oreclen");
				proc.setValue("132");
				proc.setNote("");
				tmpProc.add(proc);
				//auto_connect
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("auto_connect");
				proc.setValue("no");
				proc.setNote("允许自动连接到 ops$ 帐户");
				tmpProc.add(proc);
				//char_map
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("char_map");
				proc.setValue("string");
				proc.setNote("正在映射字符数组和字符串");
				tmpProc.add(proc);
				//close_on_commit
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("close_on_commit");
				proc.setValue("yes");
				proc.setNote("关闭所有 COMMIT 游标");
				tmpProc.add(proc);
				//cmax
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("cmax");
				proc.setValue("100");
				proc.setNote("用于连接池的 CMAX 值 0 - 65535");
				tmpProc.add(proc);
				//cmin
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("cmin");
				proc.setValue("2");
				proc.setNote("用于连接池的 CMIN 值 1 - 65535");
				tmpProc.add(proc);
				//cincr
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("cincr");
				proc.setValue("1");
				proc.setNote("用于连接池的 CINCR 值 1 - 65535");
				tmpProc.add(proc);
				//ctimeout
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("ctimeout");
				proc.setValue("0");
				proc.setNote("用于连接池的 CTIMEOUT 值 1 - 65535");
				tmpProc.add(proc);
				//cnowait
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("cnowait");
				proc.setValue("0");
				proc.setNote("用于连接池的 CNOWAIT 值 1 - 65535");
				tmpProc.add(proc);
				//code
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("code");
				proc.setValue("cpp");
				proc.setNote("所要生成的代码类型");
				tmpProc.add(proc);
				//comp_charset
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("comp_charset");
				proc.setValue("multi_byte");
				proc.setNote("C 编译器支持的字符集类型");
				tmpProc.add(proc);
				//config
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("config");
				proc.setValue("default");
				proc.setNote("使用另一配置文件覆盖系统配置文件");
				tmpProc.add(proc);
				//cpool
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("cpool");
				proc.setValue("no");
				proc.setNote("支持连接共享");
				tmpProc.add(proc);
				//cpp_suffix
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("cpp_suffix");
				proc.setValue("cpp");
				proc.setNote("覆盖默认的 C++ 文件名后缀");
				tmpProc.add(proc);
				//dbms
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("dbms");
				proc.setValue("native");
				proc.setNote("v6/v7/v8 兼容模式");
				tmpProc.add(proc);
				//def_sqlcode
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("def_sqlcode");
				proc.setValue("yes");
				proc.setNote("生成 '#define SQLCODE sqlca.sqlcode' 宏");
				tmpProc.add(proc);
				//define
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("define");
				proc.setValue("USE_PRO_C");
				proc.setNote("定义预处理程序符号");
				tmpProc.add(proc);
				//duration
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("duration");
				proc.setValue("transaction");
				proc.setNote("transaction");
				tmpProc.add(proc);
				//dynamic
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("dynamic");
				proc.setValue("oracle");
				proc.setNote("指定 Oracle 或 ANSI 动态 SQL 语义");
				tmpProc.add(proc);
				//errors
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("errors");
				proc.setValue("yes");
				proc.setNote("错误消息是否发送到终端");
				tmpProc.add(proc);
				//events
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("events");
				proc.setValue("no");
				proc.setNote("支持发布-订阅事件通知");
				tmpProc.add(proc);
				//fips
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("fips");
				proc.setValue("none");
				proc.setNote("ANSI 不兼容用法的 FIPS 标志");
				tmpProc.add(proc);
				//hold_cursor
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("hold_cursor");
				proc.setValue("yes");
				proc.setNote("控制游标高速缓存中的游标存留数");
				tmpProc.add(proc);
				//lines
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("lines");
				proc.setValue("no");
				proc.setNote("向生成的代码添加若干行指令");
				tmpProc.add(proc);
				//ltype
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("ltype");
				proc.setValue("short");
				proc.setNote("在列表文件生成的数据量");
				tmpProc.add(proc);
				//maxliteral
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("maxliteral");
				proc.setValue("1024");
				proc.setNote("生成的字符串字面量的最大长度 10 - 1024");
				tmpProc.add(proc);
				//maxopencursors
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("maxopencursors");
				proc.setValue("60");
				proc.setNote("高速缓存的打开游标的最大数量");
				tmpProc.add(proc);
				//mode
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("mode");
				proc.setValue("ansi");
				proc.setNote("代码对 Oracle 或 ANSI 规则的顺应性");
				tmpProc.add(proc);
				//native_types
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("native_types");
				proc.setValue("no");
				proc.setNote("本机浮点/双精度支持");
				tmpProc.add(proc);
				//nls_local
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("nls_local");
				proc.setValue("no");
				proc.setNote("控制如何完成 NLS 字符语义");
				tmpProc.add(proc);
				//objects
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("objects");
				proc.setValue("yes");
				proc.setNote("支持对象类型");
				tmpProc.add(proc);
				//oraca
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("oraca");
				proc.setValue("no");
				proc.setNote("控制 ORACA 的使用");
				tmpProc.add(proc);
				//pagelen
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("pagelen");
				proc.setValue("80");
				proc.setNote("列表文件的页长度 30 - 256");
				tmpProc.add(proc);
				//parse
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("parse");
				proc.setValue("partial");
				proc.setNote("控制对哪一 非 SQL 代码进行语法分析");
				tmpProc.add(proc);
				//prefetch
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("prefetch");
				proc.setValue("200");
				proc.setNote("在游标 OPEN 时预先提取的行数 0 - 65535");
				tmpProc.add(proc);
				//release_cursor
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("release_cursor");
				proc.setValue("no");
				proc.setNote("控制从游标高速缓存释放的游标数");
				tmpProc.add(proc);
				//select_error
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("select_error");
				proc.setValue("yes");
				proc.setNote("控制选择错误的标志");
				tmpProc.add(proc);
				//sqlcheck
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("sqlcheck");
				proc.setValue("syntax");
				proc.setNote("编译时 SQL 的检查量");
				tmpProc.add(proc);
				//threads
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("threads");
				proc.setValue("yes");
				proc.setNote("表示多线程的应用程序");
				tmpProc.add(proc);
				//type_code
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("type_code");
				proc.setValue("oracle");
				proc.setNote("使用 Oracle 或动态 SQL 的 ANSI 类型代码");
				tmpProc.add(proc);
				//unsafe_null
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("unsafe_null");
				proc.setValue("no");
				proc.setNote("允许不使用指示符表列的 NULL 提取");
				tmpProc.add(proc);
				//userid
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(true);
				proc.setParameter("userid");
				proc.setValue("hs_his/handsome@gfdb");
				proc.setNote("用户名/口令 [@dbname] 连接字符串");
				tmpProc.add(proc);
				//utf16_charset
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("utf16_charset");
				proc.setValue("nchar_charset");
				proc.setNote("由 UTF16 变量使用的字符集表单");
				tmpProc.add(proc);
				//varchar
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("varchar");
				proc.setValue("no");
				proc.setNote("允许使用隐式 varchar 结构");
				tmpProc.add(proc);
				//version
				proc = CresextendFactory.eINSTANCE.createProcDefine();
				proc.setIsUsed(false);
				proc.setParameter("version");
				proc.setValue("recent");
				proc.setNote("要返回哪一版本的对象");
				tmpProc.add(proc);
				
				//使用Command添加
				Command command = AddCommand.create(getEditingDomain(), owner, 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__PROC_DEFINE, tmpProc);
				getEditingDomain().getCommandStack().execute(command);
			}
			
			//mvc
			EList<MvcDefine> mvcDefine = owner.getMvcDefine();
			if(mvcDefine.isEmpty()) {
				List<MvcDefine> tmpMvc = new ArrayList<MvcDefine>();
				//"FBASE_HOME"
				MvcDefine mvc1 = CresextendFactory.eINSTANCE.createMvcDefine();
				mvc1.setIsUsed(true);
				mvc1.setParameter("FBASE_HOME");
				mvc1.setValue("../..");
				mvc1.setNote("不设置的默认值为../..");
				tmpMvc.add(mvc1);
				//"OUTDIR"
				MvcDefine mvc2 = CresextendFactory.eINSTANCE.createMvcDefine();
				mvc2.setIsUsed(true);
				mvc2.setParameter("OUTDIR");
				mvc2.setValue("$(FBASE_HOME)\\Bin");
				mvc2.setNote("不设置的默认值为$(FBASE_HOME)\\Bin");
				tmpMvc.add(mvc2);
				//"PROC_INCLUDE"
				mvc2 = CresextendFactory.eINSTANCE.createMvcDefine();
				mvc2.setIsUsed(true);
				mvc2.setParameter("PROC_INCLUDE");
				mvc2.setValue("$(ORACLE_HOME)/precomp/public," +
						"$(ORACLE_HOME)/oci/include,"+
						"$(FBASE_HOME),"+
						"$(VC_HOME)");
				mvc2.setNote("Proc预处理器包含的目录，用逗号隔开");
				tmpMvc.add(mvc2);
				//"CC_INCLUDE"
				mvc2 = CresextendFactory.eINSTANCE.createMvcDefine();
				mvc2.setIsUsed(true);
				mvc2.setParameter("CC_INCLUDE");
				mvc2.setValue("$(FBASE_HOME),$(ORACLE_HOME)/precomp/public,$(ORACLE_HOME)/oci/include");
				mvc2.setNote("C/C++编译器的包含目录，逗号隔开");
				tmpMvc.add(mvc2);
				//"LIBS"
				mvc2 = CresextendFactory.eINSTANCE.createMvcDefine();
				mvc2.setIsUsed(true);
				mvc2.setParameter("LIBS");
				mvc2.setValue("");
				mvc2.setNote("需要连接的第三方库文件,逗号隔开");
				tmpMvc.add(mvc2);
				
				//使用Command添加
				Command command = AddCommand.create(getEditingDomain(), owner, 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__MVC_DEFINE, tmpMvc);
				getEditingDomain().getCommandStack().execute(command);
			}
			
			EList<FileDefine> funcDefine = owner.getFuncDefine();
			if(funcDefine.isEmpty()){
				FileDefine define = CresextendFactory.eINSTANCE.createFileDefine();
				define.setIsUsed(true);
				define.setValue("src\\s_libpublic.h");
				define.setNote("系统公共函数");
				//使用Command添加
				Command command = AddCommand.create(getEditingDomain(), owner, 
						CresextendPackage.Literals.CRES_PROJECT_EXTEND_PROPERTY__FUNC_DEFINE, define);
				getEditingDomain().getCommandStack().execute(command);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.ui.page.ExtendPageWithMyDirtySystem#shouldLoad()
	 */
	@Override
	public boolean shouldLoad() {
		
		IProject project = getARESProject().getProject();
		try {
			if(project.hasNature(ICresExtendConstants.CRES_PROJECT_NATURE)){
				return true;
			}
		} catch (CoreException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
