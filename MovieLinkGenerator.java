import okhttp3.*;
import java.io.*;

/**
 * This program reads a list of movies based on the Disney Plus listings,
 * parses it, and then generates a new list with links found to those movies.
 *
 * Author: Joshua Sanchez
 * Last Updated: 11/23/19
 */

public class MovieLinkGenerator {

    // reusable instance of OkHttpClient
    private final OkHttpClient httpClient = new OkHttpClient();

    // reads the file with listed movies
    public void readMovieList() throws IOException {

        File file = new File("src/DisneyMovies.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String movieTitle;

        while( (movieTitle = br.readLine()) != null){
            System.out.println(movieTitle);
        }
    }

    // makes a POST request in the browser for the movie title
    public void searchMovie(String movieTitle) throws IOException {

        // form parameters
        RequestBody formBody = new FormBody.Builder()
                .add("search", movieTitle)
                .build();

        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                //.url("https://www.bing.com/")
                .addHeader("User-Agent", "OkHttp Bot")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            //System.out.println(response.body().string());

            System.out.println(response.toString());
        }
    }

    // writes the link found to a file
    public void updateList(){

    }


    public static void main(String[] args) throws IOException {
        MovieLinkGenerator mlv = new MovieLinkGenerator();

        System.out.println();
        mlv.readMovieList();
        mlv.searchMovie("WALL-E");
    }
}
