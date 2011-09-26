package com.jworx.fn;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jworx.fn.None.NoneHasNoValue;

public class OptionTest {

	private List<Option<String>> names = null;
	
	@Before
	public void setUp() {
		names = new ArrayList<Option<String>>();
		names.add(Option.of("Foo"));
		names.add(Option.of((String)null));
		names.add(Option.of("Bar"));
	}
	
	@Test
	public void getOrElse() {
		String[] expected = {"Foo", "Unknown", "Bar"};
		
		for (int i = 0; i < expected.length; i++) {
			Option<String> name = names.get(i);
			assertEquals(expected[i], name.getOrElse("Unknown"));
		}
	}
	
	@Test
	public void hasValue() {
		String[] expected = {"Foo", null, "Bar"};
		
		for (int i = 0; i < expected.length; i++) {
			Option<String> name = names.get(i);
			if(name.hasValue()) {
				assertEquals(expected[i], name.get());
			}
		}
	}
	
	@Test(expected = NoneHasNoValue.class)
	public void getOfNone() {
		None<String> none = new None<String>();
		none.get();
	}
	
	
}
