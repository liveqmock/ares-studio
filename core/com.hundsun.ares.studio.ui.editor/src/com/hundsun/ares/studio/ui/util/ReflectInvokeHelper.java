/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 利用java反射调用get和set方法的工具类
 * @author maxh
 */
public class ReflectInvokeHelper {
	Object model;
	String fieldName;
	
	Field field;
	Class classOfModel;
	Class classOfField;
	Method getMethod;
	Method setMethod;
	
	
	public ReflectInvokeHelper(Object model,String fieldName) throws Exception{
		this.fieldName = fieldName;
		setModel(model);
	}
	
	public Object getModel() {
		return model;
	}
	
	public void setModel(Object model) {
		this.model = model;
		if(model != null){
			classOfModel = model.getClass();
			try {
				createContent();
			} catch (Exception e) {
				System.out.print("\n/*************************************************************************/");
				System.out.print("\n数据绑定错误：ReflectInvokeHelper\n模型：" + model.getClass().getName() + "\n字段："+fieldName);
				System.out.print("\n/*************************************************************************/\n");
				e.printStackTrace();
			}
		}
	}
	
	void createContent() throws Exception{
		field = findField(classOfModel,fieldName);
		if(field != null){
			classOfField = field.getType();
			getMethod = findMethod(classOfModel,"get" + conversion(fieldName));
			if(getMethod == null && (classOfField == Boolean.class || classOfField == boolean.class)){
				if(fieldName.startsWith("is")){
					getMethod = findMethod(classOfModel,fieldName);
					if(getMethod == null){
						getMethod = findMethod(classOfModel,"is" + conversion(fieldName));
					}
				}else{
					getMethod = findMethod(classOfModel,"is" + conversion(fieldName));
				}
			}
			setMethod = findMethod(classOfModel,"set" + conversion(fieldName),classOfField);
			if(setMethod == null && (classOfField == Boolean.class || classOfField == boolean.class) && fieldName.startsWith("is") ){
				setMethod = findMethod(classOfModel,"set" + conversion(fieldName.substring(2)),classOfField);
			}
		}
	}
	
	
	/**
	 * 把第一个字母转换成大写
	 * @param s
	 * @return
	 */
	public static String conversion(String s){
		String begin = s.substring(0,1);
		String end = s.substring(1,s.length());
		return begin.toUpperCase() + end;
	}
	
	/**
	 * 寻找字段 包括父类中的
	 * @param classBeFind
	 * @param name
	 * @return
	 */
	public static Field findField(Class classBeFind,String name){
		Field field = null;
		while(true){
			try {
				field = classBeFind.getDeclaredField(name);
			} catch (Exception e) {
				field = null;
			}
			if(field != null){
				return field;
			}
			classBeFind = classBeFind.getSuperclass();
			if(classBeFind == null){
				return null;
			}
		}
	}
	
	/**
	 * 寻找方法 包括父类中的
	 * @param classBeFind
	 * @param name
	 * @param paras
	 * @return
	 */
	public static Method findMethod(Class classBeFind,String name,Class...paras){
		Method method = null;
		while(true){
			try {
				method = classBeFind.getDeclaredMethod(name,paras);
			} catch (Exception e) {
				method = null;
			}
			if(method != null){
				return method;
			}
			classBeFind = classBeFind.getSuperclass();
			if(classBeFind == null){
				return null;
			}
		}
	}
	
	/**
	 * 调用模型的get方法
	 * @return
	 */
	public Object invokeGetMothod(){
		if(getMethod == null){
			return null;
		}
		try {
			return getMethod.invoke(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 调用模型的set方法
	 * @param para
	 */
	public void invokeSetMothod(Object para){
		if(setMethod == null){
			return;
		}
		try {
			if(para instanceof String){
				if(classOfField == Boolean.class  || classOfField == boolean.class){
					if(para.toString().equalsIgnoreCase("true") || para.toString().equalsIgnoreCase("false")){
						setMethod.invoke(model, Boolean.valueOf((String)para));
					}
					return;
				}
				if(classOfField == Integer.class || classOfField == int.class){
					setMethod.invoke(model, Integer.valueOf(numberBlank((String)para)));
					return;
				}
				if(classOfField == Long.class || classOfField == long.class){
					setMethod.invoke(model, Long.valueOf(numberBlank((String)para)));
					return;
				}
				if(classOfField == Float.class || classOfField == float.class){
					setMethod.invoke(model, Float.valueOf(numberBlank((String)para)));
					return;
				}
				if(classOfField == Double.class || classOfField == double.class){
					setMethod.invoke(model, Double.valueOf(numberBlank((String)para)));
					return;
				}
				if(classOfField == Character.class || classOfField == char.class){
					char[] ch = ((String)para).trim().toCharArray();
					if(ch.length > 0){
						setMethod.invoke(model, ch[0]);
					}
					return;
				}
			}
			setMethod.invoke(model, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	String numberBlank(String para){
		if(para.trim().length() == 0){
			return "0";
		}
		return para;
	}
}
