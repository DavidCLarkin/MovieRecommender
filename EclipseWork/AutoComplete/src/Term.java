/**
 * Term Class
 * 
 * Term class to create custom objects that contain
 * the Weight and Term of each word taken from the Wiktionary.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Term {
	
	public static final Comparator<Term> BY_TERM = new ByTerm();
	public static final Comparator<Term> BY_WEIGHT = new ByWeight();
	private String term;
	private double weight;
	
	
	/**
	 * General constructor for a Term object, doesn't allow for negative weight.
	 * @param term		Word taken from file.
	 * @param weight	Weight(frequency) of the term.
	 */
	public Term(String term, double weight)
	{
		this.term = term;
		this.weight = weight;
		if(weight<=0)
		{
			setWeight(0.0); //can't be negative
		}
	}
	
	/**
	 * Gets the word for the Term object.
	 * @return the string word from Term.
	 */
	public String getTerm()
	{
		return term;
	}
	/**
	 * Gets the weight for the Term object.
	 * @return the weight of the Term(frequency)
	 */
	public double getWeight()
	{
		return weight;
	}
	
	/**
	 * Allows user to set a Term
	 * @param term New term for Term object.
	 */
	public void setTerm(String term)
	{
		this.term = term;
	}
	
	/**
	 * Allows user to set weight, can't be negative.
	 * @param weight Input number(frequency)
	 */
	public void setWeight(double weight)
	{
		if(weight>=0)
		{
			this.weight = weight;
		}
	}
	
	/**
	 * Class to compare each Term object by it's word (String) using comparator.
	 * @author David Larkin
	 *
	 */
	public static class ByTerm implements Comparator<Term>
	{
		@Override
		public int compare(Term v, Term w) 
		{
			return v.term.compareTo(w.term);
		}
	}
	
	/**
	 * Class to compare each Term object by it's weight (double) using comparator.
	 * @author David Larkin
	 *
	 */
	public static class ByWeight implements Comparator<Term>
	{
		@Override
		public int compare(Term v, Term w) 
		{
			if(v.weight > w.weight)
				return -1;
			if(v.weight < w.weight)
				return +1;
			return 0;
		}
	}

	/**
	 * toString to represent Term objects in String form.
	 */
	@Override
	public String toString() {
		return "Term [term=" + term + ", weight=" + weight + "]";
	}
}
