package com.hundsun.ares.studio.ui.editor.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.DefaultCharacterPairMatcher;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;
import org.eclipse.jface.text.source.projection.ProjectionSupport;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;
import org.eclipse.ui.texteditor.ChainedPreferenceStore;
import org.eclipse.ui.texteditor.ContentAssistAction;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.eclipse.ui.texteditor.SourceViewerDecorationSupport;

import com.hundsun.ares.studio.ui.editor.ARESEditorPlugin;
import com.hundsun.ares.studio.ui.editor.textbase.ITextBased;
import com.hundsun.ares.studio.ui.editor.textbase.TextBasedEditorInput;
/**
 * 普通的源代码编辑器。
 * <p>CreatedDate: 2008-1-11</p>
 * @author sundl
 */
public abstract class GeneralSourceEditor extends AbstractDecoratedTextEditor{
	
	
	
	private class BracketInserter implements VerifyKeyListener{

		/* (non-Javadoc)
		 * @see org.eclipse.swt.custom.VerifyKeyListener#verifyKey(org.eclipse.swt.events.VerifyEvent)
		 */
		public void verifyKey(VerifyEvent event) {
			
			final ISourceViewer sourceViewer= getSourceViewer();
			IDocument document= sourceViewer.getDocument();

			if(event.character == '(') {
				final Point selection= sourceViewer.getSelectedRange();
				sourceViewer.setSelectedRange(0, 1);
				event.doit = false;
			}
		}	
		
	}
	protected ProjectionSupport support;
	protected ProjectionAnnotationModel model;
	private IDocumentProvider provider;
//	private TestAnnotationModel t_Model;
	private Annotation[] oldAnnotations;
	private BracketInserter fBracketInserter = new BracketInserter();
	//IDocumentProvider provider;
	private AddAnnotationAction1 addAnnotationAction;
	private ARESPairMatcher matcher = new ARESPairMatcher(new char[] {'(', ')'});
	DefaultCharacterPairMatcher defaultPairMatcher = new DefaultCharacterPairMatcher(new char[] {'(', ')', '{', '}', '[', ']'});
	
	private static final String MATCH_PREF_KEY = "hs_matchbra";
	private static final String MATCH_COLOR_KEY = "hs_match_color";
	
	static {
		IPreferenceStore store = ARESEditorPlugin.getDefault().getPreferenceStore();
//		IPreferenceStore.
		store.setDefault(MATCH_PREF_KEY, true);
		store.setValue(MATCH_PREF_KEY, true);
		store.setDefault(MATCH_COLOR_KEY, StringConverter.asString(new RGB(192, 192, 192)));
		store.setValue(MATCH_COLOR_KEY, StringConverter.asString(new RGB(192, 192, 192)));
	}

	
	public GeneralSourceEditor() {
		super();
		provider = customGetDocumentProvider();
		setDocumentProvider(provider);
		SourceViewerConfiguration configuration = getConfiguration();
		setSourceViewerConfiguration(configuration);
		setPreferenceStore(createPreferenceStore());
	}
	
	/**
	 * 子类应该实现这个方法，创建一个文档提供程序，并返回。
	 * <p>CreatedDate: 2008-1-25</p>
	 * @return 子类的文档提供程序。
	 * @author sundl
	 */
	public abstract IDocumentProvider customGetDocumentProvider();
		
	/**
	 * 子类重写这个方法，提供自己的源查看器配置。
	 * <p>CreatedDate: 2008-1-25</p>
	 * @return SourceViewerConfiguration
	 * @author sundl
	 */
	public abstract SourceViewerConfiguration getConfiguration();
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		ProjectionViewer viewer = (ProjectionViewer)getSourceViewer();
		support = new ProjectionSupport(viewer, getAnnotationAccess(), getSharedColors());
		support.install();
		
		viewer.doOperation(ProjectionViewer.TOGGLE);	
		
//		if (viewer instanceof ITextViewerExtension)
//			((ITextViewerExtension) viewer).prependVerifyKeyListener(fBracketInserter);

		model = viewer.getProjectionAnnotationModel();
	}

//	/*
//	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#initializeKeyBindingScopes()
//	 */
//	@Override
//	protected void initializeKeyBindingScopes() {
//		setKeyBindingScopes(new String[] { "com.hundsun.cdp.sourceeditor" });  //$NON-NLS-1$
//	}

	private IPreferenceStore createPreferenceStore() {
		IPreferenceStore[] stores = new IPreferenceStore[2];
		stores[0] = EditorsUI.getPreferenceStore();
		stores[1] = ARESEditorPlugin.getDefault().getPreferenceStore();
		return new ChainedPreferenceStore(stores);
	}
	
	/* (non-Javadoc)重写这个方法，改变默认的标尺右键菜单ID，去掉无用菜单项。
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#initializeEditor()
	 */
	@Override
	protected void initializeEditor() {
		setRulerContextMenuId("#HSSourceEditorRulerContext"); //$NON-NLS-1$
		super.initializeEditor();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#createSourceViewer(org.eclipse.swt.widgets.Composite, org.eclipse.jface.text.source.IVerticalRuler, int)
	 */
	@Override
	protected ISourceViewer createSourceViewer(Composite parent, IVerticalRuler ruler, int styles) {
		ISourceViewer viewer = new ARESSourceViewer(parent, ruler,
				getOverviewRuler(), isOverviewRulerVisible(), styles);
		getSourceViewerDecorationSupport(viewer);
		return viewer;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#doSetInput(org.eclipse.ui.IEditorInput)
	 */
	@Override
	protected void doSetInput(IEditorInput input) throws CoreException {
		super.doSetInput(input);
		if(input instanceof TextBasedEditorInput) {
			ITextBased element = ((TextBasedEditorInput)input).getTextbased();
			//setPartName(element.getElementName());
		}
	}	

	/*
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	public boolean isSaveAsAllowed() {
		return false;
	}

	public void updateFoldingStructure(ArrayList positions)	{
		Annotation[] annotations = new Annotation[positions.size()];
		
		//this will hold the new annotations along
		//with their corresponding positions
		HashMap<ProjectionAnnotation, Object> newAnnotations = new HashMap<ProjectionAnnotation, Object>();
		
		for(int i =0;i<positions.size();i++) {
			ProjectionAnnotation annotation = new ProjectionAnnotation();			
			newAnnotations.put(annotation, positions.get(i));			
			annotations[i]=annotation;
//			Position p = (Position)positions.get(i);
//			System.out.println("position :" + p.getOffset() + "" + p.length);
//			try {
//				System.out.println("position Text: " + getSourceViewer().getDocument().get(p.getOffset(), p.getLength()));
//			} catch (BadLocationException e) {
//				e.printStackTrace();
//			}
		}
		
		model.modifyAnnotations(oldAnnotations,newAnnotations,null);
		oldAnnotations=annotations;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractTextEditor#rulerContextMenuAboutToShow(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void rulerContextMenuAboutToShow(IMenuManager menu) {
		menu.add(new GroupMarker("annotation"));	//$NON-NLS-1$
		super.rulerContextMenuAboutToShow(menu);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractDecoratedTextEditor#createActions()
	 */
	@Override
	protected void createActions() {
		super.createActions();
		
		String BUNDLE_FOR_CONSTRUCTED_KEYS= "org.eclipse.ui.texteditor.ConstructedEditorMessages";//$NON-NLS-1$
		ResourceBundle bundleForConstructedKeys= ResourceBundle.getBundle(BUNDLE_FOR_CONSTRUCTED_KEYS);
		
//		IAction action1= new TextOperationAction(bundleForConstructedKeys, "Comment.", this, ITextOperationTarget.PREFIX); //$NON-NLS-1$
//		action1.setActionDefinitionId("com.hundsun.cdp.ui.comment");
//		setAction("Comment", action1); //$NON-NLS-1$
//		markAsStateDependentAction("Comment", true); //$NON-NLS-1$
//		//PlatformUI.getWorkbench().getHelpSystem().setHelp(action, IJavaHelpContextIds.COMMENT_ACTION);
//
//		action1= new TextOperationAction(bundleForConstructedKeys, "Uncomment.", this, ITextOperationTarget.STRIP_PREFIX); //$NON-NLS-1$
//		action1.setActionDefinitionId("com.hundsun.cdp.ui.uncomment");
//		setAction("Uncomment", action1); //$NON-NLS-1$
//		markAsStateDependentAction("Uncomment", true); //$NON-NLS-1$

		ToggleCommentAction commentAction = new ToggleCommentAction(bundleForConstructedKeys,"ToggleComment",this);
		commentAction.setActionDefinitionId("com.hundsun.ares.studio.ui.editor.command.comment");
		setAction("ToggleComment", commentAction);
		markAsStateDependentAction("ToggleComment", true);
		configureToggleCommentAction();
		
		//bundle是国际化的资源文件。
		//ResourceBundle bundle = ResourceBundle.getBundle("com.hundsun.hdt.ui.Test");//$NON-NLS-1$
		//下面第二个参数，是资源文件中的键的前缀，资源文件中必须以此前缀的几个规定的键值。
		ContentAssistAction action = new ContentAssistAction(bundleForConstructedKeys, "ContentAssistProposal.", this);//$NON-NLS-1$
		action.setActionDefinitionId(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS);
		setAction("ContentAssistProposal", action); //$NON-NLS-1$
		markAsStateDependentAction("ContentAssistProposal", true); //$NON-NLS-1$
	}
	
	private void configureToggleCommentAction() {
		IAction action= getAction("ToggleComment"); //$NON-NLS-1$
		if (action instanceof ToggleCommentAction) {
			ISourceViewer sourceViewer= getSourceViewer();
			SourceViewerConfiguration configuration= getSourceViewerConfiguration();
			((ToggleCommentAction)action).configure(sourceViewer, configuration);
		}
	}

	protected void configureSourceViewerDecorationSupport(SourceViewerDecorationSupport support) {
		
		support.setCharacterPairMatcher(defaultPairMatcher);
		support.setMatchingCharacterPainterPreferenceKeys(MATCH_PREF_KEY, MATCH_COLOR_KEY);		
		
		super.configureSourceViewerDecorationSupport(support);
		
		//support.install(ActivatorPlugin.getDefault().getPreferenceStore());
	}
	protected IAction getAddAnnotationAction() {
//		if(addAnnotationAction == null) {
//			addAnnotationAction = new AddAnnotationAction(provider.getAnnotationModel(getEditorInput()));
//		}
//		
//		return addAnnotationAction;
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.texteditor.AbstractTextEditor#getAdapter(java.lang.Class)
	 */
//	@Override
//	public Object getAdapter(Class required) {
////		if(required.equals(IAnnotationModel.class)) {
////			return getDocumentProvider().getAnnotationModel(getEditorInput());
////		} else if(required.equals(IDocument.class)) {
////			return getDocumentProvider().getDocument(getEditorInput());
////		}
//		
//		if(required.equals(IHSElement.class)) {
//			return 
//		}
//		return super.getAdapter(required);
//	}
	
	public void dispose() {

//		ISourceViewer sourceViewer= getSourceViewer();
//		if (sourceViewer instanceof ITextViewerExtension)
//			((ITextViewerExtension) sourceViewer).removeVerifyKeyListener(fBracketInserter);

		if(matcher != null) {
			matcher.dispose();
			matcher = null;
		}
		
		if(defaultPairMatcher != null) {
			defaultPairMatcher.dispose();
			defaultPairMatcher = null;
		}
		super.dispose();
	}

	
}

class AddAnnotationAction1 extends Action{

	private IAnnotationModel model;
	private Annotation annotation;
	private Position position;
	
	public AddAnnotationAction1(IAnnotationModel model) {
		this.model = model;
	}
	
	public void setTobeAdded(Annotation annotation) {
		this.annotation = annotation;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		model.addAnnotation(annotation, position);
	}
	
}
