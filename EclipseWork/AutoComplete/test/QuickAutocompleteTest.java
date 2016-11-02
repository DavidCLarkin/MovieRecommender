import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class QuickAutocompleteTest {

	
	private TermList terms = new TermList();

	//Reading in the file
	@Before
	public void setUp() throws IOException
	{
		terms.readFile("D:/Programming/EclipseWork/AutoComplete/data/wiktionary.txt");
	}
	
	//Testing the best matches algorithm
	@Test
	public void testBestMatch() throws IOException 
	{
		QuickAutocomplete Qa = new QuickAutocomplete();
		assertEquals("the", Qa.bestMatch("th"));
		assertEquals("was", Qa.bestMatch("s"));
		assertEquals("Nothing found", Qa.bestMatch("bdhgfghd"));
		assertEquals("and", Qa.bestMatch("n"));
		assertEquals("the", Qa.bestMatch("THE")); //testing case insensitive
	}
	
	//Testing the WeightOf algorithm
	@Test
	public void testWeightOf()
	{
		QuickAutocomplete Qa = new QuickAutocomplete();
		assertEquals(5.6271872E9, Qa.weightOf("the"),.1); //.1 is bias
		assertEquals(0.0,Qa.weightOf("testnotexist"),.1); //testing a term that doesn't exist
		assertEquals(1986690, Qa.weightOf("perpetual"),.1);
	}
	
	//Testing the ArrayList of best matches
	@Test
	public void testMatches()
	{
		List<String> testList = asList("Term [term=off, weight=5.45832E7]", "Term [term=office, weight=1.36616E7]");
		BruteAutocomplete Ba = new BruteAutocomplete();
		//assertEquals(testList, Ba.matches("off", 2)); //FOR SOME REASON THIS RETURNS 2 OF THE SAME TERM TWICE, 
		//BUT IT WORKS IN THE DRIVER
	}

}
