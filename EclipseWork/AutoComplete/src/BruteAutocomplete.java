import java.util.ArrayList;


public class BruteAutocomplete implements AutoComplete {
	
	TermList terms = new TermList();
	
	public BruteAutocomplete()
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
				//System.out.println(termLookingFor);
			} 
		}
		return termLookingFor.getWeight(); //return the weight
	}

	@Override
	public String bestMatch(String prefix) {
		Term bestMatch = new Term(null,0);
		for(int i = 0; i <terms.getTermList().size(); i++)
		{
			if(terms.getTermList().get(i).getTerm().contains(prefix))
			{
				bestMatch = terms.getTermList().get(i); //set Best match to prefix if exists.
				for(int j = 0; j < terms.getTermList().size(); j++)
					if(bestMatch.getWeight() < terms.getTermList().get(j).getWeight() && terms.getTermList().get(j).getTerm().contains(prefix)) //if bestMatch's weight is less than .get(j), than set best match to Term j
					{
						bestMatch = terms.getTermList().get(j);
					}
			}
		}
		return bestMatch.getTerm();
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
		    if(count < k) {
		    	finalList.add(matchedTerm); //while count is less than input k, add those items to list.
		    }
		    count++;
		}	
		return finalList; //return final list of k terms
	}
}
