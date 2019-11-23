import java.io.*;

/**
 * This program reads a list of movies based on the Disney Plus listings,
 * parses it, and then generates a new list with links found to those movies.
 *
 * Author: Joshua Sanchez
 * Last Updated: 11/23/19
 */

public class MovieLinkGenerator {

    // reads the file with listed movies
    public void readMovieList() throws IOException {
        File file = new File("src/DisneyMovies.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String movieTitle;

        while( (movieTitle = br.readLine()) != null){
            System.out.println(movieTitle);
        }
    }

    // makes a search in the browser for the movie title

    // writes the link found to a file


    public static void main(String[] args) throws IOException {
        MovieLinkGenerator mlv = new MovieLinkGenerator();

        System.out.println();
        mlv.readMovieList();
    }
}
