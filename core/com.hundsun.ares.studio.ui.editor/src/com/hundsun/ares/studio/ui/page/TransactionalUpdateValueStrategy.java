package com.hundsun.ares.studio.ui.page;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.internal.databinding.BindingMessages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public class TransactionalUpdateValueStrategy extends UpdateValueStrategy {

	protected IStatus doSetResult;
	protected TransactionalEditingDomain domain;

	/**
	 * @param domain
	 */
	public TransactionalUpdateValueStrategy(TransactionalEditingDomain domain) {
		super();
		this.domain = domain;
	}

	@Override
	final protected IStatus doSet(final IObservableValue observableValue,
			final Object value) {
		doSetResult = Status.OK_STATUS;

		RecordingCommand command = new RecordingCommand(domain) {

			@Override
			protected void doExecute() {
				try {
					observableValue.setValue(value);
				} catch (Exception ex) {
					doSetResult = ValidationStatus
							.error(
									BindingMessages
											.getString(BindingMessages.VALUEBINDING_ERROR_WHILE_SETTING_VALUE),
									ex);
				}
			}

		};

		domain.getCommandStack().execute(command);

		return doSetResult;
	}
}