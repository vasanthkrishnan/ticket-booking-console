import java.sql.ResultSet;
import java.sql.SQLException;

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
        String sql = "INSERT INTO movies(title, genre, rating, duration, synopsis) VALUES (?,?,?,?,?)";
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
        ResultSet rs = db.getRecords(sql);
        try {
            while(rs.next())
            {
                System.out.println("Movie ID : "+ rs.getInt("MovieID"));
                System.out.println("Movie Title : " + rs.getString("Title"));
                System.out.println("Genre : " + rs.getString("Genre"));
                System.out.println("Rating : " + rs.getDouble("Rating"));
                System.out.println("Duration (in ms) : " + rs.getInt("Duration"));
                System.out.println("Synopsis" + rs.getString("Synopsis"));
                System.out.println("--------------------------------------------------------");
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
