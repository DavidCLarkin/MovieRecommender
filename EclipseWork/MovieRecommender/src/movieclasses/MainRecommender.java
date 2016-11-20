package movieclasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.Serializer;

public class MainRecommender implements Recommender{

	private LoadData data;
	private Serializer serializer;
	
	public MainRecommender()
	{
		try{
		data = new LoadData();
		}
		catch(Exception e)
		{
			System.out.println("Can't read");
		}
	}
	
	public MainRecommender(Serializer serializer)
	{
		this.serializer = serializer;
	}
	
	
	@Override
	public void addUser(String firstName, String lastName, int age, String gender, String occupation) 
	{
		data.getUserList().add(new User(data.getUserList().size()+1,firstName,lastName,age,gender,new Occupation(occupation)));
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
		ArrayList<String> ratings = new ArrayList<String>();
		for(int i = 0; i < data.getRatingList().size(); i++)
		{
			if(data.getRatingList().get(i).getUserID() == userID)
			{
				ratings.add(data.getRatingList().get(i).toString()+" Movie: "+data.getMovieList().get(data.getRatingList().get(i).getMovieID()-1).getTitle());
			}
		}
		return ratings.toString();
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
		ArrayList<String> movies = new ArrayList<String>();
		for(int i = 0; i < data.getMovieList().size(); i++)
		{
			movies.add("Rating: "+averageOneMovie(i+1)+" Movie: "+data.getMovieList().get(data.getMovieList().get(i).getMovieID()-1).getTitle()+ "\n");
		}
		
		Collections.sort(movies, Collections.reverseOrder());
		
		return movies.subList(0, 10);
	}
	
	//used for other methods, helper.
	private double calculateAverage(List<Integer> ratings) 
	{
		Integer sum = 0;
		if(!ratings.isEmpty()) 
		{
			for (Integer rating : ratings) 
			{
				sum += rating;
		    }
		    return (sum.doubleValue() / ratings.size());
		}
		return sum;
	}
	
	//average of one movie
	public double averageOneMovie(int movieID)
	{
		ArrayList<Integer> movieRatings = new ArrayList<Integer>();
		for(int i = 0; i < data.getRatingList().size(); i++)
		{
			if(movieID == data.getRatingList().get(i).getMovieID()) // if input == movieID in list
			{
				movieRatings.add(data.getRatingList().get(i).getRating());// add them to a list
			}
		}	
		return calculateAverage(movieRatings);//print the average of the movie
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
