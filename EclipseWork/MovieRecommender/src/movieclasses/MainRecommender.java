package movieclasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.Serializer;
import utils.XMLSerializer;

public class MainRecommender implements Recommender{

	private LoadData data;
	
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
	public Iterable<String> getUserRecommendations(int userID) 
	{
		ArrayList<String> averages = new ArrayList<String>();
		for(int i = 0; i < data.getMovieList().size(); i++)
		{
			averages.add("Rating: "+averageOneMovie(i+1)+" Movie: "+data.getMovieList().get(data.getMovieList().get(i).getMovieID()-1).getTitle()+ "\n");
		}
		Collections.sort(averages, Collections.reverseOrder());
		
		for(int i = 0; i < averages.size(); i++)
		{
			if(getUserRatings(userID).contains(data.getMovieList().get(i).getTitle())) //if users ratings contains the title of any movie
			{
				averages.remove(i); //remove those items
			}
		}
		return averages.subList(0, 25); //only return top 25 movies
	}

	@Override
	public Iterable<String> getTopTenMovies() 
	{
		ArrayList<String> movies = new ArrayList<String>();
		for(int i = 0; i < data.getMovieList().size(); i++)
		{
			movies.add("Rating: "+averageOneMovie(i+1)+" Movie: "+data.getMovieList().get(data.getMovieList().get(i).getMovieID()-1).getTitle()+ "\n");
		}
		
		Collections.sort(movies, Collections.reverseOrder()); //sort them by rating so it returns them in order
		
		return movies.subList(0, 10); //sublist from 0 to 10 exclusive
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
		    return (sum.doubleValue() / ratings.size()); //average of the value
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
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub
		
	}

}
