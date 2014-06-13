/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
/**
 */
package com.hundsun.ares.studio.cres.extend.ui.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewer;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.metadata.ConstantItem;
import com.hundsun.ares.studio.jres.model.metadata.DictionaryItem;
import com.hundsun.ares.studio.jres.model.metadata.ErrorNoItem;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataServiceProvider;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;
import com.hundsun.ares.studio.usermacro.UserMacro;
import com.hundsun.ares.studio.usermacro.UserMacroItem;
import com.hundsun.ares.studio.usermacro.constants.IUserMacroResType;

/**
 * <p>
 * CreatedDate: 2008-2-21
 * </p>
 * 该类处理编辑器的智能提示
 * 如下内容会进行智能提示
 * 
 * 标准字段
 * 业务函数
 * 常量
 *
 * @author sundl
 */
public abstract class CRESTextHover implements ITextHover {

	protected static String LINE_SEPERATOR = "<br>";
	
	/**
	 * 全大写字符串（中间可以有_）,例如CNST_FUTURESDIRECTION_ALL
	 */
	private static Pattern CONSTANT_PATTERN = Pattern.compile("[[A-Z]+_*]+");
	
	protected IARESProject project;

	public CRESTextHover(IARESProject project) {
		this.project = project;
	}
	
	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
		return CRESTextUtil.findWord(textViewer.getDocument(), offset);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.text.ITextHover#getHoverInfo(org.eclipse.jface.text.ITextViewer,
	 *      org.eclipse.jface.text.IRegion)
	 */
 	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
	    //IMetadataService service = DataServiceManager.getInstance().getService(project, IMetadataService.class);

		if (hoverRegion != null && hoverRegion.getLength() >= 0) {
			IDocument document = textViewer.getDocument();
			try {
				String text = document.get(hoverRegion.getOffset(), hoverRegion.getLength());
				
				//如果全大写，作为常量匹配
				Matcher m = CONSTANT_PATTERN.matcher(text);
				if(m.matches()){
					return getConstantHoverInfo(text);
				}
				
				char preChar = (char) 0;
				if (hoverRegion.getOffset() > 0) {
					preChar = document.getChar(hoverRegion.getOffset() - 1);
				}
				
				//如果是@符开头的说明是标准字段 
				if(preChar == '@'){
					ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, IMetadataRefType.StdField, text, true);
					if(null != ref && ref.getObject() instanceof StandardField){
						StandardField field = (StandardField)ref.getObject();
						//  标准字段
						StringBuffer result = new StringBuffer();
						result.append(" <b>标准字段:</b> " + field.getName() + LINE_SEPERATOR);
						result.append(" <b>中文名  :</b> " + field.getChineseName() + LINE_SEPERATOR);
						result.append(" <b>数据类型:</b> " + field.getDataType() + LINE_SEPERATOR);
						TypeDefaultValue dftValue = MetadataServiceProvider.getTypeDefaultValueOfStdFieldByName(project, text);
						if(null != dftValue) {
							result.append(" <b>C类型默认值:</b> " + dftValue.getValue(MetadataServiceProvider.C_TYPE));
						}
						return result.toString();
					}
				} else if(preChar == '<'){
					return getConstantHoverInfo(text);
					
				}else {
					// 系统宏
					IARESResource[]  sysMacroRes = project.getResources(IUserMacroResType.SYSTEM_MACRO);
					if(null != sysMacroRes && sysMacroRes.length > 0){
						UserMacro sysMacro = sysMacroRes[0].getInfo(UserMacro.class);
						if (sysMacro != null) {
							UserMacroItem macroItem = findMacro(text, sysMacro);
							if (macroItem != null) {
								StringBuilder info = new StringBuilder();
								info.append(" <b>系统宏 </b>" + LINE_SEPERATOR);
								info.append(" <b>参数列表: </b>" + LINE_SEPERATOR);
								info.append(macroItem.getSequence() + LINE_SEPERATOR);
								info.append(LINE_SEPERATOR);
								info.append(" <b>宏说明: </b>" + LINE_SEPERATOR);
								info.append(" " + preProcess(macroItem.getDescription()));
								return info.toString();
							}
						}
					}
					
					// 用户自定义宏
					IARESResource[] userMacroRes = project.getResources(IUserMacroResType.USER_MACRO);
					if (userMacroRes != null && userMacroRes.length > 0) {
						UserMacro userMacro = userMacroRes[0].getInfo(UserMacro.class);
						if (userMacro != null) {
							UserMacroItem macroItem = findMacro(text, userMacro);
							if (macroItem != null) {
								StringBuilder info = new StringBuilder();
								info.append(" <b>自定义宏 </b>" + LINE_SEPERATOR);
								info.append(" <b>参数列表: </b>" + LINE_SEPERATOR);
								info.append(macroItem.getSequence() + LINE_SEPERATOR);
								info.append(LINE_SEPERATOR);
								info.append(" <b>宏说明: </b>" + LINE_SEPERATOR);
								info.append(" " + preProcess(macroItem.getDescription()));
								return info.toString();	
							}
						}
					}
				}
				//函数调用
				return getResHoverInfo(text);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ""; //$NON-NLS-1$
	}

	private String getConstantHoverInfo(String text) {
		text = text.replaceFirst("<", "").replaceFirst(">", "");
		ReferenceInfo ref = ReferenceManager.getInstance().getFirstReferenceInfo(project, IMetadataRefType.ErrNo, text, true);
		if(null != ref && ref.getObject() instanceof ErrorNoItem){
			// 错误号
			ErrorNoItem errorNO = (ErrorNoItem)ref.getObject();
				if (errorNO != null) {
					StringBuilder info = new StringBuilder();
					info.append(" <b>错误号: </b>" + errorNO.getNo() + LINE_SEPERATOR);
					info.append(" <b>错误信息: </b>" + errorNO.getMessage() + LINE_SEPERATOR);
					info.append(" <b>说      明: </b>" + errorNO.getDescription());
					return info.toString();
				}
		}
		
		ReferenceInfo ref1 = ReferenceManager.getInstance().getFirstReferenceInfo(project, IMetadataRefType.Const, text, true);
		if(null != ref1 && ref1.getObject() instanceof ConstantItem){
			//用户常量
			ConstantItem constantObj = (ConstantItem)ref1.getObject();
			if(null != constantObj){
				StringBuilder info = new StringBuilder();
				info.append(" <b>常量名: </b>" + constantObj.getName() + LINE_SEPERATOR);
				info.append(" <b>常量值: </b>" + constantObj.getValue() + LINE_SEPERATOR);
				info.append(" <b>说      明: </b>" + constantObj.getDescription());
				return info.toString();
			}
		}
		ReferenceInfo ref2 = ReferenceManager.getInstance().getFirstReferenceInfo(project, IMetadataRefType.Dictionary_Const, text, true);
		if(null != ref2 && ref2.getObject() instanceof DictionaryItem){
			//数据字典
			DictionaryItem dicItem = (DictionaryItem)ref2.getObject();
			if(null != dicItem){
				StringBuilder info = new StringBuilder();
				info.append(" <b>条目中文名: </b>" + dicItem.getParent().getChineseName() + LINE_SEPERATOR);
				info.append(" <b>条目名: </b>" + dicItem.getParent().getName() + LINE_SEPERATOR);
				info.append(" <b>字典项: </b>" + dicItem.getChineseName() + LINE_SEPERATOR);
				info.append(" <b>值: </b>" + dicItem.getValue() + LINE_SEPERATOR);
				info.append(" <b>说      明: </b>" + dicItem.getDescription());
				return info.toString();
			}
		}
		return text;
	}

 	/**
 	 * 获取资源悬浮提示信息
 	 * @param name
 	 * @return
 	 */
 	protected abstract String getResHoverInfo(String name);

	/**
 	 * 根据名字查找宏，传进来的名字参数必须是不带[ ]的
 	 * @param name
 	 * @param macroList
 	 * @return
 	 */
 	private UserMacroItem findMacro(String name, UserMacro macroList) {
 		for (UserMacroItem item : macroList.getMacroItems()) {
 			String itemName = item.getName();
 			if (StringUtils.startsWith(itemName, "[")) {
 				itemName = StringUtils.substringAfter(itemName, "[");
 			} 
 			if (StringUtils.endsWith(itemName, "]")) {
 				itemName = StringUtils.substringBeforeLast(itemName, "]");
 			}
 			if (StringUtils.equals(itemName, name))
 				return item;
 		}
 		return null;
 	}
 	
 	/**
 	 * 对给定的字符串进行预处理，把换行转换成<br>，以便在信息控件中换行
 	 */
 	private String preProcess(String text) {
 		if (!StringUtils.isEmpty(text)) {
 			return text.replace("\n", "<br>");
 		}
 		return text;
 	}

}
