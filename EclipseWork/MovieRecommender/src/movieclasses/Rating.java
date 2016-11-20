package movieclasses;

import java.util.Comparator;

public class Rating {

	public static final Comparator<Rating> BY_RATING = new ByRating();
	private int userID;
	private int movieID;
	private int rating;
	
	public Rating(int userID, int movieID, int rating)
	{
		this.userID = userID;
		this.movieID = movieID;
		this.rating = rating;
			
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		if(rating >= -5 && rating <= 5) //rating can't be less than -5 or greater than 5
		{
			this.rating = rating;
		}
	}
	
	public static class ByRating implements Comparator<Rating>
	{
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
	@Override
	public String toString() {
		return "\nRating: userID = " + userID + ", movieID = " + movieID + ", rating = " + rating;
	}


}
