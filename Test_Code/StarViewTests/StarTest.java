package Testing;

import static org.junit.Assert.*;


import org.junit.Test;


import mapprojections.*;
import java.awt.geom.Point2D;


public class StarTest {

	//@Test
/*	public void test() {
	
		fail("Not yet implemented");
	}*/
	
	@Test
	//test to check the method to set bv colour index
	//creates new star object, uses setBV() to set colout index, then compares value using getcolourIndex() 
	public void testsetBV(){
		Star test = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 0.2, 10.0, "G", "0","0","0" );
		test.setBV(0.1);
		assertTrue(test.getcolourIndex() == 0.1);
	}
	
	@Test
	//test to check method setTemp() which calculates stellar temperature from bv colour index
	public void testsetTemp(){
		Star test = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 0.2, 10.0, "G", "0","0","0" );
		test.setBV(10.867);
		test.setTemp();
		//System.out.println(test.getTemp());
		assertTrue(test.getTemp() <= 10.00000000001 && test.getTemp()>= 0.9999999999);		
		//fail("ssg");
		
	}
	
	@Test
	//test of set abMag()
	public void testsetAbMag(){
		Star test = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 0.2, 10.0, "G", "0","0","0" );
		test.setAbsMag();
		//System.out.println(test.getAbMag());
		assertTrue(test.getAbMag() <= -10.4 && test.getAbMag() >= -10.5);
		
	}

	@Test
	//
	public void testCreateHippStarObject(){
		//public Star(String catID, String id, int sid, double vm, double r, double d, double p, double ci, String st, String cid, String hd, String hr)
		double epochChange = 9 * 0.01388888;
		Star test = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 0.2, 10.0, "G", "0","0","0" );
		test.setAbsMag();
		test.setDistance();
		test.setTemp();
		assertTrue(test.getCatalogueID().equals("H"));
		assertTrue(test.getID().equals("1234"));
		assertTrue(test.getSVid() == 0);
		assertTrue(test.getApMag() == 3);
		assertTrue(test.getRA() == epochChange + 90);
		assertTrue(test.getDec() == 90);
		assertTrue(test.getParallax() == 0.2);
		assertTrue(test.getcolourIndex() == 10);
		assertTrue(test.getStellarClass().equals("G"));
		assertTrue(test.getHDid().equals("0"));
		assertTrue(test.getHRid().equals("0"));
	}
	
	@Test
	public void testSetDisColour(){
		Star test = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 0.2, 10.0, " ", "0","0","0" );
		test.setDisplayColor();
		assertTrue(test.getDisplayColor().toString().equals("java.awt.Color[r=250,g=252,b=133]"));
	}
	@Test
	public void testCalcZ(){
		int minSize = 1;
		double displayRange = 5;
		double distanceRange = 40;
		Star test = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 770, 10.0, "G", "0","0","0" ); //max parallax
		test.calcZCoord();
		assertTrue(test.getCartZ()> 6.8 && test.getCartZ() < 6.9);
		
		Star test2 = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 20, 10.0, "G", "0","0","0" ); //min parallax
		test2.calcZCoord();
		assertTrue(test2.getCartZ() == 0.75);
		
		Star test3 = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 400, 10.0, "G", "0","0","0" ); //min parallax
		test3.calcZCoord();
		assertTrue(test3.getCartZ() > 6.6 && test3.getCartZ() < 6.7);
	}
	
	@Test
	public void testsetMapProjection(){
		Star test = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 0.2, 10.0, "G", "0","0","0" );
		Point2D.Double point = new Point2D.Double(5,9);
		test.setMapProjection(point);
		assertTrue(test.getCartX() == 5);
		assertTrue(test.getCartY() == 9);
		
		Star test2 = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 0.2, 10.0, "G", "0","0","0" );
		Point2D.Double point2 = new Point2D.Double(100,100);
		test2.setMapProjection(point2);
		assertTrue(test2.getCartX() == 100);
		assertTrue(test2.getCartY() == 100);
	}
	
	@Test
	public void testInSystem(){
		Star test = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 0.2, 10.0, "G", "0","0","0" );
		assertTrue(test.getInSystem() == false);
		Star test2 = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 0.2, 10.0, "G", "0122-1234","0","0" );
		assertTrue(test2.getInSystem() == true);
		
	}
	
	@Test
	public void testsetDisplayColour(){
		Star test = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 0.2, 10.0, "O", "0","0","0" );
		assertTrue(test.getDisplayColor().toString().equals("java.awt.Color[r=219,g=242,b=252]"));
	}
	
	@Test
	public void testSetStellarClass(){
		Star test = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 0.2, 0.01, "DEFAULT", "0","0","0" );
		test.setTemp();
		test.setStellarClass();
		assertTrue(test.getStellarClass().equals("A"));
		
		Star test2 = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 0.2, 0.5, "DEFAULT", "0","0","0" );
		test2.setTemp();
		test2.setStellarClass();
		assertTrue(test2.getStellarClass().equals("F"));
		
		Star test3 = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 0.2, 2, "DEFAULT", "0","0","0" );
		test3.setTemp();
		test3.setStellarClass();
		assertTrue(test3.getStellarClass().equals("M"));
	}
	
	@Test
	public void testHAProj(){
		MapProjection map = new MapProjection();
		Star test = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 0.2, 0.01, "DEFAULT", "0","0","0" );
		
		map.setHAProjection(test.getRA(), test.getDec());
		assertTrue(test.getCartX()== 1.225981951843823E-16);
		assertTrue(test.getCartY() == -1.4142135623730951);	
	}
	
	@Test
	public void testCyProj(){
		MapProjection map = new MapProjection();
		Star test = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 0.2, 0.01, "DEFAULT", "0","0","0" );
		
		map.setCylinProjection(test.getRA(), test.getDec());		
		assertTrue(test.getCartX()== 1.225981951843823E-16);
		assertTrue(test.getCartY() == -1.4142135623730951);	
		
		Star test2 = new Star("H", "1234", 0, 3.0, 90.0, -90.0, 0.2, 0.01, "DEFAULT", "0","0","0" );
		
		map.setCylinProjection(test2.getRA(), test2.getDec());
		assertTrue(test2.getCartX()== 1.225981951843823E-16);
		assertTrue(test2.getCartY() == 1.4142135623730951);	
		
       Star test3 = new Star("H", "1234", 0, 3.0, -180.0, 45.0, 0.2, 0.01, "DEFAULT", "0","0","0" );
		
		map.setCylinProjection(test3.getRA(), test3.getDec());
		assertTrue(test3.getCartX()== -1.999227923281233);
		assertTrue(test3.getCartY() == -0.9996145563663722);	
	}
	
	@Test
	public void testMercatorProj(){
		MapProjection map = new MapProjection();
		Star test = new Star("H", "1234", 0, 3.0, 180.0, 90.0, 0.2, 0.01, "DEFAULT", "0","0","0" );
		
		map.setMetProjection(test.getRA(), test.getDec());
		assertTrue(test.getCartX()== 1.7319110820610817E-16);
		assertTrue(test.getCartY() == -1.4142135623730951);	
	}
	
	@Test
	public void testOAProj(){
		MapProjection map = new MapProjection();
		Star test = new Star("H", "1234", 0, 3.0, 180.0, 90.0, 0.2, 0.01, "DEFAULT", "0","0","0" );
		
		map.setOAProjection(test.getRA(), test.getDec());
		System.out.println(test.getCartX());
		System.out.println(test.getCartY());
		
		assertTrue(test.getCartX()== 1.7319110820610817E-16);
		assertTrue(test.getCartY() == -1.4142135623730951);	
		
		Star test2 = new Star("H", "1234", 0, 3.0, 300.0, 90.0, 0.2, 0.01, "DEFAULT", "0","0","0" );
		
		map.setOAProjection(test2.getRA(), test2.getDec());
		System.out.println(test2.getCartX());
		System.out.println(test2.getCartY());
		
		assertTrue(test2.getCartX()== -8.643194272518607E-17);
		assertTrue(test2.getCartY() == -1.4142135623730951);
	}
	
}
