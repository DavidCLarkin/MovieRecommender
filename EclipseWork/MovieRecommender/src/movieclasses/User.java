package movieclasses;
import java.io.Serializable;

public class User implements Serializable {
	
	private String firstName;
	private String lastName; 
	private int age; 
	private String gender;
	private String occupation;
	private int userID;
	
	public User(String firstName, String lastName, int age, String gender, String occupation, int userID)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
		this.userID = userID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	@Override
	public String toString() {
		return "User: firstName = " + firstName + ", LastName = " + lastName + ", Age = " + age + ", Gender = " + gender
				+ ", Occupation = " + occupation + ", UserID = " + userID;
	}

}
