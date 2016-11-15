package movieclasses;

import java.util.Scanner;

public class Driver {
	
	public static LoadData data = new LoadData();
	public static Scanner input = new Scanner(System.in);
	public static MainRecommender app = new MainRecommender();
	
	public Driver()
	{
	}
	
	public static void main(String[] args) throws Exception
	{
		data.readUserFile("D:/Programming/EclipseWork/MovieRecommender/data/users5.dat");
		data.readRatingFile("D:/Programming/EclipseWork/MovieRecommender/data/ratings5.dat");
		data.readMovieFile("D:/Programming/EclipseWork/MovieRecommender/data/items5.dat");
		System.out.println(data.getUserList().get(1));
		runMenu();
	}
	
	public static void runMenu()
	{
	    System.out.println("Enter what you want to do \nChoices Below: \n 1) Add a User\n 2) Remove a User"
	    		+ "\n 3) Get movie by ID");
	    
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
	    }
	}
}
