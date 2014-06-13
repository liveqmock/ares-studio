/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.metadata.complier;

import org.eclipse.emf.ecore.EClass;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.compiler.IResourceCompiler;
import com.hundsun.ares.studio.jres.compiler.IResourceCompilerFactory;
import com.hundsun.ares.studio.jres.model.metadata.MetadataPackage;

/**
 * @author gongyf
 *
 */
public class MetadataCompilerFactory implements IResourceCompilerFactory {

	/**
	 * 
	 */
	public MetadataCompilerFactory() {
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.core.compiler.IResourceCompilerFactory#isSupport(com.hundsun.ares.studio.core.IARESProject)
	 */
	@Override
	public boolean isSupport(IARESProject project) {
		/*
		 * DESIGN#错误检查#龚叶峰#普通#龚叶峰#检查编译工厂是否可用
		 *
		 * 目前编译工厂应该对外部没有任何要求，但是今后可能对jresdk版本有要求
		 * 需要进行考虑
		 */
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.core.compiler.IResourceCompilerFactory#create(com.hundsun.ares.studio.core.IARESResource)
	 */
	@Override
	public IResourceCompiler create(Object resource) {
		/*
		 * DESIGN#资源编译#龚叶峰#困难#编写人#根据资源构建编译器
		 *
		 * 需要对应资源创建编译器，编译器最终将使用jet方式实现，但是目前第一阶段只有元数据部分
		 * 所以编译器应该是读取元数据资源内的脚本资源进行编译，可能编译结果在脚本中生产代码
		 * 实际的编译器可能不返回编译结果。
		 */
		return new MetadataResComplier();
	}

}
