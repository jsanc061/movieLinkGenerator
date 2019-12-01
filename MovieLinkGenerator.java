import okhttp3.*;
import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

        File file = new File("src/DisneyMovies_Test.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String movieTitle;

        while( (movieTitle = br.readLine()) != null){
            String movieLink = searchMovie(movieTitle);
            updateList(movieLink);
        }
    }

    // makes a POST request in the browser for the movie title
    public String searchMovie(String movieTitle) throws IOException {

        String movieLink;

        // form parameters
        RequestBody formBody = new FormBody.Builder()
                .add("search", movieTitle)
                .build();

        Request request = new Request.Builder()
                .url("http://api.duckduckgo.com/?q="
                        + "putlocker "
                        + movieTitle)
                //.url("https://www.bing.com/")
                .addHeader("User-Agent", "OkHttp Bot")
                .post(formBody)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            //System.out.println(response.body().string());

//            System.out.println(
//                    request.url().toString());
//                    response.toString());

            movieLink = request.url().toString();
        }
        System.out.println(movieLink);
        return movieLink;
    }

    // writes the link found to a file
    public void updateList(String movieLink) throws IOException {
        FileWriter fileWriter = new FileWriter("src/Updated_DisneyMovies_Test.txt", true);
        PrintWriter pw = new PrintWriter(fileWriter);
        pw.println();
        pw.println(movieLink);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        MovieLinkGenerator mlv = new MovieLinkGenerator();
        System.out.println();
        mlv.readMovieList();
    }
}
