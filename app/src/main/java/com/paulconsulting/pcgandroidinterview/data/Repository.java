package com.paulconsulting.pcgandroidinterview.data;

import java.util.ArrayList;

/**
 *
 * Repository class that handles fetching placeHolderData from the Penguin Random House API
 *
 */

public class Repository {

    // PlaceholderData
    private ArrayList<Author> placeHolderData;

    public ArrayList<Author> getPlaceHolderData() {

        return placeHolderData;

    }

    public Repository() {

        placeHolderData = placeHolderData();

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

    public ArrayList<Author> fetchData() {

        ArrayList<Author> fetchedData;

        fetchedData = new ArrayList<>();


        // TODO: REMOVE PLACEHOLER
        for (int i = 0; i < 5; i++) {

            ArrayList<String> currentBooks = new ArrayList<>();

            currentBooks.add("Book fetched " + i);

            currentBooks.add("Book fetched " + i+1);

            Author currentAuthor = new Author("Author fetched ", "#" + i, "This is Author fetched #" + i + " spotlight", currentBooks);

            fetchedData.add(currentAuthor);


        }



        // TODO: FETCH DATA FROM THE API




        return fetchedData;

    }
}
