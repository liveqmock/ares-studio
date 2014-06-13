/**
* <p>Copyright: Copyright (c) 2011</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.jres.model.chouse;

import org.apache.commons.lang.ObjectUtils;

import com.hundsun.ares.studio.core.IARESProject;
import com.hundsun.ares.studio.jres.database.oracle.constant.IOracleConstant;
import com.hundsun.ares.studio.jres.database.oracle.internal.service.OracleTableColumnAdapter;
import com.hundsun.ares.studio.jres.model.database.TableColumn;

/**
 * @author yanwj06282
 *
 */
public class Stock3ColumnAdapter extends OracleTableColumnAdapter implements IStock3Column{

	final protected StockColumnProperty property;
	/**
	 * @param column
	 */
	public Stock3ColumnAdapter(TableColumn column,IARESProject project) {
		super(column,project);
		property = (StockColumnProperty) ObjectUtils.defaultIfNull(column
				.getData2().get(IOracleConstant.TABLE_DATA2_KEY),
				ChouseFactory.eINSTANCE.createStockColumnProperty());
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.jres.database.stock3.service.IStock3Column#getFlag()
	 */
	@Override
	public String getFlag() {
		
		return property.getFlag();
	}

}
