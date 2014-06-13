/**
 * 源程序名称：OracleSpaceAdapter.java
 * 软件著作权：恒生电子股份有限公司 版权所有
 * 系统名称：JRES Studio
 * 模块名称：com.hundsun.ares.studio.jres.database.oracle.core
 * 功能说明：$desc
 * 相关文档：
 * 作者：王小恒
 */
package com.hundsun.ares.studio.jres.database.oracle.internal.service;

import java.util.Collections;
import java.util.List;

import com.hundsun.ares.studio.core.service.FastFindArrayList;
import com.hundsun.ares.studio.core.service.IKeyProvider;
import com.hundsun.ares.studio.jres.database.oracle.service.IOracleSpace;
import com.hundsun.ares.studio.jres.database.oracle.service.ITableSpace;
import com.hundsun.ares.studio.jres.database.oracle.service.ITableSpaceRelation;
import com.hundsun.ares.studio.jres.model.database.oracle.OracleSpaceResourceData;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpace;
import com.hundsun.ares.studio.jres.model.database.oracle.TableSpaceRelation;

/**
 * @author wangxh
 *
 */
public class OracleSpaceAdapter implements IOracleSpace {

	protected final OracleSpaceResourceData spaceResourceData;
	FastFindArrayList<String, ITableSpace> spaceList;
	FastFindArrayList<String, ITableSpaceRelation>spaceRelationList;
	
	
	public OracleSpaceAdapter(OracleSpaceResourceData spaceResourceData) {
		super();
		this.spaceResourceData = spaceResourceData;
	}

	/**
	 * @return the spaceList
	 */
	public FastFindArrayList<String, ITableSpace> getSpaceList() {
		if(spaceList == null){
			spaceList = new FastFindArrayList<String, ITableSpace>(new IKeyProvider<String, ITableSpace>() {

				@Override
				public String getKey(ITableSpace obj) {
					return obj.getName();
				}
			});
			for(TableSpace Space : spaceResourceData.getSpaces()){
				ITableSpace tableSpace = new TableSpaceAdapter(Space);
				spaceList.add(tableSpace);
			}
		}
		return spaceList;
	}

	/**
	 * @return the spaceRelationList
	 */
	public FastFindArrayList<String, ITableSpaceRelation> getSpaceRelationList() {
		if(spaceRelationList == null){
			spaceRelationList = new FastFindArrayList<String, ITableSpaceRelation>(new IKeyProvider<String, ITableSpaceRelation>() {

				@Override
				public String getKey(ITableSpaceRelation obj) {
					return obj.getMainSpace();
				}
			});
			for(TableSpaceRelation spaceRelation : spaceResourceData.getRelations()){
				ITableSpaceRelation tableSpaceRelation = new TableSpaceRelationAdapter(spaceRelation);
				spaceRelationList.add(tableSpaceRelation);
			}
		}
		return spaceRelationList;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleSpace#getTableSpaceList()
	 */
	@Override
	public List<? extends ITableSpace> getTableSpaceList() {
		return Collections.unmodifiableList(getSpaceList());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleSpace#getTableSpace(java.lang.String)
	 */
	@Override
	public ITableSpace getTableSpace(String name) {
		return getSpaceList().find(name);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleSpace#getTableSpaceRelationList()
	 */
	@Override
	public List<? extends ITableSpaceRelation> getTableSpaceRelationList() {
		return Collections.unmodifiableList(getSpaceRelationList());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.oracle.service.IOracleSpace#getTableSpaceRelation(java.lang.String)
	 */
	@Override
	public ITableSpaceRelation getTableSpaceRelation(String name) {
		return getSpaceRelationList().find(name);
	}

	@Override
	public String getName() {
		return spaceResourceData.getName();
	}

	@Override
	public String getChineseName() {
		return spaceResourceData.getChineseName();
	}

	@Override
	public String getDescription() {
		return spaceResourceData.getDescription();
	}

}
