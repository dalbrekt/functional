package com.jworx.fn;

/**
 * Used when {@link Option} doesn't have a value.
 * @author dalbrekt
 *
 * @param <T> value type
 */
public class None<T> extends Option<T> {

	public static class NoneHasNoValue extends RuntimeException {
		private static final long serialVersionUID = 1L;
		
	}
	
	public None() {
	}
	
	@Override
	public boolean hasValue() {
		return false;
	}

	@Override
	public T get() {
		throw new NoneHasNoValue();
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
	
	@Override
	public boolean equals(Object other) {
		return (other == null || other.getClass() != None.class) ? false : true;
	}
	
	@Override
	public int hashCode() {
		return -1;
	};

}
