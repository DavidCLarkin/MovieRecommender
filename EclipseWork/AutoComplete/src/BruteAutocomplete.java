
public class BruteAutocomplete implements AutoComplete {
	TermList terms = new TermList();
	
	public BruteAutocomplete()
	{	 
	}
	
	@Override
	public double weightOf(String term) 
	{
		return 0;
	}

	@Override
	public String bestMatch(String prefix) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<String> matches(String prefix, int k) {
		// TODO Auto-generated method stub
		return null;
	}
}
