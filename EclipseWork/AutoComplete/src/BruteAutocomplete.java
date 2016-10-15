import java.util.ArrayList;
import java.util.List;

public class BruteAutocomplete implements AutoComplete {
	
	TermList terms = new TermList();
	
	public BruteAutocomplete()
	{	 
	}
	
	@Override
	public double weightOf(String term) 
	{
		Term termLookingFor = new Term(null,0);
		for(int i=0;i<terms.getTermList().size();i++)
		{
			if(terms.getTermList().get(i).getTerm().equals(term)) //if the object is equal to the input term
			{
				termLookingFor = terms.getTermList().get(i); //set the Term object to the found one
				System.out.println(termLookingFor);
			} 
		}
		return termLookingFor.getWeight(); //return the weight
	}

	@Override
	public String bestMatch(String prefix) {
		Term bestMatch = new Term(null,0);
		List<Term> matchedList = new ArrayList<Term>();
		for(int i=0;i<terms.getTermList().size();i++)
		{
			if(terms.getTermList().get(i).getTerm().contains(prefix))
			{
				bestMatch = terms.getTermList().get(i);
				matchedList.add(bestMatch);
				//System.out.println(bestMatch);
			}
		}
		return matchedList.toString();
	}

	@Override
	public Iterable<String> matches(String prefix, int k) {
		// TODO Auto-generated method stub
		return null;
	}
}
