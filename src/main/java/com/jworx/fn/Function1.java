package com.jworx.fn;

/**
 * Generic interface for applying a function to an argument.
 * @author dalbrekt
 *
 * @param <A> argument
 * @param <R> return type
 */
public interface Function1<A, R> {

	/**
	 * Applying function to given argument.
	 * @param arg  
	 */
	R apply(A arg);
}
