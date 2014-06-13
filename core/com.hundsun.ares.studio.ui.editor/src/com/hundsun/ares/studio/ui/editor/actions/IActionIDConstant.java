/**
 * 源程序名称：IActionIDConstant.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.ui
 * 功能说明：JRES Studio的界面展现基础框架和编辑相关功能
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.ui.editor.actions;

/**
 * @author gongyf
 *
 */
public interface IActionIDConstant {
	/**
	 * ColumnViewer的添加条目
	 */
	String CV_ADD = "cv.add";
	
	/**
	 * ColumnViewer的插入条目
	 */
	String CV_INSERT = "cv.insert";
	
	/**
	 * ColumnViewer的删除条目
	 */
	String CV_DELETE = "cv.delete";
	/**
	 * ColumnViewer的上移(最上)
	 */
	String CV_MOVE_TOP = "cv.movetop";
	/**
	 * ColumnViewer的上移
	 */
	String CV_MOVE_UP = "cv.moveup";
	
	/**
	 * ColumnViewer的下移
	 */
	String CV_MOVE_DOWN = "cv.movedown";
	
	/**
	 * ColumnViewer的下移(最下)
	 */
	String CV_MOVE_BOTTOM = "cv.movebottom";
	/**
	 * 错误检查
	 */
	String CV_VALIDATE = "cv.validate";
	
	/**
	 * 复制
	 */
	String CV_COPY = "cv.copy";
	
	/**
	 * 粘贴
	 */
	String CV_PASTE = "cv.paste";
	
	/**
	 * 刷新
	 */
	String CV_REFRESH = "cv.refersh";
}
