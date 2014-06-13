package com.hundsun.ares.studio.logic.ui.refactor;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.atom.constants.IAtomResType;
import com.hundsun.ares.studio.atom.ui.refactor.AtomMoveParticipant;
import com.hundsun.ares.studio.logic.constants.ILogicResType;


/**
 * CRES逻辑函数,逻辑服务中监听对象资源移动并作出响应的类
 * @author liaogc
 *
 */
public class LogicMoveParticipant extends AtomMoveParticipant {

	public LogicMoveParticipant() {
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.atom.ui.refactor.AtomMoveParticipant#getDesc()
	 */
	@Override
	protected String getDesc() {
		return "CRES逻辑中对象类型的参数引用更新";
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.atom.ui.refactor.AtomMoveParticipant#getResTypes()
	 */
	@Override
	protected List<String> getResTypes() {
		List<String> resTypes = new ArrayList<String>();
		resTypes.add(ILogicResType.LOGIC_FUNCTION);
		resTypes.add(ILogicResType.LOGIC_SERVICE);
		return resTypes;
	}
	
	
}
