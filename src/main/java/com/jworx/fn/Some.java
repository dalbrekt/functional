package com.jworx.fn;

/**
 * Used when {@link Option} has a value.
 * @author dalbrekt
 *
 * @param <T> value type
 */
public class Some<T> extends Option<T> {
	private final T value;
	
	public Some(T value) {
		if(value == null) {
			throw new IllegalArgumentException("value must not be null");
		} 
		
		this.value = value;
	}
	
	public static <T> Some<T> of(T value) {
		return new Some<T>(value);
	}
	
	@Override
	public boolean hasValue() {
		return true;
	}

	@Override
	public T get() {
		return value;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + value + ")";
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null || other.getClass() != Some.class) {
			return false;
		}
		Some<?> that = (Some<?>) other;
		Object thatValue = that.get();
		return value.equals(thatValue);
	}
	
	@Override
	public int hashCode() {
		return 37 * value.hashCode();
	}

}
