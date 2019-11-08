package com.paulconsulting.pcgandroidinterview.background;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.paulconsulting.pcgandroidinterview.adapters.NetworkUtils;
import com.paulconsulting.pcgandroidinterview.fragments.HomeFragment;

/**
 *
 * WorkManager Worker class to get the json response from the uri
 *
 * Calls getWorksJSONResponse in NetworkUtils to get the JSON response given a URL
 *
 * Used to take the author's last name as a search term and get a json response based on the query
 *
 */

public class GetWorksJSONDataWorker extends Worker {

    private static final String LOG_TAG = GetWorksJSONDataWorker.class.getSimpleName();

    private static String worksJSONResponse;

    public static String getWorksJSONResponse() {
        return worksJSONResponse;
    }

    public GetWorksJSONDataWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        // Get the input data

        Data input = getInputData();

        // Get the search String

        String queryParamSearch = input.getString(HomeFragment.QUERY_PARAM_SEARCH_KEY);

        // Use the Network Utils class to get the JSON response with the given search String
        worksJSONResponse = NetworkUtils.getWorksJSONResponse(queryParamSearch);

        if(worksJSONResponse != null) {

            Log.d(LOG_TAG, "Works JSON response: " + worksJSONResponse);

            return Result.success();


        }

        else {

            Log.d(LOG_TAG, "Could not get a Works JSON response");

            return Result.failure();

        }



    }
}
