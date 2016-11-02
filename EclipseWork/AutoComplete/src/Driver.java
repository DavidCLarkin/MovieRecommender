/**
 * Driver Class
 * 
 * Used for I/O with the user using a menu driven system, 
 * calling various methods throughout the classes.
 * @author David Larkin
 * @version 25/10/2016
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
	
	public static Scanner input = new Scanner(System.in);
	public static TermList termList = new TermList();
	public static BruteAutocomplete Ba = new BruteAutocomplete();
	public static QuickAutocomplete Qa = new QuickAutocomplete();
	
	public Driver()
	{
	}
	
	/**
	 * Reads the Wiktionary file and runs the switch statement of methods.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		Driver app = new Driver();
		termList.readFile("D:/Programming/EclipseWork/AutoComplete/data/wiktionary.txt");
		runMenu();
	}
	
	/**
	 * Runs the menu after a user chooses an option.
	 * Inherits the input and performs a function corresponding to 
	 * the numbers on the switch statement.
	 */
	public static void runMenu()
	{
	    System.out.println("Enter what you want to do \nChoices: \n 1) Get weight of Term\n 2) Search for Terms\n 3) Search for 1 Term");
	    int choice = input.nextInt();
	    switch (choice) 
	    {
	    	case 1:
	    		System.out.println("Enter a term to get the weight of:");
	    		input.nextLine(); //Scanner bug
	    		String term = input.nextLine();
	    		System.out.println(Ba.weightOf(term));
	    		break;
	    	case 2:
	    		boolean goodInput = false;
	    		boolean goodInput2 = false;
	    		String word="";
	    		int cap=0;
	    		while(!goodInput)
	    		{
		    		System.out.println("What would you like to search for?");
		    		input.nextLine();
		    		word = input.nextLine();
		    		goodInput=true;
	    		}
	    		while(!goodInput2)
		    		try
		    		{
			    		System.out.println("How many results would you like to see(capped at, integer)");
			    		cap = input.nextInt();
			    		goodInput2=true;
		    		}
			    	catch(Exception e)
			    	{
			    		System.out.println("Invalid number");
			    		input.next();
			    	}
		    		System.out.println(Ba.matches(word, cap));
	    		break;
	    		
	    	case 3:
	    		System.out.println("Enter a term to search for: ");
	    		input.nextLine();
	    		String prefix = input.nextLine();
	    		System.out.println(Ba.bestMatch(prefix));
	    }
	}

}
