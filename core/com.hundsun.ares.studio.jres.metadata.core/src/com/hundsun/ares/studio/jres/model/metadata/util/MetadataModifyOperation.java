package com.hundsun.ares.studio.jres.model.metadata.util;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.log.Log;
import com.hundsun.ares.studio.jres.model.metadata.MetadataItem;
import com.hundsun.ares.studio.jres.model.metadata.MetadataResourceData;

/**
 * 对大批量元数据操作的封装，允许大量单个操作不断添加，最后统一执行一次，并报错到文件；
 * 使用这个类可以减少大量的getWorkingCopy()和save()写文件的时间。
 * @author sundl
 *
 */
public class MetadataModifyOperation<T extends MetadataItem> {
	
	private static Logger logger = Logger.getLogger(MetadataModifyOperation.class);
	
	private Multimap<String, IMetadataModifyCommand> cmdMap = ArrayListMultimap.create();
	
	private IARESProject project;
	private String resType;
	
	public MetadataModifyOperation(IARESProject project, String resType) {
		this.project = project;
		this.resType = resType;
	}
	
	public void addCommand(IMetadataModifyCommand command) {
		cmdMap.put(command.getId(), command);
	}
	
	public void run(Log log, IProgressMonitor monitor) {
		monitor.beginTask("", cmdMap.size() + 2);
		monitor.subTask("修改标准字段...");
		IARESResource res = null;
		MetadataResourceData<?> data = null;
		try {
			monitor.subTask("创建工作副本...");
			res = project.findResource(resType, resType);
			if (res != null)
				data = res.getWorkingCopy(MetadataResourceData.class);
		} catch (ARESModelException e1) {
			e1.printStackTrace();
			log.error("内部错误, 异常信息：" + e1.getMessage(), null);
		}
		
		if (data != null) {
			for (MetadataItem item : data.getItems()) {
				Collection<IMetadataModifyCommand> commands = cmdMap.get(item.getName());
				for (IMetadataModifyCommand cmd : commands) {
					try {
						monitor.subTask(cmd.getDescription());
						logger.info("字段：" + item.getName() + cmd.getDescription());
						cmd.excute(item, log);
					} catch (Exception e) {
						e.printStackTrace();
						log.error("内部错误, 异常信息：" + e.getMessage(), null);
					}
					monitor.worked(1);
				}
				// 处理完就删除
				cmdMap.removeAll(item.getName());
			}
			
			// 是否有剩余的，说明有错
			if (!cmdMap.isEmpty()) {
				for (IMetadataModifyCommand cmd : cmdMap.values()) {
					log.error(String.format("没有找到标准字段%s, 无法执行命令%s", cmd.getId(), cmd.getDescription()), null);
				}
			}
			
			monitor.subTask("保存资源...");
			try {
				res.save(data, true, null);
			} catch (ARESModelException e) {
				log.error("保存文件失败, 异常信息：" + e.getMessage(), null);
				e.printStackTrace();
			}
		}
		
		monitor.done();
	}
}
