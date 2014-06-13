/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.page;

/**
 * @author maxh
 *
 */
public interface IExtendItemLoader {
	/**
	 * 读取项目配置
	 * 根据构建信息决定是否展现自己
	 * @return
	 */
	boolean shouldLoad();
}
