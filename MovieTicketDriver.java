public class MovieTicketDriver {
    public static void main(String[] args) {
        Movie m = new Movie("LEO", "Action", 9.8, 180, "Good");
        m.insertMovie();
        m.showMovies();
    }
}
