package movieclasses;

public class Driver {
	
	public static UserList userList = new UserList();
	public static RatingList ratingList = new RatingList();
	
	
	public Driver()
	{
	}
	
	public static void main(String[] args) throws Exception
	{
		UserList.readFile("D:/Programming/EclipseWork/MovieRecommender/data/users5.dat");
		RatingList.readFile("D:/Programming/EclipseWork/MovieRecommender/data/ratings5.dat");
		System.out.println(ratingList.getRatingList().get(2));
	}

}
