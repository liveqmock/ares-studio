package com.hundsun.ares.studio.engin.token;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.engin.parser.MarcroElement;
import com.hundsun.ares.studio.engin.token.macro.IMacroToken;

public class CodeTokenFactory {

	private static CodeTokenFactory instance;
	
	private CodeTokenFactory(){
	}
	
	public static CodeTokenFactory getInstance(){
		synchronized (CodeTokenFactory.class) {
			if(null == instance){
				instance = new CodeTokenFactory();
			}
			return instance;
		}
	}
	
	public ICodeToken createCommentToken(String content){
		return new DefaultToken(content, ICodeToken.COMMENT);
	}
	
	public ICodeToken createResultSetToken(String content){
		return new DefaultResultSetToken(content, ICodeToken.CODE_TEXT);
	}
	
	public ICodeToken createCodeTextToken(String content){
		return new DefaultToken(content, ICodeToken.CODE_TEXT);
	}
	
	public ICodeToken createStringToken(String content){
		return new DefaultToken(content, ICodeToken.STRING);
	}

	
	public ICodeToken createTextWithParamsToken(String content,List<String> paramList){
		return new DefaultTextWithParamsToken(content, paramList);
	}
	
	public ICodeToken createMacroToken(String content,int lineNo){
		return new DefaultMacroToken(content,lineNo +1);
	}
	
	public ICodeToken createMacroToken(String content,int lineNo,String message){
		return new DefaultMacroToken(content,lineNo +1,message);
	}
	
	/**
	 * 创建伪代码token
	 * @param content
	 * @return
	 */
	public ICodeToken createPseudoCodeToken(String content){
		return new DefaultToken(content, ICodeToken.PseudoCode);
	}
}
class DefaultToken implements ICodeToken{

	protected String content;
	int type;
	
	public DefaultToken(String content,int type){
		//内容有可能只为换行符
		this.content = StringUtils.defaultIfEmpty(content, StringUtils.EMPTY);
		this.type = type;
	}
	
	@Override
	public String getContent() {
		return content;
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public String genCode(Map<Object, Object> context) {
		return content;
	}
}

class DefaultResultSetToken implements ICodeToken,IResultSetToken{

	protected String content;
	int type;
	
	public DefaultResultSetToken(String content,int type){
		this.content = content;
		this.type = type;
	}
	
	@Override
	public String getContent() {
		return content;
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public String genCode(Map<Object, Object> context) {
		return content;
	}
}

class DefaultMacroToken extends DefaultToken implements IMacroToken{

	MarcroElement element;
	public DefaultMacroToken(String content,int lineNo) {
		super(content, ICodeToken.MACRO);
		element = new MarcroElement(content,lineNo);
	}
	
	public DefaultMacroToken(String content,int lineNo,String message) {
		super(content, ICodeToken.MACRO);
		element = new MarcroElement(content,lineNo,message);
	}

	@Override
	public String getKeyword() {
		return element.getKeyword();
	}

	@Override
	public String getFlag() {
		return element.getFlag();
	}

	@Override
	public String[] getParameters() {
		return element.getParameters();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.uft.engin.parser.IMacroElement#getLineNo()
	 */
	@Override
	public int getLineNo() {
		return element.getLineNo();
	}

	@Override
	public String getAliasName() {
		return element.getAliasName();
	}
}

class DefaultTextWithParamsToken extends DefaultToken implements ITextWithParamsToken{
	List<String> paramList;
	public DefaultTextWithParamsToken(String content,List<String> paramList) {
		super(content, ICodeToken.CODE_TEXT);
		this.paramList = paramList;
	}

	
	@Override
	public String genCode(Map<Object, Object> context) {
		return content;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.engin.token.ISTDTextToken#getUsedParams()
	 */
	@Override
	public List<String> getUsedParams() {
		return this.paramList;
	}
	
}