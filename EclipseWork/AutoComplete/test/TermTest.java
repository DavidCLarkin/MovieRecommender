import static org.junit.Assert.*;
import java.util.Comparator;
import org.junit.Before;
import org.junit.Test;


public class TermTest {

	private Term term1;
	private Term term2;
	private Term term3;
	private Term term4;
	public Comparator<Term> BY_TERM;
	public Comparator<Term> BY_WEIGHT;
	
	//setting up the needed terms and parameters
	@Before
	public void setUp()
	{
		term1 = new Term("hello", 1234);
		term2 = new Term("negative", -123);
		term3 = new Term("testing", 105);
		term4 = new Term("phone", 1234);
	}
	
	//Testing the getters and setters
	@Test
	public void testGettersSettersData() 
	{
		assertEquals("hello", term1.getTerm());
		assertEquals("negative", term2.getTerm());
		assertEquals(1234, term1.getWeight(), 0);
		assertEquals(0, term2.getWeight(), 0);
	}
	
	//Testing invalid and valid data inputs
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
	
	@Test
	public void testByWeight()
	{
		assertEquals(-1,Term.BY_WEIGHT.compare(term1, term3));	//returns -1 because term 1 is higher
		assertEquals(1, Term.BY_WEIGHT.compare(term3, term1));  //returns 1 because term1 has higher weight
		assertEquals(0, Term.BY_WEIGHT.compare(term1, term4));  //returns 0 because equal
	}
	
	/* --wasn't sure how to test this function
	@Test
	public void testByTerm()
	{
		assertEquals(-1, Term.BY_TERM.compare(term3, term4));
	}
	*/

}
