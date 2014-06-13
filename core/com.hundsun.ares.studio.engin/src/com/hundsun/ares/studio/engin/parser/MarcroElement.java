package com.hundsun.ares.studio.engin.parser;

import java.util.ArrayList;
import java.util.List;

public class MarcroElement implements IMacroElement{
	private static final String begin = "[";
	private static final String end = "]";
	private String keyword = "";
	private String flag = "";
	private String originalText = "";
	private int lineNo = -1;
	private String messge = null;
	private List<String> parameters = new ArrayList<String>();

	public MarcroElement(String originalText,int lineNo){
		this.originalText = originalText;
		this.lineNo = lineNo;
		parse();
	}
	
	public MarcroElement(String originalText,int lineNo,String message){
		this.originalText = originalText;
		this.lineNo = lineNo;
		this.messge = message;
		parse();
	}
	
	/**
	 * 获取关键字
	 * @return
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * 获取标志位
	 * @return
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * 获取宏参数
	 * @return
	 */
	public String[] getParameters() {
		return parameters.toArray(new String[0]);
	}
	
	/**
	 * 解析宏
	 */
    private void parse() {
        String trim = originalText.trim();
        if(trim.isEmpty()){
            keyword = "";
            return;
        }

        //解析出关键字和参数
        List<String> tList = new ArrayList<String>();
        parseToken(tList, originalText.trim());
        String s[] = tList.toArray(new String[0]);
//        String s[] = trim.substring(trim.indexOf("[") + 1, trim.length() - 1).split("\\]\\[");

        int flag_start = 0;

        //解析标志位
        if (trim.startsWith("<")){
            flag_start = trim.indexOf("<");
            if(trim.indexOf(">") != -1 && trim.indexOf(">") > flag_start){
            	flag = trim.substring(flag_start + 1, trim.indexOf(">"));
            }
        }
        keyword = s[0];
        parameters.clear();

        for (int i = 1; i < s.length; i++) {
            parameters.add(s[i]);
        }
     }
    
    public void parseToken(List<String> tlist,String text){
    	int sbegin =  text.indexOf(begin);
    	int send =  text.indexOf(end);
    	if(sbegin >= 0 && send > sbegin){
    		tlist.add(text.substring(sbegin+1,send));
    		if(send != (text.length() - 1)){
    			parseToken(tlist,text.substring(send + 1));
    		}
    	}
    }

    

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.uft.engin.parser.IMacroElement#getLineNo()
	 */
	@Override
	public int getLineNo() {
		return lineNo;
	}
	

	public static void main(String[] args) {
		MarcroElement tt = new MarcroElement("",1);
		List<String> test = new ArrayList<String>();
		tt.parseToken(test, "<>jjjjjjjj]");
		test.size();
	}

	@Override
	public String getAliasName() {
		if(null == messge){
			return keyword;
		}
		return messge;
	}
}
