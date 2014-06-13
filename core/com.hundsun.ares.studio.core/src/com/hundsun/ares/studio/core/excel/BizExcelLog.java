/**
 * 源程序名称：BizExcelLog.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;

import com.hundsun.ares.studio.core.ARESCore;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.util.log.Log;

/**
 * @author sundl
 *
 */
public class BizExcelLog extends Log{

	private static Logger logger = Logger.getLogger(BizExcelLog.class);
	
	public int totle;
	
	List<Resource> createdResources = new ArrayList<Resource>();
	List<Resource> overwrittenResoruces = new ArrayList<Resource>();
	List<Resource> skippedResources = new ArrayList<Resource>();
	List<Resource> failedResources = new ArrayList<Resource>();

	public void logResCreted(Resource res) {
		createdResources.add(res);
		if (logger.isDebugEnabled())
			logger.debug(String.format("资源创建成功: %s-%s", res.name, res.type));
	}
	
	public void logResOverwritten(Resource res) {
		overwrittenResoruces.add(res);
		if (logger.isDebugEnabled())
			logger.debug(String.format("覆盖资源：%s-%s", res.name, res.type));
	}
	
	public void logResSkipped(Resource res) {
		skippedResources.add(res);
		if (logger.isDebugEnabled())
			logger.debug(String.format("跳过资源：%s-%s", res.name, res.type));
	}
	
	public void logResFailed(Resource res, String msg) {
		failedResources.add(res);
		error(msg + ":" + res.name, res.startLoc);
		logger.error(String.format("资源处理失败：%s-%s, 可能原因：%s", res.name, res.type, msg));
	}
	
	private String genResList() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tbody>");
		
		int num = 1;
		for (Resource res : failedResources) {
			sb.append(genResRow(num, "error", res, "处理失败"));
			sb.append("</tr>");
			num++;
		} 
		
		for (Resource res : skippedResources) {
			sb.append(genResRow(num, "warning", res, "用户跳过"));
			sb.append("</tr>");
			num++;
		} 
		
		for (Resource res : overwrittenResoruces) {
			sb.append(genResRow(num, "info", res, "覆盖"));
			sb.append("</tr>");
			num++;
		} 
		
//		for (Resource res : createdResources) {
//			sb.append(genResRow(num, "success", res, "创建资源"));
//			sb.append("</tr>");
//			num++;
//		} 
		sb.append("</tbody>");
		return sb.toString();
	}
	
	private String genResRow(int num, String clazz, Resource res, String handled) {
		String id = null;
		String cName = null;
		if (res.info instanceof BasicResourceInfo) {
			id = ((BasicResourceInfo) res.info).getObjectId();
			cName = ((BasicResourceInfo) res.info).getChineseName();
		}
		if (id == null)
			id = StringUtils.EMPTY;
		if (cName == null)
			cName = StringUtils.EMPTY;
		
		String name = res.name != null  ? res.name : StringUtils.EMPTY;
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("<tr class=\"%s\">", clazz));
		sb.append(String.format("<td>%s</td>", num));
		sb.append(String.format("<td>%s</td>", id));
		sb.append(String.format("<td>%s</td>", name));
		sb.append(String.format("<td>%s</td>", cName));
		sb.append(String.format("<td>%s</td>", handled));
		sb.append("</tr>");
		return sb.toString();
	}
	

	public void generateReport(OutputStream stream) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("<div id=\"overview\">共解析出%s个资源, %s个创建成功， %s个覆盖, %s个由用户跳过", 
								totle, createdResources.size(), overwrittenResoruces.size(), skippedResources.size()));
		if (!failedResources.isEmpty()) {
			sb.append(String.format("<div class=\"error\"， %s个失败</div>", failedResources.size()));
		}
		sb.append("</div>");
		String overview = sb.toString();
		
		sb.setLength(0);
		
		URL url = ARESCore.getDefault().getBundle().getEntry("import_report.html");
		try {
			url = FileLocator.resolve(url);
			String template = IOUtils.toString(url.openStream(), "utf-8");
			String result = StringUtils.replace(template, "{{overview}}", overview);
			String errInfoOverview = String.format("错误%s条，警告%s条。", String.valueOf(errors.size()), String.valueOf(warns.size()));
			if (errors.size() > 1000 || warns.size() > 1000) {
				errInfoOverview += "（信息条数太多，仅显示前2000条）";
			}
			result = StringUtils.replace(result, "{{err_info_overview}}", errInfoOverview);
			result = StringUtils.replace(result, "{{err_info_body}}", genErrorInfo(errors, warns));
			result = StringUtils.replace(result, "{{reslist}}", genResList());
			stream.write(result.getBytes("utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("读取模板文件错误", e);
		}
	}
}
