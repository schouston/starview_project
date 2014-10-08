package Testing;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.Test;

import maindisplay.*;

public class testStarManager {
	

	@Test
	public final void testStarManager() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAddStar() {
		
		StarManager man = new StarManager(2);
		Star star = new Star();
		Star star2 = new Star();
		star.setName("Test Star");
		star2.setName("Test Star 2");
		man.addStar(star);
		man.addStar(star2);
		assertTrue(man.getStarArray()[0].getName().equals("Test Star"));
		assertTrue(man.getStarArray()[1].getName().equals("Test Star 2"));
	}

	@Test
	public final void testGetStarViewID() {
		StarManager man1 = new StarManager(1);
		Star star1 = new Star();
		//System.out.println("here");
		//star1.setName("Test Star");
		//System.out.println("here");
		man1.addStar(star1);
		System.out.println("here");
		assertTrue(man1.getStarViewID() == 0);
		//assertTrue(man.getStarArray()[1].getSVid() == 1);
	}

	@Test
	public final void testPrintStarData() {
		StarManager man = new StarManager(2);
		Star star3 = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 700, 10.0, "G", "0","0","0" );
		Star star4 = new Star("G", "1234", 0, 3.0, 90.0, 90.0, 700, 10.0, "G", "0","0","0" );
		man.addStar(star3);
	//	System.out.println("here");
		man.addStar(star4);
		//System.out.println("here");
		man.setFilterArray(100, 5, 1,0,0);
		System.out.println(man.printStarData(0));
	}

	@Test
	public final void testPrintSunData() {
		
	}

	@Test
	public final void testGetStarArray() {
		StarManager man = new StarManager(1);
		Star star = new Star();
		man.addStar(star);
		star.setName("Test Star");
		assertTrue(man.getStarArray()[0].getName().equals("Test Star"));
	}

	@Test
	public final void testSearchStarArray() {
		StarManager man = new StarManager(1);
		Star star = new Star();
		star.setName("Test Star");
		//Point2D.Double point = new Point2D.Double(0, 0) ;
		//star.setMapProjection(point);
		assertTrue(man.searchStarArray("Test Star").x == 0);
		assertTrue(man.getSearchIndex() == 0);
		
	}

	@Test
	public final void testGetcoords() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetSearchIndex() {
		StarManager man = new StarManager(2);
		Star star = new Star();
		star.setName("Test Star");
		Star star1 = new Star();
		star.setName("Test Star 1");
		man.addStar(star);
		man.addStar(star1);
		man.searchStarArray("Test Star 1");
		//Point2D.Double point = new Point2D.Double(0, 0) ;
		//star.setMapProjection(point);
		//assertTrue(man.searchStarArray("Test Star").x == 0);
		assertTrue(man.getSearchIndex() == 1);
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetFilterArrayMag() {
		StarManager man = new StarManager(2);
		
		Star star = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 700, 10.0, "G", "0","0","0" );
		star.setName("Test Star");
		Star star1 = new Star("H", "12345", 0, 13.0, 90.0, 90.0, 700, 10.0, "G", "0","0","0" );
		star.setName("Test Star 1");
		man.addStar(star);
		man.addStar(star1);
		man.setFilterArray(50, 12, 1, 0, 0);
		
		assertTrue(man.getFiltArray().length == 1);
	}
	
	@Test
	public void testGetFilterArrayDist(){
		StarManager man = new StarManager(2);
		
		Star star = new Star("H", "1234", 0, 3.0, 90.0, 90.0, 700, 10.0, "G", "0","0","0" );
		star.setName("Test Star");
		Star star1 = new Star("H", "12345", 0, 7.0, 90.0, 90.0, 20, 10.0, "G", "0","0","0" );
		star.setName("Test Star 1");
		man.addStar(star);
		man.addStar(star1);
		man.setFilterArray(30, 12, 1, 0, 0);
		
		assertTrue(man.getFiltArray().length == 1);
	}

	public void testGetFilterArrayCat(){
		StarManager man = new StarManager(2);
		
		Star star = new Star("T", "1234", 0, 3.0, 90.0, 90.0, 700, 10.0, "G", "0","0","0" );
		star.setName("Test Star");
		Star star1 = new Star("T", "12345", 0, 7.0, 90.0, 90.0, 20, 10.0, "G", "0","0","0" );
		star.setName("Test Star 1");
		man.addStar(star);
		man.addStar(star1);
		man.setFilterArray(30, 12, 1, 0, 0);
		
		assertTrue(man.getFiltArray().length == 0);
	}

}
