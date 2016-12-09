/**
 * LoadData Class
 * Class to hold all Lists of objects like Rating, User, Movie and Occupation
 * 
 * @author David Larkin
 * @version 09/12/2016
 */
package movieclasses;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


public class LoadData implements Serializable {
	
	private static final long serialVersionUID = 6860964969879268141L;
	private static ArrayList<Rating> ratings = new ArrayList<Rating>();
	private static ArrayList<User> users = new ArrayList<User>();
	private static ArrayList<Movie> movies = new ArrayList<Movie>();
	private static ArrayList<Occupation> occupations = new ArrayList<Occupation>();
	static Scanner scan = new Scanner(System.in);
	
	/**
	 * Constructor for LoadData, tries to load each file or Reads them
	 * XML is not implemented to can't load
	 */
	public LoadData()
	{
		try
		{
			loadUsers("Users.xml");
			loadMovies("Movies.xml");
			loadRatings("Ratings.xml");
			loadOccupation("Occupations.xml");
		}
		catch(Exception e)
		{
			try {
				readUserFile("D:/Programming/EclipseWork/MovieRecommender/data/users.dat");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				readRatingFile("D:/Programming/EclipseWork/MovieRecommender/data/ratings.dat");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				readMovieFile("D:/Programming/EclipseWork/MovieRecommender/data/items.dat");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				readOccupationFile("D:/Programming/EclipseWork/MovieRecommender/data/occupation.dat");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Can't read "+e);
		}
		/*
		try{
			write("Users",users);
			write("Movies",movies);
			write("Ratings",ratings);
			write("Occupations", occupations);
		}
		catch(Exception e)
		{
			System.out.println("cant read");
		}
		*/
	}
	
	/**
	 * Method to read the Users file
	 * @param url the file location
	 * @throws Exception
	 */
	public static void readUserFile(String url) throws Exception
	{
		scan = new Scanner(new File(url));
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
		write("Users",users);
		scan.close();
	}
/**
 * Method to read the Rating file
 * @param url the url of the file location
 * @throws Exception
 */
	public static void readRatingFile(String url) throws Exception
	{
		scan = new Scanner(new File(url));
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
		write("Ratings",ratings);
		scan.close();
	}
/**
 * Method to read Movie file	
 * @param url the location of the file
 * @throws Exception
 */
	public static void readMovieFile(String url) throws Exception
	{
		scan = new Scanner(new File(url));
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
		write("Movies",movies);
		scan.close();
	}
/**
 * Method to read Occupation file
 * @param url the location of the file
 * @throws Exception
 */
	public static void readOccupationFile(String url) throws Exception
	{
		scan = new Scanner(new File(url));
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
		write("Occupations",occupations);
		scan.close();
	}
	
/**
 * Getter for RatingList
 * @return list of Ratings
 */
	public ArrayList<Rating> getRatingList()
	{
		return ratings;
	}

/**
 * Getter for UserList
 * @return List of Users
 */
	public ArrayList<User> getUserList()
	{
		return users;
	}
	
/**
 * Getter for MovieList
 * @return List of Movies
 */
	public ArrayList<Movie> getMovieList()
	{
		return movies;
	}
	
/**
 * Getter for OccupationList
 * @return List of Occupations
 */
	public ArrayList<Occupation> getOccupationList()
	{
		return occupations;
	}
	
	@SuppressWarnings("rawtypes")
	public static void write(String fileName, ArrayList list) throws IOException
	{
		ObjectOutputStream out = new ObjectOutputStream(new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(fileName+".xml"))));
		out.writeObject(list);
		out.close();
	}

	/**
	 * Load Users (Not implemented)
	 * @param file
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void loadUsers(String file) throws Exception
	{
		ObjectInputStream is = null;
		try{
			is = new ObjectInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(file))));
			users = (ArrayList<User>) is.readObject();
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
 * Load Movies (Not implemented)
 * @param file
 * @throws Exception
 */
	
	@SuppressWarnings("unchecked")
	public void loadMovies(String file) throws Exception
	{
		ObjectInputStream is = null;
		try{
			is = new ObjectInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(file))));
			movies = (ArrayList<Movie>) is.readObject();
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
 * Load Ratings (Not Implemented)
 * @param file
 * @throws Exception
 */
	@SuppressWarnings("unchecked")
	public void loadRatings(String file) throws Exception
	{
		ObjectInputStream is = null;
		try{
			is = new ObjectInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(file))));
			ratings = (ArrayList<Rating>) is.readObject();
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
 * Load Occupations(Not Implemented)
 * @param file
 * @throws Exception
 */
	@SuppressWarnings("unchecked")
	public void loadOccupation(String file) throws Exception
	{
		ObjectInputStream is = null;
		try{
			is = new ObjectInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(file))));
			occupations = (ArrayList<Occupation>) is.readObject();
		}
		finally
		{
			if(is != null)
			{
				is.close();
			}
		}
	}

}
