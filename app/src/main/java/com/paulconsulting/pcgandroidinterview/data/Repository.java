package com.paulconsulting.pcgandroidinterview.data;

import android.util.Log;


import java.util.ArrayList;

/**
 *
 * Repository class that handles fetching placeHolderData from the Penguin Random House API
 *
 */

public class Repository {

    private static final String LOG_TAG = Repository.class.getSimpleName();

    // PlaceholderData
    private ArrayList<Author> placeHolderData;

    // FetchedData
    private ArrayList<Author> fetchedData;

    //
    private ArrayList<Author> initialAuthorList;

    public void setFetchedData(ArrayList<Author> fetchedData) {
        this.fetchedData = fetchedData;
    }

    public ArrayList<Author> getPlaceHolderData() {

        return placeHolderData;

    }

    public Repository() {

        placeHolderData = placeHolderData();

        fetchedData = getFetchedData();

    }

    // TODO: IMPLEMENT
    public ArrayList<Author> placeHolderData() {

        ArrayList<Author> placeHolderData;

        placeHolderData = new ArrayList<>();

        // FETCH DATA FROM API
        // TODO: REMOVE PLACEHOLER
        for (int i = 0; i < 5; i++) {

            ArrayList<String> currentBooks = new ArrayList<>();

            currentBooks.add("Book " + i);

            currentBooks.add("Book " + i+1);

            Author currentAuthor = new Author("Author ", "#" + i, "This is Author #" + i + " spotlight", currentBooks);

            placeHolderData.add(currentAuthor);


        }

        return placeHolderData;


    }

    public ArrayList<Author> fetchedData() {


        return fetchedData;

    }

    public void initializeFetchedData() {

        ArrayList<Author> initialAuthorList = new ArrayList<Author>();

        initialAuthorList.add(new Author("Hello", "Goodbye"));

        Log.d(LOG_TAG, "Fetched Data: " + fetchedData.get(0).getAuthorfirst() + " " + fetchedData.get(0).getAuthorlast());

        fetchedData = new ArrayList<Author>();

        fetchedData = initialAuthorList;

    }

    public ArrayList<Author> getFetchedData() {
        return fetchedData;
    }
}
