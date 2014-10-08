package Testing;

import static org.junit.Assert.*;
import org.junit.Test;
import maindisplay.*;

public class DisplayPanelTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	StarManager man = new StarManager(1);
	StarController con = new StarController(man);
	DisplayPanel test = new DisplayPanel(man, con);
	@Test
	public void testsetaltColour(){
		
		test.setAltColour(1);
		assertTrue(test.getColourInt() == 1);
		
	}
	
	@Test
	public void testIncScale(){
		test.incScale();
		assertTrue(test.getScale() == 1.25);
	}
	
	@Test
	public void testDecScale(){
		test.decScale();
		assertTrue(test.getScale() == 0.75);
	}
	
	@Test
	public void testSetCoords(){
		test.setBounds(0, 0, 10, 10);
		test.setcoords();
		assertTrue(test.xdis ==5);
		assertTrue(test.ydis == 5);
		assertTrue(test.xdisSearch == 5);
		assertTrue(test.ydisSearch == 5);
	}
	
	@Test
	public void testSetCircleCoords(){

		test.setBounds(0, 0, 10, 10);
		test.setcoords();
		test.setCircleCoords(2, 2);
		assertTrue(test.xdisSearch == 7);
		assertTrue(test.ydisSearch == 7);		
	}
	
	@Test
	public void testSetCircleCoordsneg(){
		test.setBounds(0, 0, 10, 10);
		test.setcoords();
		test.setCircleCoords(-2, -2);
		assertTrue(test.xdisSearch == 3);
		assertTrue(test.ydisSearch == 3);	
		
	}
	
	@Test
	public void testResetCoords(){
		test.setBounds(0, 0, 10, 10);
		test.setcoords();
		test.setCircleCoords(10, 10);
		test.resetCoords();
		assertTrue(test.xdisSearch == 5);
		assertTrue(test.ydisSearch == 5);	
		
	}
	
	

}
