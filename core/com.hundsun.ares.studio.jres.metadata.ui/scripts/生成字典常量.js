importPackage(java.lang);
importPackage(java.io);
importPackage(java.util);
importPackage(org.eclipse.jface.dialogs);

//脚本改造，将用户常量生成Java文件改造为数据字典生成Java
//2011-10-20 王彬

var begin = "\r\n"+"public class DictConstant {\r\n";

var end = "}\r\n";

var path = "constant";
var project = res.getARESProject().getProject();
var resource = util.getSrcFolder(project).getFile("constant\\DictConstant.java");
var fileName = resource.getLocation().toOSString();

var metadataUtil = classLoader.loadClass("com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil").newInstance();
var stringUtil = classLoader.loadClass("org.apache.commons.lang.StringUtils").newInstance();
var sb = new StringBuffer();
var errorLogInfo = new StringBuffer();//错误提示信息
var warninginfo = "false";//源文件为空预检查

sb.append("package "+path+";\r\n");
sb.append(begin);
sb.append("\r\n");
getAllConstants(sb);
sb.append(end);

var status = util.genFile(fileName, sb.toString(),  errorLogInfo.toString(), "utf-8", true);

if(warninginfo.equals("true")){//预检查源文件内容是否为空
	MessageDialog.openWarning(null,"警告", "源文件内容为空，请填写后再进行操作！");
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
				"\r\n\r\n"+"需导出JAVA源文件中存在如下错误：\r\n" + errorLogInfo + "\r\n\r\n其余信息已正常生成相应文件");
		}		
	}
}

function getAllConstants(sb){
	//获取数据字典列表信息，判断资源文件是否为空
	var dictTypeSize = info.getItems().size();
	if(0 == dictTypeSize){
		warninginfo = "true";
	}
	else{
		
		for(var i=0; i<dictTypeSize; i++){
			//读取资源文件中信息
			var dictType = metadataUtil.decrypt(info.getItems().get(i), res);
			var refID = info.getItems().get(i).getRefId();
			if(stringUtil.isBlank(refID)){
				
				var dictName = dictType.getName();
				var dictCNName = dictType.getChineseName();
				
				var dictItemSize = dictType.getItems().size();
				
				if(0 == dictItemSize){
				}else{
					for(var j=0; j<dictItemSize; j++){
						var dictItem = dictType.getItems().get(j);
						var dictConstName = dictItem.getConstantName();
						var dictValue = dictItem.getValue();
						var dictInfo = dictItem.getChineseName();
						if(stringUtil.isBlank(dictConstName) || stringUtil.isBlank(dictValue)){
						}else{							
							if(0==j){
								sb.append("\r\n\t/** "+dictName +" "+dictCNName+" **/\r\n");
							}
							sb.append("\tpublic static final String ");
							sb.append(dictConstName + " = ");							
							sb.append("\"" + dictValue + "\"" + ";");
							sb.append("\t// " + dictInfo + "\r\n");
						}
					}
				}

			}else{
				try{
					var dictRefTypeResPair = metadataUtil.resolve(info.getItems().get(i), res);
					if(null != dictRefTypeResPair){
						
						var dictRefType = dictRefTypeResPair.first;
						var dictRefName = dictRefType.getName();
						var dictRefCNName = dictRefType.getChineseName();
						var dicRefItems = dictRefType.getItems();
						var dictRefItemSize = dicRefItems.size();
						
						if(0 == dictRefItemSize){
						}
						else if(1 == dictRefItemSize){
							var dictRefItem = dicRefItems.get(0);
							var dictRefConstName = dictRefItem.getConstantName();
							var dictRefValue = dictRefItem.getValue();
							var dictRefInfo = dictRefItem.getChineseName();
							if(stringUtil.isBlank(dictRefConstName) || stringUtil.isBlank(dictRefValue)){
							}else{
								sb.append("\r\n\t/** 字典项["+info.getItems().get(i).getName() +"]存在引用  "+refID+" **/\r\n");
								sb.append("\t//public static final String ");
								sb.append(dictRefConstName + " = ");							
								sb.append("\"" + dictRefValue + "\"" + ";");
								sb.append("\t// " + dictRefInfo + "\r\n");
							}
						}
						else{
							for(var k=0; k<dictItemSize; k++){
								var dictRefItem = dicRefItems.get(k);
								var dictRefConstName = dictRefItem.getConstantName();
								var dictRefValue = dictRefItem.getValue();
								var dictRefInfo = dictRefItem.getChineseName();
								if(stringUtil.isBlank(dictRefConstName) || stringUtil.isBlank(dictRefValue)){
								}else{							
									if(0==k){
										sb.append("\r\n\t/** 字典项["+info.getItems().get(i).getName() +"]存在引用  "+refID+" **/\r\n");
									}
									sb.append("\t//public static final String ");
									sb.append(dictRefConstName + " = ");							
									sb.append("\"" + dictRefValue + "\"" + ";");
									sb.append("\t// " + dictRefInfo + "\r\n");
								}
							}
						}
						dictRefItemSize = 0;
					}else{
						dictERName = info.getItems().get(i).getName();
						errorLogInfo.append("错误：数据字典ID为["+dictERName+"]引用的["+refID+"]不存在\r\n");
						sb.append("\r\n");
					}
				}
				catch (e){
					dictERName = info.getItems().get(i).getName();
					errorLogInfo.append("错误：数据字典ID为["+dictERName+"]循环引用\r\n");
					sb.append("\r\n");
				}
			}
		}
	}
}//数据字典生成Java