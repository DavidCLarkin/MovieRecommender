import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TermListTest {

	private Term term1;
	private Term term2;
	private ArrayList<Term> termsList;
	private TermList terms = new TermList();
	
	//Reading the file and setting up a termList
	@Before
	public void SetUp() throws IOException
	{
		terms.readFile("D:/Programming/EclipseWork/AutoComplete/data/wiktionary.txt");
		termsList = new ArrayList<Term>();
	}
	
	//Test getting TermList
	@Test
	public void testGetTermList() 
	{
		terms.getTermList();
		assertTrue(terms.getTermList().size() > 1);
	}

}
