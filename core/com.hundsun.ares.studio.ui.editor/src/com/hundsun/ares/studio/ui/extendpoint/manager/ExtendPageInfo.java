/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.extendpoint.manager;

/**
 * 表示拓展点中的一个拓展页面信息
 * @author maxh
 *
 */
public class ExtendPageInfo implements Comparable<ExtendPageInfo> {
	
	Class pageClass;
	String pageId = "";
	String pageName = "";
	int order = 0;
	boolean hidden;	// 表示这个是个隐藏的扩展页面，不创建界面，仅做后台逻辑处理
	
	
	/**
	 * @param pageClass
	 * @param pageId
	 * @param pageName
	 * @param order
	 */
	public ExtendPageInfo(Class pageClass, String pageId, String pageName, int order, boolean hidden) {
		super();
		this.pageClass = pageClass;
		this.pageId = pageId;
		this.pageName = pageName;
		this.order = order;
		this.hidden = hidden;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}
	
	public Class getPageClass() {
		return pageClass;
	}
	public void setPageClass(Class pageClass) {
		this.pageClass = pageClass;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public boolean isHidden() {
		return hidden;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ExtendPageInfo o) {
		return order - o.order;
	}
}
