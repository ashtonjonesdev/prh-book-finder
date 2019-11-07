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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *
 * WorkManager Worker class to get the json response from the uri
 *
 * Used to take the user's author search and get a json response based on the query
 *
 */

public class GetJSONDataWorker extends Worker {

    private static final String LOG_TAG = GetJSONDataWorker.class.getSimpleName();

    // Create a variable for the jsonResponse to access it outside of the doWork() method
    private static String jsonResponse;


    private ArrayList<Author> fetchedData;

    private Repository repository;

    public ArrayList<Author> getFetchedData() {
        return fetchedData;
    }

    public GetJSONDataWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

    }

    public static String getJsonResponse() {
        return jsonResponse;
    }

    @NonNull
    @Override
    public Result doWork() {

        // Get the data input to the worker (the query parameters entered by the User)
        Data input = getInputData();

        String queryParamFirstName = input.getString(HomeFragment.QUERY_PARAM_FIRST_NAME_KEY);

        String queryParamLastName = input.getString(HomeFragment.QUERY_PARAM_LAST_NAME_KEY);


        // TODO: THIS IS A PLACEHOLDER TO TEST FUNCTIONALITY
        jsonResponse = NetworkUtils.getJSONResponse(queryParamFirstName, queryParamLastName);

        if(jsonResponse != null) {

            Log.d(LOG_TAG, "JSON response: " + jsonResponse);

//            parseJson(jsonResponse);

//            repository.fetchData(jsonResponse);

            return Result.success();

        }

        else {

            Log.d(LOG_TAG, "Could not get a JSON response");

            return Result.failure();

        }


    }

}
