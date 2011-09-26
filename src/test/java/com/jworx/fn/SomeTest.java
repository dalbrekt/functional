package com.jworx.fn;

import static org.junit.Assert.*;

import org.junit.Test;

public class SomeTest {


	@Test(expected = IllegalArgumentException.class)
	public void testSome() {
		Some<String> s = new Some<String>("1");
		assertTrue(s.hasValue());
		assertEquals("1", s.get());
		assertEquals("1", s.getOrElse("2"));
		s = Some.of(null);
	}
	
	@Test
	public void testEqualsHashCode() {
		Some<String> s1 = Some.of("1");
		Some<String> s2 = Some.of("1");
		Some<String> s3 = Some.of("2");
		
		assertTrue(s1.equals(s1));
		assertTrue(s1.equals(s2));
		assertFalse(s1.equals(s3));
		
		assertTrue(s1.hashCode() == s1.hashCode());
		assertTrue(s1.hashCode() == s2.hashCode());
		assertFalse(s1.hashCode() == s3.hashCode());
	}
	
	
}
