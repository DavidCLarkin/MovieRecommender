/**
 * MainRecommender Class
 * 
 * The main class which overrides and implements all 
 * methods from the Recommender Interface
 * 
 * @author David Larkin
 * @version 09/12/2016
 */
package movieclasses;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import utils.Serializer;
import utils.XMLSerializer;


public class MainRecommender implements Recommender{

	private LoadData data;
	private Serializer serializer;
	
	/**
	 * Contstructor for class which loads the data
	 */
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
	
	/**
	 * Constructor for XML handling (not implemented)
	 * @param serializer a serializer to handle XML
	 */
	public MainRecommender(Serializer serializer)
	{
		this.serializer = serializer;
	}
	
	
	/**
	 * Method to Add a user to an ArrayList located in the LoadData class.
	 * @param String firstName - First name of user
	 * @param String lastName - Last name of user
	 * @param int age - Age of the user
	 * @param String gender - Gender of the user (m/f)
	 * @param String occupation - Occupation of the user
	 */
	@Override
	public void addUser(String firstName, String lastName, int age, String gender, String occupation) 
	{
		data.getUserList().add(new User(data.getUserList().size()+1,firstName,lastName,age,gender,new Occupation(occupation)));
	}

	/**
	 * Method to remove a user from an ArrayList located in the LoadData class
	 * @param int userID - the ID of the user in the list
	 */
	@Override
	public void removeUser(int userID) 
	{
		data.getUserList().remove(userID);
	}

	/**
	 * Method to add a Movie to the ArrayList of Movies in the LoadData class
	 * @param String title - Name of the movie
	 * @param int year - The year the movie was released
	 * @param String url - The url to find info on the movie
	 */
	@Override
	public void addMovie(String title, int year, String url) 
	{
		data.getMovieList().add(new Movie(data.getMovieList().size()+1, title, Integer.toString(year), url));
	}

	/**
	 * Method to add a Rating to the ArrayList of Ratings in the LoadData class
	 * @param int userID - ID of the user in the list
	 * @param int moveID - ID of the movie in the list
	 * @param int rating - ID of the rating in the list
	 */
	@Override
	public void addRating(int userID, int movieID, int rating) 
	{
		data.getRatingList().add(new Rating(userID,movieID,rating));
	}

	/**
	 * Method to get a Movie by it's ID
	 * @param int movieID - ID of the movie in the list
	 * @return String - return a toString of the movie by getting it by it's ID
	 */
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

	/**
	 * Method to get all of User's Ratings
	 * @param int userID - The ID of the user
	 * @return String - Add each rating to a list and return the list toString()
	 */
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

	/**
	 * Method to get a particular User's Recommendations based on their ID
	 * @param int userID - ID of the user in User List
	 * @return Iterable<String> - Return a sublist of 25 top recommendations based on what the user
	 * hasn't seen yet
	 */
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

	/**
	 * Method to get the Top 10 Movies from the whole movie list
	 * @return Iterable<String> - Return a sublist of the top ten movies based
	 * on an average rating of each
	 */
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
	

/**
 * Method to calulate averages of a list of ratings
 * @param ratings - List of ratings to take in
 * @return double - the average of all ratings
 */
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
	
/**
 * Method to get the average rating of one movie based on
 * all ratings of it
 * @param movieID - Id of the movie in the Movie list
 * @return the average of the specific movie
 */
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

/**
 * Method to load files from XML (not implemented)
 */
	@Override
	public void load() throws Exception {
		serializer.read();
		data = (LoadData) serializer.pop();
		User.counter = (Long) serializer.pop();
	}
/**
 * Method to write files to XML (not implemented)
 */
	@Override
	public void write() throws Exception {
		serializer.push(User.counter);
		serializer.push(data);
		serializer.write();
	}
/**
 * Method to load a file from XML (not implemented)
 * @param file
 * @throws Exception
 */
	void load(File file) throws Exception
	{
		ObjectInputStream is = null;
		try
		{
			XStream xstream = new XStream(new DomDriver());
			is = xstream.createObjectInputStream(new FileReader(file));
			data = (LoadData) is.readObject();
		}
		finally
		{
			if(is != null)
			{
				is.close();
			}
		}
	}
/**
 * Method to store file to XML (not implemented)
 * @param file
 * @throws Exception
 */
	void store(File file) throws Exception
	{
		XStream xstream = new XStream(new DomDriver());
		ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
		out.writeObject(data);
		out.close();
	}

}
