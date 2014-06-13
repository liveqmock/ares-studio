/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author liaogc
 */
public class ARESSearcherElement {
	private  List<String>searchItems = new ArrayList<String>();//搜索项
	private List<String> searcherResTypes =  new ArrayList<String>();//搜索对应的资源类型
	private IARESSarcher searcher;//搜索器
	private String searchId ;//搜索器
	private String order;//资源排序
	
	
	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the searchId
	 */
	public String getSearchId() {
		return searchId;
	}

	/**
	 * @param searchId the searchId to set
	 */
	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	/**
	 * @return the searchItems
	 */
	public List<String> getSearchItems() {
		return searchItems;
	}

	/**
	 * @param searchItems the searchItems to set
	 */
	public void setSearchItems(List<String> searchItems) {
		this.searchItems = searchItems;
	}


	/**
	 * @return the searcherResType
	 */
	public List<String> getSearcherResTypes() {
		return searcherResTypes;
	}


	/**
	 * @return the searcher
	 */
	public IARESSarcher getSearcher() {
		return searcher;
	}

	/**
	 * @param searcher the searcher to set
	 */
	public void setSearcher(IARESSarcher searcher) {
		this.searcher = searcher;
	}

	public ARESSearcherElement() {

	}

	

}
