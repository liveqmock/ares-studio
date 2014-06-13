/**
 * 源程序名称：GenSQLWizard.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.database.ui.wizard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.model.util.EMFJSONUtil;
import com.hundsun.ares.studio.jres.compiler.CompilationResult;
import com.hundsun.ares.studio.jres.compiler.CompileManager;
import com.hundsun.ares.studio.jres.compiler.CompileUtils;
import com.hundsun.ares.studio.jres.compiler.IResourceCompilerFactory;
import com.hundsun.ares.studio.jres.database.compiler.DBCompilationResult;
import com.hundsun.ares.studio.jres.database.constant.IDBConstant;
import com.hundsun.ares.studio.jres.database.ui.DatabaseUI;
import com.hundsun.ares.studio.jres.database.ui.wizard.page.GenSQLInitPage;
import com.hundsun.ares.studio.jres.database.ui.wizard.page.GenSQLSelectPage;
import com.hundsun.ares.studio.jres.model.database.DatabaseResourceData;
import com.hundsun.ares.studio.jres.script.internal.util.IJSONUtil;

/**
 * @author wangxh
 *
 */
public class GenSQLWizard extends Wizard{

	private static final String IS_COMMENT = "is_comment";
	GenSQLInitPage page1;
	GenSQLSelectPage page2;
	ISelection select;
	
	
	public GenSQLWizard(ISelection select) {
		super();
		this.select = select;
		setWindowTitle("生成数据库SQL脚本");
		setNeedsProgressMonitor(true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {
				
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException,
						InterruptedException {
					
					
					// TODO 获取需要生产sql的资源列表
					// TODO 获取需要输出的文件名，用户选择的是文件夹，则生成文件为 database_20xx_xx_xx.sql
					
					// 获取需要生产的资源列表
					List<IARESResource> resourceList = new ArrayList<IARESResource>();
					resourceList.addAll(page2.getResult());
					
					Collections.sort(resourceList, new DatabaseResourceCompartor());
					
					// TODO 根据选择设置编译类型，有全量和增量的区分
					String compileType = page1.getMode();//IDBConstant.COMPILE_DATABASE_FULL;
					boolean isComment = page1.isComment();
					
					monitor.beginTask("生成SQL脚本", resourceList.size());
					try {
						// 编译上下文
						Map<Object, Object> context = new HashMap<Object, Object>();
						
						// 将用户上下文放入
						context.put(IS_COMMENT, isComment);
						context.put(IDBConstant.KEY_JS_GEN_CONTEXT, EMFJSONUtil.write(page1.getGenContext()) );
						context.put(IDBConstant.DATABASE_TYPE, page1.getDBType());
						Map<String,StringBuffer> sqlByUser = new LinkedHashMap<String,StringBuffer>();
						
						IResourceCompilerFactory factory =  CompileManager.getInstance().getFactoryByType(compileType);
						IResourceCompilerFactory spaceFactory =  CompileManager.getInstance().getFactoryByType(IDBConstant.COMPILE_DATABASE_SPACE);
						IResourceCompilerFactory userFactory =  CompileManager.getInstance().getFactoryByType(IDBConstant.COMPILE_DATABASE_USER);
						if (factory != null) {
							for (IARESResource res : resourceList) {
								
								if (monitor.isCanceled()) {
									throw new InterruptedException("用户操作中止");
								}
								context.put(CompileUtils.ARES_RESOURCE, res);
								
								monitor.subTask(res.getFullyQualifiedName());
								
								CompilationResult result;
								try {
									DatabaseResourceData info = res.getInfo(DatabaseResourceData.class);
									if (StringUtils.equals(res.getType(), "dbobject") && !StringUtils.equals(IDBConstant.COMPILE_DATABASE_PATCH, compileType)) {
										result = spaceFactory.create(info).compile(info, context);
									}else if (StringUtils.equals(res.getType(), "dbuser") && !StringUtils.equals(IDBConstant.COMPILE_DATABASE_PATCH, compileType)) {
										result = userFactory.create(info).compile(info, context);
									}else {
										result = factory.create(info).compile(info, context);
									}
									if (result.getStatus().getSeverity() != IStatus.OK) {
										throw new InvocationTargetException(result.getStatus().getException(), result.getStatus().getMessage());
									}
									
									if (result instanceof DBCompilationResult) {
										Map<String, StringBuffer> resSql = ((DBCompilationResult) result).getSqlByUser();
										if (StringUtils.isNotBlank(((DBCompilationResult) result).getTextResult())) {
											if (resSql.get("") == null) {
												resSql.put("", new StringBuffer());
											}
											resSql.get("").append(((DBCompilationResult) result).getTextResult());
										}
										for (Iterator<String> iterator = resSql.keySet()
												.iterator(); iterator.hasNext();) {
											String key = iterator.next();
											if (sqlByUser.get(key) == null) {
												sqlByUser.put(key, resSql.get(key));
											}else {
												if (sqlByUser.get(key).indexOf(resSql.get(key).toString()) < 0) {
													sqlByUser.get(key).append("\r\n");
													sqlByUser.get(key).append(resSql.get(key));
												}
											}
										}
									}
									
									monitor.worked(1);
								} catch (ARESModelException e) {
									e.printStackTrace();
								}
							}
							
							// 写入文件
							try {
								for (Iterator<Entry<String, StringBuffer>> iterator = sqlByUser.entrySet()
										.iterator(); iterator.hasNext();) {
									Entry<String, StringBuffer> entry = iterator.next();
									String key = entry.getKey();
									StringBuffer value = entry.getValue();
									File outputFile = new File(page2.getPath()+ "\\" +key+"_" +"ORTable.sql");
									if(!outputFile.exists()){
										try {
											outputFile.createNewFile();
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
									
									FileUtils.writeStringToFile(outputFile, value.toString(), "UTF-8");
								}
							} catch (IOException e) {
								throw new InvocationTargetException(e);
							}
							
						} else {
							throw new InterruptedException("没有注册对应编译工厂");
						}
					}catch (Exception e) {
						e.printStackTrace();
					} finally {
						monitor.done();
					}
					
				}
			});
		} catch (Exception e) {
			MessageDialog.openError(getShell(), "错误", e.getMessage());
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		page1 = new GenSQLInitPage("初始页", select);
		addPage(page1);
		
		page2 = new GenSQLSelectPage("选择页", select);
		addPage(page2);
		getShell().setImage(AbstractUIPlugin.imageDescriptorFromPlugin(DatabaseUI.PLUGIN_ID, "icons/table.gif").createImage());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	@Override
	public boolean canFinish() {
		if(page1.isPageActive()){
			return false;
		}
		return super.canFinish();
	}
	
	class DatabaseResourceCompartor implements Comparator<IARESResource> {

		@Override
		public int compare(IARESResource r1, IARESResource r2) {
			DatabaseResourceData o1 = null;
			DatabaseResourceData o2 = null;
			try {
				o1 = r1.getInfo(DatabaseResourceData.class);
				o2 = r2.getInfo(DatabaseResourceData.class);
			} catch (ARESModelException e1) {
				e1.printStackTrace();
			}
			
			String o1id = IJSONUtil.instance.getStringFromJSON(o1.toJSON(), "chouse_objectID");
			String o2id = IJSONUtil.instance.getStringFromJSON(o2.toJSON(), "chouse_objectID");
			try {
				int o1v = 0;
				int o2v = 0;
				if (StringUtils.isNotBlank(o1id)) {
					o1v = Integer.parseInt(o1id);
				}
				if (StringUtils.isNotBlank(o2id)) {
					o2v = Integer.parseInt(o2id);
				}
				return o1v-o2v;
			} catch (Exception e) {
			}
			return 0;
		}
	}
	
}
