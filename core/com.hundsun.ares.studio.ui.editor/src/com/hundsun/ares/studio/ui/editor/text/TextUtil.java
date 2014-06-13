package com.hundsun.ares.studio.ui.editor.text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;

/**
 * <p>
 * CreatedDate: 2008-2-19
 * </p>
 * 
 * @author sundl
 */
public class TextUtil {

	/**
	 * 取普通单词，字母或者下划线开头(规则与Java规则相同)
	 * @param document
	 * @param offset
	 * @return
	 */
	public static IRegion findCommonWord(IDocument document, int offset) {
		
		char c;
		int pre_offset = offset;
		int suf_offset = offset;

		try {
			c = document.getChar(offset);
			if (Character.isWhitespace(c)) {
				return null;
			}

			int length = document.getLength();

			while (pre_offset > 0) {
				pre_offset--;
				char ch = document.getChar(pre_offset);
			    if (Character.isJavaIdentifierStart(ch)) {
			    	continue;
			    } else {
			    	break;
			    }
			}
			pre_offset++;

			while (suf_offset < length - 1) {
				suf_offset++;
				char ch = document.getChar(suf_offset);
				if (Character.isJavaIdentifierPart(ch)) {
					continue;
				} else {
					break;
				}
				
			}
			return new Region(pre_offset, suf_offset - pre_offset);
		} catch (BadLocationException e) {
			e.printStackTrace();
			System.out.println(pre_offset);
			System.out.println(suf_offset);
		}

		return null;

	}
	
	public static IRegion findWord(IDocument document, int offset) {

		char c;
		int pre_offset = offset;
		int suf_offset = offset;

		try {
			c = document.getChar(offset);
			if (Character.isWhitespace(c)) {
				return null;
			}

			int length = document.getLength();

			while (pre_offset > 0) {
				pre_offset--;
				char ch = document.getChar(pre_offset);
				// if (Character.isJavaIdentifierPart(ch))
				if (ch != '[' && ch != '\n' && ch != '\r')
					continue;
				else
					break;
			}
			pre_offset++;

			while (suf_offset < length - 1) {
				suf_offset++;
				char ch = document.getChar(suf_offset);
				// if (Character.isJavaIdentifierPart(ch))
				if (ch != ']' && ch != '\n' && ch != '\r')
					continue;
				else
					break;
			}
			return new Region(pre_offset, suf_offset - pre_offset);
		} catch (BadLocationException e) {
			e.printStackTrace();
			System.out.println(pre_offset);
			System.out.println(suf_offset);
		}

		return null;

	}

	public static IRegion findHyperlink(IDocument document, int offset) {
		IRegion region = findWord(document, offset);
		if (region == null)
			return null;

		try {
			char pre = document.getChar(region.getOffset() - 1);
			char suf = document.getChar(region.getOffset() + region.getLength());

			if (pre == '[' && suf == ']') {
				return new Region(region.getOffset() - 1, region.getLength() + 2);
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 计算当前光标与前缀之间的区域,用于标准字段提示
	 * 
	 * @param document
	 *            当前文本
	 * @param offset
	 *            光标偏移量
	 * @return IRegion 区域对象
	 */
	public static IRegion getStdFieldPrefixRegion(IDocument document, int offset) {
		try {
			
			if (offset <= 1) // 文本开头处理（空文本中按热键）（空文本中输入“[”）
				return new Region(0, offset);
			char c;
			int pre_pointer = offset;
			c = document.getChar(--pre_pointer);// 字母序列从0开始,即偏移量-1为光标前一字母的序号
			while (c != '@' ) {
				pre_pointer--;
				if (pre_pointer <= 0)
					return new Region(0, 0);// 文本开头处理
				if ((c == ' ') || (c == '\n'))// 在“[”之前遇到分割符
				{
					pre_pointer = offset;
					break;
				}
				c = document.getChar(pre_pointer);
			}
			return new Region(pre_pointer, offset - pre_pointer);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static class IntegerString {
		public IntegerString(int num, String val) {
			this.num = num;
			this.value = val;
		};
		public int num;
		public String value;
	}
	
	/**
	 * 获得不相似程度，不相关返回-1,数字越高越不相似
	 * 
	 * @param str1
	 * @param input
	 * @return
	 */
	private static int getSimilarityLevel(String str1, String input) {
		
		int ret = 0;
		char data[] = input.toCharArray();
		int order = -1;
		for (int i = 0; i < data.length; i++) {
			int nowOrder = str1.indexOf(data[i],order + 1);
			if (nowOrder == -1) {
				return -1;
			} else {
				if (nowOrder == order + 1) {
					// 直接相连有优势，不相似度不计算后面的
				} else {
					ret += nowOrder;
				}
				order = nowOrder;


			}
		}
		
		return ret;
	}
	
	public static List<String> filter(List<String> allStrings, String prefix) {

		if (prefix == null || prefix.length() == 0) {
			return allStrings;
		}
				
		prefix = prefix.toLowerCase();
		
		List<IntegerString> temp = new ArrayList<IntegerString>();
		
		for (String m : allStrings) {
			String cm = m.toLowerCase();
			
			// 获得相似度
			int x = getSimilarityLevel(cm, prefix);
			int y = getSimilarityLevel( ChineseCharToEn.getAllFirstLetter(cm) , prefix);
			
			if (x == -1) {
				if (y == -1) {
					continue;
				} else {
					temp.add(new IntegerString(y, m));
				}
			} else {
				if (y == -1) {
					temp.add(new IntegerString(x, m));
				} else {
					temp.add(new IntegerString(y > x ? x : y, m));
				}
			}
		}
		
		Collections.sort(temp, new Comparator<IntegerString>(){

			@Override
			public int compare(IntegerString o1, IntegerString o2) {
				return o1.num - o2.num;
			}});
		
		List<String> ret = new ArrayList<String>();
		for (IntegerString integerString : temp) {
			ret.add(integerString.value);
		}
		
		return ret;
	}

	public static IRegion getGeneralPrefixRegion(IDocument document, int offset) {
		if (offset == 0)
			return new Region(0, offset);;
		int preOffset = offset - 1;// 字母序列从0开始,即偏移量-1为光标前一字母的序号
		try {
			
			while (preOffset > 0) {
				char c = document.getChar(preOffset);
				if (c == '@' || c == '[') break;
				if(c == '>' && document.getChar(preOffset-1) == '-') 
					return new Region(preOffset + 1, offset - preOffset - 1);
				if (c == '\r' || c == '\t' || c == ' ' || c == '\n') 
					//由于除了@和[外，还增加了智能提示公共函数，此时的IRegion获取方式  by wangxh
					return new Region(preOffset+1, offset - preOffset -1);
				--preOffset;
			}
			if(preOffset == 0){
				char c = document.getChar(0);
				if (c == '\r' || c == '\t' || c == ' ' || c == '\n') 
					//第0个字符是以上几个字符时，从第1个字符开始获取
					return new Region(1, offset-1);
			}
			return new Region(preOffset, offset - preOffset);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Region(offset, 0);
	}
	
	public static String getGeneralPrefix(IDocument document, int offset) {

		if (offset == 0)
			return "";
		int preOffset = offset - 1;// 字母序列从0开始,即偏移量-1为光标前一字母的序号
		try {
			
			while (preOffset > 0) {
				char c = document.getChar(preOffset);
				if (c == '@' || c == '[') break;
				if(c == '>' && document.getChar(preOffset-1) == '-') 
					return document.get(preOffset + 1, offset - preOffset - 1);;
				if (c == '\r' || c == '\t' || c == ' ' || c == '\n'){
					//由于除了@和[外，还增加了智能提示公共函数，此时也是要获取prefix的  by wangxh
					return document.get(preOffset + 1, offset - preOffset - 1);
				}
				--preOffset;
			}
			if(preOffset == 0){
				char c = document.getChar(0);
				if (c == '\r' || c == '\t' || c == ' ' || c == '\n')
					//第0个字符是以上几个字符时，从第1个字符开始获取
					return document.get(1, offset - 1);
			}
			return document.get(preOffset, offset - preOffset);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 获得输入标准字段的前缀文本
	 * 
	 * @param document
	 * @param offset
	 * @return
	 */
	public static String getStdFieldPrefix(IDocument document, int offset) {
		try {
			if (offset == 0)// 文本开头处理（空文本中按热键）
				return "";
			if (offset == 1)// 文本开头处理（空文本中输入“@”）
				return document.get(0, 1);
			offset--;// 字母序列从0开始,即偏移量-1为光标前一字母的序号
			char c;
			int pre_pointer = offset;
			c = document.getChar(pre_pointer);
			while (c != '@' ) {
				pre_pointer--;
				if (pre_pointer < 0)
					return "";// 文本开头处理
				if (c == ' ' || c == '\n')// 在“[”之前遇到分割符
					return "";
				c = document.getChar(pre_pointer);
			}

			String prefix = document.get(pre_pointer, offset - pre_pointer + 1);
			return prefix;
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得前缀字符串
	 * 
	 * @param document
	 *            当前文本
	 * @param offset
	 *            光标偏移量
	 * @return 前缀字符串(包括"[")
	 */
	public static String getFunctionPrefix(IDocument document, int offset) {
		try {
			if (offset == 0)// 文本开头处理（空文本中按热键）
				return "";
			if (offset == 1)// 文本开头处理（空文本中输入“[”）
				return document.get(0, 1);
			offset--;// 字母序列从0开始,即偏移量-1为光标前一字母的序号
			char c;
			int pre_pointer = offset;
			c = document.getChar(pre_pointer);
			while (c != '[' ) {
				pre_pointer--;
				if (pre_pointer < 0)
					return "";// 文本开头处理
				if (c == ' ' || c == '\n')// 在“[”之前遇到分割符
					return "";
				c = document.getChar(pre_pointer);
			}

			String prefix = document.get(pre_pointer, offset - pre_pointer + 1);
			return prefix;
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得后缀字符串
	 * 
	 * @param document
	 *            当前文本
	 * @param offset
	 *            光标偏移量
	 * @return 后缀字符串(包括"]")
	 */
	public static String getFunctionSuffix(IDocument document, int offset) {
		try {
			if (document.getLength() == offset)// 文本末处理
				return "";
			char c;
			int pre_pointer = offset;
			c = document.getChar(pre_pointer);
			while (c != ']') {
				pre_pointer++;
				if (pre_pointer >= document.getLength())
					return "";// 文本末处理
				if (c == ' ' || c == '\n')// 在“]”之前遇到分割符
					return "";
				c = document.getChar(pre_pointer);
			}

			String suffix = document.get(offset, pre_pointer - offset + 1);
			return suffix;
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 计算当前光标与前缀之间的区域
	 * 
	 * @param document
	 *            当前文本
	 * @param offset
	 *            光标偏移量
	 * @return IRegion 区域对象
	 */
	public static IRegion getFunctionPrefixRegion(IDocument document, int offset) {
		try {
			// System.out.println("document.getChar("+0+"):"+document.getChar(0));
			// System.out.println("offset:" + offset);
			// System.out.println("document length:" + document.getLength());
			if (offset <= 1) // 文本开头处理（空文本中按热键）（空文本中输入“[”）
				return new Region(0, offset);
			char c;
			int pre_pointer = offset;
			c = document.getChar(--pre_pointer);// 字母序列从0开始,即偏移量-1为光标前一字母的序号
			while (c != '[' ) {
				pre_pointer--;
				if (pre_pointer <= 0)
					return new Region(0, 0);// 文本开头处理
				if ((c == ' ') || (c == '\n'))// 在“[”之前遇到分割符
				{
					pre_pointer = offset;
					break;
				}
				c = document.getChar(pre_pointer);
			}
			return new Region(pre_pointer, offset - pre_pointer);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断当前是否是在一个宏后
	 * 
	 * @param document
	 *            当前文本
	 * @param offset
	 *            光标偏移量
	 * @return boolean(是宏返回true,否为false)
	 */
	public static boolean isAfterMacro(IDocument document, int offset) {
		try {
			if (offset < 1)
				return false;
			offset--;
			char c;
			int pre_pointer = offset;
			c = document.getChar(pre_pointer);
			while (c != '\n') {
				pre_pointer--;
				if (pre_pointer < 0)
					return false;
				if (c == ']')
				{
					return true;
				}
				
				c = document.getChar(pre_pointer);
			}
			return false;
		} catch (BadLocationException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 判断当前参数对应的宏名
	 * 
	 * @param document
	 *            当前文本
	 * @param offset
	 *            光标偏移量
	 * @return 宏名
	 */
	public static String getMacroName(IDocument document, int offset) {
		try {
			offset--;// 字母序列从0开始,即偏移量-1为光标前一字母的序号
			char c;
			int pre_pointer = offset;
			c = document.getChar(pre_pointer);
			while (c != '[') {// 找到第一个“[”
				pre_pointer--;
				if (pre_pointer < 0)
					return "";
				if (c == ' ' || c == '\n')// 在“[”之前遇到分割符
				{
					return "";
				}
				c = document.getChar(pre_pointer);
			}
			if (pre_pointer <= 1)// 文本开头处理
				return "";
			c = document.getChar(--pre_pointer);
			int end = pre_pointer;
			if (c != ']')
				return "";
			else {// 找到“[”之前的“]”
				c = document.getChar(--pre_pointer);
				while (c != '[') {// 找到第二个“[”
					pre_pointer--;
					if (pre_pointer < 0)
						return "";
					if (c == ' ' || c == '\n')// 在“[”之前遇到分割符
					{
						return "";
					}
					c = document.getChar(pre_pointer);
				}
				return document.get(pre_pointer + 1, end - pre_pointer - 1);
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
			return "";
		}
	}

}
