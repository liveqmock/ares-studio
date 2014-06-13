/**
 * 源程序名称：AbstractSheetHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.biz.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Sheet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.excel.ISheetHandler;
import com.hundsun.ares.studio.core.excel.Module;
import com.hundsun.ares.studio.core.excel.Resource;
import com.hundsun.ares.studio.core.excel.SheetParser;
import com.hundsun.ares.studio.core.model.BasicResourceInfo;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.util.log.Log;
import com.hundsun.ares.studio.core.util.log.Log.Location;

/**
 * ISheetHanlder的抽象类实现
 * @author sundl
 *
 */
public abstract class AbstractSheetHandler implements ISheetHandler{

	protected SheetParser parser;
	protected Log log;
	
	protected Sheet sheet;
	protected Module module;
	protected List<Resource> resourceList = new ArrayList<Resource>();
	
	protected Location resStartLoc;
	
	@Override
	public void init(SheetParser parser, Log log) {
		this.parser = parser;
		this.log = log;
	}
	
	protected Location generateLocation() {
		if (sheet != null && parser.exlParser != null) {
			Location loc =  Log.createLocation(sheet.getSheetName(), parser.getCurrentRow(), -1);
			loc.file = parser.exlParser.file.getName();
			return loc;
		}
		else
			return Log.createLocation(null, parser.getCurrentRow(), -1);
	}
	
	protected IARESProject getProject() {
		return (IARESProject) parser.exlParser.context.get("project");
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#startSheet(org.apache.poi.hssf.usermodel.HSSFSheet)
	 */
	@Override
	public void startSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#startArea(java.lang.String)
	 */
	@Override
	public void startArea(String startTag) {
		resStartLoc = generateLocation();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#startBlock(java.lang.String, int)
	 */
	@Override
	public void startBlock(String startTag, int type) {
		if (StringUtils.equals(startTag, "模块中文名") ||
				StringUtils.equals(startTag, "模块英文名")
				|| StringUtils.equals(startTag, "模块名")) {
			module = new Module();
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#keyValue(java.lang.String, java.lang.String)
	 */
	@Override
	public void keyValue(String key, String value) {
		// 处理模块中英文名信息
		String blockTag = parser.getCurrentBlockTag();
		if (StringUtils.equals(blockTag, "模块中文名") || StringUtils.equals(blockTag, "模块名")
					|| StringUtils.equals(blockTag, "模块英文名")) {
			if (StringUtils.equals(key, "模块中文名")) {
				module.cName = value;
			} else if (StringUtils.equals(key, "模块名") 
							|| StringUtils.equals(key, "模块英文名")) {
				module.name = value;
				String correctName = correctResName(module.name);
				if (correctName != null) {
					module.name = correctName;
					log.warn(String.format("模块名[%s]不符合命名规范，被替换为[%s]", value, correctName), generateLocation());
				}
			} else {
				log.error("无法处理属性：" + key , generateLocation());
			}
		}
	}
	
	public static String correctResName(String name) {
		String result = null;
		if (StringUtils.contains(name, '(') || StringUtils.contains(name, ')')) {
			result = StringUtils.replace(name, "(", "_");
			result = StringUtils.replace(result, ")", "");
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#header(java.lang.String[])
	 */
	@Override
	public void header(String[] header) {
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#row(java.lang.String[])
	 */
	@Override
	public void row(String[] row) {
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#endBlock()
	 */
	@Override
	public void endBlock() {
		
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#endArea()
	 */
	@Override
	public void endArea() {
		
	}
	
	protected void resourceFound(Resource res) {
		this.resourceList.add(res);
		res.startLoc = resStartLoc;
		res.endLoc = generateLocation();
		
		// 处理资源名为空的情况
		if (StringUtils.isEmpty(res.name)) {
			if (res.info instanceof BasicResourceInfo) {
				BasicResourceInfo basicInfo = (BasicResourceInfo) res.info;
				String id = ((BasicResourceInfo) res.info).getObjectId();
				if (!StringUtils.isEmpty(id)) {
					// 英文名为空但填了对象号，尝试用对象号来生成英文名
					String generatedName = "r_" + id;
					basicInfo.setName(generatedName);
					res.name = generatedName;
					log.warn(String.format("资源英文名为空, 导入时将使用对象号自动生成文件名：%s", res.getDescription()), generateLocation());
				} else {
					log.error(String.format("资源英文名为空，对象号也为空，无法导入：%s", res.getDescription()), res.startLoc);
				}
			} else {
				log.error(String.format("资源英文名为空，无法导入：%s", res.getDescription()), generateLocation());
			}
		} else {
			// 处理资源名中的非法字符，比如()
			String correctName = correctResName(res.name);
			if (correctName != null) {
				log.warn(String.format("资源名[%s]不符合命名规范，被替换为[%s]", res.name, correctName), generateLocation());
				res.name = correctName;
			}
		}
		
		if (res.info instanceof JRESResourceInfo) {
			JRESResourceInfo info = (JRESResourceInfo) res.info;
			String id = info.getObjectId();
			Collection<RevisionHistory> histories = parser.exlParser.histories.get(id);
			for (RevisionHistory his : histories) {
				info.getHistories().add(EcoreUtil.copy(his));
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.biz.excel.ISheetHandler#endSheet()
	 */
	@Override
	public void endSheet() {
		if (parser.exlParser != null)
			parser.exlParser.resources.putAll(module, resourceList);
		
		// 此处分析一下资源是否有重复的资源名
		Multimap<String, Resource> resMap = ArrayListMultimap.create();
		for (Resource res : resourceList) {
			// 资源名为空也是另一种错误，已经在其他地方进行了检查，此处就不再重复
			if (!StringUtils.isEmpty(res.name)) {
				resMap.put(res.name, res);
			}
		}
		
		for (String name : resMap.keySet()) {
			if (resMap.get(name).size() > 1) {
				Collection<Resource> resources = resMap.get(name);
				
				// 重复的资源是否都具有对象号
				boolean noObjIdIsNull = true;
				for (Resource r : resources) {
					BasicResourceInfo brInfo = (BasicResourceInfo) r.info;
					if (StringUtils.isEmpty(brInfo.getObjectId())) {
						noObjIdIsNull = false;
						break;
					} else {
						continue;
					}
				}
				
				List<String> resNames = new ArrayList<String>();
				List<String> resLocations = new ArrayList<String>();
				
				for (Resource r : resources) {
					resLocations.add(r.startLoc == null ? "" : String.valueOf(r.startLoc.row));
					if (r.info instanceof BasicResourceInfo) {
						BasicResourceInfo brInfo = (BasicResourceInfo) r.info;
						String id = brInfo.getObjectId();
						// 资源名重复的情况下，如果对象号不为空，则尝试用对象号加前缀作为资源名，避免覆盖
						// 如果资源名重复，对象号也为空，那就没办法了，先后创建两个资源，创建时会提示覆盖
						if (noObjIdIsNull) {
							String generatedName = "r_" + id;
							brInfo.setName(generatedName);
							r.name = generatedName;
							resNames.add(String.format("(%s)%s", brInfo.getObjectId(), brInfo.getChineseName()));
						} 
					}
				}
				if (noObjIdIsNull) {
					String resNamesStr = StringUtils.join(resNames, ',');
					log.warn(String.format("发现以下资源的英文名(%s)重复，创建文件时将使用对象号自动生成文件名。资源： %s", name, resNamesStr), generateLocation());
				} else {
					String resLocStr = StringUtils.join(resLocations, ',');
					log.error(String.format("发现多个资源的英文名(%s)重复，且并非全部都有对象号，无法自动生成新资源名，只能导入其中之一，资源所在行号分别为：%s", name, resLocStr), generateLocation());
				}
			}
		}
		resMap.clear();
		resMap = null;
		
		sheet = null;
	}
	
}
