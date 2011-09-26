package com.jworx.fn;

/**
 * Generic value container. It might have a value og given type or not.
 * 
 * @author dalbrekt
 * 
 * @param <T> value type
 */
public abstract class Option<T> {

	/**
	 * Checks if value is <code>null</code> or not.
	 * 
	 * @return <code>true</code> if not <code>null</code>, else
	 *         <code>false</code>
	 */
	public abstract boolean hasValue();

	/**
	 * Gets the value;
	 * 
	 * @return value
	 */
	public abstract T get();

	/**
	 * Gets the value if not <code>null</code>, else the given alternative is
	 * returned.
	 * 
	 * @param alternative value to return if <code>null</code>
	 * @return valueº
	 */
	public T getOrElse(T alternative) {
		return hasValue() ? get() : alternative;
	}

	/**
	 * Factory to create a proper instance of {@link Option}.
	 * 
	 * @param value container value
	 * @return
	 */
	public static <T> Option<T> of(T value) {
		if (value == null) {
			return new None<T>();
		} else {
			return new Some<T>(value);
		}
	}
}
