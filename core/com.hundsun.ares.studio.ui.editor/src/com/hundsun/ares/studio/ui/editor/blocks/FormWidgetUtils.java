/**
 * 源程序名称：FormWidgetUtils.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.ui.editor.blocks;

import org.eclipse.swt.SWT;
import org.eclipse.ui.forms.widgets.ExpandableComposite;


/**
 * @author gongyf
 *
 */
public class FormWidgetUtils {
	
	/**
	 * 默认Section的样式
	 * @return
	 */
	public static int getDefaultSectionStyles() {
		return ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED;
	}
	
	public static int getExpanedSectionStyles() {
		return ExpandableComposite.TITLE_BAR | ExpandableComposite.EXPANDED;
	}
	
	public static int getDefaultSingleLineTextStyles() {
		return SWT.BORDER | SWT.SINGLE;
	}
	
	public static int getDefaultMultiLinesTextStyles() {
		return SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL;
	}
	
	public static int getDefaultTreeStyles() {
		return SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI;
	}
	
	public static int getDefaultTableStyles() {
		return SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI;
	}
	
	public static int[] getDefaultTextDataBingingEvents() {
		return new int[]{ SWT.Modify };
	}
}
