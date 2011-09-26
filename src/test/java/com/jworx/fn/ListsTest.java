package com.jworx.fn;

import org.junit.Test;

import static com.jworx.fn.Lists.*;
import static org.junit.Assert.assertEquals;


public class ListsTest {
	
	List<String> emptyStrings = emptyList();
	List<Long> emptyLongs = emptyList();
	
	@Test(expected = EmptyListHasNoHead.class)
	public void testHeadOnEmptyList() {
		emptyLongs.head();
	}
	
	@Test(expected = EmptyListHasNoTail.class)
	public void testTailOnEmptyList() {
		emptyLongs.tail();
	}
	
	@Test
	public void testTailOnNonEmptyList() {
		List<String> tail = list("one", list("two", emptyStrings)).tail();
		assertEquals(list("two", emptyStrings), tail);
	}
	
	@Test
	public void testHeadOnNonEmptyList() {
		String head = list("one", emptyStrings).head();
		assertEquals("one", head);
	}
	
	@Test
	public void testEmptyListsAreEqual() {
		assertEquals(emptyLongs, emptyStrings);
	}
	
	@Test
	public void testToString(){
		List<String> list = list("one", list("two", list("three", emptyStrings)));
		assertEquals("(one, (two, (three, ())))", list.toString());
	}
	
	@Test
	public void testFilter() {
		List<String> list = list("a1", list("b1", list("a2", list("b2", emptyStrings))));
		assertEquals(list("a1", list("a2", emptyStrings)), list.filter(new Function1<String, Boolean>() {
			
			public Boolean apply(String arg) {
				return arg.contains("a");
			}
		}));
	}
	
	@Test
	public void testMap() {
		List<Long> list = list(1L, list(2L, list(3L, emptyLongs)));
		assertEquals(list(2L, list(4L, list(6L, emptyLongs))), list.map(new Function1<Long, Long>() {
			
			public Long apply(Long arg) {
				return arg * 2;
			}
		}));
	}
	
	@Test
	public void testFoldLeft() {
		List<Long> list = list(1L, list(2L, list(3L, emptyLongs)));
		assertEquals(Long.valueOf(-5), list.foldLeft(1L, new Function2<Long, Long, Long>() {
			
			public Long apply(Long arg1, Long arg2) {
				return arg1 - arg2;
			}
		}));
	}
	
	@Test
	public void testFoldRight() {
		List<Long> list = list(1L, list(2L, list(3L, emptyLongs)));
		assertEquals(Long.valueOf(1), list.foldRight(1L, new Function2<Long, Long, Long>() {
			
			public Long apply(Long arg1, Long arg2) {
				return arg1 - arg2;
			}
		}));
	}
	
	int i;

	@Test
	public void testForeach() {
		List<Long> list = list(1L, list(2L, list(3L, emptyLongs)));
		i = 1;
		list.foreach(new Function1Void<Long>() {
			
			public void apply(Long arg) {
				assertEquals(Long.valueOf(i++), arg);
			}
		});
	}
	
	@Test
	public void testForeach2() {
		List<Long> list = list(1L, list(2L, list(3L, emptyLongs)));
		i = 1;
		list.foreach(new Function2Void<Long, Integer>() {
			
			public void apply(Long arg, Integer index) {
				assertEquals(Long.valueOf(index + 1), arg);
			}
		}, 0);
	}
}
