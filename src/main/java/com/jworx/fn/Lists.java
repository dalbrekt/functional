package com.jworx.fn;

import java.util.Arrays;

/**
 * Functional list module.
 * 
 */
public class Lists {
	public static interface List<T> {
		T head();

		List<T> tail();

		List<T> filter(Function1<T, Boolean> f);

		boolean isEmpty();

		public <T2> List<T2> map(Function1<T, T2> f);

		public <T2> T2 foldLeft(T2 seed, Function2<T2, T, T2> f);

		public <T2> T2 foldRight(T2 seed, Function2<T, T2, T2> f);

		public void foreach(Function1Void<T> f);
		
		public Integer size();

	}

	/**
	 * Functional list representation.
	 * 
	 * @author dalbrekt
	 * 
	 * @param <T> type of containing objects
	 */
	public static final class NonEmptyList<T> implements List<T> {
		private final T head;
		private final List<T> tail;

		protected NonEmptyList(T head, List<T> tail) {
			this.head = head;
			this.tail = tail;
		}

		public T head() {
			return head;
		}

		public List<T> tail() {
			return tail;
		}

		public boolean isEmpty() {
			return false;
		}

		public List<T> filter(Function1<T, Boolean> f) {
			if (f.apply(head())) {
				return list(head(), tail().filter(f));
			} else {
				return tail().filter(f);
			}
		}

		public <T2> List<T2> map(Function1<T, T2> f) {
			return list(f.apply(head()), tail.map(f));
		}

		public <T2> T2 foldLeft(T2 seed, Function2<T2, T, T2> f) {
			return tail().foldLeft(f.apply(seed, head()), f);
		}

		public <T2> T2 foldRight(T2 seed, com.jworx.fn.Function2<T, T2, T2> f) {
			return f.apply(head(), tail().foldRight(seed, f));
		};

		public void foreach(Function1Void<T> f) {
			f.apply(head());
			tail().foreach(f);
		}
		
		public Integer size() {
			return foldLeft(0, new Function2<Integer, T, Integer>() {

				public Integer apply(Integer arg1, T arg2) {
					return arg1 + 1;
				}
			});
		}

		@Override
		public boolean equals(Object other) {
			if (other == null || getClass() != other.getClass()) {
				return false;
			}
			List<?> that = (List<?>) other;
			return head().equals(that.head()) && tail().equals(that.tail());
		}

		@Override
		public int hashCode() {
			return 37 * (head().hashCode() + tail.hashCode());
		}

		@Override
		public String toString() {
			return "(" + head() + ", " + tail() + ")";
		}
	}

	public static class EmptyListHasNoHead extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}

	public static class EmptyListHasNoTail extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}

	public static final List<?> EMPTY = new List<Object>() {

		public List<Object> tail() {
			throw new EmptyListHasNoTail();
		}

		public boolean isEmpty() {
			return true;
		}

		public Object head() {
			throw new EmptyListHasNoHead();
		}

		@Override
		public String toString() {
			return "()";
		};

		public Lists.List<Object> filter(com.jworx.fn.Function1<Object, Boolean> f) {
			return this;
		};

		public <T2> Lists.List<T2> map(com.jworx.fn.Function1<Object, T2> f) {
			return emptyList();
		};

		public <T2> T2 foldLeft(T2 seed, com.jworx.fn.Function2<T2, Object, T2> f) {
			return seed;
		};

		public <T2> T2 foldRight(T2 seed, com.jworx.fn.Function2<Object, T2, T2> f) {
			return seed;
		};

		public void foreach(com.jworx.fn.Function1Void<Object> f) {
		};
		
		public Integer size() {return 0;};

	};

	/**
	 * Factory to create an empty list,
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> emptyList() {
		return (List<T>) EMPTY;
	}

	/**
	 * Factory that creates a non empty list
	 * 
	 * @param head head of list
	 * @param tail tail of list
	 * @return
	 */
	public static <T> List<T> list(T head, List<T> tail) {
		return new NonEmptyList<T>(head, tail);
	}

	/**
	 * Factory that creates a list from an array.
	 * 
	 * @param array source array
	 * @return
	 */
	public static <T> List<T> list(T... array) {
		List<T> list = emptyList();
		if (array.length > 0) {
			list = list(array[0], list(Arrays.copyOfRange(array, 1, array.length)));
		}
		return list;
	}

	/**
	 * Factory that creates a list from a java.util.List,
	 * @param sourceList
	 * @return
	 */
	public static <T> List<T> list(java.util.List<T> sourceList) {
		List<T> list = emptyList();
		if (sourceList.size() > 0) {
			list = list(sourceList.get(0), list(sourceList.subList(1, sourceList.size())));
		}
		return list;
	}
}
