package testdatapro;
import dataprocessing.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestCrossRefs {

	@Test
	public final void testCrossRefID() {
		
		CrossRefID test = new CrossRefID("123", "456", "789");
		assertTrue(test.getHD().equals("123"));
		assertTrue(test.getHR().equals("456"));
		assertTrue(test.getHIP().equals("789"));
	}
	
	@Test
	public final void testCrossRefManagerPut() {
		
		HDataIn test = new HDataIn();
		System.out.println("jsf)");
		test.processCrossRefFile("cross_test.txt");
		CrossRefID[] array = test.getCrossRefArray();
		assertTrue(array[0].getHD().equals("HD 145647"));
		assertTrue(test.getCrossRefArray()[0].getHR().equals("HR 6035"));
		assertTrue(test.getCrossRefArray()[0].getHIP().equals("79332"));
	}
	
}