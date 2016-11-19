package movieclasses;

public class Rating {

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
	
	@Override
	public String toString() {
		return "\nRating: userID = " + userID + ", movieID = " + movieID + ", rating = " + rating;
	}
}
