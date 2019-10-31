package com.paulconsulting.pcgandroidinterview.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;

public class AuthorViewModel extends AndroidViewModel {

    // Reference to the Repository
    private Repository repository;

    // Cached copy of placeholder placeHolderData
    private ArrayList<Author> placeHolderData;

    // Constructor, which will instantiate an instance of Repository and assign the placeHolderData by getting it from the Repository

    public AuthorViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository();

        placeHolderData = repository.getPlaceHolderData();

    }

    public ArrayList<Author> getPlaceholderData() {

        return placeHolderData;

    }

    public ArrayList<Author> fetchData() {

        return repository.fetchData();


    }




}
