/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.search;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESProject;

/**
 * 主要完成资源的搜索
 * @author liaogc
 */
public  class ARESSearchQuery implements ISearchQuery {
	protected final IARESElement[] scope;/*搜索区域*/
	protected final String searchText;/*搜索文本*/
	protected final boolean isCaseSensitive;/*是否大小写*/
	protected  List<String>  searchResTypes;/*搜索类型(类型类型)*/
	protected  List<String>  searchItems;/*搜索项*/
	protected ARESSearchQuery query;/*搜索*/

	protected ARESElementSearchResult searchResult;


	public ARESSearchQuery(String searchText, boolean isCaseSensitive, List<String> searchForResTypes, List<String> searchItems, IARESElement[] scope) {
		this.searchText = searchText;
		this.isCaseSensitive = isCaseSensitive;
		this.searchResTypes = searchForResTypes;
		this.searchItems = searchItems;
		this.scope = scope;
		query = this;
	}

	public IARESElement[] getSearchScope() {
		return scope;
	}

	public boolean canRunInBackground() {
		return true;
	}

	/**
	 * 搜索元素不同资源的:调用不同的扩展点的search方法
	 * @param searchPattern
	 * @param e
	 */
	private void search(IARESElement e) {
		Collection<ARESSearcherElement> searchElements = ARESSearcherManager.getInstance().getSearcherElements().values();
		for(ARESSearcherElement element :searchElements){
			element.getSearcher().search(this.searchResTypes, this.searchItems, this.scope, this);
		}
	}

	/**
	 * 
	 * @param element 搜索的资源
	 * @return 匹配结果
	 */
	public ARESSearchElementMatch addMatch(IARESElement element) {
		// 已经存在不需要添加
		if (searchResult.getMatchCount(element) > 0) {
			return (ARESSearchElementMatch) searchResult.getMatches(element)[0];
		}

		// 要把所有父节点加入
		IARESElement parent = element.getParent();
		ARESSearchElementMatch thisMatch = new ARESSearchElementMatch(element);
		if (!(element instanceof IARESProject)) {

			ARESSearchElementMatch parentMatch = addMatch(parent);
			parentMatch.getChildren().add(element);
		} 
		searchResult.addMatch(thisMatch);
		return thisMatch;
	}










	public IStatus run(final IProgressMonitor monitor) {
		ARESElementSearchResult result = (ARESElementSearchResult) getSearchResult();
		result.removeAll();
		monitor.beginTask("搜索项目", scope.length * 10);
		if(StringUtils.isNotBlank(searchText)){
			for (IARESElement e : scope) {
				search(e);
				monitor.worked(10);
			}
		}
		
		monitor.done();
		return Status.OK_STATUS;

	}


	public String getLabel() {
		return "搜索项目";
	}

	public String getSearchString() {
		return searchText;
	}

	public Pattern getSearchPattern() {
		// 转换为正则式

		StringBuffer sbPattern = new StringBuffer();
		asRegEx(getSearchString(), sbPattern);

		if (isCaseSensitive) {
			// 默认是敏感大小写的
			return Pattern.compile(sbPattern.toString());
		} else {
			return Pattern.compile(sbPattern.toString(), Pattern.CASE_INSENSITIVE);
		}

	}

	public boolean isCaseSensitive() {
		return isCaseSensitive;
	}

	public boolean canRerun() {
		return true;
	}

	public ISearchResult getSearchResult() {
		if (searchResult == null) {
			searchResult = new ARESElementSearchResult(this);
		}
		return searchResult;
	}

	/**
	 * 返回出现关键字的位置
	 */

	public int getPosition(String pseudoCode, String keyWord, int fromIndex) {
		return pseudoCode.indexOf(keyWord, fromIndex);
	}

	/**
	 * Translates a StringMatcher pattern (using '*' and '?') to a regex pattern
	 * string
	 * 
	 * @param stringMatcherPattern
	 *         a pattern using '*' and '?'
	 */
	private static StringBuffer asRegEx(String stringMatcherPattern, StringBuffer out) {
		boolean escaped = false;
		boolean quoting = false;

		int i = 0;
		while (i < stringMatcherPattern.length()) {
			char ch = stringMatcherPattern.charAt(i++);

			if (ch == '*' && !escaped) {
				if (quoting) {
					out.append("\\E"); //$NON-NLS-1$
					quoting = false;
				}
				out.append(".*"); //$NON-NLS-1$
				escaped = false;
				continue;
			} else if (ch == '?' && !escaped) {
				if (quoting) {
					out.append("\\E"); //$NON-NLS-1$
					quoting = false;
				}
				out.append("."); //$NON-NLS-1$
				escaped = false;
				continue;
			} else if (ch == '\\' && !escaped) {
				escaped = true;
				continue;

			} else if (ch == '\\' && escaped) {
				escaped = false;
				if (quoting) {
					out.append("\\E"); //$NON-NLS-1$
					quoting = false;
				}
				out.append("\\\\"); //$NON-NLS-1$
				continue;
			}

			if (!quoting) {
				out.append("\\Q"); //$NON-NLS-1$
				quoting = true;
			}
			if (escaped && ch != '*' && ch != '?' && ch != '\\')
				out.append('\\');
			out.append(ch);
			escaped = ch == '\\';

		}
		if (quoting)
			out.append("\\E"); //$NON-NLS-1$

		return out;
	}

}
