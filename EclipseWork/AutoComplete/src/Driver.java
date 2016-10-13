import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {
	
	static TermList termList = new TermList();
	
	public Driver()
	{
	}
	
	public static void main(String[] args) throws IOException
	{
		Driver app = new Driver();
		Term term = new Term(null,0);
		termList.readFile();
		System.out.println(termList.getTermList());
	}

}
