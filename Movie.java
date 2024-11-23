import java.util.*;

public class Movie {
    private String title;
    private String genre;
    private double rating;
    private int duration;
    private String synopsis;
    
    private DatabaseOperation db = new DatabaseOperation();

    public Movie(String title, String genre, double rating, int duration, String synopsis)
    {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
        this.synopsis = synopsis;
    }

    public void insertMovie()
    {
        String sql = "INSERT INTO movies(Title, Genre, Rating, Duration, Synopsis) VALUES (?,?,?,?,?)";
        Object[] values = { title, genre, rating, duration, synopsis };
        int rowAffected = db.executeUpdate(sql, values);
        if(rowAffected > 0)
        {
            System.out.println("Movies Inserted Successfully !");
        }
        else
        {
            System.out.println("Something went wrong !");
        }
    }

    public void showMovies()
    {
        String sql = "SELECT * FROM Movies";
        List<Map<String, Object>> movies = db.getRecords(sql);
        for(Map<String, Object> movie : movies) {
                System.out.println("Movie ID : "+ movie.get("MovieID"));
                System.out.println("Movie Title : " + movie.get("Title"));
                System.out.println("Genre : " + movie.get("Genre"));
                System.out.println("Rating : " + movie.get("Rating"));
                System.out.println("Duration (in ms) : " + movie.get("Duration"));
                System.out.println("Synopsis" + movie.get("Synopsis"));
                System.out.println("--------------------------------------------------------");
        }
    }
}
