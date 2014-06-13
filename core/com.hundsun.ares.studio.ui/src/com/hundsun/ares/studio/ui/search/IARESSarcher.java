/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.ui.search;

import java.util.List;

import com.hundsun.ares.studio.core.IARESElement;

/**
 * 
 * @author liaogc
 */
public interface IARESSarcher {
   public  void search(List<String> searchForResTypes, List<String> searchItems, IARESElement[] scope,ARESSearchQuery query);
}
