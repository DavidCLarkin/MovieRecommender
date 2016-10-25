import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.net.*;
import java.io.*;

public class TermList {
	
	private static ArrayList<Term> terms = new ArrayList<Term>();
	
	public TermList()
	{
	}
	
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

	public ArrayList<Term> getTermList()
	{
		return terms;
	}
}
