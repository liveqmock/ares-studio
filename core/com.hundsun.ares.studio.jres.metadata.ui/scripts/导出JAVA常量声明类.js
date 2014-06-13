importPackage(java.lang);
importPackage(java.io);
importPackage(java.util);
importPackage(org.eclipse.jface.dialogs);

var begin = 
"\r\n" +
"public class Constant {\r\n";

var end = 
"}\r\n";

var path = "constant";
var project = res.getARESProject().getProject();
var resource = util.getSrcFolder(project).getFile("constant\\Constant.java");
var fileName = resource.getLocation().toOSString();

var metadataUtil = classLoader.loadClass("com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil").newInstance();
var stringUtil = classLoader.loadClass("org.apache.commons.lang.StringUtils").newInstance();

var sb = new StringBuffer();
var errorLogInfo = new StringBuffer();//错误提示信息
var warninginfo = "false";//源文件为空预检查

var exception = "false";//捕获循环引用异常

sb.append("package "+path+";\r\n");
sb.append("import java.util.Date;\r\n");
sb.append(begin);
sb.append("\r\n");
getAllConstants(sb);
sb.append(end);

var status = util.genFile(fileName, sb.toString(),  errorLogInfo.toString(), "utf-8", true);

if(warninginfo.equals("true")){//预检查源文件内容是否为空
	MessageDialog.openWarning(null,"警告", "源文件内容为空，请填写后再进行操作！");
}else{
	
	if(exception.equals("true")){//捕获循环引用的异常
		MessageDialog.openError(null,"错误信息提示", "需要生成Java文件的源文件中存在循环引用");
		
	}else{
		
		if (!status.isOK()) {
			MessageDialog.openError("生成失败", status.getMessage());
		} else {
			if(errorLogInfo.toString().equals("")){
				resource.refreshLocal(0, null);
				MessageDialog.openInformation(null,"生成成功", "文件生成完毕！生成路径：" + fileName);
			}else{
				resource.refreshLocal(0, null);
				MessageDialog.openWarning(null,"生成成功但源文件存在错误", 
						"文件生成完毕！生成路径：" +fileName+
						"\r\n\r\n"+"需导出JAVA常量声明类的源文件中存在如下错误：\r\n" + 
						errorLogInfo + "\r\n\r\n其余信息已正常生成相应文件");
			}
		}
	}
}

function getAllConstants(sb){
	
	var constantItem = info.getItems().size();
	
	if(constantItem == 0){
		warninginfo = "true";//源文件为空改变警告信息值
	}else{
		for(var i = 0 ; i < constantItem ; i++){	
			
			var constItem = metadataUtil.decrypt(info.getItems().get(i), res);
			var constDesc = constItem.getDescription();//备注信息
			var constName = constItem .getName();//常量ID
			var refId = info.getItems().get(i).getRefId();
			
			var j = i+1;

			//1、先判断用户常量ID是否为空
			//2、然后判断是否存在引用
			
			if(stringUtil.isBlank(constName)){
				sb.append("\t//错误：当前资源第["+j+"]条记录ID为空\r\n");
				errorLogInfo.append("错误：当前资源第["+j+"]条记录ID为空\r\n");
			}else {
				if(stringUtil.isBlank(refId)){
					
					
					var constValue = constItem .getValue();//常量值
					var constLen = constItem.getLength();//长度
					var constpre = constItem.getPrecision();//精度
					
					var constDataType = constItem.getDataType();//标准数据类型
					var constDataTypeId = constItem.getDataTypeId();//标准数据类型
					
					if(stringUtil.isBlank(constDataTypeId)){ //判断引用的标准数据类型是否为空
						
						sb.append("\t//错误：用户常量ID为["+constName+"]引用的标准数据类型为空\r\n");
						errorLogInfo.append("错误：用户常量ID为["+constName+"]引用的标准数据类型为空\r\n");
						
					}else{
						
						var constType = constDataType.getRealType("java", constLen, constpre);// 获取对应的标准数据类型的真实的Java数据类型
						
						if(stringUtil.isBlank(constValue) ){
							sb.append("\t//错误：用户常量ID为["+constName+"]的常量值为空\r\n");
							errorLogInfo.append("错误：用户常量ID为["+constName+"]的常量值为空\r\n");
						}else{
							if(stringUtil.isBlank(constType)){
								sb.append("\t//错误：用户常量ID为["+constName+"]的标准数据类型无对应真实JAVA数据类型\r\n");
								errorLogInfo.append("错误：用户常量ID为["+constName+"]的标准数据类型无对应真实JAVA数据类型\r\n");
							}else if(constType.equals("String")){
								sb.append("\tpublic static final ");
								sb.append(constType + "  ");
								sb.append(constName + " = ");
								sb.append("\"" + constValue + "\"" + ";");
								sb.append("\t// " + constDesc + "\r\n");
								
							}else if(constType.equals("Date")){
								sb.append("\tpublic static final ");
								sb.append(constType + "  ");
								sb.append(constName + " = ");
								sb.append("new Date(\"" + constValue + "\");");
								sb.append("\t// " + constDesc + "\r\n");
								
							}else{
								sb.append("\tpublic static final ");
								sb.append(constType + "  ");
								sb.append(constName + " = ");
								sb.append(constValue + ";");				
								sb.append("\t// " + constDesc + "\r\n");
							}			
						}			
					}
				}else{
					try{
						var constRefItem = metadataUtil.decrypt(info.getItems().get(i), res);
						
						if(constRefItem != null){
							
							var constName = constRefItem.first.getName();//常量ID
							var constValue = constRefItem.first.getValue();//常量值
							var constLen = constRefItem.first.getLength();//长度
							var constpre = constRefItem.first.getPrecision();//精度
							var constDataType = constRefItem.first.getDataType();//标准数据类型
							
							if(stringUtil.isBlank(constDataType)){
								sb.append("\t//错误：用户常量ID为["+constName+"]引用的标准数据类型为空\r\n");
								errorLogInfo.append("错误：用户常量ID为["+constName+"]引用的标准数据类型为空\r\n");
							}else{
								var constType = constDataType.getRealType("java", constLen, constpre);// 获取实际的Java数据类型
								
								if(stringUtil.isBlank(constValue) ){
									sb.append("\t//错误：用户常量ID为["+constName+"]的常量值为空\r\n");
									errorLogInfo.append("错误：用户常量ID为["+constName+"]的常量值为空\r\n")
								}	else{
									
									if(constType.equals("String")){
										sb.append("\tpublic static final ");
										sb.append(constType + "  ");
										sb.append(constName + " = ");
										sb.append("\"" + constValue + "\"" + ";");
										sb.append("\t// " + constDesc + "\r\n");
										
									}else if(constType.equals("Date")){
										sb.append("\tpublic static final ");
										sb.append(constType + "  ");
										sb.append(constName + " = ");
										sb.append("new Date(\"" + constValue + "\");");
										sb.append("\t// " + constDesc + "\r\n");
										
									}else{
										sb.append("\tpublic static final ");
										sb.append(constType + "  ");
										sb.append(constName + " = ");
										sb.append(constValue + ";");				
										sb.append("\t// " + constDesc + "\r\n");
										
									}
								}
							}
						}else{
							var constName = info.getItems().get(i).getName();//常量ID
							sb.append("\t//错误：常量ID为["+constName+"]的用户常量引用["+refId+"]不存在\r\n");
							errorLogInfo.append("错误：常量ID为["+constName+"]的用户常量引用["+refId+"]不存在\r\n");
						}
					}catch(e){
						exception = "true";
					}
				}
			}
		}
	}
}
