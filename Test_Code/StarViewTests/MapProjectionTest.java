package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import mapprojections.*;

public class MapProjectionTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testConstructor(){
		
		MapProjection map = new MapProjection(30, 90);
		
	}
	
	@Test
	public void testHA(){
		MapProjection map = new MapProjection();
		assertTrue(map.setHAProjection(30, 90).x == 4.482518391512303E-17);
		assertTrue(map.setHAProjection(30, 90).y == -1.4142135623730951);
		
		MapProjection map2 = new MapProjection();
		assertTrue(map2.setHAProjection(-160, -45).x == -1.8587997754959826);
		assertTrue(map2.setHAProjection(-160, -45).y == 0.9437373791029344);
	}
	
	@Test
	public void testMer(){
		MapProjection map = new MapProjection();
		assertTrue(map.setMetProjection(30, 90).x == 30);
		assertTrue(map.setMetProjection(30, 90).y == -10100.63406152271);
	
		MapProjection map2 = new MapProjection();
		assertTrue(map2.setMetProjection(-179, -15).x == -179);
		assertTrue(map2.setMetProjection(-179, -15).y == 71.6566198802561);
	}
	
	@Test
	public void testCylin(){
		MapProjection map = new MapProjection();
		//System.out.println(map.setCylinProjection(45, 70).x);
		//System.out.println(map.setCylinProjection(45, 70).y);
		assertTrue(map.setCylinProjection(45, 70).x == 45);
		assertTrue(map.setCylinProjection(45, 70).y == -0.9396926207859083);
		
		MapProjection map2 = new MapProjection();
		System.out.println(map2.setCylinProjection(-135, -35).x);
		System.out.println(map2.setCylinProjection(-135, -35).y);
		assertTrue(map2.setCylinProjection(-135,-35).x == -135);
		assertTrue(map2.setCylinProjection(-135, -35).y == 0.573576436351046);
	}
	
	@Test
	public void testOA(){
		MapProjection map = new MapProjection();
		assertTrue(map.setOAProjection(45, 70).x == 0.2418447626479753);
		assertTrue(map.setOAProjection(45, 70).y == -0.9396926207859083);
		
		MapProjection map2 = new MapProjection();
		assertTrue(map2.setOAProjection(-60, -30).x == -0.75);
		assertTrue(map2.setOAProjection(-60, -30).y == 0.49999999999999994);
	}
}
