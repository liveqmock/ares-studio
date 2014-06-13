/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.extendpoint;

import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;

public abstract class PageExtendPointProvider {
	
	static final public String PAGE_ID = "pageid";
	static final public String PAGE_NAME = "name";
	static final public String EDITOR_ID = "editor_id";
	static final public String ORDER = "order";
	static final public String HIDDEN = "hidden";
	
	/**
	 * 拓展点配置的ID
	 */
	protected String id;
	/**
	 * 拓展点配置的名称
	 */
	protected String name;
	/**
	 * 创建该页面的编辑器
	 */
	protected FormEditor editor;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FormEditor getEditor() {
		return editor;
	}
	public void setEditor(FormEditor editor) {
		this.editor = editor;
	}
	
	public abstract IFormPage getPage();
}
