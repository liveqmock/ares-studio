/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.ui.refactoring;

import com.hundsun.ares.studio.core.IARESElement;

/**
 * 
 * @author sundl
 */
public abstract class ReorgPolicy implements IReorgPolicy {

	protected Object destination;
	protected IARESElement[] elements;
	
	public ReorgPolicy(IARESElement[] elements) {
		this.elements = elements;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgDestinationValidator#canChildrenBeDestinations(java.lang.Object)
	 */
	public boolean canChildrenBeDestinations(Object destination) {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgDestinationValidator#canElementBeDestination(java.lang.Object)
	 */
	public boolean canElementBeDestination(Object destinatioin) {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy#getAresElements()
	 */
	public IARESElement[] getAresElements() {
		return elements;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy#canEnable()
	 */
	public boolean canEnable() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy#getSaveMode()
	 */
	public int getSaveMode() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy#setDestination(java.lang.Object)
	 */
	public void setDestination(Object destination) {
		this.destination = destination;
	}

	public Object getDestination() {
		return this.destination;
	}
	
	/* (non-Javadoc)
	 * @see com.hundsun.ares.studio.internal.ui.refactoring.IReorgPolicy#getAresElementDestination()
	 */
	public IARESElement getAresElementDestination() {
		if (destination instanceof IARESElement) {
			return (IARESElement) destination;
		}
		return null;
	}

}
