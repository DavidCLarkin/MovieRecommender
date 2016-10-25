/**
 * Term Class
 * 
 * Used for I/O with the user using a menu driven system, 
 * calling various methods throughout the classes.
 * @author David Larkin
 * @version 25/10/2016
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
	
	public Term(String term, double weight)
	{
		this.term = term;
		this.weight = weight;
		if(weight<=0)
		{
			setWeight(0.0); //can't be negative
		}
	}
	
	public String getTerm()
	{
		return term;
	}
	
	public double getWeight()
	{
		return weight;
	}
	
	public void setTerm(String term)
	{
		this.term = term;
	}
	
	public void setWeight(double weight)
	{
		if(weight>=0)
		{
			this.weight = weight;
		}
	}
	
	public static class ByTerm implements Comparator<Term>
	{
		@Override
		public int compare(Term v, Term w) {
			return v.term.compareTo(w.term);
		}
	}
	
	public static class ByWeight implements Comparator<Term>
	{
		@Override
		public int compare(Term v, Term w) {
			if(v.weight > w.weight)
				return -1;
			if(v.weight < w.weight)
				return +1;
			return 0;
		}
	}

	@Override
	public String toString() {
		return "Term [term=" + term + ", weight=" + weight + "]";
	}
}
