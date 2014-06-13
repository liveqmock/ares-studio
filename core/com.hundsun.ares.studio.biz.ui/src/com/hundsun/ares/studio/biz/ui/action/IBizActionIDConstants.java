/**
 * 源程序名称：IBizActionIDConstants.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.biz.ui.action;

import com.hundsun.ares.studio.ui.editor.actions.IActionIDConstant;

/**
 * 业务逻辑公共Action ID常量定义
 * @author sundl
 *
 */
public interface IBizActionIDConstants extends IActionIDConstant{
	
	/** 添加标准字段参数 Action */
	String ADD_PARAM_STD_FIELD = CV_ADD + "std";
	/** 添加参数组的Action */
	String ADD_PARAM_GROUP = CV_ADD + ".paramGroup";
	/** 添加对象类型参数 */
	String ADD_OBJECT_PARAM = CV_ADD + ".objparm";
	/** 添加非标准字段参数 */
	String ADD_NON_STD_FIELD_PARME = CV_ADD + ".non-stdfield-parma";
	/** 添加到标准字段 */
	String ADD_TO_STD_FIELD = CV_ADD + ".add-to-std-field";
	/** 添加组件 */
	String ADD_PARAM_COMPONENT = CV_ADD + ".component";
}
