/**
 * Occupation class
 * Used to create an instance of an Occupation
 * 
 * @author David Larkin
 * @version 09/12/2016
 */
package movieclasses;

import java.io.Serializable;


public class Occupation implements Serializable {
	
	private String occupation;
	
	/**
	 * Constructor for Occupation object
	 * @param occupation The occupation name
	 */
	public Occupation(String occupation)
	{
		this.occupation = occupation;
	}

	/**
	 * Getter for Occupation
	 * @return the Occupation
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * Setter for Occupation
	 * @param occupation
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * toString representation of Occupation
	 */
	@Override
	public String toString() {
		return "Occupation: " + occupation;
	}


}
