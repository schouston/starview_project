package testdatapro;

import dataprocessing.*;
import static org.junit.Assert.*;

import org.junit.Test;


public class TestDataIn {

	@Test

	public final void test() {
		fail("Not yet implemented"); // TODO
	}

	@ Test
	public final void testHDataIn() {
		

		HDataIn in = new HDataIn();
		
		in.processCrossRefFile("idcrossref.txt");
		in.processFile("hipp_1_test.txt", "hipp_1_test_process.txt");
		System.out.println(in.ra);
		assertTrue(in.id.equals("70890"));
		assertTrue(in.mag == 11.010000);
		assertTrue(in.ra == 217.44894751);
		assertTrue(in.dec == -62.68135207);
		assertTrue(in.par == 772.330000);
		assertTrue(in.colourInd == 1.807);
		assertTrue(in.specType.equals("M5Ve"));
		assertTrue(in.ccdmID.equals("1"));
	
	}
	
	@ Test
	public final void testTDataIn() {
		

		TDataIn in = new TDataIn();
		
		in.processFile("ty_1_test.txt", "ty_1_test_process.txt");
		System.out.println(in.ra);
		assertTrue(in.id.equals("2472  1475 1"));
		assertTrue(in.mag == 12.12);
		assertTrue(in.ra == 121.09068479);
		assertTrue(in.dec == 32.88573532);
		assertTrue(in.par == 701.5);
		assertTrue(in.colourInd == 0.313);
		assertTrue(in.specType.equals("DEFAULT"));
		assertTrue(in.ccdmID.equals("0"));
	
	}
	@ Test
	public final void testGDataIn() {
		

		GDataIn in = new GDataIn();
		
		in.processFile("gl_1_test.txt", "gl_1_test_process.txt");
		System.out.println(in.ra);
		assertTrue(in.id.equals("NN 3001"));
		assertTrue(in.mag == 14.90);
		assertTrue(in.ra == 0.6671);
		assertTrue(in.dec == -34.2269);
		assertTrue(in.par == 75.2);
		assertTrue(in.colourInd == 0.46);
		assertTrue(in.specType.equals("DC9"));
		assertTrue(in.ccdmID.equals("0"));
	
	}
}
