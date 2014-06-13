/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.model.converter;

import org.dom4j.Element;

/**
 * 拓展模型的转换器
 * @see ExtendModelConverterUtil#
 * @author maxh
 */
public interface IExtendModelConverter {
	/**
	 * 写入拓展模型
	 * @param extendModel 需要被写入的拓展模型
	 * @param valueRoot value节点 拓展模型就序列化为该节点的子节点
	 */
	public void writeExtendModel(Object extendModel,Element valueRoot);
	/**
	 * 读出拓展模型
	 * @param element value节点
	 * @return 拓展模型
	 */
	public Object readExtendModel(Element element);
}
