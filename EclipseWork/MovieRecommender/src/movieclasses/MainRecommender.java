package movieclasses;

public class MainRecommender implements Recommender{

	private LoadData data = new LoadData();
	
	public MainRecommender()
	{
	}
	
	@Override
	public void addUser(String firstName, String lastName, int age, String gender, String occupation) 
	{
		data.getUserList().add(new User(data.getUserList().size()+1,firstName,lastName,age,gender,occupation));
	}

	@Override
	public void removeUser(int userID) 
	{
		data.getUserList().remove(userID);
		
	}

	@Override
	public void addMovie(String title, int year, String url) 
	{
		
	}

	@Override
	public void addRating(int userID, int movieID, int rating) 
	{
		data.getRatingList().add(new Rating(userID,movieID,rating));
	}

	@Override
	public String getMovie(int movieID) 
	{
		for(int i = 0; i <data.getMovieList().size(); i++)
		{
			if(data.getMovieList().get(i).getMovieID() == movieID)
				return data.getMovieList().get(i).toString();
		}
		return "Doesn't exist";
	}

	@Override
	public String getUserRatings(int userID) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserRecommendations(int userID) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<String> getTopTenMovies() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write() 
	{
		// TODO Auto-generated method stub
		
	}

}
