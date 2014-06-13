package com.hundsun.ares.studio.internal.ui.refactoring;

public interface IReorgDestinationValidator {

	/**
	 * Is it possible, that destination contains valid destinations
	 * as children?
	 *
	 * @param destination the destination to verify
	 * @return true if destination can have valid destinations
	 */
	public boolean canChildrenBeDestinations(Object destination);

	/**
	 * Is it possible, that the given kind of destination is a target for
	 * the reorg?
	 *
	 * @param destination the destination to verify
	 * @return true if possible
	 */
	public boolean canElementBeDestination(Object destinatioin);
}
