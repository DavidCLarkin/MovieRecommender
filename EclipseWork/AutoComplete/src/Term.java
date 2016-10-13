import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Term {
	
	private String term;
	private double weight;
	
	public Term(String term, double weight)
	{
		this.term = term;
		this.weight = weight;
		if(weight<=0)
		{
			setWeight(0.0);
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

	@Override
	public String toString() {
		return "Term [term=" + term + ", weight=" + weight + "]";
	}
}
