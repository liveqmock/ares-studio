package com.hundsun.ares.studio.jres.model.database.util;

import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.core.IARESProjectProperty;
import com.hundsun.ares.studio.core.IARESResource;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.jres.database.constant.IDBConstant;
import com.hundsun.ares.studio.jres.database.constant.IDatabaseResType;
import com.hundsun.ares.studio.jres.metadata.constant.IMetadataRefType;
import com.hundsun.ares.studio.jres.model.database.ColumnType;
import com.hundsun.ares.studio.jres.model.database.TableColumn;
import com.hundsun.ares.studio.jres.model.database.TableKey;
import com.hundsun.ares.studio.jres.model.database.TableResourceData;
import com.hundsun.ares.studio.jres.model.database.key_type;
import com.hundsun.ares.studio.jres.model.metadata.StandardField;
import com.hundsun.ares.studio.model.reference.ReferenceInfo;
import com.hundsun.ares.studio.reference.ReferenceManager;

/**
 * @author sundl
 *
 */
public class DatabaseUtil {
	
	private static Logger logger = Logger.getLogger(DatabaseUtil.class);

	/**
	 * 给定的项目是否允许数据库中使用非标准字段
	 * @param project
	 * @return
	 */
	public static boolean isNonStdFiledAllowed(IARESProject project) {
		IARESProjectProperty property;
		try {
			property = project.getProjectProperty();
			return property.getBoolean(IDBConstant.USE_NON_STD_FIELD, false);
		} catch (ARESModelException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static TableResourceData getTableData(IARESProject project, String fullName) {
		IARESResource res = ARESElementUtil.findResource(project, fullName, IDatabaseResType.Table);
		if (res != null) {
			try {
				return res.getInfo(TableResourceData.class);
			} catch (ARESModelException e) {
				logger.error(e);
			}
		}
		return null;
	}
	
	/**
	 * 获取表字段的业务数据类型，根据标准字段或非标准字段有不同的取法。
	 * @param project
	 * @param col
	 * @return
	 */
	public static String getBizType(IARESProject project, TableColumn col) {
		ReferenceManager manager = ReferenceManager.getInstance();
		if (col.getColumnType() == ColumnType.STD_FIELD) {
			ReferenceInfo ref = manager.getFirstReferenceInfo(project, IMetadataRefType.StdField, col.getName(), true);
			if (ref != null) {
				StandardField sf = (StandardField) ref.getObject();
				return sf.getDataType();
			}
		} else if (col.getColumnType() == ColumnType.NON_STD_FIELD) {
			return col.getDataType();
		}
		return StringUtils.EMPTY;
	}
	
	public static TableColumn findColumn(TableColumn column, TableResourceData table) {
		for (TableColumn c : table.getColumns()) {
			if (StringUtils.equals(column.getName(), c.getName()))
				return c;
		}
		return null;
	}
	
	public static TableColumn findColumn(String name, TableResourceData table) {
		for (TableColumn c : table.getColumns()) {
			if (StringUtils.equals(c.getName(), name))
				return c;
		}
		return null;
	}
	
	public static boolean contains(List<?> list, Object object) {
		for (Object obj : list) {
			if (DatabaseUtil.equals(obj, object))
				return true;
		}
		return false;
	}
	
	public static boolean equals(Object obj1, Object obj2) {
		if (obj1 instanceof EObject && obj2 instanceof EObject) {
			if( EcoreUtil.equals((EObject) obj1, (EObject)obj2)) {
				return true;
			};
		} else {
			if (ObjectUtils.equals(obj1, obj2)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 找到表的主键定义
	 * @param table 
	 * @param mark 标志位
	 * @return
	 */
	public static TableKey findPrimaryKey(TableResourceData table, String mark) {
		if (table == null)
			return null;
		mark = StringUtils.trimToEmpty(mark);
		for (TableKey key : table.getKeys()) {
			if (key.getType() == key_type.PRIMARY && StringUtils.equals(key.getMark(), mark)) {
				return key;
			}
		}
		return null;
	}
	
}
