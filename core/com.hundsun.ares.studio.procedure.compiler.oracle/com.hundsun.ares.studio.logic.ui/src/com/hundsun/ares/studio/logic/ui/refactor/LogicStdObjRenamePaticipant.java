package com.hundsun.ares.studio.logic.ui.refactor;

import java.util.ArrayList;
import java.util.List;

import com.hundsun.ares.studio.atom.ui.refactor.AtomStdObjRenamePaticipant;
import com.hundsun.ares.studio.logic.constants.ILogicResType;

public class LogicStdObjRenamePaticipant extends AtomStdObjRenamePaticipant {
	@Override
	protected String getDesc() {
		return "CRES逻辑中对象类型的参数引用更新";
	}
	
	@Override
	protected List<String> getResTypes() {
		List<String> resTypes = new ArrayList<String>();
		resTypes.add(ILogicResType.LOGIC_FUNCTION);
		resTypes.add(ILogicResType.LOGIC_SERVICE);
		return resTypes;
	}
}
