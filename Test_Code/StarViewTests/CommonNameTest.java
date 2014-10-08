package Testing;

import static org.junit.Assert.*;

import org.junit.Test;
import commonnames.*;
import maindisplay.*;

public class CommonNameTest {

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	
	@Test
	public void testCommonNameConstructor(){
		
		CommonName test = new CommonName("Test Star", 1234);
		assertTrue(test.getName().equals("Test Star"));
		assertTrue(test.getID() == 1234);
	}
	
	@Test
	public void testPutName(){
		CommonName test = new CommonName("Test Star", 1234);
		StarManager starman = new StarManager(1);
		CommonNameManager man = new CommonNameManager(starman.getStarArray());
		man.putName(test);
		assertTrue(man.getHashMap().containsValue("Test Star") == true);
		assertTrue(man.getHashMap().size() == 1);
		
		
	}

}
