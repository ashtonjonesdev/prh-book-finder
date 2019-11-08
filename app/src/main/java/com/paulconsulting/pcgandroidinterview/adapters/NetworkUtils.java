package com.paulconsulting.pcgandroidinterview.adapters;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.paulconsulting.pcgandroidinterview.data.Author;

import org.json.JSONArray;
import org.json.JSONException;
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
 * Utility class to establish a network connection and query the Penguin Random House API and return a JSON response
 *
 * Also used to parse the JSON response and return an ArrayList of Authors from the parsed JSON
 *
 */

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    // Base URL for GET authors request in PRH API
    private static final String AUTHORS_BASE_URL = "https://reststop.randomhouse.com/resources/authors?";

    /// KEYS

    // Parameter for author first name
    private static final String AUTHOR_FIRST_NAME_PARAM = "firstName";

    // Parameter for author last name
    private static final String AUTHOR_LAST_NAME_PARAM = "lastName";

    /**
     *
     * Method that establishes an HTTP connection, takes the search term, and returns the JSON String response
     *
     * Called from the Worker's doWork() method
     *
     * @param queryAuthorFirstName
     * @param queryAuthorLastName
     * @return
     */
    public static String getAuthorsJSONResponse(String queryAuthorFirstName, String queryAuthorLastName) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;

        // Build the URI and issue the query

        try {

            //// Open connection and request String from Google Books API to find the author of a book

            /// Build the Uri to use in the request and convert it to a Url to use in the request

            // Build the uri using the base url and appending the search query parameters (the first name and last name)

            Uri builtURI = Uri.parse(AUTHORS_BASE_URL).buildUpon().appendQueryParameter(AUTHOR_FIRST_NAME_PARAM, queryAuthorFirstName ).appendQueryParameter(AUTHOR_LAST_NAME_PARAM, queryAuthorLastName).build();

            Log.d(LOG_TAG, "URI: " + builtURI.toString());


            // Convert the Uri to a Url
            // The Url object takes a String as the input paramter
            URL requestURL = new URL(builtURI.toString());


            /// Open the connection to the url

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            // Needed to add this to receive a JSON response instead of an XML response
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

    /**
     *
     * Parse the JSON response and return an ArrayList of Authors
     *
     * Called in the HomeFragment after the Work has finished
     *
     * @param jsonResponse
     * @return
     */

    public static ArrayList<Author> parseAuthorsJSON(String jsonResponse) {


        // If there is no JSON String, return an empty ArrayList
        if(TextUtils.isEmpty(jsonResponse)) {

            Log.d(LOG_TAG, "Did not receive a JSON String");

            return new ArrayList<>();

        }

        // Create an ArrayList<Author> that will hold the new authors created from parsing the JSON
        ArrayList<Author> foundAuthors = new ArrayList<>();




        /**
         *
         * Parse JSON the long way, iterating through the json response line by line to extract the author first name, last name, and spotlight
         *
         */


        try {



            // Create a JSONObject from the JSON response
            JSONObject jsonObject = new JSONObject(jsonResponse);

            // Create a JSONArray object that will hold the author array of the json response
            // optJSONArray will null if there is no Array found in the JSON response
            JSONArray jsonArray = jsonObject.optJSONArray("author");




            // If there is an array in the JSON object (multiple author elements), just get the first author element
            if(jsonArray != null) {

                Log.d(LOG_TAG, "JSON authors Array: " + jsonArray.toString());

                /**
                 *
                 * Perform logic to extract information from an array authors element
                 *
                 * Only find the first author element, then exit
                 *
                 */


                // Iterate through the author array, checking each author element for firstname, lastname, and spotlight
                // This loop will go through each nested author element until one is found that contains a firstname, lastname, and spotlight

                for(int i = 0; i < jsonArray.length(); i++) {

                    try {

                        // Get the author object and store it in a JSONObject
                        JSONObject authorObject= jsonArray.getJSONObject(i);

                        // Get the author first name from the authorObject
                        String authorObjectAuthorFirst = authorObject.getString("authorfirst");

                        Log.d(LOG_TAG, "Author First Name: " + authorObjectAuthorFirst);

                        // Get the author last name from the authorObject
                        String authorObjectAuthorLast = authorObject.getString("authorlast");

                        Log.d(LOG_TAG, "Author Last Name: " + authorObjectAuthorLast );

                        // Get the author spotlight
                        String authorSpotlightString = authorObject.getString("spotlight");

                        Log.d(LOG_TAG, "Author Spotlight: " + authorSpotlightString);

                        // Create a new Author object and add it to the Author array list only if there is both a first name, last name, and spotlight found
                        if( authorObjectAuthorFirst != null && authorObjectAuthorLast != null && authorSpotlightString != null) {

                            Author author = new Author(authorObjectAuthorFirst, authorObjectAuthorLast, authorSpotlightString);

                            foundAuthors.add(author);

                            Log.d(LOG_TAG, "Added Author from author array : " + foundAuthors.get(i).getAuthorfirst() + " " + foundAuthors.get(i).getAuthorlast() + " " + "Spotlight: " + authorSpotlightString);

                            // Return an ArrayList of Authors with the first Author that is found
                            // This will exit the for loop

                            return foundAuthors;


                        }


                    }  catch (Exception e) {
                        e.printStackTrace();
                    }





                } // for loop


            } // if statement, handles array logic

            // Perform logic to get the single author element data
            else {

                Log.d(LOG_TAG, "No JSON Authors Array Found!");

                // Get the author element from the JSON response
                JSONObject authorObject = jsonObject.getJSONObject("author");

                // Get the authorFirst from the authorObject
                String authorFirstString = authorObject.getString("authorfirst");

                Log.d(LOG_TAG, "Author First Name: " + authorFirstString);

                // Get the authorlast from the authorObject
                String authorLastString = authorObject.getString("authorlast");

                Log.d(LOG_TAG, "Author Last Name: " + authorLastString);

                // Get the spotlight
                String authorSpotlightString = authorObject.getString("spotlight");

                Log.d(LOG_TAG, "Author Spotlight: " + authorSpotlightString);


                // Get the works
                // This could be either an array of works with nested work elements (if there is more than one work) or just one work element


                // Create a new Author object with the firstname, lastname, and spotlight
                // Will add the Works later with a new GET request
                Author author = new Author(authorFirstString, authorLastString, authorSpotlightString);

                // Add the Author object to the foundAuthors array and return it
                foundAuthors.add(author);

                Log.d(LOG_TAG, "Added Author From Single author element: " + foundAuthors.get(0).getAuthorfirst() + " " + foundAuthors.get(0).getAuthorlast());

                // return the ArrayList with the found author
                return foundAuthors;



            } // else statement, handles single element logic









        } // try block






        catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(LOG_TAG, "Found Authors Array Size: " + foundAuthors.size());


        // Print out the Array of all Authors found
        for(Author author: foundAuthors) {

            Log.d(LOG_TAG, "Found Author " + author.getAuthorfirst() + " " + author.getAuthorlast());


        }



        // Return the ArrayList of Authors: should return a null ArrayList if the array or single element cases did not return an ArrayList
        return  foundAuthors;



    } // parse json


    /**
     *
     * Variables and methods for the GET works request, to retrieve the works (book titles) information
     *
     */

    // Base URL for GET works request in PRH API
    private static final String WORKS_BASE_URL = "https://reststop.randomhouse.com/resources/works/?";

    /// KEYS

    // QUERY PARAMETERS
    private static final String START_PARAM = "start";
    private static final String MAX_PARAM = "max";
    private static final String EXPAND_LEVEL_PARAM = "expandLevel";

    // Author's last name, used to search for his/her works
    private static final String SEARCH_PARAM = "search";


    /**
     *
     * Return the JSON response of the GET works request
     *
     */

    public static String getWorksJSONResponse(String authorlast) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String worksJSONString = null;


        try {

            //// Open connection and request String from PRH API to find the author of a book

            /// Build the Uri to use in the request and convert it to a Url to use in the request

            // Build the uri using the base url and appending the search query parameters (the start index, max items, and search String (author's last name)

            // Limit to 5 works

            Uri builtURI = Uri.parse(WORKS_BASE_URL).buildUpon().appendQueryParameter(START_PARAM, "0").appendQueryParameter(MAX_PARAM, "5").appendQueryParameter(EXPAND_LEVEL_PARAM, "1").appendQueryParameter(SEARCH_PARAM, authorlast).build();

            Log.d(LOG_TAG, "Works URI: " + builtURI.toString());


            // Convert the Uri to a Url
            // The Url object takes a String as the input parameter
            URL requestURL = new URL(builtURI.toString());


            /// Open the connection to the url

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            // Needed to add this to receive a JSON response instead of an XML response
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
                worksJSONString = builder.toString();


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
        Log.d(LOG_TAG,"Works JSON response: " + worksJSONString );




        return worksJSONString;



    }

    /**
     *
     * Parse the JSON and return an Array<String> of the works
     *
     * Called from the HomeFragment after the JSON response has been received
     *
     */

    public static ArrayList<String> parseWorksJSON(String jsonResponse) {

        // If there is no JSON response received, return an empty ArrayList
        if (TextUtils.isEmpty(jsonResponse)) {

            Log.d(LOG_TAG, "Did not receive a JSON String");

            return new ArrayList<>();

        }

        // Create a new ArrayList holding the ArrayList of works
        ArrayList<String> foundWorks = new ArrayList<>();

        /**
         *
         * Parse JSON the long way, iterating through the json response line by line to extract the author first name, last name, and spotlight
         *
         */


        try {

            JSONObject jsonObject = new JSONObject(jsonResponse);

            // Get the works Array, if there is one
            JSONArray worksArray = jsonObject.optJSONArray("work");

            /**
             *
             * Handle the case if there is a works Array
             *
             */

            if (worksArray != null) {

                Log.d(LOG_TAG, "Works Array: " + worksArray.toString());


                // Loop through the worksArray, getting each work
                // Add each work to the worksArray

                for(int i = 0; i < worksArray.length(); i++) {

                    // Get each work object
                    JSONObject workObject = worksArray.getJSONObject(i);

                    // Get the titleweb, which is the book title, of each work
                    String worksObjectTitleWeb = workObject.getString("titleweb");

                    Log.d(LOG_TAG, "Found work from works array: " + worksObjectTitleWeb);

                    // Add the titleweb to the ArrayList of Strings
                    if(worksObjectTitleWeb != null) {

                        foundWorks.add(worksObjectTitleWeb);

                        Log.d(LOG_TAG, "Added work from works array to foundWorks: " + foundWorks.get(i));

                    } // if



                } // for loop

                // Returns an ArrayList<String> found usig the works array
                return foundWorks;

            } // if logic for works array

            /**
             *
             * Handle the single work element case
             *
             */

            else {

                Log.d(LOG_TAG, "No Works Array Found");

                // Get the single work element
                JSONObject workObject = jsonObject.getJSONObject("work");

                // Get the titleweb
                String titleWeb = workObject.getString("titleweb");

                if(titleWeb != null) {

                    // Add it to the ArrayList<String>
                    foundWorks.add(titleWeb);

                    // Return the ArrayList with the single titleweb
                    return foundWorks;

                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Will only be returned if the array works case and the single work element case did not return an ArrayList
        return foundWorks;

        } //parseWorksJSON


    }
