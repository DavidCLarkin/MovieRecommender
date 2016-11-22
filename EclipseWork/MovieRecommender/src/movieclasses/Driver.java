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
	
	public static void runMenu()
	{
	    System.out.println("Enter what you want to do \nChoices Below: \n 1) Add a User\n 2) Remove a User"
	    		+ "\n 3) Get movie by ID \n 4) Get Ratings by ID \n 5) Add a Movie \n 6) Average Rating of a Movie \n 7) Top 10 Movies");
	    
	    int choice = input.nextInt();
	    switch (choice) 
	    //String firstName, String lastName, int age, String gender, String occupation
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
	    		System.out.println("Find a movie by ID");
	    		input.nextLine();
	    		int movieID = input.nextInt();
	    		System.out.println(app.getMovie(movieID));
	    		break;
	    	//Get ratings from user
	    	case 4:
	    		System.out.println("Enter a user's ID to get the ratings from: ");
	    		input.nextLine();
	    		int usersID = input.nextInt();
	    		System.out.println(app.getUserRatings(usersID));
	    		break;
	    	//Add new movie
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
	    		System.out.println(data.getMovieList().get(data.getMovieList().size()-1));
	    		break;
	    	//average rating of a movie
	    	case 6:
	    		System.out.println("Enter a movie ID to get the average rating: ");
	    		int id = input.nextInt();
	    		System.out.println("Average for "+data.getMovieList().get(id-1).getTitle()+": "+app.averageOneMovie(id));
	    		break;
	    	case 7:
	    		System.out.println(app.getTopTenMovies());
	    		break;
	    	case 8:
	    		
	    }
	}
	
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
	
	//removing a user
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
			
}
