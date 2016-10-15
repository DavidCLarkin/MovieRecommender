import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
	
	public static Scanner input = new Scanner(System.in);
	public static TermList termList = new TermList();
	
	public Driver()
	{
	}
	
	public static void main(String[] args) throws IOException
	{
		BruteAutocomplete Ba = new BruteAutocomplete();
		Driver app = new Driver();
		Term term = new Term(null,0);
		termList.readFile();
		//System.out.println("Enter a term: ");
		//String word = input.nextLine();
		//System.out.println(Ba.weightOf(word)); //testing weightOf Method
		System.out.println("Enter a prefix: ");
		String prefix = input.nextLine();
		System.out.println(Ba.bestMatch(prefix)); //testing bestMatch
		
	}

}
