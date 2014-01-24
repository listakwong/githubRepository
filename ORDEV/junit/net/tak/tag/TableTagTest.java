package net.tak.tag;

import org.junit.Before;
import org.junit.Test;

public class TableTagTest {
	
	@Before
	public void testBefore() {
		
	}
	
	@Test
	public void testDoEndTag() {
		
	}
	@Test
	public void testMath(){
		
		System.out.println(4/10);
		System.out.println((4*10)%10);
		System.out.println(Math.round( 4%10));
		System.out.println(4/10 + Math.round( 4%10));
		
		String d = "";
		System.out.println("ddd" +d.toLowerCase());
	}
	
}

