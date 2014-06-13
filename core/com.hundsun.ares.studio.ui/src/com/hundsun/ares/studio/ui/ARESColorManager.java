package com.hundsun.ares.studio.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ARESColorManager {
	
	public static final RGB RED = new RGB(255, 0, 0);
	public static final RGB GREEN = new RGB(0, 255, 0);
	public static final RGB YELLOW = new RGB(0, 255, 255);
	public static final RGB BLACK = new RGB(0, 0, 0);
	public static final RGB BLUE = new RGB(0, 0, 255);
	public static final RGB KEYWORD = new RGB(127, 0, 85);
	public static final RGB STRING = new RGB(84, 0, 255);
	public static final RGB DEFAULT = BLACK;
	public static final RGB COMMENT = new RGB(63, 127, 95);
	public static final RGB CHARACTOR = new RGB(255,0,0);
	public static final RGB NUMBER = new RGB(255,128,0);
	public static final RGB STDFILED = new RGB(60,100,200);
	public static final RGB WHITE = new RGB(255,255,255);
	public static final RGB COLUMN = new RGB(10, 36, 106);
	public static final RGB CONST = new RGB(10,160,120);
	
	private Map<RGB, Color> colors = new HashMap<RGB, Color> ();
	
	public Color getColor(RGB rgb) {		
		Color color = colors.get(rgb);
		
		if(color == null) {
			color = new Color(Display.getCurrent(), rgb);
			colors.put(rgb, color);
		}
		
		return color;
	}
	
	public void dispose() {		
		Iterator<Color> i = colors.values().iterator();
		
		while(i.hasNext()) {
			Color color = i.next();
			if (!color.isDisposed())
				color.dispose();
		}
	}

}
