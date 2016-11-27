package movieclasses;
import java.io.Serializable;

public class Movie implements Serializable {
	
	private int movieID;
	private String title;
	private String release;
	private String url;
	

	public Movie(int movieID, String title, String release, String url)
	{
		this.movieID = movieID;
		this.title = title;
		this.release = release;
		this.url = url;
	}
	
	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "Movie: movieID=" + movieID + ", title=" + title + ", release=" + release + ", url=" + url;
	}
	
}
