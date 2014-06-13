/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.util.HashMap;
import java.util.Map;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESModelStatus;
import com.hundsun.ares.studio.core.IARESElement;

/**
 * 多个ARES模型操作的Operation
 * @author sundl
 */
public abstract class MultiOperation extends ARESModelOperation {
	
	/** 复制移动操作的时候的目的元素 */
	protected Map<IARESElement, IARESElement> newParents;
	/** 重命名的列表 */
	protected String[] renamingList = null;	
	// elements ---> newName
	protected Map<IARESElement, String> renamings;
	
	public MultiOperation(IARESElement[] elementsToProcess, IARESElement[] newParent, boolean force) {
		super(elementsToProcess, newParent, force);
		newParents = new HashMap<IARESElement, IARESElement>();
		if (newParent == null)
			return;
		if (elementsToProcess.length == newParent.length) {
			for (int i = 0; i < elementsToProcess.length; i++) {
				newParents.put(elementsToProcess[i], newParent[i]);
			}
		} else {
			for (int i = 0; i < elementsToProcess.length; i++) {
				newParents.put(elementsToProcess[i], newParent[0]);
			}
		}
	}
	
	private void initializeRenamings() {
		if (renamingList != null && renamingList.length == elementsToProcess.length) {
			this.renamings = new HashMap<IARESElement, String>(renamingList.length);
			for (int i = 0; i < renamingList.length; i++) {
				this.renamings.put(elementsToProcess[i], renamingList[i]);
			}
		}
	}
	
	protected void excuteOperation() throws ARESModelException {
		processElements();		
	}
	
	protected String getNewNameFor(IARESElement element) {
		String name = null;
		if (this.renamings != null) {
			name = this.renamings.get(element);
		}
		return name;
	}
	
	/** 主任务的名字，子类实现 */
	protected abstract String getMainTaskName();
	
	protected void processElements() throws ARESModelException {
		try {
			beginTask(getMainTaskName(), this.elementsToProcess.length);
			for (IARESElement element : elementsToProcess) {
				try {
					vertify(element);
					processElement(element);
				} catch (ARESModelException e) {
					e.printStackTrace();
				} finally {
					worked(1);
				}
			}
		} catch (Exception e) {
		} finally {
			done();
		}
	}
	
	public void setRenamingList(String[] renamingList) {
		this.renamingList = renamingList;
		initializeRenamings();
	}  
	
	protected IARESElement getDestinationParent(IARESElement child) {
		return this.newParents.get(child);
	}
	
	/**
	 * 每次执行processElement之前，对这个element调用，用来检测是否可以执行
	 */
	protected abstract void vertify(IARESElement element) throws ARESModelException;
	
	/**
	 * 对给定的元素执行这个operation,子类需实现这个方法。
	 */
	protected abstract void processElement(IARESElement element) throws ARESModelException;
	
	/**
	 * 抛出一个ARESModelException
	 */
	protected void error(int code, IARESElement element) throws ARESModelException{
		throw new ARESModelException(new ARESModelStatus(code, element));
	}
}
