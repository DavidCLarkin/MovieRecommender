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
	    System.out.println("Enter what you want to do \nChoices Below: \n BruteAutocomplete \n 1) Get weight of Term\n 2) Search for Terms\n 3) Search for 1 Term\n"
	    		+ "----------------------\n QuickAutocomplete\n 4) Get weight of Term\n 5) Search for Terms\n 6) Search for 1 Term");
	    int choice = input.nextInt();
	    switch (choice) 
	    {
	    	//BRUTEAUTOCOMPLETE
	    	case 1:
	    		System.out.println("Enter a term to get the weight of:");
	    		input.nextLine(); //Scanner bug
	    		String BaTerm = input.nextLine();
	    		System.out.println(Ba.weightOf(BaTerm));
	    		break;
	    		
	    	case 2:
	    		boolean BaGoodInput = false;
	    		boolean BaGoodInput2 = false;
	    		String BaWord="";
	    		int BaCap=0;
	    		while(!BaGoodInput)
	    		{
		    		System.out.println("What would you like to search for?");
		    		input.nextLine();
		    		BaWord = input.nextLine();
		    		BaGoodInput=true;
	    		}
	    		while(!BaGoodInput2)
		    		try
		    		{
			    		System.out.println("How many results would you like to see(capped at, integer)");
			    		BaCap = input.nextInt();
			    		BaGoodInput2=true;
		    		}
			    	catch(Exception e)
			    	{
			    		System.out.println("Invalid number");
			    		input.next();
			    	}
		    		System.out.println(Ba.matches(BaWord, BaCap));
	    		break;
	    		
	    	case 3:
	    		System.out.println("Enter a term to search for: ");
	    		input.nextLine();
	    		String BaPrefix = input.nextLine();
	    		System.out.println(Qa.bestMatch(BaPrefix));
	    		break;
	    		
	    		//QUICKAUTOCOMPLETE
	    	case 4:
	    		System.out.println("Enter a term to get the weight of:");
	    		input.nextLine(); //Scanner bug
	    		String QaTerm = input.nextLine();
	    		System.out.println(Qa.weightOf(QaTerm));
	    		break;
	    		
	    	case 5:
	    		boolean QaGoodInput = false;
	    		boolean QaGoodInput2 = false;
	    		String QaWord="";
	    		int cap=0;
	    		while(!QaGoodInput)
	    		{
		    		System.out.println("What would you like to search for?");
		    		input.nextLine();
		    		QaWord = input.nextLine();
		    		QaGoodInput=true;
	    		}
	    		while(!QaGoodInput2)
		    		try
		    		{
			    		System.out.println("How many results would you like to see(capped at, integer)");
			    		cap = input.nextInt();
			    		QaGoodInput2=true;
		    		}
			    	catch(Exception e)
			    	{
			    		System.out.println("Invalid number");
			    		input.next();
			    	}
		    		System.out.println(Qa.matches(QaWord, cap));
	    		break;
	    		
	    	case 6:
	    		System.out.println("Enter a term to search for: ");
	    		input.nextLine();
	    		String QaPrefix = input.nextLine();
	    		System.out.println(Qa.bestMatch(QaPrefix));
	    		break;
	    }
	}

}
