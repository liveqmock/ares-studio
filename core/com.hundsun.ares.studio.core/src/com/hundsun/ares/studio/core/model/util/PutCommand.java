/**
 * 源程序名称：PutCommand.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：龚叶峰
 */
package com.hundsun.ares.studio.core.model.util;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.ChangeCommand;

/**
 * 用于修改map的值
 * @author gongyf
 *
 */
public class PutCommand extends ChangeCommand {

	private EObject owner;
	private EReference reference;
	private Object key;
	private Object value;
	
	
	
	/**
	 * @param owner
	 * @param reference
	 * @param key
	 * @param value
	 */
	public PutCommand(EObject owner,
			EReference reference, Object key, Object value) {
		super(owner);
		this.owner = owner;
		this.reference = reference;
		this.key = key;
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.command.ChangeCommand#doExecute()
	 */
	@Override
	protected void doExecute() {
		EMap<Object, Object> map = (EMap<Object, Object>) owner.eGet(reference);
		map.put(key, value);
	}

	@Override
	public Collection<?> getAffectedObjects() {
		Collection<Object> result = new ArrayList<Object>();
		result.add(owner);
		return result;
	}

}
