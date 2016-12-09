/**
 * Rating Class
 * Used to create an instance of a Rating object
 * 
 * @author David Larkin
 * @version 09/12/2016
 */
package movieclasses;

import java.io.Serializable;
import java.util.Comparator;

public class Rating implements Serializable {

	public static final Comparator<Rating> BY_RATING = new ByRating();
	private int userID;
	private int movieID;
	private int rating;
	
	/**
	 * Constructor for Rating object
	 * @param userID ID of the User
	 * @param movieID ID of the Movie
	 * @param rating ID of the Rating
	 */
	public Rating(int userID, int movieID, int rating)
	{
		this.userID = userID;
		this.movieID = movieID;
		this.rating = rating;	
	}

	/**
	 * Getter for UserID
	 * @return userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Setter of UserID
	 * @param userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * Getter for MovieID
	 * @return movieID
	 */
	public int getMovieID() {
		return movieID;
	}

	/**
	 * Setter for MovieID
	 * @param movieID
	 */
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	/**
	 * Getter for Rating
	 * @return rating of movie
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * Setter for Rating
	 * @param rating
	 */
	public void setRating(int rating) {
		if(rating >= -5 && rating <= 5) //rating can't be less than -5 or greater than 5
		{
			this.rating = rating;
		}
	}

	/**
	 * Class to sort by Rating
	 * @author David Larkin
	 * @version 09/12/2016
	 *
	 */
	public static class ByRating implements Comparator<Rating>
	{
		/**
		 * Compare method to compare ratings
		 */
		@Override
		public int compare(Rating v, Rating w) 
		{
			if(v.rating > w.rating)
				return -1;
			if(v.rating < w.rating)
				return +1;
			return 0;
		}
	}
	/**
	 * To string for the Rating
	 */
	@Override
	public String toString() {
		return "\nRating: userID = " + userID + ", movieID = " + movieID + ", rating = " + rating;
	}


}
