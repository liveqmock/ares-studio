/**
 * 源程序名称：SqlFormater.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.procedure.ui.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;

import SQLinForm_200.SQLForm;

/**
 * @author liaogc
 *
 */
public class ProcedureFormater {

	
	/**
	 * 格式化sql
	 * @param sql
	 * @param 数据库取值为"Any SQL","SQL Server","DB2/UDB","MSAccess","Sybase","Informix","MYSQL","PostgreSQL","Oracle"
	 * @return
	 */
	public static String formatSql(String sql, String language) {
		
		StringBuffer sql1 = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();

		ByteArrayInputStream inputStream2 = new ByteArrayInputStream(sql.toString().getBytes());
		InputStreamReader reader2 = new InputStreamReader(inputStream2);
		BufferedReader br2 = new BufferedReader(reader2);
		 String line = "";
		boolean find = false;
		try {
			while ((line = br2.readLine()) != null) {
				if(find){
					sql2.append(line).append("\r\n");
					continue;
				}
				if(line!=null && line.indexOf("create or replace ")>-1){
					int index = line.indexOf("create or replace ") + "create or replace ".length();
					sql1.append(line.substring(0,index)).append("");
					sql2.append(line.substring(index)).append("\r\n");
					find = true;
					
				}else{
					sql1.append(line).append("\r\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			try {
				reader2.close();
				inputStream2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	

		StringBuffer newSb = new StringBuffer();
		
		String tempsql = getSqlForm(language).formatSQLAsString(sql2.toString());
		if (tempsql != null) {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(tempsql.getBytes());
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(reader);
			  line = "";
			try {
				while ((line = br.readLine()) != null) {
					if (newSb != null && "/".equals(line.trim())) {
						newSb.append(line.trim() + "\r\n");
					} else {
						newSb.append(line + "\r\n");
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

				try {
					reader.close();
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return sql1.toString()+newSb.toString();
	}
	
	private static SQLForm getSqlForm(String language) {
		SQLForm form = new SQLForm();
		form.setCase(false, false);
		form.setGraphLevel(false);
		form.setSuppressSpace(true);
		form.setQuoteCharacter("'");
		form.setSuppressEmptyLine(false);
		form.setFormatLanguage("SQL");
		if(StringUtils.isBlank(language)){
			form.setSourceSQLLanguage("Oracle");
		}else{
			form.setSourceSQLLanguage(language);
		}
		
//		form.setBracketSpaces("noSpacesAroundBracket");
//		form.setCommaSpaces("oneSpaceAfterComma");
//		form.setEqualSpaces("oneSpaceAroundEqual");
		form.setSmallSQLWidth(120);
//		form.setPageWidth(80);
		form.setAndOrIndention(true);
		form.setInitialIndentation(0);
		form.setIndention(2, true);
		form.setNumCommas(4);//每4个一列
		//form.setAlignmentAs(false);
		form.setLinebreakKeyword(false);//关键字后面不换行
		return form;
	}
	
	

}
