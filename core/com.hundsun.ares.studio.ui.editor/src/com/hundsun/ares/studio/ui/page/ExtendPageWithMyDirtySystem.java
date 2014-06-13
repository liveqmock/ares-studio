/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.page;

import org.eclipse.ui.forms.editor.FormEditor;

import com.hundsun.ares.studio.ui.extendpoint.manager.IExtendedPage;

/**
 * 带脏状态的拓展界面
 * @author maxh
 *
 */
public abstract class ExtendPageWithMyDirtySystem<T> extends FromPageWithMyDirtySystem<T> implements IExtendItemLoader, IExtendedPage{

	public ExtendPageWithMyDirtySystem(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	public abstract boolean shouldLoad();
	
	/**
	 * 增加一个保存的Hook，使页面可以在保存之前做一些操作。
	 * <b>写文件、保存扩展信息不需要在这里实现，这个方法是为了做一些其他操作，比如Acide里保存的时候，需要同步管理组文件夹</b>
	 * @Deprecated 实现IExtendedPage#beforeSave()
	 */
	@Deprecated
	public boolean doSave() {return true;}

	@Override
	public void init(FormEditor editor) {
		initialize(editor);
	}

	@Override
	public void onCreate() {
	}

	@Override
	public void beforeSave() {
	}

	@Override
	public void afterSave() {
	}
	
}
