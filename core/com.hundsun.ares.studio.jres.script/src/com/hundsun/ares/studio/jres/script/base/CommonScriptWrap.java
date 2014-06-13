/**
 * 
 */
package com.hundsun.ares.studio.jres.script.base;

import com.hundsun.ares.studio.core.IARESResource;

/**
 * @author yanwj06282
 *
 */
public class CommonScriptWrap<T> extends ScriptModelWrapBase<T> {

	private T t;
	
	public CommonScriptWrap(T t ,IARESResource resource) {
		super(resource);
		this.t = t;
	}

	@Override
	public T getOriginalInfo() {
		return t;
	}

}
