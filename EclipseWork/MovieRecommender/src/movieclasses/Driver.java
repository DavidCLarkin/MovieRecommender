package movieclasses;

import java.io.File;
import java.util.Scanner;

import utils.Serializer;
import utils.XMLSerializer;

public class Driver {
	
    static File  datastore = new File("users5.xml");
    static Serializer serializer = new XMLSerializer(datastore);
    
	public static LoadData data = new LoadData();
	public static Scanner input = new Scanner(System.in);
	public static MainRecommender app = new MainRecommender(serializer);
		
	
	public Driver()
	{
	}
	
	public static void main(String[] args) throws Exception
	{
		app.load();
		data.readUserFile("D:/Programming/EclipseWork/MovieRecommender/data/users5.dat");
		data.readRatingFile("D:/Programming/EclipseWork/MovieRecommender/data/ratings5.dat");
		data.readMovieFile("D:/Programming/EclipseWork/MovieRecommender/data/items5.dat");
		runMenu();
		app.write();
	}
	
	public static void runMenu()
	{
	    System.out.println("Enter what you want to do \nChoices Below: \n 1) Add a User\n 2) Remove a User"
	    		+ "\n 3) Get movie by ID \n 4) Get Ratings by ID \n 5) Add a Movie");
	    
	    int choice = input.nextInt();
	    switch (choice) 
	    //String firstName, String lastName, int age, String gender, String occupation
	    {
	    	case 1:
	    		System.out.println("Enter a first name for the user: ");
	    		input.nextLine(); //Scanner bug
	    		String firstName = input.nextLine();
	    		System.out.println("Enter a second name for the user: ");
	    		String lastName = input.nextLine();
	    		System.out.println("Enter the age of the user: ");
	    		int age = input.nextInt();
	    		System.out.println("Enter the user's gender: ");
	    		input.nextLine(); //bug
	    		String gender = input.nextLine();
	    		System.out.println("Enter the user's occupation: ");
	    		String occupation = input.nextLine();
	    		app.addUser(firstName,lastName,age,gender,occupation);
	    		System.out.println(data.getUserList().get(5));
	    		break;
	    	case 2:
	    		System.out.println("Enter the number of the user you want to delete: ");
	    		input.nextLine();
	    		int userID = input.nextInt();
	    		app.removeUser(userID);
	    		System.out.println(data.getUserList().size());
	    		break;
	    	case 3:
	    		System.out.println("Find a movie by ID");
	    		input.nextLine();
	    		int movieID = input.nextInt();
	    		System.out.println(app.getMovie(movieID));
	    		break;
	    	case 4:
	    		System.out.println("Enter a user's ID to get the ratings from: ");
	    		input.nextLine();
	    		int usersID = input.nextInt();
	    		System.out.println(app.getUserRatings(usersID));
	    		break;
	    	case 5:
	    		System.out.println("Add a Movie: ");
	    		System.out.println("Enter the title of the Movie: ");
	    		input.nextLine();
	    		String title = input.nextLine();
	    		System.out.println("Enter the year it was released: ");
	    		int year = input.nextInt();
	    		System.out.println("Enter the IMDB url: ");
	    		input.nextLine();
	    		String url = input.nextLine();
	    		app.addMovie(title, year, url);
	    		System.out.println(data.getMovieList().get(10));
	    }
	}
}
