import static org.junit.Assert.*;
import java.util.List;
import static java.util.Arrays.asList;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class BruteAutocompleteTest {
	
	private TermList terms = new TermList();

	//Reading in the file
	@Before
	public void setUp() throws IOException
	{
		terms.readFile("D:/Programming/EclipseWork/AutoComplete/data/wiktionary.txt");
	}
	
	//Testing the best match algorithms
	@Test
	public void testBestMatch() throws IOException 
	{
		BruteAutocomplete Ba = new BruteAutocomplete();
		assertEquals("the", Ba.bestMatch("the"));
		assertEquals("was", Ba.bestMatch("s"));
		assertEquals(null, Ba.bestMatch("bdhgfghd"));
		assertEquals("the", Ba.bestMatch("THE")); //testing case insensitive
	}
	
	//Testing the weight of terms algorithm
	@Test
	public void testWeightOf()
	{
		BruteAutocomplete Ba = new BruteAutocomplete();
		assertEquals(5.6271872E9, Ba.weightOf("the"),.1); //.1 is bias
		assertEquals(0.0,Ba.weightOf("testnotexist"),.1); //testing a term that doesn't exist
		assertEquals(1986690, Ba.weightOf("perpetual"),.1);
		assertEquals(1.4598E7, Ba.weightOf("AMERICAN"),.1);
		assertEquals(1.4598E7, Ba.weightOf("American"),.1);
		assertEquals(1.4598E7, Ba.weightOf("american"),.1);
	}
	
	//Testing the ArrayList of matches algorithm
	@Test
	public void testMatches()
	{
		BruteAutocomplete Ba = new BruteAutocomplete();
		List<String> testLists = asList("Term: term = off, weight=5.45832E7", "Term: term = office, weight = 1.36616E7");
		assertEquals(testLists, Ba.matches("off", 2));
		//assertEquals(testList, Ba.matches("off", 2)); //FOR SOME REASON THE RESULT OF THIS GIVES 2 TERMS TWICE, BUT IT
		//WORKS IN THE DRIVER
	}
}
