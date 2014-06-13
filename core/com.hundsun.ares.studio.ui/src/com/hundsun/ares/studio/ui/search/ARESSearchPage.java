/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.search.ui.ISearchPage;
import org.eclipse.search.ui.ISearchPageContainer;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkingSet;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.ui.ARESUI;

/**
 * 
 * @author liaogc
 */
public class ARESSearchPage extends DialogPage implements ISearchPage {
	private final static String STORE_CASE_SENSITIVE = "CASE_SENSITIVE";
	private final static String STORE_HISTORY_SIZE = "HISTORY_SIZE";
	private final static String PAGE_NAME = "ARESTableDesignSearchPage";
	private final static String STORE_SEARCH_RES_TYPES = "SEARCH_RES_TYPES";
	private final static String STORE_SEARCH_ITEMS = "SEARCH_ITEMS";
	private final static String STORE_HISTORY = "HISTORY";
	private static final int HISTORY_SIZE = 12;
	private IDialogSettings dialogSettings;
	private ISearchPageContainer container;
	private  List<SearchPatternData> previousSearchPatterns = new ArrayList<SearchPatternData>();
	private List<Button> btSearchResTypes = new ArrayList<Button>();
	private List<Button> btSearchItems = new ArrayList<Button>();
	private Combo pattern;
	private Button caseSensitive;
	private boolean isCaseSensitive;
	private boolean fFirstTime= true;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);
		readConfiguration();
         Composite result= new Composite(parent, SWT.NONE);
		
		GridLayout layout= new GridLayout(2, false);
		layout.horizontalSpacing= 10;
		result.setLayout(layout);
		
		Control expressionComposite= createExpression(result);
		expressionComposite.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 2, 1));
		
		Label separator= new Label(result, SWT.NONE);
		separator.setVisible(false);
		GridData data= new GridData(GridData.FILL, GridData.FILL, false, false, 2, 1);
		data.heightHint= convertHeightInCharsToPixels(1) / 3;
		separator.setLayoutData(data);
		
		Control searchResTypes = createSearchResTypes(result);
		GridData resTypeGridData = new GridData(GridData.FILL, GridData.FILL, true, false, 2, 1);
	    int resSize = SearcherUitl.getSearchCResTypes().size();
	    int hRes = (((int)Math.sqrt(resSize))+1)* 20 ;
		resTypeGridData.heightHint = hRes ;
		searchResTypes.setLayoutData(resTypeGridData);

		
		Control searchItems = createSearchItems(result);
		GridData searchItemsData = new GridData(GridData.FILL, GridData.FILL, true, false, 2, 1);
		int itemsSize = SearcherUitl.getSearchItems().size();
	    int hItem =  (((int)Math.sqrt(itemsSize))+1)* 20 ;
		searchItemsData.heightHint = hItem ;
		searchItems.setLayoutData(searchItemsData);
		setControl(result);
		Dialog.applyDialogFont(result);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean visible) {
		
		if (visible && pattern != null) {
			if (fFirstTime) {
				fFirstTime= false;
				// Set item and text here to prevent page from resizing
				pattern.setItems(getPreviousSearchPatterns());
				if (!initializePatternControl()) {
					//初始化搜索框
					if (!initializePatternControl()) {
						pattern.select(0);
						handlePatternSelected(pattern.getSelectionIndex());
					}
					
				}
			}
			pattern.setFocus();
		}
		updateOKStatus();
		super.setVisible(visible);
	}
	
	private boolean initializePatternControl() {
		ISelection selection= container.getSelection();
		if (selection instanceof ITextSelection && !selection.isEmpty()) {
			String text= ((ITextSelection) selection).getText();
			if (StringUtils.isNotBlank(text)) {
				pattern.setText(insertEscapeChars(text));
				return true;
			}
		}
		return false;
	}
	
	private String insertEscapeChars(String text) {
		if (text == null || text.equals("")) //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		StringBuffer sbIn= new StringBuffer(text);
		BufferedReader reader= new BufferedReader(new StringReader(text));
		int lengthOfFirstLine= 0;
		try {
			lengthOfFirstLine= reader.readLine().length();
		} catch (IOException ex) {
			return ""; //$NON-NLS-1$
		}
		StringBuffer sbOut= new StringBuffer(lengthOfFirstLine + 5);
		int i= 0;
		while (i < lengthOfFirstLine) {
			char ch= sbIn.charAt(i);
			if (ch == '*' || ch == '?' || ch == '\\')
				sbOut.append("\\"); //$NON-NLS-1$
			sbOut.append(ch);
			i++;
		}
		return sbOut.toString();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.search.ui.ISearchPage#performAction()
	 */
	@Override
	public boolean performAction() {
		return performNewSearch();
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.search.ui.ISearchPage#setContainer(org.eclipse.search.ui.ISearchPageContainer)
	 */
	@Override
	public void setContainer(ISearchPageContainer container) {
		this.container = container;
	}
	
	private Control createExpression(Composite parent) {
		Composite result= new Composite(parent, SWT.NONE);
		GridLayout layout= new GridLayout(3, false);
		layout.marginWidth= 0;
		layout.marginHeight= 0;
		result.setLayout(layout);

		// Pattern text + info
		Label label= new Label(result, SWT.LEFT);
		label.setText("搜索文本（* = 任意字符串，? = 任意字符）"); 
		label.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false, 3, 1));
		// Pattern combo
		pattern= new Combo(result, SWT.SINGLE | SWT.BORDER);
//		String[] patterns = getPreviousSearchPatterns(); 
//		pattern.setItems(patterns);
		/*if(patterns.length>0){
			pattern.select(0);
		}*/
		pattern.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handlePatternSelected(pattern.getSelectionIndex());
				updateOKStatus();
			}
		}
		
		);
		pattern.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
			 doPatternModified();
			 updateOKStatus();

			}
		});

//		TextFieldNavigationHandler.install(fPattern);
		GridData data= new GridData(GridData.FILL, GridData.FILL, true, false, 2, 1);
		data.widthHint= convertWidthInCharsToPixels(50);
		pattern.setLayoutData(data);

		// Ignore case checkbox		
		caseSensitive = new Button(result, SWT.CHECK);
		caseSensitive.setText("大小写敏感  "); 
		caseSensitive.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				isCaseSensitive= caseSensitive.getSelection();
			}
		});
		caseSensitive.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false, 1, 1));
		
		caseSensitive.setSelection(this.isCaseSensitive);
		

	
		return result;
	}
	
	/**
	 * 获得之前的搜索pattern
	 * @return
	 */
	private String[] getPreviousSearchPatterns() {
		// Search results are not persistent
		int patternCount= previousSearchPatterns.size();
		String [] patterns= new String[patternCount];
		for (int i= 0; i < patternCount; i++)
			patterns[i]=  previousSearchPatterns.get(i).getPattern();
		return patterns;
	}
	
	/**
	 * 获得最新的搜索项
	 * @return
	 */
	private String[] getLastSearchItems() {
		try {
			return getDialogSettings().getArray(STORE_SEARCH_ITEMS);
		} catch (Exception e) {
			return new String[0];
		}
	}
	
	/**
	 * 获得最新资源搜索类型
	 * @return
	 */
	private String[] getLastSearchResTypes() {
		try {
			return getDialogSettings().getArray(STORE_SEARCH_RES_TYPES);
		} catch (Exception e) {
			return new String[0];
		}
	}
	
	
	/**
	 * 创建搜索资源界面
	 * @param parent
	 * @return
	 */
	private Control createSearchResTypes(Composite parent) {
		Group result= new Group(parent, SWT.NONE);
		result.setText("搜索资源");
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		//rowLayout.justify = true;
		result.setLayout(rowLayout);
		List<String>cResTypes = SearcherUitl.getSearchCResTypes();
		for(String resCType:cResTypes){
			final Button btResType = createRowButton(result, SWT.CHECK, resCType, resCType, false);
			btResType.addSelectionListener(new SelectionAdapter(){
			
				/* (non-Javadoc)
				 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
				 */
				@Override
				public void widgetSelected(SelectionEvent e) {
						handlePatternSelected();
				}
			}
			);
			btSearchResTypes.add(btResType);
		}
		//setSelectResTypes(getLastSearchResTypes());
		//Label filler= new Label(result, SWT.NONE);
		//filler.setVisible(false);
		//filler.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));

		return result;		
	}
	/**
	 * 创建搜索项界面
	 * @param parent
	 * @return
	 */
	private Control createSearchItems(Composite parent) {
		Group result= new Group(parent, SWT.NONE);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		//rowLayout.justify = true;
		result.setLayout(rowLayout);
		result.setLayoutData(rowLayout);
		result.setText("搜索项"); 
		List<String> searchItems= SearcherUitl.getSearchItems();
		for(String item:searchItems){
			Button btItem = createRowButton(result, SWT.CHECK, item, item, false);
			btSearchItems.add(btItem);
		}
	
		//setSearchItems(getLastSearchItems());
		return result;
	}
	/**
	 * 创建Button
	 * @param parent
	 * @param style
	 * @param text
	 * @param data
	 * @param isSelected
	 * @return
	 */
	private Button createButton(Composite parent, int style, String text, Object data, boolean isSelected) {
		Button button= new Button(parent, style);
		button.setText(text);
		button.setData(data);
		//button.setLayoutData(new GridData());
		button.setSelection(isSelected);
		return button;
	}
	/**
	 * 创建Button
	 * @param parent
	 * @param style
	 * @param text
	 * @param data
	 * @param isSelected
	 * @return
	 */
	private Button createRowButton(Composite parent, int style, String text, Object data, boolean isSelected) {
		Button button= new Button(parent, style);
		button.setText(text);
		button.setData(data);
		button.setLayoutData(new RowData());
		button.setSelection(isSelected);
		return button;
	}
	
	/**
	 * 设置搜索资源类型
	 * @param searchResTypes
	 */
	private void setSelectResTypes(String[] searchResTypes){
		if(searchResTypes!=null){
			for(String resType:searchResTypes){
				for(Button bt :this.btSearchResTypes){
					if(StringUtils.equals(bt.getText(), resType)){
						bt.setSelection(true);
					}
				}
			}
		}
	}
	
	/**
	 * 设置搜索项
	 * @param searchItems
	 */
	private void setSearchItems(String[] searchItems){
		if(searchItems!=null){
			for(String searchItem:searchItems){
				for(Button bt :this.btSearchItems){
					if(StringUtils.equals(bt.getText(), searchItem)){
						bt.setSelection(true);
					}
				}
			}
		}
	}
	
	/**
	 * 获得搜索资源类型
	 * @return
	 */
	private String[] getSearchResTypes(){
		List<String> searchResTypes = new ArrayList<String>();
		for(Button bt :this.btSearchResTypes){
			if(bt.getSelection()){
				searchResTypes.add(bt.getText());
			}
		}
		return searchResTypes.toArray(new String[searchResTypes.size()]);
	}
	
	/**
	 * 获得搜索项
	 * @return
	 */
	private String[] getSearchItems(){
		List<String> searchItems = new ArrayList<String>();
		for(Button bt :this.btSearchItems){
			if(bt.getSelection()&&bt.isEnabled()){
				searchItems.add(bt.getText());
			}
		}
		return searchItems.toArray(new String[searchItems.size()]);
	}

	/**
	 * 搜索
	 * @return
	 */
	private boolean performNewSearch() {
		
		SearchPatternData data = getPatternData();
		String[] searchResTypes  = data.getSearchResTypes();
		String[] searchItems = data.getSearchItems();
		Set<IARESElement> scope = new HashSet<IARESElement>();
		
		switch (getContainer().getSelectedScope()) {

		case ISearchPageContainer.WORKSPACE_SCOPE:
			IProject project[] = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			for (IProject p : project) {
				if(p.isOpen()){
					IARESProject aresProject = ARESCore.create(p);
					if (aresProject != null) {
						scope.add(aresProject);
					}
				}
			}
			break;
		case ISearchPageContainer.SELECTION_SCOPE:

			ISelection sel= getContainer().getSelection();
			if (sel instanceof IStructuredSelection && !sel.isEmpty()) {
				Iterator iter= ((IStructuredSelection) sel).iterator();
				while (iter.hasNext()) {
					Object curr= iter.next();
					if (curr instanceof IARESElement) {
						scope.add((IARESElement)curr);
					} else if (curr instanceof IResource) {
						IARESElement e = ARESCore.create((IResource)curr);
						if (e != null) {
							scope.add(e);
						}
					}
				}
			}
			break;
		case ISearchPageContainer.SELECTED_PROJECTS_SCOPE:
			String[] projectNames= getContainer().getSelectedProjectNames();
			for (int i = 0; i < projectNames.length; i++) {
				IProject p = ResourcesPlugin.getWorkspace().getRoot().getProject(projectNames[i]);
				if(p.isOpen()){
					IARESProject aresProject = ARESCore.create(ResourcesPlugin.getWorkspace().getRoot().getProject(projectNames[i]));
					if (aresProject != null) {
						scope.add(aresProject);
					}	
				}
				
			}
			break;
		case ISearchPageContainer.WORKING_SET_SCOPE:
			IWorkingSet[] workingSets = getContainer().getSelectedWorkingSets();
			// should not happen -s just to be sure
			if (workingSets == null || workingSets.length < 1)
				return false;
			
			for (int i = 0; i < workingSets.length; i++) {
				if (workingSets[i].isAggregateWorkingSet() && workingSets[i].isEmpty()) {
					for (IProject p : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
						if(p.isOpen()){
							IARESProject aresProject = ARESCore.create(p);
							if (aresProject != null) {
								scope.add(aresProject);
							}
						}
						
					}
					break;
					
				}
				
				for (IAdaptable adapt : workingSets[i].getElements()) {
					Object obj = adapt.getAdapter(IResource.class);
					if (obj != null) {
						IARESElement element = ARESCore.create((IResource)obj);
						if (element != null) scope.add(element);
					}
				}
			}
			break;
		}
		
		final ARESSearchQuery areaSearchJob = new ARESSearchQuery(pattern.getText(), isCaseSensitive,Arrays.asList(searchResTypes), Arrays.asList(searchItems), scope.toArray(new IARESElement[0]));
		NewSearchUI.runQueryInBackground(areaSearchJob);
		return true;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.DialogPage#dispose()
	 */
	@Override
	public void dispose() {
		//序列化各选择项
		if(StringUtils.isNotBlank(this.pattern.getText())){
			writeConfiguration();
		}
		super.dispose();
	}
	
	
	/**
	 * Return search pattern data and update previous searches.
	 * An existing entry will be updated.
	 * @return the pattern data
	 */
	private SearchPatternData getPatternData() {
		String pattern= getPattern();
		SearchPatternData match= findInPrevious(pattern);
		if (match != null) {
			previousSearchPatterns.remove(match);
		}
		match= new SearchPatternData(
				getSearchResTypes(),
				pattern,
				caseSensitive.getSelection(),
				getContainer().getSelectedScope(),
				getContainer().getSelectedWorkingSets(),
				getSearchItems()
		);
			
		previousSearchPatterns.add(0, match); // insert on top
		return match;
	}
	
	
	private SearchPatternData findInPrevious(String pattern) {
		for (Iterator<SearchPatternData> iter= previousSearchPatterns.iterator(); iter.hasNext();) {
			SearchPatternData element =  iter.next();
			if (pattern.equals(element.getPattern())) {
				return element;
			}
		}
		return null;
	}
	/**
	 * 读取前一次搜索的内容
	 */
	private void readConfiguration() {
		IDialogSettings s= getDialogSettings();
		//isCaseSensitive = s.getBoolean(STORE_CASE_SENSITIVE);
		try {
			int historySize= s.getInt(STORE_HISTORY_SIZE);
			for (int i= 0; i < historySize; i++) {
				IDialogSettings histSettings= s.getSection(STORE_HISTORY + i);
				if (histSettings != null) {
					SearchPatternData data= SearchPatternData.create(histSettings);
					if (data != null) {
						previousSearchPatterns.add(data);
					}
				}
			}
		} catch (NumberFormatException e) {
			// ignore
		}
	}
	
	/**
	 * Stores the current configuration in the dialog store.
	 */
	private void writeConfiguration() {
		IDialogSettings s = getDialogSettings();
		s.put(STORE_CASE_SENSITIVE, isCaseSensitive);
		s.put(STORE_SEARCH_RES_TYPES, getSearchResTypes());
		s.put(STORE_SEARCH_ITEMS, getSearchItems());
		
		int historySize= Math.min(previousSearchPatterns.size(), HISTORY_SIZE);
		s.put(STORE_HISTORY_SIZE, historySize);
		for (int i= 0; i < historySize; i++) {
			IDialogSettings histSettings = s.addNewSection(STORE_HISTORY + i);
			SearchPatternData data =  previousSearchPatterns.get(i);
			data.store(histSettings);
		}
	}
	/**
	 * Returns the page settings for this Java search page.
	 * 
	 * @return the page settings to be used
	 */
	private IDialogSettings getDialogSettings() {
		if (dialogSettings == null) {
			dialogSettings= ARESUI.getDefault().getDialogSettingsSection(PAGE_NAME);
		}
		return dialogSettings;
	}
	
	/**
	 * 选择以前的搜索项时把以前的各选择项恢复
	 * @param selectionIndex
	 */
	private void handlePatternSelected(int selectionIndex) {
		
		if (selectionIndex < 0 || selectionIndex >= previousSearchPatterns.size())
			return;
		
		SearchPatternData initialData= (SearchPatternData) previousSearchPatterns.get(selectionIndex);

		setSelectResTypes(initialData.getSearchResTypes());
		setSearchItems(initialData.getSearchItems());

		pattern.setText(initialData.getPattern());
		isCaseSensitive= initialData.isCaseSensitive();
		caseSensitive.setSelection(initialData.isCaseSensitive());

		
		if (initialData.getWorkingSets() != null)
			getContainer().setSelectedWorkingSets(initialData.getWorkingSets());
		else
			getContainer().setSelectedScope(initialData.getScope());
		
	}
	/**
	 * 当用户选择不同的资源类型选项时,相应的搜索项要做相应的变化
	 
	 */
	private  void handlePatternSelected(){
		List<String> cResTypes = SearcherUitl.getSearchCResTypes();
		List<String> selectedResTypes = new ArrayList<String>();
		/*获得所有选择的搜索类型*/
		for(String resCType:cResTypes){
			for(Button btResType:btSearchResTypes){
				if(btResType.getSelection()&& StringUtils.equals(resCType, btResType.getText())){
					if(!selectedResTypes.contains(resCType)){
						selectedResTypes.add(resCType);
					}
					
				}
			}
		}
		/*获得所有搜索类型所有所有搜索项*/
		 List<String> enableSearceItem = SearcherUitl.getSearchItemsByResTypes(selectedResTypes);
		 for(Button searchItem:btSearchItems){
			 if(enableSearceItem.contains(searchItem.getText())){
				 searchItem.setEnabled(true);
			 }else{
				 searchItem.setEnabled(false);
			 }
		 }
		
	}
	
	final void updateOKStatus() {
		boolean isValid= isValidSearchPattern();
		getContainer().setPerformActionEnabled(isValid);
	}
	
	/**
	 * 是否是合法的搜索内容
	 * @return
	 */
	private boolean isValidSearchPattern() {
		if (getPattern().length() == 0) {
			return false;
		}
		return true;
	}
	
	private void doPatternModified() {
	}
	
	private String getPattern() {
		return pattern.getText();
	}
	
	/**
	 * Returns the search page's container.
	 * @return the search page container
	 */
	private ISearchPageContainer getContainer() {
		return container;
	}

	
	
}