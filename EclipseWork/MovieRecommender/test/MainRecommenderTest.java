import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import movieclasses.LoadData;
import movieclasses.MainRecommender;
import movieclasses.Movie;
import movieclasses.Occupation;
import movieclasses.Rating;
import movieclasses.User;

public class MainRecommenderTest {

	private MainRecommender app;
	private LoadData data;
 	
	@Before
	public void setUp()
	{
		app = new MainRecommender();
		data = new LoadData();
	}
	
	@After
	public void tearDown()
	{
		app = null;
		data = null;
	}
	
	@Test
	public void testAddRating()
	{
		Rating rating = new Rating(3,31,3); // make new instance to test against
		app.addRating(3,31,3);
		assertEquals(rating.getRating(),data.getRatingList().get(data.getRatingList().size()-1).getRating()); //if true, object added
	}
	
	@Test
	public void testAddUser() 
	{
		User user = new User(data.getUserList().size()+1,"Bart", "Simpson", 16, "m", new Occupation("student")); //new object to test
		app.addUser("Bart", "Simpson", 16, "m", "student");
		assertEquals(user.getFirstName(), data.getUserList().get(data.getUserList().size()-1).getFirstName()); //checking the last user is the recently added one
		assertEquals(user.getUserID(), data.getUserList().get(data.getUserList().size()-1).getUserID()); //test the id etc.
		assertEquals(user.getLastName(), data.getUserList().get(data.getUserList().size()-1).getLastName());
		assertEquals(user.getGender(), data.getUserList().get(data.getUserList().size()-1).getGender());
	}
	
	@Test
	public void testRemoveUser()
	{
		app.removeUser(2);
		assertEquals(4,data.getUserList().get(2).getUserID()); //if its 4 it means the list has shifted and there is no 3 anymore
	}
	
	@Test
	public void testAddMovie()
	{
		Movie movie = new Movie(data.getMovieList().size()+1,"Toy Story 2", "1996", "imdb.cccccxxxx.com");
		app.addMovie("Toy Story 2", 1996, "imdb.cccccxxxx.com");
		//Testing all code
		assertEquals(movie.getTitle(), data.getMovieList().get(data.getMovieList().size()-1).getTitle());
		assertEquals(movie.getUrl(), data.getMovieList().get(data.getMovieList().size()-1).getUrl());
		assertEquals(movie.getRelease(), data.getMovieList().get(data.getMovieList().size()-1).getRelease());
		assertEquals(movie.getMovieID(), data.getMovieList().get(data.getMovieList().size()-1).getMovieID());
	}
	
	@Test
	public void testGetMovie()
	{
		assertEquals("Movie: movieID=1, title=Toy Story (1995), release=01-Jan-1995, url=http://us.imdb.com/M/title-exact?Toy%20Story%20(1995)", app.getMovie(1));
		assertEquals("Movie: movieID=153, title=Fish Called Wanda, A (1988), release=01-Jan-1988, url=http://us.imdb.com/M/title-exact?Fish%20Called%20Wanda,%20A%20(1988)", app.getMovie(153));
	}
	
	@Test
	public void testGetUserRatings()
	{
		//Testing result is not null
		String ratings = app.getUserRatings(1);
		Assert.assertNotNull("ratings expected", ratings);
		
		assertTrue(app.getUserRatings(1).length() > 0); //testing if it returns a list at all, greater than 0
		assertTrue(app.getUserRatings(53).length() > 0);
		
		//Test substring to get accurate result
		assertEquals("["+"\n"+
				"Rating: userID = 1, movieID = 61, rating = 3 Movie: Three Colors: White (1994),", app.getUserRatings(1).toString().substring(0, 81));
	}
	
	@Ignore
	@Test
	public void testRecommendations()
	{
		//Testing not null
		Iterable<String> recommendations = new ArrayList<String>();
		recommendations = app.getUserRecommendations(2);
		Assert.assertNotNull("Recommendations expected", recommendations);
		assertTrue(app.getUserRecommendations(1).toString().length() > 0);
		//just test first 2 elements of the result, easiest way to test equivalence
		assertEquals("Rating: 5.0 Movie: Star Kid (1997)"+"\n"+
				", Rating: 5.0 Movie: Santa with Muscles (1996)",app.getUserRecommendations(1).toString().substring(1,82));
	}
	
	@Ignore
	@Test
	public void testTopTenMovies()
	{
		Iterable<String> movies = new ArrayList<String>();
		movies = app.getTopTenMovies();
		Assert.assertNotNull("Movies expected", movies);
		assertTrue(app.getTopTenMovies().toString().length() > 0);
		assertEquals("[Rating: 5.0 Movie: They Made Me a Criminal (1939)"+"\n"+
				", Rating: 5.0 Movie: Star Kid (1997)", app.getTopTenMovies().toString().substring(0,87));
		
	}
	

}
