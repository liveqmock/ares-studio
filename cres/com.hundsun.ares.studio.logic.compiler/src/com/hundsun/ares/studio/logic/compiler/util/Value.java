/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.logic.compiler.util;

import org.eclipse.core.runtime.Assert;


/**
 * 表示实际代码中一个值，包含左值和右值
 * 
 * @author gongyf
 *
 */
public class Value {

	public static final String CLOB = "clob";
	
	/** 声明的形式 */
	private static final String FORMAT_DECLARE = "%s %s%s = %s; %s\n";
	
	/** 无初值的声明形式 */
	private static final String FORMAT_DECLARE_NO_INIT = "%s %s%s; %s\n";
	
	/**
	 * 是否是右值，当是右值时sourceText有效，其余是 prefix和name有效
	 */
	public boolean isRightValue = true;
	
	/**
	 * 前缀
	 */
	public String prefix = "";
	
	/**
	 * 变量名
	 * 
	 */
	public String name = "";
	
	/**
	 * 获取变量全名
	 * @return 变量名
	 */
	public String getVariableName() {
		return prefix + name;
	}
	
	/** 
	 * 直接可以写入代码的文本，如 <BR>
	 * lpResultSet->GetString("mmss"), "0"," ", 0 
	 * */
	public String sourceText = "";

	/**
	 * 表示RAW类型使用的长度代码文本
	 */
	public String sourceTextLength = "";
	
	/**
	 * 获取值在代码中的表现文本
	 * @return 真实代码
	 */
	public String getValue() {
		if (isRightValue) {
			return sourceText;
		} else {
			return getVariableName();
		}
	}
	
	public String getRawLengthValue() {
		Assert.isTrue(getTypeCategory() == TC_RAW, "必须是RAW类型才能调用");
		if (!isRightValue) {
			return (prefix.equals("v_") ? "vi_" : "pi_")  + name;
		}
		return sourceTextLength;
	}
	
	/**
	 * 数据类型，如int char* IPacker*
	 */
	public String dataType = "";
	
	/**
	 * 当是数组的时候，length >= 0
	 */
	public int arrayLength = -1;
	
	/**
	 * 初始值，用于声明的时候
	 */
	public String initValue = "";
	
	/**
	 * 利用一个值进行初始化
	 * 
	 */
	public Value initValue2 = null;
	
	/**
	 * 声明时候的注释
	 */
	public String initComment = "";
	
	/**
	 * 在声明后需要特殊处理的语句
	 */
	public String extendCode = "";
	
	// 浮点专用
	/** 有效位数 */
	public int p;
	/** 小数位精确数 */
	public int s;
	
	public static final int TC_CHAR = 0;
	public static final int TC_INT = 1;
	public static final int TC_DOUBLE = 2;
	public static final int TC_STRING = 3;
	// 2008年12月15日16:28:30 新类型 RAW
	public static final int TC_RAW = 4;
	
	public static final int TC_F2UNPACKER = 5;
	
	public static final int TC_F2PACKER = 6;
	
//	private Pattern P_F2UNPACKER_POINTER = Pattern.compile("if2unpacker\\s*\\*");
//	private Pattern P_F2PACKER_POINTER = Pattern.compile("if2packer\\s*\\*");
	
	/**
	 * 获得本值的类型
	 * @return 类型
	 */
	public int getTypeCategory() {
		if (dataType.matches("^char\\s+\\*$")) {
			return TC_STRING;
		} else if (dataType.equals("char")) {
			if (arrayLength < 0) {
				return TC_CHAR;
			} else {
				return TC_STRING;
			}
		} else if (dataType.equals("float") || dataType.equals("double")) {
			return TC_DOUBLE;
		} else if (dataType.equals(CLOB)) {
			return TC_RAW;
		} else if (dataType.toLowerCase().startsWith("if2unpacker")) { // FIXME 匹配可能出现问题，如用户未输入*，或者有多个*
			return TC_F2UNPACKER;
		} else if (dataType.toLowerCase().startsWith("if2packer")) { // FIXME 匹配可能出现问题，如用户未输入*，或者有多个*
			return TC_F2PACKER;
		}else {
			return TC_INT;
		}
	}
	
	/**
	 * 从给定的Value中复制类型信息
	 * @param v
	 */
	public void setTypeInfoFrom(Value v) {
		this.dataType = v.dataType;
		this.arrayLength = v.arrayLength;
		this.p = v.p;
		this.s = v.s;
	}
	
	/**
	 * 复制内容
	 * @param v
	 */
	public void copyFrom(Value v) {
		this.arrayLength = v.arrayLength;
		this.dataType = v.dataType;
		this.extendCode = v.extendCode;
		this.initComment = v.initComment;
		this.initValue = v.initValue;
		this.initValue2 = v.initValue2;
		this.isRightValue = v.isRightValue;
		this.name = v.name;
		this.p = v.p;
		this.prefix = v.prefix;
		this.s = v.s;
		this.sourceText = v.sourceText;
		this.sourceTextLength = v.sourceTextLength;
	}
	
	/**
	 * 获得一个赋值语句，从b复制到a
	 * 
	 * @param sb
	 * @param a
	 * @param b
	 */
	static public void writeCopy(StringBuffer sb, Value a, Value b) {
		
		Assert.isTrue(a.isRightValue == false);
		
		if (a.getTypeCategory() == TC_STRING) {
			// 字符串复制
			// sprintf(v_error_info, "%s", lpResultSet1330060->getString("error_info"));
			// hs_strncpy(a, b);
			sb.append( String.format("hs_strcpy(%1$s, %2$s);\n", a.getValue(), b.getValue()));
//			sb.append("sprintf(");
//			sb.append(a.getValue());
//			sb.append(", \"%s\", ");
//			sb.append(b.getValue());
//			sb.append(");\n");
		} else if (a.getTypeCategory() == TC_RAW){
			// 有2个变量
			sb.append(a.getRawLengthValue());
			sb.append(" = ");
			String valueString = b.getRawLengthValue();
			// 为空的时候就用0代替
			if (valueString.trim().isEmpty()) {
				sb.append("0");
			} else {
				sb.append(b.getRawLengthValue());
			}

			sb.append(";\n");
			
			sb.append(a.getValue());
			sb.append(" = ");
			sb.append(b.getValue());
			sb.append(";\n");
		} else {
			sb.append(a.getValue());
			sb.append(" = ");
			sb.append(b.getValue()); // TODO 不同类型的情况是否需要处理？
			sb.append(";\n");
		}
	}
	
	/**
	 * 写入一个申明语句
	 * @param sb
	 */
	public void writeDeclare(StringBuffer sb) {
		
		Assert.isTrue(isRightValue == false, "右值不可进行声明操作");
		String array = "";
		if (arrayLength > -1) {
			array = "[" + String.valueOf(arrayLength + 1) + "]";
		}
		
		String comment = null;
		if (initComment == null || initComment.trim().isEmpty()) {
			comment = "";
		} else {
			comment = "// " + initComment;
		}
		
		String theType = dataType;
		// 2008年12月15日16:37:36
		// RAW 类型其实需要2个变量协助,添加记录长度的变量
		if (getTypeCategory() == TC_RAW) {
			sb.append( String.format(FORMAT_DECLARE, "int", getRawLengthValue(), "", "0", "") );
			theType = "void *";
		}
		
		
		if (initValue == null || initValue.trim().isEmpty()) {
			// "%s %s%s;\n";
			sb.append( String.format(FORMAT_DECLARE_NO_INIT, theType, getVariableName(), array, comment) );
		} else {
			// "%s %s%s = %s;\n";
			sb.append( String.format(FORMAT_DECLARE, theType, getVariableName(), array, initValue, comment) );
		}
		
		if (initValue2 != null) {
			writeCopy(sb, this, initValue2);
		}
		
		sb.append(extendCode);

	}
	
	public boolean isConstant = false;
}
