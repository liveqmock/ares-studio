/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

/**
 * ARES 模型中失败的异常。它包含一个{@link #IARESModelStatus}对象描述异常的原因。
 * @author sundl
 */
public class ARESModelException extends CoreException{

	private static final long serialVersionUID = -41881713227186306L;

	CoreException nestedCoreException;
	
	public ARESModelException(Throwable e, int code) {
		this(new ARESModelStatus(code, e));
	}

	public ARESModelException(CoreException exception) {
		super(exception.getStatus());
		this.nestedCoreException = exception;
	}

	public ARESModelException(IARESModelStatus status) {
		super(status);
	}
	
	public Throwable getException() {
		if (this.nestedCoreException == null) {
			return getStatus().getException();
		} else {
			return this.nestedCoreException;
		}
	}
	
	/**
	 * 返回描述具体异常信息的对象。
	 * @return 描述具体异常信息的对象
	 */
	public IARESModelStatus getARESModelStatus() {
		IStatus status = getStatus();
		if (status instanceof IARESModelStatus) {
			return (IARESModelStatus)status;
		} else {
			return new ARESModelStatus(nestedCoreException);
		}
	}
	
	public boolean isDoesNotExist() {
		IARESModelStatus aresModelStatus = getARESModelStatus();
		return aresModelStatus != null && aresModelStatus.isDoesNotExist();
	}	

	/**
	 * Prints this exception's stack trace to the given print stream.
	 *
	 * @param output the print stream
	 * @since 3.0
	 */
	public void printStackTrace(PrintStream output) {
		synchronized(output) {
			super.printStackTrace(output);
			Throwable throwable = getException();
			if (throwable != null) {
				output.print("Caused by: "); //$NON-NLS-1$
				throwable.printStackTrace(output);
			}
		}
	}

	/**
	 * Prints this exception's stack trace to the given print writer.
	 *
	 * @param output the print writer
	 * @since 3.0
	 */
	public void printStackTrace(PrintWriter output) {
		synchronized(output) {
			super.printStackTrace(output);
			Throwable throwable = getException();
			if (throwable != null) {
				output.print("Caused by: "); //$NON-NLS-1$
				throwable.printStackTrace(output);
			}
		}
	}
	
	/*
	 * Returns a printable representation of this exception suitable for debugging
	 * purposes only.
	 */
	public String toString() {
		StringBuffer buffer= new StringBuffer();
		buffer.append("ARES Model Exception: "); //$NON-NLS-1$
		if (getException() != null) {
			if (getException() instanceof CoreException) {
				CoreException c= (CoreException)getException();
				buffer.append("Core Exception [code "); //$NON-NLS-1$
				buffer.append(c.getStatus().getCode());
				buffer.append("] "); //$NON-NLS-1$
				buffer.append(c.getStatus().getMessage());
			} else {
				buffer.append(getException().toString());
			}
		} else {
			buffer.append(getStatus().toString());
		}
		return buffer.toString();
	}
}
