package com.hundsun.ares.studio.cres.extend.ui.text;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;

public class CRESTextUtil {

	// 2012-12-11 sundl 增加冒号和分号
	// NOTE： 这里使用这种黑名单的逻辑不是很好，因为大多数情况下符号都是不允许的，以后的实现可以考虑换成白名单
	private static char[] LEFT_CHARS = new char[] {'@', '[', '=', ',', '.', '\"', ':', ';','<'};
	private static char[] RIGHT_CHARS = new char[] {']', '=', ',', '.', '\"', ':', ';','>'};

	/**
	 * 获取鼠标位置上的一个符合UFT规则的单词
	 * @param document
	 * @param offset
	 * @return
	 */
	public static IRegion findWord(IDocument document, int offset) {
		char c;
		int pre_offset = offset;
		int suf_offset = offset;

		try {
			c = document.getChar(offset);
			if (Character.isWhitespace(c)) {
				return null;
			}

			char preCh = (char) 0;
			
			int length = document.getLength();
			while (pre_offset >= 0) {
				char ch = document.getChar(pre_offset);
				if (!ArrayUtils.contains(LEFT_CHARS, ch)
						&& !Character.isWhitespace(ch)) {
					pre_offset--;
					continue;
				}
				else {
					preCh = ch;
					break;
				}
			}
			pre_offset++;

			while (suf_offset < length) {
				char ch = document.getChar(suf_offset);
				if (!ArrayUtils.contains(RIGHT_CHARS, ch)
						&& !Character.isWhitespace(ch)
						&& !(preCh == '@' && ch == ')') )		{
					suf_offset++;
					continue;
				}
				else
					break;
			}
			suf_offset--;
			
			return new Region(pre_offset, suf_offset - pre_offset + 1);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		return null;
	}
}
