/**
 * 
 */
package com.hundsun.ares.studio.jres.script.api.wrap;





/**
 * 元数据，对应工程结构中的元数据模块根，可以直接或者间接的获取元数据下的所有信息
 * 
 * @author yanwj06282
 *
 */
public interface IMetadataScriptWrap{

	/**
	 * 根据类型，获取元数据对象
	 * 
	 * <li>
	 * 可选类型  :
	 * 	<i>defaultvalue</i>
	 * 	<i>stdfield</i>
	 * 	<i>datatype</i>
	 * 	<i>constant</i>
	 * 	<i>errorno</i>
	 * 	<i>dict</i>
	 * 	<i>menu</i>
	 * </li>
	 * 
	 * @param type
	 * @return
	 */
	public IMetadataResScriptWrap getMetadataInfoByType(String type);
	
	/**
	 * 根据类型，获取元数据对象
	 * 
	 * <li>
	 * 可选类型  :
	 * 	<i>defaultvalue</i>
	 * 	<i>stdfield</i>
	 * 	<i>datatype</i>
	 * 	<i>constant</i>
	 * 	<i>errorno</i>
	 * 	<i>dict</i>
	 * 	<i>menu</i>
	 * </li>
	 * 
	 * @param type
	 * @param includeRequridBundles  是否包含依赖项目里的数据
	 * @return
	 */
	public IMetadataResScriptWrap getMetadataInfoByType(String type, boolean includeRequridBundles);
	
}
