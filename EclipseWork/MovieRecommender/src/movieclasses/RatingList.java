package movieclasses;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class RatingList {
	
	private static ArrayList<Rating> ratings = new ArrayList<Rating>();
	
	public RatingList()
	{
	}
	
	public static void readFile(String url) throws Exception
	{
		Scanner scan = new Scanner(new File("D:/Programming/EclipseWork/MovieRecommender/data/ratings5.dat"));
		String separator = "[|]"; //separate by whitespace
		String temp;
		while (scan.hasNextLine()) //while scanner has another line
		{ 
			temp = scan.nextLine();
			String[] lineSplits = temp.split(separator); //separate terms by tab;
			//System.out.println(lineSplits.length);
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
		//Collections.sort(terms, Term.BY_WEIGHT); //sort the arraylist by weight.
	}
	
	public ArrayList<Rating> getRatingList()
	{
		return ratings;
	}

}
