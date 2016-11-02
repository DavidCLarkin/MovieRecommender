/**
 * QuickAutocomplete Class
 * 
 * @author David Larkin
 * @version 25/10/16
 */
import java.util.ArrayList;
import java.util.Collections;

public class QuickAutocomplete implements AutoComplete {
	
	TermList terms = new TermList();
	
	public QuickAutocomplete()
	{	 
	}
	
	/**
	 * Gets the weight of a Term by first finding a term
	 * after a term is input.
	 * @param String term - Term that the user is searching the weight of
	 * @return double - A number corresponding to the weight of the Term
	 */
	@Override
	public double weightOf(String term) 
	{
		Term termLookingFor = new Term(null,0);
		for(int i = 0; i <terms.getTermList().size(); i++)
		{
			if(terms.getTermList().get(i).getTerm().equals(term.toLowerCase())) //if the object is equal to the input term
			{
				termLookingFor = terms.getTermList().get(i); //set the Term object to the found one
			} 
		}
		return termLookingFor.getWeight(); //return the weight
	}

	/**
	 * Gets the best matched Term for a given prefix that is input by the user
	 * faster solution than BruteAutocomplete because is uses a for each loop instead of
	 * just a for loop using i and j.
	 * @param String prefix - A word that the user is searching for
	 * @return String - Finds term found suiting the prefix input, and returns the Term of the prefix.  
	 */
	@Override
	public String bestMatch(String prefix) {
		int count = 0;
		for(Term match : terms.getTermList())
		{
			if(terms.getTermList().get(count).getTerm().contains(prefix.toLowerCase()))
			{
				//System.out.println(match.getTerm());
				return match.getTerm();
			}
			count++;
		}
		return "Nothing found";
	}

	/**
	 * Gets a list of each term suiting an input prefix from the user, displaying up to k times, also an input.
	 * It uses an ArrayList data structure, in which the words are added to and the list is returned.
	 * @param String prefix - Word input from the user to search for
	 * @param int k - Integer input from the user, statement the amount of results to show.
	 * @return Returns the  list which is populated by a for each loop through the ArrayList.
	 */
	@Override
	public Iterable<String> matches(String prefix, int k) 
	{
		ArrayList<String> matchesIterable = new ArrayList<String>();

		int cnt = 0;
		for(Term matches : terms.getTermList()) //for each loop instead of for loop
		{
			if(terms.getTermList().get(cnt).getTerm().startsWith(prefix.toLowerCase()))
			{ 
				Term matchedTerm = new Term(null,0);
				matchedTerm = terms.getTermList().get(cnt);//set matched Term to this object.
				matchesIterable.add(matchedTerm.toString());
			}
			cnt++;
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

