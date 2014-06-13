package com.hundsun.ares.studio.ui.refactoring;

import org.eclipse.core.runtime.OperationCanceledException;

public interface IConfirmQuery {
	public boolean confirm(String question) throws OperationCanceledException;
	public boolean confirm(String question, Object[] elements) throws OperationCanceledException;
}
