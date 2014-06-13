/**
 * 源程序名称：ValidateUtil.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.core
 * 功能说明：JRES Studio的基础架构和模型规范
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.validate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.core.ARESProblem;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.IValidateConstant;
import com.hundsun.ares.studio.core.context.DefaultNamespaceHelper;
import com.hundsun.ares.studio.core.context.INamespaceHelper;
import com.hundsun.ares.studio.core.context.IResStatisticProvider;
import com.hundsun.ares.studio.core.context.JRESContextManager;
import com.hundsun.ares.studio.core.context.statistic.IResourceTable;
import com.hundsun.ares.studio.core.model.util.Pair;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

public class ValidateUtil {
	
	/**
	 * 保存了资源存在性检查结果前缀
	 */
	private static final String KEY_RESOURCE_EXIST_CHECK_RESULT_PREFIX = "KEY_RESOURCE_EXIST_CHECK_RESULT_PREFIX$";
	
	/**
	 * 资源检查的结果状态
	 * @author gongyf
	 *
	 */
	public static enum RESULT_REFID_CHECK {
		/**
		 * 资源存在
		 */
		OK,
		
		/**
		 * 资源不存在
		 */
		ERROR_NOT_EXIST,
		
		/**
		 * 资源重复，不能直接引用
		 */
		ERROR_DUPLICATION
	}
	
	/**
	 * 消息的界面优先级
	 */
	public static final String VALIDATE_KEY_UI_SEVERITY = "消息的界面优先级";
	
	/**
	 * 消息中写入的对象
	 */
	public static final String  VALIDATE_KEY_OBJECT = String.format("%s%s", IARESProblem.UNPERSISTENT_PROPERTY_PREFIX,".pkgide.object");
	
	/**
	 * 将emf的错误模型转换为ares错误模型
	 * 
	 * @param chain
	 * @return
	 */
	public static List<IARESProblem> diagnosticChainToARESProblem(
			BasicDiagnostic chain) {
		List<IARESProblem> tmplist = new ArrayList<IARESProblem>();
		for (Diagnostic element : chain.getChildren()) {
			IARESProblem tmpitem = null;
			if (Diagnostic.ERROR == element.getSeverity()) {
				tmpitem = ARESProblem.createError();
			} else {
				tmpitem = ARESProblem.createWaring();
			}
			setProblemAttribute(element,tmpitem);
			tmplist.add(tmpitem);
		}
		return tmplist;
	}
	
	
	/**
	 * 从chain中获取问题，如果有错误优先返回错误
	 * 如果没有消息返回null
	 * @param chain
	 * @return
	 */
	public static IARESProblem getARESProblem(BasicDiagnostic chain) {
		for (Diagnostic element : chain.getChildren()) {
			IARESProblem tmpitem = null;
			if (Diagnostic.ERROR == element.getSeverity()) {
				tmpitem = ARESProblem.createError();
				setProblemAttribute(element,tmpitem);
				return tmpitem;
			}
		}
		for (Diagnostic element : chain.getChildren()) {
			IARESProblem tmpitem = null;
			if (Diagnostic.WARNING == element.getSeverity()) {
				tmpitem = ARESProblem.createWaring();
				setProblemAttribute(element,tmpitem);
				return tmpitem;
			}
		}
		return null;
	}
	
	/**
	 * 设置消息中的各个字段
	 * @param element
	 * @param tmpitem
	 */
	private static void setProblemAttribute(Diagnostic element,IARESProblem tmpitem){
		tmpitem.setAttribute(VALIDATE_KEY_UI_SEVERITY, element.getSeverity());
		tmpitem.setAttribute(VALIDATE_KEY_OBJECT, element.getData());
		tmpitem.setAttribute(IMarker.MESSAGE, element.getMessage());
		if (element.getData().size() > 0) {
			Object object =  element.getData().get(0);
			if (object instanceof EObject) {
				Resource res = ((EObject) object).eResource();
				tmpitem.setAttribute(IMarker.LOCATION, res.getURIFragment((EObject) object));
			}
			
		}
		tmpitem.setAttribute(IMarker.PRIORITY, element.getCode());
	}
	
//	/**
//	 * 获取错误检查的上下文
//	 * @param resource     资源
//	 * @param calculator   资源引用名计算类
//	 * @param provider     项目资源信息统计类
//	 * @return
//	 */
//	public static Map<Object, Object> getValidateContext(IARESResource resource
//			,INamespaceHelper calculator
//			,IResStatisticProvider provider){
//		// 构建上下文
//		Map<Object, Object> validateContext = new HashMap<Object, Object>();
//		// 加入资源
//		validateContext.put(IValidateConstant.KEY_RESOUCE, resource);
//		//加入关联计算工具
//		validateContext.put(IValidateConstant.KEY_REFERENCE_CALCULATOR, calculator);
//
//		// 加入统计信息提供器
//		validateContext.put(IValidateConstant.KEY_STATIC_PROVIDER, provider);
//		return validateContext;
//	}
	
	/**
	 * 获取资源的错误检查上下文
	 * @param resource
	 * @return
	 */
	public static Map<Object, Object> getValidateContext(IARESResource resource){
		
		Assert.isNotNull(resource);
		
		// 构建上下文
		Map<Object, Object> validateContext = new HashMap<Object, Object>();
		
		// 加入资源
		validateContext.put(IValidateConstant.KEY_RESOUCE, resource);
		
		//加入关联计算工具
		validateContext.put(IValidateConstant.KEY_REFERENCE_CALCULATOR, new DefaultNamespaceHelper());
		
		// 加入统计信息提供器
		validateContext.put(IValidateConstant.KEY_STATIC_PROVIDER, ReferenceManager.getInstance());
		
		// 加入本工程单独统计信息提供器
		validateContext.put(IValidateConstant.KEY_CURRENT_STATISTIC_PROVIDER, ReferenceManager.getInstance());
		
		// 加入引用信息提供器
		validateContext.put(IValidateConstant.KEY_REFERENCE_PROVIDER, ReferenceManager.getInstance());
		
		validateContext.put(IValidateConstant.KEY_RESOUCE_PROJECT, resource.getARESProject());
		
		return validateContext;
	}

	/**
	 * 判断在指定容器中是否存在这个值
	 * 
	 * @param value
	 * @param listOwner
	 * @param listFeature
	 * @param idFeature
	 * @param context
	 * @return
	 */
	public static boolean isContainValue(Object value, EObject listOwner, EReference listFeature, EStructuralFeature idFeature , Map<Object, Object> context) {
		Object key = new Pair<Object, Object>("检查指定容器内部是否包含这个值", 
				new Pair<Object, Object>(listOwner, new Pair<Object, Object>(listFeature, idFeature)));
		
		Set<Object> valueSet = (Set<Object>) context.get(key);
		if (valueSet == null) {
			valueSet = new HashSet<Object>();
			if (listFeature.isMany()) {
				List<EObject> list = (List<EObject>) listOwner.eGet(listFeature);
				for (EObject eObject : list) {
					valueSet.add(eObject.eGet(idFeature));
				}
			} else {
				EObject eObj = (EObject) listOwner.eGet(listFeature);
				valueSet.add(eObj.eGet(idFeature));
			}
			
			context.put(key, valueSet);
		}
		
		return valueSet.contains(value);
	}
	
	/**
	 * 检查指定对象根据指定的字段是否在所在容器中重名
	 * <BR>
	 * 提供了转换器
	 * 
	 * @param object
	 * @param idFeature
	 * @param transform
	 * @param context
	 * @return
	 */
	public static  boolean isDuplication(EObject object, EStructuralFeature idFeature, IValueTransform transform, Map<Object, Object> context) {
		Assert.isNotNull(object);
		Assert.isNotNull(object.eContainer());
		
		EObject owner = object.eContainer();
		EReference reference = object.eContainmentFeature();
		if (!reference.isMany()) {
			// 只有一个对象不可能重名
			return false;
		}
		Object key = new Pair<EObject, Pair<EReference, EStructuralFeature>>(owner, new Pair<EReference, EStructuralFeature>(reference, idFeature)) ;
		
		// 缓存的内容是检查出有错误的条目
		Set<EObject> errors = (Set<EObject>) context.get(key);
		if (errors == null) {
			// 没有进行过重名检查，需要进行检查
			errors = new HashSet<EObject>();
			
			Map<Object, EObject> map = new HashMap<Object, EObject>();
			
			for (EObject child : (List<EObject>)owner.eGet(reference)) {
				Object id = child.eGet(idFeature);
				
				// 2012年3月29日15:13:56 gongyf
				// 如果有转换器则进行转换
				if (transform != null) {
					id = transform.transform(id);
				}
				if (id == null) {
					continue;
				}
				
				EObject find = map.get(id);
				if (find != null) {
					// 能够找到则说明存在重名
					errors.add(child);
					errors.add(find);
				} else {
					// 如果没有找到重名的，则添加自己到列表中，用于检查剩下的是否存在重名
					map.put(id, child);
				}
			}
			
			context.put(key, errors);
		}
		
		return errors.contains(object);
	}
	
	/**
	 * 检查指定对象根据指定的字段是否在所在容器中重名
	 * 
	 * @param object 必须在容器中
	 * @param idFeature 用于检查重名的字段
	 * @param context
	 * @return
	 */
	public static  boolean isDuplication(EObject object, EStructuralFeature idFeature, Map<Object, Object> context) {
		return isDuplication(object, idFeature, null, context);
	}
	
	/**
	 * 检查资源是否存在，检查范围是引用表
	 * 
	 * @param project
	 * @param refId
	 * @param refType
	 * @param context
	 * @return
	 */
	public static  RESULT_REFID_CHECK checkReferenceId(IARESProject project, String refId, String refType, Map<Object, Object> context) {

		Object key_ns = new Pair<IARESProject, String>(project, "存在性检查（有命名空间）" + refType);
		Object key_nonens = new Pair<IARESProject, String>(project, "存在性检查（无命名空间）" + refType);
		
		Map<String, Integer> noneNsMap = (Map<String, Integer>) context.get(key_nonens);
		Map<String, Integer> nsMap = (Map<String, Integer>) context.get(key_ns);
		
		if (noneNsMap == null || nsMap == null) {
			
			noneNsMap = new HashMap<String, Integer>();
			nsMap = new HashMap<String, Integer>();
			
			context.put(key_nonens, noneNsMap);
			context.put(key_ns, nsMap);
			
			List<ReferenceInfo> referenceInfoList = ReferenceManager.getInstance().getReferenceInfos(project, refType, true);
			
			// 一次性找出该类型所有数据，并准备2个hash表，一个有命名空间，一个没有命名空间
			for (ReferenceInfo referenceInfo : referenceInfoList) {
				
				String resName = referenceInfo.getRefName();
				String resNs = referenceInfo.getRefNamespace();
				//EObject owner = (EObject) referenceInfo.getObject();
				//IARESResource res = referenceInfo.getResource();
				
				{
					String fullName = String.format("%s.%s", resNs, resName);
					Integer count = nsMap.get(fullName);
					if (count == null) {
						count = 1;
					} else {
						count++;
					}
					nsMap.put(fullName, count);
				}

				{
					Integer count = nsMap.get(resName);
					if (count == null) {
						count = 1;
					} else {
						count++;
					}
					noneNsMap.put(resName, count);
				}
			}
		}
		

		int count = 0;
		INamespaceHelper helper = (INamespaceHelper)context.get(IValidateConstant.KEY_REFERENCE_CALCULATOR);
		String name = helper.removeNamespace(refId);
		if (name.equals(refId)) {
			// 没有命名空间的情况
			Integer c = noneNsMap.get(refId);
			if (c != null) {
				count = c;
			}
		} else {
			Integer c = nsMap.get(refId);
			if (c != null) {
				count = c;
			}
		}

		if (count == 0) {
			return RESULT_REFID_CHECK.ERROR_NOT_EXIST;
		} else if (count == 1) {
			return RESULT_REFID_CHECK.OK;
		} else {
			return RESULT_REFID_CHECK.ERROR_DUPLICATION;
		}
	}
	
	/**
	 * 检查指定的对象的指定特性是否在引用中重复
	 * 检查是根据统计信息表的，所以不能对没有进行保存的资源进行检查
	 * 
	 * @param object 检查的对象
	 * @param idFeature 要检查的特性
	 * @param refType 该特性关联的引用类型
	 * @param refIdFeature 引用对象中使用的特性值
	 * @param excludeSet 排除列表，可以为null
	 * @param context 上下文
	 * @return 如果有重复则返回重复的对象和资源，没有则返回null
	 */
	public static  Pair<EObject, IARESResource> checkDuplicationInResStatisticProvider(EObject object, EStructuralFeature idFeature, 
			String refType, EStructuralFeature refIdFeature, Set<IARESResource> excludeSet, Map<Object, Object> context) {
		
		Object key = new Pair<Object, Object>("checkDuplicationInResStatisticProvider", new Pair<Object, Object>(refType, refIdFeature)) ;
		
		// 缓存的是引用信息中的id映射资源对象组的map
		Multimap<Object, Pair<EObject, IARESResource>> idMap = (Multimap<Object, Pair<EObject, IARESResource>>) context.get(key);
		if (idMap == null) {
			context.put(key, idMap = HashMultimap.create());
			
			//新增引用资源中的重复检查
			IARESProject project = (IARESProject) context.get(IValidateConstant.KEY_RESOUCE_PROJECT);
			List<ReferenceInfo> referenceInfoList = ReferenceManager.getInstance().getReferenceInfos(project, refType, true);
			
			// 需要排除本工程的信息
			for (ReferenceInfo referenceInfo : referenceInfoList) {
				
				EObject owner = (EObject) referenceInfo.getObject();
				IARESResource res = referenceInfo.getResource();
				
				Object id = owner.eGet(refIdFeature);
				if (id != null) {
					idMap.put(id, new Pair<EObject, IARESResource>(owner, res));
				}
			}
		}
		
		Object id = object.eGet(idFeature);
		Collection<Pair<EObject, IARESResource>> result = idMap.get(id);
		if (result != null && !result.isEmpty()) {
			if (excludeSet == null) {
				excludeSet = Collections.emptySet();
			}
			// 只有重名的资源不在排除列表中才算是错误
			for (Pair<EObject, IARESResource> pair : result) {
				if (!excludeSet.contains(pair.second)) {
					return pair;
				}
			}
		}
		
		return null;

	}
}
