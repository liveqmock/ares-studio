/**
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.atom.reference;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.BasicEList;

import com.hundsun.ares.studio.atom.AtomFunction;
import com.hundsun.ares.studio.atom.AtomPackage;
import com.hundsun.ares.studio.atom.impl.PseudoCodeTextAttributeReferenceImpl;
import com.hundsun.ares.studio.biz.BizPackage;
import com.hundsun.ares.studio.biz.ParamType;
import com.hundsun.ares.studio.biz.Parameter;
import com.hundsun.ares.studio.biz.constants.IBizRefType;
import com.hundsun.ares.studio.biz.core.BizUtil;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.model.IReferenceProvider2;
import com.hundsun.ares.studio.core.model.Reference;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceImpl;
import com.hundsun.ares.studio.core.model.impl.TextAttributeReferenceWithNamespaceImpl;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;

/**
 * @author liaogc
 *
 */
public class AtomReferenceProvider implements IReferenceProvider2 {
		
		public static AtomReferenceProvider INSTANCE = new AtomReferenceProvider();
		
		private AtomReferenceProvider() {}

		/* (non-Javadoc)
		 * @see com.hundsun.ares.studio.core.model.IReferenceProvider2#getReferences(java.lang.Object, com.hundsun.ares.studio.core.IARESProject)
		 */
		@Override
		public List<Reference> getReferences(Object obj, IARESProject aresProject) {
			List<Reference> references = new ArrayList<Reference>();
			if (obj instanceof AtomFunction) {
				AtomFunction af = (AtomFunction) obj;
				BasicEList<Parameter> parameters = new BasicEList<Parameter>();
				parameters.addAll(af.getInputParameters());//添加输入参数
				parameters.addAll(af.getOutputParameters());//添加输出参数
				parameters.addAll(af.getInternalVariables());//添加内部变量
				for(Parameter parameter:parameters){
					if(StringUtils.isNotBlank(parameter.getId())&& parameter.getParamType()==ParamType.STD_FIELD){
						Reference ref = new TextAttributeReferenceWithNamespaceImpl(IMetadataRefType.StdField, 
								parameter, 
								BizPackage.Literals.PARAMETER__ID);
						references.add(ref);
					}else if(parameter.getParamType()==ParamType.OBJECT || parameter.getParamType()==ParamType.PARAM_GROUP){
						if (BizUtil.hasStdObjList(aresProject)) {
							if (!StringUtils.isEmpty(parameter.getId())) {
								Reference ref = new TextAttributeReferenceImpl(IBizRefType.Std_Obj, parameter, BizPackage.Literals.PARAMETER__ID);
								references.add(ref);
							}
						} else 	if (!StringUtils.isEmpty(parameter.getType())) {
							// 不使用对象标准字段的情况下，类型引用对象资源
							Reference ref = new TextAttributeReferenceImpl(IBizRefType.Object, parameter, BizPackage.Literals.PARAMETER__TYPE);
							references.add(ref);
						}
					}
				}
				//伪代码Reference
				String pseudoCode = af.getPseudoCode();
				if(StringUtils.isNotBlank(pseudoCode)){
					Reference ref1 = new PseudoCodeTextAttributeReferenceImpl(IMetadataRefType.StdField, af, AtomPackage.Literals.ATOM_FUNCTION__PSEUDO_CODE);
					references.add(ref1);
					//对象标准字段也支持伪代码重构
					Reference ref2 = new PseudoCodeTextAttributeReferenceImpl(IBizRefType.Std_Obj, af, AtomPackage.Literals.ATOM_FUNCTION__PSEUDO_CODE);
					references.add(ref2);
				}
			}
			return references;
		}


}
