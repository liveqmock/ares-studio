importPackage(java.lang);
importPackage(java.io);
importPackage(java.util);
importPackage(org.eclipse.jface.dialogs);

var project = res.getARESProject().getProject();
var resource = util.getSrcFolder(project).getFile("fragment.bizErrorMessage");

var metadataUtil = classLoader.loadClass("com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil").newInstance();
var stringUtil = classLoader.loadClass("org.apache.commons.lang.StringUtils").newInstance();

var sb = new StringBuffer();
var errorLogInfo = new StringBuffer();//错误提示信息
var warninginfo = "false";

sb.append("#The encoding of this file is UTF-8\r\n")

genAllErrorNoProperty(sb);
var fileName = resource.getLocation().toOSString();
var status = util.genFile(fileName , sb.toString(), errorLogInfo.toString(), "utf-8", true);

//生成资源文件并保存至对应文件夹
if(warninginfo.equals("true")){
	MessageDialog.openWarning(null,"警告","源文件内容为空，请填写后再进行操作！");
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
					"文件生成完毕！生成路径：" + fileName +
					"\r\n\r\n"+"需导出错误号定义文件的源文件中存在如下错误：\r\n" + errorLogInfo+
					"\r\n\r\n其余信息已正常生成相应文件");
		}
	}
}

function genAllErrorNoProperty(sb) {
	
	var errInfoItems = info.getItems().size();
	
	if(errInfoItems == 0){
		warninginfo = "true";
	}
	else{
		for(var i = 0 ; i < errInfoItems ; i++){
			
			var errItem = metadataUtil.decrypt(info.getItems().get(i), res);
			var refId = info.getItems().get(i).getRefId();//错误号引用
			
			var errorNo = errItem.getNo();//错误号编号
			var errorID = errItem.getName();//错误号ID
			
			var j = i+1;
			
			if(stringUtil.isBlank(refId)){	//判断错误号引用是否存在
				
				

				var errorMes = errItem.getMessage();//获取错误号信息
				
				if(stringUtil.isBlank(errorID)){
					sb.append("#错误：当前资源第["+j+"]条记录ID为空\r\n");
					errorLogInfo.append("错误：当前资源第["+j+"]条记录ID为空\r\n");
				}
				else if(stringUtil.isBlank(errorNo)){
					sb.append("#错误：ID为["+errorID+"]的错误号编号为空\r\n");
					errorLogInfo.append("错误：ID为["+errorID+"]的错误号编号为空\r\n");
				}else if(stringUtil.isBlank(errorMes)){
					sb.append("#错误：错误号编号["+errorNo+"]的错误信息为空\r\n");
					errorLogInfo.append("错误：错误号编号["+errorNo+"]的错误信息为空\r\n");
				}else{
					if(errorNo.search("^(\\-?)\\d*$")==0){
						//组装资源文件
						sb.append(errorNo+"=");
						sb.append(util.toUnicode(errorMes == null ? "" : errorMes, false));
						sb.append("\r\n");
					}else{
						sb.append("#错误：ID为["+errorID+"]的错误号编号["+errorNo+"]为非整数或非数字\r\n");
						errorLogInfo.append("错误：ID为["+errorID+"]的错误号编号["+errorNo+"]为非整数或非数字\r\n");
					}
				}
			}else{
				try {
					var errRefItem = metadataUtil.decrypt(info.getItems().get(i), res);
					
					if(null != errRefItem){
						
						var errorRefID = errRefItem.getName();//错误号ID
						var errorRefNO = errRefItem.getNo();//错误号编号

						sb.append("#引用资源：当前ID为["+errorID+"]的错误号资源引用资源对应的错误号编号为["+errorRefNO+"]\r\n");
					}else{
						
						sb.append("#错误：ID为["+errorID+"]引用的资源["+refId+"]不存在\r\n");
						errorLogInfo.append("#错误：ID为["+errorID+"]引用的资源["+refId+"]不存在\r\n");
					}
				} catch (e) {
					exception = "true";
				}
			}
		}
	}
}
