package com.paulconsulting.pcgandroidinterview.adapters;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.paulconsulting.pcgandroidinterview.data.Author;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * Utility class to establish a network connection and query the Penguin Random House API
 *
 */

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    // Base URL for PRH API
    private static final String BOOK_BASE_URL = "https://reststop.randomhouse.com/resources/authors?";

    // Parameter for author first name
    private static final String PARAM_AUTHOR_FIRST_NAME = "firstName";

    // Parameter for author last name
    private static final String PARAM_AUTHOR_LAST_NAME = "lastName";

    /**
     *
     * Method that takes the search term and returns the JSON String response
     *
     * Used in the FetchBook class (when using AsyncTask), and in BookLoader (after switching from using AsyncTask to AsyncTaskLoader)
     *
     * @param queryAuthorFirstName
     * @param queryAuthorLastName
     * @return
     */
    public static String getJSONResponse(String queryAuthorFirstName, String queryAuthorLastName) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;

        // Build the URI and issue the query

        try {

            //// Open connection and request String from Google Books API to find the author of a book

            /// Build the Uri to use in the request and convert it to a Url to use in the request

            // Build the uri using the base url and appending the search query parameters (the first name and last name)

            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon().appendQueryParameter(PARAM_AUTHOR_FIRST_NAME, queryAuthorFirstName ).appendQueryParameter(PARAM_AUTHOR_LAST_NAME, queryAuthorLastName).build();

            Log.d(LOG_TAG, "URI: " + builtURI.toString());


            // Convert the Uri to a Url
            // The Url object takes a String as the input paramter
            URL requestURL = new URL(builtURI.toString());


            /// Open the connection to the url

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.connect();

            /// Set up to read the response from the url

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Use a StringBuilder to hold the incoming response
            StringBuilder builder = new StringBuilder();

            // Read the input line by line while there is still input

            String line;
            while ((line = reader.readLine()) != null) {

                builder.append(line);
                // Since it's JSON, adding a newline isn't necessary (it won't
                // affect parsing) but it does make debugging a *lot* easier
                // if you print out the completed buffer for debugging.
                builder.append("\n");

                // Check the string to see if there is existing response content
                if(builder.length() == 0) {

                    return null;

                }

                // Convert the stringBuilder object to a String
                bookJSONString = builder.toString();

//                Log.d("NetworkUtils", bookJSONString);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            /// Close the connection and the buffered reader

            if(urlConnection != null) {
                urlConnection.disconnect();
            }
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Print the JSON response to the Log
        Log.d(LOG_TAG,"JSON response: " + bookJSONString );

        return bookJSONString;
    }

    public static ArrayList<Author> parseJson(String jsonResponse) {


        // If there is no JSON String, return an empty ArrayList
        if(TextUtils.isEmpty(jsonResponse)) {

            return new ArrayList<>();

        }

        // Create an ArrayList<Author> that will hold the new authors created from parsing the JSON
        ArrayList<Author> foundAuthors = new ArrayList<>();




        /**
         *
         * Parse JSON the long way, iterating through the json response line by line to extract the author first names and last names
         *
         */

        /**
         *
         * The JSON response is an array of author objects
         *
         */

        try {



            // Create a JSONObject from the JSON response
            JSONObject jsonObject = new JSONObject(jsonResponse);

            // Create a JSONArray object that will hold the author array of the json response
            JSONArray jsonArray = jsonObject.getJSONArray("author");

            Log.d(LOG_TAG, "JSONArray: " + jsonArray.toString());

            // Initialize the variables used for parsing the loop
            int i = 0;
//            String authorfirst = null;
//            String authorlast = null;

            // Iterate through the author array, checking each author element for firstname and lastname

            while(i < jsonArray.length()) {

                try {

                    // Get the author object and store it in a JSONObject
                    JSONObject authorObject= jsonArray.getJSONObject(i);

                    // Get the author first name from the authorObject
                    String authorObjectAuthorFirst = authorObject.getString("authorfirst");

                    Log.d(LOG_TAG, "Author First Name: " + authorObjectAuthorFirst);

                    // Get the author last name from the authorObject
                    String authorObjectAuthorLast = authorObject.getString("authorlast");

                    Log.d(LOG_TAG, "Author Last Name: " + authorObjectAuthorLast );

                    // Create a new Author object and add it to the Author array list only if there is both a first name and last name found
                    if( authorObjectAuthorFirst != null && authorObjectAuthorLast != null) {

                        Author author = new Author(authorObjectAuthorFirst, authorObjectAuthorLast);

                        foundAuthors.add(author);

                        Log.d(LOG_TAG, "Added Author : " + foundAuthors.get(i).getAuthorfirst() + " " + foundAuthors.get(i).getAuthorlast());


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Move on to the next author in the author array
                i++;



            } // while loop





        }






        catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(LOG_TAG, "Found Authors Array Size: " + foundAuthors.size());


        // Print out the Array of all Authors found
        for(Author author: foundAuthors) {

            Log.d(LOG_TAG, "Found Author " + author.getAuthorfirst() + " " + author.getAuthorlast());


        }



        // Return the ArrayList of Authors
        return  foundAuthors;



    } // parse json




}
