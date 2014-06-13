/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.search;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.search.ui.ISearchPageContainer;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author liaogc
 */
class SearchPatternData {

	private String pattern;
	private boolean isCaseSensitive;
	private String[] searchResTypes;
	private String[] searchItems;
	private int scope;
	private IWorkingSet[] workingSets;
	
	public SearchPatternData(String[] searchResTypes, boolean isCaseSensitive, String pattern, String[] searchItems) {
		this(searchResTypes, pattern, isCaseSensitive, ISearchPageContainer.WORKSPACE_SCOPE, null, searchItems);
	}
	
	public SearchPatternData(String[] searchResTypes, String pattern, boolean isCaseSensitive, int scope, IWorkingSet[] workingSets, String[] searchItems) {
		this.searchResTypes= searchResTypes;
		this.pattern= pattern;
		this.isCaseSensitive= isCaseSensitive;
		this.scope= scope;
		this.workingSets= workingSets;
		this.searchItems= searchItems;
		
	}
	
	public boolean isCaseSensitive() {
		return isCaseSensitive;
	}

	public String getPattern() {
		return pattern;
	}

	public int getScope() {
		return scope;
	}


	public IWorkingSet[] getWorkingSets() {
		return workingSets;
	}
	
	
	/**
	 * @return the searchResTypes
	 */
	public String[] getSearchResTypes() {
		return searchResTypes;
	}

	/**
	 * @return the searchItems
	 */
	public String[] getSearchItems() {
		return searchItems;
	}
	
	public void store(IDialogSettings settings) {
		settings.put("searchResTypes", searchResTypes); 
		settings.put("searchItems", searchItems); 
		settings.put("scope", scope); 
		settings.put("pattern", pattern); 
		settings.put("isCaseSensitive", isCaseSensitive); 
		if (workingSets != null) {
			String[] wsIds= new String[workingSets.length];
			for (int i= 0; i < workingSets.length; i++) {
				wsIds[i]= workingSets[i].getName();
			}
			settings.put("workingSets", wsIds); 
		} else {
			settings.put("workingSets", new String[0]); 
		}
	}
	
	public static SearchPatternData create(IDialogSettings settings) {
		String pattern= settings.get("pattern"); 
		if (pattern.length() == 0) {
			return null;
		}

		String[] wsIds= settings.getArray("workingSets"); 
		IWorkingSet[] workingSets= null;
		if (wsIds != null && wsIds.length > 0) {
			IWorkingSetManager workingSetManager= PlatformUI.getWorkbench().getWorkingSetManager();
			workingSets= new IWorkingSet[wsIds.length];
			for (int i= 0; workingSets != null && i < wsIds.length; i++) {
				workingSets[i]= workingSetManager.getWorkingSet(wsIds[i]);
				if (workingSets[i] == null) {
					workingSets= null;
				}
			}
		}

		try {
			
			int scope= settings.getInt("scope"); 
			boolean isCaseSensitive= settings.getBoolean("isCaseSensitive"); 
			String[] searchResTypes = new String[0]; 
			String[] searchItems = new String[0];
			if (settings.getArray("searchResTypes") != null) { 
				searchResTypes= settings.getArray("searchResTypes");
			} 
			if (settings.getArray("searchItems") != null) { 
				searchItems= settings.getArray("searchItems"); 
			} 
			return new SearchPatternData(searchResTypes, pattern, isCaseSensitive, scope, workingSets, searchItems);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	


}
