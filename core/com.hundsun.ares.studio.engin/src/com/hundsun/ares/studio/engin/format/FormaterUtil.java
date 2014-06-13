/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.engin.format;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 *代码格式化工具
 *
 */
public class FormaterUtil {

	public static String FORMATER_EXE_PATH = "format/Uncrustify.exe";

	public static String FORMATER_FOLDER_PATH = "format/";

	public static String FORMATER_EXE_WORK_PATH = FORMATER_FOLDER_PATH
			+ "Uncrustify.exe";

	public static String FORMATER_CONFIG_PATH = "format/default.cfg";

	public static String FORMATER_CONFIG_WORK_PATH = FORMATER_FOLDER_PATH
			+ "default.cfg";
	
	public static long FORMATER_LASTMODIFIED = 1224494894000l; //1218423569906l;//当前代码格式化工具版本号

	public static void copyFormaterExeToWorkSpace() {
		try {
			InputStream is = FormaterUtil.class.getClassLoader().getResource(
					FORMATER_EXE_PATH).openStream();
			File formaterExeFile = new File(FORMATER_EXE_WORK_PATH);
			if (!formaterExeFile.exists()) {
				File folder = new File(FORMATER_FOLDER_PATH);
				if (!folder.exists()) {
					folder.mkdir();
				}
				formaterExeFile.createNewFile();
			}
			DataInputStream read = new DataInputStream(new BufferedInputStream(
					is));
			DataOutputStream write = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(
							formaterExeFile)));
			byte[] temp = new byte[1024];//拷贝exe文件用字节流
			int i;
			while ((i = read.read(temp)) != -1) {
				write.write(temp);
			}
			read.close();
			write.close();
			is.close();
		} catch (IOException ioEx) {
			System.out.println(ioEx);
		}
	}

	public static void copyConfigToWorkSpace() {
		try {
			InputStream is = FormaterUtil.class.getClassLoader().getResource(
					FORMATER_CONFIG_PATH).openStream();
			File formaterConfigFile = new File(FORMATER_CONFIG_WORK_PATH);
			if (!formaterConfigFile.exists()) {
				File folder = new File(FORMATER_FOLDER_PATH);
				if (!folder.exists()) {
					folder.mkdir();
				}
				formaterConfigFile.createNewFile();
			}
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			String line = reader.readLine();
			FileWriter writer = new FileWriter(formaterConfigFile);
			while (line != null) {
				writer.write(line + "/n");
				line = reader.readLine();
			}
			writer.flush();
			writer.close();
			reader.close();
			is.close();
		} catch (IOException ioEx) {
			System.out.println(ioEx);
		}
	}

	
	public static void copyFormater() {

		File formaterExeFile = new File(FORMATER_EXE_WORK_PATH);
		
		if (!formaterExeFile.exists()) {
			copyFormaterExeToWorkSpace();
		}
		else if(formaterExeFile.lastModified() < FORMATER_LASTMODIFIED)//版本过期
		{
			System.out.println("exe版本号:" + formaterExeFile.lastModified());
			copyFormaterExeToWorkSpace();
			formaterExeFile.setLastModified(FORMATER_LASTMODIFIED);
		}
		File formaterConfigFile = new File(FORMATER_CONFIG_WORK_PATH);
		if (!formaterConfigFile.exists()) {
			copyConfigToWorkSpace();
		}
		else if(formaterConfigFile.lastModified() < FORMATER_LASTMODIFIED)//版本过期
		{
			System.out.println("config版本号:" + formaterConfigFile.lastModified());
			copyConfigToWorkSpace();
			formaterConfigFile.setLastModified(FORMATER_LASTMODIFIED);
		}
	}
	public static StringBuffer getFirstBlank(String origionStr) {
		 StringBuffer blank = new StringBuffer();
			for (int i = 0;i<origionStr.length();i++){
				if(origionStr.charAt(i) == ' ' || origionStr.charAt(i) == '\t'){
					blank.append(origionStr.charAt(i));
				}else{
					break;
				}
			}
			return blank;
		}

}
