package movieclasses;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadData {
	
	private static ArrayList<Rating> ratings = new ArrayList<Rating>();
	private static ArrayList<User> users = new ArrayList<User>();
	private static ArrayList<Movie> movies = new ArrayList<Movie>();
	
	
	public static void readUserFile(String url) throws Exception
	{
		Scanner scan = new Scanner(new File(url));
		String separator = "[|]"; //separate by whitespace
		String temp;
		while (scan.hasNextLine()) //while scanner has another line
		{ 
			temp = scan.nextLine();
			String[] lineSplits = temp.split(separator); //separate terms by tab;
			if(lineSplits.length==7)
			{
				User user = new User(0,"","",0,"","");
				user.setFirstName(lineSplits[1]); //set the firstName of object
				user.setLastName(lineSplits[2]);
				user.setAge(Integer.parseInt(lineSplits[3]));
				user.setGender(lineSplits[4]);
				user.setOccupation(lineSplits[5]);
				user.setUserID(Integer.parseInt(lineSplits[0]));
				users.add(user); //add objects to ArrayList
			}
			else
			{
				 throw new Exception("Invalid member length: "+lineSplits.length);
			}
		}
		scan.close();
	}
	
	public static void readRatingFile(String url) throws Exception
	{
		Scanner scan = new Scanner(new File(url));
		String separator = "[|]"; //separate by whitespace
		String temp;
		while (scan.hasNextLine()) //while scanner has another line
		{ 
			temp = scan.nextLine();
			String[] lineSplits = temp.split(separator); //separate terms by tab;
			if(lineSplits.length==4)
			{
				Rating rating = new Rating(0,0,0);
				rating.setUserID(Integer.parseInt(lineSplits[0])); //set the firstName of object
				rating.setMovieID(Integer.parseInt(lineSplits[1]));
				rating.setRating(Integer.parseInt(lineSplits[2]));
				ratings.add(rating); //add objects to ArrayList
			}
			else
			{
				 throw new Exception("Invalid member length: "+lineSplits.length);
			}
		}
		scan.close();
	}
	
	public static void readMovieFile(String url) throws Exception
	{
		Scanner scan = new Scanner(new File(url));
		String separator = "[|]"; //separate by whitespace
		String temp;
		while (scan.hasNextLine()) //while scanner has another line
		{ 
			temp = scan.nextLine();
			String[] lineSplits = temp.split(separator); //separate terms by tab;
			if(lineSplits.length==23)
			{
				Movie movie = new Movie(0,"","","");
				movie.setMovieID(Integer.parseInt(lineSplits[0])); //set the firstName of object
				movie.setTitle(lineSplits[1]);
				movie.setRelease(lineSplits[2]);
				movie.setUrl(lineSplits[3]);
				movies.add(movie); //add objects to ArrayList
			}
			else
			{
				 throw new Exception("Invalid member length: "+lineSplits.length);
			}
		}
		scan.close();
	}
	
	public ArrayList<Rating> getRatingList()
	{
		return ratings;
	}
	
	public ArrayList<User> getUserList()
	{
		return users;
	}
	
	public ArrayList<Movie> getMovieList()
	{
		return movies;
	}

}
