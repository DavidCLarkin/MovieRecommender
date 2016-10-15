import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TermTest {

	private Term term1;
	private Term term2;
	
	@Before
	public void setUp()
	{
		term1 = new Term("hello", 1234);
		term2 = new Term("negative", -123);
	}
	
	@Test
	public void testGettersSettersData() 
	{
		assertEquals("hello", term1.getTerm());
		assertEquals("negative", term2.getTerm());
		assertEquals(1234, term1.getWeight(), 0);
		assertEquals(0, term2.getWeight(), 0);
	}
	
	@Test
	public void testGettersSettersInvalidData()
	{
		term1.setWeight(-1);
		assertEquals(1234, term1.getWeight(), 0);
		term1.setWeight(0);
		assertEquals(0, term1.getWeight(), 0);
		term2.setTerm("test");
		assertEquals("test", term2.getTerm());
	}
	

}
