import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuickAutocomplete implements AutoComplete {
	
	TermList terms = new TermList();
	
	public QuickAutocomplete()
	{	 
	}
	
	@Override
	public double weightOf(String term) 
	{
		Term termLookingFor = new Term(null,0);
		for(int i = 0; i <terms.getTermList().size(); i++)
		{
			if(terms.getTermList().get(i).getTerm().equals(term)) //if the object is equal to the input term
			{
				termLookingFor = terms.getTermList().get(i); //set the Term object to the found one
			} 
		}
		return termLookingFor.getWeight(); //return the weight
	}

	@Override
	public String bestMatch(String prefix) {
		int count = 0;
		for(Term match : terms.getTermList())
		{
			if(terms.getTermList().get(count).getTerm().contains(prefix))
			{
				System.out.println(match.getTerm());
				return match.getTerm();
			}
			count++;
		}
		return "Nothing found";
	}

	@Override
	public Iterable<String> matches(String prefix, int k) 
	{
		ArrayList<String> matchesIterable = new ArrayList<String>();

		for(int i = 0; i <terms.getTermList().size(); i++)
		{
			if(terms.getTermList().get(i).getTerm().startsWith(prefix))
			{ 
				Term matchedTerm = new Term(null,0);
				matchedTerm = terms.getTermList().get(i);//set matched Term to this object.
				matchesIterable.add(matchedTerm.toString());
			}
		}
	
		int count = 0;
		ArrayList<String> finalList = new ArrayList<String>(); //make final list to add other objects
		for(String matchedTerm: matchesIterable) //for each matched term in matches ArrayList, print them
		{
		    if (count < k) {
		    	finalList.add(matchedTerm); //while count is less than input k, add those items to list.
		    }
		    count++;
		    Collections.sort(finalList, String.CASE_INSENSITIVE_ORDER);
		}
		
		return finalList; //return final list of k terms
	}
	
}

