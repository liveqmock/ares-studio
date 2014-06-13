package com.hundsun.ares.studio.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {

	public static void main(String[] args) {
		ActionInfo ai = new ActionInfo();
//		ai.properties.get("id").equals(obj)
//		ai.other = "other";
		File file = new File("ttt.test");
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			//XStreamConverter.getInstance().write(fos, ai);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
