
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import movieclasses.LoadData;
import movieclasses.Occupation;
import movieclasses.Rating;
import movieclasses.User;

public class RatingTest {
	
	private Rating testRating, testRating2;
	private LoadData load;

	@Before
	public void setUp() throws Exception
	{
		load = new LoadData();
		testRating = load.getRatingList().get(1);
		testRating2 = load.getRatingList().get(2);
	}
	
	@After
	public void tearDown()
	{
		load = null;
		testRating = null;
		testRating2 = null;
	}
	
	@Test
	public void testGettersSetters() {
		//getters
		assertEquals(2,testRating.getUserID());
		assertEquals(10, testRating.getMovieID());
		assertEquals(1, testRating.getRating());
		
		//setters
		testRating.setUserID(51);
		assertEquals(51, testRating.getUserID());
		testRating.setMovieID(11);
		assertEquals(11, testRating.getMovieID());
		testRating.setRating(4);
		assertEquals(4, testRating.getRating());
		testRating.setRating(6);
		assertEquals(4, testRating.getRating());
		testRating.setRating(-6);
		assertEquals(4, testRating.getRating());
		testRating.setRating(3);
		assertEquals(3, testRating.getRating());
	}

}
