/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.jres.metadata.complier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.compiler.CompilationResult;
import com.hundsun.ares.studio.jres.compiler.IResourceCompiler;
import com.hundsun.ares.studio.jres.model.metadata.Operation;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataScriptUtil;
import com.hundsun.ares.studio.jres.script.engin.ScriptUtils;

/**
 * @author qinyuan
 * 
 */
public class MetadataResComplier implements IResourceCompiler {

	private static final String USER_OPERATIONS = "用户操作列表";
	private static final String USER_OPERATIONS_CONTEXT = "用户操作列表信息内容";
	private Map<String, Object> configMsg = new HashMap<String, Object>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.jres.core.compiler.IResourceCompiler#compile(
	 * java.lang.Object, java.util.Map)
	 */
	@Override
	public CompilationResult compile(Object resource,
			Map<Object, Object> context1) {
		CompilationResult result = new CompilationResult();

		// 元数据中用户操作如果为自动编译，则需要自动运行脚本
		if (resource instanceof IARESResource) {
			IARESResource aresRes = (IARESResource) resource;
			initConfigMsg(aresRes);
			Map<String, Object> context = (Map<String, Object>) configMsg.get(USER_OPERATIONS_CONTEXT);
			EList<Operation> operations = (EList<Operation>) configMsg.get(USER_OPERATIONS);

			if (null != operations) {
				MultiStatus status = new MultiStatus(
						MetadataCompiler.PLUGIN_ID, IStatus.OK, "", null);
				for (Operation operation : operations) {
					if (operation.isAutobuild() && null != context) {
						// 用户操作需要自动编译
						try {
							String scriptFileLocation = operation.getFile();
							IARESResource jsResource = MetadataScriptUtil.getJSResource(scriptFileLocation);
							ScriptUtils.excuteJS(ScriptUtils.MODE_BUILDER, jsResource, null, null, getClass().getClassLoader()	, context);
							status.add(new Status(IStatus.OK,
									MetadataCompiler.PLUGIN_ID, "成功执行脚本["
											+ operation.getTitle() + "]"));
						} catch (Exception e) {
							status.add(new Status(Status.ERROR,
									MetadataCompiler.PLUGIN_ID, e.getMessage(),
									e));
						}
					}
				}

				if (ArrayUtils.isEmpty(status.getChildren())) {
					// 如果没有执行过任何脚本，则设置信息
					result.setStatus(new Status(IStatus.CANCEL,
							MetadataCompiler.PLUGIN_ID, "没有需要执行的脚本"));
				} else {
					// 合并所有信息
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < status.getChildren().length; i++) {
						if (i != 0) {
							sb.append(";");
						}
						sb.append(status.getChildren()[i].getMessage());
					}

					result.setStatus(new Status(status.getSeverity(),
							MetadataCompiler.PLUGIN_ID, sb.toString()));
				}
			}
		}

		return result;
	}

	/**
	 * 
	 * @param aresRes
	 * @param context
	 * @param operations
	 */
	private void initConfigMsg(IARESResource aresRes) {/*
		Map<String, Object> context = new HashMap<String, Object>();
		EList<Operation> operations = null;
		configMsg.clear();
		try {
			if (aresRes.getType().equals(IMetadataResType.ErrNo)) {
				ErrorNoList info = aresRes.getInfo(ErrorNoList.class);
				if (null != info) {
					operations = info.getOperations();

					configMsg.put(USER_OPERATIONS, operations);
					configMsg.put(USER_OPERATIONS_CONTEXT, context);
				}
			} else if (aresRes.getType().equals(IMetadataResType.BizType)) {
				BusinessDataTypeList info = aresRes
						.getInfo(BusinessDataTypeList.class);
				if (null != info) {
					operations = info.getOperations();

					configMsg.put(USER_OPERATIONS, operations);
					context.put("language", "oracle");
					configMsg.put(USER_OPERATIONS_CONTEXT, context);
				}
			} else if (aresRes.getType().equals(IMetadataResType.Const)) {
				ConstantList info = aresRes.getInfo(ConstantList.class);
				if (null != info) {
					operations = info.getOperations();

					configMsg.put(USER_OPERATIONS, operations);
					configMsg.put(USER_OPERATIONS_CONTEXT, context);
				}
			} else if (aresRes.getType().equals(IMetadataResType.DefValue)) {
				TypeDefaultValueList info = aresRes
						.getInfo(TypeDefaultValueList.class);
				if (null != info) {
					operations = info.getOperations();

					configMsg.put(USER_OPERATIONS, operations);
					configMsg.put(USER_OPERATIONS_CONTEXT, context);
				}
			} else if (aresRes.getType().equals(IMetadataResType.Dict)) {
				DictionaryList info = aresRes.getInfo(DictionaryList.class);
				if (null != info) {
					operations = info.getOperations();

					configMsg.put(USER_OPERATIONS, operations);
					configMsg.put(USER_OPERATIONS_CONTEXT, context);
				}
			} else if (aresRes.getType().equals(IMetadataResType.StdField)) {
				StandardFieldList info = aresRes
						.getInfo(StandardFieldList.class);
				if (null != info) {
					operations = info.getOperations();

					configMsg.put(USER_OPERATIONS, operations);
					configMsg.put(USER_OPERATIONS_CONTEXT, context);
				}
			} else if (aresRes.getType().equals(IMetadataResType.StdType)) {
				StandardDataTypeList info = aresRes
						.getInfo(StandardDataTypeList.class);
				if (null != info) {
					operations = info.getOperations();

					configMsg.put(USER_OPERATIONS, operations);
					configMsg.put(USER_OPERATIONS_CONTEXT, context);
				}
			}

		} catch (ARESModelException e) {
			e.printStackTrace();
		}
	*/}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hundsun.ares.studio.jres.core.compiler.IResourceCompiler#clean(java
	 * .lang.Object, java.util.Map)
	 */
	@Override
	public void clean(Object resource, Map<Object, Object> context) {

	}

	/**
	 * 获得脚本的文件名
	 * 
	 * @param operation
	 * @return
	 */
	private String getFileName(Operation operation) {
		return operation.getFile();
	}

	/**
	 * 判断脚本文件名是否合法
	 * 
	 * @param operation
	 * @return
	 */
	private boolean canExcuteByFile(Operation operation) {
		String fileaName = getFileName(operation);
		return (null != fileaName && !"".equals(fileaName));
	}

	/**
	 * 获得脚本的内容
	 * 
	 * @param operation
	 * @return
	 */
	private String getScriptCode(Operation operation) {
		String fileaName = getRelPath(getFileName(operation));

		FileInputStream inputStream = null;
		try {
			File scriptFile = new File(fileaName);
			inputStream = new FileInputStream(scriptFile);
			String scriptCode = IOUtils.toString(inputStream,"UTF-8");
			return scriptCode;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
		return "";

	}
	private String getRelPath(String scriptFileLocation){
		String newScriptFileLocation = scriptFileLocation.substring(scriptFileLocation.indexOf("/")+1) ;
		String projectName =newScriptFileLocation.substring(0,newScriptFileLocation.indexOf("/")) ;
		
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for(IProject project :projects){
			if(project.getName().equals(projectName)){
				return project.getLocation().toString()+newScriptFileLocation.substring(newScriptFileLocation.indexOf("/"));
			}
		}
		return scriptFileLocation;
		
	}

}
