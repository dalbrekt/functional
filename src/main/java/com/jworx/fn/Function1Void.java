package com.jworx.fn;

/**
 * Generic interface for applying a function to an argument.
 * @author dalbrekt
 *
 * @param <A> argument
 */
public interface Function1Void<A> {

	/**
	 * Applying function to given argument.
	 * @param arg  
	 */
	void apply(A arg);
}
