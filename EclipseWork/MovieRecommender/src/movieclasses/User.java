/**
 * User Class
 * Used to create an instance of a User object
 * 
 * @author David Larkin
 * @version 09/12/2016
 */
package movieclasses;
import java.io.Serializable;

public class User implements Serializable {
	
	static Long counter = 0l;
	public Long id;
	
	private static final long serialVersionUID = 8140505438064742386L;
	private String firstName;
	private String lastName; 
	private int age; 
	private String gender;
	private Occupation occupation;
	private int userID;
	
	/**
	 * Constructor for a User
	 * @param userID - ID of the user
	 * @param firstName - First name of the user
	 * @param lastName - Last name of the user
	 * @param age - Age of the user
	 * @param gender - Gender of the user
	 * @param occupation - Occupation of the user
	 */
	public User(int userID, String firstName, String lastName, int age, String gender, Occupation occupation)
	{
		this.id = counter++;
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

	/**
	 * Getter for userId
	 * @return userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Setter for UserID
	 * @param userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * Getter for firstName
	 * @return firstName of user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for firstName
	 * @param firstName New firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for lastName
	 * @return lastName of user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for lastName
	 * @param lastName New lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for Age
	 * @return age of user
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Setter for User age
	 * @param age New age
	 */
	public void setAge(int age) {
		if(age <= 140)
		{
			this.age = age;
		}
	}

	/**
	 * Getter for Gender
	 * @return gender of user
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Setter for Gender
	 * @param gender New gender
	 */
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

	/**
	 * Getter for Occupation
	 * @return occupation of user
	 */
	public Occupation getOccupation() {
		return occupation;
	}

	/**
	 * Setter for Occupater
	 * @param occupation
	 */
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	
	/**
	 * toString representation of User
	 */
	@Override
	public String toString() {
		return "User: UserID = " + userID +", First Name = " + firstName + ", Last Name = " + lastName + ", Age = " + age + ", Gender = " + gender
				+ ", Occupation = " + occupation.getOccupation();
	}

}
