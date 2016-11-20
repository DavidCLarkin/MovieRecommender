package movieclasses;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LoadData {
	
	public LoadData() throws Exception
	{
		try
		{
			readUserFile("D:/Programming/EclipseWork/MovieRecommender/data/users.dat");
			readRatingFile("D:/Programming/EclipseWork/MovieRecommender/data/ratings.dat");
			readMovieFile("D:/Programming/EclipseWork/MovieRecommender/data/items.dat");
			readOccupationFile("D:/Programming/EclipseWork/MovieRecommender/data/occupation.dat");
		}
		
		catch(Exception e)
		{
			System.out.println("Can't read "+e);
		}
	}
	private static ArrayList<Rating> ratings = new ArrayList<Rating>();
	private static ArrayList<User> users = new ArrayList<User>();
	private static ArrayList<Movie> movies = new ArrayList<Movie>();
	private static ArrayList<Occupation> occupations = new ArrayList<Occupation>();
	
	
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
				User user = new User(0,"","",0,"",null);
				user.setFirstName(lineSplits[1]); //set the firstName of object
				user.setLastName(lineSplits[2]);
				user.setAge(Integer.parseInt(lineSplits[3]));
				user.setGender(lineSplits[4]);
				user.setOccupation(new Occupation(lineSplits[5]));
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
	
	public static void readOccupationFile(String url) throws Exception
	{
		Scanner scan = new Scanner(new File(url));
		String separator = "[|]"; //separate by whitespace
		String temp;
		while (scan.hasNextLine()) //while scanner has another line
		{ 
			temp = scan.nextLine();
			String[] lineSplits = temp.split(separator); //separate terms by tab;
			if(lineSplits.length==1)
			{
				Occupation occupation = new Occupation("");
				occupation.setOccupation(lineSplits[0]); //set the firstName of object
				occupations.add(occupation); //add objects to ArrayList
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
	
	public ArrayList<Occupation> getOccupationList()
	{
		return occupations;
	}

}
