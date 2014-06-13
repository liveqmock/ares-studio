/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.cres.extend.ui.module.gencode.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hundsun.ares.studio.core.model.RevisionHistory;

/**
 * 生成修改记录工具类
 * @author tangjb
 * @version 1.0
 * @history
 */
public class RevisionHistoryGenUtil {
	
	/**
	 * method comments here
	 * @param 修改记录
	 * @param 注释开头的标识 如C是“//” sql是“--”
	 * @param 是否增加涉及系统
	 * @return
	 */
	public static String getAllRevisionHistoryInfo(List<RevisionHistory> logs,String begin){
		//生成修改字段字符串
		List<List<String>> modifyString = new ArrayList<List<String>>();
		for(RevisionHistory log: logs){
			List<String> modifyList = new ArrayList<String>();
			modifyList.add(RevisionHistoryGenUtil.delTime(log.getModifiedDate() == null ? "" : log.getModifiedDate()));
			modifyList.add(log.getVersion() == null ? "" : log.getVersion());
			modifyList.add(log.getOrderNumber() == null ? "" : log.getOrderNumber());
			modifyList.add(log.getModifiedBy() == null ? "" : log.getModifiedBy());
			modifyList.add(log.getCharger() == null ? "" : log.getCharger());
			modifyList.add(log.getModified() == null ? "" : log.getModified());
			modifyList.add(log.getModifiedReason() == null ? "" : log.getModifiedReason());
			modifyList.add(log.getComment() == null ? "" : log.getComment());
			modifyString.add(modifyList);
		}
		String allModifyString = RevisionHistoryGenUtil.writeCommon(
				new String[]{"修订时间", "交付项版本","修订单号",  "申请人", "负责人", "修改内容", "修改原因","备注"}, 
				new int[]{8,8,8,8,6,6,10,10}, 
				new int[]{RevisionHistoryGenUtil.MIX_TYPE,RevisionHistoryGenUtil.MIX_TYPE,RevisionHistoryGenUtil.MIX_TYPE,RevisionHistoryGenUtil.MIX_TYPE,RevisionHistoryGenUtil.MIX_TYPE,RevisionHistoryGenUtil.MIX_TYPE,RevisionHistoryGenUtil.MIX_TYPE,RevisionHistoryGenUtil.MIX_TYPE}, 
				modifyString, 3, begin);

		return allModifyString;
	}
	
	public static String writeCommon(String[] title,int[] blank,int type[],List<List<String>> data, int columnSpace,String prefix){
		int size = title.length;
		if(data == null || blank.length != size ||type.length != size) {
			return "";
		}
		if(columnSpace < 0) {
			columnSpace = 3;
		}
		for(int i = 0;i < size;i++){
			blank[i] += columnSpace;
		}
		for(List<String> o : data) {
			
			for(int i = 0;i < size;i++){
				blank[i] = getLenghMix(o.get(i),blank[i],columnSpace);
			}
			
		}
		StringBuilder res = new StringBuilder();
		//标题
		res.append(prefix);
		for(int i =0;i< size;i++){
			res.append(fillBlack(title[i],blank[i],CHN_TYPE, columnSpace));
		}
		res.append("\n");
		//修改记录内容
		for(List<String> o : data) {
			res.append(prefix);
			for(int i =0;i< size;i++){
				res.append(fillBlack(o.get(i),blank[i],type[i], columnSpace));
			}
			res.append("\n");
		}
		return res.toString();
	}
	
	
	
	/**
	 * 把时间部分删除,只留下日期部分
	 * method comments here
	 * @param date
	 * @return
	 */
	public static String delTime(String date) {
		if(date.equals("")) {
			return "";
		}
		int intDate = getIntFromString(date);
		if(intDate != -1) {
			String strTmp = "" + intDate ;
			if(strTmp.length() == 8) {
				String res = strTmp.substring(0, 4) + "-" + strTmp.substring(4, 6) 
				+ "-" + strTmp.substring(6, 8);
				return res;
			}
		}
		String[] dateArr = date.split(" ");
		if(dateArr.length == 2 && dateArr[0].length() >= 8) {
			return dateArr[0];
		}
		
		return date;
	}

	public static final int ENG_TYPE = 0;
	public static final int CHN_TYPE = 1;
	public static final int MIX_TYPE = 2;
	/**
	 * 填充空格 达到合适的长度
	 * <br>如果长度超过预订长度,返回字符串 + 3个空格
	 * method comments here
	 * @param str
	 * @param length 必须的长度
	 * @param type
	 * @return
	 */
	private static String fillBlack(String str, int length, int type, int columnSpace) {
		int curLength = str.length();
		int blackLenth = 0;
		if(ENG_TYPE == type) {
			blackLenth = length - curLength;
		}else if (CHN_TYPE == type) {
			blackLenth = length - curLength*2;
		}else if(MIX_TYPE == type) {
			blackLenth = length - getLengthWithChinese(str);
		}
		String result = "";
		if(blackLenth > -1) {
			result =  str + getBlack(blackLenth);
		}else {
			result =  str + getBlack(columnSpace);
		}
		return result.replaceAll("\n", "");
	}
	
	/** 空格String缓存,增加效率*/
	private static Map<Integer, String> blackCacheMap = new HashMap<Integer, String>();
	private static String getBlack(int num) {
		String res = blackCacheMap.get(num);
		if(res == null || res.equals("null")) {
			res = "";
			for(int i = 0; i < num; i++) {
				res +=" ";
			}
			blackCacheMap.put(num, res);
		}
		return res;
	}
	
	
	/**
	 * 按照英文算的,比较maxlength和当前的长度
	 * @param str
	 * @param maxlength
	 * @return
	 */
	private static int getlengh(String str,int maxlength, int columnSpace) {
		int curLengh = str.length() +columnSpace;
		if(maxlength > curLengh){
			return maxlength;
		}else{
			return curLengh;
		}
	}
	/**
	 * 按照中文算的,比较maxlength和当前的长度
	 * @param str
	 * @param maxlength
	 * @return
	 */
	private static int getlenghChinese(String str,int maxlength, int columnSpace) {
		int curLengh = str.length()*2 +columnSpace;
		if(maxlength > curLengh){
			return maxlength;
		}else{
			return curLengh ;
		}
	}
	/**
	 * 中英mix, 调用getLengthWithChinese方法
	 * method comments here
	 * @param str
	 * @param maxlength
	 * @return
	 */
	private static int getLenghMix(String str, int maxlength, int columnSpace){
		int curLengh = getLengthWithChinese(str) +columnSpace;
		if(maxlength > curLengh){
			return maxlength;
		}else{
			return curLengh ;
		}
	}
	
	
	/**
	 * 得到一个字符串的输出长度 中文占两个,英文占一个
	 * <br>前提是utf中汉字占3个字节
	 * <br> 如果有异常,返回长度的两倍
	 * @param str
	 * @return
	 */
	public static int getLengthWithChinese(String str) {
		int length = str.length();
		try {
			byte[] bb = str.getBytes("utf-8");
			int length2 = bb.length;
			int lengthRes = (length + length2)/2;
			if(lengthRes*2 == (length + length2)) {
				return lengthRes;
			}else {
				return length*2;
			}
		} catch (UnsupportedEncodingException e) {
			return length*2;
			
		}
	}
	
	
	/**
	 * 从一个日期的String中得到int形式(把非数字char去掉即可)
	 * 异常返回 -1
	 * @param str
	 * @return
	 */
	private static int getIntFromString(String str) {
		if(str ==null || str.equals("")) {
			return -1;
		}
		int res = 0;
		int index = 0;//res的长度 只能为8
		int length = str.length();
		for(int i = 0; i < length && index < 8; i++) {
			int num =  Character.digit(str.charAt(i), 10);
			if(!(num < 0)){
				res = res*10 + num;
				index++;
			}
		}
		if(index != 8) {
			return -1;
		}
		return res;
	}
}
