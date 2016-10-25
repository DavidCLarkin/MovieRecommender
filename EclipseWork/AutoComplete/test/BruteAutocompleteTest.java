import static org.junit.Assert.*;
import java.util.List;
import static java.util.Arrays.asList;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class BruteAutocompleteTest {
	
	//private Term term1 = new Term("test", 1000);
	//private Term term2 = new Term("tester", 500);
	//private Term term3 = new Term("gold", 700);
	private TermList terms = new TermList();

	@Before
	public void setUp() throws IOException
	{
		terms.readFile();
	}
	
	@Test
	public void testBestMatch() throws IOException 
	{
		BruteAutocomplete Ba = new BruteAutocomplete();
		assertEquals("the", Ba.bestMatch("the"));
		assertEquals("was", Ba.bestMatch("s"));
		assertEquals(null, Ba.bestMatch("bdhgfghd"));
	}
	
	@Test
	public void testWeightOf()
	{
		BruteAutocomplete Ba = new BruteAutocomplete();
		assertEquals(5.6271872E9, Ba.weightOf("the"),.1); //.1 is bias
		assertEquals(0.0,Ba.weightOf("testnotexist"),.1); //testing a term that doesn't exist
		assertEquals(1986690, Ba.weightOf("perpetual"),.1);
	}
	
	@Test
	public void testMatches()
	{
		List<String> testList = asList("Term [term=off, weight=5.45832E7]", "Term [term=office, weight=1.36616E7]");
		BruteAutocomplete Ba = new BruteAutocomplete();
		//assertEquals(testList, Ba.matches("off", 2));
	}
}
