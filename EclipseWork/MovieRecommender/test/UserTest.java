
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import movieclasses.Occupation;
import movieclasses.User;

public class UserTest {
		
	private User testUser, testUser2;
		
	@Before
	public void setUp()
	{
		testUser = new User(1, "David", "Larkin", 19, "M", new Occupation("student"));
		testUser2 = new User(2, "Bart", "Simpson", 14, "F", new Occupation("other"));
	}

	@Test //Getters and Setters
	public void testGettersSetters() {
		assertEquals(19, testUser.getAge());
		assertEquals("David", testUser.getFirstName());
		assertEquals("Larkin", testUser.getLastName());
		assertEquals("Male", testUser.getGender());
		assertEquals("Female", testUser2.getGender());
		
		testUser.setAge(140);
		assertEquals(140, testUser.getAge());
		testUser2.setAge(141); //boundary test
		assertEquals(14, testUser2.getAge());
		assertEquals("student", testUser.getOccupation().getOccupation());
		assertEquals(1, testUser.getUserID());
		testUser.setGender("M");
		assertEquals("Male", testUser.getGender());
		testUser2.setGender("f");
		assertEquals("Female", testUser2.getGender());
		testUser2.setGender("fem");
		assertEquals("Female", testUser2.getGender());
	}
	
	@After
	public void tearDown()
	{
		testUser = null;
		testUser2 = null;
	}

}
