package com.hundsun.ares.studio.engin.token;

import java.util.List;

/**
 * 包含变量的文本token
 * @author lvgao
 *
 */
public interface ITextWithParamsToken extends ICodeToken{

	/**
	 * 获取使用过的参数
	 * @return
	 */
	public List<String> getUsedParams();
}
