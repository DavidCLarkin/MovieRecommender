package movieclasses;
import java.io.Serializable;

public class Movie implements Serializable {
	
	private String title;
	private int year;
	private String url;
	

	public Movie(String title, int year, String url)
	{
		this.title = title;
		this.year = year;
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Movie: Title = " + title + ", Year = " + year + ", Url = " + url;
	}
	
}
