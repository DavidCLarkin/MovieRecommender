import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import movieclasses.LoadData;

public class LoadDataTest {

	public static LoadData data;
	
	@Before
	public void setUp() throws Exception
	{
		try{
		data = new LoadData();
		}
		catch(Exception e)
		{
			System.out.println("Cannot load data");
		}
	}
	
	@After
	public void tearDown()
	{
		data = null;
	}
	
	@Test
	public void testListGetters() {
		assertTrue(data.getMovieList().size() > 0);
		assertTrue(data.getOccupationList().size()>0);
		assertTrue(data.getRatingList().size() > 0);
		assertTrue(data.getUserList().size() > 0);
	}
}
