package com.hundsun.ares.studio.internal.ui.refactoring;

import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.util.ARESElementUtil;
import com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy.IMovePolicy;

public final class ReorgPolicyFactory {
	
	public static IMovePolicy createMovePolicy(IARESElement[] elements) {
		if (ARESElementUtil.elementsIsOfType(elements, IARESElement.ARES_RESOURCE)) {
			return new MovePolicy.MoveAresResourcePolicy(elements);
		} else if (ARESElementUtil.elementsIsOfType(elements, IARESElement.COMMON_MODULE)) {
			return new MovePolicy.MoveAresModulePolicy(elements);
		}
		return null;
	}
	
}