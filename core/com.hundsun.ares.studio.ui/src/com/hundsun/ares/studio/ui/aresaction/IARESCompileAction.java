package com.hundsun.ares.studio.ui.aresaction;

import org.eclipse.core.resources.IFile;

import com.hundsun.ares.studio.core.IARESResource;


// 这个最好是通过Adapter方式处理，这种继承没有意义。
public interface IARESCompileAction extends IARESAction {
	/**
	 * 根据当前路径 获取编译成果物路径
	 * @param path
	 * @return
	 */
	IFile[] getCompileResultPath(IARESResource res);
}
