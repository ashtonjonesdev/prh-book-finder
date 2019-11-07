package com.paulconsulting.pcgandroidinterview.background;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.paulconsulting.pcgandroidinterview.adapters.NetworkUtils;
import com.paulconsulting.pcgandroidinterview.data.Author;
import com.paulconsulting.pcgandroidinterview.data.Repository;
import com.paulconsulting.pcgandroidinterview.fragments.HomeFragment;

import java.util.ArrayList;

/**
 *
 * WorkManager Worker class to get the json response from the uri
 *
 * Calls getAuthorsJSONResponse in NetworkUtils to get the JSON response given a URL
 *
 * Used to take the user's author search and get a json response based on the query
 *
 */

public class GetJSONDataWorker extends Worker {

    private static final String LOG_TAG = GetJSONDataWorker.class.getSimpleName();

    // Create a static variable for the authorsJSONResponse to access it outside of the doWork() method
    // This will be reassigned with each new query to hold the new JSON response
    private static String authorsJSONResponse;


    private ArrayList<Author> fetchedData;

    private Repository repository;

    public ArrayList<Author> getFetchedData() {
        return fetchedData;
    }

    public GetJSONDataWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

    }

    public static String getAuthorsJSONResponse() {
        return authorsJSONResponse;
    }

    /**
     *
     * Perform the HTTP request in the background and receive a JSON response
     *
     * Once the JSON response is received, assign it to the static authorsJSONResponse variable
     *
     * Receives the query parameters (the Author first name and last name) entered by the user
     *
     * Have an Observer attached to this worker in the HomeFragment; once the work is finished, we will then execute the rest of the logic to get the new JSON response in the HomeFragment and use it to then parse the JSON
     *
     *
     * @return
     */
    @NonNull
    @Override
    public Result doWork() {

        /// Get the query parameters entered by the user

        // Get the data input to the worker (the query parameters entered by the User)
        Data input = getInputData();

        // Get the Author first name
        String queryParamFirstName = input.getString(HomeFragment.QUERY_PARAM_FIRST_NAME_KEY);

        // Get the Author last name
        String queryParamLastName = input.getString(HomeFragment.QUERY_PARAM_LAST_NAME_KEY);


        // Use NetworkUtils class to get a JSON response from the query parameters
        // Update the authorsJSONResponse variable to hold the new JSON response
        authorsJSONResponse = NetworkUtils.getAuthorsJSONResponse(queryParamFirstName, queryParamLastName);

        if(authorsJSONResponse != null) {

            Log.d(LOG_TAG, "JSON response: " + authorsJSONResponse);

//            parseAuthorsJSON(authorsJSONResponse);

//            repository.fetchData(authorsJSONResponse);

            return Result.success();

        }

        else {

            Log.d(LOG_TAG, "Could not get a JSON response");

            return Result.failure();

        }


    }

}
