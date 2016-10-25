/**
 * TermList Class
 * 
 * @author David Larkin
 * @version 25/10/16
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;

public class TermList {
	
	private static ArrayList<Term> terms = new ArrayList<Term>();
	
	public TermList()
	{
	}
	
	/**
	 * Reads the file, parses through it and separates each part by any whitespace. 
	 * Uses Scanner to see if file has a next line, and if it does, keep splitting
	 * each line by spaces, and adding a new term for each section of the file.
	 * Sorts the terms list at the end by their weight.
	 * @throws IOException no file found
	 */
	public static void readFile() throws IOException
	{
		Scanner scan = new Scanner(new File("D:/Programming/EclipseWork/AutoComplete/data/wiktionary.txt"), "UTF-8");
		String separator = "\\s+"; //separate by whitespace
		String temp;
		while (scan.hasNextLine()) //while scanner has another line
		{ 
			temp = scan.nextLine();
			String[] lineSplits = temp.split(separator); //separate terms by tab;
			if(lineSplits.length==3)
			{
				Term term = new Term(null,0.0);
				term.setTerm(lineSplits[2]); //set the Term of object
				term.setWeight(Double.parseDouble(lineSplits[1])); //parse the string of numbers to a Long
				terms.add(term); //add objects to ArrayList
			}
		}
		scan.close();
		Collections.sort(terms, Term.BY_WEIGHT); //sort the arraylist by weight.
	}	

	/**
	 * Gets the List of terms
	 * @return the ArrayList of Terms
	 */
	public ArrayList<Term> getTermList()
	{
		return terms;
	}
}
