/**
* <p>Copyright: Copyright (c) 2012</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.engin.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hundsun.ares.studio.core.ARESProblem;
import com.hundsun.ares.studio.core.IARESProblem;
import com.hundsun.ares.studio.engin.constant.ITokenConstant;
import com.hundsun.ares.studio.engin.token.ITokenEvent;
import com.hundsun.ares.studio.engin.token.ITokenListener;

/**
 * @author lvgao
 *
 */
public class EnginMessageListener  implements ITokenListener{

	List<IARESProblem> problemList = new ArrayList<IARESProblem>();
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.uft.engin.token.IUFTTokenListener#handle(com.hundsun.ares.studio.uft.engin.token.IUFTTokenEvent)
	 */
	@Override
	public void handle(ITokenEvent event) {
		if(StringUtils.equals(event.getKey(), ITokenConstant.EVENT_ENGINE_WARNNING)){
			IARESProblem problem = ARESProblem.createWaring();
			problem.setMessage(event.getData().toString());
			problemList.add(problem);
		}
		
	}
	
	/**
	 * 获取
	 * @return
	 */
	public IARESProblem[] getARESProblem(){
		return problemList.toArray(new IARESProblem[0]);
	}
	
	public void clear(){
		problemList.clear();
	}

}
