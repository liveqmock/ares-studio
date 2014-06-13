/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.control;

import org.eclipse.jface.dialogs.IMessageProvider;

import com.hundsun.ares.studio.core.util.StringUtil;

public class AresControlError {
	public static final int INFOMATION = IMessageProvider.INFORMATION;
	public static final int WARNING = IMessageProvider.WARNING;
	public static final int ERROR = IMessageProvider.ERROR;
	String showInfo;
	int level = ERROR;
	
	public AresControlError(String showInfo) {
		setShowInfo(showInfo);
	}
	
	public AresControlError(String showInfo,int level) {
		setShowInfo(showInfo);
		setLevel(level);
	}

	public String getShowInfo() {
		return showInfo;
	}

	public void setShowInfo(String showInfo) {
		this.showInfo = StringUtil.getStringSafely(showInfo);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		if(level == INFOMATION){
			this.level = INFOMATION;
		}else if(level == WARNING){
			this.level = WARNING;
		}else{
			this.level = ERROR;
		}
	}
	
	
}
