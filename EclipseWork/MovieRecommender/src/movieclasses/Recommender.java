package movieclasses;

public interface Recommender {

	public void addUser(String firstName, String lastName, int age, String gender, String occupation);
	
	public void removeUser(int userID);
	
	public void addMovie(String title, int year, String url);
	
	public void addRating(int userID, int movieID, int rating);
	
	public String getMovie(int movieID);
	
	public String getUserRatings(int userID);
	
	public Iterable<String> getUserRecommendations(int userID);
	
	public Iterable<String> getTopTenMovies();
	
	public void load();
	
	public void write();
}
