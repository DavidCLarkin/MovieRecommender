import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import movieclasses.LoadData;
import movieclasses.Movie;
import movieclasses.Rating;

public class MovieTest {
	private Movie testMovie, testMovie2;
	private LoadData load;

	@Before
	public void setUp() throws Exception
	{
		load = new LoadData();
		testMovie = load.getMovieList().get(0);
		testMovie2 = load.getMovieList().get(1);
	}
	
	@After
	public void tearDown()
	{
		load = null;
		testMovie = null;
		testMovie2 = null;
	}
	
	@Test
	public void testGettersSetters() {
		//getters
		assertEquals("01-Jan-1995",testMovie.getRelease());
		assertEquals(1, testMovie.getMovieID());
		assertEquals("Toy Story (1995)", testMovie.getTitle());
		assertEquals("http://us.imdb.com/M/title-exact?Toy%20Story%20(1995)", testMovie.getUrl());
		
		//setters
		testMovie.setMovieID(51);
		assertEquals(51, testMovie.getMovieID());
		testMovie.setMovieID(11);
		assertEquals(11, testMovie.getMovieID());
		testMovie.setRelease("01-Jan-1992");
		assertEquals("01-Jan-1992", testMovie.getRelease());

	}
}
