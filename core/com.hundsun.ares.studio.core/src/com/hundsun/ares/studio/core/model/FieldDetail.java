/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * 在模型中添加了界面的信息
 * 破坏了模型的纯洁性 抛弃
 * 模型字段标注
 * @author maxh
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldDetail {
	/**
	 * 界面上展现的名称
	 * @return
	 */
	String showName() default "";
	/**
	 * 界面上展现的图片 主要是在大纲里面用
	 * @return
	 */
	String showPic() default "";
	/**
	 * 用于获得模型展现字段
	 * @return
	 */
	String mainGetMethod() default "";
//	/**
//	 * 界面展现的控件类型
//	 * @return
//	 */
//	int showControlType() default TEXT_TYPE;
//	/**
//	 * combo和assittext的选项值
//	 * @return
//	 */
//	String[] value() default {};
	/**
	 * 是否在大纲中展现
	 * @return
	 */
	boolean showInOutline() default true;
//	/**
//	 * 是否模型生成界面
//	 * @return
//	 */
//	boolean genUI() default false;
//	
//	
//	
//	static final public int TEXT_TYPE = 0;
//	static final public int COMBO_TYPE = 1;
//	static final public int DATE_TYPE = 2;
//	static final public int ASSIT_TEXT_TYPE = 3;
}
