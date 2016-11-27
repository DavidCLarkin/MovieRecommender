package movieclasses;

import java.io.File;
import java.util.Scanner;
import utils.Serializer;
import utils.XMLSerializer;

public class Driver {
	
    static File datastore = new File("users5.xml");
    static Serializer serializer = new XMLSerializer(datastore);
    
	public static LoadData data;
	public static Scanner input = new Scanner(System.in);
	public static MainRecommender app = new MainRecommender(serializer);
		
	
	public Driver()
	{
	}
	
	public static void main(String[] args) throws Exception
	{
		data = new LoadData();
		app.load();
		runMenu();
		app.write();
	}
	
	
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
		    		//addRating();
		    		break;
		    	//Recommendations
		    	case 9:
		    		getRecommendations();
		    		break;

				default: 	System.out.println("You've entered an invalid number : " + choice +". Try again");
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
	/*public static void runMenu()
	{
	    System.out.println("Enter what you want to do \nChoices Below: \n 1) Add a User\n 2) Remove a User"
	    		+ "\n 3) Get movie by ID \n 4) Get Ratings by ID \n 5) Add a Movie \n 6) Average Rating of a Movie \n 7) Top 10 Movies"+
	    		"\n 8) Add a Rating \n 9) Get Recommendations");
	    
	    boolean goodInput = false;
	    int choice = input.nextInt();
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
		    		//addRating();
		    		break;
		    	//Recommendations
		    	case 9:
		    		getRecommendations();
		    		break;
		    	default:
		    		System.out.println("You've entered an invalid number, try again");
		    		break;
		    }
	    } 
	}*/
	
	//adding a user
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
	    		System.out.println("Invalid number entered - try again");
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
				System.out.println("Please type 'm' or 'f'");
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
				System.out.println("Please input the user's ID");
			}
		} while (!goodInput);
		
		runMenu();
		
	}
	
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
			else
			{
				System.out.println("No movie exists with that name\n\n Press Enter to return to menu");
				input.nextLine();
				runMenu();
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
				System.out.println("Invalid input - please enter a valid ID");
			}
		} while (!goodInput);
		
		runMenu();
	}
	
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
		
		runMenu();
	}
	
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
				System.out.println("Please enter an integer");
			}
		} while(!goodInput);

		System.out.println("Enter the IMDB url: ");
		input.nextLine();
		String url = input.nextLine();
		//add the movie with inputs
		app.addMovie(title, year, url);//INPUT GENRE
		//print the movie
		System.out.println(data.getMovieList().get(data.getMovieList().size()-1)+"\n");
		runMenu();
	}
			
	
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
				System.out.print("Enter an integer");
			}
		} while(!goodInput);
		
		System.out.println("Press enter to return to menu");
		input.nextLine();//pause the program
		input.nextLine();
		runMenu();
	}
	
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
				app.getUserRecommendations(userID);
				goodInput=true;
			}
			catch(Exception e)
			{
				String throwOut = input.nextLine();
				System.out.println("Please enter an integer");
			}
		} while(!goodInput);

	}
}
