package movieclasses;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UserList {
	
	private static ArrayList<User> users = new ArrayList<User>();
	
	public UserList()
	{
	}
	
	public static void readFile(String url) throws Exception
	{
		Scanner scan = new Scanner(new File("D:/Programming/EclipseWork/MovieRecommender/data/users5.dat"));
		String separator = "[|]"; //separate by whitespace
		String temp;
		while (scan.hasNextLine()) //while scanner has another line
		{ 
			temp = scan.nextLine();
			String[] lineSplits = temp.split(separator); //separate terms by tab;
			//System.out.println(lineSplits.length);
			if(lineSplits.length==7)
			{
				User user = new User("","",0,"","",0);
				user.setFirstName(lineSplits[1]); //set the firstName of object
				user.setLastName(lineSplits[2]);
				user.setAge(Integer.parseInt(lineSplits[3]));
				user.setGender(lineSplits[4]);
				user.setOccupation(lineSplits[5]);
				user.setUserID(Integer.parseInt(lineSplits[6]));
				users.add(user); //add objects to ArrayList
			}
			else
			{
				 throw new Exception("Invalid member length: "+lineSplits.length);
			}
		}
		scan.close();
		//Collections.sort(terms, Term.BY_WEIGHT); //sort the arraylist by weight.
	}
	
	public ArrayList<User> getUserList()
	{
		return users;
	}

}
