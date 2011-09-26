package com.jworx.fn;

/**
 * Generic interface for applying a function to arguments.
 * @author dalbrekt
 *
 * @param <A1> type of argument 1
 * @param <A2> type of argument 2
 */
public interface Function2Void<A1, A2> {

	/**
	 * Applying function to given arguments.
	 * @param arg1
	 * @param arg2
	 */
	void apply(A1 arg1, A2 arg2);
}
