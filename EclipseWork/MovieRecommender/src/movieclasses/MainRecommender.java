package movieclasses;

import java.util.ArrayList;
import utils.Serializer;

public class MainRecommender implements Recommender{

	private LoadData data = new LoadData();
	private Serializer serializer;
	
	public MainRecommender()
	{
	}
	
	public MainRecommender(Serializer serializer)
	{
		this.serializer = serializer;
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
		data.getMovieList().add(new Movie(data.getMovieList().size()+1, title, Integer.toString(year), url));
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
		return "Movie by that ID doesn't exist";
	}

	@Override
	public String getUserRatings(int userID) 
	{
		for(int i = 0; i < data.getRatingList().size(); i++) 
		{
			if(userID == data.getRatingList().get(i).getUserID()) //When input = Ratings object ID
			{
				ArrayList<Integer> ratings = new ArrayList<Integer>();
				ArrayList<String> movies = new ArrayList<String>();
				for(int j = 0; j < data.getRatingList().size(); j++)
				{
					if(userID == data.getRatingList().get(j).getUserID())
					{
						ratings.add(data.getRatingList().get(j).getRating());
					}
				}
				for(int k = 0; k < data.getMovieList().size(); k++)
				{
					if(data.getRatingList().get(i).getMovieID() == data.getMovieList().get(k).getMovieID())
					{
						movies.add(data.getMovieList().get(k).getTitle());
					}
				}
				return ratings.toString()+movies.toString();
			}
		}
			
		return "Doesn't exist";
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
		//if ratings are high on a movie compared to other movies
		return null;
	}

	@Override
	public void load() 
	{
		try {
			serializer.read();
		} catch (Exception e) {
			System.out.println("Cannot be read "+e);
		}
		data = (LoadData) serializer.pop();
		
	}

	@Override
	public void write() 
	{
		serializer.push(data);
		try {
			serializer.write();
		} catch (Exception e) {
			System.out.println("Cannot be stored "+e);
			e.printStackTrace();
		}
		
	}

}
