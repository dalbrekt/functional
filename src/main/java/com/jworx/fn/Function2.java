package com.jworx.fn;

/**
 * Generic interface for applying a function to given arguments.
 * 
 * @author dalbrekt
 *
 * @param <A1> type of argument 1
 * @param <A2> type of argument 2
 * @param <R> return type
 */
public interface Function2<A1, A2, R> {

	/**
	 * Applying function to given arguments.
	 * 
	 * @param arg1
	 * @param arg2
	 * @return 
	 */
	R apply(A1 arg1, A2 arg2);
}
