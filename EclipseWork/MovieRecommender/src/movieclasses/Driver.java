/**
 * Driver class
 * 
 * Used for I/O with the user using a menu driven system, 
 * calling various methods throughout the classes.
 * @author David Larkin
 * @version 09/12/2016
 */
package movieclasses;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.rules.Stopwatch;

import utils.Serializer;
import utils.XMLSerializer;

public class Driver {
	
    static File datastore = new File("users5.xml");
    static Serializer serializer = new XMLSerializer(datastore);
    
	public static LoadData data;
	public static Scanner input = new Scanner(System.in);
	public static MainRecommender app = new MainRecommender();
	
	/**
	 * Driver Constructor
	 */
	public Driver() throws Exception
	{
	}
	
	/**
	 * Main method
	 * Loads data and lists and runs the main menu
	 */
	public static void main(String[] args) throws Exception
	{
		//RUNTIMES
		//User Ratings
		/*long startTime = System.currentTimeMillis();
		//System.out.println(app.getUserRatings(235)); .1 seconds elapsed
		//long estimated = System.currentTimeMillis() - startTime; 
		System.out.println(estimated); */
		
		//User Recommendations
		/*long startTime = System.currentTimeMillis();
		System.out.println(app.getUserRecommendations(325); 1.3 seconds elapsed
		long estimated = System.currentTimeMillis() - startTime; 
		System.out.println(estimated); */
		
		//Top Ten Movies
		/*long startTime = System.currentTimeMillis();
		System.out.println(app.getTopTenMovies; 0.38 seconds elapsed
		long estimated = System.currentTimeMillis() - startTime; 
		System.out.println(estimated); */
		
		data = new LoadData();
		runMenu();
		
	}
	
	/**
	 * A menu to display options and takes in an integer
	 */
	private static int Menu()
	{
		boolean goodInput = false;
		int choice=0;
		do
		{
			try
			{
				System.out.println("---------------------------------------------------------------------|");
				System.out.println("|  Welcome to Movie Recommender				    	     |");
				System.out.println("|  Here, you can choose what option you want to do, seen below.      |");
				System.out.println("|  Simply input a number to choose your option.			     |");
				System.out.println("|--------------------------------------------------------------------|");
				System.out.println("|   1) Add a User						     |");
				System.out.println("|   2) Remove a User					     	     |");
				System.out.println("|   3) Get a Movie by ID					     |");
				System.out.println("|   4) Get Ratings by ID					     |");
				System.out.println("|   5) Add a Movie					   	     |"); 
				System.out.println("|   6) Average Rating of a Movie				     |");
				System.out.println("|   7) Top 10 Movies			     			     |"); 
				System.out.println("|   8) Add a Rating			     			     |");
				System.out.println("|   9) Get Recommendations			    		     |");
				System.out.println("|--------------------------------------------------------------------|");
				System.out.println("|   0) Exit						             |");
				System.out.println("|--------------------------------------------------------------------|");
				System.out.println("Choose your option ==>");
				choice = input.nextInt();
				goodInput = true;
			}
			catch(Exception e)
			{
				System.out.println("Enter a valid number from the choices above");
				String throwOut = input.nextLine();
			}
		} while(!goodInput);
		return choice;
	}
	
	/**
	 * Switch statement which takes an input from Menu()
	 * and executes a certain method based on it.
	 */
	public static void runMenu()
	{
		int choice = Menu();
		while(choice != 0)
		{
			switch (choice)
			{	
		    	//Add user
		    	case 1:	
		    		addUser();
		    		break;
		    	//Remove user
		    	case 2:
		    		removeUser();
		    		break;
		    	//Get a movie by ID
		    	case 3:
		    		getMovieByID();
		    		break;
		    	//Get ratings from user
		    	case 4:
		    		getRatingsByID();
		    		break;
		    	//Add new movie
		    	case 5:
		    		addMovie();
		    		break;
		    	//average rating of a movie
		    	case 6:
		    		averageRatingMovie();
		    		break;
		    	//top 10 movies
		    	case 7:
		    		System.out.println(app.getTopTenMovies());
		    		break;
		    	case 8:
		    		addRating();
		    		break;
		    	//Recommendations
		    	case 9:
		    		getRecommendations();
		    		break;

				default: 	System.err.println("You've entered an invalid number : " + choice +". Try again");
						break;
			}
			
	        System.out.println("\nPress any key to continue...");
	        input.nextLine();
	        input.nextLine(); //bug in scanner
	        choice = Menu();
		}
	       System.out.println("Exiting... bye");
	       System.exit(0);
	}

	/**
	 * Method to call the Add a user which handles exceptions
	 */
	public static void addUser()
	{
		boolean goodInput = false;
		boolean goodInputTwo = false;
		boolean answerBool = false;
		int age=0;
		String gender="";
		
		System.out.println("Enter a first name for the user: ");
		input.nextLine(); //Scanner bug
		String firstName = input.nextLine();
		System.out.println("Enter a second name for the user: ");
		String lastName = input.nextLine();
		do{
			try
			{
				System.out.println("Enter the age of the user: ");
				age = input.nextInt();
				goodInput = true;
			}
			catch(Exception e)
			{
	    		String throwOut = input.nextLine();
	    		System.err.println("Invalid number entered - try again");
			}
		} while (!goodInput);
		
		do{
			try
			{
				System.out.println("Enter the user's gender (M or F): ");
				gender = input.nextLine();
				if(gender.contains("m") || gender.contains("f") || gender.contains("M") || gender.contains("F"))
				{
					goodInputTwo = true;
				}
			}
			catch(Exception e)
			{
				String throwOut = input.nextLine();
				System.err.println("Please type 'm' or 'f'");
			}
		} while(!goodInputTwo);


		System.out.println("Enter the user's occupation: ");
		String occupation = input.nextLine();
		
		do
		{
			try
			{
				System.out.println("These are the details you entered, do you want to add this user? (Y/N) " 
						+ "\n" +new User(data.getUserList().size()+1,firstName,lastName,age,gender,new Occupation(occupation)).toString()); //print inputted data
				String answer = input.nextLine();
				if(answer.contains("y") || answer.contains("Y"))
				{
					app.addUser(firstName,lastName,age,gender,occupation);
					answerBool = true;
					System.out.println("Returning to the main menu...");
					runMenu();
				}
				else if(answer.contains("n") || answer.contains("N"))
				{
					System.out.println("Going back to the menu...(User not created)\n");
					answerBool = true;
					runMenu();
				}
			}
			catch(Exception e)
			{
				String throwOut = input.nextLine();
				System.out.println("Please type Y/N");
			}
		} while (!answerBool);
					
	}
	
	/**
	 * Method to call the Remove a user method which has exception handling
	 */
	public static void removeUser()
	{
		boolean goodInput = false;
		int userID = 0;
		System.out.println("Enter the name(s) of the users you want to choose from:(Case-Sensitive) ");
		input.nextLine();
		String userName = input.nextLine();
		
		for(int i = 0; i < data.getUserList().size(); i++)
		{	//if input contained in a first or last name
			if(data.getUserList().get(i).getFirstName().contains(userName) || data.getUserList().get(i).getLastName().contains(userName))
			{
				System.out.println(data.getUserList().get(i));
			}
		}
		do{
			try
			{
				System.out.println("\nEnter the 'UserID' of the user you want to delete: ");
				userID = input.nextInt();
				System.out.println("Removing user "+userID+"...");
				app.removeUser(userID-1); //-1 because of indexing
				System.out.println(data.getUserList().size());
				goodInput = true;
			}
			catch(Exception e)
			{
				String throwOut = input.nextLine();
				System.err.println("Please input the user's ID");
			}
		} while (!goodInput);
	}
	
	/**
	 * Method to call the MovieByID function which has exception handling
	 */
	public static void getMovieByID()
	{
		boolean goodInput = false;
		int movieID = 0;
		
		System.out.println("To get matching movies, type a keyword from the movie title: ");
		input.nextLine();
		String movieName = input.nextLine();
		//print all movies containing the input
		for(int i = 0; i < data.getMovieList().size(); i++)
		{
			if(data.getMovieList().get(i).getTitle().contains(movieName))
			{
				System.out.println("Movie ID: "+data.getMovieList().get(i).getMovieID() +", Title: "+ data.getMovieList().get(i).getTitle()+"\n");
			}
		}
		//loop until valid input
		do{
			try
			{
				System.out.println("Choose the movie you want to get, by it's ID: ");
				movieID = input.nextInt();
				goodInput = true;
				System.out.println(app.getMovie(movieID));
			}
			catch(Exception e)
			{
				String throwOut = input.nextLine();
				System.err.println("Invalid input - please enter a valid ID");
			}
		} while (!goodInput);
		
	}
	
	/**
	 * Method to call the RatingsByID function which has exception handling
	 */
	public static void getRatingsByID()
	{
		boolean goodInput = false;
		int userID = 0;
		System.out.println("Enter the name(s) of the users you want to choose from:(Case-Sensitive) ");
		input.nextLine();
		String userName = input.nextLine();
		
		for(int i = 0; i < data.getUserList().size(); i++)
		{	//if input contained in a first or last name
			if(data.getUserList().get(i).getFirstName().contains(userName) || data.getUserList().get(i).getLastName().contains(userName))
			{
				System.out.println(data.getUserList().get(i));
			}
		}
		do{
			try
			{
				System.out.println("\nEnter the 'UserID' you want to get ratings from: ");
				userID = input.nextInt();
				System.out.println("Getting "+userID+"'s ratings...");
				System.out.println(app.getUserRatings(userID));
				goodInput = true;
			}
			catch(Exception e)
			{
				String throwOut = input.nextLine();
				System.out.println("Please input the user's ID");
			}
		} while (!goodInput);
		
	}
	
	/**
	 * Method to call the Add a Movie function which has exception handling
	 */
	public static void addMovie()
	{
		boolean goodInput = false;
		int year = 0;
		
		System.out.println("Enter the title of the Movie: ");
		input.nextLine();
		String title = input.nextLine();
		do{
			try{
				System.out.println("Enter the year it was released: ");
				year = input.nextInt();
				goodInput=true;
			}
			catch(Exception e)
			{
				String throwOut = input.nextLine();
				System.err.println("Please enter an integer");
			}
		} while(!goodInput);

		System.out.println("Enter the IMDB url: ");
		input.nextLine();
		String url = input.nextLine();
		//add the movie with inputs
		app.addMovie(title, year, url);//INPUT GENRE
		//print the movie
		System.out.println(data.getMovieList().get(data.getMovieList().size()-1)+"\n");
	}
			
	/**
	 * Method to call the Average Rating function which has exception handling
	 */
	public static void averageRatingMovie()
	{
		boolean goodInput = false;
		int userID = 0;
		System.out.println("Enter a prefix of the movie you want to choose from:(Case-Sensitive) ");
		input.nextLine();
		String movieName = input.nextLine();
		
		for(int i = 0; i < data.getUserList().size(); i++)
		{	//if input contained in a first or last name
			if(data.getMovieList().get(i).getTitle().contains(movieName))
			{
				System.out.println(data.getMovieList().get(i));
			}
		}
		do{
			try{
				System.out.println("\nEnter a movie ID to get the average rating: ");
				userID = input.nextInt();
				//get the average and print it
				System.out.println("Average for "+data.getMovieList().get(userID-1).getTitle()+": "+app.averageOneMovie(userID)+"\n");
				goodInput = true;
			}
			catch(Exception e)
			{
				String throwOut = input.nextLine();
				System.err.print("Enter an integer");
			}
		} while(!goodInput);
		
		System.out.println("Press enter to return to menu");
		input.nextLine();//pause the program
		input.nextLine();
	}
	
	/**
	 * Method to call the Recommendations function which has exception handling
	 */
	public static void getRecommendations()
	{
		boolean goodInput = false;
		int userID = 0;
		System.out.println("Enter the name(s) of the users you want to choose to get recommendations from:(Case-Sensitive) ");
		input.nextLine();
		String userName = input.nextLine();
		
		for(int i = 0; i < data.getUserList().size(); i++)
		{	//if input contained in a first or last name
			if(data.getUserList().get(i).getFirstName().contains(userName) || data.getUserList().get(i).getLastName().contains(userName))
			{
				System.out.println(data.getUserList().get(i));
			}
		}
		do{
			try{
				System.out.println("Enter the user's ID");
				userID = input.nextInt();
				System.out.println("Getting recommendations based on what the user hasn't seen..");
				System.out.println(app.getUserRecommendations(userID));
				goodInput=true;
			}
			catch(Exception e)
			{
				String throwOut = input.nextLine();
				System.err.println("Please enter an integer");
			}
		} while(!goodInput);

	}
	
	/**
	 * Method to call the addRating method with exception handling
	 */
	public static void addRating()
	{
		boolean goodInput = false;
		boolean goodInputTwo = false;
		boolean goodInputThree = false;
		int movieID = 0;
		String name = "";
		int userID = 0;
		int rating = 0;
		
		System.out.println("Enter the name of the user you're adding a rating for: ");
		input.nextLine();
		name = input.nextLine();
		for(int i = 0; i < data.getUserList().size(); i++)
		{
			if(data.getUserList().get(i).getFirstName().contains(name) || data.getUserList().get(i).getLastName().contains(name))
			{
				System.out.println(data.getUserList().get(i));
			}
		}
		
		do{
			try{
				System.out.println("Enter the ID of the user you're adding a rating for: ");
				userID = input.nextInt();
				goodInputTwo = true;
			}
			catch(Exception e)
			{
				String throwOut = input.nextLine();
				System.err.println("Please enter the ID of the user");
			}
		} while(!goodInputTwo);
		
		System.out.println("Enter the title of the Movie: ");
		input.nextLine();
		String title = input.nextLine();
		for(int i = 0; i < data.getMovieList().size(); i++)
		{
			if(data.getMovieList().get(i).getTitle().contains(title))
			{
				System.out.println("Movie ID: "+data.getMovieList().get(i).getMovieID() +", Title: "+ data.getMovieList().get(i).getTitle()+"\n");
			}
		}
		
		do{
			try{
				System.out.println("Enter the ID of the movie you want to add a rating for: ");
				movieID = input.nextInt();
				goodInput = true; //while not an integer
			}
			catch(Exception e)
			{
				String throwOut = input.nextLine();
				System.err.println("Please enter the ID of the movie");
			}
		} while (!goodInput);
		
		do{
			try{
				System.out.println("Enter the rating you want to give the movie 1-10");
				rating = input.nextInt();
				goodInputThree = true;
			}
			catch(Exception e)
			{
				String throwOut = input.nextLine();
				System.err.println("Please enter a rating (1-10");
			}
		} while(!goodInputThree);
		
		app.addRating(userID, movieID, rating-5); // -5 so it fits the file structure
	}
}
