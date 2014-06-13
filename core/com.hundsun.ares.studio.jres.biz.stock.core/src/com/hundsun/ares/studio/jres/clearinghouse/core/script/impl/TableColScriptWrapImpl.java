/**
 * 源程序名称：TableColumnScriptWrapImpl.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.ui
 * 功能说明：$desc
 * 相关文档：
 * 作者：
 */
package com.hundsun.ares.studio.jres.clearinghouse.core.script.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EObject;

import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.service.DataServiceManager;
import com.hundsun.ares.studio.jres.KeyWordEntity;
import com.hundsun.ares.studio.jres.KeyWordEscapeUtil;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.metadata.core.script.impl.StandardFieldScriptWrapImpl;
import com.hundsun.ares.studio.jres.metadata.service.IBusinessDataType;
import com.hundsun.ares.studio.jres.metadata.service.IMetadataService;
import com.hundsun.ares.studio.jres.metadata.service.IStandardField;
import com.hundsun.ares.studio.jres.metadata.service.ITypeDefaultValue;
import com.hundsun.ares.studio.jres.model.chouse.util.DataBaseMetadataUtil;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.ForeignKey;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableIndex;
import com.hundsun.ares.studio.jres.model.database.TableIndexColumn;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.key_type;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.jres.model.metadata.TypeDefaultValue;
import com.hundsun.ares.studio.jres.model.metadata.decrypt.DeStandardField;
import com.hundsun.ares.studio.jres.model.metadata.util.MetadataUtil;
import com.hundsun.ares.studio.jres.script.api.database.ITableColForergnKeyScriptWrap;
import com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap;
import com.hundsun.ares.studio.jres.script.api.metadata.IStandardFieldScriptWrap;
import com.hundsun.ares.studio.jres.script.base.CommonScriptWrap;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author yanwj06282
 *
 */

public class TableColScriptWrapImpl extends CommonScriptWrap<TableColumn> implements ITableColScriptWrap {

	IStandardField field;
	ITypeDefaultValue defv;
	
	public TableColScriptWrapImpl(TableColumn column,IARESResource res) {
		super(column ,res);
		
		if (getOriginalInfo().getColumnType()==ColumnType.STD_FIELD) {
			ReferenceInfo stdInfo = ReferenceManager.getInstance().getFirstReferenceInfo(res.getARESProject(), IMetadataRefType.StdField, column.getFieldName(), true);
			if(stdInfo!=null){
				StandardField std = (StandardField) stdInfo.getObject();
				field = MetadataUtil.decrypt((StandardField) std, res);
			}
		}
		ReferenceInfo devInfo = ReferenceManager.getInstance().getFirstReferenceInfo(res.getARESProject(), IMetadataRefType.DefValue, column.getDefaultValue(), true);
		if (devInfo != null) {
			TypeDefaultValue tdv = (TypeDefaultValue) devInfo.getObject();
			defv = MetadataUtil.decrypt((TypeDefaultValue) tdv, res);
		}
	}
	
	@Override
	public boolean isPrimaryKey() {
		EObject obj = getOriginalInfo().eContainer();
		if (obj instanceof TableResourceData) {
			for (TableKey key : ((TableResourceData) obj).getKeys()) {
				if (key.getType() == key_type.PRIMARY) {
					for (TableColumn tc : key.getColumns()) {
						if (StringUtils.equals(tc.getFieldName(), getName())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public String getEscapeSql(String type){
		return escape(type ,true);
	}
	
	@Override
	public String getSql(String type) {
		return escape(type ,false);
	}

	@Override
	public String getName() {
		return getOriginalInfo().getName();
	}

	@Override
	public String[] getForeignkey() {
		List<String> foreignKeys = new ArrayList<String>();
		for (ForeignKey fk : getOriginalInfo().getForeignkey()){
			foreignKeys.add(fk.getFieldName());
		}
		return foreignKeys.toArray(new String[foreignKeys.size()]);
	}

	@Override
	public String getstdFieldChineseName() {
		if (field != null && getOriginalInfo().getColumnType()==ColumnType.STD_FIELD) {
			return field.getChineseName();
		}else if(getOriginalInfo().getColumnType()==ColumnType.NON_STD_FIELD){
			return StringUtils.defaultIfBlank(getOriginalInfo().getChineseName(), "");
		}
		return StringUtils.EMPTY;
	}

	@Override
	public ITableColForergnKeyScriptWrap[] getForeignKey() {
		List<ITableColForergnKeyScriptWrap> fks = new ArrayList<ITableColForergnKeyScriptWrap>();
		for (ForeignKey fk : getOriginalInfo().getForeignkey()){
			fks.add(new TableColPKScriptWrapImpl(fk, resource));
		}
		return fks.toArray(new ITableColForergnKeyScriptWrap[0]);
	}

	@Override
	public String getMark() {
		return getOriginalInfo().getMark();
	}

	@Override
	public boolean isUnique() {
		EObject obj = getOriginalInfo().eContainer();
		if (obj instanceof TableResourceData) {
			for (TableKey key : ((TableResourceData) obj).getKeys()) {
				if (key.getType() == key_type.UNIQUE) {
					for (TableColumn tc : key.getColumns()) {
						if (StringUtils.equals(tc.getFieldName(), getName())) {
							return true;
						}
					}
				}
			}
			for (TableIndex index : ((TableResourceData) obj).getIndexes()) {
				if (index.isUnique()) {
					for (TableIndexColumn tic : index.getColumns()) {
						if (StringUtils.equals(tic.getColumnName(), getName())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean isNullable(){
		return getOriginalInfo().isNullable();
	}
	
	public String getDefaultValue(String type){
		String defaultValue = "";
		//读取默认值，字段默认值为第一优先级
		if (StringUtils.isNotBlank(getOriginalInfo().getDefaultValue())) {
			if (defv != null) {
				defaultValue = defv.getValue(type);
			}else {
				defaultValue = getOriginalInfo().getDefaultValue();
			}
		}else{//只有在列的字段类型为标准字段类型且标准字段存在的情况下才取标准字段对应的默认值
			if(field != null && getOriginalInfo().getColumnType()==ColumnType.STD_FIELD){
				IBusinessDataType bizType = field.getDataType();
				if(bizType.getDefaultValue() != null){
					ITypeDefaultValue tv = bizType.getDefaultValue();
					defaultValue = tv.getValue(type);//字段默认值为空，类型默认值为第二优先级
				}
			}else  if(this.getOriginalInfo().getColumnType()==ColumnType.NON_STD_FIELD){//非标准字段的情况下
				 Map<String,String> infos = DataBaseMetadataUtil.getInfoByBusinessName(this.getOriginalInfo().getDataType(), resource.getARESProject(), type);
				 defaultValue =  infos.get(DataBaseMetadataUtil.KEY_VALUE);
			}
		}
		return defaultValue;
	}
	
	private String escape(String type ,boolean isEscape){

		String oracleStdType = "";
		String defaultValue = getDefaultValue(type);
		if(field != null && this.getOriginalInfo().getColumnType()==ColumnType.STD_FIELD){
			IBusinessDataType bizType = field.getDataType();
			oracleStdType = bizType.getRealType(type);
		}else if(this.getOriginalInfo().getColumnType()==ColumnType.NON_STD_FIELD){
			oracleStdType = getRealDataType(type);//根据非标准字段的规则去获得对应的真实数据库类型
		}
		if (isEscape) {
			defaultValue = StringUtils.replace(defaultValue,"'","''");
		}
		
		StringBuffer sb = new StringBuffer();
		String column_name = KeyWordEscapeUtil.getEscapeByNameAndType(getOriginalInfo().getName(),type);
		sb.append(fixLength(column_name,30,' '));
		sb.append(" ");
		if(StringUtils.isNotBlank(oracleStdType)){
			sb.append(fixLength(oracleStdType,15,' ') + " ");
		}
		
		if(StringUtils.isNotBlank(defaultValue)){
			sb.append("DEFAULT ");
			sb.append(fixLength(defaultValue,10,' ') + " ");
		}
		if(isPrimaryKey() || !getOriginalInfo().isNullable()){//允许空
			sb.append("NOT NULL");
		}
		/* 2012/11/30 zhuyf 唯一约束在脚本中生成
		 * if(column.isUnique() && !column.isPrimaryKey()){
			sb.append(" unique");
		}*/
		return sb.toString();
	}
	
	private String fixLength(String str, int len, char fill) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(str);
		
		for(int i = str.length();  i < len; i++){
			buffer.append(fill);
		}
		return buffer.toString();
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.wrap.ITableColumnScriptWrap#getRealDataType()
	 */
	@Override
	public String getRealDataType(String type) {
		String oracleStdType = "";
		if(field != null && this.getOriginalInfo().getColumnType()==ColumnType.STD_FIELD){
			IBusinessDataType bizType = field.getDataType();
			oracleStdType = bizType.getRealType(type);
		}else if(this.getOriginalInfo().getColumnType()==ColumnType.NON_STD_FIELD){//非标准字段的情况下
			 Map<String,String> infos = DataBaseMetadataUtil.getInfoByBusinessName(this.getOriginalInfo().getDataType(), resource.getARESProject(), type);
			 oracleStdType =  infos.get(DataBaseMetadataUtil.KEY_REAL_TYPE);
		}
		return oracleStdType;
	}

	@Override
	public IStandardFieldScriptWrap getstdField() {
		if(this.getOriginalInfo().getColumnType()==ColumnType.STD_FIELD){
			IMetadataService service = DataServiceManager.getInstance().getService(
					resource.getARESProject(), IMetadataService.class);
			IStandardField isf = service.getStandardField(getOriginalInfo().getName());
			if (isf != null && isf instanceof DeStandardField) {
				return new StandardFieldScriptWrapImpl(isf, resource);
			}	
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap#setComments(java.lang.String)
	 */
	@Override
	public void setComments(String comments) {
		getOriginalInfo().setComments(comments);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		getOriginalInfo().setName(name);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap#setBizType(java.lang.String)
	 */
	@Override
	public void setBizType(String name) {
		// TODO Auto-generated method stub
		if(getOriginalInfo().getColumnType() == ColumnType.NON_STD_FIELD){//非标字段
			getOriginalInfo().setDataType(name);
		}
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.script.api.database.ITableColScriptWrap#getBizType()
	 */
	@Override
	public String getStrBizType() {
		if(getOriginalInfo().getColumnType() == ColumnType.STD_FIELD){//非标字段
			return getstdField().getStrBizDataType();
		}else{
			return getOriginalInfo().getDataType();
		}
	}
	
	
	
}
