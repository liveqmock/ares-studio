/**
 * 源程序名称：HisSheetHandler.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：ARES Studio
 * 模块名称：com.hundsun.ares.studio.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：sundl
 */
package com.hundsun.ares.studio.core.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.hundsun.ares.studio.core.excel.handler.EMFSheetHandler;
import com.hundsun.ares.studio.core.model.CoreFactory;
import com.hundsun.ares.studio.core.model.CorePackage;
import com.hundsun.ares.studio.core.model.JRESResourceInfo;
import com.hundsun.ares.studio.core.model.RevisionHistory;
import com.hundsun.ares.studio.core.model.extendable.ExtensibleModelUtil;

/**
 * 专门用于处理“版本页”的修改记录
 * @author sundl
 */
public class HisSheetHandler extends EMFSheetHandler {
	
	// 正则表达式，通过匹配“修改对象”这一列的值，判断具体的修改对象号
	private static final Pattern PATTERN = Pattern.compile("\\w*\\d+");
	
	JRESResourceInfo info;
	
	@Override
	public void startSheet(Sheet sheet) {
		super.startSheet(sheet);
		info = CoreFactory.eINSTANCE.createJRESResourceInfo();
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.EMFSheetHandler#getOwner()
	 */
	@Override
	protected EObject getOwner() {
		return info;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.EMFSheetHandler#getTableFeature()
	 */
	@Override
	protected EStructuralFeature getTableFeature() {
		return CorePackage.Literals.JRES_RESOURCE_INFO__HISTORIES;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.core.excel.handler.EMFSheetHandler#getEClass()
	 */
	@Override
	protected EClass getEClass() {
		if (parser.getCurrentBlockTag().equals("修改版本")) {
			return CorePackage.Literals.REVISION_HISTORY;
		}
		return null;
	}
	
	@Override
	public void endBlock() {
		super.endBlock();
		for (RevisionHistory his : info.getHistories()) {
			String objects = ExtensibleModelUtil.getUserExtendedProperty(his, "objects");
			if (StringUtils.isEmpty(objects)) {
				log.error(String.format("修改记录%s修改对象为空!", his.getVersion()), generateLocation());
				continue;
			}
			
			Matcher matcher = PATTERN.matcher(objects);
			List<String> objlList = new ArrayList<String>();
			while (matcher.find()) {
				objlList.add(matcher.group());
			}
			
			if (objects.contains("-") || objects.contains("~") || objlList.isEmpty()) {
				parser.exlParser.histories.put("全部", his);
			} else {
				for (String obj : objlList) {
					parser.exlParser.histories.put(obj, his);
				}
			}
		}
	}

}
