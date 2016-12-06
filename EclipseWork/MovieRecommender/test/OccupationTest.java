import static org.junit.Assert.*;

import org.junit.Test;

import movieclasses.Occupation;

public class OccupationTest {

	@Test
	public void testGettersSetters() {
		Occupation occupation = new Occupation("Student");
		assertEquals("Student", occupation.getOccupation());
		occupation.setOccupation("Technician");
		assertEquals("Technician", occupation.getOccupation());
		assertEquals("Occupation: Technician", occupation.toString());
	}

}
