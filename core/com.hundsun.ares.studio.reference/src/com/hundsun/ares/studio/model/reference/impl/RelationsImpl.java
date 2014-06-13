/**
* <p>Copyright: Copyright (c) 2013</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.model.reference.impl;

import com.hundsun.ares.studio.model.reference.IRelations;
import com.hundsun.ares.studio.model.reference.RelationOperator;
import com.hundsun.ares.studio.model.reference.RelationRefTypesMapEntry;

/**
 * @author liaogc
 *
 */
public class RelationsImpl implements IRelations{
	public RelationOperator relationOperator = new RelationOperator(new RelationRefTypesMapEntry());
	@Override
	public RelationOperator getRelationOperator() {
		return relationOperator;
	}
}
