/**
 * Movie Class
 * Class to create an instance of a Movie object
 * 
 * @author David Larkin
 * @version 09/12/2016
 */
package movieclasses;
import java.io.Serializable;

public class Movie implements Serializable {
	
	private static final long serialVersionUID = -3402763906085952570L;
	private int movieID;
	private String title;
	private String release;
	private String url;
	

	/**
	 * Constructor for Movie Object
	 * @param movieID ID of the Movie (handled internally)
	 * @param title Title of the Movie
	 * @param release Release date of the Movie
	 * @param url Url of the Movie online
	 */
	public Movie(int movieID, String title, String release, String url)
	{
		this.movieID = movieID;
		this.title = title;
		this.release = release;
		this.url = url;
	}

	/**
	 * Getter of MovieID
	 * @return the ID of the Movie
	 */
	public int getMovieID() {
		return movieID;
	}

	/**
	 * Setter for MovieID
	 * @param movieID the ID for the movie
	 */
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	/**
	 * Getter for the Title
	 * @return Title of the movie
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter for the Title
	 * @param title New title for the movie
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Getter for the Release
	 * @return release date
	 */
	public String getRelease() {
		return release;
	}

	/**
	 * Setter for Release
	 * @param release New release
	 */
	public void setRelease(String release) {
		this.release = release;
	}

	/**
	 * Getter for URL
	 * @return URL of the movie
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Setter for URL
	 * @param url New URL of the movie
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * toString Representation of the Object
	 */
	@Override
	public String toString() {
		return "Movie: movieID=" + movieID + ", title=" + title + ", release=" + release + ", url=" + url;
	}
	
}
