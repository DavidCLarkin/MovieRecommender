package movieclasses;
import java.io.Serializable;

public class User implements Serializable {
	
	private String firstName;
	private String lastName; 
	private int age; 
	private String gender;
	private Occupation occupation;
	private int userID;
	
	public User(int userID, String firstName, String lastName, int age, String gender, Occupation occupation)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		if(gender.contains("m") && !gender.contains("f") || gender.contains("M") && !gender.contains("F"))
		{
			setGender("Male");
		}
		
		if(gender.contains("f") || gender.contains("F"))
		{
			setGender("Female");
		}
		
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
		if(age <= 140)
		{
			this.age = age;
		}
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		if(gender.contains("m") && !gender.contains("f") || gender.contains("M") && !gender.contains("F"))
		{
			this.gender = "Male";
		}
		if(gender.contains("f") || gender.contains("F"))
		{
			this.gender = "Female";
		}
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	
	@Override
	public String toString() {
		return "User: UserID = " + userID +", First Name = " + firstName + ", Last Name = " + lastName + ", Age = " + age + ", Gender = " + gender
				+ ", Occupation = " + occupation.getOccupation();
	}

}
